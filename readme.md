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
  mvn -f app/pom.xml -Pjib package
  ```

  Optionally, build to Docker daemon:

  ```
  mvn -f app/pom.xml -Pjib jib:dockerBuild
  ```

- Deploy to k8s:

  ```
  helm install --name myapp chart
  ```
