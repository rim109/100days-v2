package com.example.days.global.exception.common


import com.example.days.global.exception.auth.PermissionDeniedException
import com.example.days.global.exception.dto.BaseResponse
import com.example.days.global.exception.dto.ErrorResponse
import com.example.days.global.exception.status.ResultCode
import com.example.days.global.exception.user.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalExceptionHandler {

    // ㅇㅅㅇ 회원가입 시에 정규표현식을 맞게 했는데,, 문제가 무엇이냐면,, 메시지가 안뜬다. 그래서 이 에러 처리 코드를 추가해줬는데,,
    // ㅇㅅㅇ 추가하고 나니까 제대로 에러 메시지 창이 뜨므로 이는 남겨두는 것이 좋을 것 같다.
    @ExceptionHandler(MethodArgumentNotValidException::class)
    protected fun handleValidationExceptions(ex: MethodArgumentNotValidException): ResponseEntity<BaseResponse<Map<String, String>>> {
        val errors = mutableMapOf<String, String>()
        ex.bindingResult.allErrors.forEach { error ->
            val fieldName = (error as FieldError).field
            val errorMessage = error.defaultMessage
            errors[fieldName] = errorMessage ?: "Not Exception Message"
        }
        return ResponseEntity(BaseResponse(ResultCode.ERROR.name, errors, ResultCode.ERROR.msg), HttpStatus.BAD_REQUEST)
    }
    @ExceptionHandler
    fun handleRuntimeException(e: RuntimeException): ResponseEntity<ErrorResponse> {
        val errorCode = com.example.days.global.exception.common.CommonExceptionCode.INTERNAL_SERVER_ERROR

        return ResponseEntity.status(errorCode.httpStatus).body(ErrorResponse(errorCode.name, errorCode.message))
    }

    @ExceptionHandler(com.example.days.global.exception.common.ModelNotFoundException::class)
    fun handlerModelNotFoundException(e: com.example.days.global.exception.common.ModelNotFoundException): ResponseEntity<ErrorResponse> {
        val errorCode = e.errorCode
        val message = String.format(errorCode.message, e.modelName, e.modelId)
        return ResponseEntity.status(errorCode.httpStatus)
            .body(ErrorResponse(errorCode.name, message))
    }

    @ExceptionHandler(DuplicateEmailException::class)
    fun handlerDuplicateEmailException(e: DuplicateEmailException): ResponseEntity<ErrorResponse>{
        val errorCode = e.errorCode
        val message = String.format(errorCode.message, e.email)
        return ResponseEntity.status(errorCode.httpStatus)
            .body(ErrorResponse(errorCode.name, message))
    }

    @ExceptionHandler(DuplicateNicknameException::class)
    fun handlerDuplicateEmailException(e: DuplicateNicknameException): ResponseEntity<ErrorResponse>{
        val errorCode = e.errorCode
        val message = String.format(errorCode.message, e.nickname)
        return ResponseEntity.status(errorCode.httpStatus)
            .body(ErrorResponse(errorCode.name, message))
    }

    @ExceptionHandler(NoSearchUserByEmailException::class)
    fun handleNoSearchUserException(e: NoSearchUserByEmailException): ResponseEntity<ErrorResponse>{
        val errorCode = e.errorCode
        val message = String.format(errorCode.message, e.email)
        return ResponseEntity.status(errorCode.httpStatus)
            .body(ErrorResponse(errorCode.name, message))
    }

    @ExceptionHandler(
        MismatchPasswordException::class,
        UserSuspendedException::class,
        AuthCodeMismatchException::class,
        InvalidPasswordError::class,
        UserNotFoundException::class,
        )
    fun handleCustomUserExceptions(e: RuntimeException): ResponseEntity<ErrorResponse> {
        val errorCode = when (e) {
            is MismatchPasswordException -> e.errorCode
            is UserSuspendedException -> e.errorCode
            is AuthCodeMismatchException -> e.errorCode
            is InvalidPasswordError -> e.errorCode
            is UserNotFoundException -> e.errorCode
            else -> throw IllegalStateException("Unsupported exception type")
        }
        val message = errorCode.message
        return ResponseEntity.status(errorCode.httpStatus)
            .body(ErrorResponse(errorCode.name, message))
    }
    @ExceptionHandler(
        com.example.days.global.exception.common.TypeNotFoundException::class,
        com.example.days.global.exception.common.LikeAlreadyProcessedException::class,
        com.example.days.global.exception.common.CheckAlreadyCompletedException::class,
        com.example.days.global.exception.common.ResolutionAlreadyCompletedException::class)
    fun handleCustomCommonExceptions(e: RuntimeException): ResponseEntity<ErrorResponse>{
        val errorCode =  when (e) {
            is com.example.days.global.exception.common.TypeNotFoundException -> e.errorCode
            is com.example.days.global.exception.common.LikeAlreadyProcessedException -> e.errorCode
            is com.example.days.global.exception.common.CheckAlreadyCompletedException -> e.errorCode
            is com.example.days.global.exception.common.ResolutionAlreadyCompletedException -> e.errorCode
            else -> throw IllegalStateException("Unsupported exception type")
        }
        val message = errorCode.message
        return ResponseEntity.status(errorCode.httpStatus)
            .body(ErrorResponse(errorCode.name, message))
    }
    @ExceptionHandler(com.example.days.global.exception.auth.PermissionDeniedException::class)
    fun handlePermissionDeniedException(e: com.example.days.global.exception.auth.PermissionDeniedException): ResponseEntity<ErrorResponse>{
        val errorCode = e.errorCode
        val message = errorCode.message
        return ResponseEntity.status(errorCode.httpStatus)
            .body(ErrorResponse(errorCode.name, message))
    }

    @ExceptionHandler(HttpMessageNotReadableException::class)
    protected fun httpMessageNotReadableException(ex: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(ex.message))
    }

    @ExceptionHandler(com.example.days.global.exception.common.EmailExistException::class)
    fun handleEmailExistException(e: com.example.days.global.exception.common.EmailExistException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(com.example.days.global.exception.common.NicknameExistException::class)
    fun handleNicknameExistException(e: com.example.days.global.exception.common.NicknameExistException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(com.example.days.global.exception.common.NoSendMessagesException::class)
    fun handleNoSendMessagesException(e: com.example.days.global.exception.common.NoSendMessagesException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(com.example.days.global.exception.common.NoReceiverMessagesException::class)
    fun handleNoReceiverMessagesException(e: com.example.days.global.exception.common.NoReceiverMessagesException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(com.example.days.global.exception.common.NotSelfReportException::class)
    fun handleNotSelfReportException(e: com.example.days.global.exception.common.NotSelfReportException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(com.example.days.global.exception.common.NotReportException::class)
    fun handleNotReportException(e: com.example.days.global.exception.common.NotReportException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(com.example.days.global.exception.common.NotMessagesException::class)
    fun handleNotMessagesException(e: com.example.days.global.exception.common.NotMessagesException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(com.example.days.global.exception.common.AlreadyTenReportException::class)
    fun handleAlreadyTenReportException(e: com.example.days.global.exception.common.AlreadyTenReportException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(com.example.days.global.exception.common.AlreadyBANException::class)
    fun handleAlreadyBANException(e: com.example.days.global.exception.common.AlreadyBANException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(e.message))
    }

    @ExceptionHandler(com.example.days.global.exception.common.NotHaveSearchException::class)
    fun handleNotHaveSearchException(e: com.example.days.global.exception.common.NotHaveSearchException): ResponseEntity<ErrorResponse> {
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(e.message))
    }
}