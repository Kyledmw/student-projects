package com.game.save.editor.logging;

/**
 * Basic logging class that allows you to log messages to the console.
 * 
 * @author Kyle Williamson
 *
 */
public class Logger {
	
	private static final String console = "CONSOLE-LOG:";
	
	private static final String info = "INFO:";
	
	private static final String err = "ERROR:";
	
	private static final String debug = "DEBUG:";

	/**
	 * Log under info
	 * 
	 * @param message String to log
	 */
	public static void info(String message) {
		print(info, message);
	}
	
	/**
	 * Log under err
	 * 
	 * @param message String to log
	 */
	public static void err(String message) {
		print(err, message);
	}
	
	/**
	 * Log under debug
	 * 
	 * @param message String to log
	 */
	public static void debug(String message) {
		print(debug, message);
	}
	
	/**
	 * Worker method used by log methods to print to console.
	 * 
	 * @param type Type of log
	 * @param message message to log
	 */
	private static void print(String type, String message) {
		System.out.println(console + type + message);
	}
}
