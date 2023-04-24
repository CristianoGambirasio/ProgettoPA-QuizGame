package partite;
import java.util.Scanner;

import gameReport.Report;

public interface Startable<T extends Report>{
	public T start(Scanner in);
}
