package com.example.nagoyameshi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Review;
import com.example.nagoyameshi.entity.Shop;
import com.example.nagoyameshi.entity.User;

public interface ReviewRepository extends JpaRepository<Review,Integer>{
	public Review findByShopAndUser(Shop shop,User user);

	public Page<Review> findByShopOrderByCreatedAtDesc(Shop shop, Pageable pageable);
	public Page<Review> findByShopOrderByEvaluationDesc(Shop shop, Pageable pageable);

	
	
    public List<Review> findByShopOrderByCreatedAtDesc(Shop shop);
	

}
