package com.an9elkiss.api.spp.service.tushare;

import java.util.Arrays;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class Temp {

	public static void main(String[] args) {
		
		String[] x = new String[] { "a", "b", "c" };

		int iTsCode = Arrays.binarySearch(x, "b");
		System.out.println(iTsCode);

	}

}
