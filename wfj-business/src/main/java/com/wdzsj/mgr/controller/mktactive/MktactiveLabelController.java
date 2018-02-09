package com.wdzsj.mgr.controller.mktactive;

import com.wdzsj.mgr.controller.marketing.ActiveEchoController;
import com.wdzsj.mgr.entity.marketing.vo.ChannelVo;
import com.wdzsj.mgr.entity.marketing.vo.LabelSpreadVo;
import com.wdzsj.mgr.service.marketing.ActiveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 保存种子标签
 * author : sunpanhu
 * create time : 2018/2/9 下午1:18
 */
@Controller
@RequestMapping("/mktactive/label")
@SuppressWarnings({"all", "rawtypes"})
public class MktactiveLabelController {
    private final static String PAGE_NEWMKTACTIVE = "mktactive/newmktactive";
    //活动标签
    @Resource
    private ActiveService activeService;
    //公共部分
    @Resource
    private ActiveEchoController activeEchoController;

    /**
     * 保存种子数据
     * /mktactive/label/saveLabel
     * @param model
     * @param labelSpreadVo
     * @return
     * author : sunpanhu
     * createTime : 2018/2/9 下午1:24
     */
    @RequestMapping("/saveLabel")
    public String saveLabel(ModelMap model, LabelSpreadVo labelSpreadVo){

        activeEchoController.saveLabel(labelSpreadVo);

        //回显其他页面数据
        activeEchoController.selectAllCommon(labelSpreadVo.getS_storesId(), labelSpreadVo.getS_actId(), model);

        //回显 或 计算投放渠道页面数据
        ChannelVo channelVo = activeService.selectByPrimaryKey(labelSpreadVo.getS_actId());

        model.addAttribute("channelVo", channelVo);
        model.addAttribute("isSelect", 3);

        return PAGE_NEWMKTACTIVE;
    }
}
