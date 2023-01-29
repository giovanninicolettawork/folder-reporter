# Folder Reporter

Questo software consente di ottenere un report con informazioni relative a tutti i file contenuti in una determinata cartella. 
Nello specifico, dopo aver inserito il file jar nella cartella da analizzare, verrà generato un file *csv* che, per ogni file, riporta le seguenti informazioni:
- Nome
- Cartella in cui si trova il file
- Dimensione
- Estensione
- Data di creazione
- Data di Ultima modifica
- Status (Processato correttamente o Errore)

# Specifiche

Il codice è stato scritto in *Java (11)* utilizzando *Maven*. Nel file *pom.xml* è definita la modalità di build.

## Installazione e utilizzo
È possibile generare il file jar tramite il comando *Maven Install*. Verrà generato, nella cartella target, il file *folderReporter.jar* che potrà essere eseguito da terminale ovunque (con l'unico requisito che sia installato Java).
Per eseguire il jar lanciare sul terminale il comando

    java -jar folderReporter.jar
Il file *report.csv* verrà generato nella stessa cartella in cui è stato collocato il jar per l'analisi.
## Stato del progetto
Si tratta di un software implementato al solo scopo di acquisire confidenza con *Java*