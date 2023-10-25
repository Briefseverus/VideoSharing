package com.videosharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.dtos.AuthRequest;
import com.videosharing.dtos.JwtResponse;
import com.videosharing.dtos.RefreshTokenRequest;
import com.videosharing.dtos.UserDTO;
import com.videosharing.jwt.JwtService;
import com.videosharing.jwt.RefreshTokenService;
import com.videosharing.mappers.UserMapper;
import com.videosharing.models.RefreshToken;
import com.videosharing.models.User;
import com.videosharing.services.UserService;

@RestController
@RequestMapping("/login")
public class AuthenticateController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private RefreshTokenService refreshTokenService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private UserService userService;

	@GetMapping
	public String login() {
		return "login";
	}

	@PostMapping
	public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			return jwtService.generateToken(authRequest.getUsername());
		} else {
			throw new UsernameNotFoundException("invalid user request !");
		}

	}

	@PostMapping("/sign-up")
	public ResponseEntity<?> createUser(@RequestBody UserDTO userDTO) {

		try {

			User user = UserMapper.toModel(userDTO);
			userService.createUser(user);

			UserDTO createdUser = UserMapper.toDTO(user);
			return ResponseEntity.ok(createdUser);

		} catch (RuntimeException e) {

			return ResponseEntity.badRequest().body(e.getMessage());

		}

	}

	@PostMapping("/refreshToken")
	public JwtResponse refreshToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
		return refreshTokenService.findByToken(refreshTokenRequest.getToken())
				.map(refreshTokenService::verifyExpiration).map(RefreshToken::getUser).map(userInfo -> {
					String accessToken = jwtService.generateToken(userInfo.getUsername());
					return JwtResponse.builder().accessToken(accessToken).token(refreshTokenRequest.getToken()).build();
				}).orElseThrow(() -> new RuntimeException("Refresh token is not in database!"));
	}

}
