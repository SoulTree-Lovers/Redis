package com.example.redis.service

import com.example.redis.common.Log
import com.example.redis.repository.NoticeRepository
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class NoticeService (
    private val noticeRepository: NoticeRepository
) {

    companion object: Log

    /**
     * cacheNames에 들어간 notice + key에 들어간 실제 인자 값이 더해져
     * notice::[인자값]이 key가 된다.
     * value는 return하는 값으로 지정된다.
     */
    @Cacheable(cacheNames = ["notice"], key = "#notice")
    fun getNotice(notice: String?): String? {
        log.info("notice service get notice: {}", notice)
        return noticeRepository.getNotice(notice)
    }

    // redis 캐시에 저장
    @CachePut(cacheNames = ["notice"], key = "#notice") // #notice는 파라미터 이름과 매핑
    fun addNotice(notice: String?): String? {
        log.info("notice service add notice: {}", notice)
        return noticeRepository.addNotice(notice)
    }
}