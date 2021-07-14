# The purpose of the project

Recentely, I am studying the [Algorithm from the University of Princeton](https://www.coursera.org/learn/algorithms-part1/home/welcome). 

There are some assignments for the course and here are my solutions. 

Note that the repo is only for reference and inspiration. Please do not use for the cheating.


# The Score

algorithm | score
--- | ---- 
Hello world | 93 / 100
Percolation | 98 / 100


# Assignments
## Percolation
* [Reference](https://coursera.cs.princeton.edu/algs4/assignments/percolation/specification.php)

### Something We must notice that:
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

#### Something we must notice that:
* Since we override the iterator in the Queue, so we can use the for loop for
  ```
    for (<Generic> Item: Queue) {}
  ```
* In iterator method, the `next` must return the current value, the `hasNext` must return the next value
* The method `dequeue` of `random queue` means that we randomly choose an item to remove it, and we fill the empty one with the last item.




    
    
    







