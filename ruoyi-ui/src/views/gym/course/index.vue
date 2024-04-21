<template>
  <div>
    <!-- 课程管理页面内容 -->
    <div v-if="activeTab === 'course'">
      <div class="app-container">
        <!-- 添加课程按钮 -->
        <el-row :gutter="20" class="mb-20" style="margin-bottom: 20px;">
          <el-col>
            <el-button type="primary" @click="handleAddCourse" v-hasPermi="['gym:course:add']">新增课程</el-button>
          </el-col>
        </el-row>

        <!-- 课程列表 -->
        <el-table :data="courseList" v-loading="loading" style="width: 100%" border>
        <el-table-column label="课程ID" prop="courseId" align="center" width="100px"></el-table-column>
        <el-table-column label="课程名称" prop="courseName" align="center"></el-table-column>
        <el-table-column label="课程时间" prop="courseTime" align="center" width="200px"></el-table-column>
        <el-table-column label="教练名称" prop="trainerName" align="center"></el-table-column>
        <el-table-column label="课程费用" prop="courseFee" align="center"></el-table-column>
        <el-table-column label="课程描述" prop="courseDescription" align="center" width="300px"></el-table-column>
        <el-table-column label="操作" align="center" width="350px">
          <template slot-scope="scope">
            <el-button type="warning" size="mini" @click="handleAddBalanceRecord(scope.row)" v-hasPermi="['gym:course:sign']">购课</el-button>
            <el-button type="info" size="mini" @click="handleComment(scope.row)" v-hasPermi="['gym:course:watchComment']">评论</el-button>
            <el-button type="success" size="mini" @click="handleView(scope.row)" v-hasPermi="['gym:course:watch']">查看</el-button>
            <el-button type="primary" size="mini" @click="handleEdit(scope.row)" v-hasPermi="['gym:course:update']">编辑</el-button>
            <el-button type="danger" size="mini" @click="handleDelete(scope.row)" v-hasPermi="['gym:course:delete']">删除</el-button>
          </template>
        </el-table-column>
      </el-table>

        <!-- 分页组件 -->
        <pagination
          v-show="totalCourses>0"
          :total="totalCourses"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="fetchCourses"
        />

        <!-- 添加/编辑课程对话框 -->
        <el-dialog :visible.sync="dialogVisible" :title="dialogTitle" width="30%" @close="handleCloseDialog">
          <!-- 对话框内容 -->
          <div>
            <el-form :model="courseForm" label-width="100px">
              <!-- 课程ID -->
              <!--              <el-form-item label="课程ID">-->
              <!--                <el-input v-model="courseForm.courseId" disabled></el-input>-->
              <!--              </el-form-item>-->
              <el-form-item label="课程名称">
                <el-input v-model="courseForm.courseName" :disabled="isReadOnly"></el-input>
              </el-form-item>
              <el-form-item label="课程描述">
                <el-input v-model="courseForm.courseDescription" :disabled="isReadOnly"></el-input>
              </el-form-item>
              <el-form-item label="课程时间">
                <el-date-picker v-model="courseForm.courseTime" type="datetime" placeholder="选择日期时间"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                :disabled="isReadOnly"></el-date-picker>
              </el-form-item>
              <el-form-item label="教练名称" v-show="isShow">
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

        <!-- 评论对话框 -->
        <el-dialog :visible.sync="commentDialogVisible" title="评论详情" class="comment-dialog">
          <!-- 判断是否有评论，如果没有则显示暂无评论 -->
          <div v-if="selectedCourseComments.length === 0" class="no-comment">
            <p class="center-text">该课程暂无评论</p>
          </div>
          <!-- 评论内容 -->
          <div v-for="(comment, index) in selectedCourseComments" :key="index" class="comment-item">
            <div class="comment-info">
              <span class="comment-user">评论人：{{ comment.userName }}</span>
              <span class="comment-time">评论时间：{{ comment.commentTime }}</span>
            </div>
            <div class="comment-content">
              <p>{{ comment.commentContent }}</p>
              <!-- 删除按钮 -->
              <el-button type="danger" size="mini" @click="handleDeleteComment(comment.commentId)" v-hasPermi="['gym:course:deleteComment']">删除</el-button>
            </div>
          </div>

          <!-- 分割线 -->
          <div class="divider"></div>

          <!-- 新增评论输入框 -->
          <div class="new-comment">
            <el-input v-model="newComment" placeholder="请输入评论内容"></el-input>
            <el-button type="success" size="mini" @click="handleSendComment" v-hasPermi="['gym:course:addComment']">发送评论</el-button>
          </div>
        </el-dialog>

      </div>
    </div>
  </div>
</template>

<script>
import {
  listCourses,
  addCourse,
  updateCourse,
  deleteCourse,
  getCourse,
  deleteComment,
  addComment,
  listCommentsById,
} from '@/api/gym/course'

import {
  addBalanceRecord
} from '@/api/gym/balanceRecord'

export default {
  data() {
    return {
      // 遮罩层
      loading: true,
      activeTab: 'course', // 当前激活的选项卡，默认为课程管理
      courseList: [], // 课程列表数据
      // 总条数
      totalCourses: 0,
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
      isReadOnly: false,// 是否只读模式
      isShow: true,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
      commentDialogVisible: false, // 评论对话框是否可见
      selectedCourse: {}, // 保存当前选中的课程信息
      selectedCourseComments: [], // 初始化评论列表为空数组
      newComment: '', // 存储用户输入的评论内容
    }
  },
  created() {
    // 在页面加载时获取课程列表
    this.fetchCourses()
  },
  methods: {
    // 获取课程列表
    fetchCourses() {
      this.loading = true;
      listCourses(this.queryParams).then(response => {
        this.courseList = response.rows;
        this.totalCourses = response.total;
        this.loading = false;
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
      this.isShow = false;
      this.isReadOnly = false; // 设置为只读模式
      this.dialogVisible = true; // 打开对话框
    },
    // 添加课程
    addCourse(courseData) {
      // 验证课程名称是否为空
      if (!this.courseForm.courseName) {
        this.$message.error('课程名称不能为空');
        return;
      }
      addCourse(this.courseForm).then(response => {
        // 处理添加课程成功的情况
        // 添加成功后重新获取课程列表
        this.fetchCourses();
        this.dialogVisible = false; // 关闭对话框
        // 清空表单数据
        this.clearForm();
      })
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
      })
    },
    // 删除课程
    deleteCourse(courseId) {
      deleteCourse(courseId).then(response => {
        // 处理删除课程成功的情况
        // 删除成功后重新获取课程列表
        this.fetchCourses()
      })
    },
    // 编辑按钮点击事件
    handleEdit(row) {
      // 将编辑的课程数据填充到表单中
      this.courseForm = Object.assign({}, row);
      this.dialogTitle = '编辑课程'; // 设置对话框标题为编辑课程
      this.dialogButtonText = '更新'; // 设置对话框按钮文本为更新
      this.isReadOnly = false; // 设置为可编辑模式
      this.isShow = false;
      this.dialogVisible = true; // 打开对话框
    },
    // 提交表单
    handleSubmit() {
      if (this.dialogButtonText === '更新') {
        // 调用更新课程的方法
        this.updateCourse(this.courseForm);
      } else if (this.dialogButtonText === "添加") {
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
      })
    },
    // 查看课程
    handleView(row) {
      this.dialogTitle = '查看课程'; // 设置对话框标题为查看课程
      this.dialogButtonText = '关闭'; // 设置对话框按钮文本为关闭
      this.isReadOnly = true; // 设置为只读模式
      this.isShow = true;
      // 调用后端接口获取课程详细信息
      getCourse(row.courseId).then(response => {
        this.courseForm = response.data; // 填充表单数据
        this.dialogVisible = true; // 打开对话框
      })
    },
    handleCloseDialog() {
      this.clearForm();
      this.dialogVisible = false;
    },

    // 处理评论按钮点击事件
    handleComment(row) {
      this.selectedCourse = row;
      // 调用后端接口获取课程评论列表
      listCommentsById(row.courseId).then(response => {
        // 将获取到的评论列表赋值给 selectedCourseComments
        this.selectedCourseComments = response.rows;
        // 打开评论对话框
        this.commentDialogVisible = true;
      }).catch(error => {
        // 处理请求失败的情况
        console.error('获取评论列表失败：', error);
        this.$message.error('获取评论列表失败，请稍后重试');
      });
    },

    // 处理删除评论按钮点击事件
    handleDeleteComment(commentId) {
      // 调用删除评论接口
      deleteComment(commentId).then(response => {
        // 处理删除成功的情况
        this.$message.success('删除评论成功');
        this.commentDialogVisible = false; // 关闭评论对话框
      })
    },

    // 发送评论按钮点击事件处理方法
    handleSendComment() {
      // 获取用户输入的评论内容
      const commentContent = this.newComment.trim();
      if (!commentContent) {
        // 如果评论内容为空，提示用户输入评论内容
        this.$message.error('请输入评论内容');
        return;
      }
      // 构造评论数据
      const commentData = {
        courseId: this.selectedCourse.courseId, // 课程ID
        commentContent: commentContent, // 评论内容
      };
      // 调用新增评论的方法
      addComment(commentData).then(response => {
        // 处理新增评论成功的情况
        this.$message.success('评论成功');
        // 清空输入框内容
        this.newComment = '';
        this.commentDialogVisible = false; // 关闭评论对话框
      })
    },
    handleAddBalanceRecord(row){
      // const balanceRecordData = {
      //   courseId: row.courseId, // 课程ID
      //   courseName:row.courseName,//课程名称
      //   transactionAmount: row.courseFee, // 交易费用
      // };
      // addBalanceRecord(balanceRecordData).then(response => {
      //   // 处理新增评论成功的情况
      //   this.$message.success('购课成功')
      // })
      // 弹出确认框，提示用户购课
      this.$confirm(`确认购买课程《${row.courseName}》吗？价格为：${row.courseFee}元`, '购课确认', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        // 用户点击确定后执行购课操作
        const balanceRecordData = {
          courseId: row.courseId, // 课程ID
          courseName: row.courseName, // 课程名称
          transactionAmount: row.courseFee, // 交易费用
        };
        addBalanceRecord(balanceRecordData).then(response => {
          // 处理新增评论成功的情况
          this.$message.success('购课成功');
        })
      }).catch(() => {
        // 用户点击取消或点击遮罩层后执行的操作
        this.$message.info('已取消购课');
      });
    }
  }
}
</script>

<style scoped>
/* 分割线样式 */
.center-text {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100%;
  font-size: 15px; /* 设置字体大小为20像素 */
}

/* 评论对话框样式 */
.comment-dialog {
  max-height: 800px; /* 设置最大高度 */
  overflow-y: auto; /* 添加纵向滚动条 */
}

/* 评论样式 */
.comment-item {
  margin-bottom: 20px;
  border: 1px solid #eee;
  padding: 10px;
}

/* 调整评论人和评论时间的间距 */
.comment-info {
  margin-bottom: 5px; /* 增加评论人和评论时间之间的垂直间距 */
  border-bottom: 1px solid #ccc; /* 添加底部边框，实现表格线效果 */
  padding-bottom: 15px; /* 调整底部边框与评论内容之间的间距 */
}

.comment-time {
  margin-left: 300px; /* 增加评论时间与评论人之间的水平间距 */
}

/* 新增评论输入框样式 */
.new-comment {
  margin-top: 20px; /* 设置输入框与上方元素的垂直间距 */
}

/* 调整发送评论按钮的垂直间距 */
.new-comment .el-button {
  margin-top: 10px; /* 设置按钮与上方元素的垂直间距 */
}

/* 分割线样式 */
.divider {
  border-top: 3px solid #ccc;
  margin-top: 20px; /* 调整分割线上方的间距 */
  margin-bottom: 20px; /* 调整分割线下方的间距 */
}
</style>
