## Code Review

Reviewed by: Hongzhe Zhu, u7338066

Reviewing code written by: Natasha Pegler, u7447030

Component: 

* Task 12 [getAllViablePaths()](https://gitlab.cecs.anu.edu.au/u7447030/comp1110-ass2/-/blob/master/src/comp1110/ass2/Arboretum.java#L1116-1188) function in Arboretum class
* Task 13 [getHighestViablePathScore()](https://gitlab.cecs.anu.edu.au/u7447030/comp1110-ass2/-/blob/master/src/comp1110/ass2/Arboretum.java#L1190-1222) function in Arboretum class


### Comments 

**Task 12:**

Natasha has done a great job in implementing the function using limit amount of codes. 

* Best features: It is surprising to see her make
use of the Arbor class and the enum class Species that have been created in the game folder. She creates methods inside
the Arbor class so that she can make use of them and code in shorter and clearer way. It would also benefit other team
members in doing other tasks. It would also be easier to repair and add functionality based on the class.


* Well-documented: The style of the code is very good, comments are made appropriately, and Natasha leaves a space between every step of
  coding, which is definitely user-friendly.


* Decomposition: The classes and methods used are properly named and easily understand. Each method does a simple job, and it does not
  confuse me understanding the codes. The variables are also well named and the naming of the helper function is good as
  well.


* Conventions: All good, namings of variables are appropriate. Comments and docstrings make reader better understanding the codes.


* Instead of `Species.valueOf(String.valueOf(species))`it would be better if this can be simplified into just
`Species.valueOf(species)` by adding `String.valueOf(species)`at the beginning of the method and change the input from String to Species.


* Error: No error found.


**Task 13:**

* Best features: Plays around with HashMap and uses the Arbor class which make the function look clean and easy.


* Well-documented: The style of the code is very good, comments are made appropriately, and Natasha leaves a space between every step of
  coding, which is definitely user-friendly.


* Decomposition: The classes and methods used are properly named and easily understand. Each method does a simple job, and it does not
  confuse me understanding the codes. The variables are also well named and the naming of the helper function is good as
  well.


* Conventions: Maybe arborString can be a better naming instead of arborCode. 


* Instead of `Species.valueOf(String.valueOf(species))`it would be better if this can be simplified into just
`Species.valueOf(species)` by adding `String.valueOf(species)`at the beginning of the method and change the input from String to Species.


* Error: No error found.


