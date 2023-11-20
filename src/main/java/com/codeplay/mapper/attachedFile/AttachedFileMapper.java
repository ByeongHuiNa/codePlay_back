package com.codeplay.mapper.attachedFile;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.codeplay.domain.Attached_FileVo;

@Mapper
public interface AttachedFileMapper {
	public int insertAttachedFile(Attached_FileVo vo);
	public List<Attached_FileVo> findAttachedFile(int attached_app_no, int attached_kind);
}
