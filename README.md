# Example REST service project

## See it in Action

### Build

Build and run all the static checks, unit tests and the mutation tests and finally produce a local coverage report.

```shell
$ gradle check pitest jacocoTestReports
```

Static analysis and test execution report (.html) files may be found in the `build/reports` folder tree.

### Run

The console will show progress of the service.

```shell
$ gradle bootRun
```

### Run BDD tests

This command will execute all the Karate BDD tests.

```shell
$ gradle bdd
```

A report of the test run may be found in `build/karate-reports/karate-summary.html`

### All commands

```shell
$ gradle tasks --all
```

## Folder structure

### Overview

Only the top 2 directory levels are shown here.  Subsequent diagrams will address the lower levels.

    Project Root
    ├── app
    |   ├── src
    |   |   └── .......
    |   └── build.gradle
    ├── lib
    |   ├── src
    |   |   └── .......
    |   └── build.gradle
    ├── .gitignore
    ├── settings.gradle
    └── lombok.config

### Source

This is a detailed view of the source directory.  A test directory is very similar, only it has fewer resources.

    src
    └── main
        ├── java
        |   └── (package)
        |       ├── config
        |       |   ├── model.java
        |       |   └── PartnerService.java
        |       ├── controller
        |       |   └── Controller.java
        |       ├── persistence
        |       |   ├── model
        |       |   |   └── Entity.java
        |       |   └── repository
        |       |       └── Repository.java
        |       └── service
        |           └── Service.java
        └── resources
            ├── db
            |   ├── changelog
            |   |   └── 000000001-InitialLoad
            |   |       ├── db.changelog-master.yml
            |   |       └── 000000001-Create-AppThrottle.yaml
            |   └── db.changelog-master.yml
            └── application.yml

### Unit tests

Files in the test directory tree will mostly duplicate those from the `src` directory, only the filenames will include **Test**

    src
    └── main
        ├── java
        |   └── (package)
        |       ├── config
        |       |   ├── modelTest.java
        |       |   └── PartnerServiceTest.java
        |       ├── controller
        |       |   └── ControllerTest.java
        |       ├── persistence
        |       |   ├── model
        |       |   |   └── EntityTest.java
        |       |   └── repository
        |       |       └── RepositoryTest.java
        |       └── service
        |           └── Service.java
        └── resources
            └── application.yml

### Function Tests (BDD)

    src
    └── bdd
        ├── features
        |   └── package (com.discover.loan.origination.throttle)
        |       ├── Eligible.feature
        |       └── Health.feature
        ├── java
        |   └── package (com.discover.loan.origination.throttle)
        |       ├── KarateRunnerBDD.java
        └── resources
            └── karate-config.js

