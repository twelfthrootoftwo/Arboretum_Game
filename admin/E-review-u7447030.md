## Code Review

Reviewed by: Natasha Pegler, u7447030

Reviewing code written by: Junxian Chen, u7238355

Component: [Arboretum.checkArboretum()](https://gitlab.cecs.anu.edu.au/u7447030/comp1110-ass2/-/blob/master/src/comp1110/ass2/Arboretum.java#L731-811), [Game.start()](https://gitlab.cecs.anu.edu.au/u7447030/comp1110-ass2/-/blob/master/src/comp1110/ass2/gui/Game.java#L166-205)

### Comments 

checkArboretum():
Best features - Using a regex to split up the full arboretum string is nifty! 
Documentation - Logical steps are separated and explained clearly. Some more elaboration on the relationship between the elements of map and hasNext would be useful.
Decomposition - Use of variables is clear and enhances readability. However, there is extensive overlap between this method and existing code for the generation of backend classes. One improvement would be to separate overlapping code into external methods and implement these methods in all relevant locations. Using backend structures instead of strings should also be considered. 
Convention & style - Solid, variable names are clear (hasNext is slightly unclear)
Potential errors - Does not appear to check for multiple cards in the same position? Quick fix could be comparing the size of both hash maps with the size of arboretumBTreesList

start():
This function appears unfinished, since some behaviour has been hard-coded and expected functionality is missing.
Best features - Initial pregame setup (deck creation, drawing hands) looks good!
Documentation - No comments, however clear variable and function names make the code easily understandable 
Decomposition - Processes are broken up into multiple methods, though not all are currently complete. More finished versions should accept user input for playing cards (currently this is hard-coded)
Convention & style - Sensible and consistent throughout


