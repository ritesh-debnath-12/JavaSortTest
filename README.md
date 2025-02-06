# JavaSortTest

Performance analysis of Sorting Algorithms in both Singlethreaded Environment and Multithreaded Environment powered by Java

## Prerequisites
You will be needing a JDK, [I personally used this](https://github.com/adoptium/temurin21-binaries/releases/tag/jdk-21.0.6%2B7), and a text editor, I used [VSCode](https://code.visualstudio.com/), you can use Notepad or Vim or anything that pleases your itch. <br>

Feel free to use IDEs like [IntelliJ](https://www.jetbrains.com/idea/) and [Eclipse](https://eclipseide.org/) instead of text editors.<br>

If you are still having problems, watch a YouTube video or something, [this can be a good start](https://www.youtube.com/watch?v=SQykK40fFds).

## How to Run
[Clone this repo to your computer](https://docs.github.com/en/repositories/creating-and-managing-repositories/cloning-a-repository), and [change directories](https://www.wikihow.com/Change-Directories-in-Command-Prompt) to whichever method you would like to test out(Multithreaded/ or Singlethreaded/)<br>
Both the directories are Java projects on their own, so you need to open your text editor or IDE in their root contexts(i.e Singlethreaded/ or Mulithreaded/). Being in the context of JavaSortTest will **not** work!<br>
You can try cd, or if you are using VSCode or IntelliJ, you can just right-click within Singlethreaded(or the other), and choose "Open with Code" or "Open with IntelliJ", if its not present, well, you can directly open powershell or cmd from the explorer path.<br>

PS: You should really include [VSCode](https://www.youtube.com/watch?v=4qspErFtm00)/[IntelliJ](https://intellij-support.jetbrains.com/hc/en-us/community/posts/360005182840/comments/10494930377618)/etc in your file context, if its not present. 

## Index
1. Singlethreaded
   - [genFile.java](Singlethreaded/src/genFile.java)
   - [utils.java](Singlethreaded/src/utils.java)
   - [bubble_sort.java](Singlethreaded/src/bubble_sort.java)
   - [insertion_sort.java](Singlethreaded/src/insertion_sort.java)
   - [merge_sort.java](Singlethreaded/src/merge_sort.java)
   - [quick_sort.java](Singlethreaded/src/quick_sort.java)

2. Multithreaded
   - [genFile.java](Multithreaded/src/genFile.java)
   - [utils.java](Multithreaded/src/utils.java)
   - [bubble_sort.java](Multithreaded/src/bubble_sort.java)
   - [insertion_sort.java](Singlethreaded/src/insertion_sort.java)
   - [merge_sort.java](Multithreaded/src/merge_sort.java)
   - [quick_sort.java](Multithreaded/src/quick_sort.java)

## Introduction

Sorting is a fundamental operation used extensively in various applications(academic, research, industrial, commerical, or day-to-day life). The efficieny of sorting algorithms is as such, should be maintained in high regard, particularly when maintaining and processing normally/abnormally large datasets.

## Problem Statement

The objective of this study is to compare the execution times of different sorting algorithms on large datasets.<br>
This report presents an empirical analysis of four sorting algorithm.

- Bubble Sort
- Insertion Sort
- Merge Sort
- Quick Sort

## Foreword

While not the initial objective presented to me, I have taken the liberty to write a multithreaded approach to the problem, using ExecutorService and Executor(provided within java.util.concurrent package) and BufferedWriter(provided within java.io package).<br>

To also fulfill my orginal report objective I have also provided a folder with singlethreaded sequential approach as well.

## Vision

- To prove, that while naive algorithms work in small datasets, mathematically proven algorithms perform significantly well in almost all approaches
- To prove, that mathematical model of Time Complexity holds when tested in a brute force manner

## Implemention Details

### Data Generation

The genFile.java class was developed to generate 10 input files, each containing 1e6 arrays of arbitrary length with unsorted elements. These input files were stored in the **input** directory.

As of 06/02/25 genFile.java also handles automatic directory generation!

### Parsing Input Data

The utils.java class was responsible for reading and parsing the input arrays dynamically using ArrayLists, allowing for efficient data handling before passing the arrays to the respective sorting functions.

### Sorting Algorithms

Each sorting algorithm was implemented in separate Java file:

- Bubble Sort(non flag version)
- Insertion Sort
- Merge Sort
- Quick Sort(last element pivot)

The sorted arrays were written to respective output directories (**output/{sorting function}/**) in separate files.

## Experimental Setup

The tests were done locally on a laptop with Ryzen 7 7435HS CPU(8 physical cores and 16 logical cores) and 16 GB of DDR5 main memory.

## Results

The execution times(in seconds) for sorting the input files are as follows:
| Algorithm| Single-threaded(1 File)| Multithreaded(10 Files, 16 Threads)|
|:-------------:|:-------------:|:-------------:|
|Merge Sort(θ(n log n))|487|30.01|
|Quick Sort(θ(n log n))|490|32.29|
|Insertion Sort(θ(n^2))|520|39.42|
|Bubble Sort(θ(n^2))|539|37.62|

## Observation

- Merge Sort was the fastest algorithm. Probably due to it being suited for larger datasets.

- Quick Sort also exhibited strong performance, albeit slightly slower than Merge Sort. This might be due to implementation and/or its worst case being O(n^2)

- Insertion Sort and Bubble Sort were significantly slower in comparison, due to their higher time complexity (O(n^2)).

## Conclusion

Quick Sort and Merge Sort performed best due to their divide-and-conquer strategies, whereas Bubble Sort and Insertion Sort struggled with large datasets. Future improvements could include parallelized merge sort or optimized data structures to further enhance sorting efficiency. The implementation of sorting algorithms themselves can be optimized(by using flag bubble sort and randomized pivot quick sort).

## References
- [java.util.concurrent.ExecutorService](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ExecutorService.html) -> Oracle docs
- [java.util.concurrent.Executor](https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/Executors.html) -> Oracle docs
- [java.lang.Runtime](https://docs.oracle.com/javase/8/docs/api/java/lang/Runtime.html) -> Oracle docs
- [Working of Garbage Collector in Java](https://www.geeksforgeeks.org/garbage-collection-java/) -> Geeks for Geeks
- [java.io.File](https://docs.oracle.com/javase/8/docs/api/java/io/File.html) -> Oracle docs
- [java.io.FileWriter](https://docs.oracle.com/javase/8/docs/api/java/io/FileWriter.html) -> Oracle docs
- [java.io.BufferedWriter](https://docs.oracle.com/javase/8/docs/api/java/io/BufferedWriter.html) -> Oracle docs