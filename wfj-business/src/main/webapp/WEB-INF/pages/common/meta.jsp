<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*" pageEncoding="UTF-8" %>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="Cache-Control" content="no-store"/>
<meta http-equiv="Pragma" content="no-cache"/>
<meta http-equiv="Expires" content="0"/>
<span id="ctx" style="display: none">${ctx }</span>

<link href="${ctx }/assets/css/plugins/iCheck/custom.css" rel="stylesheet">
<link href="${ctx }/assets/css/bootstrap.min14ed.css?v=3.3.6" rel="stylesheet">
<link href="${ctx }/assets/css/font-awesome.min93e3.css?v=4.4.0" rel="stylesheet">
<link href="${ctx }/assets/css/plugins/dataTables/dataTables.bootstrap.css" rel="stylesheet">
<link href="${ctx }/assets/css/animate.min.css" rel="stylesheet">
<link href="${ctx }/assets/css/style.min862f.css?v=4.1.0" rel="stylesheet">
<link href="${ctx }/assets/css/base.css" rel="stylesheet">
<link href="${ctx }/assets/css/layout-sd.css" rel="stylesheet">
<link rel="stylesheet" href="${ctx }/assets/css/bootstrap-datepicker3.css">
<link rel="stylesheet" href="${ctx }/assets/css/bootstrap-select.css">
<link rel="stylesheet" href="${ctx }/assets/css/marketing.css">
<script src="${ctx }/assets/js/app.js" type="text/javascript"></script>
<script src="${ctx }/assets/js/plugins/layer/layer.js"></script>
<script src="${ctx }/assets/js/plugins/validate/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx }/assets/js/plugins/validate/messages_zh.min.js" type="text/javascript"></script>
<script src="${ctx }/assets/js/plugins/datePicker/WdatePicker.js" type="text/javascript"></script>
<script src="${ctx }/assets/js/common/common.js?v=11" type="text/javascript"></script>
<script src="${ctx }/assets/js/common/util.js?v=1" type="text/javascript"></script>
<script src="${ctx }/assets/js/common/jquery.select.js" type="text/javascript"></script>
<script src="${ctx }/assets/js/bootstrap.min.js"></script>
<script src="${ctx }/assets/js/bootstrap-datepicker.min.js"></script>
<script src="${ctx }/assets/js/bootstrap-datepicker.zh-CN.min.js"></script>
<script src="${ctx }/assets/js/bootstrap-select.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script type="text/javascript">
    $(function () {
        opt.dialog("${message}", "${messageType}");

        // 电话号码验证
        jQuery.validator.addMethod("isPhone", function (value, element) {
            var tel = /^(\d{3,4}-?)?\d{7,9}$/g;
            return this.optional(element) || (tel.test(value));
        }, "手机号码格式不正确！");
    });
</script>
