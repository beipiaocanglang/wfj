<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>消费核对二级报表</title>
    <%@ include file="../common/meta.jsp" %>
    <script type="text/javascript">
        $(function () {
            ///////导出相关/////////////////////////////////////////
            $("#exportDetail").on("click", function (evt) {
                var e = evt || window.event;
                e.preventDefault();
                window.open('${ctx}/report/export/detail');
                exporBtn();
            });

        });
    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <form id="ec" action="${ctx }/tca/showindex" method="post" class="form-horizontal">
                    <input type="hidden" name="s_transtime" id="transtime" value="${transtime}" style="display: none">
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm" style="border:solid red 0px">
                            <div class="col-md-1">
                                <button type="button" id="loading-example-btn" onclick="javascript:window.location.reload(true);" class="btn btn-white btn-sm">
                                    <i class="fa fa-refresh"></i> 刷新
                                </button>
                            </div>
                        </div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <th>门店</th>
                            <th>微众核销数</th>
                            <th>门店消费数(待定)</th>
                            <th>差额</th>
                            <th>详情</th>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.result}" var="item" varStatus="st">
                                <td>${item.storename }</td>
                                <td>${item.amount}</td>
                                <td>${item.consumeNum}</td>
                                <td>${item.amount}</td>
                                <td>
                                    <a class="btn btn-primary btn-xs pull-right" href="${ctx }/tca/showdetail/${item.storename}">查看详情</a>
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