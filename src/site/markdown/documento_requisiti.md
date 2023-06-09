# Documento dei Requisiti

## Introduzione

In questo documento vengono trattati i requisiti del progetto. 

### Definizioni, acronimi e abbreviazioni

_peso_: numero di documenti in cui appare un termine.

## Indice

- [Descrizione generale](#Descrizione-generale)
  - [Funzioni supportate](#Funzioni-supportate)
  - [Dependencies](#Dependencies)
- [Requisiti specifici](#Requisiti-specifici)
  - [Requisiti Interfacce Esterne](#Requisiti-Interfacce-Esterne)
    - [Interfacce software](#Interfacce-software)
    - [Interfacce di comunicazione](#Interfacce-di-comunicazione)
  - [Functional Requirements](#Functional-Requirements)
  - [Caratteristiche del software](#Caratteristiche-del-software)
    - [Aggiornabilità e mantenibilità](#Aggiornabilità-e-mantenibilità)
- [UseCases Diagram](#UseCases-Diagram)
  - [Descrizione](#Descrizione)
  - [Descrizione in linguaggio naturale strutturato](#Descrizione-in-linguaggio-naturale-strutturato)

## Descrizione generale

### Funzioni supportate

Il programma, al momento, supporta le seguenti funzioni:
- **download**
- **estrazione**
- **download ed estrazione**

### Dependencies

- **download ed estrazione** dipende dall'implementazione di **download** e di **estrazione**.
## Requisiti specifici

### Requisiti Interfacce Esterne

#### Interfacce software

_The Guardian API_

#### Interfacce di comunicazione

_HTTPS_

### Requisiti funzionali

1. Il sistema deve essere predisposto a supportare nuove sorgenti.
2. Dopo la fase di download, deve essere effettuata la persistenza su file
   degli articoli usando lo stesso formato per tutti gli articoli di tutte le
   sorgenti.
3. Il sistema deve poter supportare nuove modalità di memorizzazione ed
   accesso agli articoli.
4. Per estrarre i termini ed il loro peso,
   è necessario partire dai file in cui gli articoli sono memorizzati.
5. Il sistema deve poter supportare nuove strutture per memorizzare ed
   avere accesso ai termini più importanti
6. L’utente deve poter specificare se eseguire solo il download, solo
   l’estrazione dei termini a partire dai file in cui sono stati memorizzati gli
   articoli, o entrambe le azioni in sequenza.

### Limiti di progettazione

Il limite di progettazione più importante è che il sistema deve essere semplice da aggiornare.

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

### Descrizione in linguaggio naturale strutturato

Descrizione in linguaggio naturale strutturato