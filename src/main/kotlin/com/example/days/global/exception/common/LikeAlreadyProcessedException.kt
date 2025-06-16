package com.example.days.global.exception.common

class LikeAlreadyProcessedException (
    val errorCode: com.example.days.global.exception.common.CommonExceptionCode = com.example.days.global.exception.common.CommonExceptionCode.LIKE_ALREADY_PROCESSED_ERROR
): RuntimeException()