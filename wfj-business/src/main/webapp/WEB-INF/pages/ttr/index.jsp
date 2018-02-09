<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>汇总表查询</title>
    <%@ include file="../common/meta.jsp" %>
    <style>
        .red {
            border-color: red;
            color: red;
        }
    </style>
    <script type="text/javascript">
        $(function () {
            /*if (!$('#s_endPosid').val()) {
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
            });*/
            $('#search_btn').on('click', function (evt) {
                var e = evt || window.event;
                e.preventDefault();

                var s_startDate = $('input[name="s_startDate"]').val();
                var s_endDate = $('input[name="s_endDate"]').val();


                if (!!s_startDate && !!!s_endDate) {
                    layer.msg('请填写交易结束时间');
                    return false;
                }
                if (!!!s_startDate && !!s_endDate) {
                    layer.msg('请填写交易开始时间');
                    return false;
                }
                if ((!!!s_startDate && !!!s_endDate) || (!!s_startDate && !!s_endDate)) {
                    $('#ec').submit();
                }
                if (!!!s_startDate && !!!s_endDate) {
                    $('#ec').submit();
                }

            });
            $('#search_clear').on('click', function (evt) {
                var e = evt || window.event;
                e.preventDefault();
                $('input[name="s_startDate"]').val('');
                $('input[name="s_endDate"]').val('');
                $('#ec').submit();
            });
            ///////导出相关/////////////////////////////////////////
            $("#export").on("click", function (evt) {
                var e = evt || window.event;
                e.preventDefault();
                var innerData = {
                    s_startDate: $('#creatTmBegin').val(),
                    s_endDate: $('#creatTmEnd').val()
                };
                window.open('${ctx}/ttr/export/exportIndexExcel'+RequestObjectSerializer(innerData));
            });
        });
    </script>
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <form id="ec" action="${ctx }/ttr" method="post" class="form-horizontal">
                    <div class="ibox-content">
                        <div class="row m-b-sm m-t-sm" style="border:solid red 0px">
                            <div class="col-md-1">
                                <button type="button" id="loading-example-btn"
                                        onclick="javascript:window.location.reload(true);" class="btn btn-white btn-sm">
                                    <i class="fa fa-refresh"></i> 刷新
                                </button>
                            </div>
                            <!-- 搜索条件 start-->
                            <%--<div class="col-md-4">
                                <input type="text" class="searchInput " value="${pageRequest.filters.startPosid }"
                                       name="s_startPosid" id="s_startPosid" placeholder="请输入收款机号">
                                至
                                <input type="text" class="searchInput " value="${pageRequest.filters.endPosid }"
                                       name="s_endPosid" id="s_endPosid" placeholder="请输入收款机号">
                            </div>--%>

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
                            <th>日期</th>
                            <th>售卡合计</th>
                            <th>退款合计</th>
                            <th>存退款合计</th>
                            <th>详情</th>
                            </thead>
                            <tbody>
                            <c:forEach items="${page.result}" var="item" varStatus="st">
                                <tr class="gradeC">
                                    <td>${item.payTime}</td>
                                    <td>${item.activeCardPrice}</td>
                                    <td>${item.backCardPrice}</td>
                                    <td>${item.diffAmount}</td>
                                    <td>
                                        <a class="btn btn-primary btn-xs pull-right" href="${ctx }/ttr/show/${item.payTime}">查看详情</a>
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