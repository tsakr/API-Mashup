API-Mashup
==========

Twitter and Yahoo API mashup 

Create a RESTful API that consists of one or more resources. The API should allow the consumer to retrieve aggregated data from 2 or 3 sources. 

Here are some of the requirements:

Design RESTful resources 
The API takes requests for data, then delegates those requests to more than one public APIs, once the responses are received, the data from multiple sources should be aggregated and sent back to the consumer.
One of the delegations should be asynchronous and use a message queue.
Persist some request tracking information using JPA (with or without Spring); in-memory database.
Show experience in packaging a full ready-to-run project.

Optionally: add authentication
Optionally: add unit and integration tests.

Required technologies: JAX-RS on CXF, JMS, and Maven.
Optional technologies: deploy on servlet container (e.g. Tomcat), or better yet ServiceMix. Use Camel for routing.

Deliver a Maven project that builds successfully and deploys on the target container of your choice. If specific
dependencies are required on the target container to run the application, please include a list of those.