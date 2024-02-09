package com.example.redis.controller

import com.example.redis.common.Log
import com.example.redis.service.NoticeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/notice")
class NoticeApiController (
    private val noticeService: NoticeService
) {

    companion object: Log

    @GetMapping("/get-notice")
    fun getNotice(
        @RequestParam notice: String?
    ): String? {
        val response = noticeService.getNotice(notice)
        log.info("notice controller get notice: {}, response: {}", notice, response)
        return response
    }

    @GetMapping("/add-notice")
    fun addNotice(
        @RequestParam notice: String?
    ): String? {
        val response = noticeService.addNotice(notice)
        log.info("notice controller add notice: {}, response: {}", notice, response)
        return response
    }

}