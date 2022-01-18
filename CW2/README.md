### Запуск

* ```BenchPar``` - запуск параллельного bfs (запускать с параметрами jvm ```-XX:ActiveProcessorCount=4 -XX:+UseParallelGC -Xms4096m -Xmx4096m```)
* ```BenchSeq``` - запуск последовательного bfs (запускать с параметрами jvm ```-XX:ActiveProcessorCount=1 -XX:+UseParallelGC -Xms4096m -Xmx4096m```)

### Результаты запусков

Запуск на кубическом графе, со стороной куба - 200 вершин.

```
Seq: run 5 times; total - 151.8547612s;	avg - 30.37095224 s
Par: run 5 times; total - 49.3806109s;	avg - 9.87612218 s
```