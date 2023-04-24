
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Stream;

import consoleCleaner.ConsoleCleaner;
import gameReport.Report;
import partite.MultiPlayer;
import partite.Partita;
import partite.SinglePlayer;

public class QuizGame {
	public static String IDE_PATH = "..\\..\\Quiz\\";
	public static String TERMINAL_PATH = "..\\..\\..\\Quiz\\";

	public static void main(String[] args){
		ConsoleCleaner.clear();
		int scelta=0;
		String quiz = null;
		
		Scanner in = new Scanner(System.in);
		
		Partita partita = null;
		Report risultato;
		
		String quizDirPath;
		if(Paths.get("").toAbsolutePath().getFileName().toString().equals("src")){
			quizDirPath = TERMINAL_PATH;
		}else {
			quizDirPath = IDE_PATH;
		}
		
		do {			
			do {
				System.out.println("Seleziona una modalita':"
						+ "\n1)Partita Singleplayer"
						+ "\n2)Partita Multiplayer"
						+ "\n0)Esci dal gioco");
				System.out.print("Inserisci una risposta valida (1-2-0)");
				while(!in.hasNextInt()){
					ConsoleCleaner.clear();
					System.out.println("Seleziona una modalita':"
							+ "\n1)Partita Singleplayer"
							+ "\n2)Partita Multiplayer"
							+ "\n0)Esci dal gioco");
					System.out.print("Inserisci una risposta valida (1-2-0)");
					in.next();
				}
				scelta = in.nextInt();
				ConsoleCleaner.clear();
			}while(scelta<0 || scelta>2);
			
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
			
			if(scelta!=0) {
				do {
					System.out.println("Inserisci il nome del quiz che si vuole fare:"
							+ "\nQuiz disponibili: ");
					
					quizList.forEach(q ->{
						q=q.substring(quizDirPath.length());
						System.out.println(q);
					});
					String selectedQuiz = in.nextLine();
					quiz = quizDirPath + selectedQuiz;
					ConsoleCleaner.clear();
				}while(quiz == null || !quizList.contains(quiz));
			}
			
			if(scelta==1) {
				partita = new SinglePlayer(quiz);
			}
			else if(scelta == 2) {
				int players;
				do {
					System.out.print("Inserisci il numero di giocatori (max 4): ");
					while(!in.hasNextInt()) {
						ConsoleCleaner.clear();
						System.out.println("Inserisci il numero di giocatori (max 4): ");
						in.next();
					}
					players = in.nextInt();
					ConsoleCleaner.clear();
				}while(players<0 || players>4);
				partita = new MultiPlayer(quiz, 
						players);
			}
			
			if(scelta != 0) {
				risultato = partita.start(in);
				risultato.print();
				System.out.println("Premi invio per concludere la partita");
				in.nextLine();
				in.nextLine();
				ConsoleCleaner.clear();
			}
			
			
		}while(scelta!=0);
		in.close();
	}
}
