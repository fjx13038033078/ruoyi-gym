package com.ruoyi.gym.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 余额交易记录实体类
 * @author fanjiaxing
 */
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@TableName("gym_balance_record") // 对应数据库表名
public class GymBalanceRecord implements Serializable {

    @TableId(type = IdType.AUTO)
    private Long transactionId; // 交易ID，主键

    private Long userId; // 用户ID

    @TableField(exist = false)
    private String userName; // 用户姓名

    private Long courseId; // 课程ID

    @TableField(exist = false)
    private String courseName; // 课程名称

    private BigDecimal transactionAmount; // 交易金额

    private LocalDateTime transactionTime; // 交易时间

    private BigDecimal balance; // 余额

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}
