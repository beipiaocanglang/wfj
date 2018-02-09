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
                    name: "required",
                    description: "required"
                },
                messages: {
                    name: "角色必须输入",
                    description: "描述必须输入"
                },
                submitHandler: function (form) {
                    $(form).find(":submit").attr("disabled", true).text("正在提交...");
                    form.submit();
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
                    <form id="ec" action="${ctx }/role/update/${role.id}" method="post" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">角色：</label>

                            <div class="col-sm-10">
                                <input type="text" name="name" id="name" value="${role.name}" class="form-control">
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">描述：</label>
                            <div class="col-sm-10">
                                <textarea name="description" id="description" class="form-control"
                                          style="width:100%;height: 150px;">${role.description}</textarea>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-primary" type="submit">保存内容</button>
                                <button class="btn btn-white" type="button" onclick="window.location='${ctx}/role'"
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
