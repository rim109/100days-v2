package com.example.days.global.exception.user

class AuthCodeMismatchException (
    val errorCode: UserExceptionCode = UserExceptionCode.AUTH_CODE_MISMATCH_ERROR
): RuntimeException()