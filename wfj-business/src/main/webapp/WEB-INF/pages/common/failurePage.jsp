<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@ include file="../common/meta.jsp" %>
    <style type="text/css">
    </style>
    <script type="text/javascript">
        function goBack() {
            try {
                if (parent && parent.CurrMgr && parent.CurrMgr.operateWindow && parent.CurrMgr.operateWindow.dialog) {
                    parent.CurrMgr.Contants.operateSuccess = true;
                    parent.CurrMgr.closeWindow();
                } else if (parent && parent.window.dialogArguments) {
                    parent.window.close();
                } else if (window.opener) {
                    window.close();
                } else {
                    history.go(-1);
                }
            } catch (err) {
            }
        }
    </script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <div class="form-group">
                        <div class="col-sm-10">
                            错误提示：<c:out value="${messagePage}"/>
                        </div>
                    </div>
                    <div class="hr-line-dashed"></div>
                    <div class="form-group">
                        <div class="col-sm-4 col-sm-offset-2">
                            <button class="btn btn-primary" type="button" onclick="goBack()">返 回</button>
                            <button class="btn btn-primary" type="button" onclick="opt.closeWin();">关闭</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
