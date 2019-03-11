<template>
  <div>
    <nuxt-link class="Home" :to="{ path: '/charging' }">Driving Home</nuxt-link>
    <demo-charts id="chart1" :option="chrgStartTimeOption"/>
  </div>
</template>
<script type="text/ecmascript-6">
  import DemoCharts from '~/components/DemoCharts.vue'
  export default {
    head () {
      return { title: `ChrgStartTime` }
    },
    fetch ({ store }) {
      return store.dispatch('getChrgStartTime')
    },
    computed: {
      chrgstarttime: function () {
        return this.$store.state.chrgstarttime.data
      }
    },
    data () {
      return {
        chrgStartTimeOption: {
          title: {
            text: 'Charging Start Time',
            x: 'center',
            align: 'right'
          },
          tooltip: {
            trigger: 'axis',
            axisPointer: {
              type: 'cross',
              crossStyle: {
                color: '#999'
              }
            }
          },
          legend: {
            x: 'right',
            data: []
          },
          grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            containLabel: true
          },
          xAxis: {
            type: 'category',
            boundaryGap: true,
            data: [ ],
            axisLabel: {
              interval: 0,
              rotate: 0
            }
          },
          yAxis: {
            type: 'value',
            axisLabel: {
              show: true,
              interval: 'auto',
              formatter: '{value} %'
            },
            show: true
          },
          series: [
            {
              name: '',
              type: 'bar',
              barGap: 0,
              data: [ ],
              itemStyle: {
                normal: {
                  color: '#61A0A8'
                }
              }
            }
          ]
        }
      }
    },
    components: {
      DemoCharts
    },
    mounted () {
      setTimeout(() => {
        this.refreshData()
      }, 0)
    },
    methods: {
      refreshData () {
        var json = this.chrgstarttime
        this.chrgStartTimeOption.xAxis.data = json.xAxis
        this.chrgStartTimeOption.series[0].data = json.yAxis
      }
    }
  }
</script>
