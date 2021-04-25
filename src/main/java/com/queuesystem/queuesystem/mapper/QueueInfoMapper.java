package com.queuesystem.queuesystem.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.queuesystem.queuesystem.dao.QueueInfo;
import java.util.List;
import org.apache.ibatis.annotations.Param;


/**
 * Created by IntelliJ IDEA.
 *
 * @author niuwenyu
 * description:
 * @since 2021/4/25 下午9:33
 * version: 1.0.0
 */

public interface QueueInfoMapper extends BaseMapper<QueueInfo> {
    int updateBatch(List<QueueInfo> list);

    int updateBatchSelective(List<QueueInfo> list);

    int batchInsert(@Param("list") List<QueueInfo> list);

    int insertOrUpdate(QueueInfo record);

    int insertOrUpdateSelective(QueueInfo record);
}