var array = []; // 品牌列表数据 存储 种子人群
var arraydata = []; //传给智算使用的数据

var arraytwo = []; // 标签扩散 品牌列表
var arraydatatwo = []; //传给智算使用的数据


// 种子人群 删除操作
$("body").on("click", ".delectthis", function () {
    //document.querySelector("#pp00704").setAttribute("data-checked","false")
    if (array.length>0) {
        for (var i=0;i<array.length; i++) {
            if (array[i].brandid == $(this).attr("data-brandid")) {
                array.splice(i, 1);
                arraydata.splice(i, 1);
                $(this).parent().parent().remove();  //已选列表 单个x 删除
                //$("#appendlist").children().remove();
                $('body').find("#"+$(this).attr("data-brandid")).attr("data-checked","true");
                $('body').find("#"+$(this).attr("data-brandid")).removeClass("checkedstatus");
                $('body').find("#delect"+$(this).attr("data-brandid")).remove();
                //$("#appendlists").children().remove();
                /*for (x in array) {
                    //$("#appendlist").append('<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname" href="javascript:void(0)">' + array[x].brandname + '<i class="delectthis" data-brandid="'+array[x].brandid+'">X</i></a ></div>');
                    $("#appendlists").append('<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname fontcolor" href="javascript:void(0);"><span class="brandnamecss" style="color: rgba(16, 142, 233, 1) !important;">' + array[x].brandname + '</span><i class="delectthis" data-brandid="'+array[x].brandid+'" style="color: rgba(16, 142, 233, 1) !important;">X</i></a ></div>');

                }*/
                return;
            }
        }
    } else {
        $(this).parent().parent().remove();  //已选列表 单个x 删除
    }
});

$(".select_2222").bind('click',function () {
    //$(this).attr("addeventclick","false");
    if ($(this).attr("addeventclick") == "false") {
        $("#mask_2").hide();
        $("#sendCategoryId").attr("value",$(this).attr("value"));
        $("#sendCategoryId").attr("storeId",$(this).attr('storeId'));

        /*  种子人群页面  根据pid查询二级分类数据*/
        $.ajax({
            type: 'POST',
            url: '/marketing/category/selectCategoryByPid?storesId=' + $(this).attr('storeId') + '&pid=' + $(this).attr("value"),
            dataType: 'json',
            contentType: "application/json",
            success: function (categoryData) {

                if (categoryData.success == true) {
                    selectall_data(categoryData.data);
                    if (categoryData.data.length>0) {
                        $(this).attr("data-brandtitleid");
                        var brand = "";
                        $("#brandCategoryId").children().remove();
                        $("#appendlist").children().remove();
                        $.each(categoryData.data, function (a, c) {
                            if (array.length>0) {
                                var checkedstatus = "";
                                var datachecked = "true";
                                for (x in array) {
                                    if (array[x].brandid == 'pp'+c.pp) {
                                        checkedstatus = "checkedstatus";
                                        datachecked = "false";
                                        brandname = array[x].brandname;
                                    }
                                }

                                brand += '<div class="col-md-2 col-sm-3 col-xs-4">'
                                    + '<a class="search-brandname cursorsetting ' + checkedstatus + '"  href="javascript:void(0);" onclick="barndclick(this)" data-storesId="'+$(this).attr('storeId')+'" data-checked="' + datachecked + '" brandname="' + c.standardPpname + '" id="pp' + c.pp + '" data-checkedbrandid="'+c.pp+'">' + c.standardPpname + '</a>'
                                    + '</div>'
                            } else {
                                brand += '<div class="col-md-2 col-sm-3 col-xs-4">'
                                    + '<a class="search-brandname cursorsetting"  href="javascript:void(0);" onclick="barndclick(this)" data-storesId="'+$(this).attr('storeId')+'" data-checked="true" brandname="' + c.standardPpname + '" id="pp' + c.pp + '" data-checkedbrandid="'+c.pp+'">' + c.standardPpname + '</a>'
                                    + '</div>'
                            }
                        })
                        var checkselectlist = "";
                        if (array.length>0) {
                            for (x in array) {
                                var brandname = "";
                                $.each(categoryData.data, function (w, q) {
                                    //console.log(array[x].brandid+'     '+'pp'+q.pp+'       '+$("#appendlist").innertHTML)
                                    if (array[x].brandid == 'pp'+q.pp){
                                        brandname = array[x].brandname;
                                        checkselectlist= '<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname" id="ppp'+array[x].brandid+'" href="javascript:void(0)">' +'品牌:'+ brandname + '<i class="delectthis" data-brandid="'+array[x].brandid+'">X</i></a ></div>';
                                    } else {

                                    }
                                });
                                //console.log(checkselectlist);
                                $("#appendlist").append(checkselectlist);
                                checkselectlist = "";
                            }
                        }
                        $("#brandCategoryId").append(brand);

                    }
                    $("#mask_1,.bg_1").show();
                    $('body').css("overflow", "hidden");
                } else {
                    layer.msg(categoryData.errMsg);
                }
            }
        })
    } else {
        return;
    }



});
//种子人群 弹出框全选
/*function selectall(event) {
    event.preventDefault();
    $(".cursorsetting").click();
}*/
function selectall_data(seledata) {
    //console.log(seledata);
    if (seledata == undefined) {
        return;
    } else {
        $("#selectall").bind('click',function() {
            $.each(seledata, function (a, c) {
                if (array.length>=0) {
                    for (x in array) {
                        if (array[x].brandid == 'pp'+c.pp) {
                            $('#pp'+c.pp).click();
                        }
                    }

                    $('#pp'+c.pp).click();

                } else {
                    alert("操作失败")
                }
            })
        });
    }
}selectall_data();

//种子人群 弹出框选项操作
function appendElement(id,name) {
    $("#appendlist").append('<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname" id="ppp'+id+'" href="javascript:void(0);"><span class="brandnamecss">'+'品牌:' + name + '</span><i class="delectthis" data-brandid="'+id+'">X</i></a ></div>');
    $("#appendlists").append('<div class="col-md-2 col-sm-3 col-xs-4" id="delect'+id+'"><a class="search-brandname fontcolor" href="javascript:void(0);"><span class="brandnamecss" style="color: rgba(16, 142, 233, 1) !important;">'+'品牌:' + name + '</span><i class="delectthis" data-brandid="'+id+'" style="color: rgba(16, 142, 233, 1) !important;">X</i></a ></div>');
}
function barndclick(ele){
    var Obj = {};
    var Objbrandid = {};
    const branid_res = $(ele).attr('id');
    const brandname_res = $(ele).attr('brandname');
    if ($(ele).attr("data-checked")== 'true') {
        $(ele).addClass('checkedstatus');
        $(ele).attr("data-checked","false")
    } else {
        $(ele).removeClass('checkedstatus');
        $(ele).attr("data-checked","true");
        //$('body').find("#"+$(ele).attr("data-brandid")).attr("data-checked","true");
        //$('body').find("#ppp"+$(ele).attr("data-brandid")).removeClass("checkedstatus");
        $('body').find("#ppp"+$(ele).attr('id')).parent().remove();
    }

    if (array.length>0) {
        for (var i=0;i<array.length; i++) {
            if (array[i].brandid == $(ele).attr('id')) {
                array.splice(i, 1);
                arraydata.splice(i, 1);
                //console.log(array[i].brandname);
                //$("#appendlist").children().remove();
                $("#appendlists").children().remove();
                for (x in array) {
                    var d = array[x];
                    //$("#appendlist").append('<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname" href="javascript:void(0)">' + array[x].brandname + '<i class="delectthis" data-brandid="'+array[x].brandid+'">X</i></a ></div>');
                    //$("#appendlists").append('<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname fontcolor" href="javascript:void(0);"><span class="brandnamecss" style="color: rgba(16, 142, 233, 1) !important;">'+'品牌:' + array[x].brandname + '</span><i class="delectthis" style="color: rgba(16, 142, 233, 1) !important;" data-brandid="'+array[x].brandid+'">X</i></a ></div>');
                    if (d.brandid != undefined && d.brandid!="undefined"){
                        //有值就是品牌
                        $("#appendlists").append('<div class="col-md-2 col-sm-3 col-xs-4" id="delect'+d.brandid+'"><a class="search-brandname fontcolor" href="javascript:void(0);"><span class="brandnamecss" style="color: rgba(16, 142, 233, 1) !important;">'+'品牌:' + d.brandname + '</span><i class="delectthis" data-brandid="'+d.brandid+'" style="color: rgba(16, 142, 233, 1) !important;">X</i></a ></div>');
                    }
                    if (d.ppid != undefined && d.ppid!="undefined"){
                        //有值就是品类
                        $("#appendlists").append('<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname fontcolor" href="javascript:void(0);"><span class="brandnamecss" style="color: rgba(16, 142, 233, 1) !important;">' + '分类:'+d.brandname + '</span><i class="delectthis3" data-brandid="'+d.storeId+'" data-ppidd="'+d.ppid+'" style="color: rgba(16, 142, 233, 1) !important;">X</i></a ></div>');
                    }
                }
                return;
            }
        }

        Obj.brandid = $(ele).attr('id');
        Obj.brandname = $(ele).attr('brandname');
        Obj.datachecked = $(ele).attr('data-checked');
        Obj.checkedbrandid = $(ele).attr('data-checkedbrandid');
        Obj.storeId = $(ele).attr('data-storesid');
        array.push(Obj);
        arraydata.push($(ele).attr('data-checkedbrandid'));
        appendElement(branid_res,brandname_res);
    } else {
        Obj.brandid = $(ele).attr('id');
        Obj.brandname = $(ele).attr('brandname');
        Obj.datachecked = $(ele).attr('data-checked');
        Obj.checkedbrandid = $(ele).attr('data-checkedbrandid');
        Obj.storeId = $(ele).attr('data-storesid');
        array.push(Obj);
        arraydata.push($(ele).attr('data-checkedbrandid'));
        appendElement(branid_res,brandname_res);

    }

}

/*  种子人群页面  根据品牌名称搜索品牌*/
$("#sendCategoryId").bind('click',function (event) {
    event.stopPropagation();
    $("#sendCategoryId").attr("value",$(this).attr("value"));
    $("#sendCategoryId").attr("storeId",$(this).attr('storeId'));
    $.ajax({
        type: 'POST',
        url: '/marketing/category/selectCategoryByBrandName?storesId=' + $(this).attr('storeId') + '&pid=' + $(this).attr("value") + '&brandName=' + $("#brandId").val(),
        dataType: 'json',
        contentType: "application/json",
        success: function (categoryData) {
            if (categoryData.success == true) {
                var brand = "";
                $("#brandCategoryId").children().remove();
                if (categoryData.data.length>0) {
                    $.each(categoryData.data, function (a, c) {
                        if (array.length>0) {
                            var checkedstatus = "";
                            var datachecked = "true";
                            for (x in array) {
                                if (array[x].brandid == 'pp'+c.pp) {
                                    checkedstatus = "checkedstatus";
                                    datachecked = "false";
                                    brandname = array[x].brandname;
                                }
                            }

                            brand += '<div class="col-md-2 col-sm-3 col-xs-4">'
                                + '<a class="search-brandname cursorsetting ' + checkedstatus + '"  href="javascript:void(0);" onclick="barndclick(this)" data-storesId="'+$(this).attr('storeId')+'" data-checked="' + datachecked + '" brandname="' + c.standardPpname + '" id="pp' + c.pp + '" data-checkedbrandid="'+c.pp+'">' + c.standardPpname + '</a>'
                                + '</div>'
                        } else {
                            brand += '<div class="col-md-2 col-sm-3 col-xs-4">'
                                + '<a class="search-brandname cursorsetting"  href="javascript:void(0);" onclick="barndclick(this)" data-storesId="'+$(this).attr('storeId')+'" data-checked="true" brandname="' + c.standardPpname + '" id="pp' + c.pp + '" data-checkedbrandid="'+c.pp+'">' + c.standardPpname + '</a>'
                                + '</div>'
                        }
                    })
                    var checkselectlist = "";
                    if (array.length>0) {
                        for (x in array) {
                            var brandname = "";
                            $.each(categoryData.data, function (w, q) {
                                //console.log(array[x].brandid+'     '+'pp'+q.pp+'       '+$("#appendlist").innertHTML)
                                if (array[x].brandid == 'pp'+q.pp){
                                    brandname = array[x].brandname;
                                    checkselectlist= '<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname" id="ppp'+array[x].brandid+'" href="javascript:void(0)">' + brandname + '<i class="delectthis" data-brandid="'+array[x].brandid+'">X</i></a ></div>';
                                } else {

                                }
                            });
                            //console.log(checkselectlist);
                            //$("#appendlist").append(checkselectlist);
                            checkselectlist = "";
                        }
                    }
                    $("#brandCategoryId").append(brand);
                }
            } else {
                layer.msg(categoryData.errMsg);
            }
        }
    })
});
$("#colsbtn,#errok-1,#clan-1").click(function () {
    $("#mask_1,.bg_1").hide(); //隐藏种子人群弹出框和遮罩
    $('body').css("overflow", "auto")
});

// 种子人群 点击分类 选择

$(".select_1111").click(function () {
    var Obj = {};
    if (array.length>=0) {
        for (x in array) {
            if ($(this).attr("value") == array[x].ppid) {
                return;
            }
        }
        Obj.ppid = $(this).attr("value");
        Obj.brandname = $(this).attr('brandtext');
        Obj.storeId = $(this).attr("storeId");
        array.push(Obj);
        arraydata.push($(this).attr("value"));
        $(this).next().attr("addeventclick","true");
        $("#appendlists").append('<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname fontcolor" href="javascript:void(0);"><span class="brandnamecss" style="color: rgba(16, 142, 233, 1) !important;">' + '分类:'+$(this).attr("brandtext") + '</span><i class="delectthis3" data-brandid="'+$(this).attr("storeId")+'" data-ppidd="'+$(this).attr("value")+'" style="color: rgba(16, 142, 233, 1) !important;">X</i></a ></div>');
    }
});
// 种子人群 点击分类 删除
$("body").on("click", ".delectthis3", function () {
    const ppid = $("body").find("#brandtitid"+$(this).attr("data-brandid")).siblings().find('#ppidd'+$(this).attr('data-ppidd')).siblings().attr("value");
    for (x in array) {
        if (ppid == array[x].ppid) {
            array.splice(x, 1);
            arraydata.splice(x, 1);
            $(this).parent().parent().remove();
            $("body").find("#brandtitid"+$(this).attr("data-brandid")).siblings().find('#ppidd'+$(this).attr('data-ppidd')).siblings().attr("addeventclick","false");
        }
    }
})


/*------------------------------------------------------------------------base hover事件---------------------------------------------------------------------------------------*/

$(".brandnamehover").mouseenter(function () {
    $(this).css("z-index", "-1");
    $(this).next().css("display", "block");
});
$(".brandhover").mouseleave(function () {
    $(".brandnamehover").css("z-index","10");
    $(".brandhover").hide();

});
/*------------------------------------------------------------------------标签扩散---------------------------------------------------------------------------------------*/

$("body").on("click", ".delectthis2", function () {
    //document.querySelector("#pp00704").setAttribute("data-checked","false")
    if (arraytwo.length>0) {
        for (var i=0;i<arraytwo.length; i++) {
            if (arraytwo[i].brandid == $(this).attr("data-brandid")) {
                arraytwo.splice(i, 1);
                arraydatatwo.splice(i, 1);
                $(this).parent().parent().remove();  //已选列表 单个x 删除
                //$("#appendlist").children().remove();
                $('body').find("#"+$(this).attr("data-brandid")).attr("data-checked","true");
                $('body').find("#"+$(this).attr("data-brandid")).removeClass("checkedstatus");
                /*$("#appendlists_2").children().remove();
                for (x in arraytwo) {
                    //$("#appendlist").append('<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname" href="javascript:void(0)">' + array[x].brandname + '<i class="delectthis" data-brandid="'+array[x].brandid+'">X</i></a ></div>');
                    $("#appendlists_2").append('<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname fontcolor" href="javascript:void(0);"><span class="brandnamecss" style="color: rgba(16, 142, 233, 1) !important;">' + arraytwo[x].brandname + '</span><i class="delectthis2" data-brandid="'+arraytwo[x].brandid+'" style="color: rgba(16, 142, 233, 1) !important;">X</i></a ></div>');
                }*/
                return;
            }
        }
    } else {
        $(this).parent().parent().remove();  //已选列表 单个x 删除
    }
});

/*  扩散标签页面  根据pId搜索二级分类*/
$(".selectBrandData").bind('click',function () {
    if ($(this).attr("addeventclick") == "false") {
        $("#mask_1").hide();
        $("#sendCategoryId2").attr("value",$(this).attr("value"));
        $("#sendCategoryId2").attr("storeId",$(this).attr('storeId'));
        /*根据pid查询二级分类数据*/
        $.ajax({
            type: 'POST',
            url: '/marketing/category/selectCategoryByPid?storesId=' + $(this).attr('storeId') + '&pid=' + $(this).attr("value"),
            dataType: 'json',
            contentType: "application/json",
            success: function (categoryData) {
                if (categoryData.success == true) {
                    if (categoryData.data.length>0) {
                        selectall2_data(categoryData.data);
                        var brand2 = "";

                        $("#brandCategoryId2").children().remove();
                        $("#appendlist2").children().remove();
                        $.each(categoryData.data, function (a, c) {
                            if (arraytwo.length>0) {
                                var checkedstatus = "";
                                var datachecked = "true";
                                for (x in arraytwo) {
                                    if (arraytwo[x].brandid == 'qq'+c.pp) {
                                        checkedstatus = "checkedstatus";
                                        datachecked = "false";
                                        brandname = arraytwo[x].brandname;
                                    }
                                }
                                brand2+= '<div class="col-md-2 col-sm-3 col-xs-4">'
                                    + '<a class="search-brandname cursorsetting cursorsettingtwo '+checkedstatus+'"  href="javascript:void(0);" onclick="barndclicktwo(this)" data-storesId="'+$(this).attr('storeId')+'" data-checked="'+datachecked+'" brandname="'+c.standardPpname+'" id="qq'+c.pp+'" data-checkedbrandid="'+c.pp+'">'+c.standardPpname+'</a>'
                                    + '</div>'
                            } else {
                                brand2+= '<div class="col-md-2 col-sm-3 col-xs-4">'
                                    + '<a class="search-brandname cursorsetting cursorsettingtwo"  href="javascript:void(0);" onclick="barndclicktwo(this)" data-storesId="'+$(this).attr('storeId')+'" data-checked="true" brandname="'+c.standardPpname+'" id="qq'+c.pp+'" data-checkedbrandid="'+c.pp+'">'+c.standardPpname+'</a>'
                                    + '</div>'
                            }
                        });
                        var checkselectlist = "";
                        if (arraytwo.length>0) {
                            for (x in arraytwo) {
                                var brandname = "";
                                $.each(categoryData.data, function (w, q) {
                                    //console.log(array[x].brandid+'     '+'pp'+q.pp+'       '+$("#appendlist").innertHTML)
                                    if (arraytwo[x].brandid == 'qq'+q.pp){
                                        brandname = arraytwo[x].brandname;
                                        checkselectlist= '<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname" id="qqq'+arraytwo[x].brandid+'" href="javascript:void(0)">' + brandname + '<i class="delectthis2" data-brandid="'+arraytwo[x].brandid+'">X</i></a ></div>';
                                    } else {
                                    }
                                });
                                $("#appendlist2").append(checkselectlist);
                                checkselectlist = "";
                            }
                        }
                        $("#brandCategoryId2").append(brand2);
                    }
                    $("#mask_2,.bg_2").show();
                    $('body').css("overflow", "hidden");
                } else {
                    layer.msg(categoryData.errMsg);
                }
            }
        })
    } else {
        return;
    }

});
//标签扩散 弹出框全选
function selectall2_data(seledata) {
    //console.log(seledata);
    if (seledata == undefined) {
        return;
    } else {
        $("#selectall2").bind('click',function() {
            $.each(seledata, function (a, c) {
                if (arraytwo.length>=0) {
                    for (x in arraytwo) {
                        if (arraytwo[x].brandid == 'qq'+c.pp) {
                            $('#qq'+c.pp).click();
                        }
                    }

                    $('#qq'+c.pp).click();

                } else {
                    alert("操作失败")
                }
            })
        });
    }
}selectall_data();
//标签扩散 弹出框选项操作
function appendElementtwo(id,name) {
    $("#appendlist2").append('<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname" id="qqq'+id+'" href="javascript:void(0);"><span class="brandnamecss">' + name + '</span><i class="delectthis2" data-brandid="'+id+'">X</i></a ></div>');
    $("#appendlists_2").append('<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname fontcolor" href="javascript:void(0);"><span class="brandnamecss" style="color: rgba(16, 142, 233, 1) !important;">' + name + '</span><i class="delectthis2" data-brandid="'+id+'" style="color: rgba(16, 142, 233, 1) !important;">X</i></a ></div>');
}
function barndclicktwo(ele){
    var Obj = {};
    const branid_res = $(ele).attr('id');
    const brandname_res = $(ele).attr('brandname');
    if ($(ele).attr("data-checked")== 'true') {
        $(ele).addClass('checkedstatus');
        $(ele).attr("data-checked","false")
    } else {
        $(ele).removeClass('checkedstatus');
        $(ele).attr("data-checked","true");
        //$('body').find("#"+$(ele).attr("data-brandid")).attr("data-checked","true");
        //$('body').find("#ppp"+$(ele).attr("data-brandid")).removeClass("checkedstatus");
        $('body').find("#qqq"+$(ele).attr('id')).parent().remove();
    }

    if (arraytwo.length>0) {
        for (var i=0;i<arraytwo.length; i++) {
            if (arraytwo[i].brandid == $(ele).attr('id')) {
                arraytwo.splice(i, 1);
                arraydatatwo.splice(i, 1);
                //console.log(array[i].brandname);
                //$("#appendlist").children().remove();
                $("#appendlists_2").children().remove();
                for (x in arraytwo) {
                    //$("#appendlist").append('<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname" href="javascript:void(0)">' + array[x].brandname + '<i class="delectthis" data-brandid="'+array[x].brandid+'">X</i></a ></div>');
                    $("#appendlists_2").append('<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname fontcolor" href="javascript:void(0);"><span class="brandnamecss" style="color: rgba(16, 142, 233, 1) !important;">' + arraytwo[x].brandname + '</span><i class="delectthis2" style="color: rgba(16, 142, 233, 1) !important;" data-brandid="'+arraytwo[x].brandid+'">X</i></a ></div>');
                }
                return;
            }
        }

        Obj.brandid = $(ele).attr('id');
        Obj.brandname = $(ele).attr('brandname');
        Obj.datachecked = $(ele).attr('data-checked');
        Obj.checkedbrandid = $(ele).attr('data-checkedbrandid');
        Obj.storeId = $(ele).attr('data-storesid');
        arraytwo.push(Obj);
        arraydatatwo.push($(ele).attr('data-checkedbrandid'));
        appendElementtwo(branid_res,brandname_res);
    } else {
        Obj.brandid = $(ele).attr('id');
        Obj.brandname = $(ele).attr('brandname');
        Obj.datachecked = $(ele).attr('data-checked');
        Obj.checkedbrandid = $(ele).attr('data-checkedbrandid');
        Obj.storeId = $(ele).attr('data-storesid');
        arraytwo.push(Obj);
        arraydatatwo.push($(ele).attr('data-checkedbrandid'));
        appendElementtwo(branid_res,brandname_res);

    }

}

/*  扩散标签页面  根据品牌名称搜索品牌*/
$("#sendCategoryId2").on('click',function (event) {
    event.stopPropagation();
    $("#sendCategoryId2").attr("value",$(this).attr('value'));
    $("#sendCategoryId2").attr("storeId",$(this).attr('storeId'));
    $.ajax({
        type: 'POST',
        url: '/marketing/category/selectCategoryByBrandName?storesId=' + $(this).attr('storeId') + '&pid=' + $(this).attr('value') + '&brandName=' + $("#brandId2").val(),
        dataType: 'json',
        contentType: "application/json",
        success: function (categoryData) {
            if (categoryData.success == true) {
                var brand2 = "";
                $("#brandCategoryId2").children().remove();
                if (categoryData.data.length>0) {
                    $.each(categoryData.data, function (a, c) {
                        if (arraytwo.length>0) {
                            var checkedstatus = "";
                            var datachecked = "true";
                            for (x in arraytwo) {
                                if (arraytwo[x].brandid == 'qq'+c.pp) {
                                    checkedstatus = "checkedstatus";
                                    datachecked = "false";
                                    brandname = arraytwo[x].brandname;
                                }
                            }
                            brand2+= '<div class="col-md-2 col-sm-3 col-xs-4">'
                                + '<a class="search-brandname cursorsetting '+checkedstatus+'"  href="javascript:void(0);" onclick="barndclicktwo(this)" data-storesId="'+$(this).attr('storeId')+'" data-checked="'+datachecked+'" brandname="'+c.standardPpname+'" id="qq'+c.pp+'" data-checkedbrandid="'+c.pp+'">'+c.standardPpname+'</a>'
                                + '</div>'
                        } else {
                            brand2+= '<div class="col-md-2 col-sm-3 col-xs-4">'
                                + '<a class="search-brandname cursorsetting"  href="javascript:void(0);" onclick="barndclicktwo(this)" data-storesId="'+$(this).attr('storeId')+'" data-checked="true" brandname="'+c.standardPpname+'" id="qq'+c.pp+'" data-checkedbrandid="'+c.pp+'">'+c.standardPpname+'</a>'
                                + '</div>'
                        }
                    });
                    var checkselectlist = "";
                    if (arraytwo.length>0) {
                        for (x in arraytwo) {
                            var brandname = "";
                            //$("#appendlist2").children().remove();
                            $.each(categoryData.data, function (w, q) {
                                //console.log(array[x].brandid+'     '+'pp'+q.pp+'       '+$("#appendlist").innertHTML)
                                if (arraytwo[x].brandid == 'qq'+q.pp){
                                    console.log(arraytwo[x].brandid);
                                    brandname = arraytwo[x].brandname;
                                    checkselectlist= '<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname" id="qqq'+arraytwo[x].brandid+'" href="javascript:void(0)">' + brandname + '<i class="delectthis2" data-brandid="'+arraytwo[x].brandid+'">X</i></a ></div>';
                                } else {
                                }
                            });

                            //$("#appendlist2").append(checkselectlist);
                            checkselectlist = "";
                        }
                    }
                    $("#brandCategoryId2").append(brand2);
                }
            } else {
                layer.msg(categoryData.errMsg);
            }
        }
    })
});

$("#colsbtn2,#errok-2,#clan-2").click(function () {
    $("#mask_2,.bg_2").hide(); //隐藏种子人群弹出框和遮罩
    $('body').css("overflow", "auto")
});

// 扩散标签 点击分类 选择

$(".select_111").click(function () {
    var Obj = {};
    if (arraytwo.length>=0) {
        for (x in arraytwo) {
            if ($(this).attr("value") == arraytwo[x].ppid) {
                return;
            }
        }
        Obj.ppid = $(this).attr("value");
        Obj.brandname = $(this).attr('brandtext');
        Obj.storeId = $(this).attr("storeId");
        arraytwo.push(Obj);
        arraydatatwo.push($(this).attr("value"));
        $(this).next().attr("addeventclick","true");
        $("#appendlists_2").append('<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname fontcolor" href="javascript:void(0);"><span class="brandnamecss" style="color: rgba(16, 142, 233, 1) !important;">' + '分类:'+$(this).attr("brandtext") + '</span><i class="delectthis3" data-brandid="'+$(this).attr("storeId")+'" data-ppidd="'+$(this).attr("value")+'" style="color: rgba(16, 142, 233, 1) !important;">X</i></a ></div>');
    }
});
// 扩散标签 点击分类 删除
$("body").on("click", ".delectthis3", function () {
    const ppid = $("body").find("#brandtitids"+$(this).attr("data-brandid")).siblings().find('#ppidd'+$(this).attr('data-ppidd')).siblings().attr('value');
    for (x in arraytwo) {
        if (ppid == arraytwo[x].ppid) {
            arraytwo.splice(x, 1);
            arraydatatwo.splice(x, 1);
            $(this).parent().parent().remove();
            $("body").find("#brandtitids"+$(this).attr("data-brandid")).siblings().find('#ppidd'+$(this).attr('data-ppidd')).siblings().attr("addeventclick","false");
        }
    }
})
