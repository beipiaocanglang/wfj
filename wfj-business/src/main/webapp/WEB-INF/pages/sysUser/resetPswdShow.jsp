<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@ include file="../common/meta.jsp" %>
    <script type="text/javascript">
        $(function () {
            $('#ec').validate({
                rules: {
                    newPswd: {
                        required: true,
                        rangelength: [6, 12]
                    },
                    surePswd: {
                        required: true,
                        equalTo: "#newPswd"
                    }
                },
                messages: {
                    newPswd: {
                        required: "新密码必须输入！",
                        rangelength: "新密码必须在6位到12位之间！"
                    },
                    surePswd: {
                        required: "确认密码必须输入！",
                        equalTo: "新密码与确认密码不相等！"
                    }
                }
            });
        });
    </script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">

    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <form id="ec" action="${ctx }/sysUser/resetPswd" method="post" class="form-horizontal">
                        <input type="hidden" name="userId" value="${userId}"/>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">新密码：</label>
                            <div class="col-sm-10">
                                <input type="password" name="newPswd" id="newPswd" maxlength="15" class="form-control"/>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">确认密码：</label>
                            <div class="col-sm-10">
                                <input type="password" name="surePswd" id="surePswd" maxlength="15"
                                       class="form-control"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-primary" type="submit">保存内容</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
