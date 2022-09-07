cd eureka-service
mvn clean && mvn package -DskipTests # Skipeamos tests con argumento para evitar errores, por ejemplo de conexión
# Este argumento luego se utiliza como variable de entorno para empaquetar
docker build . -t eureka-service
cd ..

cd config-service
mvn clean && mvn package -DskipTests
docker build . -t config-service
cd ..

cd gateway-service
mvn clean && mvn package -DskipTests
docker build . -t gateway-service
cd ..

cd catalog-service
mvn clean && mvn package -DskipTests
docker build . -t catalog-service
cd ..

cd movie-service
mvn clean && mvn package -DskipTests
docker build . -t movie-service
cd ..

cd serie-service
mvn clean && mvn package -DskipTests
docker build . -t serie-service
cd ..

docker-compose up