package com.kruczek.infrastructure.clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient(value = "fileGenApi",
		url = "http://localhost:8102/api/docgen/smoke")
public interface FileGeneratorClient extends com.kruczek.FeignClient {

	@Override
	@RequestMapping(value = "/test", method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE)
	String call();
}
