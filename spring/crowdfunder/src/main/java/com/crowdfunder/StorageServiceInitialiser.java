package com.crowdfunder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.crowdfunder.services.interfaces.IStorageService;

@Component
public class StorageServiceInitialiser implements CommandLineRunner {
	
	@Autowired
	private IStorageService _storageService;

	@Override
	public void run(String... arg0) throws Exception {
        _storageService.deleteAll();
        _storageService.init();
		
	}

}
