#!/bin/bash

OUT_HOST="127.0.0.1"
OUT_PORT=12121

gcc first.c -o first

#from file
# ./first ${OUT_HOST} ${OUT_PORT} < #filePATH

#from urandom
# ./first ${OUT_HOST} ${OUT_PORT} < /dev/urandom

#from keyboard
./first ${OUT_HOST} ${OUT_PORT}