package com.example.appcachingused.repo;

import com.example.appcachingused.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Integer> {
}