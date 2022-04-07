package com.magazin.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter implements WebMvcConfigurer {

    private static final String[] AUTH_WHITELIST = {
            // -- swagger ui
            "/v2/api-docs",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui.html",
            "/webjars/**",
            "/products/**",
            "/wishlists/**",
            "/customers/**"
    };

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //http.csrf().disable();
        //http.csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());

        http
                .cors().disable()
                .csrf().disable();

        http.httpBasic();

        http.authorizeRequests().
                antMatchers(AUTH_WHITELIST).permitAll();

                // rules for /cart
        //        .mvcMatchers(HttpMethod.GET,"/cart/**").hasAuthority("CUSTOMER")
        //        .mvcMatchers(HttpMethod.PUT,"/cart/**","/cart/**/**").hasAuthority("CUSTOMER")
        //        .mvcMatchers(HttpMethod.DELETE,"/cart/**").hasAuthority("CUSTOMER")
                // rules for /customers
        //        .mvcMatchers(HttpMethod.GET,"/customers").hasAuthority("ADMIN")
        //        .mvcMatchers(HttpMethod.GET,"/customers/**").hasAuthority("CUSTOMER")
        //        .mvcMatchers(HttpMethod.PUT,"/customers/**").hasAuthority("CUSTOMER")
        //        .mvcMatchers(HttpMethod.DELETE,"/customers/**").hasAuthority("CUSTOMER")
                // rules for /orders
        //        .mvcMatchers(HttpMethod.GET,"/orders", "/orders/**").hasAuthority("ADMIN")
        //        .mvcMatchers(HttpMethod.GET,"/orders/**").hasAuthority("CUSTOMER")
        //        .mvcMatchers(HttpMethod.PUT,"/orders/**").hasAuthority("ADMIN")
        //        .mvcMatchers(HttpMethod.DELETE,"/orders/**").hasAuthority("CUSTOMER")

                //TODO cum trebuie??

                //.anyRequest().authenticated();
    }

}
