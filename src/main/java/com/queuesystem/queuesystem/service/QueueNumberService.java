package com.queuesystem.queuesystem.service;

import com.queuesystem.queuesystem.dto.QueueBaseDTO;
import com.queuesystem.queuesystem.result.BaseResult;
import com.queuesystem.queuesystem.vo.QueueNumberVO;

/**
 * Created by IntelliJ IDEA.
 *
 * @author 
 * description:
 * @since 2021/3/14 6:44pm
 * version: 1.0.0
 */
public interface QueueNumberService {
    
    BaseResult<QueueNumberVO> getQueueNumber(String firstName, String lastName);

    BaseResult<QueueNumberVO> queue(QueueBaseDTO queueBaseDTO);
}
