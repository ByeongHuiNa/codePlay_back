package com.codeplay.service.attachedFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.codeplay.domain.Attached_FileVo;
import com.codeplay.mapper.attachedFile.AttachedFileMapper;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AttachedFileServiceImpl implements AttachedFileService {
	@Autowired
	AttachedFileMapper attachedFileMapper;
	
	@Value("${upload.path:C:/file}")
    private String uploadPath;
	
	@Transactional
	@Override
	public void insertAttachedFile(List<MultipartFile> files, int attached_app_no, int attached_kind) {
		for(MultipartFile file : files) {
			try {
				String fileName = file.getOriginalFilename();
				String filePath = Paths.get(uploadPath, fileName).toString();
				File dest = new File(filePath);
				file.transferTo(dest);
				
				Attached_FileVo vo = new Attached_FileVo();
				vo.setAttached_app_no(attached_app_no);
				vo.setAttached_kind(attached_kind);
				vo.setAttached_type(file.getContentType());
				vo.setAttached_loc(filePath);
				vo.setAttached_name(fileName);
				log.info(vo.toString() + "***");
				attachedFileMapper.insertAttachedFile(vo);
			} catch(IOException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public List<Attached_FileVo> findAttachedFile(int attached_app_no, int attached_kind) {
		List<Attached_FileVo> fileList = attachedFileMapper.findAttachedFile(attached_app_no, attached_kind);
		return fileList;
	}

	@Override
	public Resource downloadFile(String attached_name) {
        try {
        	log.debug(uploadPath + "****");
            Path filePath = Paths.get(uploadPath, attached_name); // 파일이 저장된 실제 경로로 변경
            Resource resource = new UrlResource(filePath.toUri());
            if (resource.exists()) {
                return resource;
            } else {
                throw new FileNotFoundException("File not found: " + attached_name);
            }
        } catch (MalformedURLException | FileNotFoundException ex) {
            throw new RuntimeException("File not found: " + attached_name, ex);
        }
	}
}
