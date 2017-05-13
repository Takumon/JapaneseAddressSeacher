package jp.takumon.japaneseaddresssearcher;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * アプリケーションの設定.
 * 
 * @author takumon
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http //
				.formLogin().loginPage("/login").permitAll().and() //
				.logout().logoutUrl("/logout").and() //
				.csrf().disable() //
				.authorizeRequests() //
				.antMatchers("/login.html", //
						"/**/*.css", //
						"/**/*.js").permitAll() //
				.antMatchers("/**").authenticated().and() //
				.httpBasic();
	}
	
	@Configuration
	static protected class LoginController extends WebMvcConfigurerAdapter {

		@Override
		public void addViewControllers(ViewControllerRegistry registry) {
			registry.addViewController("/login").setViewName("login");
		}

	}

}
