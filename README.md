Coverage: 34%
# Project Title

This project is an application, developed and implemented through java, which allows users to create, read, update and delete from tables in an SQL database. The database has 4 tables - customers, items, orders and orderline (an intermediary table to manage the many-to-many relationships within Java) - and users can interact with these tables via a command line. The java application uses a JDBC connection to create statements used for executing SQL commands, where the JDBC API is implemented through a JDBC driver.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisites

In order to run this application, you will require a Java runtime environment, as well as a Java IDE (such as Eclipse). You will also need to have a version of Maven in order to build the project.


### Installing

Steps to follow to get the application running:
1. fork this repository
2. go to the main directory of the project
3. open a command line in this directory
4. enter 'mvn clean'
5. enter 'mvn package'
6. enter 'cd target'
7. enter java -jar , name of jar file with dependences, .jar


## Running the tests

To run the tests for this application, JUnit is used. Simply open the source code in your IDE and run the tests as a JUnit application.

### Unit Tests 

Unit tests are used to test this application. Unit testing is a method by which individual units of source code are tested; this is done to validate whether each unit of code performs as expected and hence determine whether each unit can successfully be used.

```
Give an example
```

### Integration Tests 
Explain what these tests test, why and how to run them

```
Give an example
```

### And coding style tests

Explain what these tests test and why

```
Give an example
```

## Deployment

Add additional notes about how to deploy this on a live system

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Eclipse](https://www.eclipse.org/downloads/) - IDE
* [MySQL](https://www.mysql.com/) - Database Language
* [GCP](https://cloud.google.com/) - Database Host
* [JUnit](https://junit.org/junit5/) - Testing
* [Mockito](https://site.mockito.org/) - Testing
* [Jira](https://jira.atlassian.com/) - Project Management

## Versioning

We use [SemVer](http://semver.org/) for versioning.

## Authors

* **Chris Perrins** - *Initial work* - [christophperrins](https://github.com/christophperrins)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* Hat tip to anyone whose code was used
* Inspiration
* etc
