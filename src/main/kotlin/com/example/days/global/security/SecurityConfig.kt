package com.example.days.global.security

import com.example.days.global.security.jwt.JwtAuthenticationFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import org.springframework.security.web.util.matcher.AntPathRequestMatcher

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
class SecurityConfig(
    private val jwtAuthenticationFilter: JwtAuthenticationFilter
) {

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        return http
            .httpBasic { it.disable() }
            .formLogin { it.disable() }
            .csrf { it.disable() }
            .cors { it.disable() }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }
            .headers { it.frameOptions { option -> option.disable() } }
            .authorizeHttpRequests {
                // common
                it.requestMatchers(AntPathRequestMatcher("/api/**")).permitAll()

                it.requestMatchers(AntPathRequestMatcher("/swagger-ui/**")).permitAll()
                it.requestMatchers(AntPathRequestMatcher("/v3/api-docs/**")).permitAll()

                it.requestMatchers(AntPathRequestMatcher("/login/oauth2")).permitAll()
                it.requestMatchers(AntPathRequestMatcher("/login/oauth2/callback")).permitAll()
                it.requestMatchers(AntPathRequestMatcher("/error")).permitAll()

                it.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                it.requestMatchers(AntPathRequestMatcher("/")).permitAll()
                    .anyRequest().authenticated()
            }
            .addFilterBefore(
                jwtAuthenticationFilter,
                UsernamePasswordAuthenticationFilter::class.java
            ).exceptionHandling {
//                it.authenticationEntryPoint(authenticationEntryPoint)
//                it.accessDeniedHandler(accessDeniedHandler)
            }
            .headers { it.frameOptions { it1 -> it1.disable() } }
            .build()
    }
}