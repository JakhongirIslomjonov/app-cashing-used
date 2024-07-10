package com.example.appcachingused.dto;

import lombok.Value;

import java.io.Serializable;

/**
 * DTO for {@link com.example.appcachingused.entity.Post}
 */
@Value
public class PostDTO implements Serializable {
    String title;
    String body;
}