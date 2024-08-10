package com.example.taxiwebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig extends WebSecurityConfigurerAdapter {

        private final UserDetailsService userDetailsService;

        public SecurityConfig(UserDetailsService userDetailsService) {
            this.userDetailsService = userDetailsService;
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .cors()
                    .and()
                    .authorizeRequests()
//            .antMatchers("/admin/**").hasRole("Admin")
//            .antMatchers("/taxist/**").hasRole("Taxist")
//            .antMatchers("/user/**").hasRole("User")
                    .antMatchers("/register").permitAll()
                    .antMatchers("/createOrder").authenticated()
                    .antMatchers("/mainPage").authenticated()
                    .antMatchers("/order").authenticated()
                    .antMatchers("/showOrdersHistory").authenticated()
                    .antMatchers("/logout").authenticated()
                    .antMatchers("/taxistOrders").hasRole("Taxist")
                    .and()
                    .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/mainPage")
                    .permitAll()
                    .and()
                    .logout()
                    .logoutUrl("/logout")
                    .logoutSuccessUrl("/login")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .permitAll()
                    .and()
                    .exceptionHandling() // Обработка исключений
                    .accessDeniedHandler((request, response, accessDeniedException) -> response.sendRedirect("/insufficientAccessPage"))
                    .authenticationEntryPoint((request, response, authException) -> {
                        if (response.getStatus() == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                            response.sendRedirect("/500error");
                        } else {
                            response.sendRedirect("/404error"); // Любая другая ошибка
                        }
                    })
                    .and()
                    .sessionManagement()
                    .sessionFixation()
                    .migrateSession()
                    .maximumSessions(1)
                    .expiredUrl("/sessionExpired");
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        }
    }