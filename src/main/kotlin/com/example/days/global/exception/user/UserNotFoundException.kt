package com.example.days.global.exception.user

class UserNotFoundException (
    val errorCode: UserExceptionCode = UserExceptionCode.USER_NOT_FOUND_ERROR
): RuntimeException()