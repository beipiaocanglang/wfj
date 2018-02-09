package com.wdzsj.mgr.controller.mktactive;

import com.wdzsj.cmn.entity.Page;
import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.mgr.controller.marketing.ActiveEchoController;
import com.wdzsj.mgr.entity.marketing.Active;
import com.wdzsj.mgr.entity.marketing.ActiveTagRelation;
import com.wdzsj.mgr.entity.marketing.Attachment;
import com.wdzsj.mgr.entity.marketing.KpiDetail;
import com.wdzsj.mgr.entity.marketing.constant.ActiveEnum;
import com.wdzsj.mgr.entity.marketing.vo.ActiveAndKpiDetailVo;
import com.wdzsj.mgr.entity.marketing.vo.ActiveVo;
import com.wdzsj.mgr.service.marketing.ActiveService;
import com.wdzsj.mgr.service.marketing.ActiveTagRelationService;
import com.wdzsj.mgr.service.marketing.AttachmentService;
import com.wdzsj.mgr.service.marketing.KpiDetailService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.wdzsj.cmn.entity.PageRequest.newPageRequest;

/**
 * 新建营销活动 ~ 活动计划
 * author : sunpanhu
 * create time : 2018/2/7 下午12:08
 */
@Controller
@SuppressWarnings({"all", "rawtypes"})
@RequestMapping("/mktactive")
public class MktactiveController {
    //新建营销活动 ~ 活动计划页面
    private final static String PAGE_NEWMKTACTIVE = "mktactive/newmktactive";
    //营销活动列表
    private final static String PAGE_INDEX = "marketing/index";

    //活动列表服务
    @Resource
    private ActiveService activeService;

    //附件表数据
    @Resource
    private AttachmentService attachmentService;

    //门店活动对应的kpi表服务
    @Resource
    private KpiDetailService kpiDetailService;

    //公共部分
    @Resource
    private ActiveEchoController activeEchoController;

    //活动标签关联服务
    @Resource
    private ActiveTagRelationService activeTagRelationService;


    /**
     * 跳转到新建营销活动 ~ 活动计划页面
     * /mktactive/plan/newActivePlan
     * author : sunpanhu
     * createTime : 2018/2/7 下午3:01
     */
    @RequestMapping("/plan/newActivePlan")
    public String newActivePlanIndex(){
        return PAGE_NEWMKTACTIVE;
    }

    /**
     * 营销活动首页列表
     * /mktactive
     * author : sunpanhu
     * createTime : 2018/2/7 下午3:40
     */
    @RequestMapping
    public String index(ModelMap model, HttpServletRequest request) {
        PageRequest<Map<String,Object>> pageRequest = newPageRequest(request);
        Map<String, Object> param = pageRequest.getAllFilters();

        String theme = (String)param.get("theme");
        if (StringUtils.isNotBlank(theme)){
            param.put("theme",theme.trim());
        }

        Integer totalCount = activeService.count(param);
        List<ActiveVo> lists = activeService.findList(param);

        Page page = new Page(pageRequest, totalCount, lists);

        model.addAttribute("page", page);
        model.addAttribute("pageRequest", pageRequest);//将搜索条件存入model

        return PAGE_INDEX;
    }

    /**
     * 保存重构后的活动计划页面数据
     * /mktactive/saveMktactivePlan
     * @param model
     * @param actAkpiVo 活动计划页面中name对应的封装值
     * @param file 要上传的二维码文件
     * @param request
     * @return
     * @throws Exception
     * author : sunpanhu
     * createTime : 2018/2/9 上午9:33
     */
    @RequestMapping("/saveMktactivePlan")
    public String saveMktactivePlan(ModelMap model, ActiveAndKpiDetailVo actAkpiVo, MultipartFile file, HttpServletRequest request) throws Exception {
        //将字符串转成日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = sdf.parse(actAkpiVo.getS_startTime());
        Date endTime = sdf.parse(actAkpiVo.getS_endTime());

        Active active = new Active();
        active.setTheme(actAkpiVo.getS_theme());
        active.setStartTime(startTime);
        active.setEndTime(endTime);
        active.setUpdateTime(new Date());
        active.setAttachmentIdExportFile(new Long(1));//设置推送文件ID(固定的)

        //表单提交是否包含文件上传
        if (file!=null && file.getSize()>0) {
            String fileName = file.getOriginalFilename();//获取文件名称
            String extNameType = fileName.substring(fileName.lastIndexOf(".")+1);//获取文件的扩展名
            byte[] bytes = file.getBytes();//获取文件内容

            Attachment attachment = new Attachment();
            attachment.setAttachmentContent(bytes);
            attachment.setAttachmentName(fileName);
            attachment.setAttachmentUpdatetime(new Date());
            attachment.setAttachmentType(extNameType);

            if (actAkpiVo.getAttachmentId() != null) {
                attachment.setAttachmentId(actAkpiVo.getAttachmentId());
                //根据主键id修改 附加表数据
                int rowNum = attachmentService.updateByPrimaryKeySelective(attachment);
            } else {
                attachment.setAttachmentCreatetime(new Date());
                //保存二维码到附件表  返回主键
                int rowNum = attachmentService.insertSelective(attachment);
                active.setAttachmentIdQrCode(attachment.getAttachmentId());
            }
        }

        //获取所选门店编码
        String countrys = actAkpiVo.getS_country();
        if (StringUtils.isNotBlank(countrys)){
            active.setStoreId(countrys);
        }
        if (StringUtils.isNotBlank(actAkpiVo.getS_content())){
            active.setContent(actAkpiVo.getS_content());
        }
        if (StringUtils.isNotBlank(actAkpiVo.getS_h5Url())){
            active.setH5Url(actAkpiVo.getS_h5Url());
        }

        boolean isStart = startTime.before(new Date());
        boolean isEnd = endTime.before(new Date());
        if (!isStart){
            active.setStatus(ActiveEnum.NOTBEGIN.getCode());
        } else if (isStart && !isEnd) {
            active.setStatus(ActiveEnum.UNDERWAY.getCode());
        } else if(isStart && isEnd){
            active.setStatus(ActiveEnum.FINISH.getCode());
        }

        //更新或修改 活动表数据
        if (actAkpiVo.getS_actId() != null) {
            active.setActId(actAkpiVo.getS_actId());
            int row = activeService.updateByPrimaryKeySelective(active);
        } else {
            active.setCreateTime(new Date());
            //保存活动表数据，返回主键，通过active.getActId()获取
            int id = activeService.insertSelective(active);
        }

        String kpiType = "";
        String kpiValue = "";
        if (StringUtils.isNotBlank(actAkpiVo.getS_sellAmount())) {
            kpiType += "1,";
            kpiValue += "1_" + actAkpiVo.getS_sellAmount() + ",";
        }
        if (StringUtils.isNotBlank(actAkpiVo.getS_passengerFlow())) {
            kpiType += "2,";
            kpiValue += "2_" + actAkpiVo.getS_passengerFlow() +",";
        }
        if (StringUtils.isNotBlank(actAkpiVo.getS_grossProfit())) {
            kpiType += "3";
            kpiValue += "3_" + actAkpiVo.getS_grossProfit();
        }

        //去除最后一个逗号
        if (kpiType.indexOf(",")!=-1){
            if ((kpiType.lastIndexOf(",")) == (kpiType.length()-1)){
                kpiType = kpiType.substring(0,kpiType.lastIndexOf(","));
            }
        }
        //去除最后一个逗号
        if (kpiValue.indexOf(",")!=-1){
            if ((kpiValue.lastIndexOf(",")) == (kpiValue.length()-1)){
                kpiValue = kpiValue.substring(0,kpiValue.lastIndexOf(","));
            }
        }

        KpiDetail kpiDetail = new KpiDetail();
        kpiDetail.setActId(active.getActId());
        kpiDetail.setKpiType(kpiType);
        kpiDetail.setKpiValue(kpiValue);

        //根据活动ID查询kpi表中的数据
        KpiDetail kpiDetail1 = kpiDetailService.selectByPrimaryKey(actAkpiVo.getS_actId());

        if (kpiDetail1!=null){//存在、更新
            int actIdz = kpiDetailService.updateByPrimaryKeySelective(kpiDetail);
        } else {//不存在 插入kpiDetail表数据
            int actId = kpiDetailService.insertSelective(kpiDetail);
        }

        //更新或保存活动标签关联表
        if (StringUtils.isNotBlank(actAkpiVo.getS_allPpArray())){
            String s_allPpArray = actAkpiVo.getS_allPpArray();
            String ppId = actAkpiVo.getS_ppIdArray();
            s_allPpArray = s_allPpArray + "_" + ppId;

            ActiveTagRelation activeTagRelation = new ActiveTagRelation();
            activeTagRelation.setActId(actAkpiVo.getS_actId());
            activeTagRelation.setSeedCategoryData(s_allPpArray);

            ActiveTagRelation seedActiveTagRelation = activeTagRelationService.selectByactId(actAkpiVo.getS_actId());
            if (seedActiveTagRelation!=null){
                int i = activeTagRelationService.updateByIdSelective(activeTagRelation);
            } else {
                int i = activeTagRelationService.insertSelective(activeTagRelation);
            }
        }

        //获取回显数据
        activeEchoController.selectAllCommon(countrys.split(","), active.getActId(), model);

        model.addAttribute("isSelect",1);

        return PAGE_NEWMKTACTIVE;
    }

}
