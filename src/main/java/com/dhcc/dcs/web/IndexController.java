package com.dhcc.dcs.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.entity.DriverInfo;
import com.dhcc.dcs.entity.TSBaseUser;
import com.dhcc.dcs.entity.TbDriverVehicle;
import com.dhcc.dcs.entity.VehicleInfo;
import com.dhcc.dcs.service.DriverInfoService;
import com.dhcc.dcs.service.TbDriverVehicleService;
import com.dhcc.dcs.service.VehicleInfoService;
import com.dhcc.dcs.util.OwnGlobals;



/**
 * 前端页面路由
 * @author zhanglei
 *
 */
@Controller
@RequestMapping("/index")
public class IndexController {
    @Autowired
    private DriverInfoService driverInfoService;
    @Autowired
    private TbDriverVehicleService tbDriverVehicleService;
    @Autowired
    private VehicleInfoService vehicleInfoService;

    @RequestMapping("/getIndexInfo")
    @ResponseBody
    public JSONObject getIndexInfo(HttpServletRequest request){
        JSONObject json = new JSONObject();

        TSBaseUser user = (TSBaseUser) request.getSession().getAttribute("user");

        if(user != null && !StringUtils.isEmpty(user.getIdCord()) ){
            List<DriverInfo> list = driverInfoService.findBySearch(new DriverInfo(user.getIdCord()));
            if(list == null || list.isEmpty() ){
                json.put("success", false);
                json.put("errMsg", "请先在个人-司机信息中完善您的信息！");
            }else if(list.size() > 1){
                json.put("success", false);
                json.put("errMsg","您的个人信息可能被重复注册，请联系管理员处理！");
            }else {
                DriverInfo driverInfo = list.get(0);
                List<TbDriverVehicle> relationList = tbDriverVehicleService.findBysearch(new TbDriverVehicle(driverInfo.getId(),driverInfo.getCompanyId(),OwnGlobals.DATA_STAUS_1));
                if(relationList != null && !relationList.isEmpty()){
                    VehicleInfo vehicleInfo = vehicleInfoService.getById(relationList.get(0).getVehicleId());
                    json.put("vehicleInfo",vehicleInfo);
                }
                
                json.put("success",true);
                json.put("driverInfo",driverInfo);
                
            }
        }else {
            json.put("success", false);
            json.put("errMsg","当前会话已失效，请重新登录！");
        }

        return json;
    }
}