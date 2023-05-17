Run db in a Docker container:

```sh
docker run --name technotronus -p 5432:5432 -e POSTGRES_USER=postgres -e POSTGRES_PASSWORD=postgres -e POSTGRES_DB=technotronus -e JAVA_OPTS="-Xms512m -Xmx2g" -v postgres-data:/var/lib/postgresql/data postgres
```


