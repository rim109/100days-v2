package com.example.days.global.infra.web

import com.example.days.domain.oauth.config.OAuth2ProviderConverter
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.format.FormatterRegistry
import org.springframework.web.servlet.config.annotation.CorsRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class WebConfig(
    @Value("\${domain.url}") private val domainUrl: String,
    @Value("\${api.url}") private val apiUrl: String
) : WebMvcConfigurer {


    override fun addFormatters(registry: FormatterRegistry) {
        registry.addConverter(OAuth2ProviderConverter())
    }

    override fun addCorsMappings(registry: CorsRegistry) {
        registry.addMapping("/**") // 모든 경로에 대해서
            .allowedOrigins(
                "http://localhost:8080", apiUrl, "http://localhost:8090", domainUrl
            ) // 이 출처로부터의 요청만 허용
            .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "HEAD", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(true) // 쿠키를 포함한 요청 허용
            .maxAge(3000)
    }
}