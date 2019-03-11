package com.saptris.erp;

import java.io.IOException;

import javax.servlet.jsp.JspWriter;

public class JspStream {
	public static JspWriter jspOut;
	public static void setOutputStream(JspWriter out) {
		jspOut=out;
	}
	public static void printException(Exception exception, JspWriter out) {
		jspOut=out;
		printException(exception);
	}
	private static void printException(Exception exception) {
		printException(exception.getMessage(), exception);
	}
	public static void printException(String message,Exception exception, JspWriter out) {
		jspOut=out;
		printException(message, exception);
	}
	private static void printException(String message,Exception exception) {
		try {
			jspOut.print(message);
			System.out.println(message+" : ");
			exception.printStackTrace();
		}
		catch(IOException e) {
			System.out.println("Some unexcepted error occured while printing exception with JspStrem: ");
			e.printStackTrace();
		}
	}
}
