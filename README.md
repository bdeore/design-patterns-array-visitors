# CSX42: Assignment 4
**Name:** Bhagwan Sanjay Deore
-----------------------------------------------------------------------

Following are the commands, and the instructions to run ANT on your project.


Note: build.xml is present in [arrayvisitors/src](./arrayvisitors/src/) folder.

## Instruction to clean:

```commandline
ant -buildfile arrayvisitors/src/build.xml clean
```

Description: It cleans up all the .class files that were generated when you
compiled your code.

## Instructions to compile:

```commandline
ant -buildfile arrayvisitors/src/build.xml all
```
The above command compiles your code and generates .class files inside the BUILD folder.

## Instructions to run:

```commandline
ant -buildfile arrayvisitors/src/build.xml run -Dinput1="input_1.txt" -Dinput2="input_2.txt" -Dcommonintsout="common_ints_out.txt" -Dmissingintsout1="array1_missing_ints_out.txt" -Dmissingintsout2="array2_missing_ints_out.txt" -Ddebug="debug_log.txt"
```
Note: Arguments accept the absolute path of the files.


## Description:


ADT Design:


MyArray: api is very close to that of the vector. I have only implemented the necessary methods.

MyArrayList: api is very small as other operations are NOT needed for this assignment.


```commandline
Time Complexity: ~ Linear Time

creating an array of 100 elements - O(100) ~ always constant
looping through first array containing m elements - O(m)
looping through second array containing n elements - O(n)
looping through reference array to store result - O(100)

so, Time Complexity: O(m + n)
```

Algorithm Design:

To find missing integers and common integers, I'm using a simple array data structure. 

algorithm steps for the visitors :

a) CommonIntsVisitor :

1) create a reference array of 100 elements as range is always [00-99] (all elements are initialized to zero by default).
2) loop through all the elements in the first array and for all numbers update those indices in reference array to 1.
    e.g. if number in the array is 11, set element at 11th position in reference array to 1. so, array[11] = 1
3) loop through all the elements in the second array and for all numbers update those indexes in reference array to 2.
    e.g. if number 11 is found in second array, set it to 2. so, array[11] = 2 
4) go through all the elements in reference array and save the indices where value is 2.
    since, 11 was present in both arrays, value at 11th position in reference array will be 2 and number 11 will be stored in results.
    
this approach doesn't require any additional logic for duplicate values.


b) MissingIntsVisitor : 

1) create a reference array of 100 elements (all are initialized to zero by default).
2) loop through all the elements in the array and for all numbers update those indices in reference array to 1.
    e.g. if number in the array is 11, set element at 11th position in reference array to 1. so, array[11] = 1
3) go through all the elements in reference array and save the indices where value is 1.
    since, 11 was present in the array, value at 11th position in reference array will be 1 and number 11 will be stored in results.
      

## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date:  July 15, 2020


