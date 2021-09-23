<template>
  <div class="app-container el-tabs--border-card" style="margin: 20px">
    <div class="filter-container">
      <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                :placeholder="$t('table.name')" v-model="listQuery.courseName" size="mini"></el-input>
      <el-select clearable filterable class="filter-item" v-model="listQuery.stageId" placeholder="请选择课阶" size="mini">
        <el-option v-for="item in courseStageList" :key="item.id" :label="item.stageName"
                   :value="item.id">
        </el-option>
      </el-select>
      <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">
        {{$t('table.search')}}
      </el-button>
      <el-button class="filter-item" style="margin-left: 10px;" @click="$refs['courseOperations'].handleCreate()"
                 type="primary" size="mini"
                 icon="el-icon-edit">{{$t('table.add')}}
      </el-button>
    </div>

    <el-table :data="list" v-loading.body="listLoading"
              height="650" element-loading-text="Loading" size="mini" border fit highlight-current-row>
      <el-table-column align="center" label='ID' width="95">
        <template slot-scope="scope">
          {{scope.$index + 1}}
        </template>
      </el-table-column>
      <el-table-column label="课程名称" width="150" align="center">
        <template slot-scope="scope">
          {{scope.row.courseName}}
        </template>
      </el-table-column>
      <el-table-column label="课程阶段" width="70" align="center">
        <template slot-scope="scope">
          <span>{{scope.row.stageName}}</span>
        </template>
      </el-table-column>
      <el-table-column label="课程状态" width="70" align="center">
        <template slot-scope="scope">
          {{ [statusOptions, scope.row.status] | keyValueFilter}}
        </template>
      </el-table-column>
      <el-table-column label="课程类型" width="70" align="center">
        <template slot-scope="scope">
          {{ [courseType, scope.row.type] | keyValueFilter}}
        </template>
      </el-table-column>
      <el-table-column label="所属周" width="70" align="center">
        <template slot-scope="scope">
          {{scope.row.weeks}}
        </template>
      </el-table-column>
      <el-table-column label="课程序号" width="70" align="center">
        <template slot-scope="scope">
          {{scope.row.serialNumber}}
        </template>
      </el-table-column>
      <el-table-column label="观看权限" width="70" align="center">
        <template slot-scope="scope">
          {{ [viewingAuthority, scope.row.viewingAuthority] | keyValueFilter}}
        </template>
      </el-table-column><!--
          <el-table-column label="内容类别" width="70" align="center" show-overflow-tooltip="">
            <template slot-scope="scope">
              {{scope.row.category}}
            </template>
          </el-table-column>-->
      <el-table-column label="视频地址" align="center" show-overflow-tooltip>
        <template slot-scope="scope">
          {{ decodeURI(scope.row.videoUrl) }}
        </template>
      </el-table-column>
      <el-table-column label="图片地址" align="center" width="120">
        <template slot-scope="scope">
          <img @click="openWindow(scope.row.imageUrl, '12',1280,720)" class="user-avatar" v-if="scope.row.imageUrl"
               :src="scope.row.imageUrl+'?imageView2/1/w/40/h/40'">
        </template>
      </el-table-column>
      <el-table-column label="tts文本" width="110" align="center">
        <template slot-scope="scope">
          {{scope.row.ttsText}}
        </template>
      </el-table-column>
      <el-table-column label="单词" width="110" align="center">
        <template slot-scope="scope">
          {{scope.row.word}}
        </template>
      </el-table-column>
      <el-table-column align="center" :label="$t('table.actions')" width="320"
                       class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="$refs['courseOperations'].handleUpdate(scope.row)">
            {{$t('table.edit')}}
          </el-button>
          <el-button size="mini" type="danger" @click="$refs['courseOperations'].handleDelete(scope.row)">
            {{$t('table.delete')}}
          </el-button>

          <router-link :to="'/school/course/single/choice/'+ scope.row.id">
            <el-button class="filter-item" type="success" size="mini">{{$t('table.playGames')}}</el-button>
          </router-link>

          <router-link :to="'/school/course/word/read/'+ scope.row.id">
            <el-button class="filter-item" type="info" size="mini">{{$t('table.voiceGame')}}</el-button>
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

    <course-operations ref="courseOperations" @refresh-list="getList"/>

  </div>
</template>

<script>
  import { courseList, courseStageList } from '@/api/course'
  import { statusOptions, courseType, viewingAuthority, keyValueFilter } from '@/utils/constant'
  import CourseOperations from './courseOperations'
  import { openWindow } from '@/utils/openWindow'

  export default {
    name: 'Course',
    components: {
      CourseOperations
    },
    data() {
      return {
        statusOptions: statusOptions,
        courseType: courseType,
        viewingAuthority: viewingAuthority,
        activeName: 'courseInfoList',
        courseStageList: [],
        courseId: null,
        list: null,
        listLoading: true,
        total: null,
        listQuery: {
          courseName: null,
          stageId: null,
          page: 1,
          limit: 10
        }
      }
    },
    filters: {
      keyValueFilter
    },
    created() {
      this.getList()
      this.getCourseStageList()
    },
    methods: {
      openWindow,
      getCourseStageList() {
        courseStageList().then((response) => {
          if (response.data.code === 0) {
            this.courseStageList = response.data.courseStageList.list
          } else {
            this.$message.error(response.data.msg)
          }
        })
      },
      getList() {
        this.listLoading = true
        courseList(this.listQuery).then(response => {
          this.list = response.data.courseList.list
          this.total = response.data.courseList.totalCount
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
