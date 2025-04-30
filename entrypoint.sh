#!/bin/bash
timestamp=$(date +%Y%m%d_%H%M%S)
HEAP_DUMP_PATH="/var/log/app/inmemory/heapdump_${timestamp}.hprof"
echo "starting the application with heapdumppath:$HEAP_DUMP_PATH"
exec java \
    -Xms512m \
    -Xmx1024m \
    -XX:+UseZGC \
    -XX:+HeapDumpOnOutOfMemoryError \
    -XX:HeapDumpPath=$HEAP_DUMP_PATH \
    -DCOMMAND_SERVER_PORT=$COMMAND_SERVER_PORT \
    -jar app.jar