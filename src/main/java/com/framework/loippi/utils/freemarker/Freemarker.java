package com.framework.loippi.utils.freemarker;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.framework.loippi.entity.GenScheme;
import com.framework.loippi.entity.GenTable;
import com.framework.loippi.entity.GenTableColumn;
import com.loippi.core.gen.strategy.Context;
import com.loippi.core.gen.strategy.FullStrategy;
import com.loippi.core.gen.strategy.OnlyBusinessStrategy;
import com.loippi.core.gen.strategy.OnlyPersistenceStrategy;
import com.loippi.core.gen.strategy.OnlyViewStrategy;
import com.loippi.core.gen.strategy.PersistenceBusinessStrategy;

public class Freemarker {

	public static void main(String[] args) {
		Date currentDate = new Date();
		
		
		GenScheme genScheme = new GenScheme();
		
		genScheme.setAuthor("wufen");
		genScheme.setCreateDate(currentDate);
		genScheme.setCreator(1l);
		genScheme.setDescription("业务表");
		genScheme.setGenTableId(2l);
		genScheme.setId(2l);
		genScheme.setModuleName("业务表");
		genScheme.setPackageName("com.framework.loippi");
		genScheme.setSchemeName("业务表");
		genScheme.setSchemeTemplate("5");
		genScheme.setStrategy("1");
		genScheme.setUpdateDate(currentDate);
		genScheme.setUpdator(1l);
		
		//**********************************
		GenTable genTable = new GenTable();
		
		genTable.setClassName("GenTable");
		genTable.setCreateDate(currentDate);
		genTable.setCreator(1l);
		genTable.setDescription("业务表");
		genTable.setId(2l);
		genTable.setTableName("t_gen_table");
		genTable.setUpdateDate(currentDate);
		
		
		List<GenTableColumn> columns = new ArrayList<GenTableColumn>();
		GenTableColumn genTableColumn = new GenTableColumn();
		
		genTableColumn.setId(14l);
		genTableColumn.setComments("编号");
		genTableColumn.setCreateDate(currentDate);
		genTableColumn.setCreator(1l);
		genTableColumn.setGenTableId(2l);
		
		genTableColumn.setIsEdit(1);
		genTableColumn.setIsInsert(1);
		genTableColumn.setIsList(1);
		genTableColumn.setIsNull(0);
		genTableColumn.setIsPk(1);
		genTableColumn.setIsQuery(1);
		genTableColumn.setJavaField("id");
		genTableColumn.setJavaType("Long");
		genTableColumn.setJdbcType("bigint(20)");
		genTableColumn.setMybatisJdbcType("BIGINT");
		genTableColumn.setName("id");
		genTableColumn.setQueryType("=");
		genTableColumn.setShowType("input");
		
		
		
		GenTableColumn genTableColumn1 = new GenTableColumn();
		genTableColumn1.setId(14l);
		genTableColumn1.setComments("编号");
		genTableColumn1.setCreateDate(currentDate);
		genTableColumn1.setCreator(1l);
		genTableColumn1.setGenTableId(2l);
		
		genTableColumn1.setIsEdit(1);
		genTableColumn1.setIsInsert(1);
		genTableColumn1.setIsList(1);
		genTableColumn1.setIsNull(1);
		genTableColumn1.setIsPk(0);
		genTableColumn1.setIsQuery(1);
		genTableColumn1.setJavaField("name");
		genTableColumn1.setJavaType("String");
		genTableColumn1.setJdbcType("varchar(255)");
		genTableColumn1.setMybatisJdbcType("VARCHAR");
		genTableColumn1.setName("name");
		genTableColumn1.setQueryType("=");
		genTableColumn1.setShowType("input");
		
		
		GenTableColumn genTableColumn2 = new GenTableColumn();
		genTableColumn2.setId(14l);
		genTableColumn2.setComments("编号");
		genTableColumn2.setCreateDate(currentDate);
		genTableColumn2.setCreator(1l);
		genTableColumn2.setGenTableId(2l);
		
		genTableColumn2.setIsEdit(1);
		genTableColumn2.setIsInsert(1);
		genTableColumn2.setIsList(1);
		genTableColumn2.setIsNull(0);
		genTableColumn2.setIsPk(0);
		genTableColumn2.setIsQuery(1);
		genTableColumn2.setJavaField("name");
		genTableColumn2.setJavaType("String");
		genTableColumn2.setJdbcType("varchar(255)");
		genTableColumn2.setMybatisJdbcType("VARCHAR");
		genTableColumn2.setName("name");
		genTableColumn2.setQueryType("=");
		genTableColumn2.setShowType("input");
		
		
		columns.add(genTableColumn1);
		columns.add(genTableColumn2);
		columns.add(genTableColumn);
		
		
		
		genTable.setColumns(columns);
		
		
		
		generate(genScheme,genTable);
		
	}

	private static void generate(GenScheme scheme,GenTable table){
		
		String fullPath="D:/competition";
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("scheme", scheme);
		data.put("table", table);
		Context context = null;
		if(scheme.getSchemeTemplate().equals("1")){
			context = new Context(new OnlyPersistenceStrategy(), data,fullPath , table.getClassName(), scheme.getPackageName());
		}else if(scheme.getSchemeTemplate().equals("2")){
			context = new Context(new OnlyBusinessStrategy(), data, fullPath , table.getClassName(), scheme.getPackageName());
		}else if(scheme.getSchemeTemplate().equals("3")){
			context = new Context(new OnlyViewStrategy(), data, fullPath , table.getClassName(), scheme.getPackageName());
		}else if(scheme.getSchemeTemplate().equals("4")){
			context = new Context(new PersistenceBusinessStrategy(), data, fullPath , table.getClassName(), scheme.getPackageName());
		}else if(scheme.getSchemeTemplate().equals("5")){
			context = new Context(new FullStrategy(), data, fullPath , table.getClassName(), scheme.getPackageName());
		}
		if(context != null){
			context.generate();
		}
	}
}
