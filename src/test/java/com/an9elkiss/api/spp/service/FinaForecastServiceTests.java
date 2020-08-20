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
package com.an9elkiss.api.spp.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import com.an9elkiss.api.spp.StockProphetApiBoot;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { StockProphetApiBoot.class })
@AutoConfigureMockMvc
public class FinaForecastServiceTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private FinaForecastService finaForecastService;

	@Test
	@Transactional
	@Rollback
	public void testfetchMyStocksToday() {
		finaForecastService.fetchMyStocksToday();
	}

}
