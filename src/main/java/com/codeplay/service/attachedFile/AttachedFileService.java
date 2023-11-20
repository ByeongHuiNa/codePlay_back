package com.codeplay.service.attachedFile;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import com.codeplay.domain.Attached_FileVo;

public interface AttachedFileService {
	public void insertAttachedFile(List<MultipartFile> files, int attached_app_no, int attached_kind);
	public List<Attached_FileVo> findAttachedFile(int attached_app_no, int attached_kind);
	public Resource downloadFile(String attached_name);
}
