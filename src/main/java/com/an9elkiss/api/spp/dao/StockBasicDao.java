package com.an9elkiss.api.spp.dao;

import com.an9elkiss.api.spp.model.FinaForecast;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockBasicDao {

	int save(@Param("fields") String[] fields, @Param("item") Object[] item);

}
