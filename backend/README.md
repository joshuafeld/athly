```shell
openssl genpkey -algorithm RSA -out private.pem -pkeyopt rsa_keygen_bits:2048
openssl rsa -pubout -in private.pem -out public.pem
```

Rename `.env.example` to `.env` and fill in the values.

```shell
mvn clean package -DskipTests
docker compose down -v
docker compose build --no-cache
docker compose up -d
```