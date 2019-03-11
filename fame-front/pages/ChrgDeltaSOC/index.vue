<template>
  <div>
    <nuxt-link class="Home" :to="{ path: '/status' }">Status Home</nuxt-link>
    <demo-charts id="chart1" :option="chrgDeltaSOCOption"/>
  </div>
</template>
<script type="text/ecmascript-6">
  import DemoCharts from '~/components/DemoCharts.vue'
  export default {
    head () {
      return { title: `ChrgDeltaSOC` }
    },
    fetch ({ store }) {
      return store.dispatch('getChrgDeltaSOC')
    },
    computed: {
      chrgdeltasoc: function () {
        return this.$store.state.chrgdeltasoc.data
      }
    },
    data () {
      return {
        chrgDeltaSOCOption: {
          title: {
            text: 'Charging Delta SOC',
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
              rotate: 270
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
        var json = this.chrgdeltasoc
        this.chrgDeltaSOCOption.xAxis.data = json.xAxis
        this.chrgDeltaSOCOption.series[0].data = json.yAxis
      }
    }
  }
</script>
