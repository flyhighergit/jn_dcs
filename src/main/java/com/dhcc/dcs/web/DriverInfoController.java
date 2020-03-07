package com.dhcc.dcs.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.alibaba.fastjson.JSONObject;
import com.dhcc.dcs.dao.TSBaseUserDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.DriverInfo;
import com.dhcc.dcs.entity.TSBaseUser;
import com.dhcc.dcs.entity.TbAttachmentZgz;
import com.dhcc.dcs.entity.TbCoalRelated;
import com.dhcc.dcs.entity.TbDriverJsz;
import com.dhcc.dcs.entity.TbDriverSfz;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.DriverInfoService;
import com.dhcc.dcs.service.TSBaseUserService;
import com.dhcc.dcs.service.TbAttachmentZgzService;
import com.dhcc.dcs.service.TbCoalRelatedService;
import com.dhcc.dcs.service.TbDriverJszService;
import com.dhcc.dcs.service.TbDriverSfzService;
import com.dhcc.dcs.util.HttpServletRequestUtil;

/**
 * 司机信息Controller
 * 
 * @author zhanglei
 *
 */
@Controller
@RequestMapping("/driverInfo")
public class DriverInfoController {
	@Autowired
	private DriverInfoService driverInfoService;
	@Autowired
	private TbCoalRelatedService tbCoalRelatedService;
	@Autowired
	private TbDriverSfzService tbDriverSfzService;
	@Autowired
	private TbDriverJszService tbDriverJszService;
	/*
	 * @Resource private TbAllCheckStatusService tbAllCheckStatusService;
	 */
	@Autowired
	private TSBaseUserService tSBaseUserService;
	@Autowired
	private TSBaseUserDao tSBaseUserDao;
	@Autowired
	private TbAttachmentZgzService tbAttachmentZgzService;

	/**
	 * 列表页面路由
	 * 
	 * @return
	 */
	@RequestMapping("/list")
	public String list() {
		return "frontend/driver/driverInfoList";
	}

	/**
	 * 详情页路由
	 * 
	 * @return
	 */
	@RequestMapping("/detail")
	public String detail() {
		return "frontend/driver/driverInfoDetail";
	}

	/**
	 * 新增页面路由
	 * 
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/add")
	public String add() {
		return "frontend/driver/driverInfoAdd";
	}

	/**
	 * 编辑页路由
	 * 
	 * @return
	 */
	@RequestMapping("/edit")
	public String edit() {
		return "frontend/driver/driverInfo";
	}

	/**
	 * 审核列表页路由
	 * 
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/check")
	public String check() {
		return "frontend/driver/driverInfoCheck";
	}

	/**
	 * 添加司机信息
	 * 
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/doAdd")
	@ResponseBody
	public JSONObject doAdd(DriverInfo driverInfo, String citizenNo, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		Map<String, String> map = new HashMap<>();

		if (driverInfo == null) {
			json.put("success", false);
			json.put("errMsg", "提交信息为空！");
			return json;
		}

		// 设置创建人信息
		TSBaseUser userInfo = (TSBaseUser) request.getSession().getAttribute("user");
		if (userInfo == null) {
			json.put("success", false);
			json.put("errMsg", "当前会话已失效，请重新登录");
			return json;
		} else {

			driverInfo.setIdCord(citizenNo);
			driverInfo.setFounder(userInfo.getRealName());
			driverInfo.setFounderId(userInfo.getId());
		}

		// 设置所在县信息
		TbCoalRelated countyInfo = tbCoalRelatedService.getById(driverInfo.getCompanyId());
		driverInfo.setCountyId(countyInfo.getParentId());
		driverInfo.setCountyCode(countyInfo.getParentCode());

		// 设置所在市信息
		TbCoalRelated cityInfo = tbCoalRelatedService.getById(countyInfo.getParentId());
		driverInfo.setCityId(cityInfo.getParentId());
		driverInfo.setCityCode(cityInfo.getParentCode());

		Execution<DriverInfo> execution = driverInfoService.add(driverInfo);
		if (execution.getState() == StateEnum.DRAFT.getState()) {

			if (null != execution.getT().getIdCord() && execution.getT().getIdCord() != "") {
				map.put("id", userInfo.getId());
				map.put("idCord", execution.getT().getIdCord());
				tSBaseUserDao.updateIdCord(map);

				userInfo.setIdCord(execution.getT().getIdCord());
				// 在session里设置用户最新的身份证号
				request.getSession().setAttribute("user", userInfo);
			}

			json.put("id", execution.getT().getId());
			json.put("success", true);
		} else {
			json.put("success", false);
			json.put("errMsg", "司机信息添加失败！");
		}

		return json;
	}

	/**
	 * 修改司机信息
	 * 
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/doEdit")
	@ResponseBody
	public JSONObject doEdit(DriverInfo driverInfo, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		Map<String, String> map = new HashMap<>();

		// 当前登录人信息
		TSBaseUser userInfo = (TSBaseUser) request.getSession().getAttribute("user");

		// 设置所在县信息
		/*
		 * TbCoalRelated countyInfo =
		 * tbCoalRelatedService.getById(driverInfo.getCompanyId());
		 * driverInfo.setCountyId(countyInfo.getParentId());
		 * driverInfo.setCountyCode(countyInfo.getParentCode());
		 */

		// 设置所在市信息
		/*
		 * TbCoalRelated cityInfo =
		 * tbCoalRelatedService.getById(countyInfo.getParentId());
		 * driverInfo.setCityId(cityInfo.getParentId());
		 * driverInfo.setCityCode(cityInfo.getParentCode());
		 */

		Execution<DriverInfo> execution = driverInfoService.modify(driverInfo);
		if (execution.getState() == StateEnum.SUCCESS.getState()) {

			if (null != execution.getT().getIdCord() && execution.getT().getIdCord() != "") {
				map.put("id", userInfo.getId());
				map.put("idCord", execution.getT().getIdCord());
				tSBaseUserDao.updateIdCord(map);

				userInfo.setIdCord(execution.getT().getIdCord());
				// 在session里设置用户最新的身份证号
				request.getSession().setAttribute("user", userInfo);
			}

			json.put("id", execution.getT().getId());
			json.put("success", true);
		} else {
			json.put("success", false);
			json.put("errMsg", "司机信息修改失败！");
		}

		return json;
	}

	/**
	 * 根据条件分页查询司机信息
	 * 
	 * @param driverInfo
	 * @param request
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/driverInfoList")
	@ResponseBody
	public JSONObject driverInfoList(DriverInfo driverInfo, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		// 获取当前人所在单位
		TbCoalRelated companyInfo = (TbCoalRelated) request.getSession().getAttribute("companyInfo");
		if (companyInfo == null) {
			json.put("success", false);
			json.put("errMsg", "当前会话已失效，请重新登录！");
			return json;
		}
		// 限定只能查看当前登录人所属单位的司机信息
		if (Integer.parseInt(companyInfo.getOrgLevel()) < 4) {
			switch (companyInfo.getOrgLevel()) {
			case "0":
				break;
			case "1":
				break;
			case "2":
				driverInfo.setCityCode(companyInfo.getCode());
				break;
			case "3":
				driverInfo.setCountyCode(companyInfo.getCode());
				break;
			default:
				break;
			}
		} else {
			driverInfo.setCompanyId(companyInfo.getId());
		}

		// 获取参数
		Integer pageIndex = HttpServletRequestUtil.getInt(request, "pageIndex");
		Integer pageSize = HttpServletRequestUtil.getInt(request, "pageSize");

		Execution<DriverInfo> execution = driverInfoService.findByPage(driverInfo, pageIndex, pageSize);
		// 结果处理
		if (execution.getState() == StateEnum.SUCCESS.getState()) {
			for (int i = 0; i < execution.getList().size(); i++) {
				String founderId = execution.getList().get(i).getFounderId();
				if (null != founderId && !founderId.equals("")) {
					TSBaseUser checkUser = tSBaseUserService.getTSBaseUserById(founderId);
					execution.getList().get(i).setFounderId(checkUser.getRealName());
				}

				String checkerId = execution.getList().get(i).getCheckerId();
				if (null != checkerId && !checkerId.equals("")) {
					TSBaseUser checkUser = tSBaseUserService.getTSBaseUserById(checkerId);
					execution.getList().get(i).setCheckerId(checkUser.getRealName());
				}
			}

			json.put("list", execution.getList());
			json.put("count", execution.getCount());
			json.put("success", true);
		} else {
			json.put("success", false);
			json.put("errMsg", execution.getStateInfo());
		}

		return json;
	}

	/**
	 * 根据条件查询司机信息 选择司机查询
	 * 
	 * @param driverInfo
	 * @param request
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/chooseDriverInfoList")
	@ResponseBody
	public JSONObject chooseDriverInfoList(DriverInfo driverInfo, HttpServletRequest request) {
		JSONObject json = new JSONObject();
		try {
			// 获取当前人所在单位
			/*
			 * TbCoalRelated companyInfo = (TbCoalRelated)
			 * request.getSession().getAttribute("companyInfo"); if(companyInfo == null) {
			 * json.put("success", false); json.put("errMsg", "当前会话已失效，请重新登录！"); return
			 * json; } // 限定只能查看当前登录人所属单位的司机信息
			 * if(Integer.parseInt(companyInfo.getOrgLevel())<4) {
			 * switch(companyInfo.getOrgLevel()) { case "0": break; case "1": break; case
			 * "2": driverInfo.setCityCode(companyInfo.getCode()); break; case "3":
			 * driverInfo.setCountyCode(companyInfo.getCode()); break; default : break; }
			 * }else { driverInfo.setCompanyId(companyInfo.getId()); }
			 */
			List<DriverInfo> list = driverInfoService.findBySearch(driverInfo);
			json.put("list", list);
			json.put("count", list != null ? list.size() : 0);
			json.put("success", true);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			json.put("success", false);
			json.put("errMsg", "查询失败");
		}

		return json;
	}

	/**
	 * 根据司机ID获取司机信息
	 * 
	 * @param driverInfoId
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/getDriverInfo")
	@ResponseBody
	public JSONObject getDriverInfo(String userId, HttpServletRequest request) {
		JSONObject json = new JSONObject();

		if (!StringUtils.isEmpty(userId)) {
			// 司机信息
			DriverInfo driverInfo = driverInfoService.getByFounderId(userId);

			if (null != driverInfo) {
				// 身份证证件信息
				List<TbDriverSfz> sfzList = tbDriverSfzService.getByDriverInfoId(driverInfo.getId());
				// 驾驶证证件信息
				List<TbDriverJsz> jszList = tbDriverJszService.getByDriverInfoId(driverInfo.getId());
				// 从业资格证证证件信息
				List<TbAttachmentZgz> zgzList = tbAttachmentZgzService.getByCompanyCode(driverInfo.getId());

				json.put("driverInfo", driverInfo);
				json.put("sfzList", sfzList);
				json.put("jszList", jszList);
				json.put("zgzList", zgzList);
				json.put("success", true);
			} else {
				json.put("success", false);
				json.put("errMsg", "未获取到司机信息");
			}
		} else {
			json.put("success", false);
			json.put("errMsg", "未获取到司机信息");
		}

		return json;
	}

	/**
	 * 根据司机身份证号获取司机信息
	 * 
	 * @param
	 * @return
	 * @author zhanglei
	 */
	@RequestMapping("/getDriverInfoByIdCord")
	@ResponseBody
	public JSONObject getDriverInfoByIdCord(String citizenNo, HttpServletRequest request) {
		JSONObject json = new JSONObject();

		if (!StringUtils.isEmpty(citizenNo)) {
			// 司机信息
			DriverInfo driverInfo = driverInfoService.getByIdCord(citizenNo);

			if (null != driverInfo) {
				// 身份证证件信息
				List<TbDriverSfz> sfzList = tbDriverSfzService.getByDriverInfoId(driverInfo.getId());
				// 驾驶证证件信息
				List<TbDriverJsz> jszList = tbDriverJszService.getByDriverInfoId(driverInfo.getId());

				json.put("driverInfo", driverInfo);
				json.put("sfzList", sfzList);
				json.put("jszList", jszList);
				json.put("success", true);
			} else {
				json.put("success", false);
				json.put("errMsg", "未获取到司机信息");
			}
		} else {
			json.put("success", false);
			json.put("errMsg", "未获取到司机信息");
		}

		return json;
	}

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 * @author zhanglei
	 */
	/*
	 * @RequestMapping("/doDelete")
	 * 
	 * @ResponseBody public JSONObject doDelete(String id) { JSONObject json = new
	 * JSONObject();
	 * 
	 * if(StringUtils.isEmpty(id)) { json.put("success", false); json.put("errMsg",
	 * "未获取到要删除的司机信息ID！"); return json; }
	 * 
	 * int effectNum = driverInfoService.delete(id); if(effectNum<0) {
	 * json.put("success", false); json.put("errMsg", "删除失败！"); }else {
	 * tbDriverSfzService.batchRemove(id); tbDriverJszService.batchRemove(id);
	 * json.put("success", true); }
	 * 
	 * return json; }
	 */

	/**
	 * 选择公司页面路由
	 * 
	 * @return
	 */
	@RequestMapping("/chooseCompany")
	public String chooseCompany() {
		return "frontend/driver/chooseCompany";
	}

	/**
	 * 绑定
	 * 
	 * @param userId
	 * @return
	 * @author guofei
	 */
	@RequestMapping("/bindDriverInfo")
	@ResponseBody
	public JSONObject bindDriverInfo(String userId, String companyId, HttpServletRequest request) {
		JSONObject json = new JSONObject();

		if (!StringUtils.isEmpty(userId)) {
			DriverInfo driverInfo = new DriverInfo();
			TbCoalRelated companyInfo = tbCoalRelatedService.getById(companyId);

			// 设置所属公司信息
			driverInfo.setCompanyId(companyInfo.getId());
			driverInfo.setCompanyCode(companyInfo.getCode());
			driverInfo.setCompanyName(companyInfo.getName());

			// 设置所属县信息
			driverInfo.setCountyId(companyInfo.getParentId());
			driverInfo.setCountyCode(companyInfo.getParentCode());

			// 设置所在市信息
			TbCoalRelated cityInfo = tbCoalRelatedService.getById(companyInfo.getParentId());
			driverInfo.setCityId(cityInfo.getParentId());
			driverInfo.setCityCode(cityInfo.getParentCode());

			// 司机信息
			driverInfo.setFounderId(userId);
			int number = driverInfoService.bindDriverInfo(driverInfo);
			if (number > 0) {
				json.put("success", true);
			} else {
				json.put("success", false);
			}
		} else {
			json.put("success", false);
			json.put("errMsg", "未获取到要解除绑定的司机信息");
		}

		return json;
	}

	/**
	 * 解除绑定
	 * 
	 * @param userId
	 * @return
	 * @author guofei
	 */
	@RequestMapping("/unbindDriverInfo")
	@ResponseBody
	public JSONObject unbindDriverInfo(String userId, HttpServletRequest request) {
		JSONObject json = new JSONObject();

		if (!StringUtils.isEmpty(userId)) {
			// 司机信息
			int number = driverInfoService.unbindDriverInfo(userId);
			if (number > 0) {
				json.put("success", true);
			} else {
				json.put("success", false);
			}
		} else {
			json.put("success", false);
			json.put("errMsg", "未获取到要解除绑定的司机信息");
		}

		return json;
	}
}
