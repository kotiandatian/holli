package com.framework.loippi.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.framework.loippi.dao.PlanetItemDao;
import com.framework.loippi.entity.PlanetItem;
import com.framework.loippi.service.PlanetItemService;
import com.framework.loippi.utils.Paramap;

import java.util.List;
/**
 * SERVICE - PlanetItem(星球资源表)
 * 
 * @author wmj
 * @version 2.0
 */
@Service
public class PlanetItemServiceImpl extends GenericServiceImpl<PlanetItem, Long> implements PlanetItemService {
	
	@Autowired
	private PlanetItemDao planetItemDao;
	
	
	@Autowired
	public void setGenericDao() {
		super.setGenericDao(planetItemDao);
	}

	public List<PlanetItem> findListByPage(Object parameter){
		return planetItemDao.findListByPage(parameter);
	}

	public Long deletes(Object parameter){
		return planetItemDao.deletes( parameter);
	}
	
	//通过ids删除多条记录
	@Transactional
	@Override
	public Long deleteMangByIds(Long ... ids) {
		return planetItemDao.deleteMangByIds(Paramap.create().put("ids", ids));
	}
	
	
}
