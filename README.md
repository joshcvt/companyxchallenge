# companyxchallenge
Company X Challenge

Quick Spring Boot REST controller sample, responding to an interview challenge. This is best understood as "what can I get working in spare weekend cycles, starting from zero knowledge of Spring Boot 48 hours ago".

Usage:

```% ./mvnw spring-boot:run```

Things completed:
* Basic (very, very basic) protection on all endpoints with Spring Security. 'user:password' 
* Single user fetch by ID: GET http://localhost:8080/api/user/{id}  
 Successful find returns HTTP 200, user object  
 If ID doesn't exist, returns HTTP 204 No Content
* Return list of users, alphabetical by lastname then firstname, case insensitive: GET http://localhost:8080/api/user/
* Add User to table: POST ```{"firstName":"John","lastName":"Doe"}``` to http://localhost:8080/api/user/save  
 Successful save returns HTTP 200, body user object with ```id``` element  
 If firstName/lastName pair is already in table, case insensitive comparison, returns HTTP 409 Conflict with User object containing ```"id":"null"```
* Delete User by ID: GET http://localhost:8080/api/user/delete/{id}  
 Successful delete returns HTTP 200, ID as body  
 ID not found returns HTTP 417 Expectation Failed, ID as body
* Preloaded some data in [data.sql](src/main/resources/data.sql)

Things I'd do with more time on it:
* *Tests*: this is completely informally tested with [a small package of Postman requests](companyxchallenge.postman_collection.json) and some curl requests as sanity checks. I prioritized learning the parts I needed to deliver working code by deadline; formal testing would not be optional in a real project.  
 This would include moving data preloading out of data.sql and into here since I'm only preloading to test the code anyway.
* Better error returns, particularly making the HTTP body more useful. Returning the same object that came in seems to be a Spring Boot idiom, so I stuck with that for now, but there's probably a better way.
* Role-based authorization rather than the blanket "just authenticate"
* Use a real authentication provider, not HttpBasic with a username and password stuffed in [application.properties](src/main/resources/application.properties)
* It's presenting a HATEOAS service at http://localhost:8080/user (vs. my services at ```/api/user```). That's at least behind authentication, but once I understand the HATEOAS concepts I'd presumably only want to present one coherent service.
