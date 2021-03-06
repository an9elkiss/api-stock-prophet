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
package com.an9elkiss.api.spp.dao;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.an9elkiss.api.spp.StockProphetApiBoot;
import com.an9elkiss.api.spp.model.FinaForecast;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { StockProphetApiBoot.class })
public class FinaForecastDaoTests {

	@Autowired
	private FinaForecastDao finaForecastDao;

	@Test
	public void testQuotationDaily() {

		FinaForecast finaForecast = finaForecastDao.findForQuotationDailysNextMonth(Arrays.asList("002560.SZ", "xxx"));

		Assert.assertNotNull(finaForecast);
		Assert.assertEquals("002560.SZ", finaForecast.getTs_code());
	}


}
