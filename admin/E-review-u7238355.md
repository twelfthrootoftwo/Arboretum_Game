## Code Review

Reviewed by: Junxian Chen, u7238355

Reviewing code written by: Hongzhe Zhu, u7338066

Component: 

- TASK_3: isHiddenStateWellFormed()

- TASK 4: isSharedStateWellFormed()

- TASK 9: canScore() 

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

#### Task 9

##### canScore():

- Best features:

  Check the given player hand to see if this player has right to score for given species



- Well-documented:

  In line comments applied with description of basically features. But doesn't include how the features work.



- Class and method structure:

  This method first get players hand strings, then check exception, then check if both side have same species, if so, finally check the score of each species with exception.

  However, when checking exception, it only checks "if one side has 1 in checked species" at canScore(), and then check another part "if another side has 8" at amount(),

  This works but may lead the method hard to read. 


- Java code conventions:

  * The variables name used in this method may lead some confusion, for example, boolean player8to0 means "if the opposite player has 1 in checked species". 

     The checking to another part of this exception "if the check player has 8 in checked species" is put in amount(). This may lead the reader hard to understand 

     what the player8to0 means.
  
  * Using var playerSpecies, var is more like C language, it needs compiler to tell you the type of the variable it would be better to directly use 
  
     int playerSpecies = playerHand.indexOf(species);
  
  * Using var playerSpecies, the logic here is, check the index of given species from player's hand, use the feature that String.indexOf(x) will return -1 
  
     if x not in String. In this case, playerHand.indexOf(species) will actually return "the first index of given species in String". However, variable name

     is playerSpecies. This may lead the reader confuse about what the playerSpecies means.

  

- Style consistent:

  Ok


- Errors in the code:

  No errors found. 
