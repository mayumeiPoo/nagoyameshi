package com.example.nagoyameshi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.form.ReservationInputForm;
import com.example.nagoyameshi.repository.ShopRepository;

@Controller
@RequestMapping("/shop")
public class ShopController {
	private final ShopRepository shopRepository;
	
	public ShopController(ShopRepository shopRepository) {
		this.shopRepository = shopRepository;
	}
	
	@GetMapping
	
	public String index(@RequestParam(name = "price" , required = false) Integer price,
			            @RequestParam(name = "area" , required = false) String area,
			            @RequestParam(name = "category_id" , required = false) Integer category_id,
			            @RequestParam(name = "capacity" , required = false) Integer capacity,
			            @RequestParam(name = "order", required = false) String order,
			            @PageableDefault(page = 0, size = 8, sort = "id", direction = Direction.ASC) Pageable pageable,
                        Model model)
	{
		Page<Shop>shopPage;
		if (price != null) {
			 if (order != null && order.equals("priceAsc")) {
				 shopPage = shopRepository.findByPriceLessThanEqualOrderByPriceAsc(price, pageable);
             } else if (order != null && order.equals("priceDesc")){
            	 shopPage = shopRepository.findByPriceLessThanEqualOrderByPriceDesc(price, pageable);
             } else {
            	 shopPage = shopRepository.findByPriceLessThanEqualOrderByCreatedAtDesc(price, pageable);
             }
        } else if (area != null && !area.isEmpty()) {
        	if (order != null && order.equals("priceAsc")) {
            shopPage = shopRepository.findByStationLikeOrderByPriceAsc("%" + area + "%", pageable);
        	} else if (order != null && order.equals("priceDesc")){
        		shopPage = shopRepository.findByStationLikeOrderByPriceDesc("%" + area + "%", pageable);
        	} else {
        		shopPage = shopRepository.findByStationLikeOrderByCreatedAtDesc("%" + area + "%", pageable);
        	}
        } else if (category_id != null) {
        	if (order != null && order.equals("priceAsc")) {
            shopPage = shopRepository.findByCategoryIdOrderByPriceAsc(category_id , pageable);
        	} else if (order != null && order.equals("priceDesc")){
        		shopPage = shopRepository.findByCategoryIdOrderByPriceDesc(category_id , pageable);
        	} else {
        		shopPage = shopRepository.findByCategoryIdOrderByCreatedAtDesc(category_id , pageable);
        	}
        } else if(capacity != null) {
        	if (order != null && order.equals("priceAsc")) {
        		shopPage = shopRepository.findByCapacityGreaterThanEqualOrderByPriceAsc(capacity, pageable);
        	} else if (order != null && order.equals("priceDesc")){
        		shopPage = shopRepository.findByCapacityGreaterThanEqualOrderByPriceDesc(capacity, pageable);
        	} else {
            shopPage = shopRepository.findByCapacityGreaterThanEqualOrderByCreatedAtDesc(capacity, pageable);
        	}
         } else if(price != null && area != null && !area.isEmpty()){
        	 if(order != null && order.equals("priceAsc")) {
        		 shopPage = shopRepository.findByPriceLessThanEqualAndStationLikeOrderByPriceAsc(price,"%" + area + "%" ,pageable);
        	}else if (order != null && order.equals("priceDesc")) {
        		 shopPage = shopRepository.findByPriceLessThanEqualAndStationLikeOrderByPriceDesc(price,"%" + area + "%" ,pageable);
        	} else {
        		 shopPage = shopRepository.findByPriceLessThanEqualAndStationLikeOrderByCreatedAtDesc(price,"%" + area + "%" ,pageable); 
        	}
         } else if (price != null && area != null && !area.isEmpty()&& category_id != null) {
        	 if(order != null && order.equals("priceAsc")) {
        		 shopPage = shopRepository.findByPriceLessThanEqualAndStationLikeAndCategoryIdOrderByPriceAsc(price,"%" + area + "%" , category_id ,pageable);
        	 }else if (order != null && order.equals("priceDesc")) {
        		 shopPage = shopRepository.findByPriceLessThanEqualAndStationLikeAndCategoryIdOrderByPriceDesc(price,"%" + area + "%" , category_id ,pageable);
        	 }else {
        		 shopPage = shopRepository.findByPriceLessThanEqualAndStationLikeAndCategoryIdOrderByCreatedAtDesc(price,"%" + area + "%" , category_id ,pageable);
        	 }
         }else {
        	if (order != null && order.equals("priceAsc")) {
        		shopPage = shopRepository.findAllByOrderByPriceAsc(pageable);
        	} else if (order != null && order.equals("priceDesc")){
        		shopPage = shopRepository.findAllByOrderByPriceDesc(pageable);
        	} else {
            shopPage = shopRepository.findAllByOrderByCreatedAtDesc(pageable);
        } 
        	
        }
		
		model.addAttribute("shopPage",shopPage);
		model.addAttribute("price",price);
		model.addAttribute("area",area);
		model.addAttribute("caregory_id",category_id);
		model.addAttribute("capacity",capacity);
		model.addAttribute("order", order);
		
		return "shop/index";
	
	
}
	
	@GetMapping("/{id}")
    public String show(@PathVariable(name = "id") Integer id, Model model) {
        Shop shop = shopRepository.getReferenceById(id);
        
        model.addAttribute("shop", shop);
        model.addAttribute("reservationInputForm", new ReservationInputForm());
        
        
        
        return "shop/show";
	}
	
	
}
