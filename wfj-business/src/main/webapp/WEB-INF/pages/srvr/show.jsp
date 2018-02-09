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

                            <p class="susong_h1">服务器资源信息</p>
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" class="susong_tab01">

                                <tr>
                                    <td width="20%" align="right"><b>主机名称：</b></td>
                                    <td>${srvr.name }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>IP：</b></td>
                                    <td>${srvr.ip }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>软件资源：</b></td>
                                    <td>${srvr.sftwe }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>CPU：</b></td>
                                    <td>${srvr.cpu }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>存储：</b></td>
                                    <td>${srvr.stor }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>内存：</b></td>
                                    <td>${srvr.mem }</td>
                                </tr>
                                <tr>
                                    <td width="20%" align="right"><b>网卡：</b></td>
                                    <td>${srvr.ntwkCrd }</td>
                                </tr>
                                <tr>
                                    <td width="20%" align="right"><b>机架：</b></td>
                                    <td>${srvr.fram }</td>
                                </tr>
                                <tr>
                                    <td width="20%" align="right"><b>环境：</b></td>
                                    <td>${srvr.envir }</td>
                                </tr>
                                <tr>
                                    <td align="right"><b>备注：</b></td>
                                    <td>${srvr.rmrk }</td>
                                </tr>

                                <tr>
                                    <td align="right"><b>创建时间：</b></td>
                                    <td><fmt:formatDate value="${srvr.creatTm}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
                                </tr>
                                <tr>
                                    <td align="right"><b>修改时间：</b></td>
                                    <td><fmt:formatDate value="${srvr.updtTm}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
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
