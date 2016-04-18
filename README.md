# Dussenger

Desktop Messenger built on Java, which implements features such as group chatting and file transfer.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. See deployment for notes on how to deploy the project on a live system.

### Prerequisities & Installing
In order to run the sever on your computer, you need a computer that run Mac OS or Linux.

Also, you need to download postgresql from http://www.postgresql.org/download/.

Then you need to create a Database named as **Dussenger** ,a login role named as **admin** and set login role's password to **ECE651**

All those things above can be done in the Postgresql user interface.


### Initialization

The first time you run the server you need to uncommon the **initialize()** line in the main function in Server.java file.

**intialize()** function will set up the database, create the table you will use and set several default users in the Database.

After you initialize your database, you need to common **initialize()** line again.

The default user is **"Mike","Bob","Alice","Alex","Ric","Steve","Lili","Drew"** , they are all friends between each other and having the password equal to their user name.

## Running the tests

Explain how to run the automated tests for this system

### Break down into end to end tests

Explain what these tests test and why

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

* Dropwizard - Bla bla bla
* Maven - Maybe
* Atom - ergaerga

## Contributing

Please read [CONTRIBUTING.md](CONTRIBUTING.md) for details on our code of conduct, and the process for submitting pull requests to us.

## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags). 

## Authors

* **Billie Thompson** - *Initial work* - [PurpleBooth](https://github.com/PurpleBooth)

See also the list of [contributors](https://github.com/your/project/contributors) who participated in this project.

## License

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Acknowledgments