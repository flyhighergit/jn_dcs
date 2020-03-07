package com.dhcc.dcs.service.impl;
import com.dhcc.dcs.service.PhoneLicensePlateInfoService;
import com.dhcc.dcs.dao.PhoneLicensePlateInfoDao;
import com.dhcc.dcs.dto.Execution;
import com.dhcc.dcs.entity.PhoneLicensePlateInfo;
import com.dhcc.dcs.enums.StateEnum;

import java.util.List;
import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

/**
 * 
 * 设备绑定service实现类
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
@Service("phoneLicensePlateInfoService")
public class PhoneLicensePlateInfoServiceImpl implements PhoneLicensePlateInfoService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Resource
	private PhoneLicensePlateInfoDao phoneLicensePlateInfoDao;
	
	
	@Override
	public List<PhoneLicensePlateInfo> findBySearch(PhoneLicensePlateInfo phoneLicensePlateInfo) {

		return phoneLicensePlateInfoDao.find(phoneLicensePlateInfo);
	}
	
	@Override
	public PhoneLicensePlateInfo getById(String id) {

		return phoneLicensePlateInfoDao.getById(id);
	}

	@Override
	@Transactional
	public Execution<PhoneLicensePlateInfo> add(PhoneLicensePlateInfo phoneLicensePlateInfo) {
		if(phoneLicensePlateInfo==null||phoneLicensePlateInfo.getId()==null
				||phoneLicensePlateInfo.getId()=="") {
			
			logger.error("绑定失败！未获取到设备信息");
			return new Execution<PhoneLicensePlateInfo>(StateEnum.EMPTY);
		}
		
		try {
			// 先去查询当前设备是否已绑定过车牌号
			PhoneLicensePlateInfo exist = phoneLicensePlateInfoDao.getById(phoneLicensePlateInfo.getId());
			if(exist!=null) {
				logger.error("当前设备已绑定过车牌号，不能重复绑定！");
				return new Execution<PhoneLicensePlateInfo>(StateEnum.INNER_ERROR);
			}
			
            int effectedNum = phoneLicensePlateInfoDao.add(phoneLicensePlateInfo);
            if (effectedNum <= 0) {
                logger.error("插入绑定设备记录时，返回0条变更");
                return new Execution<PhoneLicensePlateInfo>(StateEnum.INNER_ERROR, phoneLicensePlateInfo);
            }
        } catch (Exception e) {
            logger.error("绑定失败！error message:" + e.getMessage());
            return new Execution<PhoneLicensePlateInfo>(StateEnum.INNER_ERROR, phoneLicensePlateInfo);
        }

        return new Execution<PhoneLicensePlateInfo>(StateEnum.SUCCESS, phoneLicensePlateInfo);
	}
	
	@Override
	@Transactional
	public Execution<PhoneLicensePlateInfo> update(PhoneLicensePlateInfo phoneLicensePlateInfo) {

		if (null == phoneLicensePlateInfo
                || null == phoneLicensePlateInfo.getId()) {
            return new Execution<PhoneLicensePlateInfo>(StateEnum.EMPTY);
        }

        try {
            int effectedNum = phoneLicensePlateInfoDao
                    .updateNotNull(phoneLicensePlateInfo);
            if (effectedNum <= 0) {
                logger.error("修改绑定时，返回0条变更");
                return new Execution<PhoneLicensePlateInfo>(StateEnum.INNER_ERROR,
                        phoneLicensePlateInfo);
            }
        }catch (Exception e) {
            logger.error("修改绑定失败！error message:" + e.getMessage());
            return new Execution<PhoneLicensePlateInfo>(StateEnum.INNER_ERROR,
                    phoneLicensePlateInfo);
        }

        return new Execution<PhoneLicensePlateInfo>(StateEnum.SUCCESS,
                phoneLicensePlateInfo);
	}

	@Override
	@Transactional
	public Execution<PhoneLicensePlateInfo> delete(String id) {
		if(StringUtils.isEmpty(id)) {
			logger.error("未获取到设备信息");
			return new Execution<PhoneLicensePlateInfo>(StateEnum.EMPTY);
		}
		
		try {
            int effectedNum = phoneLicensePlateInfoDao.delete(id);
            if (effectedNum <= 0) {
                logger.error("解除绑定时，返回0条变更");
                Execution<PhoneLicensePlateInfo> execution = new Execution<PhoneLicensePlateInfo>(StateEnum.INNER_ERROR);
                execution.setStateInfo("您没有绑定过车牌！");
                return execution;
            }
        } catch (Exception e) {
            logger.error("解除绑定失败！error message:" + e.getMessage());
            return new Execution<PhoneLicensePlateInfo>(StateEnum.INNER_ERROR);
        }
		
        return new Execution<PhoneLicensePlateInfo>(StateEnum.SUCCESS);
	}
 	
}