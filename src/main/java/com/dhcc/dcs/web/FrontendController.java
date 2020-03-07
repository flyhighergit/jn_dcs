package com.dhcc.dcs.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.entity.TSBaseUser;



/**
 * 前端页面路由
 * @author zhanglei
 *
 */
@Controller
@RequestMapping("/frontend")
public class FrontendController {
    
    /**
     * 首页（作业）页面路由
     * @return
     */
    @RequestMapping("/index")
    public String index(){
        return "frontend/new_index";
    }

    /**
     * 电子货运单列表列表路由
     * @return
     */
    @RequestMapping("/waybill")
    public String waybill(){
        return "frontend/waybill/waybill";
    }

    /**
     * 电子货运单详情列表页面路由
     * @return
     */
    @RequestMapping("/billList")
    public String billList(){
        return "frontend/waybill/billList";
    }

    /**
     * 个人信息页面路由
     * @return
     */
    @RequestMapping("/personal")
    public String personal(){
        return "frontend/personal/personal";
    }

    /**
     * 二维码页面路由
     * @return
     */
    @RequestMapping("/qrcode")
    public String qrcode(){
        return "frontend/poundwork/qrcode";
    }

    /**
     * 注册页面路由
     * @return
     */
    @RequestMapping("/signin")
    public String signin(){
        return "frontend/signin";
    }

    /**
     * 协议页面路由
     * @return
     */
    @RequestMapping("/protocol")
    public String protocol(){
        return "frontend/protocol";
    }

    /**
     * 司机信息页面路由
     * @return
     */
    @RequestMapping("/driverInfo")
    public String driverInfo(){
        return "frontend/driver/driverInfo";
    }

    /**
     * 车辆关系页面路由
     * @return
     */
    @RequestMapping("/mytrucks")
    public String mytrucks(){
        return "frontend/vehicle/mytrucks";
    }

    /**
     * 查看当前绑定企业页面路由
     * @return
     */
    @RequestMapping("/relation")
    public String relation(){
        return "frontend/carriage/relation";
    }

    /**
     * 选择公司页面路由
     * @return
     */
    @RequestMapping("/chooseCompany")
    public String chooseCompany(){
        return "frontend/carriage/choose";
    }

    /**
     * 车辆绑定页面路由
     * @return
     */
    @RequestMapping("/vehicleBind")
    public String vehicleBind(){
        return "frontend/vehicle/vehicleBind";
    }
    
    /**
     * 获取个人信息
     * @param request
     * @return
     * @author guofei
     */
    @RequestMapping("/getMyInfo")
    @ResponseBody
    public JSONObject getMyInfo(HttpServletRequest request) {
    	JSONObject json = new JSONObject();
    	
    	// 当前登录人基本信息
    	TSBaseUser tSBaseUser = (TSBaseUser) request.getSession().getAttribute("user");
    	// 判断是否session已失效
    	if(tSBaseUser==null) {
    		json.put("success", false);
    		json.put("errMsg", "当前会话失效，请重新登录！");
    	}else {
    		json.put("tSBaseUser", tSBaseUser);
    		json.put("success", true);
    	}
    	
    	return json;
    }
    
    /**
     * 修改密码
     * @return
     * @author guofei
     */
    @RequestMapping("/editPassword")
    public String editPassword() {
        return "/frontend/setting/editPassword";
    }
}
