package com.example.redis.repository

import com.example.redis.common.Log
import com.example.redis.model.NoticeDto
import com.example.redis.service.NoticeService
import org.springframework.stereotype.Service

@Service
class NoticeRepository {

    // Logger 불러오기
    companion object: Log

    private val noticeList = mutableListOf<NoticeDto>()

    // redis 캐시 사용
    fun getNotice(id: Long?): NoticeDto? {
        log.info("get notice method call: {}", id)
        return noticeList.filter { it -> it.id == id }.firstOrNull()
    }

    fun addNotice(notice: NoticeDto?): NoticeDto? {
        log.info("repository add notice method call: {}", notice)

        val index = noticeList.size + 1

        return notice?.apply {
            this.id = index.toLong()
        }?.apply { // 받은 데이터를 그대로 리턴
            noticeList.add(this)
        }.also { // 받은 데이터를 그대로 리턴
            log.info("save Dto: {}", it)
        }
    }
}