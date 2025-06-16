package com.example.days.global.oauth.dto.kakao

import com.example.days.global.oauth.common.OAuth2LoginUserInfo
import com.example.days.global.oauth.model.OAuth2Provider
import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.fasterxml.jackson.databind.annotation.JsonNaming

@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy::class)
class KakaoInfoResponse(
    id: Long,
    properties: KakaoPropertiesResponse,
    account: KakaoAccountResponse,
) : OAuth2LoginUserInfo(

    provider = OAuth2Provider.KAKAO,
    id = id.toString(),
    nickname = properties.nickname,
    email = account.email
)