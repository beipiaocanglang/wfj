<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<form action="${ctx}/marketing/saveActivityPlan" method="post" class="form-horizontal tab-pane fade in" id="settings" enctype="multipart/form-data">
    <div class="newactivity-content">
        <div class="row">
            <div class="tab-spread activity-list">
                <div class="yilei-brandvip col-xs-12">
                    <span class="activi-title">异类品牌会员</span>
                    <b>3,000</b>
                    <span>（目标KPI人流：<i>48894</i>）</span>
                </div>
                <div class="yilei-brandvip col-xs-12">
                    <span class="activi-title">异类品牌种子会员</span>
                </div>
                <div class="tab-spreadlist col-xs-12">
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
                <div class="tab-spreadlist col-xs-12">
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
                <div class="tab-spreadlist col-xs-12">
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
                <div class="tab-spreadlist col-xs-12">
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
                <div class="tab-spreadlist col-xs-12">
                    <span class="spreadtitle">消费定位</span>
                    <p class="spreacheck">
                        <c:forEach items="${labelMapVo.consumeLocation}" var="cll" varStatus="c">
                            <label>
                                <input type="checkbox" name="consumeLocation" value="${cll.labelValue}" ${cll.isChecked}> ${cll.labelValue}
                            </label>
                        </c:forEach>
                    </p>
                </div>
            </div>
        </div>
    </div>
</form>
