package com.an9elkiss.api.spp.dao.ads;

import com.an9elkiss.api.spp.model.DailySortAds;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DailySortAdsDao {

	int batchSave(@Param("dailySortAdsList") List<DailySortAds> dailySortAdsList);

}
