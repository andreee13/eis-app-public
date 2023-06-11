# Documento dei Requisiti

## Introduzione

In questo documento vengono trattati in modo approfondito i requisiti del progetto,
ponendo particolare attenzione a:
- funzioni supportate
- dipendenze da interfacce software e di comunicazione
- limiti di progettazione
- casi d'uso

### Scopo

Il sistema è stato creato per effettuare operazioni su articoli di giornale.
L'utente finale è in grado di scaricare articoli, estrarre i termini più frequenti e visualizzare i risultati o effettuare entrambe le operazioni in sequenza. 

### Definizioni, acronimi e abbreviazioni

1. _peso_: numero di documenti in cui appare un termine.

2. _formato del file JSON di download_: 
```
[
  {
    "id": "int",
    "title": "string",
    "body": "string",
    "date": "date",
    "url": "string",
    "source": "string"
  },
  ...
]
```

3. _formato del file .txt di estrazione_:
```
termine: peso
```
4. _API_: Application Programming Interface.
5. Il programma dispone i termini estratti nel file .txt in ordine alfabetico come criterio aggiuntivo.

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

In ogni funzione è possibile specificare:
- una fonte
- un filtro temporale / una query di ricerca
- un numero massimo di articoli da scaricare
- un numero massimo di termini da estrarre

#### Download

Il programma scarica articoli da testate giornalistiche tramite le fonti supportate, salvandolo in un formato comune.

##### Richiesta

Fonte e opzioni supportate:
- The Guardian API
  - query di ricerca
  - filtro temporale
  - numero massimo di articoli
- The New York Times CSV
  - query di ricerca
  - filtro temporale
  - numero massimo di articoli
- File provenienti da un download precedente
  - query di ricerca
  - filtro temporale
  - numero massimo di articoli
##### Risposta

Esito, _file JSON_ contenente la lista degli articoli.

#### Estrazione

Il programma estrae i termini più ricorrenti da un articolo modellizzato, precedentemente scaricato.

##### Richiesta

File da cui estrarre i termini, opzioni supportate:

##### Risposta

Esito, _file txt_ contenente i termini più ricorrenti.

#### Download ed estrazione

Il programma effettua in sequenza il download degli articoli e l'estrazione dei termini più ricorrenti.

##### Richiesta

Fonte e opzioni supportate dal download.

##### Risposta

Esito, file generati:
- _file JSON_ contenente la lista degli articoli
- _file .txt_ contenente i termini più ricorrenti

### Caratteristiche dell'utente

Esiste un solo tipo di utente, ma il sistema può essere utilizzato in modo automatico da altri sistemi/programmi. Non sono stati implementati privilegi.

### Compatibilità

Il sistema è supportato da tutti i sistemi operativi che supportano almeno Java 8.

### Assunzioni

Si assume che l'utente:
- abbia una connessione ad internet
- sappia interfacciarsi con un terminale

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

Il sistema ha bisogno di una connessione ad internet per funzionare, quindi fa affidamento alla _scheda di rete_ del computer,
oltre che al _file system_.

#### Interfacce software

Il sistema si interfaccia con _The Guardian API_ tramite una **chiave** che l'utente deve possedere quando utilizza la funzione **download** del programma.

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
Grazie all'utilizzo di design pattern che permettono di rendere il codice più flessibile, il sistema è predisposto a supportare nuove sorgenti, 
nuove modalità di memorizzazione degli articoli e nuove strutture per memorizzare i termini estratti.

## Appendice

### UseCases Diagram

![Use Cases Diagram](use_cases_diagram.svg)

#### Descrizione

Descrizione

#### Descrizione in linguaggio naturale strutturato

Descrizione in linguaggio naturale strutturato