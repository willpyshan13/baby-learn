<template>
  <div class="">
          <el-select :size="size" :disabled="uneditable" v-model="provinceCode" @change="choseProvince" placeholder="省级地区">
            <el-option
              v-for="item in province"
              :key="item.id"
              :label="item.value"
              :value="item.id">
            </el-option>
          </el-select>
          <el-select :size="size" :disabled="uneditable" v-model="cityCode" @change="choseCity" placeholder="市级地区">
            <el-option
              v-for="item in city"
              :key="item.id"
              :label="item.value"
              :value="item.id">
            </el-option>
          </el-select>
          <el-select :size="size" :disabled="uneditable" v-model="areaCode" @change="choseBlock" placeholder="区级地区">
            <el-option
              v-for="item in area"
              :key="item.id"
              :label="item.value"
              :value="item.id">
            </el-option>
          </el-select>
  </div>
</template>

<script>
  import axios from 'axios'
  export default {
    name: 'CitySelect',
    props: {
      code: {
        type: String,
        default: ''
      },
      size: {
        type: String,
        default: ''
      },
      uneditable: {
        type: Boolean,
        default: false
      }
    },
    // computed: {
    //   getCode() {
    //     return this.code
    //   }
    // },
    watch: {
      code() {
        // this.setCode()
        console.log('-------')
        console.log(this.code)
        if (this.code !== '' && this.code !== null) {
          this.provinceCode = this.code.substring(0, 2) + '0000'
          this.cityCode = this.code.substring(0, 4) + '00'
          this.areaCode = this.code

          // this.choseProvince(this.code)
          // this.choseCity(this.cityCode)
          // this.choseBlock(this.areaCode)

          // this.choseBlock(this.cityCode)
        }
        console.log('-------')
      }
    },
    data() {
      return {
        mapJson: '../static/json/map.json',
        province: '',
        provinceCode: '',
        cityCode: '',
        city: [],
        areaCode: '',
        area: [],
        block: '',
        value: ''
      }
    },
    created: function() {
      console.log('==========')
      console.log(this.code)
      console.log('==========')
      this.getCityData()
      this.setCode()
    },
    methods: {
      setCode() {
        console.log(this.code)
        if (this.code !== '' && this.code !== null) {
          this.provinceCode = this.code.substring(0, 2) + '0000'
          this.cityCode = this.code.substring(0, 4) + '00'
          this.areaCode = this.code
          // this.choseBlock(this.cityCode)
        }
      },
      // 加载china地点数据，三级
      getCityData: function() {
        var that = this
        axios.get(this.mapJson).then(function(response) {
          if (response.status === 200) {
            const data = response.data
            that.province = []
            that.city = []
            that.block = []
            // 省市区数据分类
            for (const item in data) {
              if (item.match(/0000$/)) { // 省
                that.province.push({ id: item, value: data[item], children: [] })
              } else if (item.match(/00$/)) { // 市
                that.city.push({ id: item, value: data[item], children: [] })
              } else { // 区
                that.block.push({ id: item, value: data[item] })
              }
            }
            // 分类市级
            for (var index in that.province) {
              for (var index1 in that.city) {
                if (that.province[index].id.slice(0, 2) === that.city[index1].id.slice(0, 2)) {
                  that.province[index].children.push(that.city[index1])
                }
              }
            }
            // 分类区级
            for (var item1 in that.city) {
              for (var item2 in that.block) {
                if (that.block[item2].id.slice(0, 4) === that.city[item1].id.slice(0, 4)) {
                  that.city[item1].children.push(that.block[item2])
                }
              }
            }
          } else {
            console.log(response.status)
          }
        }).catch(function(error) { console.log(error) })
      },
      // 选省
      choseProvince: function(e) {
        for (var index2 in this.province) {
          if (e === this.province[index2].id) {
            this.city = this.province[index2].children
            this.cityCode = this.province[index2].children[0].value
            this.area = this.province[index2].children[0].children
            this.areaCode = this.province[index2].children[0].children[0].value
            this.E = this.area[0].id
            console.log(1)

            this.emitInput(this.E)
          }
        }
      },
      // 选市
      choseCity: function(e) {
        for (var index3 in this.city) {
          if (e === this.city[index3].id) {
            this.area = this.city[index3].children
            this.areaCode = this.city[index3].children[0].value
            this.E = this.area[0].id
            console.log(2)

            this.emitInput(this.E)
          }
        }
      },
      // 选区
      choseBlock: function(e) {
        console.log(3)
        console.log(e)

        this.E = e
        this.emitInput(this.E)
      },
      emitInput(val) {
        console.log(4)

        console.log(val)
        this.$emit('input', val)
      }
    }
  }
</script>

<style scoped>
  .el-select {
    display: inline-block;
  }
</style>
