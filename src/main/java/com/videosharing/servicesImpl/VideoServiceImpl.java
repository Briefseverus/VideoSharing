package com.videosharing.servicesImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.videosharing.models.Video;
import com.videosharing.repositories.VideoRepository;
import com.videosharing.services.VideoService;

@Service
public class VideoServiceImpl implements VideoService {
    @Autowired
    private VideoRepository videoRepository;

    @Override
    public Video getVideoById(Integer id) {
        return videoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    @Override
    public Video createVideo(Video video) {
        return videoRepository.save(video);
    }

    @Override
    public Video updateVideo(Integer id, Video video) {
        Video existingVideo = videoRepository.findById(id).orElse(null);
        if (existingVideo != null) {
            // Update video properties here
            existingVideo.setTitle(video.getTitle());
            existingVideo.setDescription(video.getDescription());
            existingVideo.setFilename(video.getFilename());
            // ...
            return videoRepository.save(existingVideo);
        }
        return null;
    }

    @Override
    public void deleteVideo(Integer id) {
        videoRepository.deleteById(id);
    }
}
