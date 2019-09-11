#!/bin/bash

HOST="127.0.0.1"
OUT_PORT=12345
IN_PORT=12121

wsimport -keep -d . "http://${HOST}:${OUT_PORT}/?wsdl"
javac -d . *.java
java second.Second ${HOST} ${OUT_PORT} ${IN_PORT}
