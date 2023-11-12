package com.videosharing.servicesImpl;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import org.bytedeco.javacv.FFmpegFrameGrabber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.videosharing.dtos.VideoDTO;
import com.videosharing.mappers.VideoMapper;
import com.videosharing.models.Channel;
import com.videosharing.models.Video;
import com.videosharing.repositories.ChannelRepository;
import com.videosharing.repositories.VideoRepository;
import com.videosharing.repositories.VideoTagsMappingRepository;
import com.videosharing.services.VideoCategoriesService;
import com.videosharing.services.VideoService;
import com.videosharing.services.VideoTagsMappingService;

@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoRepository videoRepository;

	@Autowired
	private VideoTagsMappingRepository videoTagsMappingRepository;

	@Autowired
	private VideoCategoriesService videoCategoriesService;

	@Autowired
	private VideoTagsMappingService videoTagsMappingService;

	@Autowired
	private ChannelRepository channelRepository;

	@Autowired
	private VideoMapper videoMapper;
	
	
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
			return videoRepository.save(existingVideo);
		}
		return null;
	}

	@Autowired
	private Bucket storageClient;

	public Video uploadVideo(MultipartFile file, VideoDTO metadata) {
		String videoName = UUID.randomUUID().toString();
		Blob blob;
		try {
			blob = storageClient.create(videoName, file.getBytes(), file.getContentType());
			String videoUrl = blob.signUrl(1, TimeUnit.HOURS).toString();

			long duration;
			try {
				duration = getVideoDuration(videoUrl);
				metadata.setVideoUrl(videoUrl);
				metadata.setDuration(duration);
				Video video = videoMapper.toModel(metadata);
				return videoRepository.save(video);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static long getVideoDuration(String videoUrl) throws Exception {
	    FFmpegFrameGrabber grabber = new FFmpegFrameGrabber(videoUrl);
	    grabber.start();
	    long duration = grabber.getLengthInTime();
	    grabber.stop();
	    return duration / 1000;
	}
	@Override
	public void deleteVideo(Integer id) {
		videoRepository.deleteById(id);
	}

	public Stream<Video> getVideosByCategoriesAsStream(Integer categoryId) {
		return videoCategoriesService.getAllVideoCategories().stream()
				.filter(videoCategory -> videoCategory.getId().equals(categoryId))
				.flatMap(videoCategory -> videoCategory.getVideos().stream());
	}

	public Stream<Video> getVideosByTagsAsStream(Integer tagId) {
		return videoTagsMappingService.getAllVideoTagsMappings().stream()
				.filter(videoTagsMapping -> videoTagsMapping.getTag().getId().equals(tagId))
				.map(videoTagsMapping -> videoTagsMapping.getVideo());
	}

	@Override
	public Stream<Video> getVideosByTitleAsStream(String title) {
		List<Video> videos = videoRepository.findAll();
		Stream<Video> videosStream = videos.stream().filter(video -> video.getTitle().contains(title));

		return videosStream;
	}

	@Override
	public Stream<Video> getVideosByChannelAsStream(Integer channelId) {
		List<Video> videos = videoRepository.findAllByChannelId(channelId);
		Stream<Video> videosStream = videos.stream();
		return videosStream;
	}

	@Override
	public Stream<Video> getVideosByChannelNameAsStream(String channelName) {
		Optional<Channel> channels = channelRepository.findByName(channelName);
		return channels.stream().flatMap(channel -> videoRepository.findAllByChannelId(channel.getId()).stream());
	}
	
	@Override
	public boolean isOwner(Integer videoId, Integer userId) {
	    Video existingVideo = getVideoById(videoId);
	    if (existingVideo != null) {
	        Channel channel = existingVideo.getChannel();
	        return channel != null && channel.getCreator().getId().equals(userId);
	    }
	    return false;
	}

}
