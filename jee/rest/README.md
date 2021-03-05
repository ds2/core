# DS/2 OSS Core JEE REST Tools

Some helpful classes to deal with REST implementations.

## Creating the client

The client creation depends on your preferred client. So
we cannot ship with a default way.

Use (Jersey)

    ClientConfig configuration = new ClientConfig();
    configuration.property(ClientProperties.CONNECT_TIMEOUT, 1000);
    configuration.property(ClientProperties.READ_TIMEOUT, 1000);
    Client client = ClientBuilder.newClient(configuration);
    
or (Apache CXF)

    client.property(org.apache.cxf.message.Message.CONNECTION_TI‌​MEOUT, 300000) // 5min
    .property(Message.RECEIVE_TIMEOUT, 300000); // 5min

or (RestEasy)

    Client client = new ResteasyClientBuilder()
    .establishConnectionTimeout(2, TimeUnit.SECONDS)
    .socketTimeout(2, TimeUnit.SECONDS)
    .build();