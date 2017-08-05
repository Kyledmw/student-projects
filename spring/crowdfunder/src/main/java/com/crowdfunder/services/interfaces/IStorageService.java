package com.crowdfunder.services.interfaces;

import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 ********************************************************************
 * Interface for storage service.
 * 
 * Taken from: <a href="https://github.com/spring-guides/gs-uploading-files">https://github.com/spring-guides/gs-uploading-files</a>
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public interface IStorageService {

    void init();

    void store(MultipartFile file);

    Stream<Path> loadAll();

    Path load(String filename);

    Resource loadAsResource(String filename);

    void deleteAll();
}
