<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>订单查询详情</title>
    <%@ include file="../common/meta.jsp" %>

    <script type="text/javascript">
        $(function () {

        });
    </script>
</head>
<body class="gray-bg">
<div class="row">
    <div class="col-sm-12">
        <div class="wrapper wrapper-content animated fadeInUp">
            <div class="ibox">
                <div class="ibox-content">
                    <div class="row m-t-sm">
                        <div class="col-sm-12">
                            <p class="susong_h1">订单明细查看</p>
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="susong_tab01">
                                <c:forEach items="${cardDetailList}" var="item" varStatus="st">
                                    <c:if test="${st.count == 1}">
                                        <tr>
                                            <td width="20%" align="right"><b>余额：</b></td>
                                            <td>${item.balance }</td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>购买时间：</b></td>
                                            <td>${item.saleTime }</td>
                                        </tr>
                                        <tr>
                                            <td width="20%" align="right"><b>卡code状态：</b></td>
                                            <td>${item.codeStatus }</td>
                                        </tr>
                                        <tr>
                                            <td width="20%" align="right"><b>卡code：</b></td>
                                            <td>${item.cardCode }</td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>过期时间：</b></td>
                                            <td>${item.expire}</td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>返回对象：</b></td>
                                            <td>OBJECT</td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>使用门店：</b></td>
                                            <td>${item.storeName }</td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>消费日期：</b></td>
                                            <td>${item.useTime }</td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>消费金额：</b></td>
                                            <td>${item.useSum }</td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>交易类型：</b></td>
                                            <td>${item.refundType }</td>
                                        </tr>
                                        <tr>
                                            <td align="right"><b>商户交易订单号：</b></td>
                                            <td>${item.useGlideNo }</td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </table>
                            <br/>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-primary" type="button" onclick="javascript:history.back(-1);"
                                        value="返回">返回
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
