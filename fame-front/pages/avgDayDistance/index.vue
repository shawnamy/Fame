<template>
  <div>
    <nuxt-link class="Home" :to="{ path: '/driving' }">Driving Home</nuxt-link>
    <demo-charts id="chart1" :option="avgDayDistanceOption"/>
  </div>
</template>
<script type="text/ecmascript-6">
  import DemoCharts from '~/components/DemoCharts.vue'
  export default {
    head () {
      return { title: `AvgDayDistance` }
    },
    fetch ({ store }) {
      return store.dispatch('getAvgDayDistance')
    },
    computed: {
      avgdaydistance: function () {
        return this.$store.state.avgdaydistance.data
      }
    },
    data () {
      return {
        avgDayDistanceOption: {
          title: {
            text: 'Average Day Distance',
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
              formatter: '{value} km'
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
        var json = this.avgdaydistance
        this.avgDayDistanceOption.xAxis.data = json.xAxis
        this.avgDayDistanceOption.series[0].data = json.yAxis
      }
    }
  }
</script>
