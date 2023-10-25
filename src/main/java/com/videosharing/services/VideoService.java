package com.videosharing.services;

import java.util.List;

import com.videosharing.models.Video;

public interface VideoService {
    Video getVideoById(Integer id);
    List<Video> getAllVideos();
    Video createVideo(Video video);
    Video updateVideo(Integer id, Video video);
    void deleteVideo(Integer id);
}
