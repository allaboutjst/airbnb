# AirBnB Interview Questions

## Disclaim

All questions below are collected from Internet, including:

* [GeeksForGeeks](http://www.geeksforgeeks.org/company-preparation/)
* [GlassDoor](https://www.glassdoor.com/Interview/san-francisco-airbnb-interview-questions-SRCH_IL.0,13_IC1147401_KE14,20.htm)

## List of Questions

#### 1 [Collatz Conjecture](https://en.wikipedia.org/wiki/Collatz_conjecture)

* If a number is odd, the next transform is 3*n+1
* If a number is even, the next transform is n/2
* The number is finally transformed into 1. 
* The step is how many transforms needed for a number turned into 1. 

Given an integer n, output the max steps of transform number in [1, n] into 1.

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/collatz_conjecture/CollatzConjecture.java)

#### 2 [Implement Queue with Limited Size of Array](https://www.cs.bu.edu/teaching/c/queue/array/types.html)

Implement a queue with a number of arrays, in which each array has fixed size.

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/implement_queue_with_fixed_size_of_arrays/ImplementQueuewithFixedSizeofArrays.java)

#### 3 List of List Iterator

Given an array of arrays, implement an iterator class to allow the client to traverse
and remove elements in the array list.

This iterator should provide three public class member functions:

* boolean hasNext() return true if there is another element in the set
* int next() return the value of the next element in the array
* void remove() remove the last element returned by the iterator. That is, remove the element that the previous next() returned. This method can be called only once per call to next(), otherwise an exception will be thrown.

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/list_of_list_iterator/ListofListIterator.java)

#### 4 Display Page (Pagination)

Given an array of CSV strings representing search results, output results sorted by a score initially. A given host may have several listings that show up in these results. Suppose we want to show 12 results per page, but we don’t want the same host to dominate the results. 

Write a function that will reorder the list so that a host shows up at most once on a page if possible, but otherwise preserves the ordering. Your program should return the new array and print out the results in blocks representing the pages. 

Given an array of csv strings, output results separated by a blank line.

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/display_page/DisplayPage.java)

#### 5 Travel Buddy

I have a wish list of cities that I want to visit to, my friends also have city wish lists that they want to visit to. If one of my friends share more than 50% (over his city count in his wish list), he is my buddy.

Given a list of city wish list, output buddy list sorting by similarity.

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/travel_buddy/TravelBuddy.java)

#### 6 File System

Write a file system class, which has two functions: create, and get 
* create("/a",1) 
* get("/a") //get 1
* create("/a/b",2) 
* get("/a/b") //get 2 
* create("/c/d",1) //Error, because "/c" is not existed 
* get("/c") //Error, because "/c" is not existed

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/file_system/FileSystem.java)

#### 7 [Palindrome Pairs](https://leetcode.com/problems/palindrome-pairs/description/)

Given a list of unique words, find all pairs of distinct indices (i, j) in the given list, so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome. 

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/palindrome_pairs/PalindromePairs.java)

#### 8 Find Median in Large Integer File of Integers

Find the median from a large file of integers. You can not access the numbers by index, can only access it sequentially. And the numbers cannot fit in memory.

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/find_median_in_large_file_of_integers/FindMedianinLargeIntegerFileofIntegers.java)

#### 9 [IP Range to CIDR](https://en.wikipedia.org/wiki/Classless_Inter-Domain_Routing)

Given an IPv4 IP address p and an integer n, return a list of CIDR strings that most succinctly represents the range of IP addresses from p to (p + n).  

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/ip_range_to_cidr/IPRangetoCIDR.java)

#### 10 [CSV Parser](http://creativyst.com/Doc/Articles/CSV/CSV01.htm#EmbedBRs)

Write a method to parse input string in CSV format.

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/csv_parser/CSVParser.java)

#### 11 [Text Justification](https://leetcode.com/problems/text-justification/description/)

 Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.

You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary so that each line has exactly L characters.

Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words, the empty slots on the left will be assigned more spaces than the slots on the right.

For the last line of text, it should be left justified and no extra space is inserted between words. 

#### 12 Regular Expression

Implement a simple regex parser which, given a string and a pattern, returns a boolean indicating whether the input matches the pattern.

By simple, we mean that the regex can only contain special character: 

* \* (star) The star means what you'd expect, that there will be zero or more of previous character in that place in the pattern.
* . (dot) The dot means any character for that position. 
* \+ (plus). The plus means one or more of previous character in that place in the pattern.

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/regular_expression/RegularExpression.java)

#### 13 Water Drop/Water Land/Pour Water

Input is a array represent how the height of water is at each position, the number of water will be poured, and the pour position.
Print the land after all water are poured.

Example:
input land height int[]{5,4,2,1,3,2,2,1,0,1,4,3}
The land is looks ike:

```text
+
++        +
++  +     ++
+++ +++   ++
++++++++ +++
++++++++++++
012345678901
```

water quantity is 8 and pour at position 5. The land becomes:

```text
+
++        +
++www+    ++
+++w+++www++
++++++++w+++
++++++++++++
012345678901
```

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/water_land/WaterLand.java)

#### 14 [Hilbert Curve](https://en.wikipedia.org/wiki/Hilbert_curve)

Given (x, y, iter), in which (x, y) is position at x-axis and y-axis, and iter is how many iterations will be. Output is in iteration *iter*, how many steps are required to move from (0, 0) to (x, y) in iteration *iter*.

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/hilbert_curve/HilbertCurve.java)

#### 15 [Simulate Diplomacy](http://www.backstabbr.com/rules)

Input (army_name, location, action())

action() could be:
* move(new_location) then army_name to new_location, If there is an army at new_location, then company strength of two aramy:
    * The army have higher strength stay at new_location, the lower army is disappeared.
    * If two army have the same strength, both are disppeared.
* hold() stay at the same location
* support(army_name) supoort another army. The supported army have one more strength.

The army's strength are intialized as the same.

### 16 Meeting Time

Input is a number of meetings (start_time, end_time)。
Output is a number of time intervals (start_time, end_time), where there is no meetings.

For exmaple, input is [[1, 3], [6, 7]], [[2, 4]], [[2, 3], [9, 12]] ] 

output [[4, 6], [7, 9]] 

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/meeting_time/MeetingTime.java)

#### 17 Round Prices

Given an array of numbers A = [x1, x2, ..., xn] and T = Round(x1+x2+... +xn). We want to find a way to round each element in A such that after rounding we get a new array B = [y1, y2, ...., yn] such that y1+y2+...+yn = T where yi = Floor(xi) or Ceil(xi), ceiling or floor of xi. 

We also want to minimize sum |x_i-y_i| 

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/round_prices/RoundPrices.java)

#### 18 Sliding Game

You're given a 3x3 board of a tile puzzle, with 8 tiles numbered 1 to 8, and an empty spot. You can move any tile adjacent to the empty spot, to the empty spot, creating an empty spot where the tile originally was. The goal is to find a series of moves that will solve the board, i.e. get [ [1, 2, 3], [4, 5, 6], [7, 8, - ]…

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/sliding_game/SlidingGame.java)

#### 19 Maximum Number of Nights You Can Accommodate

Given a set of numbers in an array which represent number of consecutive nights of AirBnB reservation requested, as a host, pick the sequence which maximizes the number of days of occupancy, at the same time, leaving at least 1 day gap in between bookings for cleaning. Problem reduces to finding max-sum of non-consecutive array elements.

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/maximum_number_of_nights_you_can_accommodate/MaximumNumberofNightsYouCanAccommodate.java)

#### 20 Find Case Combinations of a String

Find all the combinations of a string in lowercase and uppercase. For example, string "ab" >>> "ab", "Ab", "aB", "AB". So, you will have 2^n (n = number of chars in the string) output strings. The goal is for you to test each of these strings and see if it matchs a hidden string.

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/find_case_combinations_of_a_string/FindCaseCombinationsofaString.java)

#### 21 Menu Combination Sum

Given a menu (list of items prices), find all possible combinations of items that sum a particular value K. (A variation of the typical 2sum/Nsum questions).

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/menu_combination_sum/MenuCombinationSum.java)

#### 22 K Edit Distance

Find all words from a dictionary that are k edit distance away.

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/k_edit_distance/KEditDistance.java)

#### 23 Boggle Game

Given 2d matrix of letters, and a word dictionary, find a path which has largest number of words (existed inside the dictionary).

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/boggle_game/BoggleGame.java)

#### 24 Minimum Cost with At Most K Stops

Given a flight itinerary consisting of starting city, destination city, and ticket price (2d list) - find the optimal price flight path to get from start to destination. (A variation of Dynamic Programming Shortest Path)

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/minimum_cost_with_at_most_k_stops/MinimumCostwithAtMostKStops.java)

#### 25 String Pyramids Transition Matrix

Given a list of leaf nodes in a pyramid, and a map which indicates what's the possible parent node given a left and right node. Return true if the one of leaf node could turn into the root node, Otherwise, return false.
For example:

```text
     root
     / \
    X   X
   /\  /\
   X  X  X
  / \/ \/ \
 A   B  C  D
```

Map: 
```text
        left: A |  B   |   C | D
right---------------------------------
A             B |A or C|   D | A
B             D |B or C|   A |
C                              B
D
```
Note:1. If left child is B, right child is A, the parent node could be B or C

Given leaf input of "ABCD", output true.

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/string_pyramids_transition_matrix/StringPyramidsTransitionMatrix.java)

#### 26 Finding Ocean

Given: An array of strings where L indicates land and W indicates water, and a coordinate marking a starting point in the middle of the ocean.

Challenge: Find and mark the ocean in the map by changing appropriate Ws to Os.
* An ocean coordinate is defined to be the initial coordinate if a W, and
* any coordinate directly adjacent to any other ocean coordinate.

```text
void findOcean(String[] map, int row, int column);
String[] map = new String[]{
 "WWWLLLW",
 "WWLLLWW",
 "WLLLLWW"
};
printMap(map);
```
```text
STDOUT:
WWWLLLW
WWLLLWW
WLLLLWW
```
```text
findOcean(map, 0, 1);
printMap(map);
```
```text
STDOUT:
OOOLLLW
OOLLLWW
OLLLLWW
```

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/finding_ocean/FindingOcean.java)

#### 27 Preference List

Given a list of everyone's preferred city list, output the city list following the order of everyone's preference order.

For example, input is [[3, 5, 7, 9], [2, 3, 8], [5, 8]]. One of possible output is [2, 3, 5, 8, 7, 9].

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/preference_list/PreferenceList.java)

#### 28 Minimum Vertices to Traverse Directed Graph

Given a directed grapjh, represented in a two dimension array, output a list of points that can be used to travese every points with the least number of visited vertices.

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/minimum_vertices_to_traverse_directed_graph/MinimumVerticestoTraverseDirectedGraph.java)

#### 29 10 Wizards

There are 10 wizards, 0-9, you are given a list that each entry is a list of wizards known by wizard. Define the cost between wizards and wizard as square of different of i and j. To find the min cost between 0 and 9. 

For example:

wizard[0] list: 1, 4, 5 

wizard[4] list: 9

 wizard 0 to 9 min distance is (0-4)^2+(4-9)^2=41 (wizard[0]->wizard[4]->wizard[9]) 

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/ten_wizards/TenWizards.java)

#### 30 Number of Intersected Rectangles

Given lots of rectangles, output how many of them intersect.

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/number_of_intersected_rectangles/NumberofIntersectedRectangles.java)

#### 31 Guess Number

The system has a secret four digits number, in which each digit is in range of one to 6 [1, 6]. 

Given a four digital number, the system also provide a API that returns the number of correct matched digits.

Design a method to guess this secret number.

[Java Source Code](https://github.com/dqi2018/airbnb/blob/master/src/main/java/guess_number/GuessNumber.java)


## Requirements
* Java >= 1.8.151
* Gradle >= 4.2.1

## Run Unit Tests
If you write some unit tests, you can use the following command to run them.

```bash
# run all tests
gradle test 
# run Palindrome Pairs test only
gradle -Dtest.single=PalindromePairs test
# run Palindrome Pairs test only with some informnation
gradle -Dtest.single=PalindromePairs test --info
```

## Update Logs
v1.0 11/25/2017
* Update almost all questions with unit tests
