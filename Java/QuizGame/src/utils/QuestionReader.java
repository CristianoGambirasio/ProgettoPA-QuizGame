package utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class QuestionReader {
private String path;
	
	public QuestionReader(String path) {
		this.path = path;
	}
	public ArrayList<String> getDomanda(int nDomanda){
		ArrayList<String> res = new ArrayList<>();
		File file = new File(path+"\\"+nDomanda+".txt");
	    Scanner sc;
		try {
			sc = new Scanner(file);
			for(int i=0;i<5;i++) {
				res.add(sc.nextLine());
			}
			sc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		return res;
	}
}
