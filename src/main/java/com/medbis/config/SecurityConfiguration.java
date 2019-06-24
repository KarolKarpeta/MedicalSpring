package com.medbis.config;

import com.medbis.service.impl.UserPrinicipalDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserPrinicipalDetailsService userPrinicipalDetailsService;

    public SecurityConfiguration(UserPrinicipalDetailsService userPrinicipalDetailsService){
        this.userPrinicipalDetailsService = userPrinicipalDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth
                .authenticationProvider(authenticationProvider());


    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/**")
                .hasRole("NURSE")
                .and()
                .formLogin()
                .successHandler(new LoginSuccessHandler())
                .permitAll()
                .and()
                .logout();
    }



    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setUserDetailsService(userPrinicipalDetailsService);
        return daoAuthenticationProvider;
    }

    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
