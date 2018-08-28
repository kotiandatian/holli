package com.framework.loippi.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.loippi.dao.PlanetCategoryDao;
import com.framework.loippi.entity.PlanetCategory;
import com.framework.loippi.service.PlanetCategoryService;
import com.framework.loippi.utils.Paramap;
/**
 * SERVICE - PlanetCategory(星球类别表)
 * 
 * @author wmj
 * @version 2.0
 */
@Service
public class PlanetCategoryServiceImpl extends GenericServiceImpl<PlanetCategory, Long> implements PlanetCategoryService {
	
	@Autowired
	private PlanetCategoryDao planetCategoryDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(planetCategoryDao);
	}

	public List<PlanetCategory> findListByPage(Object parameter){
		return planetCategoryDao.findListByPage(parameter);
	}

	public Long deletes(Object parameter){
		return planetCategoryDao.deletes( parameter);
	}
	
	//通过ids删除多条记录
	@Transactional
	@Override
	public Long deleteMangByIds(Long ... ids) {
		return planetCategoryDao.deleteMangByIds(Paramap.create().put("ids", ids));
	}
	
}
