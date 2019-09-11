#!/bin/bash

OUT_HOST="127.0.0.1"
OUT_PORT=12121

gcc first.c -o first
./first ${OUT_HOST} ${OUT_PORT}