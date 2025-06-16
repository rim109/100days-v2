package com.example.days.domain.resolution.service

import com.example.days.domain.resolution.dto.request.ResolutionRequest
import com.example.days.domain.resolution.dto.response.ResolutionResponse
import com.example.days.domain.resolution.dto.response.SimpleResolutionResponse
import com.example.days.global.common.SortOrder
import com.example.days.global.infra.security.UserPrincipal
import org.springframework.data.domain.Page


interface ResolutionService {
    fun createResolution(resolutionRequest: ResolutionRequest, userId: Long): ResolutionResponse
    fun getResolutionById(resolutionId: Long, userPrincipal: UserPrincipal): ResolutionResponse
    fun getResolutionListPaginated(page: Int, sortOrder: SortOrder?): Page<SimpleResolutionResponse>
    fun updateResolution(resolutionId: Long, userId: Long ,resolutionRequest: ResolutionRequest): ResolutionResponse
    fun deleteResolution(resolutionId: Long, userId: Long)
    fun getResolutionRanking(): List<SimpleResolutionResponse>
    fun getMyResolutionById(resolutionId: Long, userId: Long): ResolutionResponse
    fun getMyResolutionListPaginated(page: Int, sortOrder: SortOrder?, userId: Long): Page<SimpleResolutionResponse>
}