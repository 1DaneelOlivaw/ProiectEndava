package com.magazin.demo.config;

import com.magazin.demo.filter.CustomAuthenticationFilter;
import com.magazin.demo.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;


@Configuration
@EnableWebSecurity
@EnableSwagger2
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private String[] swaggerWhiteList = {"/v2/api-docs", "/swagger-resources/configuration/ui", "/swagger-resources", "/swagger-resources/configuration/security", "/swagger-ui.html", "/swagger.json", "/webjars/**"};

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CustomAuthenticationFilter customAuthenticationFilter = new CustomAuthenticationFilter(authenticationManagerBean());
        // customAuthenticationFilter.setFilterProcessesUrl("/api/login");
        http.csrf().disable();
        http.sessionManagement().sessionCreationPolicy(STATELESS);
        http.authorizeRequests().antMatchers(swaggerWhiteList).permitAll();
        http.authorizeRequests().antMatchers("/login/**").permitAll();

        //user controller
        http.authorizeRequests().antMatchers(GET,"/api/users").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST,"/api/user/save").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST,"/api/role/save").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST,"/api/role/addtouser").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(POST,"/api/role/delete").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(DELETE,"/api/delete/**").hasAuthority("ROLE_ADMIN");

        //wishlist controller
        http.authorizeRequests().antMatchers(GET,"/wishlists").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET,"/wishlists/**").hasAuthority("ROLE_CUSTOMER");
        http.authorizeRequests().antMatchers(PUT,"/wishlists/**/**").hasAuthority("ROLE_CUSTOMER");
        http.authorizeRequests().antMatchers(DELETE,"/wishlists/**/**").hasAuthority("ROLE_CUSTOMER");

        //product controller
        http.authorizeRequests().antMatchers(GET,"/products").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOMER","ROLE_VENDOR");
        http.authorizeRequests().antMatchers(GET,"/products/**").hasAnyAuthority("ROLE_ADMIN","ROLE_CUSTOMER","ROLE_VENDOR");
        http.authorizeRequests().antMatchers(PUT,"/products/**").hasAnyAuthority("ROLE_ADMIN","ROLE_VENDOR");
        http.authorizeRequests().antMatchers(POST,"/products").hasAnyAuthority("ROLE_ADMIN","ROLE_VENDOR");
        http.authorizeRequests().antMatchers(DELETE,"/products/**").hasAnyAuthority("ROLE_ADMIN","ROLE_VENDOR");

        //cart controller
        http.authorizeRequests().antMatchers(GET, "/cart/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CUSTOMER");
        http.authorizeRequests().antMatchers(PUT, "/cart/**/**").hasAuthority("ROLE_CUSTOMER");
        http.authorizeRequests().antMatchers(DELETE, "/cart/**/**").hasAuthority("ROLE_CUSTOMER");
        http.authorizeRequests().antMatchers(PUT, "/cart/**").hasAuthority("ROLE_CUSTOMER");

        //order controller
        http.authorizeRequests().antMatchers(GET, "/orders").hasAuthority("ROLE_ADMIN");
        http.authorizeRequests().antMatchers(GET, "/orders/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CUSTOMER");
        http.authorizeRequests().antMatchers(PUT, "/orders/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_CUSTOMER", "ROLE_VENDOR");
        http.authorizeRequests().antMatchers(DELETE, "/orders/**").hasAuthority("ROLE_CUSTOMER");


        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(customAuthenticationFilter);
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);
        //http.formLogin().defaultSuccessUrl("/swagger-ui.html",true);
        http.formLogin().loginProcessingUrl("/login").successForwardUrl("/swagger-ui.html");
        http.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/swagger-ui.html").deleteCookies("JSESSIONID")
                .invalidateHttpSession(true);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception{
        return super.authenticationManagerBean();
    }

}
