package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.GenTableDao;
import com.framework.loippi.entity.GenTable;
import com.framework.loippi.service.GenTableService;
import com.framework.loippi.utils.Paramap;

/**
 * Service - 表
 * 
 * @author Mounate Yan。
 * @version 1.0
 */
@Service("genTableServiceImpl")
public class GenTableServiceImpl extends GenericServiceImpl<GenTable, Long> implements GenTableService{

	@Autowired
	private GenTableDao genTableDao;
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(genTableDao);
	}

	@Override
	public Long deleteMangByIds(Long[] ids) {
		return genTableDao.deleteMangByIds(Paramap.create().put("ids", ids));
	}
}