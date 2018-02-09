<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>消费核对二级报表</title>
    <%@ include file="../common/meta.jsp" %>
    <script type="text/javascript">
        $(function () {
            $('#search_clear').on('click', function (evt) {
                var e = evt || window.event;
                e.preventDefault();
                $('#posid').val('');
                $('#ec').submit();
            });
            ///////导出相关/////////////////////////////////////////
            $("#export").on("click", function (evt) {
                var e = evt || window.event;
                e.preventDefault();
                var innerData = {
                    s_posid: $('#posid').val(),
                    s_storename: $('#storename').val()
                };
                window.open('${ctx}/tca/export/exportSecondExcel' + RequestObjectSerializer(innerData));
            });
        });
    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <form id="ec" action="${ctx }/tca/showThreeDetail" method="post" class="form-horizontal">
                    <input type="hidden" name="s_storename" id="storename" value="${storename}" style="display: none">
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm" style="border:solid red 0px">
                            <div class="col-md-1">
                                <button type="button" id="loading-example-btn" onclick="javascript:window.location.reload(true);" class="btn btn-white btn-sm">
                                    <i class="fa fa-refresh"></i> 刷新
                                </button>
                            </div>
                            <!-- 搜索条件 start-->
                            <div class="col-md-4">
                                <input type="text" class="searchInput " name="s_posid" id="posid" value="${pageRequest.filters.posid }" placeholder="请输入pos机号">
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
                            <th>交易时间</th>
                            <th>POS机</th>
                            <th>交易流水号</th>
                            <th>门店电子卡消费金额</th>
                            <th>微众核销金额</th>
                            <th>是否平衡</th>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.result}" var="item" varStatus="st">
                                <td><fmt:formatDate value="${item.transtime }" pattern="yyyy-MM-dd" type="both"/></td>
                                <td>${item.posid }</td>
                                <td>${item.transid}</td>
                                <td>${item.consumeNum}</td>
                                <td>${item.amount}</td>
                                <td>${item.balance eq true?"是":"否"}</td>
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