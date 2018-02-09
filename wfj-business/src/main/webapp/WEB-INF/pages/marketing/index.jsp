<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>营销活动</title>
    <%@ include file="../common/meta.jsp" %>
    <link rel="stylesheet" href="${ctx }/assets/css/newactivity.css">
    <style>
        .red {
            border-color: red;
            color: red;
        }
    </style>
    <script type="text/javascript">

    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <form id="ec" action="" method="post" class="form-horizontal">
                    <div class="ibox-content" style="padding: 30px 35px">
                        <div class="row m-b-sm m-t-sm" style="border:solid red 0px">
                            <!-- 搜索条件 start-->
                            <div class="marketing-search-bj">
                                <div class="col-md-3 col-lg-3">
                                    <%--<span>活动主题：</span>--%>
                                    <input style="background: #fff" name="s_theme" id="theme" value="${pageRequest.filters.theme}"
                                           class="macrke-searchInput macrke-Wdate" placeholder="请输入活动主题" type="text"/>
                                </div>
                                <div class="col-md-5 col-lg-5">
                                    <%--<span>活动开始时间：</span>--%>
                                    <div>
                                        <input name="s_startDate" id="creatTmBegin" value="${pageRequest.filters.startDate }"
                                               class="macrkesearchInput macrke-Wdate" placeholder="请选择活动开始时间" type="text"
                                               onFocus="WdatePicker({minDate:'#F{$dp.$D(\'endTmBegins\')}',maxDate:'2050-10-01'})"
                                               readonly="readonly"/>
                                        至
                                        <input name="s_endDate" id="endTmBegins" value="${pageRequest.filters.endDate }"
                                               class="macrkesearchInput macrke-Wdate" placeholder="请选择活动结束时间" type="text"
                                               onFocus="WdatePicker({minDate:'#F{$dp.$D(\'creatTmBegin\')}',maxDate:'2050-10-01'})"
                                               readonly="readonly"/>
                                    </div>
                                    <%--<span>活动结束时间：</span>--%>

                                </div>
                                <div class="col-md-3 col-lg-3 creatime">
                                    <%--<span>创建时间：</span>--%>
                                    <input name="s_creatTime" id="creatTime" value="${pageRequest.filters.creatTime}"
                                           class="macrkesearchInput macrke-Wdate" placeholder="请选择创建时间" type="text"
                                           onFocus="WdatePicker({minDate:'#F{$dp.$D(\'creatTime\')}',maxDate:'2050-10-01'})"
                                           readonly="readonly"/>
                                </div>
                                <div class="col-md-1 searchbtn">
                                    <button type="submit" id="search_btn" class="btn btn-sm btn-primary"> 搜索</button>
                                </div>
                            </div>
                            <div class="creatmarketing col-md-12">
                                <a class="btn btn-primary creatbtn" href="${ctx}/mktactive/plan/newActivePlan" role="button">新建活动 new</a>
                                <a class="btn btn-primary creatbtn" href="${ctx}/marketing/newactivity" role="button">新建活动 old</a>
                            </div>
                            <!-- 搜索条件 end-->
                        </div>
                        <div class="table-responsive">
                            <table class="table table-striped table-hover dataTables-example table-response">
                                <thead>
                                    <th>活动主题</th>
                                    <th>活动时间</th>
                                    <th>活动KPI</th>
                                    <th>活动状态</th>
                                    <th>KPI是否达标</th>
                                    <th>投放渠道</th>
                                    <th>数据创建时间</th>
                                    <th>操作</th>
                                </thead>
                                <tbody>
                                    <c:forEach items="${page.result}" var="item" varStatus="st">
                                        <tr>
                                            <td>${item.theme}</td>
                                            <td>
                                                <fmt:formatDate value="${item.startTime}" pattern="yyyy-MM-dd" type="both"/>
                                                ~
                                                <fmt:formatDate value="${item.endTime}" pattern="yyyy-MM-dd" type="both"/>
                                            </td>
                                            <td>${item.activeKpi}</td>
                                            <td>${item.status}</td>
                                            <td>${item.upToStandard}</td>
                                            <td>${item.channelDesc}</td>
                                            <td><fmt:formatDate value="${item.createTime}" pattern="yyyy-MM-dd" type="both"/></td>
                                            <td>
                                                <a class="editcolor" href="${ctx}/marketing/editndex?actId=${item.actId}">编辑</a>
                                                <%--<a class="editcolor" href="javascript:void(0)">效果分析</a>--%>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>
                        <%@ include file="../common/pagination.jsp" %>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>
