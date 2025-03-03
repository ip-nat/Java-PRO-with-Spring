# Java-PRO-with-Spring

**Create and run DB**  
Выполните команду из директории файла README, заполнив POSTGRES_USER и  POSTGRES_PASSWORD  
`docker run --name postgres-ipp -p 5432:5432 -e POSTGRES_USER=<secret> -e POSTGRES_PASSWORD=<secret> -v "$(pwd)/initDB:/docker-entrypoint-initdb.d" -d postgres:17.4`