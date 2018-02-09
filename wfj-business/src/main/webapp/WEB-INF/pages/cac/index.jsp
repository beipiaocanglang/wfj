<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>门店消费查询报表</title>
    <%@ include file="../common/meta.jsp" %>
    <style>
        .red {
            border-color: red;
            color: red;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            if (!$('#s_endPosid').val()) {
                $('#s_endPosid').attr('readonly', 'readonly');
            }
            $('#s_startPosid').change(function () {
                if ($(this).val()) {
                    $('#s_endPosid').removeAttr('readonly');
                    $('#s_endPosid').removeClass('red');
                } else {
                    $('#s_endPosid').attr('readonly', 'readonly');
                    $('#s_endPosid').addClass('red');
                }
            });

            $('#s_startPosid').keydown(function () {
                if ($(this).val() && !$('#s_endPosid').val()) {
                    $('#s_endPosid').addClass('red');
                } else {
                    $('#s_endPosid').removeClass('red');
                }
            });

            $('#creatTmBegin').change(function () {
                if (($(this).val()) && (!$('#creatTmEnd').val())) {
                    $('#creatTmEnd').addClass('red');
                } else {
                    $('#creatTmEnd').removeClass('red');
                }
            });

            $('#s_endPosid').click(function () {
                if (!$('#s_startPosid').val()) {
                    layer.msg('请先输入第一个收款机号');
                }
            });

            $('#search_btn').on('click', function (evt) {
                var e = evt || window.event;
                e.preventDefault();
                var tel1 = $('#s_startPosid').val();
                var tel2 = $('#s_endPosid').val();
                var start = $('input[name="s_startDate"]').val();
                var end = $('input[name="s_endDate"]').val();

                if (!!tel1 && !!!tel2) {
                    layer.msg('请先输入第二收款机号');
                    return false;
                }
                if (!!!tel1 && !!tel2) {
                    layer.msg('请先输入第一收款机号');
                    return false;
                }
                if (!!start && !!!end) {
                    layer.msg('请填写交易结束时间');
                    return false;
                }
                if (!!!start && !!end) {
                    layer.msg('请填写交易开始时间');
                    return false;
                }

                if (!!tel1 && !!tel2) {
                    if ((!!!start && !!!end) || (!!start && !!end)) {
                        $('#ec').submit();
                    }
                }

                if (!!start && !!end) {
                    if ((!!tel1 && !!tel2) || (!!!tel1 && !!!tel2)) {
                        $('#ec').submit();
                    }
                }

                if (!!!tel1 && !!!tel2 && !!!start && !!!end) {
                    $('#ec').submit();
                }

            });
            $('#search_clear').on('click', function (evt) {
                var e = evt || window.event;
                e.preventDefault();
                $('#s_startPosid').val('');
                $('#s_endPosid').val('');
                $('input[name="s_startDate"]').val('');
                $('input[name="s_endDate"]').val('');
                $('#ec').submit();
            });
            ///////导出相关/////////////////////////////////////////
            $("#export").on("click", function (evt) {
                var e = evt || window.event;
                e.preventDefault();
                var innerData = {
                    s_startPosid: $('#s_startPosid').val(),
                    s_endPosid: $('#s_endPosid').val(),
                    s_startDate: $('#s_startDate').val(),
                    s_endDate: $('#s_endDate').val()
                };
                window.open('${ctx}/report/export'+RequestObjectSerializer(innerData));
                //exporBtn();
            });

            /*一下内容暂时无用*/
            function exporBtn() {
                var innerData = {
                    s_startPosid: $('#s_startPosid').val(),
                    s_endPosid: $('#s_endPosid').val(),
                    s_startDate: $('#s_startDate').val(),
                    s_endDate: $('#s_endDate').val()
                };
                $.ajax({
                    type: 'get',
                    url: "${ctx}/report/export", //我后台接口地址
                    dataType: 'json',
                    data: innerData,
                    contentType: "application/json; charset=utf-8",
                    async: false,
                    success: function (msg) {
                        console.log(msg);
                        window.open('${ctx}/report/export');
                    }
                });
            };
        });
    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <form id="ec" action="${ctx }/cac" method="post" class="form-horizontal">
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm" style="border:solid red 0px">
                            <div class="col-md-1">
                                <button type="button" id="loading-example-btn"
                                        onclick="javascript:window.location.reload(true);" class="btn btn-white btn-sm">
                                    <i class="fa fa-refresh"></i> 刷新
                                </button>
                            </div>
                            <!-- 搜索条件 start-->
                            <div class="col-md-4">
                                <input type="text" class="searchInput " value="${pageRequest.filters.startPosid }"
                                       name="s_startPosid" id="s_startPosid" placeholder="请输入收款机号">
                                至
                                <input type="text" class="searchInput " value="${pageRequest.filters.endPosid }"
                                       name="s_endPosid" id="s_endPosid" placeholder="请输入收款机号">
                            </div>

                            <div class="col-md-4">
                                <input name="s_startDate" id="creatTmBegin" value="${pageRequest.filters.startDate }"
                                       class="searchInput Wdate" placeholder="交易开始时间" type="text"
                                       onFocus="WdatePicker({maxDate:'#F{$dp.$D(\'creatTmEnd\')||\'2050-10-01\'}'})"
                                       readonly="readonly"/>
                                至
                                <input name="s_endDate" id="creatTmEnd" value="${pageRequest.filters.endDate }"
                                       class="searchInput Wdate" placeholder="交易结束时间" type="text"
                                       onFocus="WdatePicker({minDate:'#F{$dp.$D(\'creatTmBegin\')}',maxDate:'2050-10-01'})"
                                       readonly="readonly"/>
                            </div>

                            <div class="col-md-1" style="text-align:right;">
                                <button type="submit" id="search_btn" class="btn btn-sm btn-primary"> 搜索</button>
                            </div>
                            <div class="col-md-1" style="text-align:right;">
                                <button type="submit" id="search_clear" class="btn btn-sm btn-primary">清空</button>
                            </div>
                            <div class="col-md-1" style="text-align:right;">
                                <button type="button" id="export" class="btn btn-sm btn-primary">导出</button>
                            </div>
                            <!-- 搜索条件 end-->
                        </div>
                        <table class="table table-striped table-bordered table-hover dataTables-example">
                            <thead>
                            <th>收款机号</th>
                            <th>交易笔数</th>
                            <th>消费金额</th>
                            <th>退货金额</th>
                            <th>消费合计</th>
                            <th>详情</th>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.result}" var="item" varStatus="st">
                                <tr class="gradeC">
                                    <td>${item.posid }</td>
                                    <td>${item.jiaoyibishu }</td>
                                    <td>${item.xiaofiejinge}</td>
                                    <td>${item.tuihuojine}</td>
                                    <td>${item.xiaofeiheji}</td>
                                    <td>
                                        <a class="btn btn-primary btn-xs pull-right"
                                           href="${ctx }/cac/show/${item.posid}">查看详情</a>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                        <%@ include file="../common/pagination.jsp" %>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>