<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<%@ include file="../pages/common/taglibs.jsp" %>

<!DOCTYPE html>
<html>
<!-- Mirrored from www.zi-han.net/theme/hplus/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:16:41 GMT -->
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp"/>
    <link rel="icon" href="${ctx }/assets/img/sd/ico.png" type="image/x-icon">
    <link rel="shortcut icon" href="${ctx }/assets/img/sd/ico.png" type="image/x-icon">
    <title>亿百分</title>
    <%@ include file="../pages/common/meta.jsp" %>
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
    <!--左侧导航开始-->
    <nav class="navbar-default navbar-static-side" role="navigation">
        <div class="nav-close"><i class="fa fa-times-circle"></i>
        </div>
        <div class="sidebar-collapse">
            <ul class="nav" id="side-menu">
                <li class="nav-header">
                    <div class="dropdown profile-element">
                        <span>
                            <img alt="image" class="" src="${ctx }/assets/img/sd/logo-b.png" style="width: 160px;height: 65px;"/>
                        </span>

                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <%--     <span class="clear">
                                	<span class="text-muted text-xs block">数据版本管理系统</span>
                                	<span class="block m-t-xs"><strong class="font-bold">${sysUser.uname }<b class="caret"></b></strong></span>
                                </span> --%>
                            <span class="clear">
                                <!-- <span class="text-muted text-xs block">管理系统</span> -->
                                <span class="text-muted text-xs block" style="text-align: center;padding-top: 15px;">${sysUser.uname }</span>
                                <%-- <span class="block m-t-xs"><strong class="font-bold">${sysUser1.uname }<b class="caret"></b></strong></span> --%>
                            </span>
                        </a>

                        <ul class="dropdown-menu animated fadeInRight m-t-xs">
                            <li><a href="javascript:opt.openWin('${ctx }/sysUser/pswd','修改密码',630,510)">修改密码</a></li>
                            <li class="divider"></li>
                            <li><a href="${ctx }/acl/logout">安全退出</a>
                            </li>
                        </ul>

                    </div>
                    <div class="logo-element">
                        <img alt="" src="${ctx }/assets/img/sd/logo-c.png">
                    </div>
                </li>
                <%@ include file="./left.jsp" %>
            </ul>
        </div>
    </nav>
    <!--左侧导航结束-->
    <!--右侧部分开始-->
    <div id="page-wrapper" class="gray-bg dashbard-1">
        <div class="row border-bottom">
            <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">

                <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-primary " href="#"><i
                        class="fa fa-bars"></i> </a>
                    <form role="search" class="navbar-form-custom" method="post"
                          action="http://www.zi-han.net/theme/hplus/search_results.html">
                        <div class="form-group">
                            <input type="text" placeholder='管理系统' class="form-control" name="top-search" id="top-search"
                                   readonly="readonly">
                        </div>
                    </form>
                </div>
                <!--
                    <ul class="nav navbar-top-links navbar-right">
                        <li class="dropdown">
                            <a href="${ctx }/acl/logout" class="roll-nav count-info J_tabExit">
                                <i class="fa fa fa-sign-out"></i>退出</span>
                            </a>
                        </li>
                    </ul>
                      -->
            </nav>
        </div>

        <div class="row content-tabs">
            <button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
            </button>
            <nav class="page-tabs J_menuTabs">
                <div class="page-tabs-content">
                    <a href="javascript:;" class="active J_menuTab" data-id="index_v1.html">首页</a>
                </div>
            </nav>
            <button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
            </button>
            <div class="btn-group roll-nav roll-right">
                <button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

                </button>
                <ul role="menu" class="dropdown-menu dropdown-menu-right">
                    <li class="J_tabShowActive"><a>定位当前选项卡</a>
                    </li>
                    <li class="divider"></li>
                    <li class="J_tabCloseAll"><a>关闭全部选项卡</a>
                    </li>
                    <li class="J_tabCloseOther"><a>关闭其他选项卡</a>
                    </li>
                </ul>
            </div>
            <a href="${ctx }/acl/logout" class="roll-nav roll-right J_tabExit"><i class="fa fa fa-sign-out"></i> 退出</a>
        </div>
        <!-- 包在里面了 -->
        <div class="row J_mainContent" id="content-main">
            <iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="${ctx }/acl/main" frameborder="0"
                    data-id="index_v1.html" seamless></iframe>
        </div>
        <div class="footer">
            <div class="pull-right">&copy; 版权所有：亿百分
            </div>
        </div>
    </div>
    <!--右侧部分结束-->
    <!--右侧边栏开始-->
    <div id="right-sidebar">
        <div class="sidebar-container">

        </div>
    </div>
    <!--右侧边栏结束-->
</div>

<script src="${ctx }/assets/js/bootstrap.min.js?v=3.3.6"></script>
<script src="${ctx }/assets/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${ctx }/assets/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${ctx }/assets/js/hplus.min.js?v=4.1.0"></script>
<script type="text/javascript" src="${ctx }/assets/js/contabs.min.js"></script>
<script src="${ctx }/assets/js/plugins/pace/pace.min.js"></script>
</body>


<!-- Mirrored from www.zi-han.net/theme/hplus/ by HTTrack Website Copier/3.x [XR&CO'2014], Wed, 20 Jan 2016 14:17:11 GMT -->
</html>
