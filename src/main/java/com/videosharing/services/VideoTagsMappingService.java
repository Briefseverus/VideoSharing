package com.videosharing.services;

import java.util.List;

import com.videosharing.models.VideoTagsMapping;

public interface VideoTagsMappingService {
    VideoTagsMapping getVideoTagsMappingById(Integer id);
    List<VideoTagsMapping> getAllVideoTagsMappings();
    VideoTagsMapping createVideoTagsMapping(VideoTagsMapping videoTagsMapping);
    // Các phương thức tương tự cho VideoTagsMapping
	VideoTagsMapping updateVideoTagsMapping(Integer id, VideoTagsMapping videoTagsMapping);
	void deleteVideoTagsMapping(Integer id);
}
