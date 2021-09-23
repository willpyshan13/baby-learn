<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.searchKeys')" v-model="listQuery.searchKeys" size="mini"></el-input>
      <el-select clearable filterable v-model="listQuery.kindergartenId" placeholder="请选择幼儿园" size="mini" @change="handleFilter" class="filter-item">
        <el-option v-for="item in kindergartenList" :key="item.id" :label="item.name" :value="item.id"/>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">
        {{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['personOperations'].handleCreate()"
                 type="primary" size="mini"
                 icon="el-icon-edit">{{$t('table.add')}}
      </el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading" height="650" element-loading-text="Loading" size="mini" border
              fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column v-if="false" label="头像" width="95" align="center">
        <template slot-scope="scope">
          <img v-if="scope.row.photoUrl !== ''" v-bind:src="scope.row.photoUrl + '?v=' + (new Date()).valueOf()"
               v-bind:width="35" height="35"/>
          <img v-if="scope.row.photoUrl === '' && scope.row.gender === false" v-bind:src="girlPhoto" v-bind:width="35"
               height="35"/>
          <img v-if="scope.row.photoUrl === '' && scope.row.gender === true" v-bind:src="boyPhoto" v-bind:width="35"
               height="35"/>
        </template>
      </el-table-column>
      <el-table-column v-if="false" label="姓名" width="120" align="center">
        <template slot-scope="scope">
          {{scope.row.userName}}
        </template>
      </el-table-column>
      <el-table-column label="昵称" width="140" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.nickName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="手机号码" width="140" align="center">
        <template slot-scope="scope">
          {{scope.row.mobileNumber}}
        </template>
      </el-table-column>
      <!-- 格式转换 -->
      <el-table-column class-name="status-col" label="性别" width="110" align="center">
        <template slot-scope="scope">
          {{scope.row.gender | personGenderFilter }}
        </template>
      </el-table-column>
      <el-table-column label="所在班级" width="" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.className.length > 0" v-for="v in scope.row.className.split('、')">
            <router-link :to="'/school/classes/'+ encodeURI(v)">
              {{v}} |
            </router-link>
          </span>
        </template>
      </el-table-column>
      <el-table-column label="简介" width="" align="center">
        <template slot-scope="scope">
          {{scope.row.personDesc}}
        </template>
      </el-table-column>
      <el-table-column label="积分" width="80" align="center">
        <template slot-scope="scope">
          <el-row style="margin-left: 10px;">
            <el-col :span="2" align="left">
              <router-link :to="'/integral/detail/'+scope.row.personId">
                <el-button size="mini" round align="right">
                  <span v-if="scope.row.candyPoints == null">0</span>
                  <span v-else> {{ scope.row.candyPoints }}</span>
                </el-button>
              </router-link>
            </el-col>
          </el-row>
        </template>
      </el-table-column>
      <el-table-column label="绑定微信" width="95" align="center">
        <template slot-scope="scope">
          <span v-if="scope.row.wxMpId !== ''">是</span>
          <span v-else>否</span>
        </template>
      </el-table-column>
      <el-table-column label="注册时间" width="150" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.createTime }}</span>
        </template>
      </el-table-column>
      <el-table-column align="center" :label="$t('table.actions')" width="300"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="$refs['personOperations'].handleUpdate(scope.row)">
            {{$t('table.edit')}}
          </el-button>
          <el-button size="mini" type="danger" @click="$refs['personOperations'].handleDelete(scope.row)">
            {{$t('table.delete')}}
          </el-button>

          <router-link :to="'/user/course/'+ scope.row.personId">
            <el-button class="filter-item" type="success" size="mini">{{$t('table.boughtCourse')}}</el-button>
          </router-link>
        </template>
      </el-table-column>
    </el-table>

    <div class="pagination-container">
      <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="listQuery.page"
        :page-sizes="[10, 20, 30, 40]"
        :page-size="listQuery.limit"
        layout="total, sizes, prev, pager, next, jumper"
        :total="total">
      </el-pagination>
    </div>

    <person-operations ref="personOperations" @refresh-list="getList"/>

  </div>

</template>

<script>
  import { personList } from '@/api/person'
  import { personGenderFilter } from '@/utils/constant'
  import { getKindergartenInfoList } from '@/api/kindergartenInfo'

  import girlPhoto from '@/assets/header/girl.png'
  import boyPhoto from '@/assets/header/boy.png'
  import PersonOperations from './personOperations'

  export default {
    name: 'Person',
    components: {
      PersonOperations
    },
    data() {
      return {
        kindergartenList: [],
        girlPhoto: girlPhoto,
        boyPhoto: boyPhoto,
        list: null,
        listLoading: true,
        total: null,
        listQuery: {
          name: null,
          page: 1,
          limit: 10,
          searchKeys: '',
          kindergartenId: null
        },
        personId: null,
        activeName: 'personList'
      }
    },
    filters: {
      personGenderFilter
    },
    created() {
      this.fetchKindergartenList()
      this.getList()
    },
    methods: {
      fetchKindergartenList() {
        getKindergartenInfoList({ enablePage: false }).then(res => {
          if (res.data.result === 0) {
            this.kindergartenList = res.data.body
          } else {
            this.$message.error(res.data.message)
          }
        })
      },
      getList() {
        this.listLoading = true
        personList(this.listQuery).then(response => {
          this.list = response.data.personList.list
          this.total = response.data.personList.totalCount
          this.listLoading = false
        })
      },
      handleFilter() {
        this.listQuery.page = 1
        this.getList()
      },
      handleSizeChange(val) {
        this.listQuery.limit = val
        this.getList()
      },
      handleCurrentChange(val) {
        this.listQuery.page = val
        this.getList()
      }
    }
  }
</script>
