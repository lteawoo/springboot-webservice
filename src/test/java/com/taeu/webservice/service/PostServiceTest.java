package com.taeu.webservice.service;

import static org.assertj.core.api.Assertions.assertThat;

import com.taeu.webservice.dto.PostsMainResponseDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.taeu.webservice.domain.posts.Posts;
import com.taeu.webservice.domain.posts.PostsRepository;
import com.taeu.webservice.dto.PostsSaveRequestDto;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostServiceTest {
	
	@Autowired
	private PostsService postsService;
	
	@Autowired
	private PostsRepository postsRepository;
	
	@After
	public void cleanup() {
		postsRepository.deleteAll();
	}
	
	@Test
	public void Dto데이터가_posts테이블에_저장된다 () {
		//given
		PostsSaveRequestDto dto = PostsSaveRequestDto.builder()
				.author("lteawoo@gmail.com")
				.content("테스트")
				.title("테스트 타이틀")
				.build();
		
		//when
		postsService.save(dto);
		
		//then
		Posts posts = postsRepository.findAll().get(0);
		assertThat(posts.getAuthor()).isEqualTo(dto.getAuthor());
		assertThat(posts.getContent()).isEqualTo(dto.getContent());
		assertThat(posts.getTitle()).isEqualTo(dto.getTitle());
	}

	@Test
	public void 테이블데이터가_Dto를_통해_출력된다() {
		//when
		List<PostsMainResponseDto> list = postsService.findAllDesc();

		//then
		Long prevNo = 0L;
		for(PostsMainResponseDto dto : list) {
			assertThat(dto.getId()).isGreaterThan(prevNo);
			prevNo = dto.getId();
		}
	}
}
