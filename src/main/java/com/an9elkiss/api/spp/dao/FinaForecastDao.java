package com.an9elkiss.api.spp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.an9elkiss.api.spp.model.FinaForecast;

@Mapper
public interface FinaForecastDao {

	int save(@Param("fields") String[] fields, @Param("item") Object[] item);

	FinaForecast findForQuotationDailysNextMonth(@Param("tsCodes") List<String> tsCodes);

	int update(FinaForecast finaForecast);

}
