package com.example.days.global.exception.auth

class PermissionDeniedException (
    val errorCode: com.example.days.global.exception.auth.AuthExceptionCode = com.example.days.global.exception.auth.AuthExceptionCode.PERMISSION_DENIED
): RuntimeException()