# DS/2 OSS Core Options

This artifact contains the options toolkit to alter/change some data during runtime of a web application.
The idea is that you define a property, give it a default value.
And you can change the value based on

* the cluster of the web application
* the ip address of the node where the web app runs on
* time/date
* ...

You can also add encrypted data to it which gets distributed via infinispan to all nodes. And only the servers can
decrypt the data. Best for storing/managing passwords for accessing external services.
