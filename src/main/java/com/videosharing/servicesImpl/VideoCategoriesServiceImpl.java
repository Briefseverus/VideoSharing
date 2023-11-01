package com.videosharing.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videosharing.models.VideoCategories;
import com.videosharing.repositories.VideoCategoriesRepository;
import com.videosharing.services.VideoCategoriesService;

@Service
public class VideoCategoriesServiceImpl implements VideoCategoriesService {
    @Autowired
    private VideoCategoriesRepository VideoCategoriesRepository;

    @Override
    public VideoCategories getVideoCategoriesById(Integer id) {
        return VideoCategoriesRepository.findById(id).orElse(null);
    }

    @Override
    public List<VideoCategories> getAllVideoCategories() {
        return VideoCategoriesRepository.findAll();
    }

    @Override
    public VideoCategories createVideoCategories(VideoCategories VideoCategories) {
        return VideoCategoriesRepository.save(VideoCategories);
    }

	@Override
	public VideoCategories updateVideoCategories(Integer id, VideoCategories videoCategories) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteVideoCategories(Integer id) {
		// TODO Auto-generated method stub
		
	}

    // Cài đặt các phương thức tương tự cho VideoCategories
}