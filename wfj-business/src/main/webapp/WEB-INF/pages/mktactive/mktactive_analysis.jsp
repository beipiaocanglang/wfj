<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<form action="${ctx}/marketing/saveActivityPlan" method="post" class="form-horizontal tab-pane fade in" id="settingss" enctype="multipart/form-data">
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
