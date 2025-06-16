package com.example.days.domain.user.repository

import com.example.days.domain.user.model.User

interface CustomUserRepository {
    fun searchUserByNickname(nickname: String): List<User>
    fun searchUserByAccountIdAndNickname(nickname: String): List<User>
}