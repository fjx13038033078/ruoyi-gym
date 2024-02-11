<template>
  <div>
    <!-- 课程管理页面内容 -->
    <div v-if="activeTab === 'course'">
      <div class="app-container">
        <!-- 添加课程按钮 -->
        <el-row :gutter="20" class="mb-20" style="margin-bottom: 20px;">
          <el-col>
            <el-button type="primary" @click="handleAddCourse">新增课程</el-button>
          </el-col>
        </el-row>

        <!-- 课程列表 -->
        <el-table :data="courseList" style="width: 100%" border>
          <el-table-column label="课程ID" prop="courseId" align="center"></el-table-column>
          <el-table-column label="课程名称" prop="courseName" align="center"></el-table-column>
          <el-table-column label="课程描述" prop="courseDescription" align="center"></el-table-column>
          <el-table-column label="课程时间" prop="courseTime" align="center"></el-table-column>
          <el-table-column label="课程地点" prop="courseLocation" align="center"></el-table-column>
          <el-table-column label="教练名称" prop="trainerName" align="center"></el-table-column>
          <el-table-column label="课程费用" prop="courseFee" align="center"></el-table-column>
          <el-table-column label="操作" align="center" width="230px">
            <template slot-scope="scope">
              <el-button type="success" size="mini" @click="handleView(scope.row)">查看</el-button>
              <el-button type="primary" size="mini" @click="handleEdit(scope.row)">编辑</el-button>
              <el-button type="danger" size="mini" @click="handleDelete(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>

        <!-- 添加/编辑课程对话框 -->
        <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="30%" @close="handleCloseDialog">
          <!-- 对话框内容 -->
          <div>
            <el-form :model="courseForm" label-width="100px">
              <!-- 课程ID -->
              <el-form-item label="课程ID">
                <el-input v-model="courseForm.courseId" disabled></el-input>
              </el-form-item>
              <el-form-item label="课程名称">
                <el-input v-model="courseForm.courseName" :disabled="isReadOnly"></el-input>
              </el-form-item>
              <el-form-item label="课程描述">
                <el-input v-model="courseForm.courseDescription" :disabled="isReadOnly"></el-input>
              </el-form-item>
              <el-form-item label="课程时间">
                <el-date-picker v-model="courseForm.courseTime" type="datetime" placeholder="选择日期时间" :disabled="isReadOnly"></el-date-picker>
              </el-form-item>
              <el-form-item label="课程地点">
                <el-input v-model="courseForm.courseLocation" :disabled="isReadOnly"></el-input>
              </el-form-item>
              <el-form-item label="教练名称">
                <el-input v-model="courseForm.trainerName" :disabled="isReadOnly"></el-input>
              </el-form-item>
              <el-form-item label="课程费用">
                <el-input v-model="courseForm.courseFee" type="number" :disabled="isReadOnly"></el-input>
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
import {listCourses, addCourse, updateCourse, deleteCourse, getCourse} from '@/api/gym/course'

export default {
  data() {
    return {
      activeTab: 'course', // 当前激活的选项卡，默认为课程管理
      courseList: [], // 课程列表数据
      dialogVisible: false,// 控制新增课程对话框的显示与隐藏
      dialogTitle: '', // 对话框标题
      dialogButtonText: '', // 对话框按钮文本
      courseForm: { // 新增课程表单
        courseName: '',
        courseDescription: '',
        courseTime: '',
        courseLocation: '',
        trainerName: '',
        courseFee: ''
      },
      isReadOnly: false // 是否只读模式
    }
  },
  created() {
    // 在页面加载时获取课程列表
    this.fetchCourses()
  },
  methods: {
    // 获取课程列表
    fetchCourses() {
      listCourses().then(response => {
        this.courseList = response.rows; // 注意这里根据实际返回数据的结构来修改
      }).catch(error => {
        // 处理异常情况
      })
    },
    clearForm() {
      // 清空表单数据
      this.courseForm = {
        courseName: '',
        courseDescription: '',
        courseTime: '',
        courseLocation: '',
        trainerName: '',
        courseFee: ''
      };
    },
    handleAddCourse() {
      this.dialogTitle = "新增课程";
      this.dialogButtonText = "添加"
      this.isReadOnly = false; // 设置为只读模式
      this.dialogVisible = true; // 打开对话框
    },
    // 添加课程
    addCourse(courseData) {
      console.log("111")
      // 验证课程名称是否为空
      if (!this.courseForm.courseName) {
        this.$message.error('课程名称不能为空');
        return;
      }
      addCourse(this.courseForm).then(response => {
        console.log("11111")
        // 处理添加课程成功的情况
        // 添加成功后重新获取课程列表
        this.fetchCourses();
        this.dialogVisible = false; // 关闭对话框
        // 清空表单数据
        this.clearForm();
      }).catch(error => {
        // 处理添加课程失败的情况
      });
    },
    // 更新课程
    updateCourse(courseData) {
      updateCourse(courseData).then(response => {
        // 处理更新课程成功的情况
        // 更新成功后重新获取课程列表
        this.fetchCourses();
        this.dialogVisible = false; // 关闭对话框
        // 清空表单数据
        this.clearForm();
        // 将对话框按钮文本设置为其他值，避免再次触发更新操作
        this.dialogButtonText = '更新成功';
      }).catch(error => {
        // 处理更新课程失败的情况
      });
    },
    // 删除课程
    deleteCourse(courseId) {
      deleteCourse(courseId).then(response => {
        // 处理删除课程成功的情况
        // 删除成功后重新获取课程列表
        this.fetchCourses()
      }).catch(error => {
        // 处理删除课程失败的情况
      })
    },
    // 编辑按钮点击事件
    handleEdit(row) {
      // 将编辑的课程数据填充到表单中
      this.courseForm = Object.assign({}, row);
      this.dialogTitle = '编辑课程'; // 设置对话框标题为编辑课程
      this.dialogButtonText = '更新'; // 设置对话框按钮文本为更新
      this.isReadOnly = false; // 设置为可编辑模式
      this.dialogVisible = true; // 打开对话框
    },
    // 提交表单
    handleSubmit() {
      if (this.dialogButtonText === '更新') {
        // 调用更新课程的方法
        this.updateCourse(this.courseForm);
      } else if (this.dialogButtonText === "添加"){
        // 调用添加课程的方法
        this.addCourse(this.courseForm);
      } else {
        this.handleCloseDialog()
      }
    },
    // 删除按钮点击事件
    handleDelete(row) {
      // 弹出确认框，确认删除后调用删除课程方法
      this.$confirm('确认删除该课程吗？', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.deleteCourse(row.courseId)
      }).catch(() => {
        // 用户取消删除操作
      })
    },
    // 查看课程
    handleView(row) {
      this.dialogTitle = '查看课程'; // 设置对话框标题为查看课程
      this.dialogButtonText = '关闭'; // 设置对话框按钮文本为关闭
      this.isReadOnly = true; // 设置为只读模式
      // 调用后端接口获取课程详细信息
      getCourse(row.courseId).then(response => {
        this.courseForm = response.data; // 填充表单数据
        this.dialogVisible = true; // 打开对话框
      }).catch(error => {
        // 处理异常情况
      });
    },
    handleCloseDialog() {
      this.clearForm();
      this.dialogVisible = false;
    }
  }
}
</script>
