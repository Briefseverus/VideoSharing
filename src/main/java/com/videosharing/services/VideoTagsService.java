package com.videosharing.services;

import java.util.List;

import com.videosharing.models.VideoTags;

public interface VideoTagsService {
    VideoTags getVideoTagsById(Integer id);
    List<VideoTags> getAllVideoTags();
    VideoTags createVideoTags(VideoTags videoTags);
	VideoTags updateVideoTags(Integer id, VideoTags videoTags);
	void deleteVideoTags(Integer id);
}
