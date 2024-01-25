package com.example.nagoyameshi.service;

import org.springframework.stereotype.Service;

@Service
public class ReservationService {
	
	public boolean isWithinCapacity(Integer numberOfPeople, Integer capacity) {
        return numberOfPeople <= capacity;

}
}
