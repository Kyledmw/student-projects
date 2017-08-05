package com.crowdfunder.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 ********************************************************************
 * ConfigurationProperties for the file storage layer
 * 
 * Taken from: <a href="https://github.com/spring-guides/gs-uploading-files">https://github.com/spring-guides/gs-uploading-files</a>
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@Component
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String _location = "upload-dir";

    public String getLocation() {
        return _location;
    }

    public void setLocation(String location) {
        this._location = location;
    }

}
