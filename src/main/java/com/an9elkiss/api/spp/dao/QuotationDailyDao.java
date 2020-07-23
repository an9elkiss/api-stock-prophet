package com.an9elkiss.api.spp.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.an9elkiss.api.spp.model.QuotationDaily;

@Mapper
public interface QuotationDailyDao {

	int save(@Param("fields") String[] fields, @Param("item") Object[] item);

	int update(QuotationDaily quotationDaily);


}
