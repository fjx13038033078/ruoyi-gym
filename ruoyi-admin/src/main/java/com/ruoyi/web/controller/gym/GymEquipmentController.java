package com.ruoyi.web.controller.gym;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.gym.domain.GymEquipment;
import com.ruoyi.gym.service.GymEquipmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.ruoyi.common.utils.PageUtils.startPage;

/**
 * @Author fanjaixing
 * @Date 2024/2/10 22:45
 */
@RequiredArgsConstructor
@RestController
@RequestMapping("/gym/equipment")
public class GymEquipmentController extends BaseController {

    private final GymEquipmentService gymEquipmentService;

    // 获取所有设备列表
    @GetMapping("/queryAll")
    public TableDataInfo listAllEquipments() {
        startPage();
        List<GymEquipment> gymEquipmentList = gymEquipmentService.listAllEquipments();
        return getDataTable(gymEquipmentList);
    }

    // 根据设备ID获取设备信息
    @GetMapping("/getDetail")
    public AjaxResult getEquipmentById(@RequestParam Long equipmentId) {
        return success(gymEquipmentService.getEquipmentById(equipmentId));
    }

    // 添加设备
    @PostMapping("/add")
    public AjaxResult addEquipment(@RequestBody GymEquipment equipment) {
        return toAjax(gymEquipmentService.addEquipment(equipment));
    }

    // 更新设备信息
    @PostMapping("/update")
    public AjaxResult updateEquipment(@RequestBody GymEquipment equipment) {
        return toAjax(gymEquipmentService.updateEquipment(equipment));
    }

    // 删除设备
    @GetMapping("/delete")
    public AjaxResult deleteEquipment(@RequestParam Long equipmentId) {
        return toAjax(gymEquipmentService.deleteEquipment(equipmentId));
    }

}
