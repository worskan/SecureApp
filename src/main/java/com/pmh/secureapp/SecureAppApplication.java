package com.pmh.secureapp;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.pmh.Domains.Board;
import com.pmh.Repositories.BoardRepository;

@SpringBootApplication
@Configuration
@EntityScan("com.pmh.Domains")
@ComponentScan({ "com.pmh.Controllers", "com.pmh.Service", "com.pmh,secureapp" })
@EnableJpaRepositories("com.pmh.Repositories")
public class SecureAppApplication implements CommandLineRunner, WebMvcConfigurer {

	@Autowired
	private BoardRepository brepo;

	public static void main(String[] args) {
		SpringApplication.run(SecureAppApplication.class, args);
	}

//	@Override
//	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
//		WebMvcConfigurer.super.addArgumentResolvers(resolvers);
//		resolvers.add(new AccountHandlerMethodArgumentResolver());
//	}

	@Override
	public void run(String... args) throws Exception {
		
		// 한 페이지 0번째 10개 호출
//		Page<Board> page = brepo.findAll(PageRequest.of(0, 10));
//		printPageData("simple", page);

		// 한 페이지 0번째 10개 글번호 내림차순으로 호출
//		page = brepo.findAllByOrderByBnoDesc(PageRequest.of(0, 10));
//		printPageData("sort_seq_desc", page);

		
		// 한 페이지 2번째 10개 글번호 내림차순 호출
//		page = brepo.findAll(PageRequest.of(2, 10, new Sort(Direction.DESC, "bno")));
//		printPageData("sort", page);

		
		// 한 페이지 0번째 10개 글작성자 234로 호출
//		page = brepo.findAllByUsername("234", PageRequest.of(0, 10)); //
//		printPageData("sort_author", page);

		
		// 한 페이지 2페이지 10개 작성자 내림차순 호출
//		page = brepo.findAll(PageRequest.of(2,10,new Sort(Direction.DESC,"username")));
//		printPageData("sort_author_desc", page);

		
	}

	/*
	 * private void printPageData(String label, Page<Board> page) { if (page == null
	 * || page.getSize() <= 0) return; for (int i = 0; i < page.getSize(); i++) {
	 * Board board = page.getContent().get(i); System.out.println("["+label+"] "+
	 * board.getBno() + " " + board.getUsername() + " " + board.getContent()); } }
	 */
}
