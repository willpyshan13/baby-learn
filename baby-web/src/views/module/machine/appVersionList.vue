<template>
  <div class="app-container calendar-list-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item" :placeholder="$t('table.keyWord')" v-model="listQuery.mixedField" size="mini"/>

      <el-select clearable filterable class="filter-item" v-model="listQuery.model" placeholder="机器型号" size="mini" @visible-change="handleMachineModelSelectVisibleChange"
                 @change="handleMachineModelChange">
        <el-option v-for="item in machineModelList" :key="item.id" :label="item.model" :value="item.id"></el-option>
      </el-select>

      <el-select clearable filterable class="filter-item" v-model="listQuery.appId" placeholder="App" size="mini" @visible-change="handleAppInfoSelectVisibleChange"
                 @change="handleAppInfoChange">
        <el-option v-for="item in appInfoList" :key="item.id" :label="item.name" :value="item.id"></el-option>
      </el-select>

      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}</el-button>
      <el-button v-if="listQuery.model" class="filter-item" type="success" icon="el-icon-search" size="mini" @click="handleAddAppVersion">{{$t('table.add')}}</el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading" height="650" element-loading-text="Loading"
              size="mini" border fit highlight-current-row>

      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="App" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.appName}}
        </template>
      </el-table-column>
      <el-table-column label="版本名称" width="200" align="center">
        <template slot-scope="scope">
          {{scope.row.name}}
        </template>
      </el-table-column>
      <el-table-column label="版本号" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.version}}
        </template>
      </el-table-column>
      <el-table-column label="版本状态" width="95" align="center">
        <template slot-scope="scope">
          {{ [ appVersionStatus, scope.row.status ] | keyValueFilter }}
        </template>
      </el-table-column>
      <el-table-column label="版本描述" width="" align="center" show-overflow-tooltip>
        <template slot-scope="scope">
          {{scope.row.description}}
        </template>
      </el-table-column>
      <el-table-column label="备注" width="" align="center" show-overflow-tooltip>
        <template slot-scope="scope">
          {{scope.row.remark}}
        </template>
      </el-table-column>
      <el-table-column label="创建时间" width="140" align="center">
        <template slot-scope="scope">
          {{scope.row.createTime}}
        </template>
      </el-table-column>

      <el-table-column label="更新时间" width="140" align="center">
        <template slot-scope="scope">
          {{scope.row.updateTime}}
        </template>
      </el-table-column>

      <el-table-column align="center" :label="$t('table.actions')" width="300"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="primary" @click="handleEditAppVersion(scope.row)">{{$t('table.update')}}</el-button>
          <el-button size="mini" type="danger" @click="handleDeleteAppVersion(scope.row)">{{$t('table.delete')}}</el-button>
          <el-button size="mini" type="success" @click="handleOpenWhitelist(scope.row)">白名单</el-button>
          <el-button size="mini" type="info" @click="handleOpenBlacklist(scope.row)">黑名单</el-button>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="listQuery.pageNum"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="listQuery.pageSize"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>

    <el-dialog width="" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form class="form-container" :rules="rules" ref="appVersionForm" :model="appVersion"
               label-width="85px" style='margin-left:20px;' size="mini">
        <el-form-item label="版本名称:" prop="name">
          <el-input class="filter-item" v-model="appVersion.name"/>
        </el-form-item>
        <el-form-item label="版本号:" prop="version">
          <el-input class="filter-item" v-model="appVersion.version"/>
        </el-form-item>
        <el-form-item label="型号:" prop="model">
          <el-select disabled filterable class="filter-item" v-model="appVersion.model" placeholder="请选择" size="mini">
            <el-option v-for="item in machineModelList" :key="item.id" :label="item.model" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="App名称:" prop="appId">
          <el-select clearable filterable class="filter-item" v-model="appVersion.appId" placeholder="请选择" size="mini">
            <el-option v-for="item in appInfoList" :key="item.id" :label="item.name" :value="item.id"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="版本状态:" prop="status">
          <el-select filterable class="filter-item" v-model="appVersion.status" placeholder="请选择" size="mini">
            <el-option v-for="item in appVersionStatus" :key="item.key" :label="item.display_name" :value="item.key"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="升级地址:" prop="url">
          <el-input class="filter-item" v-model="appVersion.url"/>
        </el-form-item>
        <el-form-item label="版本描述:" prop="description">
          <el-input class="filter-item" type="textarea" :rows="3" v-model="appVersion.description"/>
        </el-form-item>
        <el-form-item label="备注:" prop="remark">
          <el-input class="filter-item" v-model="appVersion.remark"/>
        </el-form-item>
        <el-form-item>
          <el-button v-if="dialogStatus==='create'" type="primary" @click="createData">{{$t('table.confirm')}}
          </el-button>
          <el-button v-else type="primary" @click="updateData">{{$t('table.confirm')}}</el-button>
          <el-button @click="handleCancel">{{$t('table.cancel')}}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog> <!-- 版本信息添加弹窗 end -->

    <el-dialog width="" :title="listMap[listStatus]" :visible.sync="listTableVisible">
      <el-tabs v-model="activeName" @tab-click="handleMachineInfoListTabClick" >
        <el-tab-pane label="已选列表" name="inList">
          <div class="filter-container">
            <el-button @click="handleDeleteUpgradeList" class="filter-item" type="danger" icon="el-icon-edit" size="mini">{{$t('table.delete')}}</el-button>
          </div>
          <el-table :data="machineInfoList" v-loading="inUpgradeListLoading"  height="500"
                    element-loading-text="Loading" size="mini" border fit highlight-current-row
                    @selection-change="handleDeleteMachineInfoSelectionChange">
            <el-table-column
              type="selection"
              width="55">
            </el-table-column>
            <el-table-column align="center" label='ID' width="80">
              <template slot-scope="scope">
                {{scope.$index + 1}}
              </template>
            </el-table-column>
            <el-table-column label="序列号" width="140" align="center">
              <template slot-scope="scope">
                {{ scope.row.serialNumber }}
              </template>
            </el-table-column>
            <el-table-column label="型号" width="" align="center">
              <template slot-scope="scope">
                {{ scope.row.currentModel }}
              </template>
            </el-table-column>
            <el-table-column label="当前版本号" width="100" align="center">
              <template slot-scope="scope">
                {{ scope.row.currentVersion }}
              </template>
            </el-table-column>
            <el-table-column label="当前App编码" width="" align="center">
              <template slot-scope="scope">
                {{ scope.row.currentAppCode }}
              </template>
            </el-table-column>
            <el-table-column label="当前用户手机号" width="110" align="center">
              <template slot-scope="scope">
                {{ scope.row.mobilePhone }}
              </template>
            </el-table-column>
          </el-table>
          <div class="pagination-container">
            <el-pagination
              @size-change="handleMachineInfoListSizeChange"
              @current-change="handleMachineInfoListCurrentChange"
              :current-page="machineInfoQuery.pageNum"
              :page-sizes="[10, 20, 30, 40]"
              :page-size="machineInfoQuery.pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="machineInfoTotal">
            </el-pagination>
          </div>
        </el-tab-pane>
        <el-tab-pane label="未选列表" name="outList" >
          <div class="filter-container">
            <el-button @click="handleAddUpgradeList" class="filter-item" type="primary" icon="el-icon-edit" size="mini">{{$t('table.add')}}</el-button>
          </div>
          <el-table :data="machineInfoList" v-loading="outUpgradeListLoading" height="500"
                    element-loading-text="Loading" size="mini" border fit highlight-current-row
                    @selection-change="handleAddMachineInfoSelectionChange">
            <el-table-column
              type="selection"
              width="55">
            </el-table-column>
            <el-table-column align="center" label='ID' width="80">
              <template slot-scope="scope">
                {{scope.$index + 1}}
              </template>
            </el-table-column>
            <el-table-column label="序列号" width="140" align="center">
              <template slot-scope="scope">
                {{ scope.row.serialNumber }}
              </template>
            </el-table-column>
            <el-table-column label="型号" width="" align="center">
              <template slot-scope="scope">
                {{ scope.row.currentModel }}
              </template>
            </el-table-column>
            <el-table-column label="当前版本号" width="100" align="center">
              <template slot-scope="scope">
                {{ scope.row.currentVersion }}
              </template>
            </el-table-column>
            <el-table-column label="当前App编码" width="" align="center">
              <template slot-scope="scope">
                {{ scope.row.currentAppCode }}
              </template>
            </el-table-column>
            <el-table-column label="当前用户手机号" width="110" align="center">
              <template slot-scope="scope">
                {{ scope.row.mobilePhone }}
              </template>
            </el-table-column>
          </el-table>
          <div class="pagination-container">
            <el-pagination
              @size-change="handleMachineInfoListSizeChange"
              @current-change="handleMachineInfoListCurrentChange"
              :current-page="machineInfoQuery.pageNum"
              :page-sizes="[10, 20, 30, 40]"
              :page-size="machineInfoQuery.pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="machineInfoTotal">
            </el-pagination>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-dialog>

  </div>
</template>

<script>
  import { getAppVersionList, addAppVersion, updateAppVersions, deleteAppVersion, getMachineModelList, getAppInfoList,
    getInUpgradeList, getOutMachineInfoList, batchSaveUpgradeList, batchDeleteUpgradeList } from '@/api/app'
  import { appVersionStatus, keyValueFilter } from '@/utils/constant'

  const appVersionObject = {
    id: null,
    name: '',
    version: '',
    appId: null,
    model: null,
    url: '',
    description: '',
    remark: '',
    status: 2
  }
  export default {
    name: 'appVersion',
    data() {
      return {
        // 黑白名单 start
        listMap: {
          white: '白名单',
          black: '黑名单'
        },
        listStatus: '',
        listTableVisible: false,
        machineInfoList: [],
        machineInfoTotal: 0,
        activeName: 'inList',
        machineInfoQuery: {
          appVersionId: null,
          upgradeable: null,
          pageNum: 1,
          pageSize: 10
        },
        appId: null,
        appVersionId: null,
        inUpgradeListLoading: false,
        outUpgradeListLoading: false,
        deleteUpgradeArray: [],
        addUpgradeArray: [],
        // 黑白名单 end
        appVersionStatus: appVersionStatus,
        textMap: {
          create: '新增',
          update: '编辑'
        },
        dialogStatus: '',
        dialogFormVisible: false,
        appVersion: Object.assign({}, appVersionObject),
        list: null,
        listLoading: false,
        total: null,
        listQuery: {
          model: null,
          pageNum: 1,
          pageSize: 10
        },
        machineModelList: [],
        machineModelId: null,
        appInfoList: [],
        rules: {
          name: [{ required: true, message: '版本名称为必填项', trigger: 'change' }],
          version: [{ required: true, message: '版本号为必填项', trigger: 'change' }],
          url: [{ required: true, message: '升级地址为必填项', trigger: 'change' }],
          appId: [{ required: true, message: 'App为必选项', trigger: 'change' }]
        }
      }
    },
    filters: {
      keyValueFilter
    },
    created() {
      this.getAppInfoList()
      // this.getMachineModelList()
      this.getAppVersionList()
    },
    methods: {
      handleMachineModelSelectVisibleChange(status) {
        if (status) {
          this.getMachineModelList()
        }
      },
      handleMachineModelChange(val) {
        this.listQuery.model = val
        this.machineModelId = val
        this.getAppVersionList()
      },
      handleAppInfoSelectVisibleChange(status) {
        if (status) {
          this.getAppInfoList()
        }
      },
      handleAppInfoChange(val) {
        this.listQuery.appId = val
        this.getAppVersionList()
      },
      getAppInfoList() {
        getAppInfoList({ pageNum: 1, pageSize: 999 }).then(res => {
          if (res.data.success) {
            this.appInfoList = res.data.body.list
          } else {
            this.$message.error(res.data.message)
          }
        })
      },
      getMachineModelList() {
        getMachineModelList({ pageNum: 1, pageSize: 999 }).then(res => {
          if (res.data.success) {
            this.machineModelList = res.data.body.list
          } else {
            this.$message.error(res.data.message)
          }
        })
      },
      getAppVersionList() {
        this.listLoading = true
        getAppVersionList(this.listQuery).then(res => {
          if (res.data.success) {
            const items = res.data.body.list
            this.list = items.map(v => {
              this.appInfoList.forEach(row => {
                if (v.appId === row.id) {
                  this.$set(v, 'appName', row.name)
                }
              })
              return v
            })
            this.total = res.data.body.total
            this.listLoading = false
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      handleFilter() {
        this.listQuery.pageNum = 1
        this.getAppVersionList()
      },
      handleSizeChange(val) {
        this.listQuery.pageSize = val
        this.getAppVersionList()
      },
      handleCurrentChange(val) {
        this.listQuery.pageNum = val
        this.getAppVersionList()
      },
      handleAddAppVersion() {
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.resetData()
        this.appVersion.model = this.machineModelId
      },
      handleEditAppVersion(row) {
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.resetData()
        this.appVersion = Object.assign({}, row)
      },
      resetData() {
        this.appVersion = {
          id: null,
          name: '',
          version: '',
          appId: null,
          model: null,
          url: '',
          description: '',
          remark: '',
          status: 2
        }
        this.$nextTick(() => {
          this.$refs['appVersionForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['appVersionForm'].validate((valid) => {
          if (valid) {
            addAppVersion(this.appVersion).then(res => {
              if (res.data.success) {
                this.dialogFormVisible = false
                this.getAppVersionList()
                this.$notify({
                  title: '成功',
                  message: res.data.message,
                  type: 'success',
                  duration: 2000
                })
              } else {
                this.$message.error(res.data.message)
              }
            })
          }
        })
      },
      updateData() {
        this.$refs['appVersionForm'].validate((valid) => {
          if (valid) {
            updateAppVersions(this.appVersion).then(res => {
              if (res.data.success) {
                this.dialogFormVisible = false
                this.getAppVersionList()
                this.$notify({
                  title: '成功',
                  message: res.data.message,
                  type: 'success',
                  duration: 2000
                })
              } else {
                this.$message.error(res.data.message)
              }
            })
          }
        })
      },
      handleCancel() {
        this.dialogFormVisible = false
      },
      handleDeleteAppVersion(row) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteAppVersion(row.id).then((res) => {
            if (res.data.success) {
              this.getAppVersionList()
              this.$notify({
                title: '成功',
                message: res.data.message,
                type: 'success',
                duration: 2000
              })
            } else {
              this.$message.error(res.data.msg)
            }
          })
        }).catch(() => {

        })
      },
      // 黑白名单操作 start
      handleOpenWhitelist(row) {
        this.listTableVisible = true
        this.listStatus = 'white'
        this.activeName = 'inList'
        this.appVersionId = row.id
        this.appId = row.appId
        this.fetchInMachineInfoList()
      },
      handleOpenBlacklist(row) {
        this.listTableVisible = true
        this.listStatus = 'black'
        this.activeName = 'inList'
        this.appVersionId = row.id
        this.appId = row.appId
        this.fetchInMachineInfoList()
      },
      handleAddMachineInfoSelectionChange(arr) {
        this.addUpgradeArray = arr
      },
      handleDeleteMachineInfoSelectionChange(arr) {
        this.deleteUpgradeArray = arr
      },
      handleAddUpgradeList() {
        const addUpgradeIds = []
        this.addUpgradeArray.forEach(row => {
          addUpgradeIds.push(row.id + '')
        })
        const data = {}
        data.appVersionId = this.appVersionId
        data.appId = this.appId
        data.list = addUpgradeIds
        if (this.listStatus === 'white') {
          data.upgradeable = 1
        } else if (this.listStatus === 'black') {
          data.upgradeable = 0
        }
        batchSaveUpgradeList(data).then(res => {
          if (res.data.success) {
            this.fetchInMachineInfoList()
            this.activeName = 'inList'
            this.$notify({
              title: '成功',
              message: res.data.message,
              type: 'success',
              duration: 2000
            })
          } else {
            this.$message.error(res.data.message)
          }
        })
      },
      handleDeleteUpgradeList() {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          const deleteUpgradeIds = []
          this.deleteUpgradeArray.forEach(row => {
            deleteUpgradeIds.push(row.upgradeInfoId + '')
          })
          const data = {}
          data.list = deleteUpgradeIds
          data.status = -1
          batchDeleteUpgradeList(data).then(res => {
            if (res.data.success) {
              this.fetchInMachineInfoList()
              this.activeName = 'inList'
              this.$notify({
                title: '成功',
                message: res.data.message,
                type: 'success',
                duration: 2000
              })
            } else {
              this.$message.error(res.data.message)
            }
          })
        }).catch(() => {

        })
      },
      handleMachineInfoListTabClick(tabs, event) {
        const tabName = event.target.getAttribute('id')
        this.machineInfoQuery.appVersionId = null
        this.machineInfoQuery.upgradeable = null
        this.machineInfoQuery.pageNum = 1
        this.machineInfoQuery.pageSize = 10
        this.machineModelList = []
        if (tabName === 'tab-inList') {
          this.fetchInMachineInfoList()
          this.activeName = 'inList'
        } else if (tabName === 'tab-outList') {
          this.fetchOutMachineInfoList()
          this.activeName = 'outList'
        }
      },
      // 已选机器信息名单
      fetchInMachineInfoList() {
        this.machineInfoQuery.appVersionId = this.appVersionId
        this.inUpgradeListLoading = true
        if (this.listStatus === 'white') {
          this.machineInfoQuery.upgradeable = 1
        } else if (this.listStatus === 'black') {
          this.machineInfoQuery.upgradeable = 0
        }
        getInUpgradeList(this.machineInfoQuery).then(res => {
          if (res.data.success) {
            this.machineInfoList = res.data.body.list
            this.machineInfoTotal = res.data.body.total
          } else {
            this.$message.error(res.data.message)
          }
          this.inUpgradeListLoading = false
        })
      },
      // 未选机器信息名单
      fetchOutMachineInfoList() {
        this.machineInfoQuery.appVersionId = this.appVersionId
        this.outUpgradeListLoading = true
        getOutMachineInfoList(this.machineInfoQuery).then(res => {
          if (res.data.success) {
            this.machineInfoList = res.data.body.list
            this.machineInfoTotal = res.data.body.total
          } else {
            this.$message.error(res.data.message)
          }
          this.outUpgradeListLoading = false
        })
      },
      handleMachineInfoListSizeChange(val) {
        this.machineInfoQuery.pageSize = val
        if (this.activeName === 'inList') {
          this.fetchInMachineInfoList()
        } else if (this.activeName === 'outList') {
          this.fetchOutMachineInfoList()
        }
      },
      handleMachineInfoListCurrentChange(val) {
        this.machineInfoQuery.pageNum = val
        if (this.activeName === 'inList') {
          this.fetchInMachineInfoList()
        } else if (this.activeName === 'outList') {
          this.fetchOutMachineInfoList()
        }
      }
      // 黑白名单操作 end
    }
  }
</script>
