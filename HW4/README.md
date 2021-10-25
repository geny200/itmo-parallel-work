## HW 4

## Содержание

* Домашние работы
    * [**HW1**](./../HW1/README.md)
    * [**HW2**](./../HW2/README.md)
    * [**HW3**](./../HW3/README.md)
    * **HW4**
    * HW5 (not implemented)
* Практические задания
    * [**CW1**](./../CW1/README.md)

## Task 1

Вам дано n пар (k_i, v_i). Известно, что 0 <= k_i < log n. Отсортируйте пары по ключу за O(n) work и O(log^2 n) span.

#### Идея

Параллельная сортировка подсчётом + Accelerating Cascades

#### Алгоритм

###### c++

Рассмотрим алгоритм сортировки подсчётом

```c++
// inputArray - исходный массив значений
// inputOrderArray - исходный порядок значений
// n - size
	for (size_t i = 0; i != n; ++i)
		countArray[inputArray[i] + 1] += 1;
		
	for (size_t i = 1; i != log(n); ++i)
		countArray[i] += countArray[i - 1];
		
	for (size_t i = 0; i != n; ++i)
		resultArray[countArray[inputArray[inputOrderArray[i]]]++] = inputOrderArray[i];
```

###### Описание

Сделаем его параллельным - рассмотрим как распараллеливать каждый for:

1. Подсчёт кол-ва значений:
    * Для каждого значения от 0 до log(n) сделаем pfor (если элемент равен искомому, то возвратим 1 иначе 0) по
      результату pfor сделаем reduce (конкретнее - sum).
2. Подсчёт префиксов:
    * Сделаем scan по массиву счётчиков.
3. Расставление значений:
    * Для каждого значения от 0 до log(n) сделаем filter по ключу, и положим значения с помощью pfor со сдвигом в
      соответствии с номером в массиве счётчиков

###### Псевдокод

```
// inputArray - исходный массив значений
// resultArray - результат отсортированный массив
// n - size

	pfor i from 0 to log(n):
	    flagAarray = bool[n];
	    pfor j from 0 until n:
	        if (inputArray[j].fst == i)
	    countArray[i] = sum(flagArray);
		
	scan(countArray)
	
	pfor i from 0 to log(n):
	    filterArray = filter(lambda x: x.fst == i, inputArray)
	    pfor j from 0 to filterArray.size:
	        resultArray[countArray[i] + j] = filterArray[j]
```

#### Оценки

1. Вложенный pfor:
    * work O(nlogn);
    * span O(logn + logn);
2. Scan:
    * work O(n);
    * span O(logn);
3. Вложенный фильтр + pfor:
    * work O(nlogn);
    * span O(logn + logn).

Всё плохо, т.к. требуется work O(n), ан нет, давайте применим Accelerating Cascades, и уменьшим work.

Разобъём на блоки по logn и в них выполним для 1 и 3 пункт, получая work O(n) и span O(logn * (logn + logn))

##### Итого:

* work O(n);
* span O(log^2(n)).

## Task 2

Дано подвешенное дерево за вершину r с уже построенным Эйлеровым обходом. Нужно для каждой вершины найти её глубину в
дереве за O(n) work и O(polylog n) span.

#### Идея

Назначаем веса рёбрам +1 из предка в ребёнка, и -1 обратно.

#### Алгоритм

Разорвём эйлеровый цикл дерева в подвешенной вершине (получим путь). Назначаем веса рёбрам +1 из предка в ребёнка, и -1
обратное. Найдём расстояния, глубина вершины и есть найденное расстояние (т.к. при каждом спуске вниз как в dfs делаем
+1, при подъеме -1).

###### По шагам

1. Разорвём цикл в корне (если изначально не цикл, а путь, пропускаем шаг);
2. Найдём предков для вершин с помощью list ranking (было на лекции);
3. Назначим каждому ребру:
    * +1 если оно из предка в ребёнка;
    * -1 если оно из ребёнка в предка;
4. Найдём расстояния с помощью list ranking (было на лекции);
5. Найденное расстояние является глубиной вершины.

##### Оценки

1. Алгоритм использует константное число раз list ranking от 2n:
    * work O(n);
    * span O(log^2n * loglogn);
2. Назначить +1/-1 можно pfor:
    * work O(n);
    * span O(log(n)).

Итого:

* work O(n);
* span O(log^2n * loglogn).