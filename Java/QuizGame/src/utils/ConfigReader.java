package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConfigReader {
	private String path;
	
	public ConfigReader(String path) {
		this.path = path;
	}
	
	public int getNDomande() {
		int res = 0;
	    File file = new File(path);
	    Scanner sc;
		try {
			sc = new Scanner(file);
			res = sc.nextInt();
			sc.close();
			return res;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return res;
	}
}
