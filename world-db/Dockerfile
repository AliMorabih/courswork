# Use the latest MySQL image
FROM mysql
# Set the working directory
WORKDIR /tmp
# Copy the file world-db to the working directory of the container
COPY world-db/*.sql /tmp/
# Copy the main SQL file to docker-entrypoint-initdb.d.
# Scripts and SQL files in this folder are executed on container startup.
COPY world-db/world.sql /docker-entrypoint-initdb.d
# Set the root password
ENV MYSQL_ROOT_PASSWORD example
