# Documento dei Requisiti

## Introduzione

In questo documento vengono trattati in modo approfondito i requisiti del progetto,
ponendo particolare attenzione a:
- funzioni supportate
- dipendenze da interfacce software e di comunicazione
- limiti di progettazione
- casi d'uso

### Definizioni, acronimi e abbreviazioni

_peso_: numero di documenti in cui appare un termine.

## Descrizione generale

### Funzioni supportate

Il programma, al momento, supporta le seguenti funzioni:
- **download**
- **estrazione**
- **download ed estrazione**

### Dipendenze

- **download ed estrazione** dipende dall'implementazione di **download** e di **estrazione**.
## Requisiti specifici

### Requisiti Interfacce Esterne

#### Interfacce software

_The Guardian API_

#### Interfacce di comunicazione

_HTTPS_

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