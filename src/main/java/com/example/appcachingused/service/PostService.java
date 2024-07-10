package com.example.appcachingused.service;

import com.example.appcachingused.dto.PostDTO;
import com.example.appcachingused.entity.Post;
import com.example.appcachingused.mappers.PostMapper;
import com.example.appcachingused.repo.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {


    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @SneakyThrows
    public PostDTO getOne(Integer id) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("error this id"));
        Thread.sleep(3000);
        return postMapper.toDto(post);

    }
}
