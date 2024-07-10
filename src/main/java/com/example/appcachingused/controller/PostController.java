package com.example.appcachingused.controller;

import com.example.appcachingused.dto.PostDTO;
import com.example.appcachingused.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("{id}")
    public PostDTO getOnePost(@PathVariable Integer id){
       return postService.getOne(id);
    }


}
