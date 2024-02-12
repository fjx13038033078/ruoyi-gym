<template>
  <div>
    <!-- 设备管理页面内容 -->
    <div v-if="activeTab === 'equipment'">
      <div class="app-container">
        <!-- 添加设备按钮 -->
        <el-row :gutter="20" class="mb-20" style="margin-bottom: 20px;">
          <el-col>
            <el-button type="primary" @click="handleAddEquipment">新增设备</el-button>
          </el-col>
        </el-row>

        <!-- 设备列表 -->
        <el-table :data="equipmentList" v-loading="loading" style="width: 100%" border>
          <el-table-column label="设备ID" prop="equipmentId" align="center"></el-table-column>
          <el-table-column label="设备名称" prop="equipmentName" align="center"></el-table-column>
          <el-table-column label="设备数量" prop="equipmentQuantity" align="center"></el-table-column>
          <el-table-column label="操作" align="center" width="230px">
            <template slot-scope="scope">
              <el-button type="success" size="mini" @click="handleView(scope.row)">查看</el-button>
              <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
              <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <!-- 分页组件 -->
        <pagination
          v-show="totalEquipments > 0"
          :total="totalEquipments"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="fetchEquipments"
        />

        <!-- 添加/编辑设备对话框 -->
        <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="30%" @close="handleCloseDialog">
          <!-- 对话框内容 -->
          <div>
            <el-form :model="equipmentForm" label-width="100px">
              <!-- 设备ID -->
              <el-form-item label="设备ID">
                <el-input v-model="equipmentForm.equipmentId" disabled></el-input>
              </el-form-item>
              <el-form-item label="设备名称">
                <el-input v-model="equipmentForm.equipmentName" :disabled="isReadOnly"></el-input>
              </el-form-item>
              <el-form-item label="设备数量">
                <el-input v-model="equipmentForm.equipmentQuantity" type="number" :disabled="isReadOnly"></el-input>
              </el-form-item>
            </el-form>
          </div>
          <!-- 对话框按钮 -->
          <div slot="footer" class="dialog-footer" style="text-align: center;">
            <el-button @click="handleCloseDialog">取消</el-button>
            <el-button type="primary" @click="handleSubmit">{{ dialogButtonText }}</el-button>
          </div>
        </el-dialog>

      </div>
    </div>
  </div>
</template>

<script>
import { listEquipments, addEquipment, updateEquipment, deleteEquipment, getEquipment } from '@/api/gym/equipment'

export default {
  data() {
    return {
      // 遮罩层
      loading: true,
      activeTab: 'equipment', // 当前激活的选项卡，默认为设备管理
      equipmentList: [], // 设备列表数据
      // 总条数
      totalEquipments: 0,
      dialogVisible: false, // 控制新增设备对话框的显示与隐藏
      dialogTitle: '', // 对话框标题
      dialogButtonText: '', // 对话框按钮文本
      equipmentForm: { // 新增设备表单
        equipmentName: '',
        equipmentQuantity: '',
      },
      isReadOnly: false, // 是否只读模式
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
    }
  },
  created() {
    // 在页面加载时获取设备列表
    this.fetchEquipments()
  },
  methods: {
    // 获取设备列表
    fetchEquipments() {
      this.loading = true;
      listEquipments(this.queryParams).then(response => {
        this.equipmentList = response.rows;
        this.totalEquipments = response.total;
        this.loading = false;
      })
    },
    clearForm() {
      // 清空表单数据
      this.equipmentForm = {
        equipmentName: '',
        equipmentQuantity: '',
      };
    },
    handleAddEquipment() {
      this.dialogTitle = "新增设备";
      this.dialogButtonText = "添加"
      this.isReadOnly = false; // 设置为只读模式
      this.dialogVisible = true; // 打开对话框
    },
    // 添加设备
    addEquipment() {
      // 验证设备名称是否为空
      if (!this.equipmentForm.equipmentName) {
        this.$message.error('设备名称不能为空');
        return;
      }
      addEquipment(this.equipmentForm).then(response => {
        // 处理添加设备成功的情况
        // 添加成功后重新获取设备列表
        this.fetchEquipments();
        this.dialogVisible = false; // 关闭对话框
        // 清空表单数据
        this.clearForm();
      })
    },
    // 更新设备
    updateEquipment() {
      updateEquipment(this.equipmentForm).then(response => {
        // 处理更新设备成功的情况
        // 更新成功后重新获取设备列表
        this.fetchEquipments();
        this.dialogVisible = false; // 关闭对话框
        // 清空表单数据
        this.clearForm();
        // 将对话框按钮文本设置为其他值，避免再次触发更新操作
        this.dialogButtonText = '更新成功';
      })
    },
    // 删除设备
    deleteEquipment(equipmentId) {
      deleteEquipment(equipmentId).then(response => {
        // 处理删除设备成功的情况
        // 删除成功后重新获取设备列表
        this.fetchEquipments()
      })
    },
    // 编辑按钮点击事件
    handleEdit(row) {
      // 将编辑的设备数据填充到表单中
      this.equipmentForm = Object.assign({}, row);
      this.dialogTitle = '编辑设备'; // 设置对话框标题为编辑设备
      this.dialogButtonText = '更新'; // 设置对话框按钮文本为更新
      this.isReadOnly = false; // 设置为可编辑模式
      this.dialogVisible = true; // 打开对话框
    },
    // 提交表单
    handleSubmit() {
      if (this.dialogButtonText === '更新') {
        // 调用更新设备的方法
        this.updateEquipment();
      } else if (this.dialogButtonText === "添加") {
        // 调用添加设备的方法
        this.addEquipment();
      } else {
        this.handleCloseDialog()
      }
    },
    // 删除按钮点击事件
    handleDelete(row) {
      // 弹出确认框，确认删除后调用删除设备方法
      this.$confirm('确认删除该设备吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteEquipment(row.equipmentId)
      })
    },
    // 查看设备
    handleView(row) {
      this.dialogTitle = '查看设备'; // 设置对话框标题为查看设备
      this.dialogButtonText = '关闭'; // 设置对话框按钮文本为关闭
      this.isReadOnly = true; // 设置为只读模式
      // 调用后端接口获取设备详细信息
      getEquipment(row.equipmentId).then(response => {
        this.equipmentForm = response.data; // 填充表单数据
        this.dialogVisible = true; // 打开对话框
      })
    },
    handleCloseDialog() {
      this.clearForm();
      this.dialogVisible = false;
    },
  }
}
</script>

