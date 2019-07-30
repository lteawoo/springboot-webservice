package com.taeu.webservice.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.taeu.webservice.domain.posts.PostsRepository;
import com.taeu.webservice.dto.PostsSaveRequestDto;
import com.taeu.webservice.service.PostsService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {
	
	private PostsService postsService;
	private PostsRepository postsRepository;
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello world!";
	}
	
	@PostMapping("/posts")
	public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
		return postsService.save(dto);
	}
}
