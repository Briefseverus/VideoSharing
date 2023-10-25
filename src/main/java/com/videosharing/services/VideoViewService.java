package com.videosharing.services;

import java.util.List;

import com.videosharing.models.VideoView;

public interface VideoViewService {
    VideoView getVideoViewById(Integer id);
    List<VideoView> getAllVideoViews();
    VideoView createVideoView(VideoView videoView);
    // Các phương thức tương tự cho VideoView
	VideoView updateVideoView(Integer id, VideoView videoView);
	void deleteVideoView(Integer id);
}
