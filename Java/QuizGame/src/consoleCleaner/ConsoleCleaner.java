package consoleCleaner;

import java.io.IOException;

public class ConsoleCleaner {
	public static void clear() {
		if(System.console() != null) {
			try {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} catch (InterruptedException | IOException e) {
			} 
		}
	}
	 
}