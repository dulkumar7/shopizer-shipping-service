package com.salesmanager.shop.restclients;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.esotericsoftware.minlog.Log;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.salesmanager.core.model.customer.Customer;
import com.salesmanager.core.model.merchant.MerchantStore;
import com.salesmanager.core.model.shoppingcart.ShoppingCart;
import com.salesmanager.core.model.system.IntegrationConfiguration;
import com.salesmanager.core.model.system.IntegrationModule;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ShippingServiceClient {

	private String url = "http://localhost:8082/shipping/";

//	@HystrixCommand(fallbackMethod = "getShoppingCartByCustomer")
	
	public List<IntegrationModule> getShippingMethods(final MerchantStore merchantStore) {
		
		String uri =url + "methods?isoCode=" + merchantStore.getCountry().getIsoCode();
		
		Log.info("+++++++++++++++++++++++++  Invoking external service:- ", uri);
		Log.info("+++++++++++++++++++++++++  service method:- GET");
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-", "application/json;Charset-UTF-8");
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		ResponseEntity<List<IntegrationModule>> response = restTemplate.exchange(uri, HttpMethod.GET,
				new HttpEntity<>(null, headers), new ParameterizedTypeReference<List<IntegrationModule>>() {
				});
		return response.getBody();
	}

//	@HystrixCommand(fallbackMethod = "getByCodeAndMarchant")
	public IntegrationConfiguration getShippingConfiguration(String code, MerchantStore merchantStore) {
		ResponseEntity<ObjectNode> objectNode = invokeService(url +"configured-module?code=" + code
				+ "&merchantId="+merchantStore.getId(), HttpMethod.GET, null);;
		if(null != objectNode && null != objectNode.getBody()){
			return parseIntegrationConfiguration(objectNode.getBody());
		}
		return null;
	}


	public Map<String, IntegrationConfiguration> getShippingModulesConfigured(final MerchantStore merchantStore) {
		ResponseEntity<ObjectNode> objectNode = invokeService(url +"modules?merchantId="+merchantStore.getId(), HttpMethod.GET, null);
		if(null != objectNode && null != objectNode.getBody()){
			return parseIntegrationConfigurations(objectNode.getBody());
		}
		return null;
	}

	public void deleteShoppingCart(final long itemId) {
		invokeService(url + itemId, HttpMethod.DELETE, null);
	}
	public ResponseEntity<ObjectNode> invokeService(String uri, HttpMethod httpMethod, ObjectNode reqestObject) {
		Log.info("+++++++++++++++++++++++++  Invoking external service:- ", uri);
		Log.info("+++++++++++++++++++++++++  service method:- ", httpMethod.toString());
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-", "application/json;Charset-UTF-8");
		restTemplate.getMessageConverters().add(new StringHttpMessageConverter());
		ResponseEntity<ObjectNode> shoppingCart = restTemplate.exchange(uri, httpMethod,
				new HttpEntity<>(reqestObject, headers), ObjectNode.class);
		if (null != shoppingCart && null != shoppingCart.getBody()) {
			Log.info("+++++++++++++++++++++++++ Response back from external service:- {}",
					shoppingCart.getBody().toString());
			Log.info("+++++++++++++++++++++++++  Http Rsponse back from external service:- {}",
					shoppingCart.getStatusCode().toString());
			return shoppingCart;

		}
		return null;
	}


	private ObjectNode parseObjectNode(final Object obj) {
		ObjectMapper mapper = new ObjectMapper();
		return mapper.convertValue(obj, ObjectNode.class);
	}
	
	private List<IntegrationModule> parseIntegrationModules(ObjectNode objectNode){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return Arrays.asList(mapper.readValue(objectNode.toString(), IntegrationModule[].class));
		} catch (IOException e) {
			Log.info("error occured while parsing into collection of IntegrationModule");
			return null;
		}
	}
	
	private IntegrationConfiguration parseIntegrationConfiguration(ObjectNode objectNode){
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.readValue(objectNode.toString(), IntegrationConfiguration.class);
		} catch (IOException e) {
			Log.info("error occured while parsing into collection of IntegrationModule");
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	private Map<String,IntegrationConfiguration> parseIntegrationConfigurations(ObjectNode objectNode){
		ObjectMapper mapper = new ObjectMapper();
		if(2 < StringUtils.length(objectNode.toString())){
			return mapper.convertValue(objectNode.toString(), Map.class);
		}
		return null;
	}
	
}
