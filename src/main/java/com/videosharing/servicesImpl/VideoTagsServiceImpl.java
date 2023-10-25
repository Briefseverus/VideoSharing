package com.videosharing.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videosharing.models.VideoTags;
import com.videosharing.repositories.VideoTagsRepository;
import com.videosharing.services.VideoTagsService;

@Service
public class VideoTagsServiceImpl implements VideoTagsService {
    @Autowired
    private VideoTagsRepository videoTagsRepository;

    @Override
    public VideoTags getVideoTagsById(Integer id) {
        return videoTagsRepository.findById(id).orElse(null);
    }

    @Override
    public List<VideoTags> getAllVideoTags() {
        return videoTagsRepository.findAll();
    }

    @Override
    public VideoTags createVideoTags(VideoTags videoTags) {
        return videoTagsRepository.save(videoTags);
    }

	@Override
	public VideoTags updateVideoTags(Integer id, VideoTags videoTags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVideoTags(Integer id) {
		// TODO Auto-generated method stub
		
	}

    // Cài đặt các phương thức tương tự cho VideoTags
}
