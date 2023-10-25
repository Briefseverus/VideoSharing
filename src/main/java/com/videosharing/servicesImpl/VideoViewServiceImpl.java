package com.videosharing.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videosharing.models.VideoView;
import com.videosharing.repositories.VideoViewRepository;
import com.videosharing.services.VideoViewService;

@Service
public class VideoViewServiceImpl implements VideoViewService {
	@Autowired
	private VideoViewRepository videoViewRepository;

	@Override
	public VideoView getVideoViewById(Integer id) {
		return videoViewRepository.findById(id).orElse(null);
	}

	@Override
	public List<VideoView> getAllVideoViews() {
		return videoViewRepository.findAll();
	}

	@Override
	public VideoView createVideoView(VideoView videoView) {
		return videoViewRepository.save(videoView);
	}

	@Override
	public VideoView updateVideoView(Integer id, VideoView videoView) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVideoView(Integer id) {
		// TODO Auto-generated method stub
		
	}

	// Cài đặt các phương thức tương tự cho VideoView
}
