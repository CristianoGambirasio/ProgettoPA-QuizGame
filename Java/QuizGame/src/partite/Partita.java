package partite;

import gameReport.Report;

//Rappresenta un oggetto astratto partita, implementa Startable di Report
public abstract class Partita implements Startable<Report>{
	String quizPath;
	int nDomande;
}
