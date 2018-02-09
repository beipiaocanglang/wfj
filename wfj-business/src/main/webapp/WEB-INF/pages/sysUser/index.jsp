<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
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

                <form id="ec" action="${ctx }/sysUser" method="post" class="form-horizontal">
                    <div class="ibox-content">

                        <div class="row m-b-sm m-t-sm" style="border:solid red 0px">
                            <div class="col-md-2">
                                <button type="button" id="loading-example-btn"
                                        onclick="javascript:window.location.reload(true);" class="btn btn-white btn-sm">
                                    <i class="fa fa-refresh"></i> 刷新
                                </button>
                                <button type="button" onclick="javascript:window.location='${ctx }/sysUser/create'"
                                        class="btn btn-sm btn-primary" style="margin-left: 10px;"> 添加
                                </button>
                            </div>
                            <div class="col-md-2">
                                <input type="text" name="s_rname" id="s_rname" value="${pageRequest.filters.rname }"
                                       placeholder="姓名" class="input-sm form-control"/>
                            </div>
                            <div class="col-md-2">
                                <input type="text" name="s_uname" id="s_uname" value="${pageRequest.filters.uname }"
                                       placeholder="用户账号" class="input-sm form-control"/>
                            </div>
                            <div class="col-md-2">
                                <input type="text" name="s_tel" id="s_tel" value="${pageRequest.filters.tel }"
                                       placeholder="手机" class="input-sm form-control"/>
                            </div>
                            <div class="col-md-3">
                                <input type="text" name="s_email" id="s_email" value="${pageRequest.filters.email }"
                                       placeholder="邮箱" class="input-sm form-control"/>
                            </div>

                            <div class="col-md-1" style="text-align:right;">
                                <button type="submit" class="btn btn-sm btn-primary"> 搜索</button>
                            </div>
                        </div>

                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>真实姓名</th>
                                <th>用户账号</th>
                                <th>职位</th>
                                <th>所属部门</th>
                                <th>手机号</th>
                                <th>邮箱</th>
                                <th>加入时间</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.result}" var="item" varStatus="st">
                                <tr class="gradeC">
                                    <td>${st.count }</td>
                                    <td>${item.rname }</td>
                                    <td>${item.uname }</td>
                                    <td>${item.jobName }</td>
                                    <td>${item.orgName }</td>
                                    <td>${item.tel }</td>
                                    <td>${item.email }</td>
                                    <td><fmt:formatDate value="${item.creatTm}" pattern="yyyy-MM-dd HH:mm:ss"/></td>

                                    <td>
                                        <a class="btn btn-primary btn-xs" href="${ctx }/sysUser/${item.id}">查看</a>
                                        <a class="btn btn-primary btn-xs" href="${ctx }/sysUser/edit/${item.id}">修改</a>

                                        <a class="btn btn-primary btn-xs"
                                           href="javascript:opt.confirm('${ctx }/sysUser/delete/${item.id}')">删除</a>

                                        <a class="btn btn-primary btn-xs"
                                           href="javascript:opt.openWin('${ctx }/org?userId=${item.id}','组织结构',380,480)">组织结构</a>

                                        <a class="btn btn-primary btn-xs"
                                           href="javascript:opt.openWin('${ctx }/sysUser/assignShow/${item.id}','分配角色',630,515)">分配角色</a>

                                        <a class="btn btn-primary btn-xs"
                                           href="javascript:opt.openWin('${ctx }/sysUser/resetPswd?userId=${item.id}','修改密码',600,420)">修改密码</a>
                                        <!--
										<a class="btn btn-primary btn-xs" href="${ctx }/org?userId=${item.id}">组织结构</a>
										<a class="btn btn-primary btn-xs" href="javascript:win.openWin('${ctx }/org?userId=${item.id}','组织结构',380,530)">组织结构</a>
										<a class="btn btn-primary btn-xs" href="${ctx }/sysUser/roleShow/${item.id}">角色</a>
										-->
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th>序号</th>
                                <th>真实姓名</th>
                                <th>用户账号</th>
                                <th>职位</th>
                                <th>所属部门</th>
                                <th>手机号</th>
                                <th>邮箱</th>
                                <th>加入时间</th>
                                <th>操作</th>
                            </tr>
                            </tfoot>
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
