package com.example.appcachingused.service;

import com.example.appcachingused.dto.PostDTO;
import com.example.appcachingused.entity.Post;
import com.example.appcachingused.mappers.PostMapper;
import com.example.appcachingused.repo.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.val;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {


    private final PostRepository postRepository;
    private final PostMapper postMapper;


    //    private ConcurrentHashMap<Integer, PostDTO> cache = new ConcurrentHashMap<>();
    @Cacheable(value = "post", key = "#id")
    @SneakyThrows
    public PostDTO getOne(Integer id) {

        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("error this id"));
        Thread.sleep(3000);
        return postMapper.toDto(post);
    }

    @CacheEvict(value = "post", key = "#id")
    public void remove(Integer id) {
        postRepository.deleteById(id);
    }

    @CachePut(value = "post", key = "#id")
    public PostDTO update(Integer id, PostDTO newPostDTO) {
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("error this id"));
        post.setTitle(newPostDTO.getTitle());
        post.setBody(newPostDTO.getBody());
        postRepository.save(post);
        return postMapper.toDto(post);
    }


    @Scheduled(initialDelay = 10000,fixedDelay = 200000)
    @CacheEvict(value = "post", allEntries = true)
    public void clearCaching() {

    }


}
