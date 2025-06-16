package com.example.days.global.exception.common

class CheckAlreadyCompletedException (
    val errorCode: com.example.days.global.exception.common.CommonExceptionCode = com.example.days.global.exception.common.CommonExceptionCode.CHECK_ALREADY_COMPLETED_ERROR
): RuntimeException()