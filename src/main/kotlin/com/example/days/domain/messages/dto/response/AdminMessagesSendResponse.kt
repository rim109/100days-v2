package com.example.days.domain.messages.dto.response

import com.example.days.domain.messages.model.AdminMessagesEntity
import java.time.LocalDateTime

data class AdminMessagesSendResponse(
    val id: Long,
    val title: String,
    val content: String,
    val senderAccountId: String,
    val receiverAccountId: String,
    val sentAt: LocalDateTime,
    val readStatus: Boolean,
    val senderNickname: String,
    val receiverNickname: String
) {
    companion object {
        fun from(messages: AdminMessagesEntity): AdminMessagesSendResponse {
            return AdminMessagesSendResponse(
                id = messages.id!!,
                title = messages.title,
                content = messages.content,
                receiverAccountId = messages.receiver.accountId,
                senderAccountId = messages.admin.nickname,
                sentAt = messages.sentAt,
                readStatus = messages.readStatus,
                senderNickname = messages.admin.nickname,
                receiverNickname = messages.receiver.nickname,
            )
        }
    }
}
