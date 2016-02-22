# DS/2 OSS Core Base

This is the actual implementation runtime. It contains all the alternatives, cdi beans etc. to have the core base 
running.

Usually, you add this artifact in scope runtime:

    <dependency>
        <groupId>ds2.oss.core</groupId>
        <artifactId>ds2-oss-core-base</artifactId>
        <version>RELEASE</version>
        <scope>runtime</scope>
    </dependency>

## The SymmetricKey Service

This is a service to generate hashes or hash a given byte sequence. 
The service is used for several cryptographic methods.

Starting with version 0.3, a dummy configuration is being used, even for production. You should take care about
the configuration by providing an implementation of type SecurityBaseData in scope ApplicationScoped.
The current implementation being used is AlternateSecurityBaseDataImpl.