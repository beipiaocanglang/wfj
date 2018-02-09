<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>激活卡交易明细查询</title>
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
                <form id="ec" action="${ctx }/card/activation" method="post" class="form-horizontal">
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
                                <input type="text" name="s_code" id="s_code"
                                       value="${pageRequest.filters.transId }"
                                       placeholder="请输入卡号" class="input-sm form-control"/>
                            </div>
                            <div class="col-md-1" style="text-align:right;">
                                <button type="submit" class="btn btn-sm btn-primary"> 搜索</button>
                            </div>
                        </div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>商户订单号</th>
                                    <th>卡 code</th>
                                    <th>卡 code 状态</th>
                                    <th>余额</th>
                                    <th>购买时间</th>
                                    <th>过期时间</th>
                                    <th>使用门店</th>
                                    <th>消费金额</th>
                                    <th>消费日期</th>
                                </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.result}" var="item" varStatus="st">
                                <tr class="gradeC">
                                    <td>${st.count }</td>
                                    <td>${item.orderId }</td>
                                    <td>${item.transId }</td>
                                    <%-- <td><fmt:formatDate value="${item.payFinishTime}" pattern="yyyy-MM-dd HH:mm:ss"/> </td> --%>
                                    <td>${item.payFinishTime }</td>
                                    <td>${item.totalPrice }</td>
                                    <td>${item.openid }</td>
                                    <td>${item.unionid }</td>
                                    <td>${item.corpUserId }</td>
                                    <td>${item.phoneNo }</td>
                                    <td>${item.isChatRoom }</td>
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
