/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *	  https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.an9elkiss.api.spp.service.tushare;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.an9elkiss.api.spp.StockProphetApiBoot;
import com.an9elkiss.api.spp.command.tushare.QuotationDailyCmd;
import com.an9elkiss.api.spp.command.tushare.TushareRespCmd;
import com.an9elkiss.api.spp.service.tushare.TushareClientService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { StockProphetApiBoot.class })
@AutoConfigureMockMvc
public class TushareClientServiceTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private TushareClientService tushareClientService;

	@Test
	public void testQuotationDaily() {
		QuotationDailyCmd cmd = new QuotationDailyCmd();
		cmd.setTs_code("000651.SZ");
		cmd.setTrade_date("20200716");

		TushareRespCmd respCmd = tushareClientService.quotationDaily(cmd);
		Assert.assertEquals(1, respCmd.getData().getItems().length);
		Assert.assertEquals(59.0, respCmd.getData().getItems()[0][2]);
	}


}
