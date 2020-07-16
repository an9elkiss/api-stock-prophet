package com.an9elkiss.api.spp.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.an9elkiss.api.spp.command.TimeEntryCmd;
import com.an9elkiss.api.spp.model.TimeEntry;

@Mapper
public interface TimeEntryDao {

	int save(TimeEntry timeEntry);

	int update(TimeEntry timeEntry);

	int delete(Integer id);

	TimeEntryCmd findById(Integer id);

	Integer sumDurationOfDay(@Param("date") Date date, @Param("createBy") String createBy);

	List<TimeEntryCmd> findTimeEntries(Map<String, ?> searchParams);

}
