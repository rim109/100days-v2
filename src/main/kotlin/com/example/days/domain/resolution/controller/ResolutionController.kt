package com.example.days.domain.resolution.controller

import com.example.days.domain.resolution.dto.request.ResolutionRequest
import com.example.days.domain.resolution.dto.response.ResolutionResponse
import com.example.days.domain.resolution.dto.response.SimpleResolutionResponse
import com.example.days.domain.resolution.service.ResolutionService
import com.example.days.global.common.SortOrder
import com.example.days.global.infra.security.UserPrincipal
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/resolution")
class ResolutionController (
    private val resolutionService: ResolutionService
){
    @Operation(summary = "목표 생성")
    @PostMapping
    fun createResolution(
        @Valid @RequestBody resolutionRequest: ResolutionRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ):ResponseEntity<ResolutionResponse>{
        val userId = userPrincipal.id
        val createResolution = resolutionService.createResolution(resolutionRequest, userId)

        return ResponseEntity.status(HttpStatus.CREATED).body(createResolution)
    }

    @Operation(summary = "목표 단건 조회")
    @GetMapping("/{resolutionId}")
    fun getResolutionById(
        @PathVariable resolutionId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<ResolutionResponse>{
        val resolution = resolutionService.getResolutionById(resolutionId, userPrincipal)
        return ResponseEntity.ok(resolution)
    }
    @Operation(summary = "목표 전체 조회(페이징)")
    @CrossOrigin
    @GetMapping
    fun getResolutionListPaginated(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam sortOrder: SortOrder?
    ): ResponseEntity<Page<SimpleResolutionResponse>>{
        val resolutionList = resolutionService.getResolutionListPaginated(page, sortOrder)
        return ResponseEntity.ok(resolutionList)
    }

    @Operation(summary = "목표 수정")
    @PatchMapping("/{resolutionId}")
    fun updateResolution(
        @PathVariable resolutionId: Long,
        @Valid @RequestBody resolutionRequest: ResolutionRequest,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<ResolutionResponse>{
        val userId = userPrincipal.id
        val updateResolution = resolutionService.updateResolution(resolutionId, userId ,resolutionRequest)
        return ResponseEntity.ok(updateResolution)
    }

    @Operation(summary = "목표 삭제")
    @DeleteMapping("/{resolutionId}")
    fun deleteResolution(
        @PathVariable resolutionId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<Unit>{
        val userId = userPrincipal.id
        resolutionService.deleteResolution(resolutionId, userId)
        return ResponseEntity.noContent().build()
    }

    @Operation(summary = "목표 랭킹 API")
    @GetMapping("/ranking")
    fun getResolutionRanking(): ResponseEntity<List<SimpleResolutionResponse>>{
        val rankedResolution = resolutionService.getResolutionRanking()
        return ResponseEntity.ok(rankedResolution)
    }

    @Operation(summary = "내 목표 단건 조회")
    @GetMapping("/myResolution/{resolutionId}")
    fun getMyResolution(
        @PathVariable resolutionId: Long,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<ResolutionResponse>{
        val userId = userPrincipal.id
        val resolution = resolutionService.getMyResolutionById(resolutionId, userId)
        return ResponseEntity.ok(resolution)
    }

    @Operation(summary = "내 목표 전체 조회(페이징)")
    @GetMapping("/myResolution")
    fun getMyResolutionListPaginated(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam sortOrder: SortOrder?,
        @AuthenticationPrincipal userPrincipal: UserPrincipal
    ): ResponseEntity<Page<SimpleResolutionResponse>>{
        val userId = userPrincipal.id
        val resolutionList = resolutionService.getMyResolutionListPaginated(page, sortOrder, userId)
        return ResponseEntity.ok(resolutionList)
    }

}