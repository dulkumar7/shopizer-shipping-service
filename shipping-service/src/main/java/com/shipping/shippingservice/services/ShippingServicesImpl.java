package com.shipping.shippingservice.services;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.salesmanager.core.business.exception.ServiceException;
import com.salesmanager.core.business.services.shipping.ShippingServiceImpl;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.reference.country.Country;
import com.salesmanager.core.model.system.CustomIntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;

@Component
public class ShippingServicesImpl {

	@Autowired
	private ShippingServiceImpl shippingServiceImpl;


	public CustomIntegrationConfiguration getCustomShippingConfiguration(String moduleCode, MerchantStore store) {
		try {
			return shippingServiceImpl.getCustomShippingConfiguration(moduleCode, store);
		} catch (ServiceException e) {

		}
		return null;
	}
	
	
	public List<IntegrationModule> getShippingMethods(String isoCode) {
		try {
			MerchantStore store = new MerchantStore();
			Country country = new Country();
			country.setIsoCode(isoCode);
			store.setCountry(country);
			return shippingServiceImpl.getShippingMethods(store);
		} catch (ServiceException e) {

		}
		return null;
	}
	
	public Map<String, IntegrationConfiguration> getShippingModulesConfigured(int storeId) {
		try {
			MerchantStore store = new MerchantStore();
			store.setId(storeId);
			return shippingServiceImpl.getShippingModulesConfigured(store);
		} catch (ServiceException e) {

		}
		return null;
	}
	
	
	public IntegrationConfiguration getShippingModulesConfigured(String code, int storeId) {
		try {
			MerchantStore store = new MerchantStore();
			store.setId(storeId);
			return shippingServiceImpl.getShippingConfiguration(code, store);
		} catch (ServiceException e) {

		}
		return null;
	}
}
