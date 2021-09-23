<template>
  <div class="app-container calendar-list-container">
    <el-row>
      <el-col :span="20">
        <el-form class="form-container" :rules="rules" ref="goodsForm" :model="goodsDetail"
                 label-width="85px" style='margin-left:20px;' size="mini">
          <el-row :gutter="10">
            <el-col :span="6">
              <el-form-item label="商品名称:" prop="personId">
                <el-input class="filter-item" placeholder="请输入商品名称" v-model="goodsDetail.name"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item label="价格:" prop="price">
                <el-input type="number" min="0" class="filter-item" placeholder="请输入价格" v-model="goodsDetail.price"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item label="积分:" prop="points">
                <el-input type="number" min="0" class="filter-item" placeholder="请输入积分" v-model="goodsDetail.points"/>

              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item label="商品图片:" prop="imageUrl">
                <single-image-upload :name="goodsDetail.imageUrl" :image="goodsDetail.imageUrl" v-model="goodsDetail.imageUrl"/>
              </el-form-item>
            </el-col>
          </el-row>
          <el-row>
            <el-col :span="6">
              <el-form-item label="卡片图片:" prop="cardImageUrl">
                <single-image-upload :name="decodeURI(goodsDetail.cardImageUrl)" :image="decodeURI(goodsDetail.cardImageUrl)" v-model="goodsDetail.cardImageUrl"/>
              </el-form-item>
            </el-col>
          </el-row>

          <el-row>
            <el-col :span="8">
              <!-- 操作按钮 -->
              <el-form-item>
                <el-button v-if="!isEdit" type="primary" @click="createData">{{$t('table.confirm')}}
                </el-button>
                <el-button v-else type="primary" @click="updateData">{{$t('table.confirm')}}</el-button>
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
  import { addGoods, updateGoods, getGoodsById } from '@/api/goods'
  import SingleImageUpload from '@/components/Upload/singleImageUpload'
  import { closeCurrentView } from '@/utils'

  const goodsDetailObject = {
    id: null,
    name: '',
    price: 0,
    virtual: 0,
    points: 0,
    imageUrl: '',
    cardImageUrl: ''
  }
  export default {
    components: { SingleImageUpload },
    name: 'goodsDetail',
    props: {
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    created() {
      if (this.isEdit) {
        const id = this.$route.params && this.$route.params.id
        this.fetchData(id)
      } else {
        this.goodsDetail = Object.assign({}, goodsDetailObject)
      }
    },
    data() {
      return {
        goodsDetail: {},
        rules: {

        }
      }
    },
    filter: {
    },
    methods: {
      fetchData(id) {
        getGoodsById(id).then((res) => {
          this.goodsDetail = res.data.goods
        })
      },
      createData() {
        this.$refs.goodsForm.validate(valid => {
          if (valid) {
            this.goodsDetail.imageUrl = encodeURI(this.goodsDetail.imageUrl)
            this.goodsDetail.cardImageUrl = encodeURI(this.goodsDetail.cardImageUrl)
            addGoods(this.goodsDetail).then((res) => {
              if (res.data.code === 0) {
                // 关闭当前标签页
                closeCurrentView(this.$store, this.$router, this.$route.path, '/goods/list')
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
      updateData() {
        this.$refs['goodsForm'].validate(valid => {
          if (valid) {
            const tempData = Object.assign({}, this.goodsDetail) // 对象拷贝
            tempData.imageUrl = encodeURI(decodeURI(tempData.imageUrl))
            tempData.cardImageUrl = encodeURI(decodeURI(tempData.cardImageUrl))
            updateGoods(tempData).then((res) => {
              if (res.data.code === 0) {
                // 关闭当前标签页
                closeCurrentView(this.$store, this.$router, this.$route.path, '/goods/list')
                this.$notify({
                  title: '成功',
                  message: '更新成功',
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
      handleCancel() {
        // 关闭当前标签页
        closeCurrentView(this.$store, this.$router, this.$route.path, '/goods/list')
      }
    }
  }

</script>

<style>
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
