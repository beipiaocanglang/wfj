<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="../common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <title>新建营销活动</title>
    <%@ include file="../common/meta.jsp" %>
    <link rel="stylesheet" href="${ctx }/assets/css/newactivity.css">
</head>
<body class="gray-bg">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="col-sm-12">
            <div class="ibox float-e-margins">
                <div class="ibox-content">
                    <input type="hidden" name="hidden" value="${isSelect}" id="isSelect"/>
                    <ul class="nav nav-tabs" id="myTab">
                        <li role="presentation" class="active"><a href="#home">活动计划</a></li>
                        <li role="presentation"><a href="#profile">种子人群</a></li>
                        <li role="presentation"><a href="#messages">标签扩散</a></li>
                        <li role="presentation"><a href="#settings">选择渠道</a></li>
                        <li role="presentation"><a href="#settingss">效果分析</a></li>
                    </ul>
                </div>
                <div class="ibox-content">
                    <div class="tab-content">
                        <%--活动计划--%>
                        <form action="${ctx}/marketing/saveActivityPlan" method="post" class="form-horizontal tab-pane fade in active" id="home" enctype="multipart/form-data">
                            <%--活动计划保存后返回的主键ID--%>
                            <input type="hidden" name="s_actId" id="avtiveAct_id" value="${createEditActiveVo.actId}"/>
                            <input type="hidden" name="attachmentId" id="s_attachmentId" value="${createEditActiveVo.attachmentIdQrCode}"/>
                            <input type="hidden" id="huixianStoreIds" value="${createEditActiveVo.storeId}"/>
                            <%--活动计划--%>
                            <div class="container">
                                <div class="newactivity-content">
                                    <div class="row">
                                        <div class="activity-list col-xs-12">
                                            <div class="activi-them col-xs-12 col-sm-2">
                                                <span class="activi-title">活动主题</span>
                                            </div>
                                            <div class="col-xs-12 col-sm-10">
                                                <input name="s_theme" class="acitvi-val" type="text" id="theme" value="${createEditActiveVo.theme}">
                                            </div>
                                        </div>
                                        <div class="activity-list col-xs-12">
                                            <div class="activi-them col-xs-12 col-sm-2">
                                                <span class="activi-title">活动时间</span>
                                            </div>
                                            <div class="col-xs-12 col-sm-10">
                                                <input class="text-center" readonly="readonly" type="text" value="<fmt:formatDate value="${createEditActiveVo.startTime}" pattern="yyyy-MM-dd" type="both"/>"
                                                       placeholder="开始时间" id="date_begin" name="s_startTime"><span>至</span>
                                                <input class="text-center" readonly="readonly" type="text" value="<fmt:formatDate value="${createEditActiveVo.endTime}" pattern="yyyy-MM-dd" type="both"/>"
                                                       placeholder="结束时间" id="date_end" name="s_endTime">
                                            </div>
                                        </div>
                                        <div class="activity-list col-xs-12">
                                            <div class="activi-them col-xs-12 col-sm-2">
                                                <span class="activi-title">活动范围</span>
                                            </div>
                                            <div class="col-xs-12 col-sm-10 buttonstyle">
                                                <select name="s_country" id="country" class="selectpicker show-menu-arrow form-control" multiple>
                                                    <span value="" >全选／不选</span>
                                                    <c:forEach items="${list}" var="storesVo" varStatus="sv">
                                                        <optgroup label="${storesVo.provinceCity}" >
                                                            <c:forEach items="${storesVo.stores}" var="store" varStatus="st">
                                                                <option value="${store.storeId}">${store.storeName}</option>
                                                            </c:forEach>
                                                        </optgroup>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="activity-list col-xs-12">
                                            <div class="kpi-titlecontent">
                                                <span class="kpi-title">KPI设置</span>
                                            </div>
                                            <div class="kpitable">
                                                <div class="kpitabtitle-left">
                                                    <div class="kpitabtitle-leftcontent">
                                                        <div class="kpi-select kpi-selectfirst">
                                                            KPI指标选择：
                                                        </div>
                                                        <div class="kpi-select kpi-selectlast">
                                                            指标设置：
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="kpitabtitle-right">
                                                    <div class="kpitabtitle-rightcontent">
                                                        <div class="kpi-select kpi-selectfirst">
                                                            <div class="kipselect-tab">
                                                                <span class="tabbtn tabbtn1">销售额</span>
                                                                <span class="tabbtn tabbtn2">客流</span>
                                                                <span class="tabbtn tabbtn3">毛利</span>
                                                            </div>
                                                        </div>
                                                        <div class="kpi-select kpi-selectlast">
                                                            <div class="kpiselecttextval kiphidden1">
                                                                <span class="kpiselecttext">销售额（元）</span>
                                                                <input class="kpiselectval" value="${createEditActiveVo.saleKpiValue}" name="s_sellAmount" id="s_sellAmount" type="text" placeholder="请输入相关指标">
                                                            </div>
                                                            <div class="kpiselecttextval kiphidden2">
                                                                <span class="kpiselecttext">客流</span>
                                                                <input class="kpiselectval" value="${createEditActiveVo.keliuKpiValue}" name="s_passengerFlow" id="s_passengerFlow" type="text" placeholder="请输入相关指标">
                                                            </div>
                                                            <div class="kpiselecttextval kiphidden3">
                                                                <span class="kpiselecttext">毛利（元）</span>
                                                                <input class="kpiselectval" value="${createEditActiveVo.maoliKpiValue}" name="s_grossProfit" id="s_grossProfit" type="text" placeholder="请输入相关指标">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="activity-list col-xs-12">
                                            <div class="kpi-titlecontent">
                                                <span class="kpi-title">活动内容</span>
                                            </div>
                                            <div class="kpitable activitycontent">
                                                <div class="kpitabtitle-left">
                                                    <div class="kpitabtitle-leftcontent">
                                                        <div class="kpi-select kpi-selectfirst">
                                                            H5链接：
                                                        </div>
                                                        <div class="kpi-select kpi-selectlast">
                                                            上传二维码：
                                                        </div>
                                                        <div class="kpi-select kpi-selectlast">
                                                            活动内容描述：
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="kpitabtitle-right">
                                                    <div class="kpitabtitle-rightcontent">
                                                        <div class="kpi-select kpi-selectfirst">
                                                            <div class="kipselect-tab">
                                                                <input class="h5href" type="text" name="s_h5Url" value="${createEditActiveVo.h5Url}">
                                                            </div>
                                                        </div>
                                                        <div class="kpi-select kpi-selectlast">
                                                            <div class="kipselect-tab">
                                                                <div class="file-input">
                                                                    <p class="input-container">
                                                                        <img class="erweima" width="35" height="35" src="${ctx }/assets/img/addfile.png" alt="">
                                                                        <input type="file" name="file" id="upload" accept="image/*">
                                                                    </p>
                                                                    <span id="name">${createEditActiveVo.attachmentName}</span>
                                                                    <span id="tishi">（图片大小不能超过300kb）</span>
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <div class="kpi-select kpi-selectlast">
                                                            <textarea class="textval" name="s_content">${createEditActiveVo.content}</textarea>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="activity-list col-xs-12 text-center savebtn">
                                            <button type="submit" id="activi-them-savebtn">下一步</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>

                        <%--种子人群--%>
                        <form action="${ctx}/marketing/seed/saveSeedData" method="post" class="form-horizontal tab-pane fade in" id="profile" enctype="multipart/form-data">
                            <%--活动计划保存后返回的主键ID--%>
                            <input type="hidden" name="s_actId" id="act_id" value="${createEditActiveVo.actId}"/>
                            <%--活动计划保存时选择的门店ID--%>
                            <input type="hidden" name="s_storesId" id="stores_id" value="${createEditActiveVo.storeId}"/>
                            <%--全部数据的数组--%>
                            <input type="hidden" name="s_allPpArray" id="s_allPpArray" value='${createEditActiveVo.seedCategoryData}'/>
                            <%--只有品牌ID 数组--%>
                            <input type="hidden" name="s_ppIdArray" id="s_ppIdArray"/>
                            <%--用户上传的种子文件ID--%>
                            <input type="hidden" name="s_attachmentId" id="attachmentId" value="${createEditActiveVo.attachmentIdImportFile}"/>
                            <%--种子人群--%>
                            <div class="container">
                                <div class="population">
                                    <div class="userimport">
                                        <span>种子用户导入</span>
                                        <span>如您已具备比较完善的种子用户筛选机智，您可自行导入筛选结果</span>
                                    </div>
                                    <div class="uploadbtn">
                                        <a href="javascript:;">
                                            上传文件
                                            <div class="file-input">
                                                <input type="file" name="file" id="upload_1">
                                            </div>
                                       </a>
                                        <c:if test="${createEditActiveVo.importAttachmentName != null}">
                                            <span id="name_1" style="display: inline-block;">${createEditActiveVo.importAttachmentName}</span>
                                        </c:if>
                                        <span id="name_1"></span>

                                        <a href="${ctx}/marketing/downloadSeedTemplate">下载模板</a>
                                    </div>
                                    <div class="brand">
                                        <span>品类/品牌维度筛选</span>
                                        <div class="date-select">
                                            <span class="date-title">时间筛选</span>
                                            <span class="date-text">
                                                <input class="text-center" readonly="readonly" type="text" placeholder="开始时间" id="date_begin_1" name="s_startTime">
                                                <span>至</span>
                                                <input class="text-center" readonly="readonly" type="text" placeholder="结束时间" id="date_end_1" name="s_endTime">
                                            </span>
                                        </div>
                                        <div class="row brandtop">
                                            <div class="col-md-8 brandborder brand-left">
                                                <div class="brandlist">
                                                    <c:forEach items="${categoryList}" var="storeName" varStatus="cn">
                                                        <div class="brand-name-list">
                                                            <div class="brand-name-title" data-brandtitleid="${storeName.storeId}" id="brandtitid${storeName.storeId}">${storeName.storeName}</div>
                                                            <c:forEach items="${storeName.categroyList}" var="categoryName" varStatus="sn">
                                                                <div class="col-md-3 col-sm-4 col-xs-6">
                                                                    <div class="selectoggle">
                                                                        <a class="brandname brandnamehover" href="javascript:;">${categoryName.standardPpname}</a>
                                                                        <div class="brandhover">
                                                                            <span class="select_111 select_1111" brandtext="${categoryName.standardPpname}" storeId="${storeName.storeId}" id="ppidd${categoryName.pp}" value="${categoryName.pp}">选此分类</span>
                                                                            <span class="select_222 select_2222" addeventclick="false" storeId="${storeName.storeId}" value="${categoryName.pp}">选择品牌</span>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                            </c:forEach>
                                                        </div>
                                                    </c:forEach>
                                                </div>
                                            </div>
                                            <div class="col-md-4 brand-right">
                                                <div class="brand-screen text-center">
                                                    <span class="screenuser">筛选种子用户数量</span>
                                                </div>
                                                <div class="brand-borders">
                                                    <div class="screen-num text-center"><label id="hbashZhiSuan">${createEditActiveVo.seedNum}</label></div>
                                                    <div class="text-center screen-cal">
                                                        <button type="button" onclick="hbashZhiSuan()" class="btn btn-info">智算</button>
                                                        <button type="button" style="display: none" id="dowLoadExcle" class="btn btn-info">下载</button>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-md-8 brandselectlist">
                                                <span class="checkselect">已选：</span>
                                                <div class="col-md-12 brandlistborder" id="appendlists">
                                                </div>
                                            </div>
                                            <div class="savebtn col-md-12 text-center">
                                                <button type="submit" id="save_seedData">下一步</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>

                        <%--标签扩散--%>
                        <form action="${ctx}/marketing/label/labelSpread" method="post" class="form-horizontal tab-pane fade in" id="messages">
                            <input type="hidden" name="s_actId" id="s_actId" value="${createEditActiveVo.actId}"/>
                            <%--活动计划保存时选择的门店ID--%>
                            <input type="hidden" name="s_storesId" id="s_stores_id" value="${createEditActiveVo.storeId}"/>

                            <%--标签扩撒--%>
                            <div class="container">
                                <div class="tab-spread">
                                    <div class="tab-spreadlist">
                                        <span class="spreadtitle">年龄</span>
                                        <p class="spreacheck">
                                            <label>
                                                <input type="checkbox" id="age" value="所有" onclick="checkAll(this,'age')" ${labelMapVo.age[0].checkedNum eq 6 ?'checked':''}> 所有
                                            </label>
                                            <c:forEach items="${labelMapVo.age}" var="al" varStatus="x">
                                                <label>
                                                    <input type="checkbox" name="age" value="${al.labelValue}" ${al.isChecked}> ${al.labelValue}
                                                </label>
                                            </c:forEach>
                                        </p>
                                    </div>
                                    <div class="tab-spreadlist">
                                        <span class="spreadtitle">性别</span>
                                        <p class="spreacheck">
                                            <label>
                                                <input type="checkbox" id="gender" value="所有" onclick="checkAll(this,'gender')" ${labelMapVo.gender[0].checkedNum eq 3 ?'checked':''}> 所有
                                            </label>
                                            <c:forEach items="${labelMapVo.gender}" var="gdl" varStatus="g">
                                                <label>
                                                    <input type="checkbox" name="gender" value="${gdl.labelValue}" ${gdl.isChecked}> ${gdl.labelValue}
                                                </label>
                                            </c:forEach>
                                        </p>
                                    </div>
                                    <div class="tab-spreadlist">
                                        <span class="spreadtitle">地域</span>
                                        <div class="cityselect">
                                            <input type="hidden" id="province" value="${labelMapVo.province}"/>
                                            <select id="s_province" name="province"></select>  
                                            <input type="hidden" id="city" value="${labelMapVo.city}"/>
                                            <select id="s_city" name="city"></select>  
                                            <input type="hidden" id="county" value="${labelMapVo.county}"/>
                                            <select id="s_county" name="county"></select>
                                        </div>
                                    </div>
                                    <div class="tab-spreadlist">
                                        <span class="spreadtitle">婚否</span>
                                        <p class="spreacheck">
                                            <label>
                                                <input type="checkbox" id="isMarriage" value="所有" onclick="checkAll(this,'isMarriage')" ${labelMapVo.isMarriage[0].checkedNum eq 3 ?'checked':''}> 所有
                                            </label>
                                            <c:forEach items="${labelMapVo.isMarriage}" var="iml" varStatus="m">
                                                <label>
                                                    <input type="checkbox" name="isMarriage" value="${iml.labelValue}" ${iml.isChecked}> ${iml.labelValue}
                                                </label>
                                            </c:forEach>
                                        </p>
                                    </div>
                                    <div class="tab-spreadlist">
                                        <span class="spreadtitle">消费定位</span>
                                        <p class="spreacheck">
                                            <c:forEach items="${labelMapVo.consumeLocation}" var="cll" varStatus="c">
                                                <label>
                                                    <input type="checkbox" name="consumeLocation" value="${cll.labelValue}" ${cll.isChecked}> ${cll.labelValue}
                                                </label>
                                            </c:forEach>
                                        </p>
                                    </div>
                                    <div class="tab-spreadlist">
                                        <span class="spreadtitle">购物频次</span>
                                        <p class="spreacheck">
                                            <label>
                                                <input type="checkbox" id="shoppingFrequency" value="所有" onclick="checkAll(this,'shoppingFrequency')" ${labelMapVo.shoppingFrequency[0].checkedNum eq 3 ?'checked':''}> 所有
                                            </label>
                                            <c:forEach items="${labelMapVo.shoppingFrequency}" var="sfl" varStatus="c">
                                                <label>
                                                    <input type="checkbox" name="shoppingFrequency" value="${sfl.labelValue}" ${sfl.isChecked}> ${sfl.labelValue}
                                                </label>
                                            </c:forEach>
                                        </p>
                                    </div>
                                    <div class="tab-spreadlist">
                                        <span class="spreadtitle">客单价</span>
                                        <p class="spreacheck">
                                            <label>
                                                <input type="checkbox" id="perTicketSalesPrice" value="所有" onclick="checkAll(this,'perTicketSalesPrice')" ${labelMapVo.perTicketSalesPrice[0].checkedNum eq 5 ?'checked':''}> 所有
                                            </label>
                                            <c:forEach items="${labelMapVo.perTicketSalesPrice}" var="psp" varStatus="c">
                                                <label>
                                                    <input type="checkbox" name="perTicketSalesPrice" value="${psp.labelValue}" ${psp.isChecked}> ${psp.labelValue}
                                                </label>
                                            </c:forEach>
                                        </p>
                                    </div>
                                    <div class="tab-spreadlist">
                                        <span class="spreadtitle">RFV标签</span>
                                        <p class="spreacheck">
                                            <label>
                                                <input type="checkbox" id="rfvLabel" value="所有" onclick="checkAll(this,'rfvLabel')" ${labelMapVo.rfvLabel[0].checkedNum eq 6 ?'checked':''}> 所有
                                            </label>
                                            <c:forEach items="${labelMapVo.rfvLabel}" var="rfvL" varStatus="c">
                                                <label>
                                                    <input type="checkbox" name="rfvLabel" value="${rfvL.labelValue}" ${rfvL.isChecked}> ${rfvL.labelValue}
                                                </label>
                                            </c:forEach>
                                        </p>
                                    </div>
                                    <div class="brand tab-spreadlist">
                                        <div class="savebtn col-md-12 text-center">
                                            <button type="submit">下一步</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>

                        <%--选择渠道--%>
                        <form action="${ctx}/channel/saveChannelData" method="post" class="form-horizontal tab-pane fade in" id="settings">
                            <input type="hidden" name="s_actId" id="actId" value="${createEditActiveVo.actId}"/>
                            <input type="hidden" name="s_smsChannelId" id="s_smsChannelId" value="${channelVo.s_smsChannelId}"/>
                            <input type="hidden" name="s_friChannelId" id="s_friChannelId" value="${channelVo.s_friChannelId}"/>
                            <%--选择渠道--%>
                            <div class="container">
                                <div class="select-channel">
                                    <div class="select-channelpreadlist">
                                        <span class="select-channeltitle">基本信息</span>
                                        <div class="channel-information">
                                            <table class="table table-responsive tablestyle">
                                                <thead>
                                                <tr>
                                                    <th>活动KPI</th>
                                                    <th>种子用户数</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <tr>
                                                    <td id="channel_activeKpi">${channelVo.activeKpi}</td>
                                                    <td id="channel_seedNum">${channelVo.seedNum}</td>
                                                </tr>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                    <div class="select-channelpreadlist">
                                        <span class="select-channeltitle">选择投放渠道</span>
                                        <div class="channel-information">
                                            <div class="delivery">
                                                <div class="col-md-4 message">
                                                    <div class="messagebg text-center">短信</div>
                                                    <input type="hidden" name="sms_selectChannel" id="sms_selectChannel" value="${channelVo.sms_selectChannel}">
                                                    <div class="prompt_1">
                                                        <p class="prompt">提示：参考历史数据，短信的转化率为
                                                            <label id="channel_sms">42.0%</label>
                                                            <input type="hidden" name="sms_conversionRate" id="sms_conversionRate" value="${channelVo.sms_conversionRate}">
                                                        </p >
                                                        <p class="prompt promptfontsize">建议投放人群数量</p >
                                                    </div>
                                                    <input type="hidden" name="sms_deliveryNum" id="sms_deliveryNum" value="${channelVo.sms_deliveryNum}">
                                                    <div class="number" id="sms_peopleNo">${channelVo.sms_deliveryNum}</div>
                                                    <div class="select-channelbottom" id="selectbtn_1">
                                                        <div class="col-md-6 col-xs-6 usertbtn text-center" data-active="false">
                                                            <input type="hidden" id="sms_YseendId" value="${channelVo.sms_ContainSeedUser}">
                                                            <span>包含种子用户</span>
                                                        </div>
                                                        <div class="col-md-6 col-xs-6 usertbtn text-center">
                                                            <input type="hidden" id="sms_NseendId">
                                                            <span>不包含种子用户</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-1"></div>
                                                <div class="col-md-4 message message-right">
                                                    <div class="messagebg text-center">朋友圈</div>
                                                    <input type="hidden" name="fri_selectChannel" id="fri_selectChannel" value="${channelVo.fri_selectChannel}">
                                                    <div class="prompt_1">
                                                        <p class="prompt">提示：参考历史数据，短信的转化率为
                                                            <label id="channel_friends">36.0%</label>
                                                            <input type="hidden" name="fri_conversionRate" id="fri_conversionRate" value="${channelVo.fri_conversionRate}">
                                                        </p >
                                                        <p class="prompt promptfontsize">建议投放人群数量</p >
                                                    </div>
                                                    <input type="hidden" name="fri_deliveryNum" id="fri_deliveryNum" value="${channelVo.fri_deliveryNum}">
                                                    <div class="number" id="fri_peopleNo">${channelVo.sms_deliveryNum}</div>
                                                    <div class="select-channelbottom" id="selectbtn_2">
                                                        <div class="col-md-6 col-xs-6 usertbtn text-center" data-active="false">
                                                            <input type="hidden" id="fri_YseendId" value="${channelVo.fri_ContainSeedUser}">
                                                            <span >包含种子用户</span>
                                                        </div>
                                                        <div class="col-md-6 col-xs-6 usertbtn text-center">
                                                            <input type="hidden" id="fri_NseendId">
                                                            <span >不包含种子用户</span>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="col-md-3"></div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="select-channelpreadlist">
                                        <span class="select-channeltitle">选择投放时间</span>
                                        <div class="date-select" id="duanxin">
                                            <span class="date-title datetitstyle">短信</span>
                                            <span class="date-text">
                                                <input class="text-center" readonly="readonly" type="text" placeholder="开始时间" value="${channelVo.sms_deliveryStartTime}" id="sms_deliveryStartTime" name="sms_deliveryStartTime">
                                                <span>至</span>
                                                <input class="text-center" readonly="readonly" type="text" placeholder="结束时间" value="${channelVo.sms_deliveryEndTime}" id="sms_deliveryEndTime" name="sms_deliveryEndTime">
                                            </span>
                                        </div>
                                        <div class="date-select" id="pengyouquan">
                                            <span class="date-title datetitstyle">朋友圈</span>
                                            <span class="date-text">
                                                <input class="text-center" readonly="readonly" type="text" placeholder="开始时间" value="${channelVo.fri_deliveryStartTime}" id="fri_deliveryStartTime" name="fri_deliveryStartTime">
                                                <span>至</span>
                                                <input class="text-center" readonly="readonly" type="text" placeholder="结束时间" value="${channelVo.fri_deliveryEndTime}" id="fri_deliveryEndTime" name="fri_deliveryEndTime">
                                            </span>
                                        </div>
                                    </div>
                                    <div class="channel-bottom">
                                        <button type="submit" id="channelsave">保存</button>
                                    </div>
                                </div>
                            </div>
                        </form>

                        <%--效果分析--%>
                        <form action="" method="post" class="form-horizontal tab-pane fade in" id="settingss">
                            <input type="hidden" name="s_actId" value="${actId}"/>
                            <%--效果分析--%>
                            <div class="container">
                                <div class="select-channelpreadlist">
                                    <span class="select-channeltitle">实时效果分析</span>
                                    <div class="analysis">
                                        <div class="col-md-5 analysistab">
                                            <div class="analysistab-title">XX分析</div>
                                            <div id="container" style="height: 300px;margin: 0 auto"></div>
                                        </div>
                                        <div class="col-md-2"></div>
                                        <div class="col-md-5 analysistab">
                                            <div class="analysistab-title">XX分析</div>
                                            <div id="container2" style="height: 300px;margin: 0 auto"></div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="bg_1"></div>
<div class="brand-mask col-md-12 col-sm-12 col-xs-12" style="display: none" id="mask_1">
    <i class="colsbtn" id="colsbtn">
        <img width="32" height="32" src="${ctx }/assets/img/clsbtn.png">
    </i>
    <div class="brand-search">
        <span class="search-brand">搜索品牌：</span>
        <input class="search-text" type="text" id="brandId">
        <a class="btn btnsearchclick" id="sendCategoryId">搜索</a>
    </div>
    <div class="search-brandlist">
        <div class="col-md-12 brandlistborder"  id="brandCategoryId">

        </div>
        <div class="selectbtn">
            <button class="search-btn" id="selectall">全选</button>
            <button class="search-btn" id="errok-1">确定</button>
            <button class="search-btn" id="clan-1">取消</button>
        </div>
        <div class="checkselects">
            <div class="brand-search">
                <span class="search-brand">已选品牌：</span>
            </div>
            <div class="col-md-12 brandlistborder" id="appendlist">
            </div>
        </div>
    </div>
</div>
<div class="bg_2"></div>
<div class="brand-mask col-md-12 col-sm-12 col-xs-12" style="display: none" id="mask_2">
    <i class="colsbtn" id="colsbtn2">
        <img width="32" height="32" src="${ctx }/assets/img/clsbtn.png">
    </i>
    <div class="brand-search">
        <span class="search-brand">搜索品牌：</span>
        <input class="search-text" type="text" id="brandId2">
        <a class="btn btnsearchclick" id="sendCategoryId2">搜索</a>
    </div>
    <div class="search-brandlist">
        <div class="col-md-12 brandlistborder"  id="brandCategoryId2">

        </div>
        <div class="selectbtn">
            <button class="search-btn" id="selectall2">全选</button>
            <button class="search-btn" id="errok-2">确定</button>
            <button class="search-btn" id="clan-2">取消</button>
        </div>
        <div class="checkselects">
            <div class="brand-search">
                <span class="search-brand">已选品牌：</span>
            </div>
            <div class="col-md-12 brandlistborder" id="appendlist2">
            </div>
        </div>
    </div>
</div>

<%--判断kpi是否显示 和点击 --%>
<script type="text/javascript">
    $(function () {
        var sellAmount = $("#s_sellAmount").attr("value");
        if (sellAmount != ""){
            /*kpi 销售额 点击*/
            $(".kiphidden1").toggleClass("kipshow");
            if ($(".kiphidden1").hasClass("kipshow")) {
                $("span.tabbtn1").addClass("tabbtnactive");
            } else {
                $("span.tabbtn1").removeClass("tabbtnactive");
            }
        }
        var passengerFlow = $("#s_passengerFlow").attr("value");
        if (passengerFlow != ""){
            /*kpi 人流 点击*/
            $(".kiphidden2").toggleClass("kipshow");
            if ($(".kiphidden2").hasClass("kipshow")) {
                $("span.tabbtn2").addClass("tabbtnactive");
            } else {
                $("span.tabbtn2").removeClass("tabbtnactive");
            }
        }
        var grossProfit = $("#s_grossProfit").attr("value");
        if (grossProfit != ""){
            /*kpi 毛利 点击*/
            $(".kiphidden3").toggleClass("kipshow");
            if ($(".kiphidden3").hasClass("kipshow")) {
                $("span.tabbtn3").addClass("tabbtnactive");
            } else {
                $("span.tabbtn3").removeClass("tabbtnactive");
            }
        }

        /*活动计划页面回显 门店信息*/
        var huixianStoreIds = $("#huixianStoreIds").val();
        if (huixianStoreIds!=null && huixianStoreIds!=""){
            var ids = huixianStoreIds.split(",");
            $('.selectpicker').selectpicker('val', ids);
        }
    })

    /*上面五个标签的点击*/
    $(function () {
        var a = $("#isSelect").val() == ""? "0" : $("#isSelect").val();
        $('#myTab a:eq('+a+')').tab('show');
        $('#myTab a:eq('+a+')').addClass('tabon');
        $('#myTab a:eq('+a+')').addClass('tabfontcolor');

        /*$('#myTab a').attr('disabled','disabled').css('pointer-events','none');*/
    });
    $('#myTab a').click(function (e) {
        e.preventDefault();
        $('#myTab a').removeClass("tabon");
        $(this).parent().addClass('tabon').siblings().removeClass("tabon");
        $('#myTab a').removeClass("tabfontcolor");
        $(this).addClass('tabfontcolor');
        $(this).tab('show');
    });

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
</script>

<%--种子人群操作--%>
<script type="text/javascript">
    /*种子人群的品类赋值*/
    $('#save_seedData').on('click', function () {
        if ($("#stores_id").val() == "" || $("#stores_id").val() == null) {
            layer.msg("请返回活动计划页面选择门店");
            return false;
        } else if($("#date_begin_1").val() == ""){
            layer.msg("请输入开始的筛选时间");
            return false;
        }else if($("#date_end_1").val() == ""){
            layer.msg("请输入结束的筛选时间");
            return false;
        } else if (arraydata.length<1) {
            layer.msg("请选择品牌");
            return false;
        } else {
            var jsonArray = JSON.stringify(array);
            var jsonArraydata = JSON.stringify(arraydata);
            $("#s_allPpArray").val(jsonArray);
            $("#s_ppIdArray").val(jsonArraydata);

            $('#profile').submit();
        }
    })

    /*种子人群回显 时间和数组*/
    $(function () {
        /*判断是否显示下载按钮*/
        var seedNum = $("#hbashZhiSuan").text();
        if (seedNum != null && seedNum > 0) {
            $("#dowLoadExcle").show();
        }

        var jsonSeedData = $("#s_allPpArray").val();
        var arrySeed = [];
        if(jsonSeedData!=""){
            var a = jsonSeedData.split("_")[0];/*包含所有数据的数组*/
            var b = jsonSeedData.split("_")[1];/*包含时间的数组*/
            var c = jsonSeedData.split("_")[2];/*智算时 只包含品牌ID的数组*/
            array = JSON.parse(a);/*包含所有数据的数组*/
            arraydata = JSON.parse(c);/*智算时 只包含品牌ID的数组*/

            arrySeed = JSON.parse(b);/*包含时间的数组*/
            $("#date_begin_1").val(arrySeed[0]);
            $("#date_end_1").val(arrySeed[1]);

            for (var x=0;x<array.length;x++){
                var d = array[x];
                if (d.brandid != undefined && d.brandid!="undefined"){
                    //有值就是品牌
                    $("#appendlists").append('<div class="col-md-2 col-sm-3 col-xs-4" id="delect'+d.brandid+'"><a class="search-brandname fontcolor" href="javascript:void(0);"><span class="brandnamecss" style="color: rgba(16, 142, 233, 1) !important;">'+'品牌:' + d.brandname + '</span><i class="delectthis" data-brandid="'+d.brandid+'" style="color: rgba(16, 142, 233, 1) !important;">X</i></a ></div>');
                }
                if (d.ppid != undefined && d.ppid!="undefined"){
                    //有值就是品类
                    $("#appendlists").append('<div class="col-md-2 col-sm-3 col-xs-4"><a class="search-brandname fontcolor" href="javascript:void(0);"><span class="brandnamecss" style="color: rgba(16, 142, 233, 1) !important;">' + '分类:'+d.brandname + '</span><i class="delectthis3" data-brandid="'+d.storeId+'" data-ppidd="'+d.ppid+'" style="color: rgba(16, 142, 233, 1) !important;">X</i></a ></div>');
                }
            }
        }
    })
</script>

<%--智算功能--%>
<script type="text/javascript">
    /*智算下载*/
    $("#dowLoadExcle").on("click", function (evt) {
        var e = evt || window.event;
        e.preventDefault();
        if ($("#stores_id").val() == "" || $("#stores_id").val() == null) {
            layer.msg("请返回活动计划页面选择门店");
            return false;
        } else if($("#date_begin_1").val() == ""){
            layer.msg("请输入开始的筛选时间");
            return false;
        }else if($("#date_end_1").val() == ""){
            layer.msg("请输入结束的筛选时间");
            return false;
        } else if (arraydata.length<1) {
            layer.msg("请选择品牌");
            return false;
        } else {
            window.open('${ctx}/marketing/seed/dowloadSeedExcel?cityids='+$("#stores_id").val()+'&ppids='+arraydata+'&years='+$("#date_begin_1").val()+','+$("#date_end_1").val());
        }
    });
    /*获取智算数据*/
    function hbashZhiSuan() {
        if ($("#stores_id").val() == "" || $("#stores_id").val() == null) {
            layer.msg("请返回活动计划页面选择门店");
            return false;
        } else if($("#date_begin_1").val() == ""){
            layer.msg("请输入开始的筛选时间");
            return false;
        }else if($("#date_end_1").val() == ""){
            layer.msg("请输入结束的筛选时间");
            return false;
        } else if (arraydata.length<1) {
            layer.msg("请选择品牌");
            return false;
        } else {
            $.ajax({
                type: 'POST',
                url: '/marketing/seed/getHbaseData?cityids='+$("#stores_id").val()+'&ppids='+arraydata+'&years='+$("#date_begin_1").val()+','+$("#date_end_1").val(),
                dataType: 'json',
                contentType: "application/json",
                success: function (zhisuanData) {
                    if (zhisuanData.msg == "success") {
                        $("#hbashZhiSuan").text(zhisuanData.data);
                        if (zhisuanData.data>0){
                            $("#dowLoadExcle").show();
                        }
                    } else {
                        layer.msg(zhisuanData.msg);
                    }
                }
            })
        }
    }
</script>

<%--选择渠道 --%>
<script type="text/javascript">
    /*保存渠道前校验数据是否为空*/
    $("#channelsave").on("click", function (evt) {
        var isSelectSms = $("#sms_selectChannel").val();
        var isSelectFri = $("#fri_selectChannel").val();
        if(isSelectSms!=""){
            if($("#sms_deliveryStartTime").val() == ""){
                layer.msg("请选择开始投放时间");
                return false;
            }
            if($("#sms_deliveryEndTime").val() == ""){
                layer.msg("请选择结束投放时间");
                return false;
            }
        }
        if(isSelectFri!=""){
            if($("#fri_deliveryStartTime").val() == ""){
                layer.msg("请选择开始投放时间");
                return false;
            }
            if($("#fri_deliveryEndTime").val() == ""){
                layer.msg("请选择结束投放时间");
                return false;
            }
        }
        if(isSelectFri=="" && isSelectSms==""){
            layer.msg("请选择渠道");
            return false;
        }
        $("#settings").submit();
    })

    /*短信是否包含种子用户 切换*/
    $("#selectbtn_1 .usertbtn").click(function () {
        $("#sms_selectChannel").val("0");/*选择了短信渠道*/

        $(this).addClass('userbtnactive').siblings().removeClass("userbtnactive");

        if ($(this).attr('data-active')) {
            $("#sms_NseendId").removeAttr("name");
            $("#sms_NseendId").removeAttr("value");

            $(this).children("input").attr("name","sms_ContainSeedUser");
            $(this).children("input").attr("value","0");
        } else {
            $("#sms_YseendId").removeAttr("name");
            $("#sms_YseendId").removeAttr("value");

            $(this).children("input").attr("name","sms_ContainSeedUser");
            $(this).children("input").attr("value","1");
        }
    });

    /*朋友圈是否包含种子用户 切换*/
    $("#selectbtn_2 .usertbtn").click(function () {
        $("#fri_selectChannel").val("0");/*选择了朋友圈渠道*/

        $(this).addClass('userbtnactive').siblings().removeClass("userbtnactive");

        if ($(this).attr('data-active')) {
            $("#fri_NseendId").removeAttr("name");
            $("#fri_NseendId").removeAttr("value");

            $(this).children("input").attr("name","fri_ContainSeedUser");
            $(this).children("input").attr("value","0");
        } else {
            $("#fri_YseendId").removeAttr("name");
            $("#fri_YseendId").removeAttr("value");

            $(this).children("input").attr("name","fri_ContainSeedUser");
            $(this).children("input").attr("value","1");
        }
    });

    /*渠道页面的回显 或 新增时的 计算*/
    $(function () {
        /*给短信渠道的转化率拼接 % */
        if ($("#sms_conversionRate").val() != ""){
            $("#channel_sms").text($("#sms_conversionRate").val() + "%");
        }

        /*给朋友圈渠道的转化率拼接 % */
        if ($("#fri_conversionRate").val() != ""){
            $("#channel_friends").text($("#fri_conversionRate").val() + "%");
        }

        /*去除短信渠道的 投放人数中的小数点*/
        var sms_deliveryNum = $("#sms_deliveryNum").val();
        if (sms_deliveryNum != "" && sms_deliveryNum.lastIndexOf(".")!=-1){
            sms_deliveryNum = sms_deliveryNum.substring(0,sms_deliveryNum.length-2);
            $("#sms_peopleNo").text(sms_deliveryNum);
            $("#sms_deliveryNum").val(sms_deliveryNum);
        }

        /*去除朋友渠道的 投放人数中的小数点*/
        var fri_deliveryNum = $("#fri_deliveryNum").val();
        if (fri_deliveryNum != "" && fri_deliveryNum.lastIndexOf(".")!=-1){
            fri_deliveryNum = fri_deliveryNum.substring(0,fri_deliveryNum.length-2);
            $("#fri_peopleNo").text(fri_deliveryNum);
            $("#fri_deliveryNum").val(fri_deliveryNum);
        }

        /*是否选择了短信渠道*/
        if ($("#sms_selectChannel").val() == "0"){
            if($("#sms_YseendId").val() == 0){
                $("#selectbtn_1 div:eq(0)").addClass('userbtnactive');

                $("#sms_YseendId").attr("name","sms_ContainSeedUser");
                $("#sms_YseendId").attr("value","0");

            }else if($("#sms_YseendId").val() == 1){
                $("#selectbtn_1 div:eq(1)").addClass('userbtnactive');

                $("#sms_NseendId").attr("name","sms_ContainSeedUser");
                $("#sms_NseendId").attr("value","1");

                $("#sms_YseendId").removeAttr("name");
                $("#sms_YseendId").removeAttr("value");
            }
        };

        /*是否选择了朋友圈渠道*/
        if ($("#fri_selectChannel").val() == "0"){
            if($("#fri_YseendId").val() == 0){
                $("#selectbtn_2 div:eq(0)").addClass('userbtnactive');

                $("#fri_YseendId").attr("name","fri_ContainSeedUser");
                $("#fri_YseendId").attr("value","1");

            }else if($("#fri_YseendId").val() == 1){
                $("#selectbtn_2 div:eq(1)").addClass('userbtnactive');

                $("#fri_NseendId").attr("name","fri_ContainSeedUser");
                $("#fri_NseendId").attr("value","1");

                $("#fri_YseendId").removeAttr("name");
                $("#fri_YseendId").removeAttr("value");
            }
        };
    })
</script>

<script src="${ctx }/assets/js/newactivity.js"></script>
<script class="resources library" src="${ctx }/assets/js/area.js" type="text/javascript"></script>
<script type="text/javascript">_init_area();</script>

<%--标签扩散--%>
<script type="text/javascript">
    var Gid = document.getElementById;
    var showArea = function () {
        Gid('show').innerHTML = "<h3>省" + Gid('s_province').value + " - 市" + Gid('s_city').value + " - 县/区" + Gid('s_county').value + "</h3>"
    }

    $("#s_province option").each(function() {
        if($(this).val()==$("#province").val()){
            $(this).prop('selected',true);
            $(this).change();
        }
    });
    $("#s_city option").each(function() {
        if($(this).val()==$("#city").val()){
            $(this).prop('selected',true);
            $(this).change();
        }
    });
    $("#s_county option").each(function() {
        if($(this).val()==$("#county").val()){
            $(this).prop('selected',true);
        }
    });
</script>

<%--全选/取消全选--%>
<script type="text/javascript">
    function checkAll(thisMsg,name) {
        var checklist = $("input[name='"+name+"']");
        var flag = thisMsg.checked;

        for(var i=0;i<checklist.length;i++) {
            checklist[i].checked = flag;
        }
    }

    /*当所有的标签被勾选时  所有 也勾选上  反之也一样*/
    $(function () {
        $("input[name='age']").change(function() {
            var checklist = document.getElementsByName ("age");
            var id = document.getElementById("age");
            selectAllUtil(checklist,id);
        });
        $("input[name='gender']").change(function() {
            var checklist = document.getElementsByName ("gender");
            var id = document.getElementById("gender");
            selectAllUtil(checklist,id);
        });
        $("input[name='isMarriage']").change(function() {
            var checklist = document.getElementsByName ("isMarriage");
            var id = document.getElementById("isMarriage");
            selectAllUtil(checklist,id);
        });
        $("input[name='shoppingFrequency']").change(function() {
            var checklist = document.getElementsByName ("shoppingFrequency");
            var id = document.getElementById("shoppingFrequency");
            selectAllUtil(checklist,id);
        });
        $("input[name='perTicketSalesPrice']").change(function() {
            var checklist = document.getElementsByName ("perTicketSalesPrice");
            var id = document.getElementById("perTicketSalesPrice");
            selectAllUtil(checklist,id);
        });
        $("input[name='rfvLabel']").change(function() {
            var checklist = document.getElementsByName ("rfvLabel");
            var id = document.getElementById("rfvLabel");
            selectAllUtil(checklist,id);
        });
        function selectAllUtil(checklist,id) {
            var num = 0;
            for(var i=0;i<checklist.length;i++) {
                if (checklist[i].checked){
                    num+=1;
                }
            }
            if (num == checklist.length){
                id.checked = true;
            } else {
                id.checked = false;
            }
        }
    })
</script>

<script type="text/javascript">


</script>

<%--日期选择--%>
<script>
    function DatePicker(beginSelector, endSelector) {
        // 仅选择日期
        $(beginSelector).datepicker(
            {
                language: "zh-CN",
                autoclose: true,
                startView: 0,
                format: "yyyy-mm-dd",
                clearBtn: true,
                todayBtn: false,
                todayHighlight: true,
                //endDate:new Date()
            }).on('changeDate', function (ev) {
            if (ev.date) {
                $(endSelector).datepicker('setStartDate', new Date(ev.date.valueOf()))
            } else {
                $(endSelector).datepicker('setStartDate', null);
            }
        })

        $(endSelector).datepicker(
            {
                language: "zh-CN",
                autoclose: true,
                startView: 0,
                format: "yyyy-mm-dd",
                clearBtn: true,
                todayBtn: false,
                todayHighlight: true,
                //endDate:new Date()
            }).on('changeDate', function (ev) {
            if (ev.date) {
                $(beginSelector).datepicker('setEndDate', new Date(ev.date.valueOf()))
            } else {
                $(beginSelector).datepicker('setEndDate', null);
            }
        })
    }

    DatePicker("#date_begin", "#date_end");
    DatePicker("#date_begin_1", "#date_end_1");
    DatePicker("#sms_deliveryStartTime", "#sms_deliveryEndTime");
    DatePicker("#fri_deliveryStartTime", "#fri_deliveryEndTime");

    $("#activi-them-savebtn").click(function () {
        const diqu = document.getElementsByClassName("filter-option")[0].innerHTML;

        if (!$("#theme").val()) {
            layer.msg('请填写活动主题');
            return false;
        }
        if (!$("#date_begin").val()) {
            layer.msg('请填写活动开始时间');
            return false;
        }
        if (!$("#date_end").val()) {
            layer.msg('请填写活动结束时间');
            return false;
        }
        if (diqu === "全国/省/直辖市") {
            layer.msg('请选择活动范围');
            return false;
        }
        if (!$("#s_passengerFlow").val()) {
            layer.msg('请填写kpi指标"人流数"');
            return false;
        }
        if (!!$("#date_begin").val() && !!$("#date_end").val()) {
            $('#home').submit();
        }
    })

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

    /*文件上传 2*/
    var upload_1 = document.getElementById('upload_1');
    var nameContainer_1 = document.getElementById('name_1');

    upload_1.onchange = function () {
        var name = [];
        for (var i = 0; i < this.files.length; i++) {
            name[i] = this.files[i].name;
            if (this.files[i].size >= 307200) {
                alert("文件" + this.files[i].name + "过大，不能超过300kb")
                return name = "";
            }
        }
        nameContainer_1.innerHTML = name;
        nameContainer_1.style.display = "inline-block";
        if (nameContainer_1.style.display == "inline-block" && nameContainer_1.innerHTML == "") {
            nameContainer_1.style.display = "none";
        }
    }


</script>

<%--效果分析页面的样式--%>
<script language="JavaScript">
    $(document).ready(function () {
        $('#container').highcharts({

            credits: {
                //href: 'http://www.NewDomainExample.com',
                text: ''
            },
            title: {
                text: ''
            },
            xAxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            yAxis: {
                title: {
                    text: ' '
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            yAxis: {
                title: {
                    text: ' '
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: ' '
            },
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false,
            },
            legend: {
                layout: 'vertical',
                align: 'center',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [
                {
                    name: 'Tokyo',
                    data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2,
                        26.5, 23.3, 18.3, 13.9, 9.6]
                },
                {
                    name: 'New York',
                    data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8,
                        24.1, 20.1, 14.1, 8.6, 2.5]
                },
                {
                    name: 'London',
                    data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0,
                        16.6, 14.2, 10.3, 6.6, 4.8]
                }
            ]

        });
        $('#container2').highcharts({

            credits: {
                //href: 'http://www.NewDomainExample.com',
                text: ''
            },
            title: {
                text: ''
            },
            xAxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            yAxis: {
                title: {
                    text: ' '
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            yAxis: {
                title: {
                    text: ' '
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: ' '
            },
            line: {
                dataLabels: {
                    enabled: true
                },
                enableMouseTracking: false,
            },
            legend: {
                layout: 'vertical',
                align: 'center',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [
                {
                    name: 'Tokyo',
                    data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2,
                        26.5, 23.3, 18.3, 13.9, 9.6]
                },
                {
                    name: 'New York',
                    data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8,
                        24.1, 20.1, 14.1, 8.6, 2.5]
                },
                {
                    name: 'London',
                    data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0,
                        16.6, 14.2, 10.3, 6.6, 4.8]
                }
            ]

        });
    });
</script>
</body>
</html>
