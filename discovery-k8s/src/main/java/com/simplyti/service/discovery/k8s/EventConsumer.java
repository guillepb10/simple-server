package com.simplyti.service.discovery.k8s;

import com.simplyti.service.clients.k8s.common.K8sResource;
import com.simplyti.service.clients.k8s.common.watch.domain.EventType;

import io.netty.util.concurrent.Promise;

public interface EventConsumer<T extends K8sResource> {
	
	public void accept (EventType type, T object, Promise<Void> promise);

}
