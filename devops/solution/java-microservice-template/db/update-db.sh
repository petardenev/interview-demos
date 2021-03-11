#!/usr/bin/env bash

set -e

if [[ "$#" -ne 5 ]]; then
    echo "Illegal number of parameters"
    echo "Usage: update-db.sh DBHOST DBNAME DBUSER DBPASSWORD INFRASTRUCTURE_ENV"
    exit 1
fi

DBHOST=$1
DBNAME=$2
DBUSER=$3
DBPASSWORD=$4
INFRASTRUCTURE=$5
cd $PWD/db
docker build . -t ${INFRASTRUCTURE}:latest
docker run -e DBHOST=${DBHOST} -e DBNAME=${DBNAME} -e DBUSER=${DBUSER} -e DBPASSWORD=${DBPASSWORD} -it $INFRASTRUCTURE:latest
