package com.an9elkiss.api.spp.service.ads;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.an9elkiss.api.spp.command.ads.DailySortAdsCmd;
import com.an9elkiss.api.spp.command.tushare.StockBasicCmd;
import com.an9elkiss.api.spp.dao.QuotationDailyDao;
import com.an9elkiss.api.spp.dao.StockBasicDao;
import com.an9elkiss.api.spp.dao.ads.DailySortAdsDao;
import com.an9elkiss.api.spp.model.DailySortAds;
import com.an9elkiss.api.spp.model.StockBasic;
import com.an9elkiss.commons.command.ApiResponseCmd;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Service
public class DailySortAdsService {

	@Resource
	private QuotationDailyDao quotationDailyDao;

	@Resource
	private DailySortAdsDao dailySortAdsDao;

	@Resource
	private StockBasicDao stockBasicDao;

	public ApiResponseCmd<Integer> create(DailySortAdsCmd cmd) {
		buildToDate(cmd);

		StockBasicCmd stockBasicCmd = new StockBasicCmd();
		stockBasicCmd.setMarket(cmd.getMarket());
		stockBasicCmd.setOffSet(0);
		//一个版的股票不超过1W只
		stockBasicCmd.setLimit(10000);
		List<StockBasic> stockBasics = stockBasicDao.findByPage(stockBasicCmd);
		Map<String, String> codeIndustryMap = stockBasics.stream().collect(Collectors.toMap(StockBasic::getTsCode, StockBasic::getIndustry));

		List<DailySortAds> dailySortAdsList = quotationDailyDao.sortByPctChg(cmd);
		for(int i = 0; i<dailySortAdsList.size(); i++){
			DailySortAds dailySortAds = dailySortAdsList.get(i);
			dailySortAds.setDate(cmd.getDate());
			dailySortAds.setMarket(cmd.getMarket());
			//目前只支持 1 YEAR
			dailySortAds.setTime_num(1);
			dailySortAds.setTime_unit(cmd.getTimeUnit());
			dailySortAds.setSort(i+1);
			dailySortAds.setIndustry(codeIndustryMap.get(dailySortAds.getTs_code()));
		}

		return ApiResponseCmd.success(dailySortAdsDao.batchSave(dailySortAdsList));
	}

	private void buildToDate(DailySortAdsCmd cmd){
		DateTime dateTime = DateUtil.parse(cmd.getDate(), "yyyyMMdd");
		dateTime = DateUtil.offset(dateTime, DateField.YEAR, 1);
		cmd.setTo(DateUtil.format(dateTime, "yyyyMMdd"));
	}

}
