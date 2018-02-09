'user'
/*导航标签切换*/
$(function () {
    $('#myTab a:eq(0)').addClass('fontcolor');
    $('#myTab a').click(function (e) {
        e.preventDefault();
        $('#myTab a').removeClass("fontcolor");
        //$(this).parent().addClass('tabon').siblings().removeClass("tabon");
        //$('#myTab a').removeClass("tabfontcolor");
        $(this).addClass('fontcolor');
        $(this).tab('show');
    });
});
/*kpi选项切换*/
/*kpi 销售额 点击*/
$("span.tabbtn1").click(function () {
    $(".kiphidden1").toggleClass("kipshow");
    if ($(".kiphidden1").hasClass("kipshow")) {
        $("span.tabbtn1").addClass("tabbtnactive");
    } else {
        $("span.tabbtn1").removeClass("tabbtnactive");
        $("#s_sellAmount").val("");
    }
});
/*kpi 人流 点击*/
$("span.tabbtn2").click(function () {
    $(".kiphidden2").toggleClass("kipshow");
    if ($(".kiphidden2").hasClass("kipshow")) {
        $("span.tabbtn2").addClass("tabbtnactive");
    } else {
        $("span.tabbtn2").removeClass("tabbtnactive");
        $("#s_passengerFlow").val("");
    }
});
/*kpi 毛利 点击*/
$("span.tabbtn3").click(function () {
    $(".kiphidden3").toggleClass("kipshow");
    if ($(".kiphidden3").hasClass("kipshow")) {
        $("span.tabbtn3").addClass("tabbtnactive");
    } else {
        $("span.tabbtn3").removeClass("tabbtnactive");
        $("#s_grossProfit").val("");
    }
});
/*文件上传 活动计划*/
var upload = document.getElementById('upload');
var nameContainer = document.getElementById('name');

upload.onchange = function () {
    var name = [];
    for (var i = 0; i < this.files.length; i++) {
        name[i] = this.files[i].name;
        if (this.files[i].size >= 307200) {
            alert("文件" + this.files[i].name + "过大，不能超过300kb")
        }
    }
    nameContainer.innerHTML = name;
}

