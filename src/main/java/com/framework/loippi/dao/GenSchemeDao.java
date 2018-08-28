package com.framework.loippi.dao;

import com.framework.loippi.entity.GenScheme;
import com.framework.loippi.mybatis.dao.GenericDao;
import com.framework.loippi.utils.Paramap;

/**
 * DAO - Gen Scheme
 * 
 * @author Loippi Team
 * @version 1.0
 */
public interface GenSchemeDao extends GenericDao<GenScheme, Long> {

	Long deleteMangByIds(Paramap put);

}
