package partite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import gameReport.ReportSP;
import utils.ConfigReader;
import utils.ConsoleCleaner;
import utils.QuestionReader;

public class SinglePlayer extends Partita{
	public SinglePlayer(String path) {
		this.quizPath = path;
		ConfigReader cr = new ConfigReader(path + "\\config.txt");
		this.nDomande = cr.getNDomande();
	}
	
	@Override
	public ReportSP start(Scanner in) {
		QuestionReader qr = new QuestionReader(quizPath);
		
		String[] reportStrings = new String[nDomande];
		String rispGiusta;
		String domanda;
		int nRispGiuste = 0;
		
		for(int i=0;i<nDomande;i++) {
			ArrayList<String> q = qr.getDomanda(i+1);
			rispGiusta = q.get(1);
			domanda = q.get(0);
			
			q.remove(0);
			Collections.shuffle(q);
						
			int rispData;
			do {
				System.out.println("Domanda "+(i+1)+": "+domanda);
				System.out.println("1) "+q.get(0)+"\t\t\t2)"+q.get(1));
				System.out.println("3) "+q.get(2)+"\t\t\t4)"+q.get(3));
				System.out.print("Inserisci la risposta: (1-2-3-4): ");
				while (!in.hasNextInt()) {
					ConsoleCleaner.clear();
					System.out.println("Domanda "+(i+1)+": "+domanda);
					System.out.println("1) "+q.get(0)+"\t\t\t2)"+q.get(1));
					System.out.println("3) "+q.get(2)+"\t\t\t4)"+q.get(3));
					System.out.print("Inserisci la risposta: (1-2-3-4): ");
					in.next();
				}
				rispData = in.nextInt();
				ConsoleCleaner.clear();
			}while(rispData<1 || rispData>4);
			
	
			reportStrings[i] = buildReportString(i+1,domanda,q.get(rispData-1),rispGiusta);
			
			if(q.get(rispData-1).equals(rispGiusta)) {
				nRispGiuste++;
			}
			
		}
		ReportSP report = new ReportSP(nRispGiuste, reportStrings);
		return report;
	}

	private String buildReportString(int nDomanda,String domanda, String rispostaData, String rispostaGiusta) {
		String res = nDomanda + ") "+ domanda + " " + rispostaData + ". Risposta giusta: "+rispostaGiusta;
		return res;
	}

}
