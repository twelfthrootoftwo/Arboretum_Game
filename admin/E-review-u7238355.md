## Code Review

Reviewed by: Junxian Chen, u7238355

Reviewing code written by: Hongzhe Zhu, u7338066

<<<<<<< HEAD
Component: 

-    TASK_3: isHiddenStateWellFormed()

-    TASK 4: isSharedStateWellFormed()

### Comments 

#### Task 3

##### isHiddenStateWellFormed():

- Best features:

  Check if the input hiddenState[] is valid.



- Well-documented:

  In line comments applied with description of basically features. But due to not using variables, other in line codes are hard to read.




- Class and method structure:

  Check strings as the structure given by instruction. It can be better if using valuables at the beginning of the method,

  such like: String handPlayerA = hiddenState[1].

  Inside calling hiddenState[1], just used handPlayerA for the rest of method, which will make the code more readable


- Java code conventions:

  Ok


- Style consistent:

  The feature that convert player's/deck to sorted list can be put into a helper function to involved use repeat codes.


- Errors in the code:

  No errors found.  when checking numbers, due to not using variables, it is so strange to use charAt() to check an integer. It may work for 1-digit, but if the digits increase, it will lead the code become much bigger and unreadable.

    To solve this, can simply use

    int step = Integer.parseInt(hiddenState[1].substring(3, 5));

    to get the int number of distance, then just apply 0 < step < 99.



#### Task 4

##### isSharedStateWellFormed():

- Best features:

  Check if the input sharedState[] is valid.



- Well-documented:

  In line comments applied with description of basically features. But due to not using variables, other in line codes are hard to read.



- Class and method structure:

  Check strings as the structure given by instruction. It can be better if using variables at the beginning of the method,

  such like: String arboretumPlayerA = sharedState[1].

  Inside calling sharedState[1], just used arboretumPlayerA for the rest of method, which will make the code more readable


- Java code conventions:

  no variables used


- Style consistent:

  no variables used


- Errors in the code:

  No errors found. However, when checking numbers, due to not using variables, it is so strange to use charAt() to check an integer. It may work for 1-digit or 2-digit numbers, but if the digits increase, it will lead the code become much bigger and unreadable.

  To solve this, can simply use

  int step = Integer.parseInt(sharedState[1].substring(3, 5));

  to get the int number of distance, then just apply 0 < step < 99.
=======
Component: <the component being reviewed>

### Comments 

<write your comments here>

>>>>>>> fea4485e6a047c1bb209e0e26f9af0fee59190ee

