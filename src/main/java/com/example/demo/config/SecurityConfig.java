package com.example.demo.config;

import org.springframework.context.annotation.Configuration;


//@Configuration
//public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private final PasswordEncoder passwordEncoder;
//
//    public SecurityConfig(PasswordEncoder passwordEncoder) {
//        this.passwordEncoder = passwordEncoder;
//    }
//
//
//    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
//        auth.inMemoryAuthentication()
//                .withUser("director").password(passwordEncoder.encode("director")).roles("DIRECTOR")
//                .and()
//                .withUser("reception").password(passwordEncoder.encode("reception")).roles("RECEPTION")
//                .and()
//                .withUser("manager").password(passwordEncoder.encode("manager")).roles("MANAGER");
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable()
//                .authorizeRequests()
//                .antMatchers("/").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .failureUrl("/login?error=BadCredentials")
//                .defaultSuccessUrl("/guests", true)
//                .and()
//                .logout()
//                .logoutUrl("/logout")
//                .clearAuthentication(true)
//                .invalidateHttpSession(true)
//                .deleteCookies("JSESSIONID")
//                .logoutSuccessUrl("/");
//
//    }
//}
