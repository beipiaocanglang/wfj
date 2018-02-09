package com.wdzsj.mgr.controller.marketing;

import com.wdzsj.mgr.entity.marketing.vo.ChannelVo;
import com.wdzsj.mgr.entity.marketing.vo.LabelSpreadVo;
import com.wdzsj.mgr.service.marketing.ActiveService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@RequestMapping("/marketing/label/")
public class ActiveLabelSpreadController {

    private final static String PAGE_NEWACTIVITY = "marketing/newactivity";
    private final static String PAGE_EDITACTIVE = "marketing/editActive";

    //活动标签
    @Resource
    private ActiveService activeService;

    //公共部分
    @Resource
    private ActiveEchoController activeEchoController;

    /**
     * 保存扩散标签数据
     * /marketing/label/labelSpread
     * @return
     */
    @RequestMapping("labelSpread")
    public String labelSpread(ModelMap model,LabelSpreadVo labelSpreadVo){

        activeEchoController.saveLabel(labelSpreadVo);

        //回显其他页面数据
        activeEchoController.selectAllCommon(labelSpreadVo.getS_storesId(), labelSpreadVo.getS_actId(), model);

        //回显 或 计算投放渠道页面数据
        ChannelVo channelVo = activeService.selectByPrimaryKey(labelSpreadVo.getS_actId());

        model.addAttribute("channelVo", channelVo);
        model.addAttribute("isSelect", 3);

        return PAGE_NEWACTIVITY;
    }

    /**
     * 保存编辑后的扩散标签数据
     * /marketing/label/editLabelSpread
     * @return
     */
    @RequestMapping("editLabelSpread")
    public String editLabelSpread(ModelMap model,LabelSpreadVo labelSpreadVo){
        //保存编辑后的标签
        activeEchoController.saveLabel(labelSpreadVo);

        //回显其他页面数据
        activeEchoController.selectAllCommon(labelSpreadVo.getS_storesId(), labelSpreadVo.getS_actId(), model);

        //回显 或 计算投放渠道页面数据
        ChannelVo channelVo = activeService.selectByPrimaryKey(labelSpreadVo.getS_actId());

        model.addAttribute("channelVo", channelVo);
        model.addAttribute("isSelect", 3);

        return PAGE_EDITACTIVE;
    }
}
