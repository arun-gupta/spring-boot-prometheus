# Spring Boot Application with Prometheus

This repo contains a simple Spring Boot application that publishes Proemtheus-style metrics.

## Java Application

- Run application:

  ```
  mvn -f app/pom.xml spring-boot:run
  ```

- Access the application:

  curl http://localhost:8080/hello

- Access [metrics endpoint](http://localhost:8080/actuator/prometheus)

## Deploy to Kubernetes

- Build and push Docker image:

  ```
  docker image build -t arungupta/greeting:prom .
  docker image push arungupta/greeting:prom
  ```

  Alternatively, build using jib (not working at this time):

  ```
  mvn -f app/pom.xml package -Pjib
  ```

  Optionally, build to Docker daemon:

  ```
  mvn -f app/pom.xml jib:dockerBuild -Pjib
  ```

- Deploy to k8s:

  ```
  helm install --name myapp chart
  ```
