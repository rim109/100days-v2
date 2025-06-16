package com.example.days.domain.user.repository

import com.example.days.domain.oauth.model.OAuth2Provider
import com.example.days.domain.user.model.Status
import com.example.days.domain.user.model.User
import jakarta.transaction.Transactional
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDate
import java.time.LocalDateTime

interface UserRepository: JpaRepository<User, Long>, CustomUserRepository {
    fun existsByEmail(email: String): Boolean
//    fun existsByNickname(nickname: String): Boolean
    fun existsByAccountId(accountId: String): Boolean
    fun findUserByEmail(email: String): User?
    fun findByAccountId(accountId: String): User?

    fun existsByProviderAndProviderId(provider: OAuth2Provider, toString: String): Boolean
//    fun findByProviderAndProviderId(provider: OAuth2Provider, toString: String): User
    fun findByEmailAndProviderId(email: String, providerId: String): User

    @Modifying
    @Transactional
    @Query("DELETE FROM User u WHERE u.status = :status AND u.updatedAt <= :updatedAt")
    fun deleteUsersByStatusAndUpdatedAtIsLessThanEqualBatch(status: Status, updatedAt: LocalDateTime): Int

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.status = :status WHERE u.period <= :today")
    fun checkBanPeriod(status: Status, @Param("today") today: LocalDate)
}