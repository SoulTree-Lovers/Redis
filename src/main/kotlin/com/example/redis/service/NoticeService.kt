package com.example.redis.service

import com.example.redis.common.Log
import com.example.redis.repository.NoticeRepository
import org.springframework.stereotype.Service

@Service
class NoticeService (
    private val noticeRepository: NoticeRepository
) {

    companion object: Log

    fun getNotice(notice: String?): String? {
      log.info("notice service get notice: {}", notice)
      return noticeRepository.getNotice(notice)
    }

    fun addNotice(notice: String?): String? {
        log.info("notice service add notice: {}", notice)
        return noticeRepository.addNotice(notice)
    }
}