# Manuale

## Panoramica

Questo è un tool che permette di effettuare operazioni su articoli di giornale.

Il programma consente di:

- **Scaricare** uno o più articoli da fonti diverse. È possibile specificare la fonte di provenienza dei dati, il numero di
  articoli da scaricare o un range di date.
- **Estrarre** da uno o più articoli le parole più ricorrenti, salvandole in un file di testo. È possibile specificare il
  nome del file di output e il numero di parole da estrarre.
- **Scaricare** uno o più articoli ed estrarre direttamente le parole più ricorrenti in un file di testo. È possibile
  specificare la fonte di provenienza dei dati, il numero di articoli da scaricare o un range di date, il nome del file
  di output e il numero di parole da estrarre.

## Installazione

### Jar

    mvn package

### Docker

    docker build -t <image_name> .

## Esecuzione

### Jar

    java -jar <jarfile> [options]

        -ca,--count-articles <integer>   Number of articles
        -ct,--count-terms <integer>      Number of terms
        -d,--download                    Download only
        -e,--extract                     Extract only
        -f,--from <date>                 From date
        -h,--help                        Print this message
        -k,--api-key <string>            API key
        -oa,--output-articles <file>     Output articles file name
        -ot,--output-terms <file>        Output terms file name
        -q,--query <string>              Search query
        -t,--to <date>                   To date

### Dockerfile

    docker run -it --rm <image_name> [options]

### Docker Compose

    docker-compose run --rm <service_name> [options]

## Funzioni riutilizzate da librerie esistenti

- [Apache Commons CLI](https://commons.apache.org/proper/commons-cli/): libreria Java per la gestione di opzioni da riga
  di comando.
    - [CommandLine](https://commons.apache.org/proper/commons-cli/javadocs/api-release/org/apache/commons/cli/CommandLine.html):
      classe che rappresenta una linea di comando.
    - [CommandLineParser](https://commons.apache.org/proper/commons-cli/javadocs/api-release/org/apache/commons/cli/CommandLineParser.html):
      interfaccia che definisce il metodo `parse` per il parsing di una linea di comando.
    - [DefaultParser](https://commons.apache.org/proper/commons-cli/javadocs/api-release/org/apache/commons/cli/DefaultParser.html):
      classe che implementa l'interfaccia `CommandLineParser` e permette di effettuare il parsing di una linea di
      comando.
    - [Options](https://commons.apache.org/proper/commons-cli/javadocs/api-release/org/apache/commons/cli/Options.html):
      classe che rappresenta un insieme di opzioni.
    - [Option](https://commons.apache.org/proper/commons-cli/javadocs/api-release/org/apache/commons/cli/Option.html):
      classe che rappresenta un'opzione.
    - [HelpFormatter](https://commons.apache.org/proper/commons-cli/javadocs/api-release/org/apache/commons/cli/HelpFormatter.html):
      classe che permette di stampare un messaggio di aiuto.
    - [ParseException](https://commons.apache.org/proper/commons-cli/javadocs/api-release/org/apache/commons/cli/ParseException.html):
      classe che rappresenta un'eccezione generata durante il parsing di una linea di comando.
- [OkHttp](https://square.github.io/okhttp/): libreria Java per la creazione di client HTTP.
    - [OkHttpClient](https://square.github.io/okhttp/4.x/okhttp/okhttp3/-ok-http-client/): classe che permette di
      effettuare richieste HTTP.
    - [HttpUrl](https://square.github.io/okhttp/4.x/okhttp/okhttp3/-http-url/): classe che rappresenta un URL HTTP.
    - [Request](https://square.github.io/okhttp/4.x/okhttp/okhttp3/-request/): classe che rappresenta una richiesta
      HTTP.
    - [Response](https://square.github.io/okhttp/4.x/okhttp/okhttp3/-response/): classe che rappresenta una risposta
      HTTP.
- [Apache Commons CSV](https://commons.apache.org/proper/commons-csv/): libreria Java per la gestione di file CSV.
    - [CSVParser](https://commons.apache.org/proper/commons-csv/apidocs/org/apache/commons/csv/CSVParser.html): classe
      che permette di effettuare il parsing di un file CSV.
    - [CSVFormat](https://commons.apache.org/proper/commons-csv/apidocs/org/apache/commons/csv/CSVFormat.html): classe
      che rappresenta il formato di un file CSV.
    - [CSVRecord](https://commons.apache.org/proper/commons-csv/apidocs/org/apache/commons/csv/CSVRecord.html): classe
      che rappresenta un record di un file CSV.
- [GSON](https://www.javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/module-summary.html)
    - [JsonParser](https://www.javadoc.io/doc/com.google.code.gson/gson/latest/com.google.gson/com.google.gson.JsonParser.html):
      classe che permette di effettuare il parsing di un file JSON.
  