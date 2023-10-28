# Primes Service (Solution)

This branch contains the solution to the challenge.
The implementation has dependencies upon Spring Boot 2.1.x starters.

## The Challenge was...

Given 2 numbers, a start value and an end value, calculate all the possible prime numbers between them.

This code puzzle contains a basic interface.

You should:

* Implement the interface.
* Author a unit test that covers code all paths. That is; its test methods will assert proper and accurate functioning of the implementation and verify that exceptional behavior is handled appropriately (in a manner that does not crash the program).

Bonus points for authoring a Mock test that covers the Controller implementation.


## Tools

* [git](https://git-scm.com/downloads) 2.40.0 or better
* [sdkman](https://sdkman.io)
  * use to install and manage multiple versions of Gradle and JDK
* [cf](https://docs.cloudfoundry.org/cf-cli/install-go-cli.html) CLI 8.6.1 or better
* [httpie](https://httpie.io/) 3.2.2 or better


## Clone

```
git clone https://github.com/fastnsilver/primes.git
cd primes
git checkout 2.1
```


## Install prerequisites

```
sdk install java 8.0.372-librca
sdk install gradle 7.6.1
```


## Set active JDK

```
sdk use java 8.0.372-librca
```
> Java 8


## How to Build

```
gradle clean build
```


## How to Run

With JRE

```
gradle bootRun
```
> Press Ctrl+c to stop


## How to deploy

to Tanzu Application Service

```
cf push
```

## How to teardown

from Tanzu Application Service

```
cf delete primes -f
```

## Roadmap

Update instructions to show how to _build_ and _run_ with:

* Docker
* AKS
* EKS
* GKE
* OKE
* Azure Spring Apps Enterprise
* Openshift
* Tanzu Application Platform
