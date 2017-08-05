package com.crowdfunder.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.crowdfunder.services.interfaces.IStorageService;

/**
 ********************************************************************
 * Controller that handles RESTful HTTP Requests for file resources
 * 
 * Resources exist under the /api/files route.
 *
 * @author Kyle Williamson
 * @version 1.0.0
 ********************************************************************
 */
@RestController
@RequestMapping("/api/files")
public class ResourceRestController {
	
	@Autowired
	private IStorageService _storageServ;
	
	@RequestMapping(value = "/{filename:.+}", method = RequestMethod.GET)
	@ResponseBody
    public ResponseEntity<Resource> serveFile(@PathVariable String filename) {

        Resource file = _storageServ.loadAsResource(filename);
        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\""+file.getFilename()+"\"")
                .body(file);
    }

}
