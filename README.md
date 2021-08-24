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
8 Puzzler | 94 / 100
Kdtree | 92 / 100
wordnet | 97 / 100


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

## 8 Puzzler
* The Hamming priority is the Hamming distance of a board plus the number of moves made so far to get to the search node. Intuitively, a search node with a small number of tiles in the wrong position is close to the goal, and we prefer a search node if has been reached using a small number of moves.
* The Manhattan priority is the Manhattan distance of a board plus the number of moves made so far to get to the search node.
* Priority queue

## Kdtree
***note that***: Plz read meticulously about the specification, it virtually gives a hit about how to implement the kdtree.
* Range search. To find all points contained in a given query rectangle, start at the root and recursively search for points in both subtrees using the following pruning rule: if the query rectangle does not intersect the rectangle corresponding to a node, there is no need to explore that node (or its subtrees). A subtree is searched only if it might contain a point contained in the query rectangle.
* Nearest-neighbor search. To find a closest point to a given query point, start at the root and recursively search in both subtrees using the following pruning rule: if the closest point discovered so far is closer than the distance between the query point and the rectangle corresponding to a node, there is no need to explore that node (or its subtrees). That is, search a node only only if it might contain a point that is closer than the best one found so far. The effectiveness of the pruning rule depends on quickly finding a nearby point. To do this, organize the recursive method so that when there are two possible subtrees to go down, you always choose the subtree that is on the same side of the splitting line as the query point as the first subtree to explore—the closest point found while exploring the first subtree may enable pruning of the second subtree.


