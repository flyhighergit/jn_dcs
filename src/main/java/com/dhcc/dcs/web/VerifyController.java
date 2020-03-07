package com.dhcc.dcs.web;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.dhcc.dcs.util.HttpDeal;
import com.dhcc.dcs.util.HttpServletRequestUtil;

/**
 * 验证controller
 * 
 * @author zhanglei
 *
 */
@Controller
@RequestMapping("/verify")
public class VerifyController {

    /**
     * 验证是否能获取到对应的磅房作业记录
     * 
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping("/getRecord")
    public void getIndexInfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = HttpServletRequestUtil.getString(request, "id");
        /* TbPoundRecords tbPoundRecords = tbPoundRecordsService.getById(id);

        // 如果获取到的作业记录为空，或者是未完成状态，那么就定向到数据错误页面
        if(tbPoundRecords == null || !OwnGlobals.DATA_STAUS_1.equals(tbPoundRecords.getStatus()) ){
            response.sendRedirect("http://www.baidu.com");
        }else {// 如果有记录且完成状态，定向到数据正确页面
            response.sendRedirect("http://www.sougou.com");
        } */
        /* HttpDeal hd = new HttpDeal();
        String url = "http://119.3.185.170:9011/jn_uds/tbPoundRecordsPrintController.do?goScanPrint&id=" + id;
        hd.get(url); */
        response.sendRedirect("http://119.3.185.170:9011/jn_uds/tbPoundRecordsPrintController.do?goScanPrint&id=" + id);
    }
}