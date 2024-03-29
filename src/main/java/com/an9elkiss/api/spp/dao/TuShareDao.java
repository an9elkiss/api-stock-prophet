package com.an9elkiss.api.spp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TuShareDao {

	int batchSave(@Param("table") String table, @Param("fields") String[] fields, @Param("items") Object[][] item);

	Integer count(@Param("table") String table, @Param("ts_code") String ts_code,
				  @Param("start_date") String start_date, @Param("date_field") String date_field);

}
