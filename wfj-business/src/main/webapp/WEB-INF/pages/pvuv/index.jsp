<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>实时报表</title>

    <style>
    </style>

    <%@ include file="../common/meta.jsp" %>
    <script src="https://cdn.hcharts.cn/highcharts/highcharts.js"></script>
    <script type="text/javascript">
        $(function () {
            Highcharts.setOptions({
                colors: ['#2f7ed8', '#8bbc21'],
                global: {
                    useUTC: false
                }
            });

            function activeLastPointToolip(chart) {
                var points = chart.series[0].points;
                chart.tooltip.refresh(points[points.length - 1]);
            }

            $('#timer').highcharts({
                chart: {

                    type: 'spline',
                    animation: Highcharts.svg, // don't animate in old IE
                    marginRight: 10,
                    events: {
                        load: function () {
                            // set up the updating of the chart each second
                            var series = this.series[0],
                                series1 = this.series[1],
                                chart = this;

                            function aaa() {
                                ajax.get("${ctx}/pvuv/tsk",
                                    function (json) {
                                        if (json != null) {
                                            var x = json.timestamp,
                                                y = json.body.pv;
                                            z = json.body.uv;

                                            series.addPoint([(new Date(json.body.creat_tm)).getTime(), y], true, true);
                                            series1.addPoint([(new Date(json.body.creat_tm)).getTime(), z], true, true);
                                        }
                                    });
                            }

                            setInterval(function () {
                                aaa();
                                activeLastPointToolip(chart)
                            }, 1000);
                        }
                    }
                },
                title: {
                    text: '动态实时报表'
                },
                xAxis: {
                    type: 'datetime',
                    tickPixelInterval: 150 //X轴标签间隔
                }, credits: {
                    enabled: false
                },
                yAxis: {
                    title: {
                        text: '值'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,

                    }]
                },

                tooltip: {
                    formatter: function () {
                        return '<b>' + this.series.name + '</b><br/>' +
                            Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x) + '<br/>' +
                            Highcharts.numberFormat(this.y, 0);
                    }
                },
                legend: {
                    enabled: true
                },
                exporting: {
                    enabled: false
                },
                series: [{
                    name: 'pv',
                    data: (function () {
                        // generate an array of random data
                        var data = [],
                            time = (new Date()).getTime(),
                            i;
                        for (i = -19; i <= 0; i += 1) {
                            data.push({
                                x: time + i * 1000,
                                y: 0
                            });
                        }
                        return data;
                    }())
                }, {
                    name: 'uv',
                    data: (function () {
                        // generate an array of random data
                        var data = [],
                            time = (new Date()).getTime(),
                            i;
                        for (i = -18; i <= 0; i += 1) {
                            data.push({
                                x: time + i * 1000,
                                y: 0
                            });
                        }
                        return data;
                    }())
                }]
            }, function (c) {
                activeLastPointToolip(c)
            });
            Highcharts.setOptions({
                global: {
                    useUTC: false
                }
            });

        });


    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content" id="timer">

</div>
</body>
</html>
