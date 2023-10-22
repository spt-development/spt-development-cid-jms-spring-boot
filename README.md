````
  ____  ____ _____   ____                 _                                  _   
 / ___||  _ \_   _| |  _ \  _____   _____| | ___  _ __  _ __ ___   ___ _ __ | |_ 
 \___ \| |_) || |   | | | |/ _ \ \ / / _ \ |/ _ \| '_ \| '_ ` _ \ / _ \ '_ \| __|
  ___) |  __/ | |   | |_| |  __/\ V /  __/ | (_) | |_) | | | | | |  __/ | | | |_ 
 |____/|_|    |_|   |____/ \___| \_/ \___|_|\___/| .__/|_| |_| |_|\___|_| |_|\__|
                                                 |_|                                           
 cid-jms-spring-boot-------------------------------------------------------------
````

[![build_status](https://github.com/spt-development/spt-development-cid-jms-spring-boot/actions/workflows/build.yml/badge.svg)](https://github.com/spt-development/spt-development-cid-jms-spring-boot/actions)

Library for integrating 
[spt-development/spt-development-cid-jms-spring](https://github.com/spt-development/spt-development-cid-jms-spring) 
into a Spring Boot application.

Usage
=====

Simply add the Spring Boot starter to your Spring Boot project pom.

```xml
<dependency>
    <groupId>com.spt-development</groupId>
    <artifactId>spt-development-cid-jms-spring-boot-starter</artifactId>
    <version>1.0.2</version>
    <scope>runtime</scope>
</dependency>
```

Building locally
================

To build the library, run the following maven command:

```shell
$ ./mvnw clean install
```

Release
=======

To build a release and upload to Maven Central push to `main`.