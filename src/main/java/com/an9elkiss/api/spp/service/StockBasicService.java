package com.an9elkiss.api.spp.service;

import com.an9elkiss.api.spp.command.DailyImportCmd;
import com.an9elkiss.api.spp.command.StkHolderNumberFetchCmd;
import com.an9elkiss.api.spp.command.StkHolderNumberImportCmd;
import com.an9elkiss.api.spp.command.tushare.QuotationDailyCmd;
import com.an9elkiss.api.spp.command.tushare.StockBasicCmd;
import com.an9elkiss.api.spp.command.tushare.TushareRespCmd;
import com.an9elkiss.api.spp.dao.QuotationDailyDao;
import com.an9elkiss.api.spp.dao.StkHolderNumberDao;
import com.an9elkiss.api.spp.dao.StockBasicDao;
import com.an9elkiss.api.spp.model.QuotationDaily;
import com.an9elkiss.api.spp.model.StkHolderNumber;
import com.an9elkiss.api.spp.model.StockBasic;
import com.an9elkiss.api.spp.service.tushare.TushareClientService;
import com.an9elkiss.commons.command.ApiResponseCmd;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@Log4j2
@Service
public class StockBasicService {

	@Resource
	private StockBasicDao stockBasicDao;

	@Resource
	private QuotationDailyDao quotationDailyDao;

	@Resource
	private StkHolderNumberDao stkHolderNumberDao;

	@Resource
	private TushareClientService tushareClientService;

	@Resource
	private QuotationDailyService quotationDailyService;

	@Resource
	private StkHolderNumberService stkHolderNumberService;


	public ApiResponseCmd<Integer> fetch(StockBasicCmd cmd) {

		TushareRespCmd tushareRespCmd = tushareClientService.stockBasic(cmd);

		Arrays.stream(tushareRespCmd.getData().getItems()).forEach(item-> stockBasicDao.save(tushareRespCmd.getData().getFields(), item));

		return ApiResponseCmd.success(tushareRespCmd.getData().getItems().length);
	}

	public ApiResponseCmd<?> importDaily(DailyImportCmd cmd) {
		StockBasicCmd stockBasicCmd = new StockBasicCmd();
		BeanUtils.copyProperties(cmd, stockBasicCmd);
		stockBasicCmd.setOffSet((cmd.getPage()-1)*cmd.getSize());
		stockBasicCmd.setLimit(cmd.getSize());

		List<StockBasic> stockBasics = stockBasicDao.findByPage(stockBasicCmd);
		log.info("ts-stockBasic-分页查询 第{}页，{}/{}条", cmd.getPage(), stockBasics.size(), cmd.getSize());

		stockBasics.forEach(b->{
			QuotationDaily quotationDaily = new QuotationDaily();
			quotationDaily.setTs_code(b.getTsCode());
			if (quotationDailyDao.count(quotationDaily) > 0){
				log.info("ts-daily-已有数据，跳过 ts_code = {}", b.getTsCode());
				return;
			}

			QuotationDailyCmd quotationDailyCmd = new QuotationDailyCmd();
			BeanUtils.copyProperties(cmd, quotationDailyCmd);
			quotationDailyCmd.setTs_code(b.getTsCode());

			quotationDailyService.fetch(quotationDailyCmd);
		});

		return ApiResponseCmd.success();
	}

	public ApiResponseCmd<?> importStkHolderNumber(StkHolderNumberImportCmd cmd) {
		StockBasicCmd stockBasicCmd = new StockBasicCmd();
		BeanUtils.copyProperties(cmd, stockBasicCmd);
		stockBasicCmd.setOffSet((cmd.getPage()-1)*cmd.getSize());
		stockBasicCmd.setLimit(cmd.getSize());

		List<StockBasic> stockBasics = stockBasicDao.findByPage(stockBasicCmd);
		log.info("ts-stockBasic-分页查询 第{}页，{}/{}条", cmd.getPage(), stockBasics.size(), cmd.getSize());

		stockBasics.forEach(b->{
			StkHolderNumber stkHolderNumber = new StkHolderNumber();
			stkHolderNumber.setTs_code(b.getTsCode());
			if (stkHolderNumberDao.count(stkHolderNumber) > 0){
				log.info("ts-stockBasic-已有数据，跳过 ts_code = {}", b.getTsCode());
				return;
			}

			StkHolderNumberFetchCmd stkHolderNumberFetchCmd = new StkHolderNumberFetchCmd();
			BeanUtils.copyProperties(cmd, stkHolderNumberFetchCmd);
			stkHolderNumberFetchCmd.setTs_code(b.getTsCode());

			stkHolderNumberService.fetch(stkHolderNumberFetchCmd);
		});

		return ApiResponseCmd.success();
	}
}
