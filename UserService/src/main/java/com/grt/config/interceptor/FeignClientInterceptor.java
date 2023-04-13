package com.grt.config.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizeRequest;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientManager;
import org.springframework.stereotype.Component;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Component
public class FeignClientInterceptor implements RequestInterceptor{

	@Autowired
	OAuth2AuthorizedClientManager manager;
	
	@Override
	public void apply(RequestTemplate template) 
	{
		// 1. Fetch the Request
		OAuth2AuthorizeRequest request = OAuth2AuthorizeRequest
											.withClientRegistrationId("my-internal-client")
											.principal("internal")
											.build(); 
		
		// 2. Fetch the Token
		String token = manager.authorize(request)
								.getAccessToken()
								.getTokenValue();
		
		// 3. Add the Token in header
		template.header("Authorization", "Bearer "+ token);
		
	}

	
}
