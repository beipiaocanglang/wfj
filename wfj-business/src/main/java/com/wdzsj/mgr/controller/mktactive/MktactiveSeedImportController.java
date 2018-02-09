package com.wdzsj.mgr.controller.mktactive;

import com.wdzsj.mgr.castor.util.ExcelHelper;
import com.wdzsj.mgr.castor.util.JsonUtil;
import com.wdzsj.mgr.entity.marketing.vo.ZhiSuanVo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 重构后的种子会员 导入用户自己的excel文件 解析excel 回显
 * author : sunpanhu
 * create time : 2018/2/9 上午9:38
 */
@Controller
@RequestMapping(value = "/mktactive/seed/importSeed", method= RequestMethod.POST, produces = "text/html;charset=UTF-8")
@SuppressWarnings({"all", "rawtypes"})
public class MktactiveSeedImportController {

    private static final Logger log = Logger.getLogger(MktactiveSeedImportController.class);

    /**
     * 必须post请求
     * author : sunpanhu
     * createTime : 2018/2/9 下午3:44
     */
    @ResponseBody
    @RequestMapping
    public Object getExcelData(MultipartFile file) throws IOException, ParseException {

        //获取excel文件的io流
        InputStream inputStream = file.getInputStream();

        List<ZhiSuanVo> importExcel = new ExcelHelper(ZhiSuanVo.class).importExcel("", inputStream);

        return JsonUtil.toJson(importExcel);
    }
}
