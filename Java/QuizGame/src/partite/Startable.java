package partite;
import java.util.Scanner;

import gameReport.Report;

//Interfaccia che impone la presenza di un metodo start che restituisce una sottoclasse di Report
public interface Startable<T extends Report>{
	public T start(Scanner in);
}
