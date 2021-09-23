<template>
  <el-dialog width="" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
    <el-form :rules="gameRules" ref="gameForm" :model="game" label-width="80px" size="small"
             style="width: 90%; margin-left: 20px;">
      <!--<el-row>-->
      <!--<el-col :span="24">-->
      <!--<el-form-item label="游戏类型" prop="type">-->
      <!--<el-select v-model="game.type" placeholder="请选择游戏类型" style="display: block;">-->
      <!--<el-option v-for="item in gameTypeOptions" :key="item.key" :label="item.display_name"-->
      <!--:value="item.key">-->
      <!--</el-option>-->
      <!--</el-select>-->
      <!--</el-form-item>-->
      <!--</el-col>-->
      <!--</el-row>-->
      <el-row>
        <el-col :span="24">
          <el-form-item label="游戏信息" prop="info">
            <el-input v-model="game.info"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="游戏名称" prop="name">
            <el-input v-model="game.name"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="游戏图片" prop="imageUrl">
            <single-image-upload :name="decodeURI(game.imageUrl)" :image="decodeURI(game.imageUrl)" v-model="game.imageUrl"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="音频地址" prop="resourceUrl">
            <single-audio-upload :name="decodeURI(game.resourceUrl)" :audio="decodeURI(game.resourceUrl)" v-model="game.resourceUrl"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="游戏状态" prop="status">
            <el-select v-model="game.status" placeholder="请选择游戏状态" style="display: block;">
              <el-option v-for="item in statusOptions" :key="item.key" :label="item.display_name"
                         :value="item.key">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item>
            <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">{{$t('table.confirm')}}
            </el-button>
            <el-button v-else type="primary" @click="updateData">{{$t('table.confirm')}}</el-button>
            <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </el-dialog>
</template>

<script>
  import { addGame, editGame, deleteGameBatch } from '@/api/game'
  import { trueFalseOptions, statusOptions, gameTypeOptions } from '@/utils/constant'
  import SingleImageUpload from '../../../components/Upload/singleImageUpload'
  import SingleAudioUpload from '../../../components/Upload/singleAudioUpload'
  import { validateAudio, validateImage } from '@/utils/validate'

  const gameObject = {
    id: null,
    name: '',
    info: '',
    imageUrl: '',
    resourceUrl: '',
    status: 1,
    level: 1,
    type: 1
  }

  export default {
    components: {
      SingleAudioUpload,
      SingleImageUpload
    },
    props: {
      selectOptions: {
        type: Array,
        default: []
      }
    },
    name: 'voiceOperations',
    data() {
      return {
        gameTypeOptions: gameTypeOptions,
        trueFalseOptions: trueFalseOptions,
        statusOptions: statusOptions,
        game: Object.assign({}, gameObject),
        dialogStatus: '',
        dialogFormVisible: false,
        textMap: {
          create: '新增',
          update: '编辑'
        },
        gameRules: {
          name: [{ required: true, message: '游戏名称为必填项', trigger: 'change' }],
          info: [{ required: true, message: '游戏信息为必填项', trigger: 'change' }],
          imageUrl: [{ required: true, notNullMessage: '游戏图片为必填项', TypeErrorMessage: '请上传正确的图片文件', trigger: 'change', validator: validateImage }],
          resourceUrl: [{ required: true, notNullMessage: '音频地址为必填项', TypeErrorMessage: '请上传正确的音频文件', trigger: 'change', validator: validateAudio }]
        }
      }
    },
    methods: {
      resetForm() {
        this.game = {
          id: null,
          name: '',
          info: '',
          imageUrl: '',
          resourceUrl: '',
          status: 1,
          level: 1,
          type: 1
        }
      },
      handleCreate() {
        this.resetForm()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        if (!this.isAssigned) {
          this.$nextTick(() => {
            this.$refs['gameForm'].clearValidate()
          })
        }
      },
      handleUpdate(row) {
        this.game = Object.assign({}, row) // copy obj
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['gameForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['gameForm'].validate((valid) => {
          if (valid) {
            this.game.imageUrl = encodeURI(this.game.imageUrl)
            this.game.resourceUrl = encodeURI(this.game.resourceUrl)
            addGame(this.game).then(() => {
              this.dialogFormVisible = false
              this.$emit('refresh-list')
              this.$notify({
                title: '成功',
                message: '创建成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      updateData() {
        this.$refs['gameForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.game) // 对象拷贝
            tempData.imageUrl = encodeURI(decodeURI(tempData.imageUrl))
            tempData.resourceUrl = encodeURI(decodeURI(tempData.resourceUrl))
            editGame(tempData).then(() => {
              this.dialogFormVisible = false
              this.$emit('refresh-list')
              this.$notify({
                title: '成功',
                message: '更新成功',
                type: 'success',
                duration: 2000
              })
            })
          }
        })
      },
      handleDelete() {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          if (this.selectOptions.length > 0) {
            const ids = []
            this.selectOptions.forEach(row => {
              ids.push(row.id)
            })
            deleteGameBatch(ids).then((res) => {
              if (res.data.code === 0) {
                this.$emit('refresh-list')
                this.$notify({
                  title: '成功',
                  message: '删除成功',
                  type: 'success',
                  duration: 2000
                })
              } else {
                this.$message.error(res.data.msg)
              }
            })
          } else {
            this.$message.error('请选择列表项!')
          }
        }).catch(() => {

        })
      }
    }
  }

</script>
