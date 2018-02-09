<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@ include file="../common/meta.jsp" %>

    <script type="text/javascript" src="${ctx }/assets/js/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${ctx }/assets/js/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <link rel="stylesheet" type="text/css" href="${ctx }/assets/js/plugins/ztree/css/zTreeStyle/zTreeStyle.css"></link>
    <link rel="stylesheet" href="${ctx }/assets/js/plugins/ztree/css/demo.css" type="text/css"/>
    <script type="text/javascript">
        var DATA = [];
        var setting = {
            check: {
                enable: true,
                nocheckInherit: false,
                chkboxType: {"Y": "s", "N": "s"}
            },
            data: {
                simpleData: {
                    enable: true
                }
            },
            callback: {
                onCheck: onCheck
            }
        };

        function onCheck(event, treeId, treeNode) {
            //暂无处理
        }

        function add() {
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getCheckedNodes(true);
            var orgIds = "";
            for (var i = 0, l = nodes.length; i < l; i++) {
                orgIds += nodes[i].id + ",";
            }

            ajax.get("${ctx}/org/save", {
                "orgIds": orgIds,
                "userId": ${userId}
            }, function (data) {
                opt.closeWin();
            });
        }

        function cancel() {
            $.fn.zTree.init($("#treeDemo"), setting, DATA);
            //window.location.reload();
        }

        $(document).ready(function () {
            ajax.get("${ctx}/org/list", {
                "userId": ${userId}
            }, function (data) {
                DATA = data.body;

                $.fn.zTree.init($("#treeDemo"), setting, data.body);
            });
        });
    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight " style="border: 0px solid red;">

    <div class="zTreeDemoBackground left" style="width: 380px;height:350px;border: 0px solid red;">
        <ul id="treeDemo" class="ztree" style="border: 0px solid red;"></ul>
    </div>

    <div class="col-md-2">
        <button type="button" class="btn btn-sm btn-primary " onclick="add()" style="margin-left: 10px;"> 保存</button>
        <button type="button" class="btn btn-sm btn-primary " onclick="cancel()" style="margin-left: 10px;"> 取消</button>
    </div>
</div>
</body>
</html>
