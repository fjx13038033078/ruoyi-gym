<template>
  <div>
    <!-- 设备管理页面内容 -->
    <div v-if="activeTab === 'balanceRecord'">
      <div class="app-container">

        <!-- 设备列表 -->
        <el-table :data="balanceRecordList" v-loading="loading" style="width: 100%" border>
          <el-table-column label="交易ID" prop="transactionId" align="center"></el-table-column>
          <el-table-column label="课程名称" prop="courseName" align="center"></el-table-column>
          <el-table-column label="用户名称" prop="userName" align="center"></el-table-column>
          <el-table-column label="交易金额" prop="transactionAmount" align="center"></el-table-column>
          <el-table-column label="交易后余额" prop="balance" align="center"></el-table-column>
          <el-table-column label="交易时间" prop="transactionTime" align="center"></el-table-column>
        </el-table>

        <!-- 分页组件 -->
        <pagination
          v-show="totalBalanceRecord > 0"
          :total="totalBalanceRecord"
          :page.sync="queryParams.pageNum"
          :limit.sync="queryParams.pageSize"
          @pagination="fetchBalanceRecords"
        />

      </div>
    </div>
  </div>
</template>

<script>
import {
  listBalanceRecord
} from '@/api/gym/balanceRecord'

export default {
  data() {
    return {
      // 遮罩层
      loading: true,
      activeTab: 'balanceRecord', // 当前激活的选项卡，默认为设备管理
      balanceRecordList: [], // 设备列表数据
      // 总条数
      totalBalanceRecord: 0,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
      },
    }
  },
  created() {
    // 在页面加载时获取设备列表
    this.fetchBalanceRecords()
  },
  methods: {
    // 获取设备列表
    fetchBalanceRecords() {
      this.loading = true;
      listBalanceRecord(this.queryParams).then(response => {
        this.balanceRecordList = response.rows;
        this.totalBalanceRecord = response.total;
        this.loading = false;
      })
    },
  }
}
</script>


