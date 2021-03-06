package com.simplyti.service.builder.di.dagger;

import java.util.Map;
import java.util.Set;

import com.simplyti.service.api.builder.ApiProvider;
import com.simplyti.service.api.filter.HttpRequestFilter;
import com.simplyti.service.api.filter.HttpResponseFilter;
import com.simplyti.service.api.filter.OperationInboundFilter;
import com.simplyti.service.channel.handler.inits.HandlerInit;
import com.simplyti.service.hook.ServerStartHook;
import com.simplyti.service.hook.ServerStopHook;

import dagger.Module;
import dagger.multibindings.Multibinds;

@Module
public abstract class Multibindings {
	
	@Multibinds abstract Map<Class<?>, Object> instances();
	@Multibinds abstract Set<OperationInboundFilter> operationInboundFilters();
	@Multibinds abstract Set<ApiProvider> providers();
	
	@Multibinds abstract Set<HandlerInit> handlerInits();
	
	@Multibinds abstract Set<HttpRequestFilter> httpRequestFilters();
	@Multibinds abstract Set<HttpResponseFilter> httpResponseFilters();
	
	@Multibinds abstract Set<ServerStartHook> serverStartHooks();
	@Multibinds abstract Set<ServerStopHook> serverStopHooks();
}
