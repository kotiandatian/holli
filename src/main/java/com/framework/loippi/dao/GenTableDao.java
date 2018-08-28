package com.framework.loippi.dao;

import com.framework.loippi.entity.GenTable;
import com.framework.loippi.mybatis.dao.GenericDao;
import com.framework.loippi.utils.Paramap;

/**
 * DAO - Gen Table
 * 
 * @author Loippi Team
 * @version 1.0
 */
public interface GenTableDao extends GenericDao<GenTable, Long> {

	Long deleteMangByIds(Paramap put);

}
