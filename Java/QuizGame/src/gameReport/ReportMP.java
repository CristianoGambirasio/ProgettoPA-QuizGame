package gameReport;

import java.util.ArrayList;

//Report di una partita Multiplayer, si basa su un ArrayList di records
public class ReportMP extends Report{
	
	ArrayList<ReportRecord> records;
	
	public ReportMP(){
		this.records = new ArrayList<ReportRecord>();
	}
	
	public void addReport(int nRispGiuste, String giocatore,String[] reportStrings) {
		records.add(new ReportRecord(nRispGiuste, giocatore, reportStrings));
	}
	
	public void print() {
		for(int i=0;i<records.size();i++) {
			System.out.println(records.get(i).nomeGiocatore());
			for(int j=0;j<records.get(i).risposte().length;j++) {
				System.out.println(records.get(i).risposte()[j]);
			}
			System.out.println("Risultato: "+records.get(i).nGiuste()+"/"+records.get(i).risposte().length+"\n");
		}
	}

}
