# 🏠 Airbnb Interview Questions

<p align="center">
  <img src="https://img.shields.io/badge/Airbnb-Interview%20Preparation-FF5A5F?style=for-the-badge&logo=airbnb&logoColor=white" alt="Airbnb Interview Preparation">
  <img src="https://img.shields.io/badge/Java-11%2B-orange?style=for-the-badge&logo=openjdk&logoColor=white" alt="Java 11+">
  <img src="https://img.shields.io/badge/Gradle-5.6%2B-02303A?style=for-the-badge&logo=gradle&logoColor=white" alt="Gradle">
  <img src="https://img.shields.io/badge/Questions-31-blue?style=for-the-badge" alt="31 Questions">
</p>

<p align="center">
  <b>A collection of programming and problem-solving questions reported from Airbnb interview preparation resources.</b>
</p>

<p align="center">
  <a href="#-question-list">Questions</a> •
  <a href="#-requirements">Requirements</a> •
  <a href="#-run-unit-tests">Testing</a> •
  <a href="#-contributing">Contributing</a> •
  <a href="#-update-logs">Update Logs</a>
</p>

---

## ⚠️ Disclaimer

All questions below are collected from Internet, which include but not limit to:

- [GeeksForGeeks](http://www.geeksforgeeks.org/company-preparation/)
- [GlassDoor](https://www.glassdoor.com/Interview/san-francisco-airbnb-interview-questions-SRCH_IL.0,13_IC1147401_KE14,20.htm)

> **Note:**  
> From what I know:
>
> - AirBnB is not hiring during this coronavirus pandemic time.
> - airBnB likely will change all interview questions after they start to hire on 2021.
>
> This repository is no longer updated.

---

## 📚 Question List

This repository contains **31 programming and system/problem-solving questions**, covering topics such as:

| # | Problem | Main Concept |
|---:|---|---|
| 01 | [Collatz Conjecture](#1-collatz-conjecture) | Simulation / Math |
| 02 | [Implement Queue with Limited Size of Array](#2-implement-queue-with-limited-size-of-array) | Queue / Data Structure |
| 03 | [List of List Iterator](#3-list-of-list-iterator) | Iterator |
| 04 | [Display Page (Pagination)](#4-display-page-pagination) | Pagination / Greedy |
| 05 | [Travel Buddy](#5-travel-buddy) | Sets / Similarity |
| 06 | [File System](#6-file-system) | Tree / Design |
| 07 | [Palindrome Pairs](#7-palindrome-pairs) | Strings / Hashing |
| 08 | [Find Median in Large Integer File](#8-find-median-in-large-integer-file-of-integers) | Algorithms / External Data |
| 09 | [IP Range to CIDR](#9-ip-range-to-cidr) | Networking / Bit Manipulation |
| 10 | [CSV Parser](#10-csv-parser) | Parsing |
| 11 | [Text Justification](#11-text-justification) | Strings / Greedy |
| 12 | [Regular Expression](#12-regular-expression) | Parsing / Recursion |
| 13 | [Water Drop / Water Land / Pour Water](#13-water-dropwater-landpour-water) | Simulation |
| 14 | [Hilbert Curve](#14-hilbert-curve) | Recursion / Geometry |
| 15 | [Simulate Diplomacy](#15-simulate-diplomacy) | Simulation / Design |
| 16 | [Meeting Time](#16-meeting-time) | Intervals |
| 17 | [Round Prices](#17-round-prices) | Greedy / Math |
| 18 | [Sliding Game](#18-sliding-game) | BFS / State Search |
| 19 | [Maximum Number of Nights You Can Accommodate](#19-maximum-number-of-nights-you-can-accommodate) | Dynamic Programming |
| 20 | [Find Case Combinations of a String](#20-find-case-combinations-of-a-string) | Backtracking |
| 21 | [Menu Combination Sum](#21-menu-combination-sum) | Backtracking / N-Sum |
| 22 | [K Edit Distance](#22-k-edit-distance) | Dynamic Programming |
| 23 | [Boggle Game](#23-boggle-game) | DFS / Backtracking |
| 24 | [Minimum Cost with At Most K Stops](#24-minimum-cost-with-at-most-k-stops) | Graph / Dynamic Programming |
| 25 | [String Pyramids Transition Matrix](#25-string-pyramids-transition-matrix) | Dynamic Programming |
| 26 | [Finding Ocean](#26-finding-ocean) | DFS / Flood Fill |
| 27 | [Preference List](#27-preference-list) | Topological Sorting |
| 28 | [Minimum Vertices to Traverse Directed Graph](#28-minimum-vertices-to-traverse-directed-graph) | Graph |
| 29 | [10 Wizards](#29-10-wizards) | Graph / Shortest Path |
| 30 | [Number of Intersected Rectangles](#30-number-of-intersected-rectangles) | Geometry |
| 31 | [Guess Number](#31-guess-number) | Logic / API Design |

---

# 🧩 Detailed Questions

## 1. Collatz Conjecture

If a number is odd, the next transform is `3*n+1`.

If a number is even, the next transform is `n/2`.

The number is finally transformed into `1`.

The step is how many transforms needed for a number turned into 1.

**Given an integer `n`, output the max steps of transform number in `[1, n]` into 1.**

📄 [Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/collatz_conjecture/CollatzConjecture.java)

---

## 2. Implement Queue with Limited Size of Array

Implement a queue with a number of arrays, in which each array has fixed size.

📄 [Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/implement_queue_with_fixed_size_of_arrays/ImplementQueuewithFixedSizeofArrays.java)

---

## 3. List of List Iterator

Given an array of arrays, implement an iterator class to allow the client to traverse and remove elements in the array list.

This iterator should provide three public class member functions:

- `boolean hasNext()` — return true if there is another element in the set
- `int next()` — return the value of the next element in the array
- `void remove()` — remove the last element returned by the iterator. That is, remove the element that the previous `next()` returned. This method can be called only once per call to `next()`, otherwise an exception will be thrown.

📄 [Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/list_of_list_iterator/ListofListIterator.java)

---

## 4. Display Page (Pagination)

Given an array of CSV strings representing search results, output results sorted by a score initially. A given host may have several listings that show up in these results
