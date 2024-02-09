package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.GymEquipment;
import generator.service.GymEquipmentService;
import generator.mapper.GymEquipmentMapper;
import org.springframework.stereotype.Service;

/**
* @author 86130
* @description 针对表【gym_equipment(健身器材表)】的数据库操作Service实现
* @createDate 2024-02-09 23:03:22
*/
@Service
public class GymEquipmentServiceImpl extends ServiceImpl<GymEquipmentMapper, GymEquipment>
    implements GymEquipmentService{

}




