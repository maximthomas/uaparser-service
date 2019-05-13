# User Agent Parser Service

Parses user agent string to structured JSON

Run from sources
```
./mvnw spring-boot:run
```

Build and run docker image
```
./mvnw install
docker-compose up --build 
```

Sample usage
```
curl -G --data-urlencode 'user-agent=Mozilla/5.0 (Linux; U; Windows NT 5.1; en-US; rv:1.8.1.11) Gecko/20071127 Firefox/2.0.0.13' 'http://localhost:8080' 
```
or
```
curl -H 'User-Agent: Googlebot-Image/1.0' 'http://localhost:8080'
```
