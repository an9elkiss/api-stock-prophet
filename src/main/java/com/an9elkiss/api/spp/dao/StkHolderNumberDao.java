package com.an9elkiss.api.spp.dao;

import com.an9elkiss.api.spp.model.StkHolderNumber;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface StkHolderNumberDao {


	int batchSave(@Param("fields") String[] fields, @Param("items") Object[][] item);

	Integer count(StkHolderNumber stkHolderNumber);

}
