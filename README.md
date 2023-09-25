# Stajcell-Paycell-API-Project
## Introduction
This project is a [Spring Boot](https://spring.io/) MVC API project created for Turkcell's Stajcell internship programme. The API mimicks [{JSON} Placeholder](https://jsonplaceholder.typicode.com/todos) behavior. The main differences between our project and source is the usage of:
- Caching
- Basic authentication

Also, ImpactAnalyzer.py is the prototype for an API Change Impact Analyzer, which scrapes GitHub code to detect endpoint usage.

## Installation

1. Clone this repository:
   ```shell
      git clone https://github.com/yyusufcoskun/Stajcell-Paycell-API-Project.git
   
2. Run ```./gradlew bootRun``` in terminal.
3. Open localhost:8080 in your browser.
4. Try out the endpoints.

For basic authentication, username is ```user``` and password is ```pass```.

## Documentation
API documentation can be found [here](https://stajcell-api-project.readme.io/reference)

## Data model
Data taken from source is returned with 4 parameters:
- ```userId``` (integer): The user ID associated with the task.
- ```id``` (integer): The unique ID of the task.
- ```title``` (string): The title of the task.
-  ```completed``` (boolean): Indicates whether the task is completed.

## Examples
Example endpoints include:

- ```/api/tasks?userId=5```
- ```/api/tasks?userId=8&completed=true```
- ```/api/tasks/1```

## Caching behavior
TTL for data cached from [source](https://jsonplaceholder.typicode.com/todos) is 1 minute. Cache is automatically cleared after TTL, or manually by using the ```/api/clear``` endpoint.

## Credits
This project was created by [Can Korkmaz](https://github.com/cankorkmazz) and [Yusuf Coşkun](https://github.com/yyusufcoskun). I would like to thank Can for all his contributions to this project, and for allowing me to host the project repo on my own profile.
