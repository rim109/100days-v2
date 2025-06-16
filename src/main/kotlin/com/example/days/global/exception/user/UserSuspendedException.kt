package com.example.days.global.exception.user

class UserSuspendedException (
    val errorCode: UserExceptionCode = UserExceptionCode.USER_SUSPENDED_ERROR
): RuntimeException()