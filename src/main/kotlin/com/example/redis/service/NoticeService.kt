package com.example.redis.service

import com.example.redis.common.Log
import com.example.redis.model.NoticeDto
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
    @Cacheable(cacheNames = ["notice"], key = "#id")
    fun getNotice(id: Long?): NoticeDto? {
        log.info("notice service get notice: {}", id)
        return noticeRepository.getNotice(id)
    }

    // redis 캐시에 저장
    @CachePut(cacheNames = ["notice"], key = "#notice.id") // #notice는 파라미터 이름과 매핑
    fun addNotice(notice: NoticeDto?): NoticeDto? {
        log.info("notice service add notice: {}", notice)
        return noticeRepository.addNotice(notice)
    }
}