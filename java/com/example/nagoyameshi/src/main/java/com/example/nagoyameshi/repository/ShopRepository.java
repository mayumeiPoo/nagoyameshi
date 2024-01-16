package com.example.nagoyameshi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.nagoyameshi.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop,Integer>{
	
	public Page<Shop> findByPriceGreaterThanEqualOrderByCreatedAtDesc(Integer price, Pageable pageable);
	public Page<Shop> findByPriceGreaterThanEqualOrderByPriceAsc(Integer price, Pageable pageable);
	public Page<Shop> findByPriceGreaterThanEqualOrderByPriceDesc(Integer price, Pageable pageable);
	public Page<Shop> findByStationLikeOrderByCreatedAtDesc(String area, Pageable pageable);
	public Page<Shop> findByStationLikeOrderByPriceAsc(String area, Pageable pageable);
	public Page<Shop> findByStationLikeOrderByPriceDesc(String area, Pageable pageable);
	public Page<Shop> findByCategoryLikeOrderByCreatedAtDesc(String category, Pageable pageable);
	public Page<Shop> findByCategoryLikeOrderByPriceAsc(String category, Pageable pageable);
	public Page<Shop> findByCategoryLikeOrderByPriceDesc(String category, Pageable pageable);
    public Page<Shop> findByCapacityGreaterThanEqualOrderByCreatedAtDesc(Integer capacity, Pageable pageable);
    public Page<Shop> findByCapacityGreaterThanEqualOrderByPriceAsc(Integer capacity, Pageable pageable);
    public Page<Shop> findByCapacityGreaterThanEqualOrderByPriceDesc(Integer capacity, Pageable pageable);
    public Page<Shop> findAllByOrderByCreatedAtDesc(Pageable pageable);
    public Page<Shop> findAllByOrderByPriceAsc(Pageable pageable);
    public Page<Shop> findAllByOrderByPriceDesc(Pageable pageable);
}
