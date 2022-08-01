package com.an9elkiss.api.spp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface TuShareDao {

	int batchSave(@Param("table") String table, @Param("fields") String[] fields, @Param("items") Object[][] item);

}
