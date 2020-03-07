package com.dhcc.dcs.service.impl;
import com.dhcc.dcs.service.TbCoalRelatedService;
import com.dhcc.dcs.util.PageCalculator;
import com.dhcc.dcs.dao.TbCoalRelatedDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TbCoalRelated;
import com.dhcc.dcs.enums.StateEnum;

import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

/**
 * 
 * 涉煤关联表service实现类
 * 
 * <pre>
 * 	历史记录：
 * 	2019-09-25 zhanglei
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
@Service("tbCoalRelatedService")
public class TbCoalRelatedServiceImpl implements TbCoalRelatedService {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Resource
	private TbCoalRelatedDao tbCoalRelatedDao;

	@Override
	public Execution<TbCoalRelated> findByPage(TbCoalRelated tbCoalRelated, Integer pageIndex, Integer pageSize) {
		try {
            // 页转行
            int rowIndex = PageCalculator.calculateRowIndex(pageIndex,pageSize);
                    
            // 根据查询条件查询列表信息
            List<TbCoalRelated> list = tbCoalRelatedDao.findByPage(tbCoalRelated, rowIndex, pageSize);
                    
            // 查询在当前条件下的记录总数
            int count = tbCoalRelatedDao.queryCount(tbCoalRelated);
            
            //结果处理
            Execution<TbCoalRelated> execution = new Execution<>(
                    StateEnum.SUCCESS, list);
            execution.setList(list);
            execution.setCount(count);

            return execution;
        } catch (Exception e) {
            logger.error("分页查询人员信息列表失败！", e);
            return new Execution<TbCoalRelated>(StateEnum.INNER_ERROR,
                    tbCoalRelated);
        }
	}

	@Override
	public List<TbCoalRelated> findBySearch(TbCoalRelated tbCoalRelated) {
		return tbCoalRelatedDao.findBySearch(tbCoalRelated);
	}

	@Override
	public TbCoalRelated getById(String id) {
		return tbCoalRelatedDao.getById(id);
	}

	@Override
	public Execution<TbCoalRelated> find(TbCoalRelated tbCoalRelated) {
		if(ObjectUtils.isEmpty(tbCoalRelated)) {
			return new Execution<TbCoalRelated>(StateEnum.EMPTY);
		}
		
		List<TbCoalRelated> list = tbCoalRelatedDao.findBySearch(tbCoalRelated);
		if(ObjectUtils.isEmpty(list)) {
			return new Execution<>(StateEnum.INNER_ERROR);
		}
		
		return new Execution<>(StateEnum.SUCCESS,list);
	}
	
}