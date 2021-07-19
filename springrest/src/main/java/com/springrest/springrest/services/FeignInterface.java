package com.springrest.springrest.services;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "marvel",url = "http://localhost:2021/rambo")
	public interface FeignInterface {

		@GetMapping(path = "/fetch",produces = "application/json")
		public String getDetails();
	}



