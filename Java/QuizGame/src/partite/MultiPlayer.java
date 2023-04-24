package partite;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import consoleCleaner.ConsoleCleaner;
import gameReport.Report;
import gameReport.ReportMP;
import letturaFile.ConfigReader;
import letturaFile.QuestionReader;

public class MultiPlayer extends Partita {
	int nPlayers;
	
	public MultiPlayer(String path, int nPlayers) {
		this.roundPath = path;
		ConfigReader cr = new ConfigReader(path + "\\config.txt");
		this.nDomande = cr.getNDomande();
		this.nPlayers = nPlayers;
	}

	@Override
	public Report start(Scanner in) {
		QuestionReader qr = new QuestionReader(roundPath);
		
		ArrayList<String[]> reportStrings = new ArrayList<>(); 
		String rispGiusta;
		String domanda;
		int[] nRispGiuste = new int[nPlayers];
		for(int i=0;i<nPlayers;i++) {
			reportStrings.add(new String[nDomande]);
			nRispGiuste[i] = 0;
		}
		for(int numDomanda = 0; numDomanda<nDomande;numDomanda++) {
			ArrayList<String> q = qr.getDomanda(numDomanda+1);
			rispGiusta = q.get(1);
			domanda = q.get(0);
			
			q.remove(0);
			Collections.shuffle(q);
			
			for(int numPlayer=0;numPlayer<nPlayers;numPlayer++) {
				int rispData;
				do {
					System.out.println("Domanda "+(numDomanda+1)+": "+domanda);
					System.out.println("1) "+q.get(0)+"\t\t\t2)"+q.get(1));
					System.out.println("3) "+q.get(2)+"\t\t\t4)"+q.get(3));
					System.out.print("[PLAYER "+(numPlayer+1)+"]Inserisci la risposta: (1-2-3-4): ");
					while (!in.hasNextInt()) {
						ConsoleCleaner.clear();
						System.out.println("Domanda "+(numDomanda+1)+": "+domanda);
						System.out.println("1) "+q.get(0)+"\t\t\t2)"+q.get(1));
						System.out.println("3) "+q.get(2)+"\t\t\t4)"+q.get(3));
						System.out.print("[PLAYER "+(numPlayer+1)+"]Inserisci la risposta: (1-2-3-4): ");
						in.next();
						
					}
					rispData = in.nextInt();
					ConsoleCleaner.clear();
				}while(rispData<1 || rispData>4);
				
				reportStrings.get(numPlayer)[numDomanda] = buildReportString(numDomanda+1,domanda,q.get(rispData-1),rispGiusta);
				
				if(q.get(rispData-1).equals(rispGiusta)) {
					nRispGiuste[numPlayer]++;
				}
			}
		}
		ReportMP report = new ReportMP();
		for(int i=0;i<nPlayers;i++) {
			report.addReport(nRispGiuste[i], "[PLAYER "+(i+1)+"]" , reportStrings.get(i));
		}
		return report;
	}

	private String buildReportString(int nDomanda, String domanda, String rispostaData, String rispostaGiusta) {
		String res = nDomanda + ") "+ domanda + " " + rispostaData + ". Risposta giusta: "+rispostaGiusta;
		return res;
	}
	
	
}
