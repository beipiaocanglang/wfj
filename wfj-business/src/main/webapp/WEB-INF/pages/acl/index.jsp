<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@ include file="../common/meta.jsp" %>
    <script type="text/javascript">
        //授权
        function addOrUpdatePermission(field, mdulId, permission) {

            //如果被选择上，则同时选择其"不继承"和"启用"checkbox
            if (field.checked) {
                document.getElementById(mdulId + "_USE").checked = true;
                <c:if test="${prinType eq 'user' }">
                document.getElementById(mdulId + "_EXT").checked = true;
                addOrUpdateExtends(field, mdulId);
                </c:if>

                //上级模块选中
                var parId = $(field).attr("data-parId");
                var parObj = document.getElementById(parId + "_USE");
                if (!parObj.checked) {
                    parObj.checked = true;
                    usePermissionTop(parObj, parId);
                }
            } else {
                var parId = $(field).attr("data-parId");

                var isNoCheck = true;
                $(field).siblings().each(function (index, item) {
                    if (item.checked) {
                        isNoCheck = false;
                    }
                });
                if (isNoCheck) {
                    var useObj = document.getElementById(mdulId + "_USE");
                    useObj.checked = false;
                    usePermission(useObj, mdulId);
                }

                var isAllNoCheck = true;
                $("." + parId + "_mk").each(function (index, item) {
                    if (item.checked) {
                        isAllNoCheck = false;
                    }
                });
                if (isAllNoCheck) {
                    var parObj = document.getElementById(parId + "_USE");
                    parObj.checked = false;
                    usePermissionTop(parObj, parId);
                }
            }
            var data = "prinType=${prinType}&prinId=${prinId}&mdulId=" + mdulId + "&permission=" + permission + "&checked=" + field.checked;
            var html = $.ajax({
                type: "POST",
                url: "${ctx}/acl/addOrUpdatePermission",
                data: data,
                async: false
            }).responseText;

        }

        //授权顶级
        function addOrUpdatePermissionTop(field, mdulId) {
            if (field.checked) {
                document.getElementById(mdulId + "_USE").checked = true;
            }

            var data = {
                prinType: "${prinType}",
                prinId: "${prinId}",
                mdulId: mdulId,
                permission: 0,
                checked: true,
                top: 'top'
            };
            $.post("${ctx}/acl/addOrUpdatePermission", data);
        }

        //设置用户的继承特性
        function addOrUpdateExtends(field, mdulId) {
            var data = "prinId=${prinId}&mdulId=" + mdulId + "&checked=" + !field.checked;
            var html = $.ajax({
                type: "POST",
                url: "${ctx}/acl/addOrUpdateUserExtends",
                data: data,
                async: false
            }).responseText;

        }

        //点击启用checkbox
        function usePermission(field, mdulId) {
            //如果checkbox被选中，意味着需要更新ACL的状态
            //更新C/R/U/D以及Extends状态

            if (field.checked) {
                for (var i = 0; i < 50; i++) {
                    if (document.getElementById(mdulId + "_" + i) != null) {
                        document.getElementById(mdulId + "_" + i).checked = true;
                        addOrUpdatePermission(document.getElementById(mdulId + "_" + i), mdulId, i);
                    }
                }

                <c:if test="prinType eq 'user' }">
                addOrUpdateExtends(document.getElementById(mdulId + "_EXT"), mdulId);
                </c:if>
            } else {

                var data = {prinType: "${prinType}", prinId: "${prinId}", mdulId: mdulId};
                $.post("${ctx}/acl/delPermission", data);

                for (var i = 0; i < 50; i++) {
                    if (document.getElementById(mdulId + "_" + i) != null) {
                        document.getElementById(mdulId + "_" + i).checked = false;
                    }
                }

                <c:if test="${prinType eq 'user' }">
                document.getElementById(mdulId + "_EXT").checked = false;
                </c:if>

                var parId = $(field).attr("data-parId");
                var isAllNoCheck = true;
                $("." + parId + "_mk").each(function (index, item) {
                    if (item.checked) {
                        isAllNoCheck = false;
                    }
                });
                if (isAllNoCheck) {
                    var parObj = document.getElementById(parId + "_USE");
                    parObj.checked = false;
                    usePermissionTop(parObj, parId);
                }
            }
        }

        //点击启用顶级模块
        function usePermissionTop(field, mdulId) {
            if (field.checked) {
                addOrUpdatePermissionTop(field, mdulId);
            } else {

                var data = {prinType: "${prinType}", prinId: "${prinId}", mdulId: mdulId};
                $.post("${ctx}/acl/delPermission", data);

            }
        }

        function searchAclRecord() {
            var data = {prinType: "${prinType}", prinId: "${prinId}"};
            $.post("${ctx}/acl/searchAclRecord", data, function (datas) {

                $.each(datas.body, function (n, val) {
                    var mdulId = val.mdulId;

                    var extState = val.acltristate;
                    var aclList = val.aclList;

                    $.each(aclList, function (i, val) {
                        var mod = document.getElementById(mdulId + "_" + i);
                        if (mod != null) {
                            mod.checked = val == 0 ? false : true;
                        }
                    });

                    <c:if test="${prinType eq 'user' }">
                    var mod_z = document.getElementById(mdulId + "_EXT");
                    if (mod_z != null) {
                        mod_z.checked = extState == 0 ? true : false;
                    }
                    </c:if>
                    var mod_u = document.getElementById(mdulId + "_USE");
                    if (mod_u != null) {
                        mod_u.checked = true;
                    }
                });
            });

        }

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

    <c:choose>
        <c:when test="${prinType eq 'role' }">
            <c:set var="title" value="角色【${role.name }】授权"/>
            <c:set var="name" value="角色授权"/>
        </c:when>
        <c:otherwise>
            <c:set var="title" value="用户【${user.username }】授权"/>
            <c:set var="name" value="用户授权"/>
        </c:otherwise>
    </c:choose>
    <title>${title }</title>
</head>

<body class="gray-bg" onload="searchAclRecord()">
<form id="ec" action="" method="post" class="form-horizontal">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <!--
					<div class="ibox-title">
						<h5>
							 <small>${title }</small>
						</h5>
					</div>
					-->
                    <div class="ibox-content">
                        <p class="susong_h1">功能项列表</p>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <tr>
                                <th width="5%">序号</th>
                                <th width="8%">上级模块</th>
                                <th width="10%">模块名称</th>
                                <th>功能项</th>
                                <c:if test="${prinType eq 'user' }">
                                    <th width="3%">不继承</th>
                                </c:if>
                                <th width="5%">启用</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${modulesFun }" var="module" varStatus="st">
                                <tr class="gradeX">
                                    <td>${st.count }</td>
                                    <td>${module.name }</td>
                                    <td></td>
                                    <td></td>
                                    <c:if test="${prinType eq 'user' }">
                                        <td align="center"></td>
                                    </c:if>
                                    <td align="center"><input type="checkbox" id="${module.id }_USE"
                                                              onclick="usePermissionTop(this, ${module.id })"/></td>
                                </tr>
                                <c:forEach items="${module.children }" var="child" varStatus="s">
                                    <c:if test="${child.status == 0 }">
                                        <tr class="gradeX">
                                            <td>&nbsp;</td>
                                            <td></td>
                                            <td>${child.name }</td>
                                            <td>

                                                <c:forEach items="${child.functions}" var="function" varStatus="st">
                                                    <c:if test="${st.count % 12 == 0 }"><br/></c:if>
                                                    <input type="checkbox" id="${function.mdulId }_${st.index }"
                                                           data-parId="${module.id }" class="${module.id }_mk"
                                                           onclick="addOrUpdatePermission(this, ${function.mdulId }, ${function.levels })"/>${function.name }&nbsp;&nbsp;&nbsp;&nbsp;
                                                </c:forEach>

                                            </td>
                                            <c:if test="${prinType eq 'user' }">
                                                <td align="center"><input type="checkbox" id="${child.id }_EXT"
                                                                          onclick="addOrUpdateExtends(this, ${child.id })"/>
                                                </td>
                                            </c:if>
                                            <td align="center"><input type="checkbox" id="${child.id }_USE"
                                                                      data-parId="${module.id }"
                                                                      onclick="usePermission(this, ${child.id })"/></td>
                                        </tr>
                                    </c:if>

                                    <!-- qy -->
                                    <c:if test="${child.status != 0 }">
                                        <tr class="gradeX" style="display: none">
                                            <td></td>
                                            <td>${child.name }</td>
                                            <td>

                                                <c:forEach items="${child.functions}" var="function" varStatus="st">
                                                    <input type="checkbox" id="${function.mdulId }_${st.index }"
                                                           onclick="addOrUpdatePermission(this, ${function.mdulId }, ${function.levels })"/>${function.name }
                                                </c:forEach>

                                            </td>
                                            <c:if test="${prinType eq 'user' }">
                                                <td><input type="checkbox" id="${child.id }_EXT"
                                                           onclick="addOrUpdateExtends(this, ${child.id })"/></td>
                                            </c:if>
                                            <td><input type="checkbox" id="${child.id }_USE"
                                                       onclick="usePermission(this, ${child.id })"/></td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                            </tbody>
                        </table>

                        <p class="susong_h1">数据项列表</p>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <tr>
                                <th width="5%">序号</th>
                                <th width="8%">上级模块</th>
                                <th width="10%">模块名称</th>
                                <th>功能项</th>
                                <c:if test="${prinType eq 'user' }">
                                    <th width="3%">不继承</th>
                                </c:if>
                                <th width="5%">启用</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${modulesData }" var="module" varStatus="st">
                                <tr class="gradeX">
                                    <td>${st.count }</td>
                                    <td>${module.name }</td>
                                    <td></td>
                                    <td></td>
                                    <c:if test="${prinType eq 'user' }">
                                        <td align="center"></td>
                                    </c:if>
                                    <td align="center">
                                        <!--<input type="checkbox" id="${module.id }_USE" onclick="usePermissionTop(this, ${module.id })"/>--></td>
                                </tr>
                                <c:forEach items="${module.children }" var="child" varStatus="s">
                                    <c:if test="${child.status == 0 }">
                                        <tr class="gradeX">
                                            <td>&nbsp;</td>
                                            <td></td>
                                            <td>${child.name }</td>
                                            <td>

                                                <c:forEach items="${child.functions}" var="function" varStatus="st">
                                                    <c:if test="${st.count % 12 == 0 }"><br/></c:if>
                                                    <input type="checkbox" id="${function.mdulId }_${st.index }"
                                                           onclick="addOrUpdatePermission(this, ${function.mdulId }, ${function.levels })"/>${function.name }&nbsp;&nbsp;&nbsp;&nbsp;
                                                </c:forEach>

                                            </td>
                                            <c:if test="${prinType eq 'user' }">
                                                <td align="center"><input type="checkbox" id="${child.id }_EXT"
                                                                          onclick="addOrUpdateExtends(this, ${child.id })"/>
                                                </td>
                                            </c:if>
                                            <td align="center"><input type="checkbox" id="${child.id }_USE"
                                                                      onclick="usePermission(this, ${child.id })"/></td>
                                        </tr>
                                    </c:if>

                                    <!-- qy -->
                                    <c:if test="${child.status != 0 }">
                                        <tr class="gradeX" style="display: none">
                                            <td></td>
                                            <td>${child.name }</td>
                                            <td>

                                                <c:forEach items="${child.functions}" var="function" varStatus="st">
                                                    <input type="checkbox" id="${function.mdulId }_${st.index }"
                                                           onclick="addOrUpdatePermission(this, ${function.mdulId }, ${function.levels })"/>${function.name }
                                                </c:forEach>

                                            </td>
                                            <c:if test="${prinType eq 'user' }">
                                                <td><input type="checkbox" id="${child.id }_EXT"
                                                           onclick="addOrUpdateExtends(this, ${child.id })"/></td>
                                            </c:if>
                                            <td><input type="checkbox" id="${child.id }_USE"
                                                       onclick="usePermission(this, ${child.id })"/></td>
                                        </tr>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                            </tbody>
                        </table>
                        <div class="ibox-title">
                            <button class="btn btn-white" type="button" onclick="goBack()" value="返回">返回</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</form>
</body>
</html>

