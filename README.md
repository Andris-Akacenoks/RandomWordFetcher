# Random Word Fetcher

#### Run unit tests:
`mvn clean -Dtest=RandomWordTests test`
#### Run (main class):
`mvn clean compile exec:java -Dexec.mainClass=Main -Dexec.cleanupDaemonThreads=false`

Text file with summary is generated under `result` folder.

#### Dependencies:
* JUnit - unit testing
* OkHttp - making HTTP requests
* Mockito - mock REST API response in tests