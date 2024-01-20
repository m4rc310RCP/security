package br.com.m4rc310.auth.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity
public class SecurityConfiguration {
//	@Bean
//	SecurityWebFilterChain filterChain(ServerHttpSecurity http) {
//		return http.httpBasic(ServerHttpSecurity.HttpBasicSpec::disable).build();
//	}
    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests((auth) -> auth.anyRequest().permitAll()).build();
    }
    
//    @Bean
//    WebSecurityCustomizer webSecurityCustomizer() {
//        return (web) -> web.ignoring().requestMatchers("/*");
//    }
}
