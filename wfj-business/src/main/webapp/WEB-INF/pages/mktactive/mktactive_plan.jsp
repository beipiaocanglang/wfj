<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<form action="${ctx}/marketing/saveActivityPlan" method="post" class="form-horizontal tab-pane fade in active" id="home" enctype="multipart/form-data">
    <%--活动计划保存后返回的主键ID--%>
    <input type="hidden" name="s_actId" id="avtiveAct_id" value="${createEditActiveVo.actId}"/>
    <input type="hidden" name="attachmentId" id="s_attachmentId" value="${createEditActiveVo.attachmentIdQrCode}"/>
    <input type="hidden" id="huixianStoreIds" value="${createEditActiveVo.storeId}"/>
    <%--活动计划--%>
        <div class="newactivity-content">
            <div class="row">
                <div class="activity-list col-xs-12">
                    <div class="activi-them">
                        <span class="activi-title">活动范围</span>
                        <span id="activity_click"><input name="s_theme" disabled="disabled" class="acitvi-val selectwrapper" type="text" placeholder="+选择活动范围及门店" value="${createEditActiveVo.theme}"></span>
                    </div>
                    <div class="range-window" id="activity_showhide" style="display: none">
                        <div class="range-window-content">
                            <div class="citylist">
                                <div class="selecity">
                                    <input type="checkbox">
                                    <span>北京市</span>
                                </div>
                                <div class="selecity">
                                    <input type="checkbox">
                                    <span>北京市</span>
                                </div>
                                <div class="selecity">
                                    <input type="checkbox">
                                    <span>北京市</span>
                                </div>
                                <div class="selecity">
                                    <input type="checkbox">
                                    <span>北京市</span>
                                </div>
                                <div class="selecity">
                                    <input type="checkbox">
                                    <span>北京市</span>
                                </div>
                                <div class="selecity">
                                    <input type="checkbox">
                                    <span>北京市</span>
                                </div>
                                <div class="selecity">
                                    <input type="checkbox">
                                    <span>北京市</span>
                                </div>


                            </div>
                            <div class="brandlist">
                                <div class="selecity">
                                    <input type="checkbox">
                                    <span>北京王府井</span>
                                </div>
                                <div class="selecity">
                                    <input type="checkbox">
                                    <span>北京王府井</span>
                                </div>
                                <div class="selecity">
                                    <input type="checkbox">
                                    <span>北京王府井</span>
                                </div>
                                <div class="selecity">
                                    <input type="checkbox">
                                    <span>北京王府井</span>
                                </div>
                                <div class="selecity">
                                    <input type="checkbox">
                                    <span>北京王府井</span>
                                </div>
                                <div class="selecity">
                                    <input type="checkbox">
                                    <span>北京王府井</span>
                                </div>
                                <div class="selecity">
                                    <input type="checkbox">
                                    <span>北京王府井</span>
                                </div>

                            </div>
                        </div>
                    </div>
                </div>
                <div class="activity-list col-xs-12">
                    <span class="activi-title">活动时间</span>
                    <input name="s_theme" class="acitvi-val selectwrapper" type="text"  value="">
                </div>
                <div class="activity-list col-xs-12">
                    <div class="kpi-titlecontent">
                        <span class="kpi-title">活动主题</span>
                    </div>
                    <div class="kpitable activity_height">
                        <div class="kpitabtitle-left">
                            <div class="kpitabtitle-leftcontent">
                                <div class="kpi-select kpi-selectfirst">自定义活动主题：</div>
                                <div class="kpi-select kpi-selectlast">选择已有主题：</div>
                            </div>
                        </div>
                        <div class="kpitabtitle-right">
                            <div class="kpitabtitle-rightcontent">
                                <div class="kpi-select kpi-selectfirst">
                                    <div class="kipselect-tab">
                                        <input class="h5href" type="text" name="s_h5Url" value="${createEditActiveVo.h5Url}">
                                    </div>
                                </div>
                                <div class="kpi-select kpi-selectfirst" style="border: none">
                                    <div class="kipselect-tab">
                                        <span id="activititle_click"><input class="h5href selectwrapper" disabled="disabled" style="border:none;cursor: pointer" type="text" name="s_h5Url" placeholder="+选择品类/品牌或事件" value=""></span>
                                    </div>
                                    <div class="range-window range-window-height" id="activititle_showhide" style="display: none">
                                        <div class="range-window-content">
                                            <div class="tabs_toggle">
                                                <div class="brand_btn" id="tabstoggle_click">
                                                    <a class="brandselectclick brand_btn_active" href="javascript:void(0)">品类/品牌</a>
                                                    <a class="brandselectclick" href="javascript:void(0)">事件</a>
                                                </div>
                                                <div id="tab-div-toggle">
                                                    <div>
                                                        <div class="brand_search">
                                                            <input class="brand_search-text" placeholder="输入后直接出搜索结果" type="text" value="">
                                                        </div>
                                                        <div class="brand-classification" id="brand-all">
                                                            <a class="brandtext" href="javascript:void(0)">
                                                                精品
                                                                <div class="brand-selet-title">
                                                                    <span onclick="checkfenlei(this)" class="brand-sel">选此分类</span>
                                                                    <span onclick="checkpinpai(this)" class="brand-sel">选此品牌</span>
                                                                </div>
                                                            </a>
                                                            <a class="brandtext" href="javascript:void(0)">
                                                                精品
                                                                <div class="brand-selet-title">
                                                                    <span onclick="checkfenlei(this)" class="brand-sel">选此分类</span>
                                                                    <span onclick="checkpinpai(this)" class="brand-sel">选此品牌</span>
                                                                </div>
                                                            </a>
                                                            <a class="brandtext" href="javascript:void(0)">
                                                                精品
                                                                <div class="brand-selet-title">
                                                                    <span onclick="checkfenlei(this)" class="brand-sel">选此分类</span>
                                                                    <span onclick="checkpinpai(this)" class="brand-sel">选此品牌</span>
                                                                </div>
                                                            </a>
                                                            <a class="brandtext" href="javascript:void(0)">
                                                                精品
                                                                <div class="brand-selet-title">
                                                                    <span onclick="checkfenlei(this)" class="brand-sel">选此分类</span>
                                                                    <span onclick="checkpinpai(this)" class="brand-sel">选此品牌</span>
                                                                </div>
                                                            </a>
                                                            <a class="brandtext" href="javascript:void(0)">
                                                                精品
                                                                <div class="brand-selet-title">
                                                                    <span onclick="checkfenlei(this)" class="brand-sel">选此分类</span>
                                                                    <span onclick="checkpinpai(this)" class="brand-sel">选此品牌</span>
                                                                </div>
                                                            </a>
                                                            <a class="brandtext" href="javascript:void(0)">
                                                                精品
                                                                <div class="brand-selet-title">
                                                                    <span onclick="checkfenlei(this)" class="brand-sel">选此分类</span>
                                                                    <span onclick="checkpinpai(this)" class="brand-sel">选此品牌</span>
                                                                </div>
                                                            </a>
                                                            <a class="brandtext" href="javascript:void(0)">
                                                                精品
                                                                <div class="brand-selet-title">
                                                                    <span onclick="checkfenlei(this)" class="brand-sel">选此分类</span>
                                                                    <span onclick="checkpinpai(this)" class="brand-sel">选此品牌</span>
                                                                </div>
                                                            </a>
                                                            <a class="brandtext" href="javascript:void(0)">
                                                                精品
                                                                <div class="brand-selet-title">
                                                                    <span onclick="checkfenlei(this)" class="brand-sel">选此分类</span>
                                                                    <span onclick="checkpinpai(this)" class="brand-sel">选此品牌</span>
                                                                </div>
                                                            </a>
                                                            <a class="brandtext" href="javascript:void(0)">
                                                                精品
                                                                <div class="brand-selet-title">
                                                                    <span onclick="checkfenlei(this)" class="brand-sel">选此分类</span>
                                                                    <span onclick="checkpinpai(this)" class="brand-sel">选此品牌</span>
                                                                </div>
                                                            </a>
                                                            <a class="brandtext" href="javascript:void(0)">
                                                                精品
                                                                <div class="brand-selet-title">
                                                                    <span onclick="checkfenlei(this)" class="brand-sel">选此分类</span>
                                                                    <span onclick="checkpinpai(this)" class="brand-sel">选此品牌</span>
                                                                </div>
                                                            </a>
                                                            <a class="brandtext" href="javascript:void(0)">
                                                                精品
                                                                <div class="brand-selet-title">
                                                                    <span onclick="checkfenlei(this)" class="brand-sel">选此分类</span>
                                                                    <span onclick="checkpinpai(this)" class="brand-sel">选此品牌</span>
                                                                </div>
                                                            </a>
                                                            <a class="brandtext" href="javascript:void(0)">
                                                                精品
                                                                <div class="brand-selet-title">
                                                                    <span onclick="checkfenlei(this)" class="brand-sel">选此分类</span>
                                                                    <span onclick="checkpinpai(this)" class="brand-sel">选此品牌</span>
                                                                </div>
                                                            </a>
                                                            <a class="brandtext" href="javascript:void(0)">
                                                                精品
                                                                <div class="brand-selet-title">
                                                                    <span onclick="checkfenlei(this)" class="brand-sel">选此分类</span>
                                                                    <span onclick="checkpinpai(this)" class="brand-sel">选此品牌</span>
                                                                </div>
                                                            </a>
                                                            <a class="brandtext" href="javascript:void(0)">
                                                                精品
                                                                <div class="brand-selet-title">
                                                                    <span onclick="checkfenlei(this)" class="brand-sel">选此分类</span>
                                                                    <span onclick="checkpinpai(this)" class="brand-sel">选此品牌</span>
                                                                </div>
                                                            </a>
                                                            <a class="brandtext" href="javascript:void(0)">
                                                                精品
                                                                <div class="brand-selet-title">
                                                                    <span onclick="checkfenlei(this)" class="brand-sel">选此分类</span>
                                                                    <span onclick="checkpinpai(this)" class="brand-sel">选此品牌</span>
                                                                </div>
                                                            </a>
                                                            <a class="brandtext" href="javascript:void(0)">
                                                                精品
                                                                <div class="brand-selet-title">
                                                                    <span onclick="checkfenlei(this)" class="brand-sel">选此分类</span>
                                                                    <span onclick="checkpinpai(this)" class="brand-sel">选此品牌</span>
                                                                </div>
                                                            </a>
                                                        </div>
                                                        <div class="brand-classification" id="pinpai-listall" style="display: none;">
                                                            <a class="brand-list" href="javascript:void(0)">素</a>
                                                            <a class="brand-list" href="javascript:void(0)">HEYIN</a>
                                                            <a class="brand-list" href="javascript:void(0)">珍珠小姐</a>
                                                            <a class="brand-list" href="javascript:void(0)">MCAVCCI</a>
                                                            <a class="brand-list" href="javascript:void(0)">素</a>
                                                            <a class="brand-list" href="javascript:void(0)">HEYIN</a>
                                                            <a class="brand-list" href="javascript:void(0)">珍珠小姐</a>
                                                            <a class="brand-list" href="javascript:void(0)">MCAVCCI</a>
                                                            <a class="brand-list" href="javascript:void(0)">素</a>
                                                            <a class="brand-list" href="javascript:void(0)">HEYIN</a>
                                                            <a class="brand-list" href="javascript:void(0)">珍珠小姐</a>
                                                            <a class="brand-list" href="javascript:void(0)">MCAVCCI</a>
                                                            <a class="brand-list" href="javascript:void(0)">素</a>
                                                            <a class="brand-list" href="javascript:void(0)">HEYIN</a>
                                                            <a class="brand-list" href="javascript:void(0)">珍珠小姐</a>
                                                            <a class="brand-list" href="javascript:void(0)">MCAVCCI</a>
                                                            <a class="brand-list" href="javascript:void(0)">素</a>
                                                            <a class="brand-list" href="javascript:void(0)">HEYIN</a>
                                                            <a class="brand-list" href="javascript:void(0)">珍珠小姐</a>
                                                            <a class="brand-list" href="javascript:void(0)">MCAVCCI</a>
                                                            <a class="brand-list" href="javascript:void(0)">素</a>
                                                            <a class="brand-list" href="javascript:void(0)">HEYIN</a>
                                                            <a class="brand-list" href="javascript:void(0)">珍珠小姐</a>
                                                            <a class="brand-list" href="javascript:void(0)">MCAVCCI</a>
                                                        </div>
                                                        <div class="brand-select-all">
                                                            <span class="brand-select-allbtn">全选</span>
                                                            <span class="brand-select-allbtn">确定</span>
                                                            <span class="brand-select-allbtn" onclick="brandcanle()">取消</span>
                                                        </div>
                                                    </div>
                                                    <div class="brandclick-list">
                                                        <div class="brandclick">
                                                            <input class="brandclick-select" type="checkbox">
                                                            <span class="brandclick-name">事件名称</span>
                                                        </div>
                                                        <div class="brandclick">
                                                            <input class="brandclick-select" type="checkbox">
                                                            <span class="brandclick-name">事件名称</span>
                                                        </div>
                                                        <div class="brandclick">
                                                            <input class="brandclick-select" type="checkbox">
                                                            <span class="brandclick-name">事件名称</span>
                                                        </div>
                                                        <div class="brandclick">
                                                            <input class="brandclick-select" type="checkbox">
                                                            <span class="brandclick-name">事件名称</span>
                                                        </div>
                                                        <div class="brandclick">
                                                            <input class="brandclick-select" type="checkbox">
                                                            <span class="brandclick-name">事件名称</span>
                                                        </div>
                                                        <div class="brandclick">
                                                            <input class="brandclick-select" type="checkbox">
                                                            <span class="brandclick-name">事件名称</span>
                                                        </div>
                                                        <div class="brandclick">
                                                            <input class="brandclick-select" type="checkbox">
                                                            <span class="brandclick-name">事件名称</span>
                                                        </div>
                                                        <div class="brandclick">
                                                            <input class="brandclick-select" type="checkbox">
                                                            <span class="brandclick-name">事件名称</span>
                                                        </div>
                                                        <div class="brandclick">
                                                            <input class="brandclick-select" type="checkbox">
                                                            <span class="brandclick-name">事件名称</span>
                                                        </div>
                                                        <div class="brandclick">
                                                            <input class="brandclick-select" type="checkbox">
                                                            <span class="brandclick-name">事件名称</span>
                                                        </div>
                                                        <div class="brandclick">
                                                            <input class="brandclick-select" type="checkbox">
                                                            <span class="brandclick-name">事件名称</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
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
</form>
