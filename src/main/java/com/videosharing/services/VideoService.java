package com.videosharing.services;

import java.util.List;
import java.util.stream.Stream;

import com.videosharing.dtos.VideoSummaryDTO;
import com.videosharing.models.Video;

public interface VideoService {
    Video getVideoById(Integer id);
    List<Video> getAllVideos();
    Video createVideo(Video video);
    Video updateVideo(Integer id, Video video);
    void deleteVideo(Integer id);
    Stream<Video> getVideosByTitleAsStream(String title);
    Stream<Video> getVideosByChannelAsStream(Integer channelId);
	Stream<Video> getVideosByTagsAsStream(Integer tagId);
	Stream<Video> getVideosByCategoriesAsStream(Integer categoryId);
	Stream<Video> getVideosByChannelNameAsStream(String channelName);
	
    
}
