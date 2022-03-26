# COMP1110 Assignment 2

## Academic Honesty and Integrity

Honesty and integrity are of utmost importance. These goals are *not* at odds
with being resourceful and working collaboratively. You *should* be
resourceful, you should collaborate within your team, and you should discuss
the assignment and other aspects of the course with others taking the class.
However, *you must never misrepresent the work of others as your own*. If you
have taken ideas from elsewhere or used code sourced from elsewhere, you must
say so with *utmost clarity*. At each stage of the assignment you will be asked
to submit a statement of originality, either as a group or as individuals. This
statement is the place for you to declare which ideas or code contained in your
submission were sourced from elsewhere.

Please read the ANU's [official position](http://academichonesty.anu.edu.au/)
on academic honesty. If you have any questions, please ask me.

Carefully review the statements of originality in the [admin](admin) folder
which you must complete at each stage.  Edit the relevant statement and update
it as you complete each stage of the assignment, ensuring that when you
complete each stage, a truthful statement is committed and pushed to your repo.

## Purpose

In this assignment you will *work as a group* to master a number of major
themes of this course, including software design and implementation, group
work, using development tools such as Git and IntelliJ, and using JavaFX to
build a user interface.  **Above all, this assignment will emphasize group
work**; while you will receive an individual mark for your work based on your
contributions to the assignment, **you can only succeed if all members
contribute to your group's success**.

## Assignment Deliverables

The assignment is worth 30% of your total assessment, and it will be marked out of 30.
So each mark in the assignment corresponds to a mark in your final assessment for the course.
Note that for some stages of the assignment, you will get a _group_ mark, and for others you will be _individually_ marked.
The mark breakdown and the due dates are described on the [deliverables](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/) page.

Your tutor will mark your work via GitLab, so it is essential that you carefully follow instructions for setting up and maintaining your group repository.
You will be marked according to whatever is committed to your repository at the time of the deadline.
You will be assessed on how effectively you use Git as a development tool.

## Problem Description
Your task is to implement in Java, using JavaFX, a board game called [Arboretum](https://renegadegamestudios.com/arboretum/),
designed by [Dan Cassar](http://www.dancassar.com/) and published by [Renegade Game Studios](https://renegadegamestudios.com/).
Board Game Geek provides a [description of Arboretum](https://boardgamegeek.com/boardgame/140934/arboretum), and you can watch
a [how to play](https://www.youtube.com/watch?v=Ufh606Ettvo) and an [overview and review](https://www.youtube.com/watch?v=mmiXSOG1BtQ).

## Game Overview

Arboretum is a two- to four-player game in which players attempt to create carefully planned paths between trees. Players take turns *Drawing* cards from the *Deck* and *Discard* piles, placing one card in their *Arboretum* and discarding another card. The game ends when there are no cards left in the deck.

Use the above resources in addition to the rules description below. In case of any ambiguities, please consult Piazza for a clarification.

**Note** there are some modifications in our version, so you must read this page in its entirety. If you have any confusions please search the class Piazza forum and post a new question if you can't find your answer. 

### Components

The cards in the game have 10 different "suits" which correspond to 10 species of trees each with a unique colour. Within each species there are 8 cards numbered 1 to 8. This assignment will focus on a 2 player version of the game in which *only 6 of the 10 species are used*, making up a total deck size of 48.

### Game Setup

Each player has their own arboretum, discard pile and hand. Before the game starts, the following should be completed before the first player takes their turn.

1. The Deck is filled with 48 cards made up of 6 species of trees, and is shuffled (8 species are used for the three-player game and all 10 species for the four-player games).
2. Each player randomly draws 7 cards from the deck.
3. Player A takes the first turn.

We will be playing a two-player game, but you may extend your game to accommodate up to four players. 

## Player Turn

The game is played over multiple turns alternating between players, where each turn is composed of a sequence of three actions:
1. **Draw** 2 cards.
2. **Play** 1 card to arboretum.
3. **Discard** 1 card.

In more detail:

### Draw

On each turn a player draws 2 cards. They may draw randomly from the deck, or they may draw the top card from any player's discard pile (including their own!). You may look at the first card you draw before deciding where to draw the second card from.

* It is possible to draw both cards from the same location or from separate
  locations. This makes it possible to draw two cards deep into the discard
  pile of any player on a single turn.
* The cards in the deck remain hidden, so the players do not know precisely
  what cards remain in the deck.

### Play

After drawing the player then plays face up a card from their hand to their *arboretum*. Their arboretum is a grid where only a single card can occupy each space on the grid.

* If it is the player's first turn they can play a card anywhere in their
  arboretum.
* For each subsequent turn cards must be played adjacent (horizontally or
  vertically) to one or more cards already in their arboretum.

There is no explicit limit on the size of a player's arboretum in any direction. However, in practice it will be bounded by the number of cards that can be played, as determined by the finite deck size and end game condition.

### Discard

The player discards a card from their hand face up to *their* discard pile. This can be any card from your hand, including a card you drew this turn. After discarding, you should have exactly 7 cards in your hand.

## End of Game Scoring

The game ends when there are no more cards in the deck. The player who drew the last card finishes their turn normally, and then scoring occurs. The player with the highest score wins. 

### Gaining the Right to Score
For each tree species, each player reveals the cards of that species in their hand, and sums their values (each card 1 to 8). The player with the highest sum for that species gains the right to score their single highest scoring path for that species in their arboretum. If there is a tie, all tied players gain the right to *score* their highest scoring path of that species.

**Exception!** If you have the "8" of a species in hand, but an opponent has the "1" of that species in hand, the value "8" is considered to be "0" instead when determining who has the highest sum. (the "1" is always considered to be "1").

**Note 1:** It's possible to gain the right to score points for a species that is not present in your arboretum. In this case, you will not gain any points for that species.

**Note 2:** If no player has cards of a particular species in hand, all players gain the right to score points for their highest scoring path of that species.

### Scoring Paths
A path is a sequence of orthogonally adjacent cards of *ascending* value, where the *first* and *last* cards in the path are of the same species. Each card in the path must have a value greater than the card before it, but the values do not have to be consecutive. The only cards that must match the species you have gained the right to score are the first and last card. The cards in between can be of any species.

To score a path use the following criteria: 
1. Score 1 point for each card in the path.
2. Score 1 additional point for each card in the path if the path is at least 4 cards long *and* all cards in the path are the same species. 
3. Score 1 additional point if the path begins with "1".
4. Score 2 additional points if the path ends with "8".

**Note 1:** A card may be used in different paths for different species.

**Note 2:** You may only score one path for each species, so choose your highest scoring path for that species!

![Scoring_example](assets/scoring_example_with_paths.svg)
Credit to [Josh (Zhashiya)](https://boardgamegeek.com/user/zhashiya) on board game geek who posted these [fan-made cards](https://boardgamegeek.com/filepage/146943/arboretum-fan-made-cards) they made. Note: Some of the species names differ to our version.


In the above example, the blue path is `j4j5j6j7` and the green path is `j1m2m5m6mj8`.

The green path `j1m2m5m6mj8` is worth 8 points in total. 
- 5 points for the length.
- 1 extra point because it starts with a '1'.
- 2 extra points because it ends with an '8'.

The blue path `j4j5j6j7` is also worth 8 points in total.
- 4 points for the length.
- an extra 4 points for the length because the path is at least 4 cards long, and every card in the path is of the same species.


### Winner
The winner is the player who has scored the most points. In the case of a tie, the player with the most different species present in their arboretum wins. 

If there is still a tie, the tied players should plant a tree and the player whose tree has grown the tallest in five years' time is the winner! (We will consider the game to be a tie in this scenario!) 

## Encoding Game State

The following encoding is used as part of the `Arboretum` class as a way of interfacing with the tests for the various tasks.
You are strongly encouraged to use your own internal representation of the game, and convert to and from the encoding presented here
as required to fulfill the tests / tasks. Note, the string representation is *neither robust nor convenient* to work with, hence why
you should use your own approach.

We encourage you to create your own tests. These tests can interface more directly with your code. Do no edit the supplied tests, instead add new files.

At any time in the game, the current game `state` is encoded in two String arrays.
`sharedState` is a string array representing the shared knowledge in the game.
`hiddenState` is a string array representing the hidden knowledge in the game.

### Card
Each card is represented by a two-character string that specifies the tree species on the card and the value of the card.
The first character corresponds to the species as follows:
- Cassia : `'a'`
- Blue Spruce : `'b'`
- Cherry Blossom : `'c'`
- Dogwood : `'d'`
- Jacaranda : `'j'`
- Maple : `'m'`

The second character is a digit from `1` to `8` inclusive that represents the value of the card.

For example, a Dogwood card of value 7 would be represented by the string `"d7"`

There are four additional tree species. If you are playing with three players, add `Oak` and `Royal Poinciana` species to the deck. If you are playing with four players, add `Tulip Poplar` and `Willow` in addition to the species added for three players.

### Shared State Array `sharedState`
`sharedState` is a string array representing whose turn it is and each player's Shared information.

`sharedState[0]` is a single-character *Turn* string.
`sharedState[1]` is player A's *PlayerID* followed by their *Arboretum* string.
`sharedState[2]` is player A's *PlayerID* followed by their *Discard* string.
`sharedState[3]` is player B's *PlayerID* followed by their *Arboretum* string.
`sharedState[4]` is player B's *PlayerID* followed by their *Discard* string.

For example, a sharedState string array might look like:
`{"B", "Am1C00C00a4S01C00",
"Ac5a7", "Bj5C00C00j6C00E01", "Bd2"}`

An image displaying this sharedState string can be seen below: 

![Arboretum Shared State](../assets/shared_state_example.svg)

Credit to [Josh (Zhashiya)](https://boardgamegeek.com/user/zhashiya) on board game geek who posted these [fan-made cards](https://boardgamegeek.com/filepage/146943/arboretum-fan-made-cards) they made. Note: Some of the species names differ to our version.

#### Turn and PlayerID string
The Turn/PlayerID string is one character `"A"`or`"B"` representing a player. In the case of the Turn string, this indicates that it is this player's turn.

#### Arboretum
A player's arboretum string starts with their *Player ID* and then is followed by a string of placements in the order in which they've been played. The location of each card is respective to the very first card a player has placed in their arboretum this game.

A single placement is represented as an eight-character string as follows:

**{Card}{Direction North/South/Centre}{Distance}{Direction East/West/Centre}{Distance}**

- **{Card}** The first two characters represent the *Card* as described above.
- **{Direction North/South/Centre}** The third character represents whether this card is North `'N'`, South `'S'` or  Centre `'C'` (ie: no movement) of the first card.
- **{Distance}** The fourth and fifth characters represent a two-digit number that denotes how far North/South this card is from the first card. For example `"05"` would mean this card is 5 spaces away from the first card in the direction specified by the third character.
- **{Direction East/West/Centre}** The sixth character represents whether this card is East `'E'`, West `'W'` or  Centre `'C'` (ie: no movement) of the first card.
- **{Distance}** The seventh and eighth characters represent a two-digit number that denotes how far East/West this card is from the first card. For example `"05"` would mean this card is 5 spaces away from the first card in the direction specified by the sixth character.

If a card is the first card to be played, or it is neither North or South (or alternatively East or West) of the first card played then the corresponding **{Direction}** character should be `'C'` and the **{Distance}** digits should be `"00"`.

In the example above, if my first card played is `"a4"` then my placement string would be `"a4C00C00"`
If I then played `"m1"` 1 square South of this card, my full placement string would be `"a4C00C00m1S01C00"`
`"m1"` describes the card played. `"N01"` says it has been played 1 square South of the first card played. `"C00"` says it was placed neither East nor West of the first card.


#### Discard
Each player's discard pile starts with their *Player ID* and is then followed by a number of *Card* strings in the order in which they were added to the discard pile. The final *Card* in this string should be the card that is on top of the player's discard pile.

For example:
`"Ba2m4a1c7"` tells us that player B first discarded `"a2"`, followed by `"m4"`, then `"a1"` and finally `"c7"`.

### Hidden State Array `hiddenState`
`hiddenState` is a string array representing each player's hidden information.

`hiddenState[0]` is the *Deck* string.
`hiddenState[1]` is player A's *PlayerID* followed by their *Hand* string.
`hiddenState[2]` is player B's *PlayerID* followed by their *Hand* string.

For example, a hiddenState string array might look like this:
`{"a3a8b5b6c2c7d1d3d7d8m1", "Ab3b4c1j1m2m5m8", "Ba6b7b8c8d2j2j8"}`

#### Deck
The Deck is a number of *Card* strings representing the cards that are in the deck. These are sorted alphanumerically. ie: They are first sorted alphabetically, and then in ascending numerical order.

For example: `"a1a5a6b8j1"` tells us that `"a1"`, `"a5"`, `"a6"`, `"b8"` and `"j1"` are in the deck whilst there are no cards of other species in the deck.

#### Hand
A player's hand starts with their *Player ID* and is then followed by 7 - 9 *Card* strings representing the cards in a player's hand. A player always has 7 cards in hand, but will draw 2 cards to bring their total to 9 on their turn. These are also sorted alphanumerically.

For example:
`"a3c4j5j6j8m3m7"` tells us that this player has `"a3"`, `"c4"`, `"j5"`, `"j6"`, `"j8"`, `"m3"` and `"m7"` in hand.


## Evaluation Criteria

It is essential that you refer to the
[deliverables page](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/)
to check that you understand each of the deadlines and what is
required.  Your assignment will be marked via tests run through git's
continuous integration (CI) framework, so all submittable materials
will need to be in git and in the *correct* locations, as prescribed
by the
[deliverables page](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/).


**The mark breakdown is described on the
[deliverables](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/)
page.**

### Part One

In the first part of the assignment you will:
* Set up your assignment (Task #1).
* Create a design skeleton (Task #2).
* Implement parts of the text interface to the game (Tasks #3 and #4).
* Implement a method to draw an arbitrary card from the deck (Task #5)
* Implement a simple viewer that allows you to visualize game states (Task #6).
* Implement a check to determine whether a placement is valid (Task #7).
* Implement a check to determine whether a game state is valid (Task #8)

An indicative grade level for each task for the [completion of part one](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/#D2C) is as follows:

**Pass**
* Tasks #1, #2, #3, #4 and #5

**Credit**
* Task #6 *(in addition to all tasks required for Pass)*

**Distinction**
* Task #7 and #8 *(in addition to all tasks required for Credit)*

### Part Two

Create a fully working game, using JavaFX to implement a playable
graphical version of the game in a 1200x700 window.

Notice that aside from the window size, the details of exactly how the
game looks etc, are **intentionally** left up to you.  The diagrams
above are for illustration purposes only, although you are welcome to
use all of the resources provided in this repo.

The only **firm** requirements are that:

* you use Java 17 and JavaFX 17,
* the game respects the specification of the game given here,
* the game be easy to play,
* it runs in a 1200x700 window, and
* that it is executable on a standard lab machine from a jar file called `game.jar`,

Your game must successfully run from `game.jar` from within another
user's (i.e.  your tutor's) account on a standard lab machine (in
other words, your game must not depend on features not self-contained
within that jar file and the Java 17 runtime).

An indicative grade level for each task for the [completion of part two](https://cs.anu.edu.au/courses/comp1110/assessments/deliverables/#D2F) is as follows:

**Pass**
* Correctly implements all of the <b>Part One</b> criteria.
* Appropriate use of git (as demonstrated by the history of your repo).
* Completion of Task #9 and #10.
* Executable on a standard lab computer from a runnable jar file,
  game.jar, which resides in the root level of your group repo.

**Credit**
* _All of the Pass-level criteria, plus the following..._
* Task #11.

**Distinction**
* _All of the Credit-level criteria, plus the following..._
* Tasks #12, #13, #14 and #15.

**High Distinction**
* _All of the Distinction-level criteria, plus the following..._
* Tasks #16, #17 and #18.
