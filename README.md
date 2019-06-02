# Word Frequency Analyzer
Word Frequency Analyzer is a web application that analyzes the frequency of words in a .txt file. 
The back end is a RESTful API service that takes in HTTP requests, analyze the .txt (if it is a POST
request) and returns the status and results in JSON. The user can use the front end to upload their
files and make requests to the back end. The front end uses React (template from [Dunebook](https://github.com/Dunebook/routing-react-apps/)),
while the back end is written in Java with the Spring MVC framework.

## Features

  - Separation of concerns (React front end and RESTful API from Spring MVC)
  - Supports cross origin to allow the front end to interact with the back end server
  - Uploads file and immediately returns the top 10 most frequent words
  - Categorize different inflections of the same word together
  - Uses a variation [Porter stemmer algoritm](https://snowballstem.org/algorithms/english/stemmer.html) to stem words. 
    Includes additional rules to categorize common irregular verbs (like "go", "gone", "went") together.
  - Allows downloading of the original files uploaded
  - Data is stored on the disk without the use of any regular RDBMS or noSQL
  - Data is persistant and will still exist when back end server is restarted

## Installation

Requires jdk 9, maven, and npm to run.

### Important:

Due to the development of the web service on a windows machine and the lack of traditional databases, 
the user is required to edit [dev.properties](https://github.com/nickosaur/Word-Analyzer/blob/master/src/main/resources/dev-app.properties)
file and change `root` so that the value of `root` is at the root of the project directory as the current value of `root` 
is the absolute path to the project directory on my local machine. 

To run this web application, the user will have to load the backend server first. 
If running on a Unix machine, be in the project root folder and run:
```sh
$ mvn clean install
$ java -jar target/wordanalyzer-0.0.1-SNAPSHOT.jar
```

Then, to load the front end server, be at the root of the project directory and run:
```sh
$ cd reactclientfront
$ npm install
$ npm start
```


## Additional notes on structure and design choices
The reason why I had decided to go against using traditional rdbms and noSQL for storage is because
there will always only be a max of 10 files stored. I am able to avoid the need of setting up a database for a
small scaled application. Furthermore, files are stored on [disk](https://github.com/nickosaur/Word-Analyzer/tree/master/data) 
and their names are overwritten as the time and date when they are uploaded to avoid collision of same named files. 
The results are saved in [results.json](https://github.com/nickosaur/Word-Analyzer/blob/master/data/results.json) and
are updated/loaded during the appropriate GET/POST requests. The results.json is empty with "[]" if there arent any results saved.
I have tested the application with both unit tests and integration tests. Unit tests are used to test the Stemmer's functionality
and they can be found [here](https://github.com/nickosaur/Word-Analyzer/blob/master/src/test/java/com/wordanalyze/demo/StemmerTest.java)
and [here](https://github.com/nickosaur/Word-Analyzer/blob/master/src/test/java/com/wordanalyze/demo/WordFrequencyResultRepositoryTest.java).
The [integration tests](https://github.com/nickosaur/Word-Analyzer/blob/master/src/test/java/com/wordanalyze/demo/WordFrequencyServiceTest.java)
focused on the service-model interaction and ensure that files are saved and deleted properly.



## Functionality

If you would just want to test out the RESTful API service, load the back end. 

The HTTP service provides these methods by url:

#### POST file=".txt file" stopSetting={true,false} localhost:8080/api/analyze
Analyzes the .txt file based on the stopSetting provided and returns the result if successful


#### GET localhost:8080/api/:file
Gets the analyzed results of file (in terms of DDMMYYYY). 


#### GET localhost:8080/api/results
Returns the list of results of texts that have been analyzed. 10 max.


#### POST localhost:8080/delete/:file
Deletes the file specified and update the results.json file. Not used in the web service but the functionality is alive and well. 


#### POST localhost:8080/deleteAll
Deletes all the files stored and update the results.json file. Not used in the web service but the functionality is alive and well. 

