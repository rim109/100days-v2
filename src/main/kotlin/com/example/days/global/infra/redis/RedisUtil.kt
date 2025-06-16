package com.example.days.global.infra.redis

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.data.redis.core.ValueOperations
import org.springframework.stereotype.Component
import java.time.Duration


@Component
class RedisUtil(
    private val stringRedisTemplate: StringRedisTemplate,
    private val redisTemplate: RedisTemplate<String, String>
) {

    fun getData(key: String): String? {
        val valueOperations: ValueOperations<String, String> = stringRedisTemplate.opsForValue()
        return valueOperations.get(key)
    }

    fun setData(key: String, value: String) {
        val valueOperations: ValueOperations<String, String> = stringRedisTemplate.opsForValue()
        valueOperations.set(key, value)
    }

    fun setDataExpire(key: String, value: String, duration: Long) {
        val valueOperations: ValueOperations<String, String> = stringRedisTemplate.opsForValue()
        val expireDuration: Duration = Duration.ofSeconds(duration)
        valueOperations.set(key, value, expireDuration)
    }

    // 메일 인증코드 확인
    fun getDataMatch(key: String, value: String, duration: Long): String? {
        val storedValue = getData(key)
        val oldValue = stringRedisTemplate.opsForValue().getAndSet(key, value)
        if (oldValue == storedValue) {
            Duration.ofSeconds(duration)
            setDataExpire(key, value, duration)
            return oldValue
        } else {
            return null
        }
    }

    fun deleteData(key: String) {
        stringRedisTemplate.delete(key)
    }
}