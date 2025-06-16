package com.example.days.global.oauth.repository

import com.example.days.domain.oauth.model.SocialLoginUser
import org.springframework.data.repository.CrudRepository

interface SocialUserLoginRepository: CrudRepository<SocialLoginUser, String> {
}