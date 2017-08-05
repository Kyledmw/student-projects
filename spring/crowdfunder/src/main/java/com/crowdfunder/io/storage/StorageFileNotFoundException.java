package com.crowdfunder.io.storage;

/**
 ********************************************************************
 * Exception thrown from Storage service operations for when a file is not found
 * 
 * Taken from: <a href="https://github.com/spring-guides/gs-uploading-files">https://github.com/spring-guides/gs-uploading-files</a>
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
public class StorageFileNotFoundException extends StorageException {

	private static final long serialVersionUID = 1L;

	public StorageFileNotFoundException(String message) {
        super(message);
    }

    public StorageFileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}