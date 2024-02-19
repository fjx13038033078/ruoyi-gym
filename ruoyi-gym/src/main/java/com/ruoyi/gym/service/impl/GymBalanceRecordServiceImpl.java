package com.ruoyi.gym.service.impl;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.gym.domain.GymBalanceRecord;
import com.ruoyi.gym.domain.GymCourse;
import com.ruoyi.gym.mapper.GymBalanceRecordMapper;
import com.ruoyi.gym.service.GymBalanceRecordService;
import com.ruoyi.gym.service.GymCourseService;
import com.ruoyi.system.service.ISysRoleService;
import com.ruoyi.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * @Author fanjaixing
 * @Date 2024/2/17 23:01
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class GymBalanceRecordServiceImpl implements GymBalanceRecordService {

    private final GymBalanceRecordMapper gymBalanceRecordMapper;

    private final ISysRoleService iSysRoleService;

    private final GymCourseService gymCourseService;

    private final ISysUserService iSysUserService;

    /**
     * 添加余额交易记录
     *
     * @param balanceRecord 余额交易记录对象
     * @return 是否添加成功
     */
    @Override
    @Transactional
    public boolean addBalanceRecord(GymBalanceRecord balanceRecord) {
        // 获取当前登录用户ID
        Long userId = SecurityUtils.getUserId();
        // 获取当前登录用户姓名
        String userName = SecurityUtils.getUsername();
        // 获取当前用户的余额
        BigDecimal userBalance = iSysUserService.selectUserById(userId).getBalance();
        // 获取当前用户的角色
        Set<String> roles = iSysRoleService.selectRolePermissionByUserId(userId);
        // 遍历用户角色，如果是教练或管理员则抛出异常
        for (String role : roles) {
            if ("coach".equals(role) || "admin".equals(role)) {
                throw new RuntimeException("教练员或管理员无法报名课程");
            }
        }
        // 获取课程费用
        BigDecimal courseFee = balanceRecord.getTransactionAmount();
        // 判断余额是否足够支付课程费用
        if (userBalance.compareTo(courseFee) < 0) {
            throw new RuntimeException("您的余额不足");
        }

        // 获取报名课程对应的教练ID
        Long courseId = balanceRecord.getCourseId();
        GymCourse course = gymCourseService.getCourseById(courseId);
        Long trainerId = course.getTrainerId();
        // 获取教练当前余额
        BigDecimal trainerBalance = iSysUserService.selectUserById(trainerId).getBalance();

        // 计算用户实际交易金额（考虑VIP用户）
        BigDecimal actualTransactionAmount = roles.contains("common") ? courseFee : courseFee.multiply(BigDecimal.valueOf(0.8)).setScale(2, RoundingMode.HALF_UP);
        // 将用户实际消费的金额从用户余额中扣除
        BigDecimal newBalance = userBalance.subtract(actualTransactionAmount);

        // 更新用户余额信息
        SysUser currentUser = iSysUserService.selectUserById(userId);
        currentUser.setBalance(newBalance);
        iSysUserService.updateUserBalance(currentUser);

        // 设置用户消费记录信息
        GymBalanceRecord userBalanceRecord = new GymBalanceRecord();
        userBalanceRecord.setUserId(userId);
        userBalanceRecord.setUserName(userName);
        userBalanceRecord.setCourseId(courseId);
        userBalanceRecord.setTransactionAmount(actualTransactionAmount);
        userBalanceRecord.setTransactionTime(LocalDateTime.now());
        userBalanceRecord.setBalance(newBalance);

        // 添加用户消费记录
        gymBalanceRecordMapper.addBalanceRecord(userBalanceRecord);

        // 更新教练的余额信息
        BigDecimal newTrainerBalance = trainerBalance.add(actualTransactionAmount);
        SysUser trainer = iSysUserService.selectUserById(trainerId);
        trainer.setBalance(newTrainerBalance);
        iSysUserService.updateUserBalance(trainer);

        // 设置教练收益记录信息
        GymBalanceRecord trainerBalanceRecord = new GymBalanceRecord();
        trainerBalanceRecord.setUserId(trainerId);
        trainerBalanceRecord.setUserName(trainer.getUserName());
        trainerBalanceRecord.setCourseId(courseId);
        trainerBalanceRecord.setTransactionAmount(actualTransactionAmount);
        trainerBalanceRecord.setTransactionTime(LocalDateTime.now());
        trainerBalanceRecord.setBalance(newTrainerBalance);

        // 添加教练收益记录
        gymBalanceRecordMapper.addBalanceRecord(trainerBalanceRecord);

        return true; // 表示操作成功
    }


    /**
     * 获取所有的余额交易记录
     *
     * @return 所有的余额交易记录列表
     */
    @Override
    public List<GymBalanceRecord> getAllBalanceRecords() {
        // 获取当前登录用户ID
        Long userId = SecurityUtils.getUserId();
        // 获取当前用户的角色
        Set<String> roles = iSysRoleService.selectRolePermissionByUserId(userId);
        // 判断用户角色
        if (roles.contains("admin")) {
            // 如果当前用户是管理员，则查询所有交易记录
            startPage();
            List<GymBalanceRecord> allRecords = gymBalanceRecordMapper.getAllBalanceRecords();
            // 填充课程名称和用户名称
            fillCourseAndUserName(allRecords);
            return allRecords;
        } else {
            // 如果不是管理员，则只查询与当前用户相关的交易记录
            startPage();
            List<GymBalanceRecord> userRecords = gymBalanceRecordMapper.getBalanceRecordsByUserId(userId);
            // 填充课程名称和用户名称
            fillCourseAndUserName(userRecords);
            return userRecords;
        }
    }

    /**
     * 填充交易记录中的课程名称和用户名称
     *
     * @param records 待填充的交易记录列表
     */
    private void fillCourseAndUserName(List<GymBalanceRecord> records) {
        for (GymBalanceRecord record : records) {
            // 获取课程名称
            Long courseId = record.getCourseId();
            GymCourse course = gymCourseService.getCourseById(courseId);
            if (course != null) {
                record.setCourseName(course.getCourseName());
            }
            // 获取用户名称
            Long userId = record.getUserId();
            SysUser user = iSysUserService.selectUserById(userId);
            if (user != null) {
                record.setUserName(user.getUserName());
            }
        }
    }
}
