package com.an9elkiss.api.spp.dao;

import com.an9elkiss.api.spp.command.ads.DailySortAdsCmd;
import com.an9elkiss.api.spp.model.DailySortAds;
import com.an9elkiss.api.spp.model.QuotationDaily;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface QuotationDailyDao {

	int save(@Param("fields") String[] fields, @Param("item") Object[] item);

	int batchSave(@Param("fields") String[] fields, @Param("items") Object[][] item);

	int update(QuotationDaily quotationDaily);

	Integer count(QuotationDaily quotationDaily);

	List<DailySortAds> sortByPctChg(DailySortAdsCmd dailySortAdsCmd);
}
