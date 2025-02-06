package com.strategicgains.cli.util;

public abstract class Utils {
	private Utils() {
		// Prevent instantiation.
	}

	/**
	 * Reads a command string from the standard input.
	 * 
	 * @return The command entered by the user, stripped of leading and trailing whitespace and converted to lowercase.
	 */
	public static String readInput() {
		StringBuilder input = new StringBuilder();

		while (true) {
			try {
				int c = System.in.read();
				if (c == -1 || c == '\n') {
					break;
				}
				input.append((char) c);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return input.toString().strip().toLowerCase();
	}
}
