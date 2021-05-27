package com.example.demo.login;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	//パスワードエンコーダーのBean定義
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception {
		//直リンク禁止、ログイン不要ページの設定
		http
			.authorizeRequests()
				.antMatchers("/login").permitAll()//ログインページは直リンクOK
				.anyRequest().authenticated();//それ以外は直リンク禁止
			
		//ログイン処理
		http
			.formLogin()
				.loginProcessingUrl("/login")//ログイン処理のパス
				.loginPage("/login")//ログインページの指定
				.failureUrl("/login")//ログイン失敗時の遷移先
				.usernameParameter("userId")//ログインページのユーザーID
				.passwordParameter("password")//ログインページのパスワード
				.defaultSuccessUrl("/home", true);//ログイン成功後の遷移先
		
		//ログアウト処理
		http
			.logout()
				.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))//GETメソッドでログアウトする場合
				.logoutUrl("/logout")//POSTメソッドでログアウトする場合
				.logoutSuccessUrl("/login");//ログアウト成功時の遷移先
				
		//CSRF対策を一時的に無効
		http.csrf().disable();
	}
}
