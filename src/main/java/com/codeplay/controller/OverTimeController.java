package com.codeplay.controller;

import com.codeplay.domain.AlarmVo;
import com.codeplay.domain.CriteriaVo;
import com.codeplay.domain.overTime.dto.OvertimeDto;
import com.codeplay.domain.overTime.vo.RequestOvertimeVo;
import com.codeplay.domain.overTime.vo.ResponseOvertimeVo;
import com.codeplay.security.TokenUtils;
import com.codeplay.service.alarm.AlarmService;
import com.codeplay.service.overTime.OverTimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Tag(name = "초과 근무관리", description = "초과근무 관리에 필요한 API")
@RestController
@Slf4j
@RequestMapping(value = "/api")
public class OverTimeController {
    @Autowired
    AlarmService alarmService;

    @Autowired
    OverTimeService overTimeService;

    @Operation(summary = "초과 근무 신청", description = "사용자가 초과근무 신청할때 사용합니다.")
    @PostMapping("/over-time")
    public ResponseEntity<Object> PostOverTime(@RequestBody RequestOvertimeVo vo, @RequestHeader("Authorization") String token) {
        if (TokenUtils.getPageListFromToken(token.substring(6)).contains(14)) {
            log.info("14번 페이지 권한(초과 근무 신청페이지)이 있습니다.");
        } else
            return ResponseEntity.status(403).build();
        log.info("over-time호출됨, vo: {}", vo);
        vo.getAttendanceVo().setAttend_status(vo.getAttendanceVo().getAttend_status().substring(0, 2));
        int data_no = overTimeService.app(vo);
        //알림전송

        AlarmVo alarmVo = new AlarmVo();
        alarmVo.setAlarm_content("초과근무 결재요청");
        alarmVo.setAlarm_kind(0);
        alarmVo.setUser_no(vo.getOvertimeVo().getOvertimeapp_user_no());
        alarmVo.setAlarm_send_user_no(vo.getAttendanceVo().getUser_no());
        alarmVo.setGo_to_url("/approvalattendance");
        alarmVo.setAlarm_data_no(data_no);
        alarmVo.setAlarm_index(2);
        log.info("전송한 알림 vo: {}", alarmVo);
        alarmService.addAlarm(alarmVo);
        return ResponseEntity.ok("신청 success");
    }

    @Operation(summary = "사용자별 초과근무 신청내역 조회할때 사용", description = "초과근무 페이지에서 목록탭을 클릭했을때 사용자가 신청한 초과근무 전체 조회합니다.")
    @Parameter(name = "user_no", description = "사용자를 식별하는 사용자번호")
    @Parameter(name = "start_date", description = "조회할 기간 시작일자")
    @Parameter(name = "end_date", description = "조회할 기간 마지막일자")
    @GetMapping("/over-time")
    public ResponseEntity<Object> getOverTimeList(@RequestParam int user_no, @RequestParam String start_date, @RequestParam String end_date, @RequestHeader("Authorization") String token) {
        log.info("/over-time에 호출함. user_no: {}, start : {}, end : {}", user_no, start_date, end_date);
        if (TokenUtils.getPageListFromToken(token.substring(6)).contains(14)) {
            log.info("14번 페이지 권한(초과 근무 신청페이지)이 있습니다.");
        } else
            return ResponseEntity.status(403).build();
        CriteriaVo criteriaVo = new CriteriaVo();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            criteriaVo.setStart_date(dateFormat.parse(start_date));
            criteriaVo.setEnd_date(dateFormat.parse(end_date));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        criteriaVo.setPage(0);
        criteriaVo.setLimit(7);
        OvertimeDto dto = new OvertimeDto(user_no, criteriaVo);
        List<ResponseOvertimeVo> list = overTimeService.getOverTimeList(dto);
        log.info("가져온 데이터 : {}", list);
        return ResponseEntity.ok(list);
    }

    @Operation(summary = "초과 근무 신청취소", description = "사용자가 초가근무 신청한것을 결재전 취소할때 사용합니다.")
    @PutMapping("/over-time")
    public ResponseEntity<Object> PutOverTime(@RequestBody RequestOvertimeVo vo, @RequestHeader("Authorization") String token) {
        if (!(TokenUtils.getUserNoFromToken(token.substring(6)).equals(vo.getAttendanceVo().getUser_no() + ""))) {
            if (TokenUtils.getPageListFromToken(token.substring(6)).contains(14)) {
                log.info("14번 페이지 권한(초과 근무 신청페이지)이 있습니다.");
                log.info("put over-time호출됨, vo: {}", vo);
                overTimeService.delete(vo);
                return ResponseEntity.ok("신청취소 success");
            } else
                return ResponseEntity.status(403).build();
        } else {
            log.info("초과근무 신청자와 로그인한 유저의 No가 다릅니다.");
            return ResponseEntity.status(403).build();
        }
    }
}