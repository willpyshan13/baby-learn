<template>
  <el-dialog width="" :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible">
    <el-form :rules="rules" ref="dataForm" :model="person" label-width="90px"
             style='width:90%;margin-left:20px;' size="mini">

      <el-form-item label="姓名" prop="userName">
        <el-input v-model="person.userName"/>
      </el-form-item>
      <el-form-item label="昵称" prop="nickName">
        <el-input v-model="person.nickName"/>
      </el-form-item>
      <el-form-item label="手机号码" prop="mobileNumber">
        <el-input v-model="person.mobileNumber"/>
      </el-form-item>
      <el-form-item label="性别" prop="gender">
        <el-select clearable class="filter-item" v-model="person.gender" placeholder="请选择">
          <el-option v-for="item in typeOptions" :key="item.key" :label="item.display_name"
                     :value="item.key">
          </el-option>
        </el-select>
      </el-form-item>
      <el-form-item label="简介">
        <el-input type="textarea" v-model="person.personDesc"></el-input>
      </el-form-item>

      <el-form-item label="积分" prop="accumulatedPoints">
        <el-input v-model="person.accumulatedPoints"/>
      </el-form-item>
    </el-form>
    <div slot="footer" class="dialog-footer">
      <el-button @click="dialogFormVisible = false">{{$t('table.cancel')}}</el-button>
      <el-button v-if="dialogStatus=='create'" type="primary" @click="createData">{{$t('table.confirm')}}</el-button>
      <el-button v-else type="primary" @click="updateData">{{$t('table.confirm')}}</el-button>
    </div>
  </el-dialog>
</template>
<script>
  import { addPerson, editPerson, deletePerson } from '@/api/person'
  import { typeOptions } from '@/utils/constant'

  const personObject = {
    personId: null,
    birthday: '',
    gender: true,
    mobileNumber: '',
    nickName: '',
    password: 0,
    personDesc: '',
    photoUrl: '',
    status: 1,
    userName: '',
    candyPoints: 0,
    cakePoints: 0
  }
  export default {
    name: 'personOperations',
    data() {
      return {
        typeOptions: typeOptions,
        person: Object.assign({}, personObject),
        dialogFormVisible: false,
        dialogStatus: '',
        textMap: {
          update: '编辑',
          create: '新增'
        },
        rules: {
          userName: [{ required: true, message: '姓名不能为空', trigger: 'change' }],
          mobileNumber: [{ required: true, message: '手机号码不能为空', trigger: 'change' }]
        }
      }
    },
    methods: {
      resetTemp() {
        this.person = {
          personId: null,
          birthday: '',
          gender: true,
          mobileNumber: '',
          nickName: '',
          password: '',
          personDesc: '',
          photoUrl: '',
          status: 1,
          userName: ''
        }
      },
      handleCreate() {
        this.resetTemp()
        this.dialogStatus = 'create'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      createData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            addPerson(this.person).then((res) => {
              if (res.data.code === 0) {
                this.$emit('refresh-list')
                this.dialogFormVisible = false
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
      handleUpdate(row) {
        this.person = Object.assign({}, row) // copy obj
        if (row.type === 2) {
          this.parentMenuSelectVisible = false
        } else {
          this.parentMenuSelectVisible = true
        }
        this.dialogStatus = 'update'
        this.dialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['dataForm'].clearValidate()
        })
      },
      updateData() {
        this.$refs['dataForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.person) // 对象拷贝
            editPerson(tempData).then((res) => {
              if (res.data.code === 0) {
                this.$emit('refresh-list')
                this.dialogFormVisible = false
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
          }
        })
      },
      handleDelete(row) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deletePerson(row.personId).then((res) => {
            if (res.data.code === 0) {
              this.$emit('refresh-list')
              this.dialogFormVisible = false
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
        }).catch(() => {

        })
      }
    }
  }
</script>
