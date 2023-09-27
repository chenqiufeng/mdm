<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="数据源名称" prop="datasourceName">
        <el-input
          v-model="queryParams.datasourceName"
          placeholder="请输入数据源名称"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数据源" prop="datasource">
        <el-input
          v-model="queryParams.datasource"
          placeholder="请输入数据源"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数据源分组" prop="datasourceGroup">
        <el-input
          v-model="queryParams.datasourceGroup"
          placeholder="请输入数据源分组"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="数据库名" prop="databaseName">
        <el-input
          v-model="queryParams.databaseName"
          placeholder="请输入数据库名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="用户名" prop="jdbcUsername">
        <el-input
          v-model="queryParams.jdbcUsername"
          placeholder="请输入用户名"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="密码" prop="jdbcPassword">
        <el-input
          v-model="queryParams.jdbcPassword"
          placeholder="请输入密码"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="jdbc驱动类" prop="jdbcDriverClass">
        <el-input
          v-model="queryParams.jdbcDriverClass"
          placeholder="请输入jdbc驱动类"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="${comment}" prop="zkAdress">
        <el-input
          v-model="queryParams.zkAdress"
          placeholder="请输入${comment}"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="创建时间" prop="createDate">
        <el-date-picker clearable
          v-model="queryParams.createDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择创建时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="更新时间" prop="updateDate">
        <el-date-picker clearable
          v-model="queryParams.updateDate"
          type="date"
          value-format="yyyy-MM-dd"
          placeholder="请选择更新时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['dataintegration:datasource:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['dataintegration:datasource:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['dataintegration:datasource:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['dataintegration:datasource:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="datasourceList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="自增主键" align="center" prop="id" />
      <el-table-column label="数据源名称" align="center" prop="datasourceName" />
      <el-table-column label="数据源" align="center" prop="datasource" />
      <el-table-column label="数据源分组" align="center" prop="datasourceGroup" />
      <el-table-column label="数据库名" align="center" prop="databaseName" />
      <el-table-column label="用户名" align="center" prop="jdbcUsername" />
      <el-table-column label="密码" align="center" prop="jdbcPassword" />
      <el-table-column label="jdbc url" align="center" prop="jdbcUrl" />
      <el-table-column label="jdbc驱动类" align="center" prop="jdbcDriverClass" />
      <el-table-column label="${comment}" align="center" prop="zkAdress" />
      <el-table-column label="状态：0删除 1启用 2禁用" align="center" prop="status" />
      <el-table-column label="创建时间" align="center" prop="createDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="更新时间" align="center" prop="updateDate" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.updateDate, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="comments" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['dataintegration:datasource:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['dataintegration:datasource:remove']"
          >删除</el-button>
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

    <!-- 添加或修改jdbc数据源配置对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="数据源名称" prop="datasourceName">
          <el-input v-model="form.datasourceName" placeholder="请输入数据源名称" />
        </el-form-item>
        <el-form-item label="数据源" prop="datasource">
          <el-input v-model="form.datasource" placeholder="请输入数据源" />
        </el-form-item>
        <el-form-item label="数据源分组" prop="datasourceGroup">
          <el-input v-model="form.datasourceGroup" placeholder="请输入数据源分组" />
        </el-form-item>
        <el-form-item label="数据库名" prop="databaseName">
          <el-input v-model="form.databaseName" placeholder="请输入数据库名" />
        </el-form-item>
        <el-form-item label="用户名" prop="jdbcUsername">
          <el-input v-model="form.jdbcUsername" placeholder="请输入用户名" />
        </el-form-item>
        <el-form-item label="密码" prop="jdbcPassword">
          <el-input v-model="form.jdbcPassword" placeholder="请输入密码" />
        </el-form-item>
        <el-form-item label="jdbc url" prop="jdbcUrl">
          <el-input v-model="form.jdbcUrl" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="jdbc驱动类" prop="jdbcDriverClass">
          <el-input v-model="form.jdbcDriverClass" placeholder="请输入jdbc驱动类" />
        </el-form-item>
        <el-form-item label="${comment}" prop="zkAdress">
          <el-input v-model="form.zkAdress" placeholder="请输入${comment}" />
        </el-form-item>
        <el-form-item label="创建时间" prop="createDate">
          <el-date-picker clearable
            v-model="form.createDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择创建时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="更新时间" prop="updateDate">
          <el-date-picker clearable
            v-model="form.updateDate"
            type="date"
            value-format="yyyy-MM-dd"
            placeholder="请选择更新时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="备注" prop="comments">
          <el-input v-model="form.comments" type="textarea" placeholder="请输入内容" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listDatasource, getDatasource, delDatasource, addDatasource, updateDatasource } from "@/api/dataintegration/datasource";

export default {
  name: "Datasource",
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // jdbc数据源配置表格数据
      datasourceList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        datasourceName: null,
        datasource: null,
        datasourceGroup: null,
        databaseName: null,
        jdbcUsername: null,
        jdbcPassword: null,
        jdbcUrl: null,
        jdbcDriverClass: null,
        zkAdress: null,
        status: null,
        createDate: null,
        updateDate: null,
        comments: null
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        datasourceName: [
          { required: true, message: "数据源名称不能为空", trigger: "blur" }
        ],
        datasource: [
          { required: true, message: "数据源不能为空", trigger: "blur" }
        ],
        jdbcUrl: [
          { required: true, message: "jdbc url不能为空", trigger: "blur" }
        ],
        status: [
          { required: true, message: "状态：0删除 1启用 2禁用不能为空", trigger: "change" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询jdbc数据源配置列表 */
    getList() {
      this.loading = true;
      listDatasource(this.queryParams).then(response => {
        this.datasourceList = response.rows;
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
        id: null,
        datasourceName: null,
        datasource: null,
        datasourceGroup: null,
        databaseName: null,
        jdbcUsername: null,
        jdbcPassword: null,
        jdbcUrl: null,
        jdbcDriverClass: null,
        zkAdress: null,
        status: null,
        createBy: null,
        createDate: null,
        updateBy: null,
        updateDate: null,
        comments: null
      };
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
      this.ids = selection.map(item => item.id)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加jdbc数据源配置";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getDatasource(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改jdbc数据源配置";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateDatasource(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addDatasource(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除jdbc数据源配置编号为"' + ids + '"的数据项？').then(function() {
        return delDatasource(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('dataintegration/datasource/export', {
        ...this.queryParams
      }, `datasource_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
