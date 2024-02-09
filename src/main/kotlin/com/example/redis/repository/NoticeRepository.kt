package com.example.redis.repository

import com.example.redis.common.Log
import org.springframework.stereotype.Service

@Service
class NoticeRepository {

    // Logger 불러오기
    companion object: Log

    private val noticeList = mutableListOf<String>()

    // redis 캐시 사용
    fun getNotice(notice: String?): String? {
        log.info("get notice method call: {}", notice)
        return noticeList.filter { it -> it == notice }.firstOrNull()
    }

    fun addNotice(notice: String?): String? {
        log.info("repository add notice method call: {}", notice)
        notice?.let { noticeList.add(it) }
        return notice
    }
}