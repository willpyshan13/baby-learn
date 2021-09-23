<template>
  <div class="app-container calendar-list-container">
    <el-row>

      <el-col :span="2"><div class="grid-content bg-purple"></div></el-col>
      <el-col :span="20">

        <el-form class="form-container" :rules="rules" ref="exchangeForm" :model="exchangeDetail"
                 label-width="85px" style='margin-left:20px;' size="mini">
          <!--<el-button class="filter-item" type="warning" size="small" @click="handleDraft">{{$t('table.draft')}}</el-button>-->

          <el-row :gutter="10">
            <el-col :span="6">
              <el-form-item label="用户:" prop="personId">
                <el-select :disabled="uneditable" clearable class="filter-item" v-model="exchangeDetail.personId" placeholder="请选择" value="">
                  <el-option v-for="item in personList" :key="item.personId" :label="item.mobileNumber"
                             :value="item.personId">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6" :offset="1">
              <el-form-item label="电话:" prop="phone">
                <el-input :disabled="uneditable" class="filter-item" placeholder="请输入联系电话" v-model="exchangeDetail.phone"/>
              </el-form-item>
            </el-col>
            <el-col :span="6" :offset="1">
              <el-form-item label="类型:" prop="integralType">
                <el-select :disabled="uneditable" clearable class="filter-item" v-model="exchangeDetail.integralType" placeholder="请选择" value="">
                  <el-option v-for="item in pointsType" :key="item.key" :label="item.display_name"
                             :value="item.key">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="12">
              <el-form-item label="地区:" prop="areaCode">
                  <city-select :uneditable="uneditable" v-model="exchangeDetail.areaCode" :size="'mini'" :code="exchangeDetail.areaCode"/>
              </el-form-item>
            </el-col>
            <el-col :span="6" :offset="2">
              <el-form-item style="" label="详细地址:" prop="address">
                <el-input :disabled="uneditable" placeholder="请输入详细地址" v-model="exchangeDetail.address"/>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="10">

            <el-col :span="6">
              <el-form-item label="商品:" prop="goodsId">
                <el-select :disabled="uneditable" v-model="exchangeDetail.goodsId" filterable placeholder="请选择商品" @change="handleGoodsSelected">
                  <el-option v-for="item in goodsList" :key="item.id" :label="item.name"
                             :value="item.id">
                  </el-option>
                </el-select>

                <!--<el-select clearable class="filter-item" v-model="exchangeDetail.goodsId" placeholder="请选择商品" value="">-->
                  <!--<el-option v-for="item in goodsList" :key="item.id" :label="item.name"-->
                             <!--:value="item.id">-->
                  <!--</el-option>-->
                <!--</el-select>-->
                <!--<el-input placeholder="请选择商品" v-model="exchangeDetail.goodsId" :disabled="true">-->
                  <!--<el-button slot="append" class="filter-item" type="warning" icon="el-icon-search" size="small" @click="handleDraft"><span>商品列表</span></el-button>-->
                <!--</el-input>-->
              </el-form-item>
            </el-col>
            <el-col :span="6" :offset="1">
              <el-form-item style="" label="数量:" prop="numbers">
                <el-input :disabled="uneditable" type="number" min="1" placeholder="请输入数量" v-model="exchangeDetail.numbers" @change="handleNumberChange"/>
              </el-form-item>
            </el-col>
            <el-col :span="6" :offset="1">
              <el-form-item style="" label="消耗积分:" prop="points">
                <el-input :disabled="uneditable" type="number" min="0" placeholder="消耗积分" v-model="exchangeDetail.points"/>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row :gutter="10">
            <el-col :span="6">
              <el-form-item label="状态:" prop="status">
                <el-select :disabled="uneditable" clearable class="filter-item" v-model="exchangeDetail.status" placeholder="请选择" value="">
                  <el-option v-for="item in expressStatus" :key="item.key" :label="item.display_name"
                             :value="item.key">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6" :offset="1">
              <el-form-item label="快递:" prop="express">
                <el-select :disabled="uneditable" clearable class="filter-item" v-model="exchangeDetail.express" placeholder="请选择" value="">
                  <el-option v-for="item in expressList" :key="item.key" :label="item.display_name"
                             :value="item.key">
                  </el-option>
                </el-select>
              </el-form-item>
            </el-col>
            <el-col :span="6" :offset="1">
              <el-form-item style="" label="单号:" prop="expressCode">
                <el-input :disabled="uneditable" type="number" placeholder="请输入数量" v-model="exchangeDetail.expressCode"/>
              </el-form-item>
            </el-col>

          </el-row>

          <el-row :gutter="10">
            <el-col :span="6">
              <el-form-item style="" label="发货时间:" prop="sendTime">
                <el-date-picker :disabled="uneditable"
                                v-model="exchangeDetail.sendTime"
                                type="datetime"
                                value-format="yyyy-MM-dd HH:mm:ss"
                                placeholder="选择日期时间">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="6" :offset="1">
              <el-form-item style="" label="收货时间:" prop="receiveTime">
                <el-date-picker
                  :disabled="uneditable"
                  v-model="exchangeDetail.receiveTime"
                  type="datetime"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  placeholder="选择日期时间">
                </el-date-picker>
              </el-form-item>
            </el-col>
            <el-col :span="6" :offset="1">
              <el-form-item style="" label="备注:" prop="remark">
                <el-input type="textarea" class="article-textarea" :rows="1" autosize placeholder="" v-model="exchangeDetail.remark"/>
              </el-form-item>
            </el-col>

          </el-row>

          <el-row>
            <el-col :span="8" :offset="16">
              <!-- 操作按钮 -->
              <el-form-item>
                <el-button v-if="!isEdit" type="primary" @click="createData">{{$t('table.push')}}
                </el-button>
                <el-button v-else type="primary" @click="createData">{{$t('table.confirm')}}</el-button>
                <el-button @click="handleCancel">{{$t('table.cancel')}}</el-button>
              </el-form-item>
            </el-col>
          </el-row>

        </el-form>

      </el-col>
      <el-col :span="2"><div class="grid-content bg-purple"></div></el-col>
    </el-row>
  </div>
</template>

<script>
  import { exchangeGoods, getExchangeDetailById } from '@/api/integral'
  import { getGoodsList } from '@/api/goods'
  import { expressStatus, pointsType, keyValueFilter } from '@/utils/constant'
  import CitySelect from '@/components/CitySelect/citySelect'
  import axios from 'axios'
  import { closeCurrentView } from '@/utils'
  import { personList } from '@/api/person'

  const exchangeDetailObject = {
    id: null,
    personId: null,
    goodsId: null,
    integralType: 2,
    numbers: 1,
    points: 1,
    areaCode: '',
    address: '',
    phone: '',
    express: null,
    expressCode: null,
    sendTime: null,
    status: 0,
    receiveTime: null,
    remark: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  }
  export default {
    components: { CitySelect },
    name: 'exchangeDetail',
    props: {
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    created() {
      this.fetchDataList()
      this.fetchPersonList()
      if (this.isEdit) {
        const detailId = this.$route.params && this.$route.params.id
        this.exchangeDetail.personId = this.$route.params && this.$route.params.pid
        this.fetchData(detailId)
      } else {
        const personId = this.$route.params && this.$route.params.pid
        this.exchangeDetail.personId = Number(personId)
      }
    },
    data() {
      const validateRequire = (rule, value, callback) => {
        if (this.exchangeDetail.status === 1) {
          if (value === '' || value == null) {
            callback(new Error(rule.notNullMessage))
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      const v = (rule, value, callback) => {
        if (this.exchangeDetail.status === 2) {
          if (value === '' || value == null) {
            callback(new Error(rule.notNullMessage))
          } else {
            callback()
          }
        } else {
          callback()
        }
      }
      return {
        expressJson: '../static/json/express.json',
        pointsType: pointsType,
        expressStatus: expressStatus,
        exchangeDetail: Object.assign({}, exchangeDetailObject),
        personList: [],
        expressList: [],
        goodsList: [],
        goodsMap: {},
        goodsPoints: 0,
        uneditable: false,
        rules: {
          personId: [{ required: true, message: '请选择用户', trigger: 'change' }],
          goodsId: [{ required: true, message: '请选择商品', trigger: 'change' }],
          integralType: [{ required: true, message: '请选择积分类型', trigger: 'change' }],
          areaCode: [{ required: true, message: '请选择地区', trigger: 'change' }],
          address: [{ required: true, message: '详细地址为必填项', trigger: 'change' }],
          phone: [{ required: true, message: '联系电话为必填项', trigger: 'change' }],
          express: [{ required: false, notNullMessage: '请选择快递', trigger: 'change', validator: validateRequire }],
          expressCode: [{ required: false, notNullMessage: '请输入快递单号', trigger: 'change', validator: validateRequire }],
          sendTime: [{ required: false, notNullMessage: '请选择发货时间', trigger: 'change', validator: validateRequire }],
          receiveTime: [{ required: false, notNullMessage: '请选择发货时间', trigger: 'change', validator: v }]
        }
      }
    },
    filter: {
      keyValueFilter
    },
    methods: {
      fetchData(id) {
        getExchangeDetailById(id).then(res => {
          if (res.data.code === 0) {
            this.exchangeDetail = res.data.exchangeDetail
            // 已经收货商品部分属性不可编辑
            this.uneditable = this.exchangeDetail.status === 2
          } else {
            this.$message.error(res.data.msg)
          }
          this.listLoading = false
        })
      },
      fetchPersonList() {
        personList().then(res => {
          if (res.data.code === 0) {
            this.personList = res.data.personList.list
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      handleCancel() {
        // 关闭当前标签页
        closeCurrentView(this.$store, this.$router, this.$route.path, '/integral/detail/' + this.exchangeDetail.personId)
      },
      createData() {
        this.$refs.exchangeForm.validate(valid => {
          if (valid) {
            exchangeGoods(this.exchangeDetail).then((res) => {
              if (res.data.code === 0) {
                this.handleCancel()
                this.$notify({
                  title: '成功',
                  message: '新增成功',
                  type: 'success',
                  duration: 2000
                })
              } else {
                this.$message.error(res.data.msg)
              }
            })
          } else {
            console.log('error submit!!')
            return false
          }
        })
      },
      fetchDataList() {
        getGoodsList(this.listQuery).then(res => {
          if (res.data.code === 0) {
            this.goodsList = res.data.goodsList.list
            this.goodsList.forEach((row) => {
              this.goodsMap[row.id] = row
            })
          } else {
            this.$message.error(res.data.msg)
          }
        })

        const that = this
        axios.get(this.expressJson).then(function(response) {
          if (response.status === 200) {
            that.expressList = response.data
          }
        })
      },
      handleGoodsSelected(val) {
        this.goodsPoints = this.exchangeDetail.numbers * this.goodsMap[val].points
        this.exchangeDetail.points = this.goodsPoints
      },
      handleNumberChange(val) {
        if (val <= 0) {
          this.$message.error('数量必须大于0')
          this.exchangeDetail.numbers = 1
        } else {
          this.exchangeDetail.points = this.exchangeDetail.numbers * this.goodsPoints
        }
      }
    }
  }

</script>

<style>
  .main{
    text-align: center; /*让div内部文字居中*/
    background-color: #fff;
    border-radius: 20px;
    width: 100%;
    margin: auto;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
  }
  .grid-content {
    border-radius: 4px;
    min-height: 36px;
  }
  .el-select {
    display: block;
  }
  .el-date-editor.el-input, .el-date-editor.el-input__inner {
    width: 100%;
  }
</style>
