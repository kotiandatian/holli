package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.framework.loippi.dao.GenSchemeDao;
import com.framework.loippi.entity.GenScheme;
import com.framework.loippi.service.GenSchemeService;
import com.framework.loippi.utils.Paramap;

/**
 * Service - 生成方案
 * 
 * @author Mounate Yan。
 * @version 1.0
 */
@Service("genSchemeServiceImpl")
public class GenSchemeServiceImpl extends GenericServiceImpl<GenScheme, Long> implements GenSchemeService{

	@Autowired
	private GenSchemeDao genSchemeDao;
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(genSchemeDao);
	}

	@Override
	public Long deleteMangByIds(Long[] ids) {
		return genSchemeDao.deleteMangByIds(Paramap.create().put("ids", ids));
	}
}