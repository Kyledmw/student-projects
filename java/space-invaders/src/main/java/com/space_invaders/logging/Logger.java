package com.space_invaders.logging;

public class Logger {

	private static final String console = "CONSOLE-LOG:";

	private static final String info = "INFO:";

	private static final String err = "ERROR:";

	private static final String debug = "DEBUG:";

	public static void info(String message) {
		System.out.println(console + info + message);
	}

	public static void err(String message) {
		System.err.println(console + err + message);
	}

	public static void debug(String message) {
		System.out.println(console + debug + message);
	}
}
