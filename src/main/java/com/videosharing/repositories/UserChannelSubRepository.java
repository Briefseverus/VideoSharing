package com.videosharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videosharing.models.UserChannelSub;

@Repository
public interface UserChannelSubRepository extends JpaRepository<UserChannelSub, Integer> {

	boolean existsByUserIdAndChannelId(Integer userId, Integer channelId);

	void deleteByUserIdAndChannelId(Integer userId, Integer channelId);

	Integer countByAndChannelId(Integer channelId);

	}
