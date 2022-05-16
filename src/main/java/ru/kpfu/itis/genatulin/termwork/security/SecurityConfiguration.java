package ru.kpfu.itis.genatulin.termwork.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Autowired
    public SecurityConfiguration(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                          .mvcMatchers("/resources/**", "/", "/about", "/login", "/register", "/forgot").permitAll()
                          .mvcMatchers("/user", "/user/edit", "/feed", "/articles", "/meetings", "/speeddates").authenticated()
                          .mvcMatchers("/articles/{articleId}").access("@articleServiceImpl.checkArticleId(#articleId, authentication)")
                          .mvcMatchers("/meetings/{meetingId}").access("@meetingServiceImpl.checkMeetingId(#meetingId, authentication)")
                          .mvcMatchers("/speeddates/{speeddateId}").access("@speeddateServiceImpl.checkSpeeddateId(#speeddateId, authentication)")
                          .mvcMatchers("**/edit").hasRole("ADMIN")
                          .anyRequest().denyAll()
                .and()
                .formLogin()
                        .loginPage("/login")
                        .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider(users(), passwordEncoder()));
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsManager users() {
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsManager);
        return daoAuthenticationProvider;
    }
}
