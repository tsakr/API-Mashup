CXF REST Resource Provider
==========================

A simple implemantion which receives REST web service calls (via a CXF consumer, using bean binding)
and writes these requests into AMQ queue as XML payload represenation of search query parameters and invoked operation. 

The goal of this appliation is to show how to implement CXF consumer component in a Camel environment as an HTTP service activator. 
All Camel bundles using a Camel CXF consumer can use this HTTP service without needing to start individual Jetty instances. 

so far there are 2 GET resources are exposers in this application:

http://localhost:9000/QueryService/city/{city}/state/{state}
example: http://localhost:9000/QueryService/city/Berkeley/state/CA

and 

http://localhost:9000/QueryService/{query}
example: http://localhost:9000/QueryService/Berkeley.CA


