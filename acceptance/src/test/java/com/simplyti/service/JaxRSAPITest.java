package com.simplyti.service;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;

import com.google.common.base.Joiner;
import com.simplyti.service.api.APIContext;

@Path("/jaxrs")
public class JaxRSAPITest {
	
	@GET
	public void getHello(APIContext<String> ctx) {
		ctx.send("Hello!");
	}
	
	@GET
	@Path("blocking")
	public String getHello() {
		return "Hello!";
	}
	
	@POST
	@Path("echo")
	public void echo(APIContext<String> ctx, String message) {
		ctx.send(message);
	}
	
	@GET
	@Path("/pathparams/{name}")
	public void getPathParams(APIContext<String> ctx, @PathParam("name") String name) {
		ctx.send(name);
	}

	@GET
	@Path("queryparams/primitives")
	public void getQueryParamsPromitives(APIContext<String> ctx, 
			@QueryParam("string") String string,
			@QueryParam("short") short shortValue,
			@QueryParam("int") int intValue,
			@QueryParam("double") double doubleValue,
			@QueryParam("long") long longValue,
			@QueryParam("float") float floatValue) {
		ctx.send(Joiner.on('|').join(string,shortValue,intValue,doubleValue,longValue,floatValue));
	}
	
	@GET
	@Path("queryparams")
	public void getQueryParams(APIContext<String> ctx, 
			@QueryParam("string") String string,
			@QueryParam("short") Short shortValue,
			@QueryParam("int") Integer intValue,
			@QueryParam("double") Double doubleValue,
			@QueryParam("long") Long longValue,
			@QueryParam("float") Float floatValue) {
		ctx.send(Joiner.on('|').join(string,shortValue,intValue,doubleValue,longValue,floatValue));
	}
	
	@GET
	@Path("queryparams/list")
	public void getQueryParams(APIContext<String> ctx, 
			@QueryParam("string") List<String> names) {
		ctx.send(Joiner.on(',').join(names));
	}
	
	@GET
	@Path("queryparams/default")
	public void getQueryParamDefault(APIContext<String> ctx, 
			@QueryParam("string") @DefaultValue("Hello Default!") String name) {
		ctx.send(name);
	}
	
	@GET
	@Path("throwexception")
	public void throwException(APIContext<String> ctx) {
		throw new RuntimeException("This is a test a error");
	}
	
	@GET
	@Path("failure")
	public void failure(APIContext<String> ctx,@QueryParam("msg") String msg) {
		ctx.failure(new RuntimeException(msg));
	}
	
	@GET
	@Path("blocking/throwexception")
	public void throwException() {
		throw new RuntimeException("This is a test a error");
	}
	
	@GET
	@Path("headerparam")
	public void headerParam(APIContext<String> ctx, @HeaderParam("x-my-header") String myHeader) {
		ctx.send(myHeader);
	}

}
