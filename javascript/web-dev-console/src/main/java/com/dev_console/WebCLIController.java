package com.dev_console;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/webcli")
public class WebCLIController {

	@RequestMapping(method = RequestMethod.POST, headers = "Accept=application/json")
	public CommandResponse postCommand(@RequestBody CommandInput cmdInput) {
		return new CommandResponse();
	}
}