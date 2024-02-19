package com.ruoyi.gym.mapper;

import com.ruoyi.gym.domain.GymBalanceRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author fanjaixing
 * @Date 2024/2/17 22:50
 */
@Mapper
public interface GymBalanceRecordMapper {

    /**
     * 添加余额交易记录
     *
     * @param balanceRecord 余额交易记录对象
     * @return 是否添加成功
     */
    boolean addBalanceRecord(GymBalanceRecord balanceRecord);

    /**
     * 获取指定用户的余额交易记录
     *
     * @param userId 用户ID
     * @return 该用户的余额交易记录列表
     */
    List<GymBalanceRecord> getBalanceRecordsByUserId(Long userId);

    /**
     * 获取所有的余额交易记录
     *
     * @return 所有的余额交易记录列表
     */
    List<GymBalanceRecord> getAllBalanceRecords();

}
