# The purpose of the project

Recentely, I am studying the [Algorithm from the University of Princeton](https://www.coursera.org/learn/algorithms-part1/home/welcome). 

There are some assignments for the course and here are my solutions. 

Note that the repo is only for reference and inspiration. Please do not use for the cheating.


# The Score

algorithm | score
--- | ---- 
Hello world | 93 / 100
Percolation | 98 / 100
Deque | 94 / 100
Collinear | 94 / 100


# Assignments
## Percolation
* [Reference](https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php)

### Notice that:
* The corner case:
    * the argument must be positive integer
    * the open site must be inside the n*n grid
    * the union site must be inside the n*n grid
    * throw new IllegalArgumentException
* The repetitive open sites
    * check whether a site is open or not
* The [backwash](https://introcs.cs.princeton.edu/java/assignments/checklist/percolation.html) scenario
    * Using two array to store the site
        * one array stores all sites
        * the other array stores the sites other than the bottom site
* The test case from the Coursera assumes that the row and col starts from 1 to n;

## Queue

### Unit Testing
#### Reference:

* Test Cases
    * [Unit tests for priority queue](http://cda.morris.umn.edu/~elenam/2101Spring2016/examples/unit_tests_pq.html)
    * [Queue Tests](https://sites.cs.ucsb.edu/~cappello/56/code/L9/QueueTest.html)

To be honest, the implementation of Deque(deck) is quite easy, but the unit test is quite difficult.

To test each method, we should use [Junit 4](https://junit.org/junit4/) or [Junit 5](https://junit.org/junit5/)

#### Notice that:
* Since we override the iterator in the Queue, so we can use the for loop for
  ```
    for (<Generic> Item: Queue) {}
  ```
* In iterator method, the `next` must return the current value, the `hasNext` must return the next value
* The method `dequeue` of `random queue` means that we randomly choose an item to remove it, and we fill the empty one with the last item.


## Collinear

## BruteCollinearPoints
### Notice that:
* For the `BruteCollinear`, there is a mention in the doc that `For simplicity, we will not supply any input to BruteCollinearPoints that has 5 or more collinear points`.
Therefore, we can use 4 loops to iterator the points and compare the slopes of two points. Besides, as the `brute` name suggested, we can totally use 4 loops in the code.
* For the `BruteCollinear`, the test code is in the [Sample client](https://coursera.cs.princeton.edu/algs4/assignments/collinear/specification.php) section. 

## FastCollinearPoints
* Using `do-while` for the time when we must execute the logic code first then check the condition.
* Using our customize `comparator` to sort the points.
* the `compareTo` method in the class `Point` is the invoking point comparing to the argument point. It is invoked when we sort the array. Therefore, the points sort according to their position.
* In the array of backupPoints, we must check that if the p1 is less than the last point.








