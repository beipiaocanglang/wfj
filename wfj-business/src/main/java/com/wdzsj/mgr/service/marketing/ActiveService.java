package com.wdzsj.mgr.service.marketing;

import com.wdzsj.mgr.castor.util.DoubleUtil;
import com.wdzsj.mgr.dao.marketing.ActiveDao;
import com.wdzsj.mgr.dao.marketing.ChannelDao;
import com.wdzsj.mgr.entity.marketing.Active;
import com.wdzsj.mgr.entity.marketing.Channel;
import com.wdzsj.mgr.entity.marketing.constant.ActiveEnum;
import com.wdzsj.mgr.entity.marketing.constant.KpiTypeEnum;
import com.wdzsj.mgr.entity.marketing.vo.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings({"all", "rawtypes"})
public class ActiveService{

    @Resource
    private ActiveDao activeDao;

    @Resource
    private ChannelDao channelDao;

    @Resource
    private DoubleUtil doubleUtil;

    /**
     * 统计查询
     * @param map
     * @return
     */
    public Integer count(Map<String, Object> map){
        return activeDao.count(map);
    }

    /**
     * 动态插入 活动计划页面的数据
     * @param active
     * @return
     */
    public int insertSelective(Active active){

        return activeDao.insertSelective(active);
    }

    /**
     * 根据主键查询
     * @param id
     * @return
     */
    public ChannelVo selectByPrimaryKey(Long id){
        ActiveVo activeVo = activeDao.selectByPrimaryKey(id);

        ChannelVo channelVo = new ChannelVo();
        channelVo.setSeedNum(Long.parseLong(activeVo.getSeedNum()));//获取种子数量
        channelVo.setS_actId(activeVo.getActId());

        String kpiValue = activeVo.getKpiValue();
        String activeKpi = "";
        if (StringUtils.isNotBlank(kpiValue)) {
            String[] split = kpiValue.split(",");
            for(int x=0; x<split.length;x++){
                String value = split[x].substring(2, split[x].length());
                String KpiTypeEnumCode = split[x].substring(0, 1);
                if (KpiTypeEnumCode.equals("2")){
                    String channel_sms = "42.0";
                    String channel_fri = "36.0";

                    Double sms_deliveryNum = doubleUtil.div(doubleUtil.mul(Double.parseDouble(value),100),Double.parseDouble(channel_sms), 0);

                    channelVo.setSms_deliveryNum(String.valueOf(sms_deliveryNum));
                    channelVo.setSms_conversionRate(channel_sms);

                    Double fri_deliveryNum = doubleUtil.div(doubleUtil.mul(Double.parseDouble(value),100),Double.parseDouble(channel_fri), 0);

                    channelVo.setFri_deliveryNum(String.valueOf(fri_deliveryNum));
                    channelVo.setFri_conversionRate(channel_fri);
                }
                String kpiDesc = KpiTypeEnum.getByCode(KpiTypeEnumCode).getValue();
                if (x != split.length-1){
                    activeKpi += kpiDesc+"("+value+"),";
                } else {
                    activeKpi += kpiDesc+"("+value+")";
                }
            }
        }
        channelVo.setActiveKpi(activeKpi);//获取活动kpi描述

        String channelIds = activeVo.getChannelId();//获取渠道字段数据

        if (StringUtils.isNotBlank(channelIds)){//判断渠道是否为空
            String[] ids = channelIds.split(",");

            List<Channel> list = channelDao.selectByIds(ids);

            if (list!=null && list.size()>0){
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                for (Channel channel : list) {
                    String conversionRate = channel.getConversionRate().setScale(1, BigDecimal.ROUND_HALF_UP).toString();

                    if (channel.getChannelName().equals("短信")){//短信渠道
                        channelVo.setS_smsChannelId(channel.getChannelId());
                        channelVo.setSms_selectChannel("0");
                        channelVo.setSms_deliveryNum(String.valueOf(channel.getPeopleNo()));
                        channelVo.setSms_conversionRate(conversionRate);
                        channelVo.setSms_ContainSeedUser(channel.getSeedUser());
                        channelVo.setSms_deliveryStartTime(format.format(channel.getReleaseTime()));
                        channelVo.setSms_deliveryEndTime(format.format(channel.getReleaseEndTime()));

                    } else if (channel.getChannelName().equals("朋友圈")){//朋友圈渠道
                        channelVo.setS_friChannelId(channel.getChannelId());
                        channelVo.setFri_selectChannel("0");
                        channelVo.setFri_deliveryNum(String.valueOf(channel.getPeopleNo()));
                        channelVo.setFri_conversionRate(conversionRate);
                        channelVo.setFri_ContainSeedUser(channel.getSeedUser());
                        channelVo.setFri_deliveryStartTime(format.format(channel.getReleaseTime()));
                        channelVo.setFri_deliveryEndTime(format.format(channel.getReleaseEndTime()));
                    }
                }
            }
        }
        return channelVo;
    }

    /**
     * 编辑活动数据时  根据活动表的主键查询 活动表 kpi表 附件表数据
     * @param actId
     * @return
     */
    public EditActiveAndKpiVo selectEditActiveDataByActId(Long actId){
        EditActiveAndKpiVo activeSeedVo = activeDao.selectEditActiveDataByActId(actId);
        return activeSeedVo;
    }

    public ActiveSeedVo selectEditActiveSeedByActId(Long actId){
        ActiveSeedVo activeSeedVo = activeDao.selectEditActiveSeedByActId(actId);
        return activeSeedVo;
    }

    /**
     * 根据主键动态修改数据
     * @param active
     * @return
     */
    public int updateByPrimaryKeySelective(Active active){
        return activeDao.updateByPrimaryKeySelective(active);
    }

    /**
     * 新建活动数据时 可以返回编辑的功能时 的查询 用于回显
     * @param actId
     * @return
     */
    public CreateEditActiveVo selectCreateEditActiveDataByActId(Long actId){

        return activeDao.selectCreateEditActiveDataByActId(actId);
    }



    //******************************** 以下是页面重构后的服务 ********************************
    /**
     * 分页查询营销活动首页列表
     * author : sunpanhu
     * createTime : 2018/2/7 下午4:02
     */
    public List<ActiveVo> findList(Map<String, Object> map){

        List<ActiveVo> lists = activeDao.findList(map);

        if (lists==null || lists.size()<=0) {
            return lists;
        }

        for (ActiveVo activeVo : lists) {
            //处理KPI类型与KPI对应值的关心
            String kpiValue = activeVo.getKpiValue();
            String activeKpi = "";
            if (StringUtils.isNotBlank(kpiValue)){
                String[] split = kpiValue.split(",");
                for(int x=0; x<split.length;x++){
                    String value = split[x].substring(2, split[x].length());

                    String KpiTypeEnumCode = split[x].substring(0, 1);

                    String kpiDesc = KpiTypeEnum.getByCode(KpiTypeEnumCode).getValue();

                    if (x != split.length-1){
                        activeKpi += kpiDesc+"("+value+"),";
                    } else {
                        activeKpi += kpiDesc+"("+value+")";
                    }
                }
            }
            activeVo.setActiveKpi(activeKpi);

            //处理活动状态
            String status = activeVo.getStatus();
            String statusValue = ActiveEnum.getByCode(status).getValue();
            activeVo.setStatus(statusValue);

            //处理kpi是否达标
            String upToStandard = activeVo.getUpToStandard();
            if (StringUtils.isNotBlank(upToStandard)) {
                activeVo.setUpToStandard(upToStandard);
            }

            //处理投放渠道
            String channelValue = activeVo.getChannelId();
            String channelDesc = "";
            if (StringUtils.isNotBlank(channelValue)){
                String[] split = channelValue.split(",");
                for (int x=0;x<split.length;x++) {
                    Channel channel = channelDao.selectById(Long.parseLong(split[x]));
                    if (channel!=null) {
                        if (x == split.length-1){
                            channelDesc += channel.getChannelName();
                        } else {
                            channelDesc += channel.getChannelName() + ",";
                        }
                    }
                }
            }
            activeVo.setChannelDesc(channelDesc);
        }

        return lists;
    }

}
