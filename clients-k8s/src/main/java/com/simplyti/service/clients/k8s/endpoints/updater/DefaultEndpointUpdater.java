package com.simplyti.service.clients.k8s.endpoints.updater;

import java.util.Collections;

import com.simplyti.service.api.serializer.json.Json;
import com.simplyti.service.clients.http.HttpClient;
import com.simplyti.service.clients.k8s.K8sAPI;
import com.simplyti.service.clients.k8s.common.updater.AbstractK8sResourceUpdater;
import com.simplyti.service.clients.k8s.common.updater.JsonPatch;
import com.simplyti.service.clients.k8s.endpoints.builder.DefaultEndpointSubsetBuilder;
import com.simplyti.service.clients.k8s.endpoints.builder.EndpointSubsetBuilder;
import com.simplyti.service.clients.k8s.endpoints.builder.SubsetHolder;
import com.simplyti.service.clients.k8s.endpoints.domain.Endpoint;
import com.simplyti.service.clients.k8s.endpoints.domain.Subset;

public class DefaultEndpointUpdater extends AbstractK8sResourceUpdater<Endpoint> implements EndpointUpdater, SubsetHolder<DefaultEndpointUpdater> {

	private boolean adding;

	public DefaultEndpointUpdater(HttpClient client,Json json, K8sAPI api, String namespace, String resource, String name) {
		super(client,json, api, namespace, resource, name, Endpoint.class);
	}
	
	@Override
	public EndpointSubsetBuilder<? extends EndpointUpdater> addSubset() {
		this.adding=true;
		return new DefaultEndpointSubsetBuilder<>(this);
	}

	@Override
	public EndpointSubsetBuilder<? extends EndpointUpdater> setSubset() {
		this.adding=false;
		return new DefaultEndpointSubsetBuilder<>(this);
	}

	@Override
	public DefaultEndpointUpdater addSubset(Subset subset) {
		if(adding) {
			addPatch(JsonPatch.add("/subsets/-", subset));
		}else {
			addPatch(JsonPatch.replace("/subsets", Collections.singleton(subset)));
		}
		return this;
	}

	@Override
	public EndpointUpdater clearSubsets() {
		setPatch(JsonPatch.replace("/subsets", Collections.emptyList()));
		return this;
	}
	
	


}
