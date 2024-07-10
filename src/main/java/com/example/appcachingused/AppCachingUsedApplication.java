package com.example.appcachingused;

import com.example.appcachingused.entity.Post;
import com.example.appcachingused.repo.PostRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.CookieStore;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@EnableScheduling
@SpringBootApplication
public class AppCachingUsedApplication {

    public static void main(String[] args) {
        SpringApplication.run(AppCachingUsedApplication.class, args);
    }

    @SneakyThrows
    @Bean
    public ApplicationRunner applicationRunner(ObjectMapper objectMapper, PostRepository postRepository) throws MalformedURLException {
        return args -> {
            Post[] posts = objectMapper.readValue(new URL("https://jsonplaceholder.typicode.com/posts"), Post[].class);
            postRepository.saveAll(Arrays.asList(posts));

        };
    }

    @Bean
    public CacheManager cacheManager(){
        ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
        cacheManager.setCacheNames(List.of("post"));
        return cacheManager;
    }

}
