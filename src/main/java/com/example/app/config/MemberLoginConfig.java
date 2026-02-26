package com.example.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class MemberLoginConfig {

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeHttpRequests(auth -> auth
        .requestMatchers(
          "/loginhome",
          "/members/**",
          "/admins/**",
          "/css/**", "/js/**", "/images/**"
        ).permitAll()
        .anyRequest().permitAll()
      )
      .formLogin(form -> form.disable())   // ←超重要：これで/loginhomeへ飛ばす仕組みを止める
      .httpBasic(basic -> basic.disable())
      .csrf(csrf -> csrf.disable());

    return http.build();
  }
}