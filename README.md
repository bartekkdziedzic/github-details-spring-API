
# Github Repository Listing Spring Boot 3 API

The project consists of a Spring Boot 3 API that can list specific details of given username Github repositories.

# Table of Contents 

API has been divided into packages:
- controller,
- dto (data transfer object) - transferred object body models,
- exception - custom exceptions +  custom exceptions handling,
- mapper - utility for datatype mapping,
- model - received object body models,
- service - service of requests,
- test - tests for controllers, mappers, services

# How to Install and Run the Project
1) Clone the repository
2) Build the project using Maven
3) Run the RTaskApplication
# How to Use the Project
To access Github API requests you may need to authenticate your personal Github token. For that please follow:

https://docs.github.com/en/rest/overview/authenticating-to-the-rest-api?apiVersion=2022-11-28

Note - You should choose the newest API version on given site.

# API Endpoint
/api/github/{username}   - endpoint for listing username GitHub repositories that are not forks with the response structure including 
- Repository Name,
- Owner Login
- For each branch, its name and last commit sha 

# Configuration
You can configure the application's behaviour by modifying the application.properties.

# Contributing
Contributions are welcome! Feel free to create a pull request if you find any issues or would like to enhance the project.
