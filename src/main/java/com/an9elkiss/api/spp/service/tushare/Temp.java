package com.an9elkiss.api.spp.service.tushare;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Temp {

	public static void main(String[] args) {

		Object[] item = new Object[3];
		item[0] = "aaa";
		item[1] = 111;
		item[2] = 123;

		log.error("拉取fina forecast数据，存DB时异常！{}", Arrays.toString(item));

	}

}
