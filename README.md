# plans-management-system

This app is unfinished for not have enough management time. I create the main structure and mapper but I miss the validation in domain and more config(no time). 
For that I could not make a Make file for run the app.
What is already implemented is
- Avro and Kafka libraries in an extra package
- Kafka Consumer and provider config on each infrastructure
- Ports and adapters configuration
- Scheduler for get the xml events
- Repository for Postgresql

The management domain is on the stage of receive the event and save it on database and create the queries and rest
<br/>

My idea is 2 main domains focused one in consume the xml event through a scheduler, process it and create a Kafka topic 
for the second domain that is going to be responsable o management in a Postgres Database the topic and also
being able to make queries through rest controller for consulting events.
<br/>
The architecture is Hexagonal with DDD and CQRS, also my idea was to validate the domain through entities and valueObjects.

<br/>Apart of Kafka and Postgresql I would like to implement a redis for cache events from provider xml events and for queries of postgresql

<br/> Also implement Unit test with JUNIT and Mocks