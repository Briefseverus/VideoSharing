package com.videosharing.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.models.Video;
import com.videosharing.services.VideoService;
import com.videosharing.services.VideoViewService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/video-views")
public class VideoViewController {

	@Autowired
	private VideoViewService videoViewService;

	@Autowired
	VideoService videoServices;

	@GetMapping("/{id}")
	public int getTotalViewsForVideo(@PathVariable Integer id) {
		return videoViewService.getTotalViewsForVideo(id);
	}

	@PostMapping("/{id}")
	public void createVideoView(@PathVariable Integer id, HttpServletRequest request) {
		String viewerIp = request.getRemoteAddr();
		Video video = videoServices.getVideoById(id);
		videoViewService.createVideoView(viewerIp, video);

	}
}