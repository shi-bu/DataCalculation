package com.example.demo.login;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//直リンク禁止、ログイン不要ページの設定
		/*http
			.authorizeRequests()
				.antMatchers("/login").permitAll()//ログインページは直リンクOK
				.anyRequest().authenticated();//それ以外は直リンク禁止
		*/
		
		//CSRF対策を一時的に無効
		http.csrf().disable();
	}
}
