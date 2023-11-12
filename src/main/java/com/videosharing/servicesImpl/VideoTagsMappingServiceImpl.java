package com.videosharing.servicesImpl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videosharing.models.VideoTagsMapping;
import com.videosharing.repositories.VideoTagsMappingRepository;
import com.videosharing.services.VideoTagsMappingService;

@Service
public class VideoTagsMappingServiceImpl implements VideoTagsMappingService {
    @Autowired
    private VideoTagsMappingRepository videoTagsMappingRepository;

    @Override
    public VideoTagsMapping getVideoTagsMappingById(Integer id) {
        return videoTagsMappingRepository.findById(id).orElse(null);
    }

    @Override
    public List<VideoTagsMapping> getAllVideoTagsMappings() {
        return videoTagsMappingRepository.findAll();
    }

    @Override
    public VideoTagsMapping createVideoTagsMapping(VideoTagsMapping videoTagsMapping) {
        return videoTagsMappingRepository.save(videoTagsMapping);
    }

	@Override
	public VideoTagsMapping updateVideoTagsMapping(Integer id, VideoTagsMapping videoTagsMapping) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVideoTagsMapping(Integer id) {
		// TODO Auto-generated method stub
		
	}


    // Cài đặt các phương thức tương tự cho VideoTagsMapping
}