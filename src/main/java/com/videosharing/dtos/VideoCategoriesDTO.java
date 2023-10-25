package com.videosharing.dtos;

import lombok.Data;

@Data
public class VideoCategoriesDTO {
private Integer id;
private VideoDTO video;
private CategoriesDTO categories;
}