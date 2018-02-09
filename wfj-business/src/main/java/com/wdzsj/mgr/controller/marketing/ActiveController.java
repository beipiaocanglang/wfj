package com.wdzsj.mgr.controller.marketing;

import com.wdzsj.cmn.entity.Page;
import com.wdzsj.cmn.entity.PageRequest;
import com.wdzsj.mgr.controller.BaseController;
import com.wdzsj.mgr.entity.marketing.*;
import com.wdzsj.mgr.entity.marketing.constant.ActiveEnum;
import com.wdzsj.mgr.entity.marketing.vo.*;
import com.wdzsj.mgr.service.marketing.*;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 这个是关于营销活动的controller
 */
@Controller
@RequestMapping(value="/marketing")
@SuppressWarnings({"all", "rawtypes"})
public class ActiveController extends BaseController {

    private final static String PAGE_INDEX = "marketing/index";
    private final static String PAGE_NEWACTIVITY = "marketing/newactivity";
    private final static String PAGE_UPLOAD = "marketing/upload";
    private final static String PAGE_EDITACTIVE = "marketing/editActive";

    //活动列表服务
    @Resource
    private ActiveService activeService;

    //门店所在城市服务
    @Resource
    private StoresService storesService;

    //门店活动对应的kpi表服务
    @Resource
    private KpiDetailService kpiDetailService;

    //品类服务
    @Resource
    private CategoryService categoryService;

    //附件表数据
    @Resource
    private AttachmentService attachmentService;

    //活动标签关联服务
    @Resource
    private ActiveTagRelationService activeTagRelationService;

    //标签服务
    @Resource
    private ActiveLabelSpreadController labelSpreadControllerl;

    //公共部分
    @Resource
    private ActiveEchoController activeEchoController;

    /**
     * /marketing/testUpload
     * @return
     */
    @RequestMapping("testUpload")
    public String testUpload(){
        return PAGE_UPLOAD;
    }

    @RequestMapping("upload")
    public String upload(MultipartFile file) throws IOException {

        if (file!=null && file.getSize()>0) {
            String fileName = file.getOriginalFilename();//获取文件名称
            String extNameType = fileName.substring(fileName.lastIndexOf(".")+1);//获取文件的扩展名
            fileName = fileName.substring(0,fileName.lastIndexOf("."));//获取文件的扩展名
            byte[] bytes = file.getBytes();//获取文件内容

            Attachment attachment = new Attachment();
            attachment.setAttachmentId(2L);
            attachment.setAttachmentContent(bytes);
            attachment.setAttachmentName(fileName);
            attachment.setAttachmentCreatetime(new Date());
            attachment.setAttachmentUpdatetime(new Date());
            attachment.setAttachmentType(extNameType);

            //保存二维码到附件表  返回主键
            int rowNum = attachmentService.updateByPrimaryKeySelective(attachment);
        }

        return PAGE_UPLOAD;
    }

    /**
     * 营销活动首页列表
     * /marketing
     * @param model
     * @param request
     * @return
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
     * 新建营销活动页面
     * /marketing/newactivity
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/newactivity")
    public String newActivity(ModelMap model) {

        //获取所有门店名称及 所有信息
        List<StoresVo> list = storesService.findList();

        model.addAttribute("isSelect", 0);//作为标识 跳转到新建活动的第一个  活动计划
        model.addAttribute("list", list);

        return PAGE_NEWACTIVITY;
    }

    /**
     * 营销活动 ~ 保存活动计划
     * /marketing/saveActivityPlan
     * @param file
     * @param model
     * @param activeVo
     * @return
     */
    @RequestMapping("/saveActivityPlan")
    public String saveActivityPlan(ModelMap model, ActiveAndKpiDetailVo actAkpiVo, MultipartFile file, HttpServletRequest request) throws IOException, ParseException {

        //将字符串转成日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = sdf.parse(actAkpiVo.getS_startTime());
        Date endTime = sdf.parse(actAkpiVo.getS_endTime());

        Active active = new Active();
        active.setTheme(actAkpiVo.getS_theme());
        active.setStartTime(startTime);
        active.setEndTime(endTime);
        active.setUpdateTime(new Date());

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

        //设置推送文件ID(固定的)
        active.setAttachmentIdExportFile(new Long(1));

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

        KpiDetail kpiDetail1 = kpiDetailService.selectByPrimaryKey(actAkpiVo.getS_actId());
        if (kpiDetail1!=null){
            int actIdz = kpiDetailService.updateByPrimaryKeySelective(kpiDetail);
        } else {
            //保存kpiDetail表数据
            int actId = kpiDetailService.insertSelective(kpiDetail);
        }

        activeEchoController.selectAllCommon(countrys.split(","), active.getActId(), model);

        model.addAttribute("isSelect",1);

        return PAGE_NEWACTIVITY;
    }

    /**
     * 下载种子模板
     * /marketing/downloadSeedTemplate
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping("/downloadSeedTemplate")
    public void downloadSeedTemplate(HttpServletRequest request,HttpServletResponse response) throws IOException {
        Attachment attachment = attachmentService.selectByPrimaryKey(2L);
        String fileName = attachment.getAttachmentName();//文件名待确定
        String extNameType = attachment.getAttachmentType();//文件类型
        byte[] flowParent = attachment.getAttachmentContent();
        SimpleDateFormat format = new SimpleDateFormat("HHmmss");
        fileName = fileName + "_" + format.format(new Date()) + "." + extNameType;
        fileName = URLEncoder.encode(fileName, "UTF-8");
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
        response.addHeader("Content-Length", "" + flowParent.length);
        response.setContentType("application/octet-stream;charset=UTF-8");

        OutputStream outputStream = new BufferedOutputStream(response.getOutputStream());

        outputStream.write(flowParent);
        outputStream.flush();
        outputStream.close();
    }

    /**
     * 页面回显图片 暂时没有用到
     * /marketing/active_erWeiMaImg?actId={actId}
     * @param request
     * @param response
     * @param id 上传成功后返回的对应id
     * @throws Exception
     */
    @RequestMapping("/active_erWeiMaImg")
    public void book_getBookImg(HttpServletRequest request,HttpServletResponse response,Long actId) throws Exception{
        //根据ID查询活动表数据
        EditActiveAndKpiVo activeSeedVo = activeService.selectEditActiveDataByActId(actId);
        byte[] attachmentContent = activeSeedVo.getAttachmentContent();
        if (attachmentContent!=null){
            //写到页面
            OutputStream out= response.getOutputStream();
            out.write(attachmentContent);
        }
    }

    /**
     * 编辑营销活动首页列表
     * /marketing/editndex
     * @param model
     * @param request
     * @param actId
     * @return
     */
    @RequestMapping("/editndex")
    public String editndex(ModelMap model, HttpServletRequest request, Long actId) {

        activeEchoController.selectAllCommon(new String[]{}, actId, model);

        model.addAttribute("isSelect", 0);
        return PAGE_EDITACTIVE;
    }

    /**
     * 保存编辑后的活动计划
     * /marketing/saveEditActivityPlan
     * @param model
     * @param request
     * @param actAkpiVo
     * @param file
     * @return
     */
    @RequestMapping("/saveEditActivityPlan")
    public String saveEditActivityPlan(ModelMap model, HttpServletRequest request,ActiveAndKpiDetailVo actAkpiVo,
                                       MultipartFile file) throws IOException, ParseException {
        //将字符串转成日期
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        Date startTime = sdf.parse(actAkpiVo.getS_startTime());
        Date endTime = sdf.parse(actAkpiVo.getS_endTime());

        Active active = new Active();
        active.setActId(actAkpiVo.getS_actId());
        active.setTheme(actAkpiVo.getS_theme());
        active.setStartTime(startTime);
        active.setEndTime(endTime);
        active.setUpdateTime(new Date());

        if (file!=null && file.getSize()>0) {
            String fileName = file.getOriginalFilename();//获取文件名称
            String extNameType = fileName.substring(fileName.lastIndexOf(".")+1);//获取文件的扩展名
            byte[] bytes = file.getBytes();//获取文件内容

            Attachment attachment = new Attachment();
            attachment.setAttachmentContent(bytes);
            attachment.setAttachmentName(fileName);
            attachment.setAttachmentUpdatetime(new Date());
            attachment.setAttachmentType(extNameType);

            if (actAkpiVo.getAttachmentId() != null){
                attachment.setAttachmentId(actAkpiVo.getAttachmentId());
                //根据主键id修改 附加表数据
                int rowNum = attachmentService.updateByPrimaryKeySelective(attachment);
            } else {
                attachment.setAttachmentCreatetime(new Date());
                //根据主键id修改 附加表数据
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

        //保存活动表数据
        int row = activeService.updateByPrimaryKeySelective(active);

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

        //保存kpiDetail表数据
        int actId = kpiDetailService.updateByPrimaryKeySelective(kpiDetail);

        activeEchoController.selectAllCommon(countrys.split(","), active.getActId(), model);

        model.addAttribute("isSelect", 1);//控制打开哪一个标签

        return PAGE_EDITACTIVE;
    }
}
