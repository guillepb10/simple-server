package com.simplyti.service.clients.k8s.pods.domain;

import com.dslplatform.json.CompiledJson;

import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(fluent=true)
public class ContainerState {
	
	private final ContainerStateTerminated terminated;
	
	@CompiledJson
	public ContainerState(ContainerStateTerminated terminated) {
		this.terminated=terminated;
	}

}
