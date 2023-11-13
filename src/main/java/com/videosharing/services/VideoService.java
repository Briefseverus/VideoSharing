package com.videosharing.services;

import java.util.List;
import java.util.stream.Stream;

import org.springframework.web.multipart.MultipartFile;

import com.videosharing.dtos.VideoDTO;
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
	Video uploadVideo(MultipartFile file, VideoDTO videoDTO);
	boolean isOwner(Integer videoId, Integer userId);
	Stream<Video> getVideosByCategoriesAsStream(List<Integer> categoryIds);
	Stream<Video> getVideosByTagsAsStream(List<Integer> tagIds);
	Stream<Video> getSimilarVideos(Integer videoId);
    
}
