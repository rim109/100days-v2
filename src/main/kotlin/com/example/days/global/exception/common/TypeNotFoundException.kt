package com.example.days.global.exception.common

class TypeNotFoundException (
    val errorCode: com.example.days.global.exception.common.CommonExceptionCode = com.example.days.global.exception.common.CommonExceptionCode.TYPE_NOT_FOUND_ERROR
): RuntimeException()