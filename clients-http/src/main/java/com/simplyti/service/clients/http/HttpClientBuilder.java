package com.simplyti.service.clients.http;

import java.util.Base64;

import com.simplyti.service.clients.ClientBuilder;
import com.simplyti.service.clients.Endpoint;

import io.netty.channel.EventLoopGroup;
import io.netty.util.CharsetUtil;

public class HttpClientBuilder extends ClientBuilder<HttpClientBuilder>{

	private EventLoopGroup eventLoopGroup;
	private Endpoint endpoint;
	private boolean checkStatusCode;
	private String authHeader;

	public HttpClientBuilder eventLoopGroup(EventLoopGroup eventLoopGroup) {
		this.eventLoopGroup = eventLoopGroup;
		return this;
	}

	public HttpClient build() {
		return new DefaultHttpClient(eventLoopGroup,endpoint,authHeader,checkStatusCode,poolConfig);
	}

	public HttpClientBuilder withEndpoint(Endpoint endpoint) {
		this.endpoint=endpoint;
		return this;
	}
	
	public HttpClientBuilder withCheckStatusCode() {
		checkStatusCode = true;
		return this;
	}

	public HttpClientBuilder withBearerAuth(String bearerAuth) {
		this.authHeader="Bearer " + bearerAuth;
		return this;
	}
	
	public HttpClientBuilder withBasicAuth(String user,String password) {
		String userpass = user+":"+password;
		this.authHeader= "Basic " + Base64.getEncoder().encodeToString(userpass.getBytes(CharsetUtil.UTF_8));
		return this;
	}

}
