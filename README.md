# Simple Logic Interpreter [![Build Status](https://travis-ci.org/abrden/7510-TP1-Funcional.svg?branch=develop)](https://travis-ci.org/abrden/7510-TP1-Funcional)

A Clojure library designed to serve as a logic interpreter.

## Prerequisites

- JDK 1.8 or greater
- [Leiningen](https://github.com/technomancy/leiningen)

## Tests
Run tests
```
$ lein test
```

## Usage
Clone the project
```
$ git clone https://github.com/abrden/7510-TP1-Funcional.git
$ cd 7510-TP1-Funcional/
```
Write your database a file (there's some examples [here](https://github.com/abrden/7510-TP1-Funcional/tree/master/test/files)) and run the interpreter with it's path.
For example:
```
$ lein run test/files/number_database.txt
```
Make any query
```
add(2,2,1)
(SLI) false
```
Malformed queries return nil
```
Hi!
(SLI)
```
Press 'q' to exit.
```
q
(SLI) Exiting
```

Or you could run the SLI with the jar
```
$ lein uberjar
$ java -jar target/tdd-tp1-0.1.0-SNAPSHOT.jar test/files/number_database.txt
```
## License

Distributed under the Eclipse Public License either version 1.0 or any later version.
