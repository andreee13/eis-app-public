# Documento di Test

Versione: 1.0

## Indice:

1. [Introduzione](#introduzione)
    - [Scopo](#scopo)
    - [Obiettivi](#obiettivi)

2. [Strategia di Test](#strategia-di-test)
    - [Approccio di Test](#approccio-di-test)
    - [Risorse di Test](#risorse-di-test)
    - [Pianificazione di Test](#pianificazione-di-test)
    - [Criteri di Uscita di Test](#criteri-di-uscita-di-test)

3. [Ambiente di Test](#ambiente-di-test)
    - [Requisiti di Software](#requisiti-di-software)
    - [Dati di Test](#dati-di-test)

## 1. Introduzione<a id="introduzione"></a>

### 1.1 Scopo<a id="scopo"></a>

Il presente documento vuole innanzitutto fornire un quadro di riferimento per comprendere l'importanza e la motivazione
dell'attività di test nel contesto del progetto software. Questa sezione del documento di test vuole definire i limiti e
le aspettative generali dell'attività di test e fornire una visione d'insieme del suo contributo al processo di sviluppo
del software. Lo scopo include:

- Verificare che il software soddisfi i requisiti specificati dal committente del progetto.
- Assicurarsi che il software funzioni correttamente, senza difetti o errori, al fine di garantire la qualità e la
  stabilità del prodotto finale.
- Identificare difetti o problemi nel software per correggerli e migliorare la qualità del software.
- Assicurarsi che il software sia sicuro, affidabile e performante, in linea con le aspettative degli utenti finali.
- Fornire una base per valutare l'accettabilità e l'idoneità del software per il suo scopo previsto.

### 1.2 Obiettivi<a id="obiettivi"></a>

Gli obiettivi di test consentono di concentrarsi su aspetti specifici del software che devono essere esaminati e
valutati. Inoltre aiutano a stabilire le priorità, a pianificare le attività di test e a valutare il successo dei test
eseguiti.

Gli obiettivi di test includono:

- Verificare che il software soddisfi i requisiti funzionali specificati.
- Identificare difetti o errori nel software per migliorarne la qualità e la stabilità.
- Valutare le prestazioni del software in termini di tempi di risposta, capacità di gestire carichi di lavoro elevati e
  consumo di risorse.
- Verificare la compatibilità del software con diversi sistemi operativi.
- Confermare la corretta integrazione tra i moduli o i componenti del sistema.
- Verificare la sicurezza del software e la protezione da potenziali vulnerabilità.

## 2. Strategia di Test<a id="strategia-di-test"></a>

### 2.1 Approccio di Test<a id="approccio-di-test"></a>

L'approccio di test è basato sul modello a V, che prevede l'esecuzione di test di unità, integrazione e sistema, senza
la parte di accettazione del cliente. Questo perché il progetto è un progetto didattico e non è prevista la consegna ad
un cliente esterno, bensì la consegna al docente che risulta essere il committente e l'ideatore del progetto.
Gli stadi di test sono i seguenti:

- **Test dello sviluppo**, elaborati durante lo sviluppo con il pieno coinvolgimento del team di sviluppo.
- **Test della release**. Nonostante il team di test coincida con il team di sviluppo, questo stadio di test è separato
  dal
  precedente in quanto è un processo di convalida, non ideato solamente per la scoperta di difetti.

I **test dello sviluppo** sono suddivisi in:

- **Test di unità**, che verificano il corretto funzionamento di singole unità di codice, prevalentemente le più
  importanti
  o che racchiudono in sé snodi critici del programma. La strategia seguita per la scelta dei test case è quella dei
  test delle partizioni (o partizioni di equivalenza), che prevede di suddividere l'input in partizioni con
  caratteristiche comuni (da elaborare in modo analogo) e di selezionare un test case per ogni partizione.
- **Test di integrazione** (o delle componenti), che verificano il corretto funzionamento delle interfacce tra le varie
  unità di codice.

I **test della release** sono suddivisi in:

- **Test dei requisiti**: alcuni requisiti sono testati singolarmente per verificare il loro corretto funzionamento.
- **Test degli scenari**: tramite alcuni realistici scenari d'uso si testano numerosi requisiti all'interno di un unico
  scenario.

### 2.2 Risorse di Test<a id="risorse-di-test"></a>

La gestione delle risorse di test è essenziale per garantire che siano disponibili le risorse necessarie per eseguire
l'attività di test in modo efficace ed efficiente.

Lo strumento di test utilizzato è JUnit, che permette di eseguire test di unità di lavoro in Java. Spesso un' unità di
lavoro distinta è un singolo metodo.

Tramite le Assertions di JUnit è possibile dichiarare con certezza che il risultato di un test sia uguale a quello
atteso, verificando che il codice funzioni correttamente, con l'ausilio di dati/risorse di test fittizi ma realistici.
JUnit è stato scelto per la sua semplicità e per la sua integrazione con l'IDE IntelliJ IDEA, quest'ultimo utilizzato
per lo sviluppo del progetto.

### 2.3 Pianificazione di Test<a id="pianificazione-di-test"></a>

La pianificazione dei test fa si che l'attività di test sia eseguita in modo sistematico, ottimizzando l'utilizzo delle
risorse disponibili e soddisfacendo gli obiettivi e i requisiti di test definiti.
Aspetti chiave della pianificazione dei test sono stati:

- Identificazione degli obiettivi e dei requisiti di test: Definire chiaramente gli obiettivi di test e i requisiti che
  devono essere soddisfatti durante l'attività di test. Questo include stabilire i livelli di test richiesti e i tipi di
  test da eseguire.
- Definizione dei deliverable dei test: Identificare i documenti e le informazioni che devono essere prodotti come
  output del processo di test, come report dei risultati dei test e documentazione correlata. Con l'uso del plugin
  Surefire di Maven, è possibile generare un report dei risultati dei test in formato HTML.
- Stima e assegnazione delle risorse.
- Definizione delle metriche di test.

### 2.4 Criteri di Uscita di Test<a id="criteri-di-uscita-di-test"></a>

I criteri di uscita sono serviti come punto di riferimento per valutare se il processo di test è stato completato con
successo.
Una volta testate con successo le componenti più critiche del sistema, e individuati e corretti difetti di progettazione
o di implementazione che andavano a compromettere il funzionamento del sistema nella sua totalità di casi possibili, i
criteri sono stati considerati raggiunti.

## 3. Ambiente di Test<a id="ambiente-di-test"></a>

### 3.1 Requisiti di Software<a id="requisiti-di-software"></a>

**Requisiti minimi**

- _JDK 8_
- _Maven 3.3.1_

### 3.2 Dati di test<a id="dati-di-test"></a>

I dati di test utilizzati sono statici, nelle forme di input predefiniti o dati di riferimento.

La directory /src/test/resources contiene i file di test. 

In particolare, articles.json viene utilizzato per testare le funzionalità delle componenti legate all'elaborazione di dati JSON (come ad esempio la classe JsonRepositoryTest), mentre file.csv
viene utilizzato per testare le funzionalità delle componenti legate all'elaborazione di dati CSV (come ad esempio la
classe CsvRepositoryTest). 

Allo stesso modo, terms.txt viene utilizzato per testare le funzionalità delle componenti
legate all'elaborazione di dati testuali (come ad esempio la classe TermsExtractionRepositoryTest).




