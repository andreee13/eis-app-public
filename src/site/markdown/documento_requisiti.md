# Documento dei Requisiti

---

Versione: 1.0

Data: 11 giugno 2023

Standard: IEEE 830-1998

---

## Introduzione

In questo documento vengono trattati in modo approfondito i requisiti del progetto,
ponendo particolare attenzione a:

- funzioni supportate
- dipendenze da interfacce software e di comunicazione
- limiti di progettazione
- casi d'uso

### Scopo

Il sistema è stato creato per effettuare operazioni su articoli di giornale.
L'utente finale è in grado di scaricare articoli, estrarre i termini più frequenti e visualizzare i risultati o
effettuare entrambe le operazioni in sequenza.

### Acronimi

- _API_: Application Programming Interface.
- _JSON_: JavaScript Object Notation.

### Definizioni

- _peso_: numero di documenti in cui appare un termine.
- Il programma dispone i termini estratti nel file di testo in ordine alfabetico come criterio aggiuntivo.
- _formato del file JSON di download_:

```json lines
[
  {
    "id": 0,
    "title": "Title",
    "body": "Body",
    "date": "Jan 1, 2000 12:00:00 AM",
    "url": "https://www.example.com",
    "source": "SOURCE"
  },
  ...
]
```

- _formato del file di testo di estrazione_:

```text
termine: peso
```

---

## Descrizione generale

In questa sezione si affronta in modo estensivo la descrizione di:

- Funzioni supportate
- Caratteristiche dell'utente
- Compatibilità

### Prospettiva del prodotto

Questo è un sistema creato per un progetto universitario, quindi non è destinato ad un uso commerciale.

### Funzioni supportate

Il programma, al momento, supporta le seguenti funzioni:

- **download** degli articoli
- **estrazione** dei termini più ricorrenti in un file di testo
- **download ed estrazione** in sequenza

Sono possibili le seguenti specificazioni:

- una fonte
- un filtro temporale / una query di ricerca
- un numero massimo di articoli da scaricare
- un numero massimo di termini da estrarre
- nome file di output articoli
- nome del file di output termini
- estrarre lemmi o solo parole senza punteggiatura

#### Download

Il programma scarica articoli da testate giornalistiche tramite le fonti supportate, salvandoli in un formato comune.

##### Richiesta

Fonte e opzioni supportate:

- The Guardian API
    - query di ricerca
    - filtro temporale
    - numero massimo di articoli
    - estrazione lemmi o parole senza punteggiatura
- The New York Times CSV
    - query di ricerca
    - filtro temporale
    - numero massimo di articoli
    - estrazione lemmi o parole senza punteggiatura
- File provenienti da un download precedente
    - query di ricerca
    - filtro temporale
    - numero massimo di articoli
    - estrazione lemmi o parole senza punteggiatura

##### Risposta

Esito, _file JSON_ contenente la lista degli articoli.

#### Estrazione

Il programma estrae in un file di testo i termini più ricorrenti da un articolo modellizzato, precedentemente scaricato.

##### Richiesta

File da cui estrarre i termini, opzioni supportate.

##### Risposta

Esito, _file txt_ contenente i termini più ricorrenti.

#### Download ed estrazione

Il programma effettua in sequenza il download degli articoli e l'estrazione dei termini più ricorrenti.

##### Richiesta

Fonte e opzioni supportate dal download.

##### Risposta

Esito, file generati:

- _file JSON_ contenente la lista degli articoli
- _file di testo_ contenente i termini più ricorrenti

### Caratteristiche dell'utente

Esiste un solo tipo di utente, ma il sistema può essere utilizzato in modo automatico da altri sistemi/programmi. Non
sono stati implementati privilegi.

### Compatibilità

Il sistema è supportato da tutti i sistemi operativi che supportano almeno _Java 8_.

### Assunzioni

Si assume che l'utente:

- abbia una connessione ad internet
- sappia interfacciarsi con un terminale

---

## Requisiti specifici

In questa sezione verranno trattati i requisiti specifici del sistema. In particolare:

- Interfacce esterne
- Requisiti funzionali
- Limiti di progettazione
- Caratteristiche del software

### Requisiti Interfacce Esterne

In questa sezione verranno trattate le interfacce esterne con cui il sistema interagisce, in particolar modo:

- Interfacce software
- Interfacce di comunicazione

#### Interfaccia del sistema

L'interfaccia del sistema è il terminale del sistema operativo su cui viene eseguito il programma.
Si può visualizzare:

- lo stato di caricamento dell'applicazione
- cosa sta eseguendo l'applicazione in real time
- eventuali errori e cosa li ha causati, con possibile risoluzione

#### Interfacce hardware

Il sistema ha bisogno di una connessione ad internet per funzionare, quindi fa affidamento alla _scheda di rete_ del
computer,
oltre che al _file system_.

#### Interfacce software

Il sistema si interfaccia con _The Guardian API_ tramite una **chiave** che l'utente deve possedere quando utilizza la
funzione **download** del programma.

#### Interfacce di comunicazione

Il sistema utilizza il protocollo _HTTPS_.

### Requisiti funzionali

1. Il sistema deve poter supportare nuove sorgenti.
2. Dopo la fase di download, deve essere effettuata la persistenza su file
   degli articoli usando lo stesso formato per tutti gli articoli di tutte le
   sorgenti.
3. Il sistema deve poter supportare nuove modalità di memorizzazione ed
   accesso agli articoli.
4. Per estrarre i termini ed il loro _peso_,
   è necessario partire dai file in cui gli articoli sono memorizzati.
5. Il sistema deve poter supportare nuove strutture per memorizzare ed
   avere accesso ai termini più importanti
6. L’utente deve poter specificare se eseguire solo il download, solo
   l’estrazione dei termini a partire dai file in cui sono stati memorizzati gli
   articoli, o entrambe le azioni in sequenza.

### Limiti di progettazione

Il sistema deve essere predisposto ad aggiornamenti futuri.

### Caratteristiche del software

In questa sezione verranno trattate le caratteristiche del software.

#### Aggiornabilità e manutenibilità

Il sistema è stato progettato in modo da rispettare le richieste di aggiornabilità e manutenibilità.
Grazie all'utilizzo di design pattern che permettono di rendere il codice più flessibile, il sistema è predisposto a
supportare nuove sorgenti,
nuove modalità di memorizzazione degli articoli e nuove strutture per memorizzare i termini estratti.

---

## Appendice

### UseCases Diagram

![Use Cases Diagram](use_cases_diagram.svg)

#### Descrizione in linguaggio naturale strutturato

| Attori              | Descrizione                                                                                                                                                                                                                         |
|---------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Utente              | L'utente finale è in grado di scaricare articoli [-d], estrarre i termini più frequenti e visualizzare i risultati [-e] o effettuare entrambe le operazioni in sequenza, a seconda del comando impartito al momento dell'esecuzione |
| Download            | In base alla sorgente specificata (The Guardian API, The New York Times CSV o file provenienti da un download precedente) scarica i dati, li converte in articoli e li scrive in un _file JSON_                                     |
| Estrazione          | A partire da un articolo modelizzato precedentemente scaricato, estrae i termini più ricorrenti e li salva in un file di testo _file txt_                                                                                           |
| Download&Estrazione | Invoca sequenzialmente Download ed Extract, producendo i rispettivi output (_file JSON_ e _file txt_)                                                                                                                               |
| The Guardian API    | Sorgente dalla quale vengono scaricati i dati, che saranno poi convertiti in articoli, alla quale si accede tramite una API Key                                                                                                     |
| File System         | Sorgente in cui vengono salvati articoli scaricati, e produce i file di output dei diversi use case: _file JSON_ con la lista di articoli e _file txt_ con i termini più ricorrenti                                                 |

##### Use Case Download

|          | Descrizione                                                                                                                                                                   |
|----------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Attori   | Utente, The Guardian API, File System                                                                                                                                         |
| Dati     | Vengono scaricati i dati dalla sorgente (The Guardian API o File System, file  CSV) e convertiti in articoli standardizzati scritti in un _file JSON_ restitutito come output |
| Stimolo  | Al momento dell'esecuzione del programma deve essere dato il comando di eseguire solo il download [-d]                                                                        |
| Risposta | La lista degli articoli stanrdardizzati è scritta in un _file JSON_ restituito dal File System                                                                                |

##### Use Case Estrazione

|          | Descrizione                                                                                                                                                                                  |
|----------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Attori   | Utente, The Guardian API, File System                                                                                                                                                        |
| Dati     | Si accede agli articoli standardizzati e ne vengono estratti i termini più frequenti, tramite la libreria StanfordCoreNLP, salvati poi in ordine di frequenza in un file di testo _file txt_ |
| Stimolo  | Al momento dell'esecuzione del programma deve essere dato il comando di eseguire solo l'estrazione [-e]                                                                                      |
| Risposta | I termini sono salvati in ordine di frequenza in un file di testo _file txt_ restituito come output                                                                                          |

##### Use Case Download&Estrazione

|          | Descrizione                                                                                                                                                                                                                                                  |
|----------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Attori   | Utente, The Guardian API, File System                                                                                                                                                                                                                        |
| Dati     | Vengono invocati Download ed Estrazione che vengono eseguiti in sequenza, scaricando quindi prima i dati dalla fonte e convertiti in articoli, dai quali successivamente vengono estratti i termini più frequenti e riportati in un file di testo _file txt_ |
| Stimolo  | Al momento dell'esecuzione del programma di default viene eseguito questo comando, può essere tuttavia esplicitato [-d -e]                                                                                                                                   |
| Risposta | La lista degli articoli stanrdardizzati è scritta in un _file JSON_ restituito dal File System e i termini sono salvati in ordine di frequenza in un file di testo _file txt_ restituito come output                                                         |
