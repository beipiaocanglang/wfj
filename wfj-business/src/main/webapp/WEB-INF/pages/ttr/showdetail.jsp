<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>汇总表三级查询</title>
    <%@ include file="../common/meta.jsp" %>
    <script type="text/javascript">
        $(function () {
            $('#search_btn').on('click', function (evt) {
                var e = evt || window.event;
                e.preventDefault();
                var start = $('input[name="s_amount"]').val();
                var end = $('input[name="s_order_number"]').val();

                if ((!!start || !!end)) {
                    $('#ec').submit();
                }
                if (!!!start && !!!end) {
                    $('#ec').submit();
                }
            });
            $('#search_clear').on('click', function (evt) {
                var e = evt || window.event;
                e.preventDefault();
                $('input[name="s_amount"]').val('');
                $('input[name="s_order_number"]').val('');
                $('#ec').submit();
            });

            ///////导出相关/////////////////////////////////////////
            $("#export").on("click", function (evt) {
                var e = evt || window.event;
                e.preventDefault();
                var innerData = {
                    s_amount: $('#amount').val(),
                    s_order_number: $('#order_number').val(),
                    s_transId: $('#transId').val()
                };
                window.open('${ctx}/ttr/export/exporThreeLevelExcel' + RequestObjectSerializer(innerData));
            });
        });
    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <form id="ec" action="${ctx }/ttr/showDetailIndex" method="post" class="form-horizontal">
                    <input type="hidden" id="transId" name="s_transId" value="${transId}" style="display: none">
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm" style="border:solid red 0px">
                            <div class="col-md-1">
                                <button type="button" id="loading-example-btn"
                                        onclick="javascript:window.location.reload(true);" class="btn btn-white btn-sm">
                                    <i class="fa fa-refresh"></i> 刷新
                                </button>
                            </div>
                            <!-- 搜索条件 start-->
                            <div class="col-md-4">
                                <input type="text" class="searchInput " name="s_amount" id="amount"
                                       value="${pageRequest.filters.amount}" placeholder="请输入金额">
                                <input type="text" class="searchInput " name="s_order_number" id="order_number"
                                       value="${pageRequest.filters.order_number}" placeholder="请输入订单号">
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
                            <th>卡号</th>
                            <th>面值</th>
                            <th>顾客手机号</th>
                            </thead>
                            <tbody>
                            <c:forEach items="${list}" var="item" varStatus="st">
                                <tr>
                                    <td>${item.cardCode}</td>
                                    <td>${item.price}</td>
                                    <td>${item.phoneNo}</td>
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