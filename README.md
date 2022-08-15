# messaging-service

## Rework plan
- Implement 2 applications CLIENT and SERVER
- CLIENT should connect to the server and continuously send messages with specific interval
- Message should contain fields: logLevel, message, timestamp, index
- CLIENT should be able to re-connect to the server in case it's not available for a while
- SERVER filters out the messages, which have logLevel higher than configured level
- SERVER prints messages to console / log
- Everything should be implemented on Reactive Streams and Spring Web Flux
- Additionally, cashing of the last 1000 messages can be implemented on the CLIENT side in case when SERVER is not responding



### Message fields description
- Field LogLevel - randomly filled value for every message. Possible logLevel values: ERROR, WARNING, INFO, DEBUG
- Field message - any text
- Field timestamp - the timestamp when the message was created
- Field index - order number