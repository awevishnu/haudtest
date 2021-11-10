# haudtest

The system uses publish and subscribe model for inter services communication.
We have three micro services. message-sender-service, filter-service, charging-service.
Application message-sender-service  HTTP endpoint is responsible to send the message.
All the incoming messages are processing asynchronusly. message-sender-service send messages to RabbitMQ exchange and then respond to the calling client that the request has been accepted for processing.
Application charging-service and filter-service are listening for the incoming messages from incoming messages from exchange. 
