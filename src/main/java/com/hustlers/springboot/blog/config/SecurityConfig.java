package com.hustlers.springboot.blog.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    //    Spring Security provides password encoding feature using the PasswordEncoder interface.
//    It's a one way transformation, means you can only encode the password, but there is no way to decode
//    the password back to the plaintext form
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public SecurityConfig(@Qualifier("dataSource") DataSource dataSource) {
        this.dataSource = dataSource;
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled"
                        + " from users where username=?")
                .authoritiesByUsernameQuery("select username, authority "
                        + "from authorities where username=?")
                .passwordEncoder(new BCryptPasswordEncoder());
    }
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/","/users/register","/css/**","/img/**","/js/**")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/showMyLoginPage")
                .loginProcessingUrl("/authenticateTheUser")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/showMyLoginPage")
                .permitAll();
    }


}
