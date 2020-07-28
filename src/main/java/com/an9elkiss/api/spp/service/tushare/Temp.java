package com.an9elkiss.api.spp.service.tushare;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Temp {

	private final static String FIELD_TS_CODE = "ts_code";

	public static void main(String[] args) {
		
		String[] x = new String[] { "a", "ts_code", "c" };

		int iTsCode = indexOf(x, FIELD_TS_CODE);
		System.out.println(iTsCode);

	}

	private static int indexOf(String[] x, String s) {
		for (int i = 0; i < x.length; i++) {
			if (x[i] == s) {
				return i;
			}
		}
		return -1;
	}

}
