#!/bin/bash
set -e

export PGPASSWORD=secret

psql -v ON_ERROR_STOP=1 --username oneflow --dbname postgres <<-EOSQL
    CREATE DATABASE id;
    GRANT ALL PRIVILEGES ON DATABASE id TO oneflow;

    CREATE DATABASE cms;
    GRANT ALL PRIVILEGES ON DATABASE cms TO oneflow;

    CREATE DATABASE shop;
    GRANT ALL PRIVILEGES ON DATABASE cms TO oneflow;
EOSQL