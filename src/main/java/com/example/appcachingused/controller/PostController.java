package com.example.appcachingused.controller;

import com.example.appcachingused.dto.PostDTO;
import com.example.appcachingused.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("{id}")
    public PostDTO getOnePost(@PathVariable Integer id){
       return postService.getOne(id);
    }

    @DeleteMapping("{id}")
    public void deletePost(@PathVariable Integer id){
        postService.remove(id);
    }

    @PutMapping("{id}")
    public void update(@PathVariable Integer id,PostDTO newPostDTO){
        postService.update(id,newPostDTO);

    }


}
