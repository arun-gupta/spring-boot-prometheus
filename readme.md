# Spring Boot Application with Prometheus

This repo contains a simple Spring Boot application that publishes Proemtheus-style metrics.

- Run application:

  ```
  mvn spring-boot:run
  ```

- Access the application:

  curl http://localhost:8080/hello

- Access [metrics endpoint](http://localhost:8080/actuator/prometheus)

- Build and push Docker image:

  ```
  mvn package -Pjib
  ```

- Deploy to k8s:

  ```
  kubectl create -f k8s.yaml
  ```
