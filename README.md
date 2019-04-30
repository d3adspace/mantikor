# Mantikor

Mantikor is a simple and lightweight webserver designed build it into your own application as an 
embedded webserver to handle handle web interfaces or similar shit. If you want to build RESTful
services better take a look at https://github.com/D3adspaceEnterprises/echidna

# Installation / Usage

- Install [Maven](http://maven.apache.org/download.cgi)
- Clone this repo
- Install: ```mvn clean install```

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

```xml
<dependency>
    <groupId>de.d3adspace.mantikor</groupId>
    <artifactId>mantikor-core</artifactId>
    <version>1.0-SNAPSHOT</version>
</dependency>
```

# Example
```java
MantikorConfig mantikorConfig = MantikorConfig.newBuilder()
	.setServerHost("localhost")
	.setServerPort(8080)
	.createMantikorConfig();
		
MantikorServer mantikorServer = new MantikorServer(mantikorConfig) {
	@Override
	public HTTPResponse handleRequest(HTTPRequest request) {
		return HTTPResponse.newBuilder()
			.setStatus(HTTPStatus.NOT_FOUND)
			.setHeaders(new HTTPHeaders())
			.createHTTPResponse();
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
