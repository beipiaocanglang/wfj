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
<form id="ec" action="${ctx }/srvr" method="post" class="form-horizontal">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-content">

                        <div class="row m-b-sm m-t-sm">
                            <div class="col-md-2">
                                <button type="button" id="loading-example-btn"
                                        onclick="javascript:window.location.reload(true);" class="btn btn-white btn-sm">
                                    <i class="fa fa-refresh"></i> 刷新
                                </button>
                                <button type="button" onclick="javascript:window.location='${ctx }/srvr/create'"
                                        class="btn btn-sm btn-primary"> 添加
                                </button>
                            </div>
                            <div class="col-md-3">
                                <input type="text" name="s_name" id="s_name" value="${pageRequest.filters.name }"
                                       placeholder="主机名称" class="input-sm form-control"/>
                            </div>
                            <div class="col-md-3">
                                <input type="text" name="s_ip" id="s_ip" value="${pageRequest.filters.ip }"
                                       placeholder="主机IP" class="input-sm form-control"/>
                            </div>
                            <div class="col-md-3">
                                <input type="text" name="s_sftwe" id="s_sftwe" value="${pageRequest.filters.sftwe }"
                                       placeholder="软件资源" class="input-sm form-control"/>
                            </div>


                            <div class="col-md-1" style="text-align:right;">
                                <div class="input-group">
                                    <span class="input-group-btn"><button type="submit" class="btn btn-sm btn-primary"> 搜索</button> </span>
                                </div>
                            </div>
                        </div>


                        <table
                                class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <tr>
                                <th>序号</th>
                                <th>主机名称</th>
                                <th>主机IP</th>
                                <th>软件资源</th>
                                <th>CPU</th>
                                <th>存储</th>
                                <th>内存</th>
                                <th>网卡</th>
                                <th>机架</th>
                                <th>环境</th>
                                <th>备注</th>
                                <th>操作</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.result}" var="item" varStatus="st">
                                <tr class="gradeX">
                                    <td>${st.count }</td>
                                    <td>${item.name }</td>
                                    <td>${item.ip}</td>
                                    <td>${item.sftwe}</td>
                                    <td>${item.cpu}</td>
                                    <td>${item.stor}</td>
                                    <td>${item.mem}</td>
                                    <td>${item.ntwkCrd}</td>
                                    <td>${item.fram}</td>
                                    <td>${item.envir}</td>
                                    <td>${item.rmrk}</td>
                                    <td>
                                        <a class="btn btn-primary btn-xs" href="${ctx }/srvr/edit/${item.id}">修改</a>
                                        <a class="btn btn-primary btn-xs"
                                           href="javascript:win.confirm('${ctx }/srvr/delete/${item.id}')">删除</a>
                                        <a class="btn btn-primary btn-xs" href="${ctx }/srvr/${item.id}">查看</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                            <tfoot>
                            <tr>
                                <th>序号</th>
                                <th>主机名称</th>
                                <th>主机IP</th>
                                <th>软件资源</th>
                                <th>CPU</th>
                                <th>存储</th>
                                <th>内存</th>
                                <th>网卡</th>
                                <th>机架</th>
                                <th>环境</th>
                                <th>备注</th>
                                <th>操作</th>
                            </tr>
                            </tfoot>
                        </table>

                        <%@ include file="../common/pagination.jsp" %>

                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>
