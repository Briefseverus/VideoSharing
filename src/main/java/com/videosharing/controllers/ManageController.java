package com.videosharing.controllers;

import java.util.List;

import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.videosharing.dtos.CategoriesDTO;
import com.videosharing.dtos.ChannelDTO;
import com.videosharing.dtos.UserDTO;
import com.videosharing.dtos.VideoTagsDTO;
import com.videosharing.mappers.CategoriesMapper;
import com.videosharing.mappers.ChannelMapper;
import com.videosharing.mappers.UserMapper;
import com.videosharing.mappers.VideoTagsMapper;
import com.videosharing.services.CategoriesService;
import com.videosharing.services.ChannelService;
import com.videosharing.services.UserService;
import com.videosharing.services.VideoTagsService;
import com.videosharing.services.VideoViewService;

@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("/api/manage")
public class ManageController {

	private final UserService userService;

	private final VideoTagsService videoTagsService;

	private final VideoViewService videoViewService;

	private final CategoriesService categoriesService;

	private final ChannelService channelService;

	private final UserMapper userMapper;

	private final VideoTagsMapper videoTagsMapper;

	private final CategoriesMapper categoriesMapper;

	private final ChannelMapper channelMapper;

	public ManageController(UserService userService, VideoTagsService videoTagsService,
			VideoViewService videoViewService, CategoriesService categoriesService, ChannelService channelService,
			UserMapper userMapper, VideoTagsMapper videoTagsMapper, CategoriesMapper categoriesMapper,
			ChannelMapper channelMapper) {

		this.userService = userService;
		this.videoTagsService = videoTagsService;
		this.videoViewService = videoViewService;
		this.categoriesService = categoriesService;
		this.channelService = channelService;
		this.userMapper = userMapper;
		this.videoTagsMapper = videoTagsMapper;
		this.categoriesMapper = categoriesMapper;
		this.channelMapper = channelMapper;
	}

	// User
	@GetMapping("/users/{id}")
	public UserDTO adminGetUserById(@PathVariable Integer id) {
		return userMapper.toDTO(userService.getUserById(id));
	}

	@GetMapping("/users")
	public List<UserDTO> adminGetAllUsers() {
		return userMapper.toDTOList(userService.getAllUsers());
	}

	@Secured("ROLE_ADMIN")
	@PostMapping("/users")
	public UserDTO adminCreateUser(@RequestBody UserDTO userDTO) {
		return userMapper.toDTO(userService.createUser(userMapper.toModel(userDTO)));
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/users/{id}")
	public UserDTO adminUpdateUser(@PathVariable Integer id, @RequestBody UserDTO userDTO) {
		return userMapper.toDTO(userService.updateUser(id, userMapper.toModel(userDTO)));
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/users/{id}")
	public void adminDeleteUser(@PathVariable Integer id) {
		userService.deleteUser(id);
	}

	// VideoTag
	@Secured("ROLE_ADMIN")
	@PostMapping("/video-tags/")
	public VideoTagsDTO adminCreateVideoTags(@RequestBody VideoTagsDTO videoTagsDTO) {
		return videoTagsMapper.toDTO(videoTagsService.createVideoTags(videoTagsMapper.toModel(videoTagsDTO)));
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/video-tags/{id}")
	public VideoTagsDTO adminUpdateVideoTags(@PathVariable Integer id, @RequestBody VideoTagsDTO videoTagsDTO) {
		return videoTagsMapper.toDTO(videoTagsService.updateVideoTags(id, videoTagsMapper.toModel(videoTagsDTO)));
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/video-tags/{id}")
	public void adminDeleteVideoTags(@PathVariable Integer id) {
		videoTagsService.deleteVideoTags(id);
	}

	// Categories
	@Secured("ROLE_ADMIN")
	@PostMapping("/categories")
	public CategoriesDTO adminCreateCategory(@RequestBody CategoriesDTO categoryDTO) {
		return categoriesMapper.toDTO(categoriesService.createCategory(categoriesMapper.toModel(categoryDTO)));
	}

	@Secured("ROLE_ADMIN")
	@PutMapping("/categories/{id}")
	public CategoriesDTO adminUpdateCategory(@PathVariable Integer id, @RequestBody CategoriesDTO categoryDTO) {
		return categoriesMapper.toDTO(categoriesService.updateCategory(id, categoriesMapper.toModel(categoryDTO)));
	}

	@Secured("ROLE_ADMIN")
	@DeleteMapping("/categories/{id}")
	public void adminDeleteCategory(@PathVariable Integer id) {
		categoriesService.deleteCategory(id);
	}

	// Channels
	@Secured("ROLE_ADMIN")
	@DeleteMapping("/channels/{id}")
	public void adminDeleteChannel(@PathVariable Integer id) {
		channelService.deleteChannel(id);
	}

	@Secured("ROLE_ADMIN")
	@GetMapping
	public List<ChannelDTO> adminGetAllChannels() {
		return channelMapper.toDTOList(channelService.getAllChannels());
	}
}