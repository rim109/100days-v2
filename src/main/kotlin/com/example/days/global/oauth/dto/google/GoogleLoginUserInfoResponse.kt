package com.example.days.global.oauth.dto.google

import com.example.days.global.oauth.common.OAuth2LoginUserInfo
import com.example.days.global.oauth.model.OAuth2Provider
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class GoogleLoginUserInfoResponse(
    id: String,
    nickname: String,
    email: String
) : OAuth2LoginUserInfo(

    provider = OAuth2Provider.GOOGLE,
    id = id,
    nickname = nickname,
    email = email
)