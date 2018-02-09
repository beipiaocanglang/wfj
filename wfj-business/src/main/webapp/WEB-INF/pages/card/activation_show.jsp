<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>购卡激活详情查询</title>
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
                            <p class="susong_h1">购卡激活信息</p>
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="susong_tab01">
                                <tr>
                                    <td width="20%" align="right"><b>商户订单号：</b></td>
                                    <td>${cardActivation.orderId }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>交易订单号：</b></td>
                                    <td>${cardActivation.transId }</td>
                                </tr>
                                <tr>
                                    <td width="20%" align="right"><b>付款成功时间：</b></td>
                                    <td>${cardActivation.payFinishTime }</td>
                                </tr>
                                <tr>
                                    <td width="20%" align="right"><b>总价：</b></td>
                                    <td>${cardActivation.totalPrice }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>付款人openid：</b></td>
                                    <td>
                                        ${cardActivation.openid}
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right"><b>付款人unionid：</b></td>
                                    <td>${cardActivation.unionid }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>合作方用户id：</b></td>
                                    <td>${cardActivation.corpUserId }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>手机号：</b></td>
                                    <td>${cardActivation.phoneNo }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>是否群发：</b></td>
                                    <td>${cardActivation.isChatRoom }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>卡类型：</b></td>
                                    <td>${cardActivation.cardId }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>单价：</b></td>
                                    <td>${cardActivation.price }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>code：</b></td>
                                    <td>${cardActivation.cardCode }</td>
                                </tr>
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
