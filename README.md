# The purpose of the project

Recentely, I am studying the [Algorithm from the University of Princeton](https://www.coursera.org/learn/algorithms-part1/home/welcome). 

There are some assignments for the course and here are my solutions. 

Note that the repo is only for reference and inspiration. Please do not use for the cheating.


# The Score

algorithm | score
--- | ---- 
Hello world | 93 / 100
Percolation | 98 / 100


# Assignment
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
* The backwash scenario
    * Using two array to store the site
        * one array stores all sites
        * the other array stores the sites other than the bottom site
* The test case from the Coursera assumes that the row and col starts from 1 to n;

    
    
    







