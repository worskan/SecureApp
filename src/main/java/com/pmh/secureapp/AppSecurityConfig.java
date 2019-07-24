package com.pmh.secureapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailsService;

	
	
	@Bean
	public AuthenticationProvider authProvier() {
		DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		provider.setUserDetailsService(userDetailsService);
		provider.setPasswordEncoder(new BCryptPasswordEncoder());
		return provider;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			//로그인 처리부분		
			.csrf().disable()//개발 초기에만 적용
			.authorizeRequests().antMatchers("/login","/regist","/registRst","/emailConfirm","/login_fail","/logincheck").permitAll() //로그인 페이지 및 회원가입 접근 허용
			.antMatchers("/registFail","/registSuccess").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin()
			.loginPage("/login").loginProcessingUrl("/logincheck") //html 안붙혀주면 접근을 못함
			.usernameParameter("username").passwordParameter("password")
			.failureUrl("/login").defaultSuccessUrl("/").permitAll()
			.and()
			//로그아웃 처리부분
			.logout().invalidateHttpSession(true)
			.clearAuthentication(true)
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.logoutSuccessUrl("/login").permitAll();
		
		//X-Frame-Options : SAMEORIGIN을 허용
		http
		   .headers()
		      .frameOptions()
		         .sameOrigin();
	}

//	@Bean
//	@Override
//	protected UserDetailsService userDetailsService() {
//		
//		
//		List<UserDetails> users = new ArrayList<>();
//		users.add(User.withDefaultPasswordEncoder().username("navin").password("1234").roles("USER").build());
//		
//		
//		return new InMemoryUserDetailsManager(users);
//	}

}
