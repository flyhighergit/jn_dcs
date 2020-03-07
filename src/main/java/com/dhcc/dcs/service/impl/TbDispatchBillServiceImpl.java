package com.dhcc.dcs.service.impl;

import com.dhcc.dcs.service.TbDispatchBillService;
import com.dhcc.dcs.util.PageCalculator;
import com.dhcc.dcs.dao.TbDispatchBillDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TbDispatchBill;
import com.dhcc.dcs.entity.TbDriverVehicle;
import com.dhcc.dcs.enums.StateEnum;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 
 * 派车单service实现类
 * 
 * <pre>
 * 	历史记录：
 * 	2020-02-19 zhanglei
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
@Service("tbDispatchBillService")
public class TbDispatchBillServiceImpl implements TbDispatchBillService {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private TbDispatchBillDao tbDispatchBillDao;

	@Override
	public Execution<TbDispatchBill> findByPage(TbDispatchBill tbDispatchBill, Integer pageIndex, Integer pageSize) {
		try {
			// 页转行
			int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
			// 根据查询条件查询合同列表信息
			List<TbDispatchBill> list = tbDispatchBillDao.findByPage(tbDispatchBill, rowIndex, pageSize);
			// 查询在当前条件下的记录总数
			int count = tbDispatchBillDao.queryCount(tbDispatchBill);
			Execution<TbDispatchBill> execution = new Execution<TbDispatchBill>(StateEnum.SUCCESS, list);
			execution.setCount(count);
			return execution;
		} catch (Exception e) {
			logger.error("查询订单失败！exception:" + e, e);
			return new Execution<TbDispatchBill>(StateEnum.INNER_ERROR);
		}

	}

	@Override
	public List<TbDispatchBill> findBySearch(TbDispatchBill tbDispatchBill) {

		return tbDispatchBillDao.find(tbDispatchBill);
	}

	@Override
	public TbDispatchBill getById(String id) {

		return tbDispatchBillDao.getById(id);
	}

	@Override
	public void add(TbDispatchBill tbDispatchBill) {

		tbDispatchBill.setId(UUID.randomUUID().toString().replaceAll("-", ""));
		tbDispatchBillDao.add(tbDispatchBill);
	}

	@Override
	public void update(TbDispatchBill tbDispatchBill) {
		tbDispatchBillDao.updateNotNull(tbDispatchBill);
	}

	@Override
	public void delete(String id) {

		tbDispatchBillDao.delete(id);
	}

	@Override
	public Boolean signfor(TbDispatchBill tbDispatchBill) {
		if (tbDispatchBill.getDriverId() == null || tbDispatchBill.getId() == null) {
			return false;
		}
		int effectNum = tbDispatchBillDao.updateNotNull(tbDispatchBill);
		if (effectNum == 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public Execution<TbDispatchBill> findByLicense(List<TbDriverVehicle> relationlist, String status, Integer pageIndex,
			Integer pageSize) {

		try {
			// 页转行
			int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
			// 根据查询条件查询合同列表信息
			List<TbDispatchBill> list = tbDispatchBillDao.findByLicense(relationlist, status,rowIndex, pageSize);
			// 查询在当前条件下的记录总数
			int count = tbDispatchBillDao.queryCountByLicense(relationlist,status);
			Execution<TbDispatchBill> execution = new Execution<TbDispatchBill>(StateEnum.SUCCESS, list);
			execution.setCount(count);
			return execution;
		} catch (Exception e) {
			logger.error("查询订单失败！exception:" + e, e);
			return new Execution<TbDispatchBill>(StateEnum.INNER_ERROR);
		}
	}

}