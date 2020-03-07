package com.dhcc.dcs.web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.dao.TbCoalRelatedDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TSBaseUser;
import com.dhcc.dcs.entity.TbCoalPerson;
import com.dhcc.dcs.entity.TbCoalRelated;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.TSBaseUserService;
import com.dhcc.dcs.service.TbCoalPersonService;
import com.dhcc.dcs.service.TbCoalRelatedService;
import com.dhcc.dcs.util.HttpServletRequestUtil;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 企业Controller
 * @author zhanglei
 *
 */
@Controller
@RequestMapping("/coalRelated")
public class TBCoalRelatedController {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private TbCoalRelatedService tbCoalRelatedService;
	
	@Autowired
    private TbCoalPersonService tbCoalPersonService;
	
	@Resource
	private TbCoalRelatedDao tbCoalRelatedDao;
	
	@Autowired
	private TSBaseUserService tSBaseUserService;
	
	 /**
     * 列表页路由
     * @return
     * @author guofei
     */
    @RequestMapping("/list")
    public String list() {
        return "/frontend/coalRelated/coalRelatedList";
    }
    
    /**
     * 详情页路由
     * @return
     * @author zhanglei
     */
    @RequestMapping("/detail")
    public String detail() {
        return "/frontend/coalRelated/coalRelatedDetail";
    }
    
    /**
     * 录入人员详情路由
     * @return
     * @author guofei
     */
    @RequestMapping("/enterPersonDetail")
    public String enterPersonDetail() {
        return "/frontend/coalRelated/coalRelatedEnterPersonDetail";
    }
    
    /**
     * 企业信息录入页路由
     * @return
     * @author guofei
     */
    @RequestMapping("/goAdd")
    public String goAdd() {
        return "/frontend/coalRelated/coalRelatedAdd";
    }
    
    /**
     * 企业信息修改页路由
     * @return
     * @author guofei
     */
    @RequestMapping("/edit")
    public String edit() {
        return "/frontend/coalRelated/coalRelatedEdit";
    }
    
    /**
     *录入人员 企业信息修改页路由
     * @return
     * @author guofei
     */
    @RequestMapping("/enterPersonEdit")
    public String enterPersonEdit() {
        return "/frontend/coalRelated/coalRelatedEnterPersonEdit";
    }
    
    /**
     * 我的企业信息修改页路由
     * @return
     * @author guofei
     */
    @RequestMapping("/myEdit")
    public String myEdit() {
        return "/frontend/coalRelated/coalRelatedMyEdit";
    }
    
    /**
     * 我的企业信息录入路由
     * @return
     * @author guofei
     */
    @RequestMapping("/myComInfo")
    public String myAdd() {
        return "/frontend/coalRelated/coalRelatedMyDetail";
    }
    
    /**
     *  企业信息
     * @param coalId
     * @return
     * @author guofei
     */
    @RequestMapping("/getCoalRelatedInfo")
    @ResponseBody
    public JSONObject getCoalRelatedInfo(String coalId,HttpServletRequest request) {
        JSONObject json = new JSONObject();
        
        // 空值判断
        if(StringUtils.isEmpty(coalId)) {
        	// 当前人所在企业信息
        	TbCoalRelated companyInfo = (TbCoalRelated) request.getSession() .getAttribute("companyInfo");
        	TbCoalRelated tbCoalRelated = new TbCoalRelated();
        	
        	json.put("companyInfo", companyInfo);
        	json.put("tbCoalRelated", tbCoalRelated);
        	json.put("success", true);
        }else {
        	// 根据传入的ID获取企业信息基本信息
        	TbCoalRelated tbCoalRelated = tbCoalRelatedService.getById(coalId);
        	// 根据传入的ID获取营业执照
        	/*List<TbAttachmentQyxx> tbAttachmentQyxxList = tbAttachmentQyxxService.getByCoalId(coalId);*/
        	
            json.put("tbCoalRelated", tbCoalRelated);
            /*json.put("tbAttachmentQyxxList", tbAttachmentQyxxList);*/
            json.put("success", true);
        }
        
        return json;
    }
    
    /**
     *  企业信息
     * @param coalId
     * @return
     * @author guofei
     */
    @RequestMapping("/getMyCoalRelatedInfo")
    @ResponseBody
    public JSONObject getMyCoalRelatedInfo(HttpServletRequest request) {
        JSONObject json = new JSONObject();
        
        // 当前登录人信息
    	TSBaseUser user = (TSBaseUser) request.getSession().getAttribute("user");
    	
    	// 查找关联信息
    	TbCoalPerson personInfo = new TbCoalPerson();
        personInfo.setUserid(user.getId());
        List<TbCoalPerson> personInfoList = tbCoalPersonService.findBySearch(personInfo);
        
        // 查找企业信息
        TbCoalRelated companyInfo = tbCoalRelatedService.getById(personInfoList.get(0).getQyId());
    	
    	// 根据当前人所在企业信息的ID获取营业执照
    	/*List<TbAttachmentQyxx> tbAttachmentQyxxList = tbAttachmentQyxxService.getByCoalId(companyInfo.getId());*/
    	
    	json.put("companyInfo", companyInfo);
        /*json.put("tbAttachmentQyxxList", tbAttachmentQyxxList);*/
        json.put("success", true);
        
        return json;
    }
	
	/**
	 * 根据条件查询企业
	 * @param tbCoalRelated
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/getList")
	@ResponseBody
	public JSONObject getList(TbCoalRelated tbCoalRelated) {
		JSONObject json = new JSONObject();
		Execution<TbCoalRelated> execution =  tbCoalRelatedService.find(tbCoalRelated);
		if(execution.getState() == StateEnum.SUCCESS.getState()) {
			json.put("success", true);
			json.put("list", execution.getList());
		}else {
			json.put("success", false);
			json.put("errMsg", execution.getStateInfo());
		}
		
		return json;
	}
	
	/**
     * 核对页路由
     * @return
     * @author guofei
     */
    @RequestMapping("/check")
    public String check() {
        return "/frontend/coalRelated/coalRelatedCheck";
    }
    
    /**
     * 查询企业信息列表
     * 
     * @param carriage
     * @param request
     * @return
     */
    @RequestMapping(value = "/contractlist")
    @ResponseBody
    public JSONObject getCoalRelatedInfoList(TbCoalRelated tbCoalRelated,
            HttpServletRequest request) {
        JSONObject json = new JSONObject();
        
    	// 当前登录人信息
    	TSBaseUser user = (TSBaseUser) request.getSession().getAttribute("user");
    	
    	// 当前人所在企业信息
    	//TbCoalRelated companyInfo = (TbCoalRelated) request.getSession().getAttribute("companyInfo");
       
        // 分页信息
        Integer pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
        Integer pageSize = HttpServletRequestUtil.getInt(request, "pageSize");
        if ((pageIndex < 0) || (pageSize <0)) {
        	json.put("success", false);
        	json.put("errMsg", "没有分页信息！");
    		return json;
    	}
        
        // 获取企业
        Execution<TbCoalRelated> execution = tbCoalRelatedService.findByPage(tbCoalRelated, pageIndex, pageSize);
        
        // 结果处理
        if(execution.getState() == StateEnum.SUCCESS.getState()) {
        	for(int i =0;i<execution.getList().size();i++) {
        		String checkerId = execution.getList().get(i).getCheckerId();
        		if(null != checkerId && !checkerId.equals("")) {
        			TSBaseUser checkUser = tSBaseUserService.getTSBaseUserById(checkerId);
            		execution.getList().get(i).setCheckerId(checkUser.getRealName());
        		}
        	}
        	
        	json.put("user", user);
        	json.put("list", execution.getList());
        	json.put("count", execution.getCount());
            json.put("success", true);
        }else {
        	json.put("success", false);
        	json.put("errMsg", execution.getStateInfo());
        }
        
        return json;
    }
    
    /**
     * 新增企业信息
     * @param request
     * @return
     * @author zhanglei
     */
    @RequestMapping(value = "/doAdd", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject doAdd(HttpServletRequest request){
        JSONObject json = new JSONObject();
        // 当前登录人信息
    	TSBaseUser user = (TSBaseUser) request.getSession().getAttribute("user");
    	// 当前人所在企业信息
    	TbCoalRelated companyInfo = (TbCoalRelated) request.getSession().getAttribute("companyInfo");
    	
        // 1.接收并转化相应的参数
   	    String coalRelatedStr = HttpServletRequestUtil.getString(request,"coalRelatedStr");
   	    ObjectMapper mapper = new ObjectMapper();
   	    TbCoalRelated tbCoalRelated = null;
		try {
			tbCoalRelated = mapper.readValue(coalRelatedStr, TbCoalRelated.class);
		} catch (Exception e) {
			logger.error("对象转换失败！", e);
			json.put("success", false);
		    json.put("errorMsg", e.getMessage());
		    return json;
		}
		
        // 2.添加合同信息
        if (null != tbCoalRelated) {
	   	     try {
	   	        // 执行添加操作
	   	    	tbCoalRelated.setCreateBy(user.getUserName());//创建人账号
	   	    	tbCoalRelated.setCreateName(user.getRealName());//创建人
	   	    	
	   	    	//设置上级行政区划
	   	    	tbCoalRelated.setParentId(companyInfo.getId());
	   	    	tbCoalRelated.setParentCode(companyInfo.getCode());
	   	    	tbCoalRelated.setParentName(companyInfo.getName());
	   	    	tbCoalRelated.setParentShortName(companyInfo.getOrgShortName());
	   	    	
	   	    	//设置机构类型
	   	    	tbCoalRelated.setOrgTypeCode("2");
	   	    	tbCoalRelated.setOrgTypeName("企业");
	   			//判断如果审核状态是提交 
	     	 	if("0".equals(tbCoalRelated.getCheckStatus())){
	     	 		//TbAllCheckStatus tballcheckstatus = tbAllCheckStatusService.getByBusinessTable(OwnGlobals.TABLE_TB_COAL_RELATED);
	     	 		//判断是否核对，如果不核对，则改为核对通过，如果核对，则不做处理
	     	 		/*if("0".equals(tballcheckstatus.getCheckStatus())) {
	     	 			tbCoalRelated.setCheckStatus("1");
	     	 			tbCoalRelated.setCheckerId(user.getRealName());
	     	 			tbCoalRelated.setCheckTime(new Date());
	     	 		}*/
	     	 	}	
	   	    	/*Execution<TbCoalRelated> execution = tbCoalRelatedService.add(tbCoalRelated);*/
	   	    	/*if (execution.getState() == StateEnum.CHECK.getState()) {
	   	            json.put("success", true);
	   	            json.put("tbCoalRelated", execution.getT());
	   	            json.put("id", execution.getT().getId());
	   	        }  else {
	   	            json.put("success", false);
	   	            json.put("errMsg",execution.getStateInfo());
	   	        }*/
	   	     } catch (Exception e) {
	   	         json.put("success", false);
	   	         json.put("errMsg", e.toString());
	   	         return json;
	   	     }
	   	 }
	   	 else {
	   	     json.put("success", false);
	   	     json.put("errMsg", "请输入企业信息");
	   	 }

        return json;
    }
    
    /**
     *  修基本信息
     * @param carriageId
     * @return
     * @author guofei
     */
    @RequestMapping("/doUpdate")
    @ResponseBody
    public JSONObject doUpdate(String coalId,HttpServletRequest request) {
    	JSONObject json = new JSONObject();
    	// 1.接收并转化相应的参数
   	    String coalRelatedStr = HttpServletRequestUtil.getString(request,"coalRelatedStr");
   	    ObjectMapper mapper = new ObjectMapper();
   	    TbCoalRelated tbCoalRelated = null;
		try {
			tbCoalRelated = mapper.readValue(coalRelatedStr, TbCoalRelated.class);
		} catch (Exception e) { 
			logger.error("对象转换失败！", e);
			json.put("success", false);
		    json.put("errorMsg", e.getMessage());
		    return json;
		}
		
		 try {
				//判断如果审核状态是提交 
     	 	if("0".equals(tbCoalRelated.getCheckStatus())){
     	 		//TbAllCheckStatus tballcheckstatus = tbAllCheckStatusService.getByBusinessTable(OwnGlobals.TABLE_TB_COAL_RELATED);
     	 		//判断是否核对，如果不核对，则改为核对通过，如果核对，则不做处理
     	 		/*if("0".equals(tballcheckstatus.getCheckStatus())) {
     	 			tbCoalRelated.setCheckStatus("1");
     	 		}*/
     	 	}	
			 tbCoalRelated.setId(coalId);
			 tbCoalRelatedDao.updateNotNull(tbCoalRelated);
			 json.put("id", coalId);
			 json.put("success", true);
		 }
   	     catch (Exception e) {
   	         json.put("success", false);
   	         json.put("errMsg", e.toString());
   	         return json;
   	     }
		 
		 return json;
    }
    
    /**
     *  删除基本信息
     * @param coalId
     * @return
     * @author guofei
     */
    @RequestMapping("/doDelete")
    @ResponseBody
    public JSONObject doDelete(String coalId,HttpServletRequest request) {
    	JSONObject json = new JSONObject();
    	
    	if(StringUtils.isEmpty(coalId)) {
    		json.put("success", false);
    		json.put("errMsg", "未获取到要删除的企业信息编号！");
    		return json;
    	}
        
    	//int effectNum = tbCoalRelatedService.delete(coalId);
    	/*if(effectNum<0) {
    		json.put("success", false);
    		json.put("errMsg", "删除失败！");
    	}else {
    		json.put("success", true);
    	}*/
        
        return json;
        
    }
    
    /**
     * 跳转选择组织机构页面
     * @return
     * @author zhanglei
     */
    @RequestMapping("/choose")
    public String choose() {
    	return "frontend/coalRelated/chooseOrg";
    }
    
    @RequestMapping("/chooseDistrict")
    public String chooseDistrict() {
    	return "frontend/coalRelated/chooseDistrict";
    }
    
    /**
     * 获取下级组织机构或企业
     * @param parentId
     * @param request
     * @return
     * @author zhanglei
     */
    @RequestMapping("/getChildren")
    @ResponseBody
    public JSONObject getChildren(String parentId,HttpServletRequest request) {
    	JSONObject json = new JSONObject();
    	// 非空校验
    	if(StringUtils.isEmpty(parentId)) {
    		json.put("success", false);
    		json.put("errMsg", "您没有选择组织！");
    		return json;
    	}
    	
    	// 根据传入的组织ID，获取该组织信息
    	//TbCoalRelated parent = tbCoalRelatedService.getById(parentId);
    	// 如果这个组织是企业，那么就返回这个企业的信息
    	/*if(OwnGlobals.ORG_LEVEL_4.equals(parent.getOrgLevel())) {
    		json.put("success", true);
    		json.put("isCompany", true );
    		json.put("companyInfo", parent);
    		return json;
    	}*/
    	
    	// 查出下级单位
    	TbCoalRelated child = new TbCoalRelated();
    	//child.setCheckStatus("1");
    	child.setParentId(parentId);
    	List<TbCoalRelated> companyList = tbCoalRelatedService.findBySearch(child);
    	if(companyList!=null && !companyList.isEmpty()) {
    		json.put("success", true);
    		json.put("isCompany", false );
        	json.put("list", companyList);
    	}else {
    		json.put("success", false);
    		json.put("errMsg", "该组织没有下级组织或企业！");
    	}
    	
    	return json;
    }
    
    /**
     * 获取所有父级组织机构
     * 直到顶级
     * @return
     * @author zhanglei
     */
    @RequestMapping("/getParents")
    @ResponseBody
    public JSONObject getParents(String currentId,HttpServletRequest request) {
    	JSONObject json = new JSONObject();
    	
    	// 非空校验
    	if(StringUtils.isEmpty(currentId)) {
    		json.put("success", false);
    		json.put("errMsg", "您没有选择组织！");
    		return json;
    	}
    	
    	// 根据传入的组织ID，获取该组织信息
    	TbCoalRelated current = tbCoalRelatedService.getById(currentId);
    	if(current == null) {
    		json.put("success", false);
    		json.put("errMsg", "未找到您所在的企业信息");
    		return json;
    	}
    	
    	List<TbCoalRelated> list = new ArrayList<TbCoalRelated>();
    	list.add(current);
    	getParent(current.getParentId(),list);
    	if(list.isEmpty()) {
    		json.put("success", false);
    		json.put("errMsg", "未获取到任何父级组织机构！");
    	}else {
    		json.put("list", list);
    		json.put("success", true);
    	}
    	
    	return json;
    }
    
    /**
     * 获取所有父级组织
     * @param id
     * @param list
     * @author zhanglei
     */
    private void getParent(String id,List<TbCoalRelated> list) {
    	if(id!=null) {
    		TbCoalRelated company = tbCoalRelatedService.getById(id);
    		if(company!=null) {
    			list.add(company);
    			getParent(company.getParentId(),list);
    		}
    	}
    }
    
    /**
     * 获取省份信息
     * @return
     * @author zhanglei
     */
    @RequestMapping("/getProvinces")
    @ResponseBody
    public JSONObject getProvinces(HttpServletRequest request) {
    	JSONObject json = new JSONObject();
    	
    	TbCoalRelated company = new TbCoalRelated();
    	//company.setOrgLevel(OwnGlobals.ORG_LEVEL_1);
    	
    	List<TbCoalRelated> list = tbCoalRelatedService.findBySearch(company);
    	
    	if(list.isEmpty()) {
    		json.put("success", false);
    		json.put("errMsg", "数据获取失败！");
    	}else {
    		// 遍历列表并将山西省去除，因为无论是驻企运营人员还是县级运营人员
    		// 都不能直接录入本省的其他企业，因此不做展示
    		// 县级运营人员如果要录入所在县的企业，那么需要在页面单独提供所在县的选项
    		for(int i = 0; i < list.size();i++) {
    			if("140000000".equals(list.get(i).getCode())) {
    				list.remove(i);
    			}
    		}
    		json.put("list", list);
    		json.put("success", true);
    	}
    	
    	return json;
    }
    
    /**
     * 选择公司页面路由
     * @return
     */
    @RequestMapping("/chooseCompany")
    public String chooseCompany(){
        return "frontend/carriage/choose";
    }
}
