package ru.kpfu.itis.genatulin.termwork.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
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
import org.springframework.security.data.repository.query.SecurityEvaluationContextExtension;
import org.springframework.security.provisioning.UserDetailsManager;
import ru.kpfu.itis.genatulin.termwork.repositories.UserRepository;
import ru.kpfu.itis.genatulin.termwork.security.details.UserDetailsManagerImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserRepository userRepository;

    @Autowired
    public SecurityConfiguration(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                          .mvcMatchers("/resources/**", "/", "/about", "/login", "/register", "/forgot").permitAll()
                          .mvcMatchers("/user", "/user/edit", "/feed", "/articles", "/meetings", "/speeddates").authenticated()
                          .mvcMatchers("/articles/{articleId}").access("@articleServiceImpl.checkArticleId(#articleId, authentication)")
                          .mvcMatchers("/meetings/{meetingId}").access("@meetingServiceImpl.checkMeetingId(#meetingId, authentication)")
                          .mvcMatchers("/speeddates/{speeddateId}").access("@speeddateServiceImpl.checkSpeeddateId(#speeddateId, authentication)")
                          .mvcMatchers("**/edit").hasRole("ADMIN")
                          .anyRequest().denyAll()
                .and()
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll())
                .logout()
                .permitAll()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/");
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
        return new UserDetailsManagerImpl(userRepository);
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsManager userDetailsManager, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        daoAuthenticationProvider.setUserDetailsService(userDetailsManager);
        return daoAuthenticationProvider;
    }

    @Bean
    public SecurityEvaluationContextExtension securityEvaluationContextExtension() {
        return new SecurityEvaluationContextExtension();
    }
}
