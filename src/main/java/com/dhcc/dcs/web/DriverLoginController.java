package com.dhcc.dcs.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.entity.TSBaseUser;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.TSBaseUserService;
import com.dhcc.dcs.util.CodeUtil;
import com.dhcc.dcs.util.HttpServletRequestUtil;
import com.dhcc.dcs.util.PasswordUtil;

/**
 * 
 * 司机登录controller
 */
@Controller
@RequestMapping("/driverlogin")
public class DriverLoginController {
    @Autowired
    private TSBaseUserService tSBaseUserService;

    /**
     * 登录页(登录页已集成到apk，无需做路由跳转，方便离线状态下访问)
     * 
     * @param request
     * @return
     */
    @RequestMapping("/login")
    public String login(HttpServletRequest request) {
        return "local/new_login";
    }

    /**
     * 主页
     * 
     * @return
     */
    @RequestMapping("/index")
    public String index() {
        return "frontend/new_index";
    }

    /**
     * 修改密码
     * 
     * @param request
     * @return
     */
    @RequestMapping(value = "/changePwd", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> changePwd(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 校验验证码
        if (!CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        // 获取原密码
        String password = HttpServletRequestUtil.getString(request, "password");
        // 获取新密码
        String newPassword = HttpServletRequestUtil.getString(request, "newPassword");
        // 从session中获取当前用户信息(用户一旦通过微信登录之后，便能获取到用户的信息)
        TSBaseUser user = (TSBaseUser) request.getSession().getAttribute("user");
        if (user == null) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "当前会话已失效，请重新登录！");
            return modelMap;
        }

        TSBaseUser userInfo = tSBaseUserService.getTSBaseUserByUserName(user.getUserName());
        if (null == userInfo) {
            modelMap.put("resultFlag", "1");// 不存在当前用户
            return modelMap;
        }

        // 非空判断，要求帐号新旧密码以及当前的用户session非空，且新旧密码不相同
        if (password != null && newPassword != null) {
            if (password.equals(newPassword)) {
                modelMap.put("success", false);
                modelMap.put("errMsg", "您输入的新、旧密码一致！");
                return modelMap;
            }
            try {
                // 数据库中 加密之后的密码
                String passwordMd5 = userInfo.getPassword();
                // 加密之后的输入的原密码
                String encryptPassword = PasswordUtil.encrypt(user.getUserName(), password,
                        PasswordUtil.getStaticSalt());
                if (encryptPassword != null && !encryptPassword.equals(passwordMd5)) {
                    modelMap.put("resultFlag", "2");// 旧密码错误
                    return modelMap;
                }

                // 加密之后的输入的新密码
                newPassword = PasswordUtil.encrypt(user.getUserName(), newPassword, PasswordUtil.getStaticSalt());
                tSBaseUserService.updatePwd(user.getId(), newPassword);
                modelMap.put("success", true);
            } catch (Exception e) {
                modelMap.put("success", false);
                modelMap.put("errMsg", StateEnum.INNER_ERROR.getStateInfo());
                return modelMap;
            }

        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "请输入密码");
        }
        return modelMap;
    }

    /**
     * 登录校验
     * 
     * @param request
     * @return
     * @author zhanglei
     */
    @RequestMapping(value = "/logincheck", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> logincheck(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 获取输入的帐号
        String idCord = HttpServletRequestUtil.getString(request, "idCord");
        // 获取输入的密码
        String password = HttpServletRequestUtil.getString(request, "password");
        // 获取是否需要进行验证码校验的标识符
        boolean needVerify = HttpServletRequestUtil.getBoolean(request, "needVerify");
        if (needVerify && !CodeUtil.checkVerifyCode(request)) {
            modelMap.put("success", false);
            modelMap.put("errMsg", "输入了错误的验证码");
            return modelMap;
        }
        // 非空校验
        if (idCord != null && password != null) {
            // 传入帐号和密码去获取平台帐号信息
            password = PasswordUtil.encrypt(idCord, password, PasswordUtil.getStaticSalt());
            TSBaseUser user = tSBaseUserService.getTSBaseUserByIdCardAndPwd(idCord, password);

            if (user != null && !StringUtils.isEmpty(user.getId()) && user.getStatus() == 1
                    && "4".equals(user.getBsUserType()) && !"1".equals(user.getDeleteFlag())) {
                // 若能取到帐号信息则登录成功
                modelMap.put("success", true);
                // 同时在session里设置用户信息
                request.getSession().setAttribute("user", user);
            } else {
                modelMap.put("success", false);
                modelMap.put("errMsg", "身份证号或密码错误");
            }
        } else {
            modelMap.put("success", false);
            modelMap.put("errMsg", "身份证号和密码均不能为空");
        }
        return modelMap;
    }

    /**
     * 当用户点击登出按钮的时候注销session
     * 
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    private Map<String, Object> logout(HttpServletRequest request) {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        // 将用户session置为空
        request.getSession().setAttribute("user", null);
        request.getSession().setAttribute("companyInfo", null);
        modelMap.put("success", true);
        return modelMap;
    }

    /**
     * 注册用户 身份证不能重复； 账号名不能重复； 1.先验证身份证是否重复 2.再验证用户名是否重复
     * 
     * @param request
     * @return
     * @throws IOException
     */
    @RequestMapping(value = "/signinUser", method = RequestMethod.POST)
    @ResponseBody
    private JSONObject signinUser(HttpServletRequest request, TSBaseUser baseUser) {
        JSONObject json = new JSONObject();
        if (!CodeUtil.checkVerifyCode(request)) {
            json.put("success", false);
            json.put("errMsg", "输入了错误的验证码");
            return json;
        }
        try {
            TSBaseUser idCordUser = tSBaseUserService.getTSBaseUserByIdCord(baseUser.getIdCord());
            // TSBaseUser userNameUser =
            // tSBaseUserService.getTSBaseUserByUserName(baseUser.getUserName());
            if (idCordUser == null) {
                String password = PasswordUtil.encrypt(baseUser.getIdCord(), baseUser.getPassword(),
                        PasswordUtil.getStaticSalt());
                baseUser.setPassword(password);
                baseUser.setUserName(baseUser.getIdCord());
                baseUser.setRealName(baseUser.getIdCord());
                short i = 1;
                baseUser.setStatus(i);
                baseUser.setDeleteFlag("0");
                baseUser.setBsUserType("4");
                tSBaseUserService.save(baseUser);
                request.getSession().setAttribute("user", baseUser);
                json.put("success", true);
                json.put("msg", "注册成功！");
            } else {
                json.put("success", false);
                json.put("errMsg", "注册失败，身份证已被注册，请确认！");
            }
        } catch (Exception e) {
            json.put("success", false);
            json.put("errMsg", "注册失败，请稍后重试！");
            e.printStackTrace();
        }
        return json;
    }

    @RequestMapping("/signin")
    public String signin() {
        return "frontend/signin";
    }

    @RequestMapping("/protocol")
    public String protocol() {
        return "frontend/protocol";
    }
}