# plans-management-system

It's app based on management, store and messaging of events through an external provider

This app is based in:
- Avro and Kafka libraries in an extra package
- Kafka Consumer and provider config on each infrastructure
- Ports and adapters configuration
- Scheduler for get the xml events
- Repository for Postgresql
- Redis for Cache queries and Events

We have 2 bounded context were one is responsable of obtained and messaging the events from and external provider, and the other ones is 
who takes that processed events and store him through a database. We use Redis in both context for cache queries and processed events.
<br/>
In this task I don't make so much extravalidation that I could make in the domain and value objects and also the testing is not done, but is something that is very important(I spend the time in troubles with configuration)
<br/>

I made a make file for running and also you can run the running the docker-compose and then the app.
<br/>
Open api is facilitated to check the queries from events http://localhost:8080/swagger-ui/index.html (I missed the validation for dates, so for timing I considered the happy path when the user enter the dates)

