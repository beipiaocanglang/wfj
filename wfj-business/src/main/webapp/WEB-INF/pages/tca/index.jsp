<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>消费核对报表</title>
    <%@ include file="../common/meta.jsp" %>
    <style>
        .red {
            border-color: red;
            color: red;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $('#search_btn').on('click', function (evt) {
                var e = evt || window.event;
                e.preventDefault();
                var start = $('input[name="s_startDate"]').val();
                var end = $('input[name="s_endDate"]').val();
                if (!!start && !!!end) {
                    layer.msg('请填写交易结束时间');
                    return false;
                }
                if (!!!start && !!end) {
                    layer.msg('请填写交易开始时间');
                    return false;
                }
                if ((!!!start && !!!end) || (!!start && !!end)) {
                    $('#ec').submit();
                }
                if (!!!start && !!!end) {
                    $('#ec').submit();
                }

            });
            $('#search_clear').on('click', function (evt) {
                var e = evt || window.event;
                e.preventDefault();
                $('input[name="s_startDate"]').val('');
                $('input[name="s_endDate"]').val('');
                $('#ec').submit();
            });

            ///////导出相关/////////////////////////////////////////
            $("#export").on("click", function (evt) {
                var e = evt || window.event;
                e.preventDefault();
                var innerData = {
                    s_startDate: $('#creatTmBegin').val(),
                    s_endDate: $('#creatTmEnd').val()
                };
                window.open('${ctx}/tca/export/exportIndexExcel'+RequestObjectSerializer(innerData));
            });
        });
    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <form id="ec" action="${ctx }/tca" method="post" class="form-horizontal">
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm" style="border:solid red 0px">
                            <div class="col-md-1">
                                <button type="button" id="loading-example-btn" onclick="javascript:window.location.reload(true);" class="btn btn-white btn-sm">
                                    <i class="fa fa-refresh"></i> 刷新
                                </button>
                            </div>
                            <!-- 搜索条件 start-->

                            <div class="col-md-4">
                                <input name="s_startDate" id="creatTmBegin" value="${pageRequest.filters.startDate }"
                                       class="searchInput Wdate" placeholder="交易开始时间" type="text"
                                       onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'creatTmEnd\')||\'2050-10-01\'}'})"
                                       readonly="readonly"/>
                                至
                                <input name="s_endDate" id="creatTmEnd" value="${pageRequest.filters.endDate }"
                                       class="searchInput Wdate" placeholder="交易结束时间" type="text"
                                       onFocus="WdatePicker({minDate:'#F{$dp.$D(\'creatTmBegin\')}',maxDate:'2050-10-01'})"
                                       readonly="readonly"/>
                            </div>

                            <div class="col-md-1" style="text-align:right;">
                                <button type="submit" id="search_btn" class="btn btn-sm btn-primary"> 搜索</button>
                            </div>
                            <div class="col-md-1" style="text-align:right;">
                                <button type="submit" id="search_clear" class="btn btn-sm btn-primary">清空</button>
                            </div>
                            <div class="col-md-1" style="text-align:right;">
                                <button type="button" id="export" class="btn btn-sm btn-primary">导出</button>
                            </div>
                            <!-- 搜索条件 end-->
                        </div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <th>日期</th>
                                <th>微众银行核销数</th>
                                <th>富基系统消费数(待定)</th>
                                <th>是否平衡</th>
                                <th>详情</th>
                            </thead>
                            <tbody>
                                <c:forEach items="${page.result}" var="item" varStatus="st">
                                    <tr class="gradeC">
                                        <td><fmt:formatDate value="${item.transtime }" pattern="yyyy-MM-dd" type="both"/></td>
                                        <td>${item.money}</td>
                                        <td>${item.consumeNum}</td>
                                        <td>${item.balance eq true?"是":"否"}</td>
                                        <td>
                                            <a class="btn btn-primary btn-xs pull-right" href="${ctx }/tca/show/${item.time}">查看详情</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                        <%@ include file="../common/pagination.jsp" %>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>