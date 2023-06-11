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

## 1 - Descrizione generale

### 1.1 Funzioni supportate

Il programma, al momento, supporta le seguenti funzioni:
- **download**
- **estrazione**
- **download ed estrazione**

### 1.2 Dipendenze

- **download ed estrazione** dipende dall'implementazione di **download** e di **estrazione**.
## 2 - Requisiti specifici

### 2.1 Requisiti Interfacce Esterne

#### 2.1.1 Interfacce software

_The Guardian API_

#### 2.1.2 Interfacce di comunicazione

_HTTPS_

### 2.2 Requisiti funzionali

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

### 2.3 Limiti di progettazione

Il sistema deve essere predisposto ad aggiornamenti futuri.

### 2.4 Caratteristiche del software

#### 2.4.1 Aggiornabilità e manutenibilità

Il sistema è stato progettato in modo da rispettare le richieste di aggiornabilità e manutenibilità.
Grazie all'utilizzo di design pattern che permettono di rendere il codice più flessibile, il sistema è predisposto a supportare nuove sorgenti, 
nuove modalità di memorizzazione degli articoli e nuove strutture per memorizzare i termini estratti.

## 3 - Appendice

### 3.1 UseCases Diagram

![Use Cases Diagram](use_cases_diagram.svg)

#### 3.1.1 Descrizione

Descrizione

#### 3.1.2 Descrizione in linguaggio naturale strutturato

Descrizione in linguaggio naturale strutturato