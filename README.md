Coverage: 62%
# Inventory Management System

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

## Deployment

Deployment of this project has been managed through the use of a CI pipeline. Source code is written on Java, pushed to a VCS (GitHub) and tracked using a Jira Kanban Board. Maven is used as a build tool for this process, and JUnit is used for automated testing.

## Built With

* [Maven](https://maven.apache.org/) - Dependency Management
* [Eclipse](https://www.eclipse.org/downloads/) - IDE
* [MySQL](https://www.mysql.com/) - Database Language
* [GCP](https://cloud.google.com/) - Database Host
* [JUnit](https://junit.org/junit5/) - Testing
* [Mockito](https://site.mockito.org/) - Testing
* [Jira](https://jira.atlassian.com/) - Project Management

## Versioning

* [Git](https://git-scm.com/) - Version Control System (VCS)
* [Github](https://github.com/) - VCS Host

## Authors

* **Kiera Walton** - *Author* - [kierawaltonqa](https://github.com/kierawaltonqa)

## License

This project is licensed under the MIT license - see the [LICENSE.md](LICENSE.md) file for details 

*For help in [Choosing a license](https://choosealicense.com/)*

## Acknowledgments

* **Chris Perrins** - *provided a base for the project* - [christophperrins](https://github.com/christophperrins) (base can be found here)
* **Nicholas Johnson** - *software trainer*
* **Aswene Sivaraj** - *Java trainer* 
* **Vinesh Ghela** - *Java trainer*

