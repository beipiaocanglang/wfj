<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title></title>
    <%@ include file="../common/meta.jsp" %>
    <link rel="stylesheet" href="${ctx }/assets/js/plugins/ztree/css/demo.css" type="text/css"/>
    <link rel="stylesheet" href="${ctx }/assets/js/plugins/ztree/css/metroStyle/metroStyle.css" type="text/css">
    <%-- <link rel="stylesheet"  href="${ctx }/assets/js/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"></link> --%>
    <script type="text/javascript" src="${ctx }/assets/js/plugins/ztree/js/jquery-1.4.4.min.js"></script>
    <script type="text/javascript" src="${ctx }/assets/js/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
    <script type="text/javascript" src="${ctx }/assets/js/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
    <script type="text/javascript" src="${ctx }/assets/js/plugins/ztree/js/jquery.ztree.exedit.js"></script>

    <script type="text/javascript">
        //	id:id    pId ：父ID    name： 名称
        var setting = {
            view: {
                addHoverDom: addHoverDom,
                removeHoverDom: removeHoverDom,
                dblClickExpand: false,
                //showIcon: showIconForTree,               //不显示图标
                //showLine: false,                        //不显示连接线
                selectedMulti: false

            },
            edit: {
                enable: true,
                //showRemoveBtn: false,
                //showRenameBtn: false
            },
            /* check: {                //方格选框
                enable: true,
                nocheckInherit: false,
                chkboxType: { "Y" : "s", "N" : "s" }
            }, */
            async: {
                enable: true,
                contentType: "application/x-www-form-urlencoded",
                type: "post",
                url: "${ctx }/pst/findByParId",      //获取后台的数据
                autoParam: ["id"],
                dataFilter: filter
            },
            callback: {
                onClick: onClick,
                onCheck: onCheck,
                beforeRemove: beforeRemove,//删除之前判断
                onRemove: onRemove, //删除
                beforeRename: beforeRename,//修改之前判断
                onRename: onRename //修改
            }
        };

        function showIconForTree(treeId, treeNode) {
            return !treeNode.id;                 //设置所有的都不显示图标
        };


        function filter(treeId, parentNode, childNodes) {
            childNodes = childNodes.body;
            if (!childNodes) return null;
            for (var i = 0, l = childNodes.length; i < l; i++) {
                childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
            }
            return childNodes;
        }

        function onClick(event, treeId, treeNode, clickFlag) {
            //$("#name").text(treeNode.name);
            //$("#code").text(treeNode.code);

            alert(treeNode.name);

            //$("#list").load('${ctx }/pst/findByParId?parId='+zTree.getSelectedNodes()[0].id);
        }

        function onCheck(event, treeId, treeNode) {
            var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
            var nodes = treeObj.getCheckedNodes(true);
            for (var i = 0; i < nodes.length; i++) {
                $("#treeDemo1").append(nodes[i].name + " / "); //获取选中节点的值
            }
        }

        /*=====================================重命名的判断==============================================*/

        function beforeRename(treeId, treeNode, newName, isCancel) {
            if (newName.length == 0) {
                alert("节点名称不能为空.");
                return false;
            }
            return true;
        }

        /*==========================================重命名的请求=================================================*/

        function onRename(event, treeId, treeNode, isCancel) {
            //需要对名字做判定的，这里写

            var data = {
                "id": treeNode.id,
                "name": treeNode.name
            }
            $.ajax({
                url: '${ctx }/pst/update',
                type: 'post',
                data: data,
                success: function (data) {
                    console.log('修改' + treeNode.name)
                },
                error: function (e) {
                    console.log('修改---' + treeNode.name + '---出错');
                    console.log(e);
                }
            })
        }

        /*=============================================删除前的提示==========================================================*/

        function beforeRemove(treeId, treeNode) {
            var zTree = $.fn.zTree.getZTreeObj("treeDemo");
            zTree.selectNode(treeNode);
            return confirm("确认删除 节点 -- " + treeNode.name + " 吗？");
        }


        /*================================================删除的请求==================================================*/
        function onRemove(event, treeId, treeNode) {
            //需要对删除做判定或者其它操作

            var data = {"id": treeNode.id};
            $.ajax({
                url: '${ctx }/pst/delete',
                data: data,
                type: 'post',
                success: function (data) {
                    console.log('删除了' + treeNode.name)
                },
                error: function (e) {
                    console.log('删除了---' + treeNode.name + '---出错');
                    console.log(e);
                }
            });
        }


        var zTree, rMenu;
        $(document).ready(function () {
            $.fn.zTree.init($("#treeDemo"), setting);
            zTree = $.fn.zTree.getZTreeObj("treeDemo");
            rMenu = $("#rMenu");
        });

        /*==================================================新增========================================================*/
        var newCount = 1;

        function addHoverDom(treeId, treeNode) {
            var sObj = $("#" + treeNode.tId + "_span");
            if (treeNode.editNameFlag || $("#addBtn_" + treeNode.tId).length > 0) return;
            var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
                + "' title='add node' onfocus='this.blur();'></span>";
            sObj.after(addStr);
            var btn = $("#addBtn_" + treeNode.tId);
            if (btn) btn.bind("click", function () {

                var zTree = $.fn.zTree.getZTreeObj("treeDemo");
                var addName = "新增岗位" + (newCount++);     //新增岗位n

                ajax.post("${ctx }/pst/save", {
                    "parId": treeNode.id,
                    "name": addName
                }, function (data) {
                    var pst = data.body;

                    zTree.addNodes(treeNode, {id: pst.id, pId: treeNode.id, name: addName});
                });
                return false;
            });
        };

        function removeHoverDom(treeId, treeNode) {
            //debugger;         //打断点
            $("#addBtn_" + treeNode.tId).unbind().remove();
        };
    </script>

</head>
<!-- <body class="gray-bg">
<div class="ibox-content" style="border: 0px solid red;">
	<div class="wrapper wrapper-content animated fadeInRight " style="border: 0px solid red;">
	<h3>公司组织机构管理</h3>
		<div class="zTreeDemoBackground left" style="width: 100%;height:90%'';border: 0px solid red;">
			<ul id="treeDemo" class="ztree" style="border: 0px solid red;"></ul>
		</div>
	</div>
</body>
</html>
 -->

<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="ibox float-e-margins">
        <h3>职位管理</h3>
        <div class="zTreeDemoBackground left" style="width: 100%;height:90%'';border: 0px solid red;">
            <ul id="treeDemo" class="ztree" style="border: 0px solid red;"></ul>
        </div>
    </div>
</div>
</body>
</html>

