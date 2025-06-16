package com.example.days.global.oauth.client

import com.example.days.global.oauth.common.OAuth2LoginUserInfo
import com.example.days.global.oauth.model.OAuth2Provider

interface OAuth2Client {
    fun generateLoginPageUrl(): String
    fun getAccessToken(authorizationCode: String): String
    fun retrieveUserInfo(accessToken: String): OAuth2LoginUserInfo
    fun supports(provider: OAuth2Provider): Boolean
}