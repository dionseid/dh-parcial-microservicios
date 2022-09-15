mvn clean && mvn package -DskipTests # Skipeamos tests con argumento para evitar errores, por ejemplo de conexión
# Este argumento luego se utiliza como variable de entorno para empaquetar
docker build . -t movie-service