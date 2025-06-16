package com.example.days.domain.resolution.dto.response

import com.example.days.domain.resolution.model.Resolution
import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDate
import java.time.LocalDateTime

data class ResolutionResponse(
    val id: Long?,
    val title: String,
    val description: String,
    val completeStatus: Boolean,
    val dailyStatus: Boolean,
    val category: String,
    val progress: Long,
    val likeCount: Long,
    val isAuthor: Boolean,

    // ^오^: 시간 형식으로 값을 바꿔주는 어노테이션
    @JsonFormat(pattern = "yyyy-MM-dd")
    val deadline: LocalDate,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val createdAt: LocalDateTime,
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    val updatedAt: LocalDateTime
) {

    companion object {
        fun from(resolution: Resolution, status: Boolean) = ResolutionResponse(
            id = resolution.id,
            title = resolution.title,
            description = resolution.description,
            completeStatus = resolution.completeStatus,
            dailyStatus = resolution.dailyStatus,
            category = resolution.category.name,
            progress = resolution.progress,
            likeCount = resolution.likeCount,
            createdAt = resolution.createdAt,
            updatedAt = resolution.updatedAt,
            deadline = resolution.deadline,
            isAuthor = status
        )
    }
}
