package com.dhcc.dcs.service.impl;

import com.dhcc.dcs.service.TbPoundRecordsService;
import com.dhcc.dcs.util.PageCalculator;
import com.dhcc.dcs.dao.TbPoundRecordsDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TbPoundRecords;
import com.dhcc.dcs.enums.StateEnum;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 
 * 称重记录表service实现类
 * 
 * <pre>
 * 	历史记录：
 * 	2019-10-04 zhanglei
 * 	新建文件
 * </pre>
 * 
 * @author
 * 
 *         <pre>
 * SD
 * 	zhanglei
 * PG
 *	zhanglei
 * UT
 *
 * MA
 *         </pre>
 * 
 * @version $Rev$
 *
 *          <p/>
 *          $Id$
 *
 */
@Service("tbPoundRecordsService")
public class TbPoundRecordsServiceImpl implements TbPoundRecordsService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private TbPoundRecordsDao tbPoundRecordsDao;

	@Override
	public List<TbPoundRecords> findBySearch(TbPoundRecords tbPoundRecords) {

		return tbPoundRecordsDao.find(tbPoundRecords);
	}

	@Override
	public TbPoundRecords getById(String id) {

		return tbPoundRecordsDao.getById(id);
	}

	@Override
	@Transactional
	public Execution<TbPoundRecords> add(TbPoundRecords tbPoundRecords) {
		if (tbPoundRecords == null) {

			logger.error("添加称重记录失败！未获取到传递的信息");
			return new Execution<TbPoundRecords>(StateEnum.EMPTY);
		}

		try {
			tbPoundRecords.setId(UUID.randomUUID().toString().replace("-", ""));
			int effectedNum = tbPoundRecordsDao.add(tbPoundRecords);
			if (effectedNum <= 0) {
				logger.error("新增称重记录时，返回0条变更");
				return new Execution<TbPoundRecords>(StateEnum.INNER_ERROR, tbPoundRecords);
			}
		} catch (Exception e) {
			logger.error("新增称重记录失败！error message:" + e.getMessage());
			return new Execution<TbPoundRecords>(StateEnum.INNER_ERROR, tbPoundRecords);
		}

		return new Execution<TbPoundRecords>(StateEnum.SUCCESS, tbPoundRecords);
	}

	@Override
	@Transactional
	public Execution<TbPoundRecords> update(TbPoundRecords tbPoundRecords) {

		if (null == tbPoundRecords || null == tbPoundRecords.getId()) {
			return new Execution<TbPoundRecords>(StateEnum.EMPTY);
		}

		try {
			int effectedNum = tbPoundRecordsDao.updateNotNull(tbPoundRecords);
			if (effectedNum <= 0) {
				logger.error("修改称重记录时，返回0条变更");
				return new Execution<TbPoundRecords>(StateEnum.INNER_ERROR, tbPoundRecords);
			}
		} catch (Exception e) {
			logger.error("修改称重记录失败！error message:" + e.getMessage());
			return new Execution<TbPoundRecords>(StateEnum.INNER_ERROR, tbPoundRecords);
		}

		return new Execution<TbPoundRecords>(StateEnum.SUCCESS, tbPoundRecords);
	}

	@Override
	@Transactional
	public Execution<TbPoundRecords> delete(String id) {
		if (id == null || id == "") {
			logger.error("未获取到要删除记录的ID");
			return new Execution<TbPoundRecords>(StateEnum.EMPTY);
		}

		try {
			int effectedNum = tbPoundRecordsDao.delete(id);
			if (effectedNum <= 0) {
				logger.error("删除称重记录时，返回0条变更");
				return new Execution<TbPoundRecords>(StateEnum.INNER_ERROR, new TbPoundRecords());
			}
		} catch (Exception e) {
			logger.error("删除称重记录失败！error message:" + e.getMessage());
			return new Execution<TbPoundRecords>(StateEnum.INNER_ERROR, new TbPoundRecords());
		}

		return new Execution<TbPoundRecords>(StateEnum.SUCCESS, new TbPoundRecords());
	}

	@Override
	public Execution<TbPoundRecords> findUnderWay(String licensePlate) {
		if (StringUtils.isEmpty(licensePlate)) {
			return new Execution<TbPoundRecords>(StateEnum.EMPTY);
		}

		List<TbPoundRecords> list = tbPoundRecordsDao.findUnderWay(licensePlate);

		if (list == null || list.isEmpty()) {
			return new Execution<TbPoundRecords>(StateEnum.EMPTY, list);
		} else if (list.size() > 1) {
			logger.error("数据异常！车牌：“" + licensePlate + "”的车辆有多条未完成的磅房作业记录");
			return new Execution<TbPoundRecords>(StateEnum.INNER_ERROR, list);
		}

		return new Execution<TbPoundRecords>(StateEnum.SUCCESS, list.get(0));
	}

	@Override
	public Execution<Map<String, String>> getHistory(TbPoundRecords tbPoundRecords) {
		if (StringUtils.isEmpty(tbPoundRecords.getLicensePlate())) {
			return new Execution<>(StateEnum.EMPTY);
		}
		List<Map<String, String>> list = tbPoundRecordsDao.findHistoryWithContractInfo(tbPoundRecords);
		if (list == null || list.isEmpty()) {
			return new Execution<>(StateEnum.EMPTY, list);
		}
		return new Execution<>(StateEnum.SUCCESS, list);

	}

	@Override
	public Execution<Map<String, Object>> findByPage(Map<String, Object> map) {
		try {
			// 页转行
			String pageIndexStr = (String) map.get("pageIndex");
			String pageSizeStr = (String) map.get("pageSize");
			if (StringUtils.isEmpty(pageIndexStr) || StringUtils.isEmpty(pageSizeStr)) {
				return new Execution<Map<String, Object>>(StateEnum.INNER_ERROR);
			}

			Integer pageIndex = Integer.valueOf(pageIndexStr);
			Integer pageSize = Integer.valueOf(pageSizeStr);
			Integer rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
			map.put("rowIndex", rowIndex);
			// 根据查询条件查询合同列表信息
			List<Map<String, Object>> list = tbPoundRecordsDao.findByPage(map);
			// 查询在当前条件下的记录总数
			int count = tbPoundRecordsDao.queryCount(map);
			Execution<Map<String, Object>> execution = new Execution<>(StateEnum.SUCCESS, list);
			execution.setCount(count);

			return execution;
		} catch (Exception e) {
			logger.error("分页查询购销合同列表失败！error massage:" + e.getMessage(), e);
			return new Execution<Map<String, Object>>(StateEnum.INNER_ERROR);
		}
	}

	@Override
	public Map<String,Object> getWayBill(String poundRecordId) {
		
		return tbPoundRecordsDao.getWayBill(poundRecordId);
	}

}