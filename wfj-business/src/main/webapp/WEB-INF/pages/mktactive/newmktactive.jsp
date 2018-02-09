<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>新建营销活动</title>
    <%@ include file="../common/meta.jsp" %>
    <link rel="stylesheet" href="${ctx }/assets/css/newmktactive.css">
</head>
<body>
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="ibox float-e-margins">
            <div class="tabtoggle">
                <ul class="nav nav-tabs" id="myTab">
                    <li role="presentation" class="active"><a href="#home">活动计划</a></li>
                    <li role="presentation"><a href="#profile">种子会员</a></li>
                    <li role="presentation"><a href="#messages">异类品牌</a></li>
                    <li role="presentation"><a href="#settings">异类会员</a></li>
                    <li role="presentation"><a href="#settingss">选择渠道</a></li>
                </ul>
                <div class="tab-content">
                    <%--活动计划--%>
                    <%@ include file="./mktactive_plan.jsp" %>
                    <%--种子会员--%>
                    <%@ include file="./mktactive_multitude.jsp"%>
                    <%--异类品牌--%>
                    <%@ include file="./mktactive_label.jsp" %>
                    <%--异类会员--%>
                    <%@ include file="./mktactive_channel.jsp" %>
                    <%--选择渠道--%>
                    <%@ include file="./mktactive_analysis.jsp" %>

                </div>
            </div>
        </div>
    </div>
</div>
<script src="${ctx }/assets/js/newmktactive.js"></script>
</body>
</html>
