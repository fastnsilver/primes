# Primes Service (Solution)

This branch contains the solution to the challenge.
The implementation has dependencies upon Spring Boot 3.2.x starters.

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
git checkout 3.2
```


## Install prerequisites

```
sdk install java 21-graalce
sdk install gradle 8.4
```


## Set active JDK

```
sdk use java 21-graalce
```
> GraalVM Community Edition, required for native image compilation


## How to Build

```
gradle clean build -x processTestAot
```
> AOT disabled in tests; @see https://github.com/mockito/mockito/issues/2435

### Alternative builds

with Wavefront
* adds a dependency on [wavefront-springboot-starter](https://docs.wavefront.com/wavefront_springboot3.html)
* you will also need to update key-value pairs in [application.properties](src/main/resources/application.properties)

```
gradle clean build -PisObserved
```
> Export metrics and traces to Wavefront

Native image (native)
* uses [Spring AOT](https://docs.spring.io/spring-native/docs/current/reference/htmlsingle/#spring-aot-maven) to compile a native executable with [GraalVM](https://www.graalvm.org/docs/introduction/)

```
gradle nativeCompile
```


## How to Run

With JRE

```
gradle bootRun
```
> Press Ctrl+c to stop

With native image

```
./build/native/nativeCompile/primes
```


## How to deploy

to Tanzu Application Service

```
cf push
cf push primes-native -m 128m -c './build/native/nativeCompile/primes' -b binary_buildpack -s cflinuxfs4 --no-manifest
```

## How to teardown

from Tanzu Application Service

```
cf delete primes -f
cf delete primes-native -f
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
