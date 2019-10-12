<p align="center">
<img src=".github/logo.png" alt="Mantikor" title="Mantikor" />
</p>
# Mantikor

Mantikor is a simple and lightweight webserver designed build it into your own application as an 
embedded webserver to handle handle web interfaces or similar shit. If you want to build RESTful
services better take a look at https://github.com/D3adspaceEnterprises/echidna

# Build Status

|             | Build Status                                                                                                            |
|-------------|-------------------------------------------------------------------------------------------------------------------------|
| Master      | [![Build Status](https://travis-ci.org/d3adspace/mantikor.svg?branch=master)](https://travis-ci.org/d3adspace/mantikor) |
| Development | [![Build Status](https://travis-ci.org/d3adspace/mantikor.svg?branch=dev)](https://travis-ci.org/d3adspace/mantikor)    |

# Installation / Usage

- Clone this repo
- Install: ```./gradlew build```

**Maven repositories**

```xml
<repositories>
    <repository>
        <id>klauke-enterprises-maven-releases</id>
        <name>Klauke Enterprises Maven Releases</name>
        <url>https://repository.klauke-enterprises.com/repository/maven-releases/</url>
    </repository>

    <repository>
        <id>klauke-enterprises-maven-snapshots</id>
        <name>Klauke Enterprises Maven Snapshots</name>
        <url>https://repository.klauke-enterprises.com/repository/maven-snapshots/</url>
    </repository>
</repositories>
```

**Maven dependencies**

_Server:_
```xml
<dependency>
    <groupId>de.d3adspace.mantikor</groupId>
    <artifactId>mantikor-server</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

_HTTP Models:_
```xml
<dependency>
    <groupId>de.d3adspace.mantikor</groupId>
    <artifactId>mantikor-commons</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```  

_Netty channel handler implementations:_
```xml
<dependency>
    <groupId>de.d3adspace.mantikor</groupId>
    <artifactId>mantikor-codec-netty</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

**Gradle repositories:**
```groovy
maven {
    url = 'https://repository.klauke-enterprises.com/repository/maven-releases/'
}

maven {
    url = 'https://repository.klauke-enterprises.com/repository/maven-snapshots/'
}
```

**Gradle dependencies:**

_Server:_

```groovy
dependencies {
    compile 'de.d3adspace.mantikor:mantikor-server:1.0-SNAPSHOT'
}
```

_Commons:_

```groovy
dependencies {
    compile 'de.d3adspace.mantikor:mantikor-commons:1.0-SNAPSHOT'
}
```

_Netty channel handler implementations:_

```groovy
dependencies {
    compile 'de.d3adspace.mantikor:mantikor-codec-netty:1.0-SNAPSHOT'
}
```

# Example

**Build an HTTP request**
```java
HTTPRequest request = HTTPRequest.newBuilder()
    .withMethod(HTTPMethod.GET)
    .withLocation("/test")
    .withHeader("Authorization", "3456-6543-4566-2342")
    .build();
```

**Build an HTTP response**
```java
HTTPResponse response = HTTPResponse.newBuilder()
    .withStatus(HTTPStatus.OK)
    .withHeader("X-Powered-By", "Me")
    .withBody("NOPE!")
    .build();
```

**Create a custom server**
```java
MantikorConfig mantikorConfig = MantikorConfig.newBuilder()
	.setServerHost("localhost")
	.setServerPort(8080)
	.createMantikorConfig();
		
MantikorServer mantikorServer = new MantikorServer(mantikorConfig) {
	@Override
	public HTTPResponse handleRequest(HTTPRequest request) {
	  return HTTPResponse.newBuilder()
          .withStatus(HTTPStatus.OK)
          .withHeader("X-Powered-By", "Me")
          .withBody("NOPE!")
          .build();
	}
};
		
mantikorServer.start();
```

# File Server
We deliver a really simple example of a static file server located at [file-server](https://github.com/D3adspaceEnterprises/mantikor/tree/master/file-server). 
It can serve local files really fast. The preferred way of deployment is via Docker:

```
docker run -it -v /home/user:/opt/base d3adspace/mantikor-file-server
```

For docker-compose, take a look at `docker-compose.yml`
