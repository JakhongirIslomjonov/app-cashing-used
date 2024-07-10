package com.example.appcachingused.service;

import com.example.appcachingused.dto.PostDTO;
import com.example.appcachingused.entity.Post;
import com.example.appcachingused.mappers.PostMapper;
import com.example.appcachingused.repo.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

@RequiredArgsConstructor
@Service
public class PostService {


    private final PostRepository postRepository;
    private final PostMapper postMapper;

    private ConcurrentHashMap<Integer, PostDTO> cache = new ConcurrentHashMap<>();

    @SneakyThrows
    public PostDTO getOne(Integer id) {
        PostDTO cachedPost = cache.get(id);
        if (cachedPost != null) {
            return  cachedPost;
        }
        Post post = postRepository.findById(id).orElseThrow(() -> new RuntimeException("error this id"));
        Thread.sleep(3000);
        cache.put(id, postMapper.toDto(post));
        return postMapper.toDto(post);
    }

    public void remove(Integer id) {
        postRepository.deleteById(id);
        cache.remove(id);
    }

    public void update(Integer id, PostDTO newPostDTO) {

        PostDTO postDTO = getOne(id);
        Post post = postMapper.toEntity(postDTO);
        post.setTitle(newPostDTO.getTitle());
        post.setBody(newPostDTO.getBody());
        postRepository.save(post);
        cache.put(id,postMapper.toDto(post));
    }
}
