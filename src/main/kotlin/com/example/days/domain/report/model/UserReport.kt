package com.example.days.domain.report.model

import com.example.days.domain.user.model.Status
import com.example.days.domain.user.model.User
import com.example.days.global.entity.BaseEntity
import jakarta.persistence.*
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction

@Entity
@Table(name = "UserReport")
class UserReport(
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reporter_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    var reporter: User,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reported_user_id")
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    var reportedUserId: User,

    @Column(name = "content")
    var content: String,

    @Column(name = "reportStatus")
    val reportStatus: String,


    ) : BaseEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null

    fun reportUser() {
        reportedUserId.status = Status.WARNING
    }

}