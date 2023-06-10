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

## Indice

1. [Descrizione generale](#Descrizione-generale)
   - [Funzioni supportate](#Funzioni-supportate)
   - [Dependencies](#Dependencies)
2. [Requisiti specifici](#Requisiti-specifici)
   - [Requisiti Interfacce Esterne](#Requisiti-Interfacce-Esterne)
     - [Interfacce software](#Interfacce-software)
     - [Interfacce di comunicazione](#Interfacce-di-comunicazione)
   - [Requisiti Funzionali](#Requisiti-Funzionali)
   - [Limiti di progettazione](#Limiti-di-progettazione)
   - [Caratteristiche del software](#Caratteristiche-del-software)
     - [Aggiornabilità e manutenibilità](#AeM)
3.  [Appendice](#Appendice)
    - [UseCases Diagram](#UseCases-Diagram)
      - [Descrizione](#Descrizione)
      - [Descrizione in linguaggio naturale strutturato](#Descrizione-in-linguaggio-naturale-strutturato)

## 1. Descrizione generale<a id="Descrizione-generale"></a>

### 1.1 Funzioni supportate<a id="Funzioni-supportate"></a>

Il programma, al momento, supporta le seguenti funzioni:
- **download**
- **estrazione**
- **download ed estrazione**

### 1.2 Dependencies<a id="Dependencies"></a>

- **download ed estrazione** dipende dall'implementazione di **download** e di **estrazione**.
## 2. Requisiti specifici<a id="Requisiti-specifici"></a>

### 2.1 Requisiti Interfacce Esterne<a id="Requisiti-Interfacce-Esterne"></a>

#### 2.1.1 Interfacce software<a id="Interfacce-software"></a>

_The Guardian API_

#### 2.1.2 Interfacce di comunicazione<a id="Interfacce-di-comunicazione"></a>

_HTTPS_

### 2.2 Requisiti funzionali<a id="Requisiti-Funzionali"></a>

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

### 2.3 Limiti di progettazione<a id="Limiti-di-progettazione"></a>

Il sistema deve essere predisposto ad aggiornamenti futuri.

### 2.4 Caratteristiche del software<a id="Caratteristiche-del-software"></a>

#### 2.4.1 Aggiornabilità e manutenibilità<a id="AeM"></a>

Il sistema è stato progettato in modo da rispettare le richieste di aggiornabilità e manutenibilità.
Grazie all'utilizzo di design pattern che permettono di rendere il codice più flessibile, il sistema è predisposto a supportare nuove sorgenti, 
nuove modalità di memorizzazione degli articoli e nuove strutture per memorizzare i termini estratti.

## 3. Appendice<a id="Appendice"></a>

### 3.1 UseCases Diagram<a id="UseCases-Diagram"></a>

![Use Cases Diagram](use_cases_diagram.svg)

#### 3.1.1 Descrizione<a id="Descrizione"></a>

Descrizione

#### 3.1.2 Descrizione in linguaggio naturale strutturato<a id="Descrizione-in-linguaggio-naturale-strutturato"></a>

Descrizione in linguaggio naturale strutturato