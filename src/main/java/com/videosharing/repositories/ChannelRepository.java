package com.videosharing.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.videosharing.models.Channel;

@Repository
public interface ChannelRepository extends JpaRepository<Channel, Integer> {
	
}
