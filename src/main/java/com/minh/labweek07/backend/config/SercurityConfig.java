package com.minh.labweek07.backend.config;

import com.minh.labweek07.backend.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

@Configuration
@EnableWebSecurity
public class SercurityConfig  {
    @Autowired
    private  UserService userService;



    public SercurityConfig(UserService userService) {
        this.userService = userService;
    }

    @Bean
    SecurityFilterChain sercurityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity.authorizeHttpRequests(req->req.requestMatchers("/admin/**").hasAnyAuthority("admin").requestMatchers("/check-out/**").hasAnyAuthority("admin","user").anyRequest().permitAll()).formLogin(
                form -> form.loginPage("/login").loginProcessingUrl("/login").defaultSuccessUrl("/admin/").successHandler((request, response, authentication)->{
                    UserDetails userDetails=(UserDetails) authentication.getPrincipal();
                    Logger logger=org.slf4j.LoggerFactory.getLogger(SercurityConfig.class);
                    logger.info("User logged in: "+userDetails.getUsername());
                    request.getSession().setAttribute("acc",userDetails);
                    SavedRequest savedRequest = new HttpSessionRequestCache().getRequest(request, response);
                    if (savedRequest != null) {
                        String redirectUrl = savedRequest.getRedirectUrl();
                        if (StringUtils.hasText(redirectUrl)) {
                            response.sendRedirect(redirectUrl);
                        } else {
                            response.sendRedirect("/admin/"); // Default redirect if no saved request
                        }
                    } else {
                        response.sendRedirect("/admin/"); // Default redirect if no saved request
                    }
                }).permitAll()).userDetailsService(userService).build();
//        return httpSecurity.formLogin(
//             form -> form.loginPage("/login").loginProcessingUrl("/authenticate").defaultSuccessUrl("/admin").permitAll()).authorizeHttpRequests(req->req.requestMatchers("/admin/**").hasAnyAuthority("admin").anyRequest().permitAll()).userDetailsService(userService).build();

    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
    @Bean
    public AuthenticationSuccessHandler myAuthenticationSuccessHandler() {
        return (request, response, authentication) -> {
            // Log thông tin authentication
            System.out.println(authentication.getName());
            // Chuyển hướng đến "/admin" hoặc nơi bạn muốn
            response.sendRedirect("/admin");
        };
    }
}
