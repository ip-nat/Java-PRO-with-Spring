# Java-PRO-with-Spring

**Create and run DB**  
docker run --name postgres-ipp -p 5432:5432 -e POSTGRES_USER=<secret> -e POSTGRES_PASSWORD=<secret> -v "${PWD}:/docker-entrypoint-initdb.d" -d postgres:17.4