<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>汇总表二级查询</title>
    <%@ include file="../common/meta.jsp" %>
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
                $('#creatTmBegin').val('');
                $('#creatTmEnd').val('');
                $('#ec').submit();
            });

            ///////导出相关/////////////////////////////////////////
            $("#export").on("click", function (evt) {
                var e = evt || window.event;
                e.preventDefault();
                var innerData = {
                    s_startDate: $('#creatTmBegin').val(),
                    s_endDate: $('#creatTmEnd').val(),
                    s_payFinishTime: $('#payFinishTime').val()
                };
                window.open('${ctx}/ttr/export/exporSecondLevelExcel'+RequestObjectSerializer(innerData));
            });
        });
    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <form id="ec" action="${ctx }/ttr/showindex" method="post" class="form-horizontal">
                    <input type="hidden" name="s_payFinishTime" id="payFinishTime" value="${payFinishTime}" style="display: none">
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm" style="border:solid red 0px">
                            <div class="col-md-1">
                                <button type="button" id="loading-example-btn" onclick="javascript:window.location.reload(true);" class="btn btn-white btn-sm">
                                    <i class="fa fa-refresh"></i> 刷新
                                </button>
                            </div>
                            <!-- 搜索条件 start-->
                            <%--<div class="col-md-4">
                                <input type="text" class="searchInput " name="s_startPosid" id="startPosid"
                                       value="${pageRequest.filters.startPosid }" placeholder="请输入收款机号">
                            </div>--%>
                            <div class="col-md-4">
                                <input name="s_startDate" id="creatTmBegin" class="searchInput Wdate"
                                       placeholder="交易开始时间" type="text" value="${pageRequest.filters.startDate }"
                                       onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'creatTmEnd\')||\'2050-10-01\'}'})"
                                       readonly="readonly"/>
                                至
                                <input name="s_endDate" id="creatTmEnd" class="searchInput Wdate" placeholder="交易结束时间"
                                       type="text" value="${pageRequest.filters.endDate }"
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
                                <th>销售日期</th>
                                <th>订单号</th>
                                <th>订单金额</th>
                                <th>顾客手机号</th>
                                <th>是否生成发票</th>
                                <th>详情</th>
                            </thead>
                            <tbody>
                                <c:forEach items="${list}" var="item" varStatus="st">
                                    <tr>
                                        <td>${item.payFinishTime}</td>
                                        <td>${item.transId}</td>
                                        <td>${item.price}</td>
                                        <td>${item.phoneNo}</td>
                                        <td>${item.isMakeInvoice}</td><%--待定--%>
                                        <td>
                                            <a class="btn btn-primary btn-xs pull-right" href="${ctx}/ttr/showDetail/${item.transId}">查看详情</a>
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