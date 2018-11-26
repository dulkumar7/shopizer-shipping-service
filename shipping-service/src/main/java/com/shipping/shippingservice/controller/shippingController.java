package com.shipping.shippingservice.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;
import com.shipping.shippingservice.services.ShippingServicesImpl;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping(value="/shipping")
public class shippingController {
	
	@Autowired
	private ShippingServicesImpl shippingServicesImpl;
	
	
//	@GetMapping("/module-code/{moduleCode}/store-id/{storeId}")
//	public CustomIntegrationConfiguration getShipping(){
////		return shippingServices.getCustomShippingConfiguration(moduleCode, store)
//		return  null;
//	}
	
	
	@GetMapping("/methods")
	public List<IntegrationModule> getShippingMethods(@RequestParam String isoCode){
		return shippingServicesImpl.getShippingMethods(isoCode);
	}
	
	
	@GetMapping("/modules")
	public Map<String, IntegrationConfiguration> getShippingModulesConfigured(@RequestParam int merchantId){
		return shippingServicesImpl.getShippingModulesConfigured(merchantId);
	}
	
	@GetMapping("/configured-module")
	public IntegrationConfiguration getShippingModuleConfigured(@RequestParam int merchantId, @RequestParam String code){
		return shippingServicesImpl.getShippingModulesConfigured(code, merchantId);
	}

}
