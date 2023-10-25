package com.videosharing.services;

import java.util.List;

import com.videosharing.models.VideoCategories;

public interface VideoCategoriesService {
    VideoCategories getVideoCategoriesById(Integer id);
    List<VideoCategories> getAllVideoCategoriess();
    VideoCategories createVideoCategories(VideoCategories VideoCategories);
	VideoCategories updateVideoCategories(Integer id, VideoCategories videoCategories);
	void deleteVideoCategories(Integer id);
}
