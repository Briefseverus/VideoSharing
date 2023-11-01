package com.videosharing.servicesImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videosharing.models.Video;
import com.videosharing.models.VideoView;
import com.videosharing.repositories.VideoViewRepository;
import com.videosharing.services.VideoViewService;

@Service
public class VideoViewServiceImpl implements VideoViewService {
	@Autowired
	private VideoViewRepository videoViewRepository;

	@Override
	public int getTotalViewsForVideo(Integer videoId) {
		return videoViewRepository.countByVideoId(videoId);
	}

	@Override
	public List<VideoView> getAllVideoViewByVideoID(Integer videoId) {
		return videoViewRepository.findAllByVideoId(videoId);
	}

	@Override
	public VideoView createVideoView(String viewerIp,Video video) {
		
		VideoView videoView = new VideoView();
		videoView.setVideo(video);
		videoView.setViewerIp(viewerIp);
		videoView.setViewDatetime(new Date());

		return videoViewRepository.save(videoView);
	}


}
