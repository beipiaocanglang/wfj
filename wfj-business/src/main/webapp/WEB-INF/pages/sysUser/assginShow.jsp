<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@ include file="../common/meta.jsp" %>
    <style type="text/css">
        select option {
            width: 260px;
            height: 25px;
            line-height: 25px;
            padding: 5px 5px;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            $("#right").on("click", function () {
                $("#select1 option").each(function (index, item) {
                    if (item.selected == true) {
                        document.getElementById("select2").appendChild(item);
                    }
                });
            });

            $("#left").on("click", function () {
                $("#select2 option").each(function (index, item) {
                    if (item.selected == true) {
                        document.getElementById("select1").appendChild(item);
                    }
                });
            });
        });

        function funRight() {
            $("#right").trigger("click");
        }

        function funLeft() {
            $("#left").trigger("click");
        }

        function add() {
            var roleIds = "";
            $("#select2 option").each(function (index, item) {
                roleIds += $(item).val() + ",";
            });
            ajax.get("${ctx}/sysUser/assignRole", {
                "roleIds": roleIds,
                "userId":${userId}
            }, function (data) {
                opt.closeWin();
            });
        }

        function cancel() {
            window.location.reload();
        }

    </script>
</head>

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight" style="height: 466px;">
    <div>
        <div style="text-align: center;padding-left: 20px;">
            <div id="s1" style="float: left;">
                <div style="font-weight:900;">全部</div>
                <select id="select1" multiple="multiple" style="width: 260px;height: 350px;overflow-y:auto;"
                        ondblclick="funRight()">
                    <c:forEach items="${roleList}" var="item">
                        <c:set var="isExist" value="0"></c:set>
                        <c:forEach items="${userRoleList}" var="it">
                            <c:if test="${item.id == it.id}">
                                <c:set var="isExist" value="1"></c:set>
                            </c:if>
                        </c:forEach>
                        <c:if test="${isExist == 0}">
                            <option value="${item.id}">${item.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </div>
            <div style="float: left;">
                <br/>
                <input type="button" value=">>" id="right"/><br/><br/>
                <input type="button" value="<<" id="left"/><br/><br/>
            </div>
            <div id="s2" style="float: left;">
                <div style="font-weight:900;">已选择</div>
                <select id="select2" multiple="multiple" style="width: 260px;height: 350px;overflow-y:auto;"
                        ondblclick="funLeft()">
                    <c:forEach items="${userRoleList}" var="item">
                        <option value="${item.id}">${item.name}</option>
                    </c:forEach>
                </select>
            </div>

            <div class="form-group" style="clear: left;padding-top: 20px;">
                <button type="button" class="btn btn-sm btn-primary " onclick="add()" style="margin-left: 10px;"> 保存
                </button>
                <button type="button" class="btn btn-sm btn-primary " onclick="cancel()" style="margin-left: 10px;">
                    取消
                </button>
            </div>
            <br/>
        </div>

    </div>
</div>
</body>
</html>
