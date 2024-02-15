package com.example.nagoyameshi.service;

import org.springframework.beans.factory.annotation.Value;

import com.example.nagoyameshi.form.UserEditForm;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

import jakarta.servlet.http.HttpServletRequest;

public class StripeService {
	 @Value("${stripe.api-key}")
     private String stripeApiKey;
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
                               
                                .setCurrency("jpy")                                
                                .build())
                        .setQuantity(1L)
                        .build())
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(requestUrl.replaceAll("/subscription", ""))
                .setCancelUrl(requestUrl.replace("/subscription", ""))
                .setPaymentIntentData(
                    SessionCreateParams.PaymentIntentData.builder()
                        .putMetadata("name", userEditForm.getName().toString()
                        .build())
                .build());
        try {
            Session session = Session.create(params);
            return session.getId();
        } catch (StripeException e) {
            e.printStackTrace();
            return "";
        }
    } 

	}


