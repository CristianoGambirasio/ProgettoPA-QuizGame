package gameReport;

public class ReportSP extends Report{

	ReportRecord playerRecord;
	public ReportSP(int nRispGiuste, String[] reportStrings) {
		playerRecord = new ReportRecord(nRispGiuste,null,reportStrings);
	}

	@Override
	public void print() {
		System.out.println("Risposte date: ");
		for(int i=0;i<playerRecord.risposte().length;i++) {
			System.out.println(playerRecord.risposte()[i]);
		}
		System.out.println("Risultato: "+playerRecord.nGiuste()+"/"+playerRecord.risposte().length);
	}
	
}
