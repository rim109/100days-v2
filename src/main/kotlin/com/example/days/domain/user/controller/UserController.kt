package com.example.days.domain.user.controller

import com.example.days.domain.mail.dto.EmailRequest
import com.example.days.domain.user.dto.request.*
import com.example.days.domain.user.dto.response.*
import com.example.days.domain.user.service.UserService
import com.example.days.global.infra.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(
    val userService: UserService
) {

    @Operation(summary = "이메일 찾기")
    @GetMapping("/search/email")
    fun searchUserEmail(
        @RequestParam(value = "nickname") nickname: String
    ): ResponseEntity<List<EmailResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.searchUserEmail(nickname))
    }

    @Operation(summary = "비밀번호 분실시 재발급(메일보내기)")
    @PatchMapping("/search/pass")
    fun reissueUserPassword(
        @RequestBody request: EmailRequest
    ): ResponseEntity<Unit> {
        userService.reissueUserPassword(request)
        return ResponseEntity.status(HttpStatus.OK).build()
    }

    @Operation(summary = "로그인")
    @PostMapping("/login")
    fun userLogin(
        @RequestBody @Valid request: LoginRequest
    ): ResponseEntity<LoginResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.login(request))
    }

    @Operation(summary = "회원가입")
    @PostMapping("/signup")
    fun signUp(
        @RequestBody @Valid request: SignUpRequest
    ): ResponseEntity<SignUpResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.signUp(request))
    }

    @Operation(summary = "회원정보 보기(로그인 회원)")
    @GetMapping("/myinfo")
    fun getMyInfo(
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<ModifyMyInfoResponse> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.getMyInfo(userPrincipal))
    }

    @Operation(summary = "회원정보 수정(로그인 회원)")
    @PutMapping()
    fun modifyMyInfo(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody @Valid request: ModifyMyInfoRequest
    ): ResponseEntity<ModifyMyInfoResponse> {
        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(userService.modifyMyInfo(userPrincipal, request))
    }

    @Operation(summary = "회원탈퇴")
    @DeleteMapping()
    fun withdraw(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody @Valid request: WithdrawRequest
    ): ResponseEntity<Unit> {
        userService.withdraw(userPrincipal, request)
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build()
    }

    @Operation(summary = "비밀번호 변경")
    @PatchMapping("/passwordchange")
    fun passwordChangeInMyInfo(
        @AuthenticationPrincipal userPrincipal: UserPrincipal,
        @RequestBody @Valid request: UserPasswordRequest
    ): ResponseEntity<Unit> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.passwordChangeInMyInfo(userPrincipal, request))
    }

    @Operation(summary = "유저 검색(쪽지 등)")
    @GetMapping("/searchAccountId")
    fun searchUserAccountId(
        @RequestParam(value = "nickname") nickname: String
    ): ResponseEntity<List<AccountSearchResponse>> {
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(userService.searchUserAccountId(nickname))
    }
}