<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>订单查询</title>
    <%@ include file="../common/meta.jsp" %>

    <script type="text/javascript">
        $(function () {

        });
    </script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">

                <form id="ec" action="${ctx }/card/order" method="post" class="form-horizontal">
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm" style="border:solid red 0px">
                            <div class="col-md-2">
                                <button type="button" id="loading-example-btn"
                                        onclick="javascript:window.location.reload(true);"
                                        class="btn btn-white btn-sm">
                                    <i class="fa fa-refresh"></i> 刷新
                                </button>
                            </div>
                            <div class="col-md-3">
                                <input type="text" name="s_order_no" id="s_order_no"
                                       value="${pageRequest.filters.order_no }"
                                       placeholder="商户订单号"
                                       class="input-sm form-control"/>
                            </div>
                            <div class="col-md-1" style="text-align:right;">
                                <button type="submit" class="btn btn-sm btn-primary">搜索</button>
                            </div>
                        </div>

                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>交易人openid</th>
                                    <th>余额</th>
                                    <th>卡名称</th>
                                    <th>卡类型 Id</th>
                                    <th>卡 code</th>
                                    <th>操作</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${cardOrderList}" var="item" varStatus="st">
                                <tr class="gradeC">
                                    <td>${st.count }</td>
                                    <td>${item.openid }</td>
                                    <td>${item.balance }</td>
                                    <td>${item.cardName }</td>
                                    <td>${item.cardId }</td>
                                    <td>${item.cardCode }</td>
                                    <td>
                                        <a class="btn btn-primary btn-xs"
                                           href="${ctx }/card/detail?s_openid=${item.openid}&s_cardId=${item.cardId}&s_cardCode=${item.cardCode}">
                                           订单明细查看
                                        </a>
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
