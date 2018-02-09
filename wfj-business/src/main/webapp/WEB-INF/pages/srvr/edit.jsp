<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@ include file="../common/meta.jsp" %>
    <script type="text/javascript">
        $(function () {          ////-- id, name, ip, sftwe, cpu, stor, mem, ntwk_crd, fram, envir, rmrk, creat_tm, updt_tm, enab_flag
            $('#ec').validate({
                rules: {
                    name: "required",
                    ip: "required",
                    sftwe: "required",
                    cpu: "required",
                    stor: "required",
                    mem: "required",
                    ntwkCrd: "required",
                    fram: "required",
                    envir: "required",
                    rmrk: "required"
                },
                messages: {
                    name: "主机名必须输入",
                    ip: "主机IP必须输入",
                    sftwe: "软件资源必须输入",
                    cpu: "cpu必须输入",
                    stor: "存储必须输入",
                    mem: "内存必须输入",
                    ntwkCrd: "网卡必须输入",
                    fram: "机架必须输入",
                    envir: "环境必须输入",
                    rmrk: "备注必须输入"
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
                    <form id="ec" action="${ctx }/srvr/update/${srvr.id}" method="post" class="form-horizontal">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">主机名：</label>

                            <div class="col-sm-10">
                                <input type="text" name="name" id="name" value="${srvr.name}" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">主机IP：</label>
                            <div class="col-sm-10">
                                <input type="text" name="ip" id="ip" value="${srvr.ip}" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">软件资源：</label>
                            <div class="col-sm-10">
                                <input type="text" name="sftwe" id="sftwe" value="${srvr.sftwe}" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">CPU：</label>
                            <div class="col-sm-10">
                                <input type="text" name="cpu" id="cpu" value="${srvr.cpu}" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">存储：</label>
                            <div class="col-sm-10">
                                <input type="text" name="stor" id="stor" value="${srvr.stor}" class="form-control">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="col-sm-2 control-label">内存：</label>
                            <div class="col-sm-10">
                                <input type="text" name="mem" id="mem" value="${srvr.mem}" class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">网卡：</label>
                            <div class="col-sm-10">
                                <input type="text" name="ntwkCrd" id="ntwkCrd" value="${srvr.ntwkCrd}"
                                       class="form-control">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">所属机架：</label>
                            <div class="col-sm-10">
                                <input type="text" name="fram" id="fram" value="${srvr.fram}" class="form-control">
                            </div>
                        </div>
                        <!-- <!--  id, name, ip, sftwe, cpu, stor, mem, ntwk_crd, fram, envir, rmrk, creat_tm, updt_tm, enab_flag -->
                        <div class="form-group">
                            <label class="col-sm-2 control-label">所属环境：</label>
                            <div class="col-sm-10">
                                <input type="text" name="envir" id="envir" value="${srvr.envir}" class="form-control">
                            </div>
                        </div>

                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">备注：</label>
                            <div class="col-sm-10">
                                <textarea name="rmrk" id="rmrk" class="form-control"
                                          style="width:100%;height: 150px;">${srvr.rmrk}</textarea>
                            </div>
                        </div>
                        <div class="hr-line-dashed"></div>
                        <div class="form-group">
                            <div class="col-sm-4 col-sm-offset-2">
                                <button class="btn btn-primary" type="submit">保存内容</button>
                                <button class="btn btn-white" type="button" onclick="window.location='${ctx}/srvr'"
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
