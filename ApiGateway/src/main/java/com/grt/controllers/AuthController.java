package com.grt.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grt.models.AuthResponse;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	
	private Logger logger = LoggerFactory.getLogger(AuthController.class);

	@GetMapping("/login")
	public ResponseEntity<AuthResponse> login(
			@RegisteredOAuth2AuthorizedClient("okta") OAuth2AuthorizedClient client,
			@AuthenticationPrincipal OidcUser user,
			Model model
			)
	{
		
		logger.info("User Email is: {}",user.getEmail());
		
		
		
		// 0. Create AuthResponse Object
		AuthResponse authResponse = new AuthResponse();
		
		// 1. Set UserId
		authResponse.setUserId(user.getEmail());
		
		// 2. Set accessToken
		authResponse.setAccessToken(client.getAccessToken().getTokenValue());
		
		// 3. Set refreshToken
		authResponse.setRefreshToken(client.getRefreshToken().getTokenValue());
		
		// 4. Set expireAt
		authResponse.setExpireAt(client.getAccessToken().getExpiresAt().getEpochSecond());
		
		// 5. Set authorities
		List<String> authorities = user.getAuthorities()
											.stream()
												.map(grantedAuthority -> grantedAuthority.getAuthority() )
													.collect(Collectors.toList());
		
		
		authResponse.setAuthorities(authorities);
		
		return ResponseEntity.status(HttpStatus.OK).body(authResponse);		
		
	}
}
