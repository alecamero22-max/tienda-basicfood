#!/bin/sh
# Render (y otros hosts) inyectan el puerto en la variable PORT.
# Tomcat lo lleva escrito en conf/server.xml, así que lo cambiamos al arrancar.
set -e

PUERTO="${PORT:-8080}"

sed -i "s/port=\"8080\"/port=\"${PUERTO}\"/" /usr/local/tomcat/conf/server.xml

echo "BasicFood arrancando en el puerto ${PUERTO}..."

exec catalina.sh run
