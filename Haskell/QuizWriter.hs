import System.IO
import System.Directory
import Text.Read (readMaybe)

main = do
    putStrLn "Inserisci nome Quiz"
    quizName <- getLine
    putStrLn ("Nome inserito: "++quizName)
    putStrLn "Inserisci il numero di domande"
    nDomande <- getLine
    case readMaybe nDomande :: Maybe Int of
        Just number -> do
            createDirectoryIfMissing True ("../Quiz/" ++ quizName)
            writeFile ("../Quiz/"++quizName++"/config.txt") (show number)
            creaDomande number 1 ("../Quiz/"++quizName)
        Nothing -> putStrLn "Inserisci un numero"

creaDomande n i path = if i == n 
                    then scriviDomanda i path
                    else do
                        scriviDomanda i path
                        creaDomande n (i+1) path

scriviDomanda i path = do
    putStrLn ("[DOMANDA "++(show i)++"]")
    putStrLn "Scrivi la domanda"
    domanda <- getLine
    putStrLn "Scrivi la risposta corretta"
    r1 <- getLine
    putStrLn "Scrivi una risposta errata"
    r2 <- getLine
    putStrLn "Scrivi una risposta errata"
    r3 <- getLine
    putStrLn "Scrivi una risposta errata"
    r4 <- getLine
    writeFile (path++"/"++ show i++".txt") (domanda++"\n"++r1++"\n"++r2++"\n"++r3++"\n"++r4)

