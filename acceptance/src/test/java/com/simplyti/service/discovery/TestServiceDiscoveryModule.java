package com.simplyti.service.discovery;

import com.google.inject.AbstractModule;
import com.simplyti.service.gateway.ServiceDiscovery;

public class TestServiceDiscoveryModule extends AbstractModule{

	@Override
	public void configure() {
		TestServiceDiscovery discovery =TestServiceDiscovery.getInstance(); 
		requestInjection(discovery);
		bind(ServiceDiscovery.class).toInstance(discovery);
	}
	
}
