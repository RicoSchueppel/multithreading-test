A simple demo app to demonstrate multithreading with ExecutorService.
The additional cost for 'much' more threads than CPUs impacts at about 100 threads.
Anyway, its surprising to me to still benefit from more threads if we have 8 CPUs and 50 threads. 

start with `mvn spring-boot:run`

Example on 8 CPUs 

Pool size 1 : 1306 ms
Pool size 1 : 514 ms
Pool size 2 : 149 ms
Pool size 4 : 66 ms
Pool size 6 : 43 ms
Pool size 8 : 33 ms
Pool size 9 : 29 ms
Pool size 20 : 14 ms
Pool size 50 : 8 ms
Pool size 100 : 9 ms
Pool size 200 : 16 ms
Pool size 500 : 44 ms
Pool size 1000 : 83 ms




