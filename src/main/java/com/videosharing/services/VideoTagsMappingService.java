package com.videosharing.services;

import java.util.List;

import com.videosharing.models.VideoTagsMapping;

public interface VideoTagsMappingService {
    VideoTagsMapping getVideoTagsMappingById(Integer id);
    List<VideoTagsMapping> getAllVideoTagsMappings();
    VideoTagsMapping createVideoTagsMapping(VideoTagsMapping videoTagsMapping);
	VideoTagsMapping updateVideoTagsMapping(Integer id, VideoTagsMapping videoTagsMapping);
	void deleteVideoTagsMapping(Integer id);
	List<Integer> getTagIdbyVideoId(Integer videoId);
	
}
