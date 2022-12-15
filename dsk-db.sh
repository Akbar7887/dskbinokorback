#!/bin/bash

docker stop test-project-db
docker rm test-project-db

docker run -d --hostname test-project-db.dsk.uz \
	--name postgres \
	--network dskbinikorback \
	-p 5432:5432 \
	--restart unless-stopped \
	-v postgres_db:/var/lib/postgresql/data \
	-e POSTGRES_DB="dsk" \
	-e POSTGRES_USER="postgres" \
	-e POSTGRES_PASSWORD="123" \
	-v /etc/timezone:/etc/timezone \
	-v /etc/localtime:/etc/localtime:ro \
	postgres:14-alpine \
	-N 500
exit 0