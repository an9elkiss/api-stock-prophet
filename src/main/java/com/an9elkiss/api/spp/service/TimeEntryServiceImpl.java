package com.an9elkiss.api.spp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.an9elkiss.api.spp.command.TimeEntryCmd;
import com.an9elkiss.api.spp.dao.TimeEntryDao;

@Service
public class TimeEntryServiceImpl implements TimeEntryService {

	private final Logger logger = LoggerFactory.getLogger(TimeEntryServiceImpl.class);

	private final static Integer MIN_DURATION_EVERY_DAY = 30;

	@Autowired
	private TimeEntryDao timeEntryDao;


	@Override
	public TimeEntryCmd findById(Integer id) {

		return timeEntryDao.findById(id);
	}



}
