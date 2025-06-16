package com.example.days.global.exception.common


class ModelNotFoundException(
    val modelName: String,
    val modelId: Long? = null,
    val errorCode: com.example.days.global.exception.common.CommonExceptionCode = com.example.days.global.exception.common.CommonExceptionCode.ENTITY_NOT_FOUND_ERROR
): RuntimeException()