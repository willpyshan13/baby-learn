<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-form class="form-container" :rules="rules" :model="menu"
               label-width="85px" style='margin-left:20px;' size="mini">
        <el-button type="primary" size="mini" @click="handleAddButton('menu')">{{$t('table.add')}}</el-button>
        <el-table :data="buttons" ref="menuButtonTable" v-loading.body="tableLoading"  height="650"
                  element-loading-text="Loading" size="mini" border fit highlight-current-row
                  default-expand-all>

          <el-table-column type="expand">
            <template slot-scope="props">
              <el-button type="primary" size="mini" @click="handleAddButton('sub', props.$index)">{{$t('table.add')}}</el-button>

              <el-table :data="props.row.subButtons" width="" border fit highlight-current-row>

                <el-table-column align="center" label="序号" width="80">
                  <template slot-scope="prop">
                    <span>{{ prop.$index + 1 }}</span>
                  </template>
                </el-table-column>

                <el-table-column label="菜单名称" width="220" align="center" prop="number">
                  <template slot-scope="prop">
                    {{prop.row.name }}
                  </template>
                </el-table-column>

                <el-table-column label="菜单类型" width="180" align="center" prop="number">
                  <template slot-scope="prop">
                    {{ [ wechatMenuButtonType, prop.row.type ] | keyValueFilter }}
                  </template>
                </el-table-column>

                <el-table-column label="网页链接" :show-overflow-tooltip="true" width="" align="center" prop="number">
                  <template slot-scope="prop">
                    {{prop.row.url }}
                  </template>
                </el-table-column>

                <el-table-column label="页面路径" :show-overflow-tooltip="true" width="" align="center" prop="number">
                  <template slot-scope="prop">
                    {{prop.row.pagePath }}
                  </template>
                </el-table-column>

                <el-table-column label="小程序ID" width="95" align="center" prop="number">
                  <template slot-scope="prop">
                    {{prop.row.appId }}
                  </template>
                </el-table-column>

                <el-table-column align="center" label="操作" width="220">
                  <template slot-scope="prop">
                    <el-button type="danger" size="mini" @click="handleDeleteSubButtons(prop.$index, props.row.subButtons)">{{$t('table.delete')}}</el-button>
                  </template>
                </el-table-column>
              </el-table>
            </template>
          </el-table-column>

          <el-table-column label="菜单名称" width="200" align="center" prop="number">
            <template slot-scope="scope">
              <span>{{ scope.row.name }}</span>
            </template>
          </el-table-column>

          <el-table-column label="菜单类型" width="180" align="center" prop="number">
            <template slot-scope="scope">
              {{ [ wechatMenuButtonType, scope.row.type ] | keyValueFilter }}
            </template>
          </el-table-column>

          <el-table-column label="网页链接" width="300" align="center" prop="number">
            <template slot-scope="scope">
              <span>{{ scope.row.url }}</span>
            </template>

          </el-table-column>

          <el-table-column label="页面路径" width="" align="center" prop="number">
            <template slot-scope="scope">
              {{scope.row.pagePath }}
            </template>
          </el-table-column>

          <el-table-column label="小程序ID" width="95" align="center" prop="number">
            <template slot-scope="scope">
              {{scope.row.appId }}
            </template>
          </el-table-column>

          <el-table-column align="center" label="操作" width="180">
            <template slot-scope="scope">
              <!-- @click="scope.row.edit=!scope.row.edit" -->
              <el-button type="primary" @click="handleEditButton('menu', scope.$index)" size="mini">{{$t('table.edit')}}</el-button>
              <el-button type="danger" @click="handleDeleteButton(scope.$index)" size="mini">{{$t('table.delete')}}</el-button>
            </template>
          </el-table-column>
        </el-table>

        <el-form-item>
          <el-button type="primary" @click="createData">{{$t('table.add')}}
          </el-button>
          <el-button >{{$t('table.cancel')}}</el-button>
        </el-form-item>
      </el-form>
    </div>


    <el-dialog width="" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
      <el-form :rules="rules" ref="menuForm" :model="button" label-width="90px"
               style='width:90%;margin-left:20px;' size="small">

        <el-form-item label="菜单名称" prop="name">
          <el-input v-model="button.name"/>
        </el-form-item>

        <el-form-item label="菜单类型" prop="type">
          <el-select clearable filterable class="filter-item" v-model="button.type" placeholder="请选择菜单类型" @change="handleMenuTypeChange">
            <el-option v-for="item in wechatMenuButtonType" :key="item.key" :label="item.display_name"
                       :value="item.key">
            </el-option>
          </el-select>

        </el-form-item>

        <el-form-item label="网页链接" prop="url">
          <el-input v-model="button.url"/>
        </el-form-item>

        <el-form-item v-if="miniProgramShow" label="页面路径" prop="pagePath">
          <el-input v-model="button.pagePath"/>
        </el-form-item>

        <el-form-item v-if="miniProgramShow" label="小程序ID" prop="appId">
          <el-input v-model="button.appId"/>
        </el-form-item>
        <el-form-item>
          <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
          <el-button v-if="dialogStatus=='create'" type="primary" @click="createButton">{{$t('table.confirm')}}</el-button>
          <el-button v-else type="primary" @click="updateButton">{{$t('table.confirm')}}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
  import { createMpMenu, getMpButtons } from '@/api/weixin'
  import { keyValueFilter, wechatMenuButtonType } from '@/utils/constant'

  export default {
    name: 'WxMpMenus',
    created() {
      this.fetchWechatMpMenu()
    },
    data() {
      const urlValidator = (rule, value, callback) => {
        if (this.button.type === 'view' || this.button.type === 'miniprogram') {
          if (value.trim() === '') {
            callback(new Error('网页链接为必填项'))
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      const typeValidator = (rule, value, callback) => {
        if (this.buttonType === 'sub') {
          if (value === null) {
            callback(new Error('菜单类型为必填项'))
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      return {
        wechatMenuButtonType: wechatMenuButtonType,
        miniProgramShow: false,
        buttonType: 'menu',
        arrayIndex: 0,
        menu: {},
        buttons: [],
        button: {
          appId: null,
          key: null,
          mediaId: null,
          name: '',
          pagePath: '',
          subButtons: [],
          edit: false,
          type: null,
          url: ''
        },
        originMenu: {},
        rules: {
          name: [{ required: true, message: '菜单名称为必填项', trigger: 'change' }],
          type: [{ required: false, trigger: 'change', validator: typeValidator }],
          url: [{ required: false, trigger: 'change', validator: urlValidator }]
        },

        textMap: {
          create: '新增',
          update: '编辑'
        },
        dialogStatus: '',
        dialogFormVisible: false,

        resourceList: null,
        resourceTotal: 0,
        tableLoading: true,
        resourceQuery: {
          name: null,
          type: null,
          pageNum: 1,
          pageSize: 10
        }
      }
    },
    filters: {
      keyValueFilter
    },
    methods: {
      fetchWechatMpMenu() {
        this.tableLoading = true
        getMpButtons().then(res => {
          this.buttons = res.data.menu.buttons
          this.tableLoading = false
        })
      },
      handleDeleteButton(index) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.buttons.splice(index, 1)
        }).catch(() => {

        })
      },
      handleMenuTypeChange(val) {
        if (val === 'miniprogram') {
          this.miniProgramShow = true
        } else {
          this.miniProgramShow = false
        }
      },
      createData() {
        const data = {}
        data.buttons = this.buttons
        createMpMenu(data).then(res => {
          if (res.data.success) {
            this.$notify({
              title: '成功',
              message: res.data.msg,
              type: 'success',
              duration: 2000
            })
            this.fetchWechatMpMenu()
          } else {
            this.$message.error(res.data.message)
          }
        })
      },
      handleDeleteSubButtons(index, rows) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          rows.splice(index, 1)
        }).catch(() => {

        })
      },
      handleEditButton(type, index) {
        this.buttonType = type
        this.arrayIndex = index

        this.button = Object.assign({}, this.buttons[index])
        this.dialogFormVisible = true
        this.dialogStatus = 'update'
        this.$nextTick(() => {
          this.$refs['menuForm'].clearValidate()
        })
      },
      updateButton() {
        console.log(this.button)
        this.$refs['menuForm'].validate((valid) => {
          if (valid) {
            if (this.buttonType === 'menu') {
              this.buttons[this.arrayIndex].appId = this.button.appId
              this.buttons[this.arrayIndex].key = this.button.key
              this.buttons[this.arrayIndex].mediaId = this.button.mediaId
              this.buttons[this.arrayIndex].name = this.button.name
              this.buttons[this.arrayIndex].pagePath = this.button.pagePath
              this.buttons[this.arrayIndex].subButtons = this.button.subButtons
              this.buttons[this.arrayIndex].type = this.button.type
              this.buttons[this.arrayIndex].url = this.button.url
            } else if (this.buttonType === 'sub') {
              this.buttons[this.arrayIndex].subButtons.appId = this.button.appId
              this.buttons[this.arrayIndex].subButtons.key = this.button.key
              this.buttons[this.arrayIndex].subButtons.mediaId = this.button.mediaId
              this.buttons[this.arrayIndex].subButtons.name = this.button.name
              this.buttons[this.arrayIndex].subButtons.pagePath = this.button.pagePath
              this.buttons[this.arrayIndex].subButtons.subButtons = this.button.subButtons
              this.buttons[this.arrayIndex].subButtons.type = this.button.type
              this.buttons[this.arrayIndex].subButtons.url = this.button.url
            }

            this.dialogFormVisible = false
            this.button = {
              appId: null,
              key: null,
              mediaId: null,
              name: '',
              pagePath: '',
              subButtons: [],
              edit: false,
              type: null,
              url: ''
            }
          }
        })
      },
      handleAddButton(type, index) {
        this.buttonType = type
        this.arrayIndex = index

        if (this.buttonType === 'menu') {
          if (this.buttons.length >= 3) {
            this.$message.error('菜单上限为3个')
            return
          }
        } else if (this.buttonType === 'sub') {
          if (this.buttons[this.arrayIndex].subButtons.length >= 5) {
            this.$message.error('按钮上限为5个')
            return
          }
        }

        this.dialogFormVisible = true
        this.dialogStatus = 'create'

        this.$nextTick(() => {
          this.$refs['menuForm'].clearValidate()
        })
      },
      createButton() {
        this.$refs['menuForm'].validate((valid) => {
          if (valid) {
            if (this.buttonType === 'menu') {
              this.buttons.push(this.button)
            } else if (this.buttonType === 'sub') {
              this.buttons[this.arrayIndex].subButtons.push(this.button)
            }

            this.dialogFormVisible = false
            this.button = {
              appId: null,
              key: null,
              mediaId: null,
              name: '',
              pagePath: '',
              subButtons: [],
              edit: false,
              type: null,
              url: ''
            }
          }
        })
      }
    }
  }
</script>
