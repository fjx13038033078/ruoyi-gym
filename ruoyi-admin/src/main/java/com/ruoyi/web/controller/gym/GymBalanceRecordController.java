package com.ruoyi.web.controller.gym;

import com.ruoyi.common.constant.HttpStatus;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.gym.domain.GymBalanceRecord;
import com.ruoyi.gym.service.GymBalanceRecordService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author fanjaixing
 * @Date 2024/2/18 15:43
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/gym/balanceRecord")
public class GymBalanceRecordController extends BaseController {

    private final GymBalanceRecordService gymBalanceRecordService;

    /**
     * 获取所有费用明细
     *
     * @return 课程评论列表
     */
    @GetMapping("/listAll")
    public TableDataInfo listAllBalanceRecords() {
//        startPage();
        List<GymBalanceRecord> gymBalanceRecordList = gymBalanceRecordService.getAllBalanceRecords();
        return getDataTable(gymBalanceRecordList);
    }

    /**
     * 添加费用明细
     *
     * @param gymBalanceRecord 费用明细
     * @return 是否添加成功
     */
    @PostMapping("/add")
    public AjaxResult addBalanceRecord(@RequestBody GymBalanceRecord gymBalanceRecord) {
        return toAjax(gymBalanceRecordService.addBalanceRecord(gymBalanceRecord));
    }

}
