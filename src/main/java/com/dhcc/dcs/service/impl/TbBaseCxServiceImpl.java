package com.dhcc.dcs.service.impl;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dhcc.dcs.dao.TbBaseCxDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TbBaseCx;
import com.dhcc.dcs.enums.StateEnum;
import com.dhcc.dcs.service.TbBaseCxService;
import com.dhcc.dcs.util.PageCalculator;

/**
 * 
 * service实现类
 * 
 * <pre>
 * 	历史记录：
 * 	2019-09-26 zhanglei
 * 	新建文件
 * </pre>
 * 
 * @author 
 * <pre>
 * SD
 * 	zhanglei
 * PG
 *	zhanglei
 * UT
 *
 * MA
 * </pre>
 * @version $Rev$
 *
 * <p/> $Id$
 *
 */
@Service("tbBaseCxService")
public class TbBaseCxServiceImpl implements TbBaseCxService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private TbBaseCxDao tbBaseCxDao;

	@Override
	public Execution<TbBaseCx> findByPage(TbBaseCx tbBaseCx, Integer pageIndex, Integer pageSize) {
		try {
            // 页转行
            int rowIndex = PageCalculator.calculateRowIndex(pageIndex,
                    pageSize);
            // 根据查询条件查询列表信息
            List<TbBaseCx> list = tbBaseCxDao.find(tbBaseCx, rowIndex, pageSize);
            // 查询在当前条件下的记录总数
            int count = tbBaseCxDao.queryCount(tbBaseCx);
            Execution<TbBaseCx> execution = new Execution<>(
                    StateEnum.SUCCESS, list);
            execution.setCount(count);

            return execution;
        } catch (Exception e) {
            logger.error("分页查询承运合同列表失败！", e);
            return new Execution<TbBaseCx>(StateEnum.INNER_ERROR,
                    tbBaseCx);
        }
	}

	@Override
	public List<TbBaseCx> findBySearch(TbBaseCx tbBaseCx) {
		return tbBaseCxDao.findBySearch(tbBaseCx);
	}

	@Override
	public TbBaseCx getById(String id) {
		return tbBaseCxDao.getById(id);
	}

	@Override
	@Transactional
	public Execution<TbBaseCx> add(TbBaseCx tbBaseCx) {
		if (null == tbBaseCx) {
            return new Execution<TbBaseCx>(StateEnum.EMPTY);
        }

        try {
            tbBaseCx.setId(UUID.randomUUID().toString().replace("-", ""));
            tbBaseCx.setCreateTime(new Date());
            
            int effectedNum = tbBaseCxDao
                    .insert(tbBaseCx);
            if (effectedNum <= 0) {
                logger.error("新增承运合同时，返回0条变更");
                return new Execution<TbBaseCx>(StateEnum.INNER_ERROR,
                        tbBaseCx);
            }
        }
        catch (Exception e) {
            logger.error("新增承运合同失败！error message:" + e.getMessage());
            return new Execution<TbBaseCx>(StateEnum.INNER_ERROR,
                    tbBaseCx);
        }

        return new Execution<TbBaseCx>(StateEnum.SUCCESS,
                tbBaseCx);
	}

	@Override
	@Transactional
	public Execution<TbBaseCx> update(TbBaseCx tbBaseCx) {
		if (null == tbBaseCx
                || null == tbBaseCx.getId()) {
            return new Execution<TbBaseCx>(StateEnum.EMPTY);
        }

        try {
            int effectedNum = tbBaseCxDao
                    .updateNotNull(tbBaseCx);
            if (effectedNum <= 0) {
                logger.error("修改承运合同时，返回0条变更");
                return new Execution<TbBaseCx>(StateEnum.INNER_ERROR,
                        tbBaseCx);
            }
        }
        catch (Exception e) {
            logger.error("修改承运合同失败！error message:" + e.getMessage());
            return new Execution<TbBaseCx>(StateEnum.INNER_ERROR,
                    tbBaseCx);
        }

        return new Execution<TbBaseCx>(StateEnum.SUCCESS,
                tbBaseCx);
	}

	@Override
	@Transactional
	public Execution<TbBaseCx> delete(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	
 	
}