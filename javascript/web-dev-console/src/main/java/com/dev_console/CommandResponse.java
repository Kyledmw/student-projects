package com.dev_console;

public class CommandResponse {

	private boolean _isHTML;
	private boolean _isError;
	private String _output;
	
	public boolean getIsHTML() {
		return _isHTML;
	}
	
	public boolean getIsError() {
		return _isError;
	}
	
	public String getOutput() {
		return _output;
	}
	
	public void setIsHTML(boolean isHtml) {
		this._isHTML = isHtml;
	}
	
	public void setIsError(boolean isError) {
		this._isError = isError;
	}
	
	public void setOutput(String output) {
		this._output = output;
	}
}
