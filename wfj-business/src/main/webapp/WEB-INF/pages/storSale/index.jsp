<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@ include file="../common/meta.jsp" %>
    <script src="https://cdn.hcharts.cn/highcharts/highcharts.js"></script>
    <script type="text/javascript">
        $(function () {

            init();

            function init() {
                var creatTmBegin = $("#creatTmBegin").val();
                var creatTmEnd = $("#creatTmEnd").val();
                var count = 0;
                if (util.isNotEmpty(creatTmBegin)) {
                    count += 1;
                }
                if (util.isNotEmpty(creatTmEnd)) {
                    count += 1;
                }
                if (count == 1) {
                    alert("时间段不全！");
                    return;
                }

                var chart1 = new Highcharts.Chart('main1', {
                    chart: {
                        plotBackgroundColor: null,
                        plotBorderWidth: null,
                        plotShadow: false
                    },
                    title: {
                        text: '广场店铺销售额'
                    },
                    tooltip: {
                        pointFormat: '{series.name}-{point.y}: <b>{point.percentage:.1f}%</b>'
                    },
                    plotOptions: {
                        pie: {
                            allowPointSelect: true,
                            showInLegend: true,
                            cursor: 'pointer',
                            dataLabels: {
                                enabled: true,
                                format: '<b>{point.name}</b>:{point.percentage:.1f} %',
                                style: {
                                    color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
                                },
                                connectorColor: 'silver'
                            }
                        }
                    },
                    series: [{
                        type: 'pie',
                        name: '广场店铺销售额',
                        data: []
                    }]
                });

                var chart2 = new Highcharts.Chart('main2', {
                    chart: {
                        type: 'column'
                    },
                    title: {
                        text: '广场店铺销售额'
                    },
                    xAxis: {
                        type: 'category'
                    },
                    yAxis: {
                        title: {
                            text: '销售额'
                        }
                    },
                    legend: {
                        enabled: false
                    },
                    plotOptions: {
                        series: {
                            borderWidth: 0,
                            dataLabels: {
                                enabled: true,
                                format: '{point.value}元:{point.y:.1f}%'
                            }
                        }
                    },
                    tooltip: {
                        headerFormat: '<span style="font-size:11px">{series.name}</span><br>',
                        pointFormat: '<span style="color:{point.color}">{point.name}</span>: <b>{point.y:.2f}%</b> of total<br/>'
                    },
                    series: []
                });

                ajax.get("${ctx}/storSale/pie", {
                    plazaId: $("#plazaId").find("option:selected").val(),
                    creatTmBegin: creatTmBegin,
                    creatTmEnd: creatTmEnd
                }, function (data) {
                    var _DATA = [];
                    $.extend(_DATA, data.body.seriesDate);
                    if (_DATA.length == 0) {
                        alert("暂无数据！");
                        return
                    }

                    //1
                    var DATA_ = new Array(_DATA.length);
                    for (var i = 0, len = _DATA.length; i < len; i++) {
                        DATA_[i] = [_DATA[i].name, _DATA[i].value];
                    }
                    var series1 = {
                        type: 'pie',
                        name: '广场店铺销售额',
                        data: DATA_
                    }
                    chart1.setTitle("测试", null, true);
                    chart1.addSeries(series1);
                    chart1.redraw();


                    //2
                    var total = 0;
                    for (var i = 0, len = _DATA.length; i < len; i++) {
                        var value = _DATA[i].value;

                        total += parseInt(value);
                    }
                    for (var i = 0, len = _DATA.length; i < len; i++) {
                        var name = _DATA[i].name;
                        var value = _DATA[i].value;

                        //delete _DATA[i].value;
                        _DATA[i].y = (parseInt(value) / total) * 100;
                    }
                    //alert(JSON.stringify(_DATA));

                    var series2 = {
                        name: '广场店铺',
                        colorByPoint: true,
                        data: _DATA
                    }

                    chart2.addSeries(series2);
                    chart2.redraw();
                });
            }

            $("#search").on("click", function () {
                init();
            });
        });
    </script>
</head>
<body class="gray-bg">
<form id="ec" action="${ctx }/role" method="post" class="form-horizontal">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">

                        <div class="row m-b-sm m-t-sm">
                            <div class="col-md-1">
                                <button type="button" id="loading-example-btn"
                                        onclick="javascript:window.location.reload(true);" class="btn btn-white btn-sm">
                                    <i class="fa fa-refresh"></i> 刷新
                                </button>
                            </div>
                            <div class="col-md-5">
                                <select id="plazaId" name="plazaId" class="form-control">
                                    <option value="">选择广场编号</option>
                                    <c:forEach items="${plazaDataList}" var="item">
                                        <option value="${item.orgId}">${item.name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                            <div class="col-md-5">
                                <input name="creatTmBegin" id="creatTmBegin" class="searchInput Wdate"
                                       placeholder="交易时间开始" type="text" value=""
                                       onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'creatTmEnd\')||\'2050-10-01\'}'})"
                                       readonly="readonly"/>
                                至
                                <input name="creatTmEnd" id="creatTmEnd" class="searchInput Wdate" placeholder="交易时间结束"
                                       type="text" value=""
                                       onFocus="WdatePicker({minDate:'#F{$dp.$D(\'creatTmBegin\')}',maxDate:'2050-10-01'})"
                                       readonly="readonly"/>
                            </div>
                            <div class="col-md-1" style="text-align:right;">
                                <div class="input-group">
                                    <span class="input-group-btn"><button type="button" id="search"
                                                                          class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>

                        <div class="row m-b-sm m-t-sm">
                            <div id="main1"
                                 style="width: 45%;height:450px;float:left;border: 0px solid red;margin-left: 30px;"></div>

                            <div id="main2"
                                 style="width: 45%;height:450px;float:right;border: 0px solid red;margin-right: 30px;"></div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
