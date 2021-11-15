# HW 8

## Содержание

* Домашние работы
    * [**HW1**](./../HW1/README.md)
    * [**HW2**](./../HW2/README.md)
    * [**HW3**](./../HW3/README.md)
    * [**HW4**](./../HW4/README.md)
    * [**HW5**](./../HW5/README.md)
    * [**HW6**](./../HW6/README.md)
    * [**HW7**](./../HW7/README.md)
    * **HW8**
* Практические задания
    * [**CW1**](./../CW1/README.md)

## Task 1

Напишите MIS (maximal independent set) на Deterministic Reservations с reserve и commit
(требуется только reserve и commit).

#### Решение

```python
vertexes = []   # Список смежности         (общий для потоков)
flags = []      # Список флагов для вершин (общий для потоков)
flag            # Флаг состояния           (локальный для контекста)
from enum import Enum


class State(Enum):
    IN = 1
    OUT = 2
    LIVE = 3


def reserve(i):
    flag = State.IN
    for neighbor in vertexes[i].neighbours:
        if neighbor < i:
            if flags[neighbor] == State.IN:
                flag = State.OUT
                return 1
            elif flags[neighbor] == State.LIVE:
                flag = State.LIVE
    return 1


def commit(i):
    flags[i] = flag
    return flags[i] != State.LIVE
```

## Task 2

Напишите MST (minimum spanning tree) на Deterministic Reservations с reserve и commit (требуется только reserve и
commit).

#### Решение
Предварительно отсортируем список рёбер по весу, и применим чуть изменённый алгоритм для Spanning Forest.
```python
import numpy as np

edges = []      # Список рёбер (предварительно отсортированный по весу, общий для потоков)
v_edge = np.asarray(map(lambda edge: edge.left, edges))     # Левые вершины (общий для потоков)
u_edge = np.asarray(map(lambda edge: edge.right, edges))    # Правые вершины (общий для потоков)

R = ...         # Список резервирований вершин (reserve = CAS при условии минимальности)
find = ...      # По вершине возвращает лидера класса в disjointSet  (общий для потоков)
union = ...     # Объединяет классы эквивалентности в disjointSet (общий для потоков)

v, u = ...      # Локальные для контекста переменные


def reserve(i):
    v = find(v_edge[i])
    u = find(u_edge[i])

    if u == v:
        return 0

    R[v].reserve(i)
    R[u].reserve(i)
    return 1


def commit(i):
    if R[v].isReserved(i) and R[u].isReserved(i):
        union(v, u)
        return 1
    return 0
```

###### Источники:

[1] Guy E. Blelloch, Jeremy T. Fineman, Phillip B. Gibbons, Julian Shun Internally deterministic parallel algorithms can
be fast PPoPP, 2012.
