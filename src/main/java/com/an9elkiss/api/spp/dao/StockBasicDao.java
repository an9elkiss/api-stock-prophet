package com.an9elkiss.api.spp.dao;

import com.an9elkiss.api.spp.command.tushare.StockBasicCmd;
import com.an9elkiss.api.spp.model.FinaForecast;
import com.an9elkiss.api.spp.model.StockBasic;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface StockBasicDao {

	int save(@Param("fields") String[] fields, @Param("item") Object[] item);

	List<StockBasic> find(StockBasicCmd stockBasicCmd);

}
