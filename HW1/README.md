# HW 1

## Содержание

* Домашние работы
    * **HW1**
    * [**HW2**](./../HW2/README.md)
    * [**HW3**](./../HW3/README.md)
    * [**HW4**](./../HW4/README.md)
    * [**HW5**](./../HW5/README.md)
    * [**HW6**](./../HW6/README.md)
* Практические задания
    * [**CW1**](./../CW1/README.md)

## Task 1

Значение *X* хранится где-то в памяти *EREW PRAM*.

Покажите, как скопировать *X* в каждую ячейку массива длины *p* в *EREW PRAM* с *p* процессами за *O(log p)* time.

Определите, за сколько можно сделать тоже самое в CREW и CRCW PRAM.

### Обозначения:

```
p - Количество процессов
i - Номер процесса (нумерация с 0)
set(A, x) - Функция исполняемая каждым процессом
```

### EREW

Дано:

* X в *EREW PRAM*;
* Массив A в *EREW PRAM*.

```python
def set(A, x):
    if i == 0:
        A[0] <- x
    else:
        skip(1)
        
    for h in range(log_2(p)):
        if i % 2 == 0:
            y <- A[i / 2]
            skip(1)
        else:
            skip(1)
            y <- A[i / 2]
        A[i] <- y
```

*time: O(log(p))*

### CREW

Дано:

* X в *EREW PRAM*;
* Массив A в *CREW PRAM*.

```python
def set(A, x):
    if i == 0:
        A[0] = x
        skip(1)
    else:
        skip(1)
        A[i] <- A[0]
```

*time: O(1)*

### CRCW

Дано:

* X в *EREW PRAM*;
* Массив A в *CRCW PRAM*.

```python
def set(A, x):
    if i == 0:
        A[0] <- x
    else:
        skip(1)
    A[i] <- A[0]
```

*time: O(1)*

## Task 2

Докажите, что *level-by-level* шедулер работает не хуже, чем в 2 раза от оптимального.

### Обозначения:

```
T - time
S - span
W - work
P - количество процессов
```

### Time for *level-by-level* scheduler

```T < W / P + S```

### Time for Optimal scheduler

```T >= W / P```

### Рассуждения (Док-во)

    Оценим span как S <= W т.к. на каждом уровне как минимум 1 w
    Тогда перепишем нер-во для lvl-by-lvl как 

    T < W / P + S < W / P + W
    T < W / P + W

    Теперь покажем, что оценка сверху меньше, чем оптимальное время умноженное на 2 

    (W / P + W) / 2 ? W / P
    W / P + W  ? 2 * W / P 
    W ? W/P
    1 ? P

    Тогда для P >= 1 верно, что lvl-by-lvl scheduler работает не хуже, 
    чем в 2 раза от оптимального, чтд.