package com.example.days.global.oauth.service

import com.example.days.global.oauth.model.OAuth2Provider
import com.example.days.domain.user.dto.response.LoginResponse
import com.example.days.global.security.jwt.JwtPlugin
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Service

@Service
class OAuth2LoginService(
    private val oauth2ClientService: OAuth2ClientService,
    private val socialUserService: SocialUserService,
    private val jwtPlugin: JwtPlugin
) {

    fun login(provider: OAuth2Provider, response: HttpServletResponse, authorizationCode: String): LoginResponse {
//        return oauth2ClientService.login(provider, authorizationCode)
//            .let { socialUserService.registerIfAbsent(it) }
//            .let { jwtPlugin.accessToken(it.id!!, it.email, it.role) }

        val info = oauth2ClientService.login(provider, authorizationCode)
        val user = socialUserService.registerIfAbsent(info)
        val token = jwtPlugin.accessToken(user.id!!, user.email, user.role)
        return LoginResponse(token)
    }
}