package com.example.nagoyameshi.service;

 import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.example.nagoyameshi.form.UserEditForm;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import com.stripe.param.checkout.SessionRetrieveParams;

import jakarta.servlet.http.HttpServletRequest;
 
 @Service
public class StripeService {
	 @Value("${stripe.api-key}")
     private String stripeApiKey;
private final UserService userService;
     
     public StripeService(UserService userService) {
         this.userService = userService;
     }    
     public String createStripeSession(String name, UserEditForm userEditForm, HttpServletRequest httpServletRequest) {
    	 Stripe.apiKey = stripeApiKey;
         String requestUrl = new String(httpServletRequest.getRequestURL());
         SessionCreateParams params =
             SessionCreateParams.builder()
                 .addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
                 .addLineItem(
                     SessionCreateParams.LineItem.builder()
                         .setPriceData(
                             SessionCreateParams.LineItem.PriceData.builder()   
                                 .setProductData(
                                     SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                         .setName(name)
                                         .build())
                                 .setUnitAmount((long)userEditForm.getAmount())
                                 .setCurrency("jpy")                                
                                 .build())
                         .setQuantity(1L)
                         .build())
                 .setMode(SessionCreateParams.Mode.PAYMENT)
                 .setSuccessUrl(requestUrl.replaceAll("/houses/[0-9]+/reservations/confirm", "") + "/reservations?reserved")
                 .setCancelUrl(requestUrl.replace("/reservations/confirm", ""))
                 .setPaymentIntentData(
                     SessionCreateParams.PaymentIntentData.builder()
                         .putMetadata("userName", userEditForm.getName().toString())
                         .putMetadata("amount", userEditForm.getAmount().toString())
                         .build())
                 .build();
         try {
             Session session = Session.create(params);
             return session.getId();
         } catch (StripeException e) {
             e.printStackTrace();
             return "";
         }
     } 
     public void processSessionCompleted(Event event) {
         Optional<StripeObject> optionalStripeObject = event.getDataObjectDeserializer().getObject();
         optionalStripeObject.ifPresent(stripeObject -> {
             Session session = (Session)stripeObject;
             SessionRetrieveParams params = SessionRetrieveParams.builder().addExpand("payment_intent").build();
 
             try {
                 session = Session.retrieve(session.getId(), params, null);
                 Map<String, String> paymentIntentObject = session.getPaymentIntentObject().getMetadata();
                 userService.updateAmount(paymentIntentObject);
             } catch (StripeException e) {
                 e.printStackTrace();
             }
         });
     }   
}