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
ant -buildfile arrayvisitors/src/build.xml run -Dinput1="input_1.txt" -Dinput2="input_2.txt" -Dcommonintsout="common_ints_out.txt" -Dmissingintsout="missing_ints_out.txt" -Ddebug="debug_log.txt"
```
Note: Arguments accept the absolute path of the files.


## Description:




## Academic Honesty statement:

"I have done this assignment completely on my own. I have not copied
it, nor have I given my solution to anyone else. I understand that if
I am involved in plagiarism or cheating an official form will be
submitted to the Academic Honesty Committee of the Watson School to
determine the action that needs to be taken. "

Date:  July 15, 2020


