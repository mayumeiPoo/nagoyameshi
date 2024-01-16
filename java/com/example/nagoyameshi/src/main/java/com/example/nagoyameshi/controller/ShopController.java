package com.example.nagoyameshi.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.nagoyameshi.entity.Shop;
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
			            @RequestParam(name = "category" , required = false) String category,
			            @RequestParam(name = "capacity" , required = false) Integer capacity,
			            @RequestParam(name = "order", required = false) String order,
			            @PageableDefault(page = 0, size = 8, sort = "id", direction = Direction.ASC) Pageable pageable,
                        Model model)
	{
		Page<Shop>shopPage;
		if (price != null) {
			 if (order != null && order.equals("priceAsc")) {
				 shopPage = shopRepository.findByPriceGreaterThanEqualOrderByPriceAsc(price, pageable);
             } else if (order != null && order.equals("priceDesc")){
            	 shopPage = shopRepository.findByPriceGreaterThanEqualOrderByPriceDesc(price, pageable);
             } else {
            	 shopPage = shopRepository.findByPriceGreaterThanEqualOrderByCreatedAtDesc(price, pageable);
             }
        } else if (area != null && !area.isEmpty()) {
        	if (order != null && order.equals("priceAsc")) {
            shopPage = shopRepository.findByStationLikeOrderByPriceAsc("%" + area + "%", pageable);
        	} else if (order != null && order.equals("priceDesc")){
        		shopPage = shopRepository.findByStationLikeOrderByPriceDesc("%" + area + "%", pageable);
        	} else {
        		shopPage = shopRepository.findByStationLikeOrderByCreatedAtDesc("%" + area + "%", pageable);
        	}
        } else if (category != null && !category.isEmpty()) {
        	if (order != null && order.equals("priceAsc")) {
            shopPage = shopRepository.findByCategoryLikeOrderByPriceAsc("%" + category + "%", pageable);
        	} else if (order != null && order.equals("priceDesc")){
        		shopPage = shopRepository.findByCategoryLikeOrderByPriceDesc("%" + category + "%", pageable);
        	} else {
        		shopPage = shopRepository.findByCategoryLikeOrderByCreatedAtDesc("%" + category + "%", pageable);
        	}
        } else if(capacity != null) {
        	if (order != null && order.equals("priceAsc")) {
        		shopPage = shopRepository.findByCapacityGreaterThanEqualOrderByPriceAsc(capacity, pageable);
        	} else if (order != null && order.equals("priceDesc")){
        		shopPage = shopRepository.findByCapacityGreaterThanEqualOrderByPriceDesc(capacity, pageable);
        	} else {
            shopPage = shopRepository.findByCapacityGreaterThanEqualOrderByPriceDesc(capacity, pageable);
        	}
        } else {
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
		model.addAttribute("caregory",category);
		model.addAttribute("capacity",capacity);
		model.addAttribute("order", order);
		
		return "shop/index";
	
	
}
}
