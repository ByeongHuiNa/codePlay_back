package com.codeplay.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.codeplay.domain.Attached_FileVo;
import com.codeplay.service.attachedFile.AttachedFileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;

@Tag(name = "첨부파일 관리", description = "첨부파일에 필요한 API")
@RestController
@Slf4j
@RequestMapping(value = "/api")
public class FileUploadController {
	@Autowired
	private AttachedFileService attachedFileService;
	
	@Operation(summary = "파일 업로드", description = "사용자의 출퇴근 수정, 휴가 신청 페이지에서 사용")
	@Parameter(name = "attached_app_no", description = "첨부파일이 속한 신청서의 번호")
	@Parameter(name = "attached_kind", description = "첨부파일이 속한 신청서의 종류")
	@PostMapping("/file-upload")
	public ResponseEntity<String> fileUpload(@RequestParam List<MultipartFile> files, 
										@RequestParam int attached_app_no, @RequestParam int attached_kind) {
	    try {
	        attachedFileService.insertAttachedFile(files, attached_app_no, attached_kind);
	        return ResponseEntity.ok("파일 업로드 성공");
	    } catch (Exception e) {
	        log.error("파일 업로드 실패", e);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("파일 업로드 실패");
	    }
	}
	
	@Operation(summary = "파일 조회", description = "근태담당자의 출퇴근, 휴가 결재 페이지에서 사용")
	@Parameter(name = "attached_app_no", description = "첨부파일이 속한 신청서의 번호")
	@Parameter(name = "attached_kind", description = "첨부파일이 속한 신청서의 종류")
	@GetMapping("/file-list")
	public ResponseEntity<List<Attached_FileVo>> getFileList(@RequestParam int attached_app_no, @RequestParam int attached_kind){
		List<Attached_FileVo> files = attachedFileService.findAttachedFile(attached_app_no, attached_kind);
		return ResponseEntity.ok(files);
	}
	
	@Operation(summary = "파일 다운로드", description = "근태담당자의 출퇴근, 휴가 결재 페이지에서 사용")
	@Parameter(name = "attached_name", description = "첨부파일의 이름")
	@GetMapping("/file/{attached_name}")
	public ResponseEntity<Resource> downLoadFile(@PathVariable String attached_name){
        try {
			String decodedFileName = URLDecoder.decode(attached_name, StandardCharsets.UTF_8.toString());
			Resource resource = attachedFileService.downloadFile(decodedFileName);
			return ResponseEntity.ok()
					.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
					.body(resource);
		} catch (UnsupportedEncodingException e) {
	        log.error("Error decoding file name: " + e.getMessage(), e);
	        throw new RuntimeException("Error decoding file name: " + attached_name, e);
		}
	}
}
