<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="买家名字" prop="buyerName">
        <el-input
          v-model="queryParams.buyerName"
          placeholder="请输入买家名字"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="买家电话" prop="buyerPhone">
        <el-input
          v-model="queryParams.buyerPhone"
          placeholder="请输入买家电话"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="买家桌号" prop="buyerAddress">
        <el-input
          v-model="queryParams.buyerAddress"
          placeholder="请输入买家桌号"
          clearable
          size="small"
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="订单状态" prop="orderStatus">
        <el-select v-model="queryParams.orderStatus" placeholder="请选择订单状态">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="支付状态" prop="payStatus">
        <el-select v-model="queryParams.payStatus" placeholder="请选择支付状态" clearable size="small">
          <el-option
            v-for="item in patStatus"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['buyer:order:edit']"
        >查看</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['buyer:order:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="orderList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="订单号" align="center" prop="orderId" />
      <el-table-column label="买家名字" align="center" prop="buyerName" />
      <el-table-column label="买家电话" align="center" prop="buyerPhone" />
      <el-table-column label="买家桌号" align="center" prop="buyerAddress" />
      <el-table-column label="订单总金额" align="center" prop="orderAmount" />
      <el-table-column label="订单状态" align="center" prop="orderStatus" >
        <tempalte slot-scope="scope">
          <el-tag v-if="scope.row.orderStatus==0" type="danger">新订单，未支付</el-tag>
          <el-tag v-if="scope.row.orderStatus==1" type="success">新订单，已支付</el-tag>
          <el-tag v-if="scope.row.orderStatus==2" type="info">已取消</el-tag>
          <el-tag v-if="scope.row.orderStatus==3" type="warning">待评价</el-tag>
          <el-tag v-if="scope.row.orderStatus==4" type="success">已完成</el-tag>
        </tempalte>
      </el-table-column>
      <el-table-column label="支付状态" align="center" prop="payStatus" >
        <tempalte slot-scope="scope">
          <el-tag  v-if="scope.row.payStatus==0" type="danger">未支付</el-tag >
          <el-tag  v-if="scope.row.payStatus==1" type="success">已支付</el-tag >
        </tempalte>
      </el-table-column>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['buyer:order:edit']"
          >查看</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改订单对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="买家名字" prop="buyerName">
          <el-input v-model="form.buyerName" placeholder="请输入买家名字" />
        </el-form-item>
        <el-form-item label="买家电话" prop="buyerPhone">
          <el-input v-model="form.buyerPhone" placeholder="请输入买家电话" />
        </el-form-item>
        <el-form-item label="买家桌号" prop="buyerAddress">
          <el-input v-model="form.buyerAddress" placeholder="请输入买家桌号" />
        </el-form-item>
        <el-form-item label="总金额" prop="orderAmount">
          <el-input v-model="form.orderAmount" placeholder="请输入订单总金额" />
        </el-form-item>
        <el-form-item label="订单状态" prop="orderStatus">
          <el-radio-group v-model="form.orderStatus">
            <el-radio :label="0" >新订单，未支付</el-radio>
            <el-radio :label="1" >新订单，已支付</el-radio>
            <el-radio :label="2" >已取消</el-radio>
            <el-radio :label="3" >已评价</el-radio>
            <el-radio :label="4" >已完成</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-divider content-position="center">订单商品信息</el-divider>
<!--        <el-row :gutter="10" class="mb8">-->
<!--          <el-col :span="1.5">-->
<!--            <el-button type="primary" icon="el-icon-plus" size="mini" @click="handleAddOrderDetail">添加</el-button>-->
<!--          </el-col>-->
<!--          <el-col :span="1.5">-->
<!--            <el-button type="danger" icon="el-icon-delete" size="mini" @click="handleDeleteOrderDetail">删除</el-button>-->
<!--          </el-col>-->
<!--        </el-row>-->
        <el-table :data="orderDetailList" :row-class-name="rowOrderDetailIndex" @selection-change="handleOrderDetailSelectionChange" ref="orderDetail">
          <el-table-column type="selection" width="50" align="center" />
          <el-table-column label="序号" align="center" prop="index" width="50"/>
          <el-table-column label="商品名称" prop="productName">
            <template slot-scope="scope">
              <el-input v-model="scope.row.productName" placeholder="请输入商品名称" />
            </template>
          </el-table-column>
          <el-table-column label="价格" prop="productPrice">
            <template slot-scope="scope">
              <el-input v-model="scope.row.productPrice" placeholder="请输入当前价格,单位分" />
            </template>
          </el-table-column>
          <el-table-column label="数量" prop="productQuantity">
            <template slot-scope="scope">
              <el-input v-model="scope.row.productQuantity" placeholder="请输入数量" />
            </template>
          </el-table-column>
          <el-table-column label="小图" prop="productIcon">
            <template slot-scope="scope">
              <img :src="scope.row.productIcon" width="100" height="100">
            </template>
          </el-table-column>
        </el-table>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listOrder, getOrder, delOrder, addOrder, updateOrder, exportOrder } from "@/api/merchant/order";

export default {
  name: "Order",
  components: {
  },
  data() {
    return {
      patStatus:[{
        value: '0',
        label: '未支付'
      },{
        value: '1',
        label: '已支付'
      }],
      options: [{
        value: '0',
        label: '新订单，未支付'
      }, {
        value: '1',
        label: '新订单，已支付'
      },{
        value: '2',
        label: '已取消'
      }, {
        value: '3',
        label: '待评价'
      }, {
        value: '4',
        label: '已完成'
      }],
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 子表选中数据
      checkedOrderDetail: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 订单表格数据
      orderList: [],
      // 订单商品表格数据
      orderDetailList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        buyerName: null,
        buyerPhone: null,
        buyerAddress: null,
        buyerOpenid: null,
        orderAmount: null,
        orderStatus: null,
        payStatus: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        buyerName: [
          { required: true, message: "买家名字不能为空", trigger: "blur" }
        ],
        buyerAddress: [
          { required: true, message: "买家桌号不能为空", trigger: "blur" }
        ],
        orderAmount: [
          { required: true, message: "订单总金额不能为空", trigger: "blur" }
        ],
        orderStatus: [
          { required: true, message: "订单状态不能为空", trigger: "blur" }
        ],
        payStatus: [
          { required: true, message: "支付状态不能为空", trigger: "blur" }
        ],
        createTime: [
          { required: true, message: "创建时间不能为空", trigger: "blur" }
        ],
        updateTime: [
          { required: true, message: "修改时间不能为空", trigger: "blur" }
        ]
      }
    };
  },
  created() {
    this.getList();

  },
  methods: {
    /** 查询订单列表 */
    getList() {
      this.loading = true;
      listOrder(this.queryParams).then(response => {
        this.orderList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        orderId: null,
        buyerName: null,
        buyerPhone: null,
        buyerAddress: null,
        orderAmount: null,
        orderStatus: 0,
        payStatus: 0,
        createTime: null,
        updateTime: null
      };
      this.orderDetailList = [];
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.orderId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加订单";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const orderId = row.orderId || this.ids
      getOrder(orderId).then(response => {
        this.form = response.data;
        this.orderDetailList = response.data.orderDetailList;
        this.open = true;
        this.title = "修改订单";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.orderDetailList = this.orderDetailList;
          if (this.form.orderId != null) {
            updateOrder(this.form).then(response => {
              this.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addOrder(this.form).then(response => {
              this.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
	 /** 订单商品序号 */
    rowOrderDetailIndex({ row, rowIndex }) {
      row.index = rowIndex + 1;
    },
    /** 订单商品添加按钮操作 */
    handleAddOrderDetail() {
      let obj = {};
      obj.productId = "";
      obj.productName = "";
      obj.productPrice = "";
      obj.productQuantity = "";
      obj.productIcon = "";
      this.orderDetailList.push(obj);
    },
    /** 订单商品删除按钮操作 */
    handleDeleteOrderDetail() {
      if (this.checkedOrderDetail.length == 0) {
        this.$alert("请先选择要删除的订单商品数据", "提示", { confirmButtonText: "确定", });
      } else {
        this.orderDetailList.splice(this.checkedOrderDetail[0].index - 1, 1);
      }
    },
    /** 单选框选中数据 */
    handleOrderDetailSelectionChange(selection) {
      if (selection.length > 1) {
        this.$refs.orderDetail.clearSelection();
        this.$refs.orderDetail.toggleRowSelection(selection.pop());
      } else {
        this.checkedOrderDetail = selection;
      }
    },
    /** 导出按钮操作 */
    handleExport() {
      const queryParams = this.queryParams;
      this.$confirm('是否确认导出所有订单数据项?', "警告", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          type: "warning"
        }).then(function() {
          return exportOrder(queryParams);
        }).then(response => {
          this.download(response.msg);
        })
    }
  }
};
</script>
