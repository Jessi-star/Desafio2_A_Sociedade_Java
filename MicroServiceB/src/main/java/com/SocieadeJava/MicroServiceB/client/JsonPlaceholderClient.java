package com.SocieadeJava.MicroServiceB.client;

import com.SocieadeJava.MicroServiceB.dto.PostDTO;
import com.SocieadeJava.MicroServiceB.entity.Post;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "jsonPlaceholderClient", url = "https://jsonplaceholder.typicode.com")
public interface JsonPlaceholderClient {


        @GetMapping("/posts")
        List<PostDTO> getAllPosts();

        @GetMapping("/posts/{id}")
        PostDTO getPostById(@PathVariable("id") Long id);
    }


