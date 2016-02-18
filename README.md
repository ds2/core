# DS/2 OSS Core

[![Build Status](https://travis-ci.org/ds2/core.svg?branch=develop)](https://travis-ci.org/ds2/core)

The OSS components of the DS/2 Core Implementation. This set of artifacts provides you with a bunch of tools
and utilities to create flexible code with only small code additions. All is meant for JEE7 in mind.

## Usage

To better understand this library, we use a sample project where you need some tools to develop faster.

### Read properties files

For example, you need the content of a properties file in your Properties class in one of your JEE7 services. To
get it, first add the ds2-oss-core-api artifact to your common deployment. Usually, you add it via Maven
in scope compile:

    <dependency>
      <groupId>ds2.oss.core</groupId>
      <artifactId>ds2-oss-core-api</artifactId>
      <version>RELEASE</version>
    </dependency>

Then, to fill the api later with some functionality, add the core-base artifact in scope runtime:

    <dependency>
      <groupId>ds2.oss.core</groupId>
      <artifactId>ds2-oss-core-base</artifactId>
      <version>RELEASE</version>
      <scope>runtime</scope>
    </dependency>

You develop against the api artifact. In your class, add the properties file:

    private Properties myProps;

Then add a specific annotation to it:

    @PropertiesLoader
    private Properties myProps;

You now have to define how to load the properties file. There are several ways. For the quickest solution and the
purpose of this sample tutorial, we assume that the properties file you need is in your classpath:

    @PropertiesLoader(resource="/com/test/myProps.properties")
    private Properties myProps;

When you run your class now (via CDI), the properties file content will be added to your Properties field.

Also, a common way is to use a system property to tell your system where a specific properties file is. You can
load it via

    @PropertiesLoader(sysProp="dd.my.props.file")
    private Properties myProps;

You need to run it now via

    -Ddd.my.props.file=/home/test/bla/myFile.properties

