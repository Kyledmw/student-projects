package com.crowdfunder.io.storage;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;

import com.crowdfunder.models.Project;

/**
 ********************************************************************
 * Wrapper for a Multipart file that allows for easy customization of file properties without effecting application layers
 * 
 * Taken from: <a href="https://github.com/spring-guides/gs-uploading-files">https://github.com/spring-guides/gs-uploading-files</a>
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class ProjectMultipartFileWrapper implements MultipartFile {
	
	private MultipartFile _obj;
	private String _fileName;
	
	public ProjectMultipartFileWrapper(MultipartFile obj, Project proj) {
		this._obj = obj;
		String type = getContentType().replaceAll("image/", ".");
		_fileName = String.format("%d_project%s", proj.getProjectId(), type);
	}

	@Override
	public byte[] getBytes() throws IOException {
		return _obj.getBytes();
	}

	@Override
	public String getContentType() {
		return _obj.getContentType();
	}

	@Override
	public InputStream getInputStream() throws IOException {
		return _obj.getInputStream();
	}

	@Override
	public String getName() {
		return _fileName;
	}

	@Override
	public String getOriginalFilename() {
		return _fileName;
	}

	@Override
	public long getSize() {
		return _obj.getSize();
	}

	@Override
	public boolean isEmpty() {
		return _obj.isEmpty();
	}

	@Override
	public void transferTo(File arg0) throws IOException, IllegalStateException {
		_obj.transferTo(arg0);
		
	}
	
	public MultipartFile getOriginalObj() {
		return _obj;
	}

}
