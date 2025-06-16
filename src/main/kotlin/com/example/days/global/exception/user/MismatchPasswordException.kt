package com.example.days.global.exception.user

class MismatchPasswordException (
    val errorCode: UserExceptionCode = UserExceptionCode.MISMATCH_PASSWORD_ERROR
): RuntimeException()