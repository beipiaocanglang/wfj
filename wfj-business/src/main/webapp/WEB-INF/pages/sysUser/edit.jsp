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
                    uname: {
                        required: true,
                        rangelength: [3, 15]
                    },
                    tel: {
                        isPhone: true
                    },
                    email: {
                        email: true
                    },
                    rname: "required"
                },
                submitHandler: function (form) {
                    $(form).find(":submit").attr("disabled", true).text("正在提交...");
                    form.submit();
                }
            });

            $("#uname").bind("blur", function () {
                var uname = $("#uname").val();

                if (uname == '') return;

                var oldUserName = "${sysUser.uname }";
                if (oldUserName == subAccount) {
                    return;
                }

                $("#unameLabel").text("");
                $.post("${ctx}/sysUser/checkUserName", {
                    uname: uname
                }, function (data) {
                    if (data.status != 200) {
                        $("#unameLabel").text(data.message);
                        alert(data.message);
                        $("#uname").val(oldUserName);
                    }
                });
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
                    <form id="ec" action="${ctx }/sysUser/update/${sysUser.id}" method="post" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">真实姓名：</label>
                            <div class="col-sm-10">
                                <input type="text" name="rname" id="rname" value="${sysUser.rname }"
                                       class="form-control"/>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">用户账号：</label>
                            <div class="col-sm-10">
                                <input type="text" name="uname" id="uname" value="${sysUser.uname }"
                                       class="form-control"/>
                                <label for="uname" class="error" id="unameLabel"></label>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">职位：</label>
                            <div class="col-sm-10">
                                <input type="text" name="jobName" id="jobName" value="${sysUser.jobName }"
                                       class="form-control"/>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">所属部门：</label>
                            <div class="col-sm-10">
                                <input type="text" name="orgName" id="orgName" value="${sysUser.orgName }"
                                       class="form-control"/>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">手机号码：</label>
                            <div class="col-sm-10">
                                <input type="text" name="tel" id="tel" value="${sysUser.tel }" maxlength="11"
                                       class="form-control"/>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">邮箱：</label>
                            <div class="col-sm-10">
                                <input type="text" name="email" id="email" value="${sysUser.email }"
                                       class="form-control"/>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-primary" type="submit">保存内容</button>
                                <button class="btn btn-white" type="button" onclick="window.location='${ctx}/sysUser'"
                                        value="返回">返回
                                </button>
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
