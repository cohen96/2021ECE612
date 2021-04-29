package com.queuesystem.queuesystem.service.impl;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.queuesystem.queuesystem.dao.QueueInfo;
import com.queuesystem.queuesystem.dto.QueueBaseDTO;
import com.queuesystem.queuesystem.mapper.QueueInfoMapper;
import com.queuesystem.queuesystem.result.BaseResult;
import com.queuesystem.queuesystem.service.QueueNumberService;
import com.queuesystem.queuesystem.util.IdWorkerUtil;
import com.queuesystem.queuesystem.vo.QueueNumberVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author
 * description:
 * @since 2021/3/14 6:44pm
 * version: 1.0.0
 */
@Slf4j
@Service
public class QueueNumberServiceImpl implements QueueNumberService {
    @Resource
    private QueueInfoMapper queueInfoMapper;
    @Resource
    private IdWorkerUtil idWorkerUtil;

    @Override
    public BaseResult<QueueNumberVO> getQueueNumber(String firstName, String lastName) {
        return null;
    }

    @Override
    public BaseResult<QueueNumberVO> queue(QueueBaseDTO queueBaseDTO) {
        String firstName = queueBaseDTO.getFirstName();
        String lastName = queueBaseDTO.getLastName();
        String userId = queueBaseDTO.getUserId();
        long nextId = idWorkerUtil.nextId();
        QueueInfo queueInfo = new QueueInfo();
        queueInfo.setFirstName(firstName);
        queueInfo.setLastName(lastName);
        queueInfo.setSnowFlakeId(nextId);
        queueInfo.setCreateTime(System.currentTimeMillis());
        queueInfo.setUpdateTime(System.currentTimeMillis());
        queueInfo.setCreateUsername("admin");
        queueInfo.setUpdateUsername("admin");
        queueInfo.setUserId(userId);
        queueInfoMapper.insertOrUpdateSelective(queueInfo);
        Integer total = queueInfoMapper.selectCount(Wrappers.lambdaQuery());
        List<QueueInfo> queueInfos = queueInfoMapper.selectList(
                Wrappers.<QueueInfo>lambdaQuery()
                        .eq(QueueInfo::getFirstName, firstName)
                        .eq(QueueInfo::getLastName, lastName)
        );
        QueueInfo queueInfo1 = queueInfos.get(queueInfos.size() - 1);
        QueueNumberVO queueNumberVO = new QueueNumberVO();
        queueNumberVO.setFirstName(firstName);
        queueNumberVO.setLastName(lastName);
        queueNumberVO.setUserId(userId);
        queueNumberVO.setQueueIndex(queueInfo1.getId().toString());
        queueNumberVO.setQueueTotal(total.toString());
        queueNumberVO.setQueueInfo(String.format("current queue %s, your position is %s", total, queueInfo1.getId()));
        return BaseResult.of(queueNumberVO);
    }
}
