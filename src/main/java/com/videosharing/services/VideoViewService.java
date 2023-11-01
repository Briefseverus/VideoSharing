package com.videosharing.services;

import java.util.List;

import com.videosharing.models.Video;
import com.videosharing.models.VideoView;

public interface VideoViewService {

    
	int getTotalViewsForVideo(Integer videoId);

	List<VideoView> getAllVideoViewByVideoID(Integer videoId);

	VideoView createVideoView(String viewerIp,Video video);
}
