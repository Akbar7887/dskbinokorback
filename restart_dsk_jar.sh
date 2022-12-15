#!/bin/bash

docker stop dsk_jar_api
docker rm dsk_jar_api

docker run -d --hostname dsk_jar_api.uz \
	-p 8089:8089 \
	--name dsk_jar_api \
	--network dskbinikorback \
	-v /Users/ulugbek/uploads:/app \
	dsk_jar_api

exit 0