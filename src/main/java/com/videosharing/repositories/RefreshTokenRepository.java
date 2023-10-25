package com.videosharing.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.videosharing.models.RefreshToken;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Integer> {
	Optional<RefreshToken> findByToken(String token);
}