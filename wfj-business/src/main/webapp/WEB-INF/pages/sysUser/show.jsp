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
<div class="row">
    <div class="col-sm-12">
        <div class="wrapper wrapper-content animated fadeInUp">
            <div class="ibox">
                <div class="ibox-content">
                    <div class="row m-t-sm">
                        <div class="col-sm-12">

                            <p class="susong_h1">用户信息</p>
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="susong_tab01">
                                <tr>
                                    <td width="20%" align="right"><b>姓名：</b></td>
                                    <td>${sysUser.rname }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>部门：</b></td>
                                    <td>${sysUser.orgName }</td>
                                </tr>
                                <tr>
                                    <td width="20%" align="right"><b>职位：</b></td>
                                    <td>${sysUser.jobName }</td>
                                </tr>
                                <tr>
                                    <td width="20%" align="right"><b>账号：</b></td>
                                    <td>${sysUser.uname }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>手机：</b></td>
                                    <td>
                                        ${sysUser.tel}
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right"><b>邮箱：</b></td>
                                    <td>${sysUser.email }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>创建时间：</b></td>
                                    <td><fmt:formatDate value="${sysUser.creatTm}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
