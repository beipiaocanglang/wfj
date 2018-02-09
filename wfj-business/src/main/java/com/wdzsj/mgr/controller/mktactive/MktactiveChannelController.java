package com.wdzsj.mgr.controller.mktactive;

import com.wdzsj.mgr.entity.marketing.Active;
import com.wdzsj.mgr.entity.marketing.Channel;
import com.wdzsj.mgr.entity.marketing.vo.ChannelVo;
import com.wdzsj.mgr.service.marketing.ActiveService;
import com.wdzsj.mgr.service.marketing.ChannelService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * 选择渠道
 * author : sunpanhu
 * create time : 2018/2/9 下午1:38
 */
@Controller
@RequestMapping("/mktactive/channel")
@SuppressWarnings({"all", "rawtypes"})
public class MktactiveChannelController {

    private final static String PAGE_INDEX = "/mktactive";

    //活动表服务
    @Resource
    private ActiveService activeService;

    //渠道服务
    @Resource
    private ChannelService channelService;

    /**
     * 保存渠道信息
     * /mktactive/channel/saveChannel
     * @param channelVo
     * @return
     * author : sunpanhu
     * createTime : 2018/2/9 下午1:44
     */
    @RequestMapping("/saveChannel")
    public String saveChannelData(ChannelVo channelVo) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        String channelIds = "";
        //选择了短信渠道
        if (StringUtils.isNotBlank(channelVo.getSms_selectChannel())){
            Channel channel = new Channel();

            channel.setChannelName("短信");//渠道名称

            if (StringUtils.isNotBlank(channelVo.getSms_deliveryNum())){//建议投放数据
                channel.setPeopleNo(Long.parseLong(channelVo.getSms_deliveryNum()));//建议投放人数
            }

            String sms_conversionRate = channelVo.getSms_conversionRate();
            if (StringUtils.isNotBlank(sms_conversionRate)){//短信转化率
                sms_conversionRate = sms_conversionRate.substring(0, sms_conversionRate.length()-1);  //去掉“%”
                double d = Double.valueOf(sms_conversionRate);
                BigDecimal b = BigDecimal.valueOf(d);
                channel.setConversionRate(b);
            }

            if (channelVo.getSms_ContainSeedUser()!=null){//是否包含种子用户
                channel.setSeedUser(channelVo.getSms_ContainSeedUser());
            }

            if (StringUtils.isNotBlank(channelVo.getSms_deliveryStartTime())){//投放时间
                channel.setReleaseTime(sdf.parse(channelVo.getSms_deliveryStartTime()));
            }
            //短信投放结束时间
            if (StringUtils.isNotBlank(channelVo.getSms_deliveryEndTime())){
                channel.setReleaseEndTime(sdf.parse(channelVo.getSms_deliveryEndTime()));
            }

            if (channelVo.getS_smsChannelId()!=null){
                channel.setChannelId(channelVo.getS_smsChannelId());
                int i = channelService.updateSelective(channel);
            } else {
                //插入
                int rowNum = channelService.insertSelective(channel);
            }

            if (StringUtils.isNotBlank(channelVo.getFri_selectChannel())){
                channelIds += channel.getChannelId() + ",";
            } else {
                channelIds += channel.getChannelId();
            }
        }

        //选择了朋友圈渠道
        if (StringUtils.isNotBlank(channelVo.getFri_selectChannel())){
            Channel channel = new Channel();

            channel.setChannelName("朋友圈");//渠道名称

            if (StringUtils.isNotBlank(channelVo.getFri_deliveryNum())){//建议投放数据
                channel.setPeopleNo(Long.parseLong(channelVo.getFri_deliveryNum()));//建议投放人数
            }

            String Fri_conversionRate = channelVo.getFri_conversionRate();
            if (StringUtils.isNotBlank(Fri_conversionRate)){//转化率
                Fri_conversionRate = Fri_conversionRate.substring(0, Fri_conversionRate.length()-1);  //去掉“%”
                double d = Double.valueOf(Fri_conversionRate);
                BigDecimal b = BigDecimal.valueOf(d);
                channel.setConversionRate(b);
            }

            //是否包含种子用户?0:是、1:否
            if (channelVo.getFri_ContainSeedUser()!=null){//是否包含种子用户
                channel.setSeedUser(channelVo.getFri_ContainSeedUser());
            }
            //朋友圈投放开始时间
            if (StringUtils.isNotBlank(channelVo.getFri_deliveryStartTime())){
                channel.setReleaseTime(sdf.parse(channelVo.getFri_deliveryStartTime()));
            }
            //朋友圈投放结束时间
            if (StringUtils.isNotBlank(channelVo.getFri_deliveryEndTime())){
                channel.setReleaseEndTime(sdf.parse(channelVo.getFri_deliveryEndTime()));
            }

            if (channelVo.getS_friChannelId()!=null){
                channel.setChannelId(channelVo.getS_friChannelId());
                int i = channelService.updateSelective(channel);
            } else {
                int rowNum = channelService.insertSelective(channel);
            }

            channelIds += channel.getChannelId();
        }

        Active active = new Active();
        active.setActId(channelVo.getS_actId());
        active.setChannelId(channelIds);

        //更新活动表中的渠道字段
        int i = activeService.updateByPrimaryKeySelective(active);

        return PAGE_INDEX;
    }

    /**
     * 保存编辑后的渠道信息
     * /mktactive/channel/saveEditChannel
     * @param channelVo
     * @return
     * author : sunpanhu
     * createTime : 2018/2/9 下午1:43
     */
    @RequestMapping("/saveEditChannel")
    public String saveEditChannelData(ChannelVo channelVo) throws ParseException {

        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

        String channelIds = "";
        //选择了短信渠道
        if (StringUtils.isNotBlank(channelVo.getSms_selectChannel())){
            Channel channel = new Channel();

            channel.setChannelName("短信");//渠道名称

            if (StringUtils.isNotBlank(channelVo.getSms_deliveryNum())){//建议投放数据
                channel.setPeopleNo(Long.parseLong(channelVo.getSms_deliveryNum()));//建议投放人数
            }

            String sms_conversionRate = channelVo.getSms_conversionRate();
            if (StringUtils.isNotBlank(sms_conversionRate)){//短信转化率
                sms_conversionRate = sms_conversionRate.substring(0, sms_conversionRate.length()-1);  //去掉“%”
                double d = Double.valueOf(sms_conversionRate);
                BigDecimal b = BigDecimal.valueOf(d);
                channel.setConversionRate(b);
            }

            if (channelVo.getSms_ContainSeedUser()!=null){//是否包含种子用户
                channel.setSeedUser(channelVo.getSms_ContainSeedUser());
            }
            //短信投放开始时间
            if (StringUtils.isNotBlank(channelVo.getSms_deliveryStartTime())){
                channel.setReleaseTime(sdf.parse(channelVo.getSms_deliveryStartTime()));
            }
            //短信投放结束时间
            if (StringUtils.isNotBlank(channelVo.getSms_deliveryEndTime())){
                channel.setReleaseEndTime(sdf.parse(channelVo.getSms_deliveryEndTime()));
            }

            if (channelVo.getS_smsChannelId()!=null){
                channel.setChannelId(channelVo.getS_smsChannelId());
                int i = channelService.updateSelective(channel);
            } else {
                //插入
                int rowNum = channelService.insertSelective(channel);
            }
            if (StringUtils.isNotBlank(channelVo.getFri_selectChannel())){
                channelIds += channel.getChannelId() + ",";
            } else {
                channelIds += channel.getChannelId();
            }
        }

        //选择了朋友圈渠道
        if (StringUtils.isNotBlank(channelVo.getFri_selectChannel())){
            Channel channel = new Channel();

            channel.setChannelName("朋友圈");//渠道名称

            if (StringUtils.isNotBlank(channelVo.getFri_deliveryNum())){//建议投放数据
                channel.setPeopleNo(Long.parseLong(channelVo.getFri_deliveryNum()));//建议投放人数
            }

            //转化率
            String Fri_conversionRate = channelVo.getFri_conversionRate();
            if (StringUtils.isNotBlank(Fri_conversionRate)){
                Fri_conversionRate = Fri_conversionRate.substring(0, Fri_conversionRate.length()-1);  //去掉“%”
                double d = Double.valueOf(Fri_conversionRate);
                BigDecimal b = BigDecimal.valueOf(d);
                channel.setConversionRate(b);
            }
            //是否包含种子用户?0:是、1:否
            if (channelVo.getSms_ContainSeedUser()!=null){
                channel.setSeedUser(channelVo.getSms_ContainSeedUser());
            }
            //朋友圈投放开始时间
            if (StringUtils.isNotBlank(channelVo.getFri_deliveryStartTime())){
                channel.setReleaseTime(sdf.parse(channelVo.getFri_deliveryStartTime()));
            }
            //朋友圈投放结束时间
            if (StringUtils.isNotBlank(channelVo.getFri_deliveryEndTime())){
                channel.setReleaseEndTime(sdf.parse(channelVo.getFri_deliveryEndTime()));
            }

            if (channelVo.getS_friChannelId()!=null){
                channel.setChannelId(channelVo.getS_friChannelId());
                int i = channelService.updateSelective(channel);
            } else {
                int rowNum = channelService.insertSelective(channel);
            }

            channelIds += channel.getChannelId();
        }

        Active active = new Active();
        active.setActId(channelVo.getS_actId());
        active.setChannelId(channelIds);

        //更新活动表中的渠道字段
        int i = activeService.updateByPrimaryKeySelective(active);

        return PAGE_INDEX;
    }
}
