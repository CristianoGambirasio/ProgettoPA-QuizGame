package gameReport;

public record ReportRecord(int nGiuste,String nomeGiocatore, String... risposte){
	public void print(){
		System.out.println("Risposte date: ");
		for(int i=0;i<risposte.length;i++) {
			System.out.println(risposte[i]);
		}
		System.out.println("Risultato: "+nGiuste+"/"+risposte.length);
	}
}
