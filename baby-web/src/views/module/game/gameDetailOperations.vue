<template>
  <el-dialog width="30%" :title="textMap[dialogStatus]" :visible.sync="gameDetailDialogForm">
    <el-form :rules="gameDetailRules" ref="gameDetailForm" :model="gameDetail" label-width="80px" size="small"
             style="width: 85%; margin-left: 20px;">
      <el-row>
        <el-col :span="24">
          <el-form-item label="类型" prop="type">
            <el-select v-model="gameDetail.gameType" placeholder="请选择游戏类型" style="display: block;">
              <el-option v-for="item in gameTypeOptions" :key="item.key" :label="item.display_name"
                         :value="item.key">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="正确答案" prop="rightAnswer">
            <el-select v-model="gameDetail.rightAnswer" placeholder="请选择" style="display: block;">
              <el-option v-for="item in trueFalseOptions" :key="item.key" :label="item.display_name"
                         :value="item.key">
              </el-option>
            </el-select>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="单词" prop="word">
            <el-input v-model="gameDetail.word"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="播报内容" prop="ttsText">
            <single-audio-upload :name="decodeURI(gameDetail.ttsText)" :audio="decodeURI(gameDetail.ttsText)" v-model="gameDetail.ttsText"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item label="图片" prop="imageUrl">
            <single-image-upload :name="decodeURI(gameDetail.imageUrl)" :image="decodeURI(gameDetail.imageUrl)" v-model="gameDetail.imageUrl"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row v-bind:hidden="true">
        <el-col :span="24">
          <el-form-item label="游戏资源" prop="resourceUrl">
            <single-video-upload v-model="gameDetail.resourceUrl"/>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row>
        <el-col :span="24">
          <el-form-item>
            <el-button v-if="dialogStatus=='create'" type="primary" @click="gameDetailCreate">
              {{$t('table.confirm')}}
            </el-button>
            <el-button v-else type="primary" @click="gameDetailUpdate">{{$t('table.confirm')}}</el-button>
            <el-button @click="gameDetailDialogForm = false">{{$t('table.cancel')}}</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </el-dialog>
</template>

<script>
  import { addGameDetail, updateGameDetail, deleteGameDetail } from '@/api/game'
  import { trueFalseOptions, statusOptions, gameTypeOptions } from '@/utils/constant'
  import SingleImageUpload from '../../../components/Upload/singleImageUpload'
  import { validateAudio, validateImage } from '@/utils/validate'
  import SingleAudioUpload from '../../../components/Upload/singleAudioUpload'

  const gameDetailObject = {
    id: null,
    gameId: null,
    imageUrl: '',
    resourceUrl: '',
    ttsText: '',
    word: '',
    gameType: 0,
    rightAnswer: false
  }

  export default {
    name: 'gameDetailOperations',
    components: {
      SingleAudioUpload,
      SingleImageUpload },
    props: {
      gameId: {
        type: Number,
        default: null
      }
    },
    computed: {
      getGameId() {
        return this.gameId
      }
    },
    data() {
      return {
        gameTypeOptions: gameTypeOptions,
        trueFalseOptions: trueFalseOptions,
        statusOptions: statusOptions,
        gameDetail: Object.assign({}, gameDetailObject),
        textMap: {
          create: '新增',
          update: '编辑'
        },
        dialogStatus: '',
        gameDetailDialogForm: false,
        gameDetailRules: {
          word: [{ required: true, message: '单词为必填项', trigger: 'change' }],
          ttsText: [{ required: false, notNullMessage: '播报内容为必填项', TypeErrorMessage: '请上传正确类型的文件', trigger: 'change', validator: validateAudio }],
          imageUrl: [{ required: true, notNullMessage: '图片为必填项', TypeErrorMessage: '请上传正确类型的文件', trigger: 'change', validator: validateImage }]
        }
      }
    },
    methods: {
      resetGameDetailForm() {
        this.gameDetail = {
          id: null,
          gameId: null,
          imageUrl: '',
          resourceUrl: '',
          ttsText: '',
          word: '',
          gameType: 0,
          rightAnswer: false
        }
      },
      handleGameDetailCreate() {
        this.resetGameDetailForm()
        this.gameDetailDialogForm = true
        this.dialogStatus = 'create'
        this.$nextTick(() => {
          this.$refs['gameDetailForm'].clearValidate()
        })
      },
      gameDetailCreate() {
        this.$refs['gameDetailForm'].validate((valid) => {
          if (valid) {
            this.gameDetail.gameId = this.getGameId
            this.gameDetail.imageUrl = encodeURI(this.gameDetail.imageUrl)
            this.gameDetail.resourceUrl = encodeURI(this.gameDetail.resourceUrl)
            addGameDetail(this.gameDetail).then((res) => {
              if (res.data.code === 0) {
                this.gameDetailDialogForm = false
                this.$emit('refresh-list')
                this.$notify({
                  title: '成功',
                  message: '创建成功',
                  type: 'success',
                  duration: 2000
                })
              } else {
                this.$message.error(res.data.msg)
              }
            })
          }
        })
      },
      handleGameDetailUpdate(row) {
        this.gameDetailDialogForm = true
        this.gameDetail = Object.assign({}, row) // copy obj
        this.dialogStatus = 'update'
        this.$nextTick(() => {
          this.$refs['gameDetailForm'].clearValidate()
        })
      },
      gameDetailUpdate() {
        this.$refs['gameDetailForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.gameDetail) // 对象拷贝
            tempData.imageUrl = encodeURI(decodeURI(tempData.imageUrl))
            tempData.resourceUrl = encodeURI(decodeURI(tempData.resourceUrl))
            updateGameDetail(tempData).then(() => {
              this.gameDetailDialogForm = false
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
      handleGameDetailDelete(row) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteGameDetail(row.id).then(() => {
            this.$emit('refresh-list')
            this.$notify({
              title: '成功',
              message: '删除成功',
              type: 'success',
              duration: 2000
            })
          })
        }).catch(() => {

        })
      }
    }
  }
</script>
