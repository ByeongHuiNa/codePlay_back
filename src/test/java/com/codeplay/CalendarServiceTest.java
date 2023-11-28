package com.codeplay;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.codeplay.domain.Leave_ApprovalVo;
import com.codeplay.domain.ScheduleVo;
import com.codeplay.domain.Schedule_MemoVo;
import com.codeplay.domain.UserVo;
import com.codeplay.domain.calendar.vo.UserLeaveVo;
import com.codeplay.domain.calendar.vo.UserScheduleLeaveMemoVo;
import com.codeplay.domain.calendar.vo.UserScheduleVo;
import com.codeplay.service.calendar.CalendarService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
class CalendarServiceTest {
	@Autowired
	CalendarService service;


//	//Calendar에서 user 본인의 사용자 일정을 조회할 때 사용
//	public List<ScheduleVo> getScheduleList(Long user_no);

	@Test
	@DisplayName("Calendar에서 user 본인의 사용자 일정을 조회할 때 사용")
	void test1() {
		log.info(service.getScheduleList(1L).toString());
	}
	
//	//Calendar에서 user 본인의 사용자 휴가를 조회할 때 사용
//	public List<Leave_ApprovalVo> getLeaveList(Long user_no);

	@Test
	@DisplayName("Calendar에서 user 본인의 사용자 휴가를 조회할 때 사용")
	void test2() {
		log.info(service.getLeaveList(1L).toString());
	}
	
//	//Calendar에서 user 본인의 사용자 일정을 추가할 때 사용
//	public void createSchedule(ScheduleVo schedule);

	@Test
	@DisplayName("Calendar에서 user 본인의 사용자 일정을 추가할 때 사용")
	void test3() {
		service.getScheduleList(1L);
		log.info("추가 성공");
	}
	
//	//Calendar에서 user 본인의 사용자 일정을 수정할 때 사용
//	public void updateSchedule(ScheduleVo schedule);

	@Test
	@DisplayName("Calendar에서 user 본인의 사용자 일정을 수정할 때 사용")
	void test4() {
		service.getScheduleList(1L);
		log.info("수정 성공");
	}
	
//	//Calendar에서 user 본인의 사용자 일정을 수정할 때 사용(카드뷰)
//	public void updateScheduleCardview(ScheduleVo schedule);

	@Test
	@DisplayName("Calendar에서 user 본인의 사용자 일정을 수정할 때 사용(카드뷰)")
	void test5() {
		service.getScheduleList(1L);
		log.info("수정 성공");
	}
	
//	//Calendar에서 user 본인의 사용자 일정을 삭제할 때 사용
//	public void deleteSchedule(Long schedule_no);

	@Test
	@DisplayName("Calendar에서 user 본인의 사용자 일정을 삭제할 때 사용")
	void test6() {
		service.getScheduleList(1L);
		log.info("삭제 성공");
	}
	
//	//Calendar에서 사용자 및 사용자 부서의 공유 일정을 조회할 때 사용
//	public List<UserScheduleVo> getShereScheduleList(Long user_no);

	@Test
	@DisplayName("Calendar에서 사용자 및 사용자 부서의 공유 일정을 조회할 때 사용")
	void test7() {
		log.info(service.getShereScheduleList(1L).toString());
	}
	
//	//Calendar에서 사용자 및 사용자 부서의 휴가 일정을 조회할 때 사용
//	public List<UserLeaveVo> getShereLeaveList(Long user_no);

	@Test
	@DisplayName("Calendar에서 사용자 및 사용자 부서의 휴가 일정을 조회할 때 사용")
	void test8() {
		log.info(service.getShereLeaveList(1L).toString());
	}
	
//	//Calendar에서 사용자 및 사용자 부서 메모를 조회할 때 사용 (일정을 클릭했을때)
//	public List<UserScheduleLeaveMemoVo> getScheduleMemoList(Long schedule_no);

	@Test
	@DisplayName("Calendar에서 사용자 및 사용자 부서 메모를 조회할 때 사용 (일정을 클릭했을때)")
	void test9() {
		log.info(service.getScheduleMemoList(1L).toString());
	}
	
//	//Calendar에서 사용자 및 사용자 부서 메모를 조회할 때 사용 (휴가를 클릭했을때)
//	public List<UserScheduleLeaveMemoVo> getLeaveMemoList(Long leave_no);

	@Test
	@DisplayName("Calendar에서 사용자 및 사용자 부서 메모를 조회할 때 사용 (휴가를 클릭했을때)")
	void test10() {
		log.info(service.getLeaveMemoList(1L).toString());
	}
	
//	//Calendar에서 사용자 및 사용자 부서 메모를 클릭 했을때 작성자의 정보를 추가 조회
//	public UserVo getMemoDetail(Long memo_no);

	@Test
	@DisplayName("Calendar에서 사용자 및 사용자 부서 메모를 클릭 했을때 작성자의 정보를 추가 조회")
	void test11() {
		log.info(service.getMemoDetail(1L).toString());
	}
	
//	//Calendar에서 사용자 및 사용자 부서 메모를 추가할 때 사용
//	public void createMemo(Schedule_MemoVo memoVo);

	@Test
	@DisplayName("Calendar에서 사용자 및 사용자 부서 메모를 추가할 때 사용")
	void test12() {
		service.getScheduleList(1L);
		log.info("추가 성공");
	}
	
//	//Calendar에서 사용자 및 사용자 부서 메모를 수정할 때 사용
//	public void updateMemo(Schedule_MemoVo memoVo);

	@Test
	@DisplayName("Calendar에서 사용자 및 사용자 부서 메모를 수정할 때 사용")
	void test13() {
		service.getScheduleList(1L);
		log.info("수정 성공");
	}
	
//	//Calendar에서 사용자 및 사용자 부서 메모를 제거할 때 사용
//	public void deleteMemo(Long memo_no);
	
	@Test
	@DisplayName("Calendar에서 사용자 및 사용자 부서 메모를 제거할 때 사용")
	void test14() {
		service.getScheduleList(1L);
		log.info("제거 성공");
	}
	

}
