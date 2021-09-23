<template>
  <div class="app-container calendar-list-container">
    <el-row>
      <el-col>
        <el-card class="box-card" style="margin-bottom: 20px;">
          <div slot="header" class="clearfix">
            <span>基础信息</span>
          </div>
          <div class="text item">
            <el-form :rules="rules" ref="courseStageFrom" :model="courseStage" label-width="90px"
                     style='width:90%;margin-left:20px;margin-top: 20px;' size="mini">
              <el-row>
                <el-col :span="6">
                  <el-form-item label="标题" prop="stageName">
                    <el-input v-model="courseStage.stageName"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <el-form-item label="阶段" prop="level">
                    <el-input type="number" v-model="courseStage.level"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <el-form-item label="课时" prop="spendTime">
                    <el-input v-model="courseStage.spendTime"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <el-form-item label="课程数量" prop="courseCount">
                    <el-input v-model="courseStage.courseCount"></el-input>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="6">
                  <el-form-item label="原价" prop="originPrice">
                    <el-input v-model="courseStage.originPrice"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <el-form-item label="优惠价" prop="preferentialPrice">
                    <el-input v-model="courseStage.preferentialPrice"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <el-form-item label="已售数量" prop="soldCount">
                    <el-input v-model="courseStage.soldCount"></el-input>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <el-form-item label="教师" prop="teacherId">
                    <el-select clearable filterable class="filter-item" v-model="courseStage.teacherId" placeholder="请选择">
                      <el-option v-for="item in teacherList" :key="item.teacherId" :label="item.nickName"
                                 :value="item.teacherId">
                      </el-option>
                    </el-select>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-row>
                <el-col :span="12">
                  <el-form-item label="封面图片" prop="imgUrl">
                    <single-image-upload :name="decodeURI(courseStage.imgUrl)" :image="decodeURI(courseStage.imgUrl)"
                                         v-model="courseStage.imgUrl"/>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <el-form-item label="是否出售" prop="status">
                    <el-switch
                      v-model="courseStage.selling"
                      active-color="#13ce66"
                      :active-value="1"
                      :inactive-value="0">
                    </el-switch>
                  </el-form-item>
                </el-col>
                <el-col :span="6">
                  <el-form-item label="体验课阶" prop="status">
                    <el-switch @change="handleDefaultStageChange"
                               v-model="courseStage.type"
                               active-color="#13ce66"
                               :active-value="0"
                               :inactive-value="1">
                    </el-switch>
                  </el-form-item>
                </el-col>
              </el-row>
              <el-form-item label="描述" prop="name">
                <el-input type="textarea" v-model="courseStage.description" rows="1"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button @click="handleCancelEdit">{{$t('table.cancel')}}</el-button>
                <el-button v-if="!isEdit" type="primary" @click="createData">{{$t('table.confirm')}}</el-button>
                <el-button v-else type="primary" @click="updateData">{{$t('table.confirm')}}</el-button>
              </el-form-item>
            </el-form>
          </div>
        </el-card>
      </el-col>
    </el-row>
    <el-row>
      <el-col>
        <el-card class="box-card" style="margin-bottom: 20px;">
          <div slot="header" class="clearfix">
            <span>单元信息</span>
          </div>
          <div class="text item">
            <div class="filter-container">
              <el-button type="primary" @click="handleCreateStageGroup" size="mini">{{$t('table.add')}}</el-button>
            </div>
            <el-table :data="courseStageGroup" :show-header="true" element-loading-text="Loading" size="mini" border fit highlight-current-row
              @expand-change="handleExpandUnitInfoColumns" :row-key="handleExpandUnitColumnsRowKey" :expand-row-keys="expandUnitColumnsRowKeys">

              <el-table-column type="expand">
                <template slot-scope="props">

                  <el-card class="box-card">
                    <div slot="header" class="clearfix">
                      <span>{{ unitName }} 周信息</span>
                    </div>
                    <div class="text item">
                      <div class="filter-container">
                        <el-button type="primary" @click="handleCreateUnitWeek" size="mini">{{$t('table.add')}}</el-button>
                      </div>
                      <el-table :data="courseWeekList" v-loading.body="courseWeekTableListLoading" element-loading-text="Loading" size="mini" border fit highlight-current-row>
                        <el-table-column align="center" label='ID' width="95">
                          <template slot-scope="scope">
                            {{scope.$index + 1}}
                          </template>
                        </el-table-column>
                        <el-table-column label="周名称" width="" align="center">
                          <template slot-scope="scope">
                            {{scope.row.name}}
                          </template>
                        </el-table-column>
                        <el-table-column label="课程数量" width="" align="center">
                          <template slot-scope="scope">
                            <!--<el-button disabled size="mini">{{scope.row.courseNumber}}</el-button>-->
                            <el-button @click="handleViewWeekCourse(scope.row.id)" size="mini"><span>查看课程</span></el-button>
                          </template>
                        </el-table-column>
                        <el-table-column label="主题资源" width="" align="center">
                          <template slot-scope="scope">
                            <el-button type="primary" size="mini" @click="handleViewWeekResources(scope.row.id)">{{$t('table.detail')}}</el-button>
                          </template>
                        </el-table-column>
                        <el-table-column align="center" :label="$t('table.actions')" width="200" class-name="small-padding fixed-width">
                          <template slot-scope="scope">
                            <el-button type="primary" size="mini" @click="handleUpdateUnitWeek(scope.row)">{{$t('table.edit')}}</el-button>
                            <el-button type="danger" size="mini" @click="handleDeleteUnitWeek(scope.row.id)">{{$t('table.delete')}}</el-button>
                          </template>
                        </el-table-column>
                      </el-table>
                    </div>
                  </el-card>
                </template>
              </el-table-column>


              <el-table-column prop="code" label="单元编码" width="180" align="center"></el-table-column>
              <el-table-column prop="name" label="单元名称" width="180" align="center"></el-table-column>
              <el-table-column label="单元课程" align="center">
                <template slot-scope="scope">
                  <el-button @click="handleViewCourse(scope.row.id)" size="mini"><span>查看课程</span></el-button>
                </template>
              </el-table-column>
              <el-table-column label="单元奖品" align="center">
                <template slot-scope="scope">
                  <el-button @click="handleViewGoods(scope.row.id)" size="mini"><span>查看奖品</span></el-button>
                </template>
              </el-table-column>
              <el-table-column label="操作" align="center">
                <template slot-scope="scope">
                  <el-button type="primary" size="mini" @click="handleUpdateStageGroup(scope.row)"><span>编辑</span></el-button>
                  <el-button type="danger" size="mini" @click="handleDeleteStageGroup(scope.row.id)"><span>删除</span></el-button>
                </template>
              </el-table-column>
            </el-table>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <el-row>
      <el-col>

      </el-col>
    </el-row>

    <el-dialog width="" title="查看课程" :visible.sync="groupCourseDialogFormVisible">
      <div>
        <el-transfer
          style="text-align: left;"
          v-model="outGroupCourse"
          filterable
          :titles="['当前单元课程', '其他单元课程']"
          :button-texts="['到左边', '到右边']"
          :format="{
            noChecked: '${total}',
            hasChecked: '${checked}/${total}'
          }"
          @change="handleChange"
          :data="groupCourse">
          <span slot-scope="{ option }">{{ option.label }}</span>
        </el-transfer>
        <div slot="footer" class="dialog-footer" style="margin-top: 20px;" align="right">
          <el-button @click="groupCourseDialogFormVisible = false">{{$t('table.cancel')}}</el-button>
          <el-button type="primary" @click="handleUpdateGroupCourse">{{$t('table.confirm')}}</el-button>
        </div>
      </div>
    </el-dialog> <!-- 周课程信息弹窗 end -->

    <el-dialog width="60%" title="查看奖品" :visible.sync="groupGoodsDialogFormVisible" top="6vh">
      <el-tabs v-model="activeName" @tab-click="reloadList">
        <el-tab-pane label="已选商品" name="inGroupGoodsTab">
          <div class="filter-container">
            <el-input @keyup.enter.native="handleFilter" style="width: 200px;" class="filter-item"
                      :placeholder="$t('table.name')" v-model="groupGoodsQuery.name" size="mini"></el-input>
            <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}</el-button>
          </div>
          <el-table :data="groupGoodsList" v-loading.body="listLoading"  height="500"
                    element-loading-text="Loading" size="mini" border fit highlight-current-row>
            <el-table-column align="center" label='ID' width="95">
              <template slot-scope="scope">
                {{scope.$index + 1}}
              </template>
            </el-table-column>
            <el-table-column label="商品图片" width="120" align="center">
              <template slot-scope="scope">
                <img v-if="scope.row.imageUrl !== ''" v-bind:src="scope.row.imageUrl + '?v=' + (new Date()).valueOf()" v-bind:width="35" height="35"/>
              </template>
            </el-table-column>
            <el-table-column label="商品名称" width="" align="center">
              <template slot-scope="scope">
                {{ scope.row.name }}
              </template>
            </el-table-column>
            <el-table-column label="商品价格" width="140" align="center" prop="number">
              <template slot-scope="scope">
                {{scope.row.price }}
              </template>
            </el-table-column>
            <el-table-column align="center" label="目标积分" width="220">
              <template slot-scope="scope">
                <template v-if="scope.row.edit">
                  <el-input class="edit-input" size="mini" v-model="scope.row.exchangePoints" style="width: 50%"></el-input>
                  <el-button class='cancel-btn' size="mini" icon="el-icon-refresh" type="warning" @click="cancelEdit(scope.row)">{{$t('table.cancel')}}</el-button>
                </template>
                <span v-else>{{ scope.row.exchangePoints }}</span>
              </template>
            </el-table-column>
            <el-table-column v-if="true" align="center" :label="$t('table.actions')" width="340" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button v-if="scope.row.edit" type="success" @click="confirmEdit(scope.row)" size="mini" icon="el-icon-circle-check-outline">{{$t('table.confirm')}}</el-button>
                <el-button v-else type="primary" @click='scope.row.edit = !scope.row.edit' size="mini" icon="el-icon-edit">{{$t('table.edit')}}</el-button>
                <el-button size="mini" type="danger" @click="handleDeleteGroupGoodsById(scope.row.groupGoodsId)">{{$t('table.delete')}}</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div class="pagination-container">
            <el-pagination
              @size-change="handleSizeChange"
              @current-change="handleCurrentChange"
              :current-page="groupGoodsQuery.page"
              :page-sizes="[10, 20, 30, 40]"
              :page-size="groupGoodsQuery.limit"
              layout="total, sizes, prev, pager, next, jumper"
              :total="groupGoodsTotal">
            </el-pagination>
          </div>
        </el-tab-pane>
        <el-tab-pane label="未选商品" name="outGroupGoodsTab">
          <!-- 未选商品列表 -->
          <div class="filter-container">
            <el-input @keyup.enter.native="handleGoodsFilter" style="width: 200px;" class="filter-item"
                      :placeholder="$t('table.name')" v-model="goodsQuery.name" size="mini"></el-input>
            <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleFilter">{{$t('table.search')}}</el-button>
            <el-button @click="handleAddGroupGoods" class="filter-item" type="primary" icon="el-icon-edit" size="mini">
              {{$t('table.add')}}
            </el-button>
          </div>
          <el-table :data="goodsList" v-loading.body="goodsListLoading"  height="500"
                    element-loading-text="Loading" size="mini" border fit highlight-current-row
                    @selection-change="handleGoodsSelectionChange">
            <el-table-column
              type="selection"
              width="55">
            </el-table-column>
            <el-table-column align="center" label='ID' width="95">
              <template slot-scope="scope">
                {{scope.$index + 1}}
              </template>
            </el-table-column>
            <el-table-column label="商品图片" width="120" align="center">
              <template slot-scope="scope">
                <img v-if="scope.row.imageUrl !== ''" v-bind:src="scope.row.imageUrl + '?v=' + (new Date()).valueOf()" v-bind:width="35" height="35"/>
              </template>
            </el-table-column>
            <el-table-column label="商品名称" width="" align="center">
              <template slot-scope="scope">
                {{ scope.row.name }}
              </template>
            </el-table-column>
            <el-table-column label="商品价格" width="140" align="center" prop="number">
              <template slot-scope="scope">
                {{scope.row.price }}
              </template>
            </el-table-column>
          </el-table>
          <div class="pagination-container">
            <el-pagination
              @size-change="handleGoodsSizeChange"
              @current-change="handleGoodsCurrentChange"
              :current-page="goodsQuery.page"
              :page-sizes="[10, 20, 30, 40]"
              :page-size="goodsQuery.limit"
              layout="total, sizes, prev, pager, next, jumper"
              :total="goodsTotal">
            </el-pagination>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-dialog> <!-- 单元兑换商品设置弹窗 end -->

    <el-dialog width="" :title="groupDialogTitle[groupDialogStatus]" :visible.sync="groupDialogFormVisible">
      <el-form :rules="stageGroupFromRules" ref="stageGroupForm" :model="stageGroup" label-width="90px"
               style='width:90%;margin-left:20px;margin-top: 20px;' size="mini">
        <el-row>
          <el-col :span="12">
            <el-form-item label="单元编码" prop="code">
              <el-input type="number" v-model="stageGroup.code"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="单元名称" prop="name">
              <el-input v-model="stageGroup.name"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-row>
          <el-col :span="12">
            <el-form-item label="单元周数" prop="week">
              <el-input type="number" v-model="stageGroup.week"></el-input>
            </el-form-item>
          </el-col>
          <el-col :span="12">
            <el-form-item label="每周课程数" prop="courseNumber">
              <el-input type="number" v-model="stageGroup.courseNumber"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button @click="groupDialogFormVisible = false">{{$t('table.cancel')}}</el-button>
          <el-button v-if="groupDialogStatus === 'create'" type="primary" @click="createStageGroup">{{$t('table.confirm')}}</el-button>
          <el-button v-else type="primary" @click="updateStageGroup">{{$t('table.confirm')}}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog> <!-- 课阶单元信息设置弹窗 end -->

    <el-dialog width="30%" :title="unitWeekDialogTitle[weekDialogStatus]" :visible.sync="weekDialogFormVisible">
      <el-form :rules="unitWeekFormRules" ref="unitWeekForm" :model="courseStageWeek" label-width="90px"
               style='margin-left:20px;margin-top: 20px;' size="mini">
        <el-row>
          <el-col :span="22">
            <el-form-item label="周名称" prop="name">
              <el-input v-model="courseStageWeek.name"></el-input>
            </el-form-item>
          </el-col>

        </el-row>
        <el-row>
          <el-col :span="22">
            <el-form-item label="课程数" prop="courseNumber">
              <el-input type="number" v-model="courseStageWeek.courseNumber"></el-input>
            </el-form-item>
          </el-col>
        </el-row>
        <el-form-item>
          <el-button @click="weekDialogFormVisible = false">{{$t('table.cancel')}}</el-button>
          <el-button v-if="weekDialogStatus === 'create'" type="primary" @click="createUnitWeek">{{$t('table.confirm')}}</el-button>
          <el-button v-else type="primary" @click="updateUnitWeek">{{$t('table.confirm')}}</el-button>
        </el-form-item>
      </el-form>
    </el-dialog> <!-- 课阶单元周信息设置弹窗 end -->

    <el-dialog width="60%" title="查看资源" :visible.sync="weekResourcesDialogFormVisible" top="6vh">
      <el-tabs v-model="weekResourcesTabActiveName" @tab-click="handleWeekResourcesTabClick" ref="weekResourcesTabs">
        <el-tab-pane label="已选资源" name="inWeekResourcesTab">
          <div class="filter-container">
            <el-input @keyup.enter.native="handleWeekResourcesFilter" style="width: 200px;" class="filter-item"
                      :placeholder="$t('table.name')" v-model="weekResourcesQuery.mixedField" size="mini"></el-input>
            <el-select v-model="weekResourcesQuery.type" filterable placeholder="请选择资源类型" @change="handleWeekResourcesFilter" class="filter-item" size="mini">
              <el-option label="请选择" :key="null" :value="null"/>
              <el-option v-for="item in weekResourceType" :key="item.key" :label="item.display_name"
                         :value="item.key">
              </el-option>
            </el-select>
            <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleWeekResourcesFilter">{{$t('table.search')}}</el-button>
          </div>
          <el-table :data="weekResourcesList" v-loading.body="listLoading"  height="500"
                    element-loading-text="Loading" size="mini" border fit highlight-current-row>
            <el-table-column align="center" label='ID' width="95">
              <template slot-scope="scope">
                {{scope.$index + 1}}
              </template>
            </el-table-column>
            <el-table-column label="资源名称" width="180" align="center">
              <template slot-scope="scope">
                {{ scope.row.resourceName }}
              </template>
            </el-table-column>
            <el-table-column label="资源类型" width="120" align="center">
              <template slot-scope="scope">
                {{ [ weekResourceType, scope.row.type ] | keyValueFilter }}
              </template>
            </el-table-column>
            <el-table-column label="资源地址" width="" align="center">
              <template slot-scope="scope">
                {{ decodeURI(scope.row.resourceUrl) }}
              </template>
            </el-table-column>
            <el-table-column v-if="true" align="center" :label="$t('table.actions')" width="120" class-name="small-padding fixed-width">
              <template slot-scope="scope">
                <el-button size="mini" type="danger" @click="handleDeleteWeekResources(scope.row.id)">{{$t('table.delete')}}</el-button>
              </template>
            </el-table-column>
          </el-table>
          <div class="pagination-container">
            <el-pagination
              @size-change="handleWeekResourcesSizeChange"
              @current-change="handleWeekResourcesCurrentChange"
              :current-page="weekResourcesQuery.pageNum"
              :page-sizes="[10, 20, 30, 40]"
              :page-size="weekResourcesQuery.pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="weekResourcesTotal">
            </el-pagination>
          </div>
        </el-tab-pane>
        <el-tab-pane label="未选资源" name="outWeekResourcesTab">
          <!-- 未选商品列表 -->
          <div class="filter-container">
            <el-input @keyup.enter.native="handleResourcesFilter" style="width: 200px;" class="filter-item" :placeholder="$t('table.name')" v-model="resourcesQuery.mixedField" size="mini"></el-input>
            <el-select v-model="resourcesQuery.resourceType" filterable size="mini" class="filter-item" @change="handleResourcesTypeChange">
              <el-option :value="null" :key="null" label="请选择"/>
              <el-option v-for="item in resourceType" :key="item.key" :label="item.display_name"
                         :value="item.key">
              </el-option>
            </el-select>
            <el-button class="filter-item" type="primary" icon="el-icon-search" size="mini" @click="handleResourcesFilter">{{$t('table.search')}}</el-button>
            <el-button @click="handleAddWeekResources(1)" class="filter-item" type="primary" icon="el-icon-edit" size="mini">{{$t('table.addSongs')}}</el-button>
            <el-button @click="handleAddWeekResources(2)" class="filter-item" type="primary" icon="el-icon-edit" size="mini">{{$t('table.addStories')}}</el-button>
          </div>
          <el-table :data="resourcesList" v-loading.body="listLoading"  height="500"
                    element-loading-text="Loading" size="mini" border fit highlight-current-row
                    @selection-change="handleResourcesSelectionChange">
            <el-table-column
              type="selection"
              width="55">
            </el-table-column>
            <el-table-column align="center" label='ID' width="95">
              <template slot-scope="scope">
                {{scope.$index + 1}}
              </template>
            </el-table-column>
            <el-table-column label="资源名称" width="220" align="center">
              <template slot-scope="scope">
                {{ scope.row.name }}
              </template>
            </el-table-column>
            <el-table-column label="资源地址" width="" align="center">
              <template slot-scope="scope">
                {{ decodeURI(scope.row.url) }}
              </template>
            </el-table-column>
          </el-table>
          <div class="pagination-container">
            <el-pagination
              @size-change="handleResourcesSizeChange"
              @current-change="handleResourceCurrentChange"
              :current-page="weekResourcesQuery.pageNum"
              :page-sizes="[10, 20, 30, 40]"
              :page-size="weekResourcesQuery.pageSize"
              layout="total, sizes, prev, pager, next, jumper"
              :total="resourcesTotal">
            </el-pagination>
          </div>
        </el-tab-pane>
      </el-tabs>
    </el-dialog> <!-- 主题资源详情弹出 end -->
  </div>
</template>

<script>
  import { addCourseStage, editCourseStage, getCourseStageById, getCourseStageGroupList, updateCourseGroup, editWeekCourse,
    courseList, getExperienceStage, getListByUnitId, createUnitWeek, updateUnitWeek, deleteUnitWeekById,
    getWeekResources, deleteWeekResourcesPhysically, batchAddWeekResources } from '@/api/course'
  import { addStageGroup, editStageGroup, deleteStageGroup, getStageGroupGoods, addGroupGoodsBatch, deleteStageGroupGoods, updateGroupGoodsBatch } from '@/api/courseStageGroup'
  import { getGoodsList } from '@/api/goods'
  import { getListOutWeekSelected } from '@/api/resource'
  import { weekResourceType, resourceType, keyValueFilter } from '@/utils/constant'

  import { teacherList } from '@/api/teacher'
  import SingleImageUpload from '../../../components/Upload/singleImageUpload'
  import { validateImage } from '@/utils/validate'
  import Goods from '../goods/list'
  import { closeCurrentView } from '@/utils'

  const courseStageObject = {
    id: null,
    stageName: '',
    level: '',
    description: '',
    spendTime: '',
    courseCount: '',
    originPrice: '',
    preferentialPrice: '',
    soldCount: '',
    imgUrl: '',
    teacherId: null,
    selling: 1,
    type: 1,
    status: 1
  }

  const stageGroupObject = {
    id: null,
    name: '',
    code: '',
    stageId: null,
    week: null,
    courseNumber: null
  }

  const courseStageWeekObject = {
    name: '',
    unitId: null,
    courseNumber: 0,
    status: 1
  }

  export default {
    name: 'CourseStageDetail',
    components: {
      Goods,
      SingleImageUpload },
    props: {
      isEdit: {
        type: Boolean,
        default: false
      }
    },
    created() {
      if (this.isEdit) {
        this.stageId = this.$route.params && this.$route.params.id
        this.fetchData()
        this.fetchStageGroupList()
      } else {
        this.courseStage = Object.assign({}, courseStageObject)
      }
      this.getTeacherList()
    },
    data() {
      return {
        courseStageWeek: Object.assign({}, courseStageWeekObject),
        courseWeekList: [],
        courseWeekTableListLoading: false,
        weekDialogFormVisible: false,
        weekDialogStatus: '',
        unitWeekDialogTitle: {
          update: '编辑',
          create: '新增'
        },
        unitWeekFormRules: {
          name: [{ required: true, message: '周名称为必填项', trigger: 'change' }],
          courseNumber: [{ required: true, message: '周课程数为必填项', trigger: 'change' }]
        },
        unitId: null,
        unitName: '',
        viewCourseType: '',
        // 单元每周信息 end
        groupCourseDialogFormVisible: false,
        groupId: null,
        weekId: null,
        stageId: null,
        nullGroupCourse: [],
        outGroupCourse: [],
        inGroupCourse: [],
        groupCourse: [],

        nullWeekCourse: [],
        outWeekCourse: [],
        inWeekCourse: [],
        groupWeekCourse: [],

        // 每周课程信息 end
        weekResourcesDialogFormVisible: false,
        weekResourcesTabActiveName: 'inWeekResourcesTab',
        weekResourcesQuery: {
          mixedField: '',
          type: null,
          weekId: null,
          pageNum: 1,
          pageSize: 10
        },
        weekResourceType: weekResourceType,
        weekResourcesTotal: 0,
        weekResourcesList: [],
        resourceType: resourceType,
        resourcesList: [],
        resourcesTotal: 0,
        resourcesQuery: {
          resourceType: 2,
          mixedField: '',
          pageNum: 1,
          pageSize: 10
        },
        resourcesSelection: [],
        // 每周主题资源信息 end
        stageGroup: Object.assign({}, stageGroupObject),
        courseStageGroup: [],
        groupDialogFormVisible: false,
        groupDialogStatus: '',
        groupDialogTitle: {
          update: '编辑',
          create: '新增'
        },
        // 单元弹窗信息 end
        activeName: 'inGroupGoodsTab',
        groupGoodsListLoading: false,
        groupGoodsList: [],
        groupGoodsTotal: 0,
        groupGoodsIds: null,
        groupGoodsDialogFormVisible: false,
        goodsSelection: [],
        goodsListLoading: false,
        goodsList: [],
        goodsTotal: 0,
        goodsQuery: {
          name: null,
          page: 1,
          limit: 10,
          virtual: 0
        },
        groupGoodsQuery: {
          page: 1,
          limit: 10,
          stageId: null,
          groupId: null
        },
        // 单元兑换商品信息 end
        expandUnitColumnsRowKey: null,
        expandUnitColumnsRowKeys: [],
        listLoading: false,
        dataList: {},
        submit: false,
        courseStage: Object.assign({}, courseStageObject),
        teacherList: [],
        rules: {
          stageName: [{ required: true, message: '标题为必填项', trigger: 'change' }],
          courseCount: [{ required: true, message: '课程数量为必填项', trigger: 'change' }],
          originPrice: [{ required: true, message: '原价为必填项', trigger: 'change' }],
          preferentialPrice: [{ required: true, message: '折扣价为必填项', trigger: 'change' }],
          soldCount: [{ required: true, message: '销售数量为必填项', trigger: 'change' }],
          imgUrl: [{ required: true, notNullMessage: '图片为必填项', TypeErrorMessage: '请上传正确类型的文件', trigger: 'change', validator: validateImage }],
          spendTime: [{ required: true, message: '课时为必填项', trigger: 'change' }],
          level: [{ required: true, message: '阶段为必填项', trigger: 'change' }],
          teacherId: [{ required: true, message: '教师为必填项', trigger: 'change' }]
        },
        stageGroupFromRules: {
          code: [{ required: true, message: '单元编号为必填项', trigger: 'change' }],
          name: [{ required: true, message: '单元名称为必填项', trigger: 'change' }]
        }
      }
    },
    filters: {
      keyValueFilter
    },
    methods: {
      createUnitWeek() {
        if (this.courseStageWeek.unitId == null) {
          this.$message.error('请先选择单元')
          return
        }
        this.$refs['unitWeekForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.courseStageWeek) // 对象拷贝
            createUnitWeek(tempData).then((res) => {
              if (res.data.success) {
                this.$notify({
                  title: '成功',
                  message: res.data.message,
                  type: 'success',
                  duration: 2000
                })
                this.weekDialogFormVisible = false
                this.getCourseUnitWeekListByUnitId(this.courseStageWeek.unitId)
              } else {
                this.$message.error(res.data.message)
              }
            })
          }
        })
      },
      updateUnitWeek() {
        this.$refs['unitWeekForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.courseStageWeek) // 对象拷贝
            updateUnitWeek(tempData).then((res) => {
              if (res.data.success) {
                if (res.data.success) {
                  this.$notify({
                    title: '成功',
                    message: res.data.message,
                    type: 'success',
                    duration: 2000
                  })
                  this.weekDialogFormVisible = false
                  this.getCourseUnitWeekListByUnitId(this.courseStageWeek.unitId)
                } else {
                  this.$message.error(res.data.message)
                }
              }
            })
          }
        })
      },
      handleDeleteUnitWeek(id) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteUnitWeekById(id).then(res => {
            if (res.data.success) {
              if (res.data.success) {
                this.$notify({
                  title: '成功',
                  message: res.data.message,
                  type: 'success',
                  duration: 2000
                })
                this.weekDialogFormVisible = false
                this.getCourseUnitWeekListByUnitId(this.courseStageWeek.unitId)
              } else {
                this.$message.error(res.data.message)
              }
            }
          })
        }).catch(() => {

        })
      },
      handleCreateUnitWeek() {
        this.courseStageWeek = {
          name: '',
          unitId: null,
          courseNumber: 0,
          status: 1
        }
        this.courseStageWeek.unitId = this.unitId
        this.weekDialogStatus = 'create'
        this.weekDialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['unitWeekForm'].clearValidate()
        })
      },
      handleUpdateUnitWeek(row) {
        this.courseStageWeek = {
          name: '',
          unitId: null,
          courseNumber: 0,
          status: 1
        }
        this.courseStageWeek = Object.assign({}, row)
        this.weekDialogStatus = 'update'
        this.weekDialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['unitWeekForm'].clearValidate()
        })
      },
      getCourseUnitWeekListByUnitId(unitId) {
        getListByUnitId(unitId).then((res) => {
          if (res.data.success) {
            this.courseWeekList = res.data.body.list
          } else {
            this.$message.error(res.data.message)
          }
        })
      }, // 课阶单元周信息操作方法 end
      handleViewWeekCourse(weekId) {
        this.viewCourseType = 'week'
        this.groupCourseDialogFormVisible = true
        this.groupId = this.unitId
        this.weekId = weekId
        this.fetchWeekCourseList()
      },
      fetchWeekCourseList() {
        // 更新数据
        this.inGroupCourse = []
        this.outGroupCourse = []
        this.groupCourse = []
        this.nullGroupCourse = []

        const params = {}
        params.stageId = this.stageId
        params.groupId = this.unitId
        // params.weeks = this.weekId
        courseList(params).then((res) => {
          res.data.courseList.list.forEach((row) => {
            if (row.weeks !== this.weekId) {
              this.outGroupCourse.push(row.id)
            } else {
              this.inGroupCourse.push(row.id)
            }
            this.groupCourse.push({
              key: row.id,
              label: row.groupName ? row.groupName + ' - ' + row.courseName : row.courseName,
              disabled: false
            })
          })
        })
      },
      // 课阶单元周课程信息操作方法 end
      handleCancelEdit() {
        // 关闭当前标签页
        closeCurrentView(this.$store, this.$router, this.$route.path, '/school/course/stage')
      },
      handleDefaultStageChange(val) {
        if (val === 0) {
          getExperienceStage().then(res => {
            if (res.data.code === 0) {
              if (res.data.courseStage != null) {
                this.courseStage.type = 1
                this.$message.error('当前已设置默认体验课阶, 无法再设置.')
              }
            }
          })
        }
      },
      // 商品相关
      handleAddGroupGoods() {
        const data = []
        this.goodsSelection.forEach((row) => {
          data.push({
            goodsId: row.id,
            stageId: this.stageId,
            groupId: this.groupId,
            exchangePoints: 0
          })
        })
        addGroupGoodsBatch(data).then((res) => {
          if (res.data.code === 0) {
            this.activeName = 'inGroupGoodsTab'
            this.fetchStageGroupGoodsList()
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      handleGoodsSelectionChange(val) {
        this.goodsSelection = val
      },
      fetchGoodsList() {
        this.goodsListLoading = true
        if (this.groupGoodsIds !== '') {
          this.goodsQuery.outGoodsId = this.groupGoodsIds
        }
        getGoodsList(this.goodsQuery).then(res => {
          if (res.data.code === 0) {
            this.goodsList = res.data.goodsList.list
            this.goodsTotal = res.data.goodsList.totalCount
          } else {
            this.$message.error(res.data.msg)
          }
          this.goodsListLoading = false
        })
      },
      handleGoodsFilter() {
        this.groupGoodsQuery.page = 1
        this.fetchStageGroupGoodsList()
      },
      handleGoodsSizeChange(val) {
        this.groupGoodsQuery.limit = val
        this.fetchStageGroupGoodsList()
      },
      handleGoodsCurrentChange(val) {
        this.groupGoodsQuery.page = val
        this.fetchStageGroupGoodsList()
      },
      // 单元商品相关
      fetchStageGroupGoodsList() {
        this.groupGoodsListLoading = true
        getStageGroupGoods(this.groupGoodsQuery).then((res) => {
          if (res.data.code === 0) {
            this.dataList = res
            // this.groupGoodsList = res.data.goods.list
            const items = res.data.goods.list
            this.groupGoodsList = items.map(v => {
              this.$set(v, 'edit', false) // https://vuejs.org/v2/guide/reactivity.html
              v.originalPoints = v.exchangePoints //  will be used when user click the cancel botton
              return v
            })
            this.groupGoodsTotal = res.data.goods.totalCount

            let t = ''
            this.groupGoodsList.forEach((row) => {
              t += row.id + ','
            })
            this.groupGoodsIds = t !== '' ? t.substring(0, t.lastIndexOf(',')) : ''
          } else {
            this.$message.error(res.data.msg)
          }
        })
        this.groupGoodsListLoading = false
      },
      cancelEdit(row) {
        row.exchangePoints = row.originalPoints
        row.edit = false
      },
      confirmEdit(row) { // 确定修改信息
        row.edit = false
        console.log(row)
        if (row.exchangePoints !== row.originalPoints) {
          const temp = Object.assign({}, row) // copy obj
          temp.id = temp.groupGoodsId
          updateGroupGoodsBatch(temp).then((res) => {
            if (res.data.code === 0) {
              row.originalPoints = row.exchangePoints
              this.$notify({
                title: '成功',
                message: '操作成功',
                type: 'success',
                duration: 2000
              })
            } else {
              this.$message.error(res.data.msg)
            }
          })
        }
      },
      handleFilter() {
        this.groupGoodsQuery.page = 1
        this.fetchStageGroupGoodsList()
      },
      handleSizeChange(val) {
        this.groupGoodsQuery.limit = val
        this.fetchStageGroupGoodsList()
      },
      handleCurrentChange(val) {
        this.groupGoodsQuery.page = val
        this.fetchStageGroupGoodsList()
      },
      handleViewGoods(groupId) {
        this.groupGoodsQuery.stageId = null
        this.groupGoodsQuery.groupId = null
        this.groupGoodsDialogFormVisible = true
        this.groupId = groupId
        this.activeName = 'inGroupGoodsTab'
        this.groupGoodsQuery.stageId = this.stageId
        this.groupGoodsQuery.groupId = this.groupId
        this.fetchStageGroupGoodsList()
      },
      reloadList(tabs, event) {
        const tabName = event.target.getAttribute('id')
        if (tabName === 'tab-inGroupGoodsTab') {
          // 刷新单元奖品列表
          this.fetchStageGroupGoodsList()
        } else if (tabName === 'tab-outGroupGoodsTab') {
          this.fetchGoodsList()
        }
      },
      handleDeleteGroupGoodsById(id) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteStageGroupGoods(id).then((res) => {
            if (res.data.code === 0) {
              this.fetchStageGroupGoodsList()
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
      },
      // 单元信息操作相关
      fetchStageGroupList() {
        getCourseStageGroupList(this.stageId).then((res) => {
          if (res.data.code === 0) {
            this.courseStageGroup = res.data.groupList
          } else {
            this.$message.error(res.data.msg)
          }
        })
      },
      handleUpdateStageGroup(row) {
        this.resetStageTemp()
        this.groupDialogStatus = 'update'
        this.groupDialogFormVisible = true
        this.stageGroup = Object.assign({}, row)
        this.$nextTick(() => {
          this.$refs['stageGroupForm'].clearValidate()
        })
      },
      updateStageGroup() {
        this.$refs['stageGroupForm'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.stageGroup) // 对象拷贝
            editStageGroup(tempData).then((res) => {
              if (res.data.code === 0) {
                this.fetchStageGroupList()
                this.groupDialogFormVisible = false
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
      handleDeleteStageGroup(groupId) {
        deleteStageGroup(groupId).then((res) => {
          if (res.data.code === 0) {
            this.fetchStageGroupList()
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
      },
      createStageGroup() {
        this.$refs['stageGroupForm'].validate((valid) => {
          if (valid) {
            this.stageGroup.stageId = this.stageId
            addStageGroup(this.stageGroup).then((res) => {
              if (res.data.code === 0) {
                this.groupDialogFormVisible = false
                this.fetchStageGroupList(this.stageId)
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
      handleCreateStageGroup() {
        this.resetStageTemp()
        this.groupDialogStatus = 'create'
        this.groupDialogFormVisible = true
        this.$nextTick(() => {
          this.$refs['stageGroupForm'].clearValidate()
        })
      },
      resetStageTemp() {
        this.stageGroup = {
          id: null,
          name: '',
          code: '',
          stageId: null,
          type: 1
        }
      },
      // 单元课程相关
      handleViewCourse(groupId) {
        this.viewCourseType = 'unit'
        this.groupCourseDialogFormVisible = true
        this.groupId = groupId
        this.fetchCourseList()
      },
      handleChange(value, direction, movedKeys) {
        if (direction === 'right') {
          movedKeys.forEach((o) => {
            this.nullGroupCourse.push(o)
            const i = this.inGroupCourse.indexOf(o)
            if (i >= 0) {
              this.inGroupCourse.splice(i, 1)
            }
          })
        } else {
          movedKeys.forEach((o) => {
            this.inGroupCourse.push(o)
          })
        }
      },
      fetchCourseList() {
        // 更新数据
        this.inGroupCourse = []
        this.outGroupCourse = []
        this.groupCourse = []
        this.nullGroupCourse = []

        const params = {}
        params.stageId = this.stageId
        courseList(params).then((res) => {
          res.data.courseList.list.forEach((row) => {
            if (row.groupId !== this.groupId) {
              this.outGroupCourse.push(row.id)
            } else {
              this.inGroupCourse.push(row.id)
            }
            this.groupCourse.push({
              key: row.id,
              label: row.groupName ? row.groupName + ' - ' + row.courseName : row.courseName,
              disabled: false
            })
          })
        })
      },
      handleUpdateGroupCourse() {
        this.$refs['courseStageFrom'].validate((valid) => {
          if (valid) {
            if (this.viewCourseType === 'week') {
              const params = {}
              params.inCourseId = this.inGroupCourse
              params.groupId = this.groupId
              params.outCourseId = this.nullGroupCourse
              params.weekId = this.weekId
              editWeekCourse(params).then((res) => {
                console.log(res)
                if (res.data.success) {
                  this.groupCourseDialogFormVisible = false
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
              const params = {}
              params.courseIds = this.inGroupCourse
              params.groupId = this.groupId
              params.classCourseIds = this.nullGroupCourse
              updateCourseGroup(params).then((res) => {
                if (res.data.code === 0) {
                  this.groupCourseDialogFormVisible = false
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
          }
        })
      },
      getTeacherList() {
        teacherList().then((response) => {
          this.teacherList = response.data.teacherList.list
        })
      },
      fetchData() {
        getCourseStageById(this.stageId).then((res) => {
          this.courseStage = res.data.courseStage
        })
      },
      createData() {
        this.$refs['courseStageFrom'].validate((valid) => {
          if (valid) {
            addCourseStage(this.courseStage).then((res) => {
              if (res.data.code === 0) {
                // 关闭当前标签页
                closeCurrentView(this.$store, this.$router, this.$route.path, '/school/course/stage')
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
      updateData() {
        this.$refs['courseStageFrom'].validate((valid) => {
          if (valid) {
            const tempData = Object.assign({}, this.courseStage) // 对象拷贝
            editCourseStage(tempData).then((res) => {
              if (res.data.code === 0) {
                // 关闭当前标签页
                // closeCurrentView(this.$store, this.$router, this.$route.path, '/school/course/stage')
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
      handleExpandUnitInfoColumns(row, expandedRows) {
        if (expandedRows.length !== 0) { // 展开
          this.expandUnitColumnsRowKeys = []
          this.expandUnitColumnsRowKeys.push(row.id)

          this.weekId = null
          this.unitName = row.name
          this.unitId = row.id
          this.getCourseUnitWeekListByUnitId(row.id)
        } else { // 关闭
          this.expandUnitColumnsRowKeys = []
        }
      },
      handleExpandUnitColumnsRowKey(row) {
        return row.id
      },
      // 课程周主题资源方法 start
      handleViewWeekResources(weekId) {
        this.weekResourcesList = []
        this.weekResourcesTotal = 0
        this.weekResourcesTabActiveName = 'inWeekResourcesTab'
        this.weekResourcesQuery.weekId = null
        this.weekResourcesQuery.mixedField = ''
        this.weekResourcesQuery.type = null
        this.weekResourcesQuery.pageNum = 1
        this.weekResourcesQuery.pageSize = 10

        this.weekResourcesQuery.weekId = weekId
        this.weekResourcesDialogFormVisible = true
        this.fetchWeekResources()
      },
      handleWeekResourcesTabClick(tabs, event) {
        const tabName = event.target.getAttribute('id')
        if (tabName === 'tab-inWeekResourcesTab') {
          this.weekResourcesQuery.mixedField = ''
          this.weekResourcesQuery.type = null
          this.weekResourcesQuery.pageNum = 1
          this.weekResourcesQuery.pageSize = 10
          this.fetchWeekResources()
        } else if (tabName === 'tab-outWeekResourcesTab') {
          this.resourcesQuery = {
            resourceType: 2,
            mixedField: '',
            pageNum: 1,
            pageSize: 10
          }
          this.fetchResources()
        }
      },
      fetchResources() {
        this.listLoading = true
        getListOutWeekSelected(this.weekResourcesQuery.weekId, this.resourcesQuery).then(res => {
          this.listLoading = false
          if (res.data.success) {
            this.resourcesList = res.data.body.list
            this.resourcesTotal = res.data.body.total
          } else {
            this.$message.error(this.data.message)
          }
        })
      },
      handleResourcesTypeChange() {
        this.fetchResources()
      },
      fetchWeekResources() {
        this.listLoading = true
        getWeekResources(this.weekResourcesQuery.weekId, this.weekResourcesQuery).then(res => {
          this.listLoading = false
          if (res.data.success) {
            this.weekResourcesList = res.data.body.list
            this.weekResourcesTotal = res.data.body.total
          } else {
            this.$message.error(res.data.message)
          }
        })
      },
      handleDeleteWeekResources(id) {
        this.$confirm('此操作将删除数据, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          deleteWeekResourcesPhysically(id).then(res => {
            if (res.data.success) {
              this.fetchWeekResources()
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
      handleResourcesSelectionChange(val) {
        this.resourcesSelection = val
      },
      handleAddWeekResources(type) {
        const params = {}
        const ids = []
        this.resourcesSelection.forEach(row => {
          ids.push(row.id + '')
        })
        params.list = ids
        params.weekId = this.weekResourcesQuery.weekId
        params.type = type
        batchAddWeekResources(params).then(res => {
          if (res.data.success) {
            this.$notify({
              title: '成功',
              message: res.data.message,
              type: 'success',
              duration: 2000
            })
            this.fetchWeekResources()
            this.weekResourcesTabActiveName = 'inWeekResourcesTab'
          } else {
            this.$message.error(res.data.message)
          }
        })
      },
      handleWeekResourcesFilter() {
        this.weekResourcesQuery.pageNum = 1
        this.fetchWeekResources()
      },
      handleWeekResourcesCurrentChange(val) {
        this.weekResourcesQuery.pageNum = val
        this.fetchWeekResources()
      },
      handleWeekResourcesSizeChange(val) {
        this.weekResourcesQuery.pageSize = val
        this.fetchWeekResources()
      },
      handleResourcesFilter() {
        this.resourcesQuery.pageNum = 1
        this.fetchResources()
      },
      handleResourcesSizeChange(val) {
        this.resourcesQuery.pageSize = val
        this.fetchResources()
      },
      handleResourceCurrentChange(val) {
        this.resourcesQuery.pageNum = val
        this.fetchResources()
      }
    }
  }
</script>
<style>
  /*.el-dialog__body {*/
    /*padding: 0;*/
  /*}*/
  .el-transfer-panel {
    width: 30%;
  }
</style>
