package com.uttara.mvc.contactsApp;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

/**
 * 
 * @author mariojoshuaaugustine
 *
 */

public class Logger {

	//path where log file will be saved	
	private String path = "/Users/mariojoshuaaugustine/Desktop/tasklog.txt";
	// If Developer mode is set true, then the log will get O/P on display
	private boolean devMode = false;

	public synchronized void log(String data) {
		new Thread(new Runnable() {

			@Override
			public void run() {

				BufferedWriter bw = null;
				Date date = new Date();
				try {
					String msg = date + " : " + data;
					bw = new BufferedWriter(new FileWriter(path, true));
					bw.write(msg);
					bw.newLine();
					if (devMode)
						System.out.println(msg);
				} catch (IOException e) {
					e.printStackTrace();
				} finally {
					if (bw != null)
						try {
							bw.close();
						} catch (IOException e) {
							e.printStackTrace();
						}

				}
			}
		}).start();
	}

	private Logger() {

	}

	private static Logger obj = null;

	public static Logger getInstance() {
		if (obj == null)
			obj = new Logger();

		return obj;
	}

}
