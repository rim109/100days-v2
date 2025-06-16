package com.example.days.global.exception.common

class ResolutionAlreadyCompletedException (
    val errorCode: com.example.days.global.exception.common.CommonExceptionCode = com.example.days.global.exception.common.CommonExceptionCode.RESOLUTION_ALREADY_COMPLETED_ERROR
): RuntimeException()