package com.dhcc.dcs.service.impl;

import com.dhcc.dcs.service.TbCoalPersonService;
import com.dhcc.dcs.util.PageCalculator;
import com.dhcc.dcs.dao.TbCoalPersonDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.TbCoalPerson;
import com.dhcc.dcs.enums.StateEnum;

import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 
 * 涉煤单位人员关联表service实现类
 * 
 * <pre>
 * 	历史记录：
 * 	2019-09-25 zhanglei
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
@Service("tbCoalPersonService")
public class TbCoalPersonServiceImpl implements TbCoalPersonService {

        private final Logger logger = LoggerFactory.getLogger(this.getClass());

        @Resource
        private TbCoalPersonDao tbCoalPersonDao;

        @Override
        public Execution<TbCoalPerson> findByPage(TbCoalPerson tbCoalPerson, Integer pageIndex, Integer pageSize) {
                try {
                        // 页转行
                        int rowIndex = PageCalculator.calculateRowIndex(pageIndex, pageSize);
                        // 根据查询条件查询列表信息
                        List<TbCoalPerson> list = tbCoalPersonDao.find(tbCoalPerson, rowIndex, pageSize);
                        // 查询在当前条件下的记录总数
                        int count = tbCoalPersonDao.queryCount(tbCoalPerson);
                        Execution<TbCoalPerson> execution = new Execution<>(StateEnum.SUCCESS, list);
                        execution.setList(list);
                        execution.setCount(count);

                        return execution;
                } catch (Exception e) {
                        logger.error("分页查询人员关系列表失败！", e);
                        return new Execution<TbCoalPerson>(StateEnum.INNER_ERROR, tbCoalPerson);
                }
        }

        @Override
        public List<TbCoalPerson> findBySearch(TbCoalPerson tbCoalPerson) {
                return tbCoalPersonDao.findBySearch(tbCoalPerson);
        }

        @Override
        public TbCoalPerson getById(String id) {
                return tbCoalPersonDao.getById(id);
        }

}