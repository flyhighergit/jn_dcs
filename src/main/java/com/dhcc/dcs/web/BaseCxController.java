package com.dhcc.dcs.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TbBaseCx;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.TbBaseCxService;
import com.dhcc.dcs.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 基础车型Controller
 * @author zhanglei
 *
 */
@Controller
@RequestMapping("/baseCx")
public class BaseCxController {
    @Autowired
    private TbBaseCxService tbBaseCxService;

    /**
     * 新增/编辑页路由
     * 
     * @return
     */
    @RequestMapping("/goEdit")
    public String goEdit() {
        return "frontend/baseCx/baseCxEdit";
    }

    /**
     * 获取信息
     * 
     * @param baseCxInfo
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @RequestMapping("/getBaseCxinfo")
    @ResponseBody
    public JSONObject getBaseCxinfo(TbBaseCx baseCx) {
        JSONObject json = new JSONObject();
        
        List<TbBaseCx> list = tbBaseCxService.findBySearch(baseCx);
        
        if (list !=null && !list.isEmpty()) {
            json.put("success", true);
            json.put("list", list);
        }else {
            json.put("success", false);
            json.put("errMsg", "没有符合的车型！");
        }

        return json;
    }

    /**
     * 添加
     * 
     * @param request
     * @return
     */
    @RequestMapping("/doAdd")
    @ResponseBody
    public JSONObject doAdd(HttpServletRequest request) {
        JSONObject json = new JSONObject();

        // 1.接收并转化相应的参数
        String baseCxStr = HttpServletRequestUtil.getString(request,"baseCxStr");
        ObjectMapper mapper = new ObjectMapper();
        TbBaseCx baseCx = null;
        try {
            baseCx = mapper.readValue(baseCxStr, TbBaseCx.class);
        }
        catch (Exception e) {
            json.put("success", false);
            json.put("errorMsg", "内部错误！");
            return json;
        }

        // 2.执行添加车型操作
        if (null != baseCx) {
            Execution<TbBaseCx> execution = tbBaseCxService.add(baseCx);
            if (execution.getState() == StateEnum.SUCCESS.getState()) {
                json.put("success", true);
                json.put("company", execution.getT());
            }
            else {
                json.put("success", false);
                json.put("errMsg", "添加失败！");
            }
        }

        return json;
    }
}
