package ru.kpfu.itis.genatulin.termwork.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(
                  authorize -> authorize
                          .mvcMatchers("/resources/**", "/", "/about", "/login", "/register", "/forgot").permitAll()
                          .mvcMatchers("/user", "/user/edit", "/feed", "/articles", "/meetings", "/speeddates").authenticated()
                          .mvcMatchers("/articles/{articleId}").access("@articleServiceImpl.checkArticleId(#articleId, authentication)")
                          .mvcMatchers("/meetings/{meetingId}").access("@meetingServiceImpl.checkMeetingId(#meetingId, authentication)")
                          .mvcMatchers("/speeddates/{speeddateId}").access("@speeddateServiceImpl.checkSpeeddateId(#speeddateId, authentication)")
                          .mvcMatchers("**/edit").hasRole("ADMIN")
                          .anyRequest().denyAll()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll());
    }
}
