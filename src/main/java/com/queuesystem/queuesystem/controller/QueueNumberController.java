package com.queuesystem.queuesystem.controller;

import com.queuesystem.queuesystem.dto.QueueBaseDTO;
import com.queuesystem.queuesystem.result.BaseResult;
import com.queuesystem.queuesystem.service.QueueNumberService;
import com.queuesystem.queuesystem.vo.QueueNumberVO;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;

/**
 * Created by IntelliJ IDEA.
 *
 * @author
 * description:
 * @since 2021/3/14 下午6:35
 * version: 1.0.0
 */
@RequestMapping("/queue/base")
@RestController
public class QueueNumberController {

    private final QueueNumberService queueNumberService;

    public QueueNumberController(QueueNumberService queueNumberService) {
        this.queueNumberService = queueNumberService;
    }

    @GetMapping("/getQueueInfo")
    public BaseResult<QueueNumberVO> getQueueNumber(
            String firstName,
            String lastName
    ) {
        return queueNumberService.getQueueNumber(firstName, lastName);
    }

    @PostMapping("/queue")
    public BaseResult<QueueNumberVO> queue(
            @RequestBody QueueBaseDTO queueBaseDTO
    ) {
        Assert.isTrue(queueBaseDTO.getFirstName()!=null && !queueBaseDTO.getFirstName().equals(""), "请求参数firstName为空");
        Assert.isTrue(queueBaseDTO.getLastName()!=null && !queueBaseDTO.getLastName().equals(""), "请求参数lastName为空");
        Assert.isTrue(queueBaseDTO.getUserId()!=null && !queueBaseDTO.getUserId().equals(""), "请求参数userId为空");
        return queueNumberService.queue(queueBaseDTO);
    }

}
