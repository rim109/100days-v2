package com.example.days.global.exception.user

class DuplicateNicknameException(
    val nickname: String,
    val errorCode: UserExceptionCode = UserExceptionCode.DUPLICATE_NICKNAME_ERROR
): RuntimeException()