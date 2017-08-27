package com.dev_console;

public class CommandInput {

	private String _cmdLine;
	
	public String getCmdLine() {
		return _cmdLine;
	}
	
	public void setCmdLine(String cmdLine) {
		this._cmdLine = cmdLine;
	}
	
	public String[] getArgs() {
		return _cmdLine.split("/[^\\s\"]+|\"[^\"]*\"/g");
	}
}
