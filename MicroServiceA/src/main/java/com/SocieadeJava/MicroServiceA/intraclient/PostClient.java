package com.SocieadeJava.MicroServiceA.intraclient;



import com.SocieadeJava.MicroServiceA.dto.PostDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "microserviceb", url = "${microserviceb.url}")
public interface PostClient {

    @GetMapping("/posts")
    List<PostDTO> getAllPosts();

    @PostMapping("/posts")
    PostDTO createPost(@RequestBody PostDTO postDTO);
}
