package com.codeplay.service.calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeplay.mapper.calendar.CalendarMapper;

@Service
public class CalendarServiceImpl implements CalendarService {

	@Autowired
	CalendarMapper calendarMapper;
	

}
