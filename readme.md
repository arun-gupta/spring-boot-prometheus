# Spring Boot Application with Prometheus

This repo contains a simple Spring Boot application that publishes Proemtheus-style metrics in Kubernetes

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
  mvn -f app/pom.xml package -Pjib
  ```

  Optionally, build to Docker daemon:

  ```
  mvn -f app/pom.xml jib:dockerBuild -Pjib
  ```

  Alternatively, build using `Dockerfile`:

  ```
  docker image build -t arungupta/greeting:prom .
  docker image push arungupta/greeting:prom
  ```

- Deploy to k8s:

  ```
  helm install --name myapp chart
  ```

- Access the application:

  ```
  ENDPOINT=$(kubectl get svc/myapp-greeting \
    -o jsonpath='{.status.loadBalancer.ingress[0].hostname}')
  curl http://$ENDPOINT/hello
  ```

- Access prometheus metrics at:

  ```
  curl http://$ENDPOINT/actuator/prometheus
  # HELP jvm_threads_daemon_threads The current number of live daemon threads
  # TYPE jvm_threads_daemon_threads gauge
  jvm_threads_daemon_threads 16.0
  # HELP http_server_requests_seconds  
  # TYPE http_server_requests_seconds summary
  http_server_requests_seconds_count{exception="None",method="GET",outcome="SUCCESS",status="200",uri="/hello",} 2.0
  http_server_requests_seconds_sum{exception="None",method="GET",outcome="SUCCESS",status="200",uri="/hello",} 0.040638166
  http_server_requests_seconds_count{exception="None",method="GET",outcome="CLIENT_ERROR",status="404",uri="/**",} 2.0
  http_server_requests_seconds_sum{exception="None",method="GET",outcome="CLIENT_ERROR",status="404",uri="/**",} 0.01150999
  # HELP http_server_requests_seconds_max  
  # TYPE http_server_requests_seconds_max gauge
  http_server_requests_seconds_max{exception="None",method="GET",outcome="SUCCESS",status="200",uri="/hello",} 0.003151873
  http_server_requests_seconds_max{exception="None",method="GET",outcome="CLIENT_ERROR",status="404",uri="/**",} 0.006369922
  # HELP tomcat_global_received_bytes_total  
  # TYPE tomcat_global_received_bytes_total counter
  tomcat_global_received_bytes_total{name="http-nio-8080",} 0.0
  # HELP jvm_threads_live_threads The current number of live threads including both daemon and non-daemon threads
  # TYPE jvm_threads_live_threads gauge
  jvm_threads_live_threads 20.0
  # HELP tomcat_sessions_alive_max_seconds  
  # TYPE tomcat_sessions_alive_max_seconds gauge
  tomcat_sessions_alive_max_seconds 0.0
  # HELP jvm_gc_memory_promoted_bytes_total Count of positive increases in the size of the old generation memory pool before GC to after GC
  # TYPE jvm_gc_memory_promoted_bytes_total counter
  jvm_gc_memory_promoted_bytes_total 1.2372152E7
  # HELP tomcat_global_request_max_seconds  
  # TYPE tomcat_global_request_max_seconds gauge
  tomcat_global_request_max_seconds{name="http-nio-8080",} 0.065
  # HELP logback_events_total Number of error level events that made it to the logs
  # TYPE logback_events_total counter
  logback_events_total{level="warn",} 0.0
  logback_events_total{level="debug",} 0.0
  logback_events_total{level="error",} 0.0
  logback_events_total{level="trace",} 0.0
  logback_events_total{level="info",} 7.0
  # HELP tomcat_sessions_active_max_sessions  
  # TYPE tomcat_sessions_active_max_sessions gauge
  tomcat_sessions_active_max_sessions 0.0
  # HELP process_files_max_files The maximum file descriptor count
  # TYPE process_files_max_files gauge
  process_files_max_files 65536.0
  # HELP jvm_memory_used_bytes The amount of used memory
  # TYPE jvm_memory_used_bytes gauge
  jvm_memory_used_bytes{area="heap",id="Tenured Gen",} 2.7163832E7
  jvm_memory_used_bytes{area="heap",id="Eden Space",} 6.8747016E7
  jvm_memory_used_bytes{area="nonheap",id="Metaspace",} 3.9226672E7
  jvm_memory_used_bytes{area="nonheap",id="Code Cache",} 1.072608E7
  jvm_memory_used_bytes{area="heap",id="Survivor Space",} 0.0
  jvm_memory_used_bytes{area="nonheap",id="Compressed Class Space",} 4998160.0
  # HELP jvm_classes_unloaded_classes_total The total number of classes unloaded since the Java virtual machine has started execution
  # TYPE jvm_classes_unloaded_classes_total counter
  jvm_classes_unloaded_classes_total 1.0
  # HELP process_files_open_files The open file descriptor count
  # TYPE process_files_open_files gauge
  process_files_open_files 81.0
  # HELP tomcat_sessions_expired_sessions_total  
  # TYPE tomcat_sessions_expired_sessions_total counter
  tomcat_sessions_expired_sessions_total 0.0
  # HELP process_start_time_seconds Start time of the process since unix epoch.
  # TYPE process_start_time_seconds gauge
  process_start_time_seconds 1.561743338405E9
  # HELP jvm_buffer_memory_used_bytes An estimate of the memory that the Java virtual machine is using for this buffer pool
  # TYPE jvm_buffer_memory_used_bytes gauge
  jvm_buffer_memory_used_bytes{id="direct",} 90112.0
  jvm_buffer_memory_used_bytes{id="mapped",} 0.0
  # HELP jvm_memory_max_bytes The maximum amount of memory in bytes that can be used for memory management
  # TYPE jvm_memory_max_bytes gauge
  jvm_memory_max_bytes{area="heap",id="Tenured Gen",} 5.48339712E9
  jvm_memory_max_bytes{area="heap",id="Eden Space",} 2.193358848E9
  jvm_memory_max_bytes{area="nonheap",id="Metaspace",} -1.0
  jvm_memory_max_bytes{area="nonheap",id="Code Cache",} 2.5165824E8
  jvm_memory_max_bytes{area="heap",id="Survivor Space",} 2.74137088E8
  jvm_memory_max_bytes{area="nonheap",id="Compressed Class Space",} 1.073741824E9
  # HELP tomcat_threads_current_threads  
  # TYPE tomcat_threads_current_threads gauge
  tomcat_threads_current_threads{name="http-nio-8080",} 10.0
  # HELP tomcat_threads_busy_threads  
  # TYPE tomcat_threads_busy_threads gauge
  tomcat_threads_busy_threads{name="http-nio-8080",} 1.0
  # HELP jvm_gc_memory_allocated_bytes_total Incremented for an increase in the size of the young generation memory pool after one GC to before the next
  # TYPE jvm_gc_memory_allocated_bytes_total counter
  jvm_gc_memory_allocated_bytes_total 6.3571608E7
  # HELP jvm_classes_loaded_classes The number of classes that are currently loaded in the Java virtual machine
  # TYPE jvm_classes_loaded_classes gauge
  jvm_classes_loaded_classes 7621.0
  # HELP tomcat_sessions_active_current_sessions  
  # TYPE tomcat_sessions_active_current_sessions gauge
  tomcat_sessions_active_current_sessions 0.0
  # HELP process_uptime_seconds The uptime of the Java virtual machine
  # TYPE process_uptime_seconds gauge
  process_uptime_seconds 440.611
  # HELP jvm_gc_live_data_size_bytes Size of old generation memory pool after a full GC
  # TYPE jvm_gc_live_data_size_bytes gauge
  jvm_gc_live_data_size_bytes 2.7163832E7
  # HELP process_cpu_usage The "recent cpu usage" for the Java Virtual Machine process
  # TYPE process_cpu_usage gauge
  process_cpu_usage 7.506521012208091E-6
  # HELP tomcat_global_error_total  
  # TYPE tomcat_global_error_total counter
  tomcat_global_error_total{name="http-nio-8080",} 2.0
  # HELP jvm_gc_pause_seconds Time spent in GC pause
  # TYPE jvm_gc_pause_seconds summary
  jvm_gc_pause_seconds_count{action="end of major GC",cause="Metadata GC Threshold",} 1.0
  jvm_gc_pause_seconds_sum{action="end of major GC",cause="Metadata GC Threshold",} 0.065
  # HELP jvm_gc_pause_seconds_max Time spent in GC pause
  # TYPE jvm_gc_pause_seconds_max gauge
  jvm_gc_pause_seconds_max{action="end of major GC",cause="Metadata GC Threshold",} 0.0
  # HELP tomcat_sessions_rejected_sessions_total  
  # TYPE tomcat_sessions_rejected_sessions_total counter
  tomcat_sessions_rejected_sessions_total 0.0
  # HELP jvm_threads_peak_threads The peak live thread count since the Java virtual machine started or peak was reset
  # TYPE jvm_threads_peak_threads gauge
  jvm_threads_peak_threads 20.0
  # HELP system_cpu_count The number of processors available to the Java virtual machine
  # TYPE system_cpu_count gauge
  system_cpu_count 1.0
  # HELP system_load_average_1m The sum of the number of runnable entities queued to available processors and the number of runnable entities running on the available processors averaged over a period of time
  # TYPE system_load_average_1m gauge
  system_load_average_1m 0.01
  # HELP tomcat_sessions_created_sessions_total  
  # TYPE tomcat_sessions_created_sessions_total counter
  tomcat_sessions_created_sessions_total 0.0
  # HELP jvm_memory_committed_bytes The amount of memory in bytes that is committed for the Java virtual machine to use
  # TYPE jvm_memory_committed_bytes gauge
  jvm_memory_committed_bytes{area="heap",id="Tenured Gen",} 3.43932928E8
  jvm_memory_committed_bytes{area="heap",id="Eden Space",} 1.37691136E8
  jvm_memory_committed_bytes{area="nonheap",id="Metaspace",} 4.1680896E7
  jvm_memory_committed_bytes{area="nonheap",id="Code Cache",} 1.1862016E7
  jvm_memory_committed_bytes{area="heap",id="Survivor Space",} 1.7170432E7
  jvm_memory_committed_bytes{area="nonheap",id="Compressed Class Space",} 5505024.0
  # HELP jvm_threads_states_threads The current number of threads having NEW state
  # TYPE jvm_threads_states_threads gauge
  jvm_threads_states_threads{state="runnable",} 6.0
  jvm_threads_states_threads{state="blocked",} 0.0
  jvm_threads_states_threads{state="waiting",} 12.0
  jvm_threads_states_threads{state="timed-waiting",} 2.0
  jvm_threads_states_threads{state="new",} 0.0
  jvm_threads_states_threads{state="terminated",} 0.0
  # HELP system_cpu_usage The "recent cpu usage" for the whole system
  # TYPE system_cpu_usage gauge
  system_cpu_usage 0.004398821266581523
  # HELP jvm_gc_max_data_size_bytes Max size of old generation memory pool
  # TYPE jvm_gc_max_data_size_bytes gauge
  jvm_gc_max_data_size_bytes 5.48339712E9
  # HELP jvm_buffer_total_capacity_bytes An estimate of the total capacity of the buffers in this pool
  # TYPE jvm_buffer_total_capacity_bytes gauge
  jvm_buffer_total_capacity_bytes{id="direct",} 90112.0
  jvm_buffer_total_capacity_bytes{id="mapped",} 0.0
  # HELP tomcat_threads_config_max_threads  
  # TYPE tomcat_threads_config_max_threads gauge
  tomcat_threads_config_max_threads{name="http-nio-8080",} 200.0
  # HELP jvm_buffer_count_buffers An estimate of the number of buffers in the pool
  # TYPE jvm_buffer_count_buffers gauge
  jvm_buffer_count_buffers{id="direct",} 11.0
  jvm_buffer_count_buffers{id="mapped",} 0.0
  # HELP tomcat_global_sent_bytes_total  
  # TYPE tomcat_global_sent_bytes_total counter
  tomcat_global_sent_bytes_total{name="http-nio-8080",} 314.0
  # HELP tomcat_global_request_seconds  
  # TYPE tomcat_global_request_seconds summary
  tomcat_global_request_seconds_count{name="http-nio-8080",} 4.0
  tomcat_global_request_seconds_sum{name="http-nio-8080",} 0.123
  ```
