# CW1

## Содержание

* Домашние работы
    * [**HW1**](./../HW1/README.md)
    * [**HW2**](./../HW2/README.md)
    * [**HW3**](./../HW3/README.md)
    * [**HW4**](./../HW4/README.md)
    * [**HW5**](./../HW5/README.md)
* Практические задания
    * **CW1**
        * **Условие**
        * [**Реализация**](.src/)
            * [**Линейная реализация quicksort (in-place)**](./src/Quicksort/Line.hs)
            * [**Параллельная реализация quicksort**](./src/Quicksort/Parallel.hs)
        * [**Тесты**](./test/Spec.hs)

## Условие

Нужно реализовать *quicksort*. От Вас требуется написать последовательную версию алгоритма  (seq) и параллельную
версию (
par). Взять случайный массив из 10^8 элементов и отсортировать. (Усреднить по 5 запускам) Сравнить время работы par на 4
процессах и seq на одном процессе

### Запуск

#### Run

```
stack run
```

#### Тесты

```
stack test
```

#### Benchmark

Тестировалось на рандомном списке длины 10^7 т.к. для 10^8 бенчмарк занимал кучу времени и памяти.

```
stack bench
```

##### Вывод

```
Running 2 benchmarks...
Benchmark line-itmo-parallel-work-bench: RUNNING...
```

###### На одном процессе

```
benchmarking Linear quick sort /10^7
time                 9.413 s    (9.160 s .. 9.904 s)
                     1.000 R²   (0.999 R² .. 1.000 R²)
mean                 9.216 s    (9.159 s .. 9.319 s)
std dev              99.33 ms   (732.0 μs .. 119.2 ms)
variance introduced by outliers: 19% (moderately inflated)

benchmarking std sort/10^7
time                 852.5 ms   (564.0 ms .. 1.072 s)
                     0.987 R²   (0.954 R² .. 1.000 R²)
mean                 716.2 ms   (537.7 ms .. 782.9 ms)
std dev              124.4 ms   (3.956 ms .. 154.2 ms)
variance introduced by outliers: 47% (moderately inflated)

Benchmark line-itmo-parallel-work-bench: FINISH

```

###### На четырёх процессах

```
Benchmark par-itmo-parallel-work-bench: RUNNING...

benchmarking Parallel quick sort /10^7
time                 2.204 s    (1.879 s .. 2.646 s)
                     0.996 R²   (0.986 R² .. 1.000 R²)
mean                 1.841 s    (1.630 s .. 1.970 s)
std dev              215.1 ms   (96.79 ms .. 299.5 ms)
variance introduced by outliers: 23% (moderately inflated)

Benchmark par-itmo-parallel-work-bench: FINISH
```