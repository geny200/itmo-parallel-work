# HW 2

## Содержание

* Домашние работы
    * [**HW1**](./../HW1/README.md)
    * **HW2**
    * [**HW3**](./../HW3/README.md)
    * [**HW4**](./../HW4/README.md)
    * [**HW5**](./../HW5/README.md)
    * [**HW6**](./../HW6/README.md)
    * [**HW7**](./../HW7/README.md)
    * [**HW8**](./../HW8/README.md)
* Практические задания
    * [**CW1**](./../CW1/README.md)

## Task 1

Доказать, что $\sum_{i=0}^{\log n} \log(n / 2^i) = \Theta(\log^2 n)$

### Док-во

$$\sum_{i=0}^{\log(n)} log(n / 2^i) = \sum_{i=0}^{\log(n)} \log(n) - \sum_{i=0}^{\log(n)} \log(2^i) = $$ $$ = \log(n) *
\sum_{i=0}^{\log(n)} 1 - \sum_{i=0}^{\log(n)} \log(2^i) = \log(n) * \log(n) - \sum_{i=0}^{\log(n)} i =$$ $$ = \log(n) *
\log(n) - (0 + \log(n)) * \log(n) / 2 =$$ $$=\log(n) * \log(n) / 2 = \Theta(\log^2 n)$$

## Task 2

Написать код алгоритма **scan** в **fork-join** модели с $\mathcal{O}(n)$ work и $\mathcal{O}(\log(n))$ span

### Scan

```python
def scan_combine(a, l, r, left, f):
    if r - l == 1:
        return

    middle = l + (r - l) // 2

    a[r - 1] = f(a[r - 1], a[middle - 1])
    a[middle - 1] = left

    fork2join(
        lambda: scan_combine(a, l, middle, left, f),
        lambda: scan_combine(a, middle, r, a[r - 1], f)
    )


def scan_tree(a, l, r, f):
    if r - l == 1:
        return

    middle = l + (r - l) // 2

    fork2join(
        lambda: scan_tree(a, l, middle, f),
        lambda: scan_tree(a, middle, r, f)
    )

    a[r - 1] = f(a[middle - 1], a[r - 1])


# Scan in place (a - array, f - function, x - first element)
def scan(a, f, x=0):
    n = len(a)

    scan_tree(a, 0, n, f)

    a[n - 1] = x
    scan_combine(a, 0, n, x, f)
    return a


# Scan not in place (a - array, f - function, x - first element)
def scan_2(a, f, x=0):
    b = a.copy()
    return scan(b, f, x)


# Example
def fork2join(f_a, f_b):
    f_a()
    f_b()


a = [5, 3, 7, -2, 2, 0, 4, -5]
scan(a, lambda a, b: a + b)
print(a)  # [0, 5, 8, 15, 13, 15, 15, 19]
```

## Task 3

Опишите алгоритм нахождения всех простых до $N$ за $\mathcal{O}(N log log N)$ work и $\mathcal{O}(polylog N)$ span (
желательно за $\mathcal{O}(log N * log log N)$ span). Тут можно пользоваться *parallel_for*, *map*, *scan* и *filter*.
Псевдокод даже необязательно.

### Решение

Описание алгоритма текстом + поясняющие вставки псевдокода (код дублирует текст).

#### Обозначения

```python
prime = [i for i in range(N)]
```

#### Цель:

Приспособить алгоритм решета Эратосфена для выполнения паралельно.

#### Идея:

##### Шаг 1.

Запустим в два вложенных параллельных цикла алгоритм нахождения решета Эратосфена, только будем идти не до $n$, а до
$\sqrt n$ в обоих циклах.

```python
# work: N #(т.е. sqrt(N)^2) 
# span: log(sqrt(N))
def parallel_prime_step_1(n, prime):
    parallel_for i in range(2, sqrt(n)):
        if prime[i] > 0:
            parallel_for j in range(i * i, sqrt(n), i):
                prime[j] = 0
```

Применим *filter* (x > 0) и получим набор простых чисел в диапазоне $0..\sqrt n$, обозначим за *set_of_primes*

##### Шаг 2.

Осталось проверить оставшиеся числа, которых $(n - \sqrt n) \approx \mathcal{O}(n)$, на простоту.

Сделаем *parallel_for* по *set_of_primes* (полученные простые числа из шага 1) и будем заполнять с помощью вложенного *
parallel_for* оставшееся решето эратосфена от $\sqrt n$ до $n$.

```python
# work: Nloglog(n) (Аналогично асимптотике последовательного решета Эратосфена)
# Span: log(n) + log(n) (parallel_for с вложенным parallel_for)
def parallel_prime_step_2(n, prime):
    # (sqrt(n) + x) // x * x - Ближайшее к sqrt(n) число делящееся на x без остатка
    parallel_for x in set_of_primes:
        parallel_for j in range((sqrt(n) + x) // x * x, n, x):
            prime[j] = 0
        
```

Применим *filter* (x > 0) и получим набор простых чисел в диапазоне $\sqrt n..n$ и объединим с простыми числами из
$0..\sqrt n$

##### Итого

* Всего
    * Шаг 1:
        * ```parallel_prime_step_1``` work: $N$ (т.е. $\sqrt N ^2$); span: $\log(\sqrt N)$;
        * ```filter``` work: $\sqrt N$; span: $\log(\sqrt N)$.
    * Шаг 2:
        * ```parallel_prime_step_2``` work: $N*\log(log(n))$; span: $\log(n) + \log(n)$;
        * ```filter``` work: $N$; span: $\log(N)$;
        * ```concat of arrays``` work: $N$; span: $1$.
* Итого:
    * work: $N*\log(log(n))$;
    * span: $\log(n)$.
