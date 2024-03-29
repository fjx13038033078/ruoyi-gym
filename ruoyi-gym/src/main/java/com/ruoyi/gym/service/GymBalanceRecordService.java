package com.ruoyi.gym.service;

/**
 * 会员余额交易记录服务接口
 * @Author fanjaixing
 * @Date 2024/2/17 22:45
 */

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.gym.domain.GymBalanceRecord;
import com.ruoyi.gym.domain.GymCourse;

import java.util.List;

public interface GymBalanceRecordService {

    /**
     * 添加余额交易记录
     * @param balanceRecord 余额交易记录对象
     * @return 是否添加成功
     */
    public boolean addBalanceRecord(GymBalanceRecord balanceRecord);

    /**
     * 获取所有的余额交易记录
     * @return 所有的余额交易记录列表
     */
    public List<GymBalanceRecord> getAllBalanceRecords();
}
