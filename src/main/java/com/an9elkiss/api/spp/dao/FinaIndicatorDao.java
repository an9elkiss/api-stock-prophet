package com.an9elkiss.api.spp.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.an9elkiss.api.spp.model.FinaIndicator;

@Mapper
public interface FinaIndicatorDao {

	int save(@Param("fields") String[] fields, @Param("item") Object[] item);

	FinaIndicator findForQuotationDailysNextMonth(@Param("tsCodes") List<String> tsCodes);

	int update(FinaIndicator finaIndicator);
}
