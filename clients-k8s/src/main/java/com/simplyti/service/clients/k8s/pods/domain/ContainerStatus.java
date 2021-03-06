package com.simplyti.service.clients.k8s.pods.domain;

import com.dslplatform.json.CompiledJson;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent=true)
public class ContainerStatus {
	
	private final Boolean ready;
	private final ContainerState state;

	@CompiledJson
	public ContainerStatus(Boolean ready,ContainerState state) {
		this.ready=ready;
		this.state=state;
	}

}
