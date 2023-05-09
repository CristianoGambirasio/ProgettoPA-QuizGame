
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import gameReport.Report;
import partite.MultiPlayer;
import partite.Partita;
import partite.SinglePlayer;
import utils.ConsoleCleaner;

public class QuizGame {
	//Due diversi path in base a se l'esecuzione parte da ide o da terminale
	public static String IDE_PATH = "..\\..\\Quiz\\";
	public static String TERMINAL_PATH = "..\\..\\..\\Quiz\\";

	public static void main(String[] args){
		ConsoleCleaner.clear();//Pulisce la console (solo su terminale)
		int scelta=0; //Comanda il flusso del software
		String quiz = null; //path del quiz selezionato
		
		Scanner in = new Scanner(System.in);
		
		Partita partita = null; //Partita da avviare
		Report risultato; //Report della partita 
		
		String quizDirPath;//Determinazione di quale path di base usare
		if(Paths.get("").toAbsolutePath().getFileName().toString().equals("src")){
			quizDirPath = TERMINAL_PATH;
		}else {
			quizDirPath = IDE_PATH;
		}
		
		do {
			//Inserimento modalità
			scelta = safeInput("Seleziona una modalita':"
					+ "\n1)Partita Singleplayer"
					+ "\n2)Partita Multiplayer"
					+ "\n0)Esci dal gioco"
					+ "\nInserisci una risposta valida (1-2-0): ",
					0, 2, in);
			
			//Calcolo della lista di quiz
			ArrayList<String> quizList = new ArrayList<>();
			
			try (Stream<Path> paths = Files.walk(Paths.get(quizDirPath))) {
			    paths
			        .filter(Files::isDirectory)
			        .map(Object::toString)
			        .forEach(quizList::add);
			} catch (IOException e) {
				System.out.println(e);
			}
			if(!quizList.isEmpty()) {
				quizList.remove(0);
			}
			in.nextLine();
			
			//Se scelta = 0 esco dall'esecuzione
			if(scelta == 0) {
				break;
			}
			
			//Inserimento del quiz da fare
			quiz = safeInput("Inserisci il nome del quiz che si vuole fare:"
					+ "\nQuiz disponibili: ", quizList, quizDirPath, in);
			
			//Esecuzione della modalità giusta
			if(scelta==1) {
				partita = new SinglePlayer(quiz);
			}
			else if(scelta == 2) {
				int players=safeInput("Inserisci il numero di giocatori (max 4): "
						, 1, 4, in);
				partita = new MultiPlayer(quiz, 
						players);
			}
			
			risultato = partita.start(in);
			
			//Stampa del report
			risultato.print();
			
			System.out.println("Premi invio per concludere la partita");
			in.nextLine();
			in.nextLine();
			ConsoleCleaner.clear();
			
			
		}while(scelta!=0);
		in.close();
	}

	
	//Definizione dei metodi per il safe input
	private static String safeInput(String out, ArrayList<String> choices, String prefix, Scanner in) {
		String res;
		do {
			System.out.println(out);
			
			choices.forEach(e ->{
				e=e.substring(prefix.length());
				System.out.println(e);
			});
			
			String choice = in.nextLine();
			res = prefix + choice;
			ConsoleCleaner.clear();
		}while(res==null || !choices.contains(res));
		return res;
	}

	private static int safeInput(String out, int min, int max, Scanner in) {
		int res;
		do {
			System.out.println(out);
			while(!in.hasNextInt()) {
				ConsoleCleaner.clear();
				System.out.println(out);
				in.next();
			}
			res = in.nextInt();
			ConsoleCleaner.clear();
		}while(res<min || res>max);
		
		return res;
	}
	
	
}
