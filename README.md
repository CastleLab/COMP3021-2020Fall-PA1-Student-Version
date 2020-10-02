## COMP3021 2020Fall Programming Assignment 1

Please clone this repository, or download the zip file in the [Release](https://github.com/CastleLab/COMP3021-2020Fall-PA1-Student-Version/releases/tag/PA1_release). 
Then, you need to create a **PRIVATE** Github repository of your own, and upload your code there. 
Please make sure to frequently make commits in your repository since commits also contribute to your grade of
 assignment 1. 

## Background

[Jeson Mor](https://en.wikipedia.org/wiki/Jeson_Mor) is a two-player strategy board game from Mongolia, which is a
 variant of chess. 
Each player has several pieces and wins the game by being the first to occupy the central place and then leave that
 place. 
 
In this assignment, we will implement an adapted, text version of Jeson Mor in the console. 

## Environment

This assignment uses the following environments
* Java `>= 14`
* Intellij IDEA (Community or Ultimate Edition) `>= 2020.2`

## Assignment Specification

### Piece

Students will implement a type of piece: `Knight` which
 moves and captures in the same way as **Horse in Chinese chess**
 [(Wikipedia)](https://en.wikipedia.org/wiki/Xiangqi#Horse). 
Note that this moving rule is very similar to knights in chess but with additional blocking rules. 

The class `Knight` in package `castle.comp3021.assignment.piece` is prepared for you to implement. 
You only need to implement `Move[] getAvailableMoves(Game game, Place source)` method to obtain all **VALID** moves
 for each knight object. 
 
**Valid** moves means those moves that do not violote the rule of the game, including but not limit to:
* The moving rule of knight
* Cannot move out of the gameboard
* ...
 
Knight object is constructed with the player object that it belongs to, e.g. `new Knight(consolePlayer)`.
 
### Game

In this text version of Jeson Mor, the gameboard is displayed as follows: 
```
        a b c
        -----
      3|. . .|3
      2|. x .|2
      1|K K .|1
        -----
        a b c
```

The pieces are represented as a character on the board. 
Here, `Knight` is represented as `K`.
A dot `.` represents a square, or a place on the board where there is no piece. 
A cross `x` represents the central square of the game board. 

We use 2-dimension coordinates to represent the place where a piece is. 
We call the horizontal axis as `x`-axis while the vertical axis as `y`-axis. 
The `origin` of the coordinate system is at bottom-left corner. 
Indices of x-axis start from alphabet `a` while indices of y-axis start from `1`. 
This is consistent with the coordinates in the gameboard of chess. 

**Note**: In the Java program, to facilitate implementation, both x and y-axis use integer as coordinates, and
 the index starts from `0`. Make sure to convert different coordinate formats. 
 
A class `JesonMor` in package `castle.comp3021.assignment` is prepared for you to implement the game logic of Jeson Mor.
You need to implement the methods in the class according to the associated JavaDoc. 
`JesonMor` object is constructed using a `Configuration` object by `new JesonMor(configuration)`.

### Configuration

There is a `Configuration` class representing the configuraton of a game. 
The game is initialized according to the configuration. 

The configuration has the following fields:
* `size`: The height and width of the game board are always the same. 
          We call the height or width as `size` of gameboard. 
          The size of the gameboard is configurable, complying to the following requirements:
          (1) The size of gameboard must be **at least 3**.
          (2) The size of gameboard must be an **odd number**. 
          (3) The size of gameboard is **at most 25**.
* `numMovesProtection`: Within the first `numMovesProtection` number of moves, the following things are **NOT** allowed: 
                        (1) capture the enemy's pieces
                        (2) win the game by moving a piece out of central place
* `players`: An array of two players in the game.
             The order of players moving pieces when game starts is the order in this array. 
* `initialBoard`: The initial gameboard when game starts. 
                  Student should use `Configuration.addInitialPiece(Piece, Place)` method to set the initial position
                   of pieces of each player. 
* `centralPlace`: The central place of the gameboard.


### Player

There are two players in this game. 
The number of pieces belonging to each player is equal to the size of gameboard. 
Pieces of the two players are put in the topmost and lowest row on the gameboard. 
The player on the lowest row is the first to move. 

Students need to implement two kinds of players: 
* A computer player, who moves pieces randomly. 
* A user player, who moves pieces according to console input.

The user player should move first when game starts. 

Two classes `ConsolePlayer` and `RandomPlayer` in package `castle.comp3021.assignment.player` are prepared for you to
 implement. 
You only need to implement `nextMove(Game game, Move[] availableMoves)` to make a move according to the strategy of the
 player. 
 
### Layout of Gameboard

In the initial gameboard when game starts, each player has the number of piece equal to the size of the gameboard. 
All pieces of a player are put in one row on the gameboard. 
Pieces of user player should be put in the bottom row while pieces of computer player should be put in the top row. 

### Moves with Protection

There is a field of `Configuration` class called `numMovesProtection` which represents that within the first
 `numMovesProtection` number of moves, the following things are not allowed: 
* capture the enemy's pieces
* win the game by moving a piece out of central place

### Score Mechanism

Each player also has its own score during the game. 
The score is the cumulative score of all the moves made by this player. 
The score of each move is calculated as the Manhattan distance between the destination and source of the move. 

Note that winning the game with a lower score indicates that the player is more intelligent. 
The score is also used to break the tie when no piece can be moved on the gameboard. 

### Winning Condition

There are two scenarios for a player to win: 
* Be the first one to move a **knight** (not other type of pieces) to the central square of the gameboard and then leave
 the
 central square. 
Note that leaving the central square does not have to be the immediately subsequent move after it reaches the central
 square. 
* Capture all the pieces of the other player. 

**Note that only knights can win the game by leaving the central place while archers cannot.**

**Tie Breaking Rule**: If there is no available moves for the player, the game ends and the player with lower score
 wins. If two players have the same score, current player (the player who should make a move now but there is no
  available moves) wins.

Note that there can be scenarios where the game continues infinitely with available moves for each player, but no one is
 able to win. 
However, in this assignment, we do not consider such scenarios. 

## Student Tasks

Here we summarize the requirements of implementation in this assignment. 

Students should create a **PRIVATE** Github repository to hold their code, and commit frequently for at least three
 days. 

### Basic Tasks
Students need to implement the following functionalities based on the provided skeleton code:
* Initialize the gameboard by putting pieces of each player on the board, the size of gameboard should be configurable.
* `Knight` piece, which moves in the same way as horses in Chinese chess. 
* Two players: 
    * A user player, who moves pieces according to console input.
    Console input will be the in the format `xy->xy` when it is the player's turn.
    `x` and `y` are x and y coordinates respectively of source and destination of the move. 
    For example, `a1->b3` represents moving the piece at `(a, 1)` to place `(b, 3)` on the gameboard.
    * A computer player, who moves pieces randomly with uniform distribution. 
* When the game starts, pieces of user player should be put at the lowest row while pieces of computer player is put
 at the topmost row. 
* The user player is the first to move. 
* Invalid inputs of user player from the console should be properly handled. 
* Maintain a counter which counts the total number of moves made by the two players. 
* Implement a score for each player. 
The score of a player is the cumulative score of the moves made by the player.
The score of a move is the [Manhattan distance](https://xlinux.nist.gov/dads/HTML/manhattanDistance.html) between
 the destination and source of the move. 
* Players cannot capture pieces of their enemy or win within the first `N` moves, where `N` should be configurable.

### Bonus Task 1 - Archer

Implement piece type `Archer`, which moves in the same way as **cannon in Chinese chess** 
 [(Wikipedia)](https://en.wikipedia.org/wiki/Xiangqi#Cannon). 
When initialize the gameboard, each player should have half knights and half archers. 
Since the total number is odd, each player should have one more knight than archers. 
The arrangement of knights and archers of each player are should be in a staggered pattern. 

**Note** that player cannot win the game by move an archer out of the central place. 

### Bonus Task 2 - Additional Tests

In this bonus task, you are required to design additional JUnit5 tests besides the sample tests provided by us. 
The objective of this bonus task is to increase the [branch coverage](https://en.wikipedia.org/wiki/Code_coverage) of
 all classes under folder `src/main/java` by executing your tests along with the sample tests, compared
 with only using sample tests. 
The more branches (that are not covered by sample tests) are covered by your tests, the more bonus points you will get. 
You can get 1 point for each 10% improvement of branch coverage. 

Your additional tests should be put in the `AdditionalTests` class in folder `src/test/java/castle/comp3021/assignment`.

We use JaCoCo test coverage runner in IntelliJ to measure branch coverage. 

## Obfuscated Version

We provide an obfuscated demo program which implements Jeson Mor. 
The demo program can be found at `artifacts/PA1_obfuscated.jar`.

Usage: 
```
java -jar artifacts/PA1_obfuscated.jar <size> <numMovesProtection>
```
The demo program takes two arguments: 
* `<size>` is the size of gameboard
* `<numMovesProtection>` is the number of first moves when capturing pieces and winning the game are not allowed. 

For example, `java -jar artifacts/PA1_obfuscated.jar 9 5` means start a game with gameboard size = 9 and capturing
 enemy's pieces
 is not allowed within 5 moves.
 
You can use `Ctrl-C/Command-C` to interrupt/terminate the program. 

## Java Doc

[Java doc](https://castlelab.github.io/COMP3021-2020Fall-PA1-Student-Version/) of this assignment is available in
 `docs`.
You can open `docs/index.html` to view it in your browser. 

## Code Skeleton

We provide code skeleton with detailed JavaDoc for you to complete this assignemnt. 
`// TODO student implementation` indicates that you should write your own code there. 
There are in total 9 (10 if you plan to implement Archer) `TODO`s for you to implement.
Do not modify other parts of the Java code, otherwise many tests will fail unexpectedly.  

### Dependencies

Code of this assignment depends on several libraries, including `JUnit5` and Jetbrains `Annotations`. 
Jar package of these dependencies are provided in folder `lib`, you need to add `lib` folder as the library of your
 IntelliJ Project. Typically, this is already done, if you download use the prepared code in this repository. 

### Coordinate System

In order to facilitate implementation, we both use integers in the coordinates of x and y-axis (starting from 0) to
 represent the places on the gameboard. 
One place on the gameboard is represented as a `Place` object with x and y coordinates. 
The x and y coordinates stored in `Place` object count from `0`, which is **VERY** different from the coordinates in
 console display, where x-coordinate starts from `a` and x-coordinate starts from `1`. 
You should convert different formats between Place object and console. 

### Important Constraints when Implementing Methods

* `Move[] Piece.getAvailableMoves(Game game, Place source)` method should return **exactly all** the **VALID**
 moves of the piece object given the current place where the piece is on the board. 

* `Move[] getAvailableMoves(Player player)` method should also return **exactly all** the **VALID** moves of this player at
 present. 
 
* `Move Player.nextMove(Game game, Move[] availableMoves)` method takes all the available moves of all pieces
 belonging to this player, which are returned by `Move[] Piece.getAvailableMoves(Game game, Place source)` of each
 piece. The move returned should be a **VALID** move. 
 
* `void Game.updateScore(Player player, Piece piece, Move move)` method updates the score of the give player according
 to the move, and the piece he just moved. We do not want you to validate the move inside this method, since in
  unit tests, this method may be called separately without a valid gameboard being constructed. So all you need is to
   implement the score algorithm. That's it. 
 
### Program Entry

The main method is at `castle.comp3021.assignment.Main` class, which requires two program arguments: 
`<size> <numMovesProtection>`, which are the size of the gameboard, and the number of first moves where capturing
 pieces and winning the game are not allowed, respectively. 
 
### Tests

We provide 15 sample tests for you check your implementation. 
You should make sure your code passes all sample tests before submission. 
Note that passing all sample tests does not mean you get full marks. 
We also have 20 unit tests and 3 integrated tests to test your implementation, which is also an important part of
 your grade of assignment. 

For bonus tasks, we have 8 separate tests for your implementation. 

Sample tests are given in `src/test/java` folder. 
 
### Reminders 

* Try your best to consider as many corner cases as possible, your tests will test if your implementation is robust
 enough to handle various kinds of scenarios. 
* If you have doubts of any behaviours that are not specified, you can check the provided obfuscated version, or ask
 the TAs. 
 
### Frequently Asked Questions

We offer a separate page collecting all the frequently asked questions [here](FAQ.md).
This page will be updated regularly as more questions are asked by your and your classmates. 
You can get notifications for update by 
 [watching this repository](https://docs.github.com/en/enterprise/2.20/user/github/receiving-notifications-about-activity-on-github/watching-and-unwatching-repositories). 
 
## Code Style

Maintaining [a good code](https://google.github.io/styleguide/javaguide.html) style in Java program is good practice for a developer. 
To help you practise good code style in Java programs, we provide a jar application to check the code style of your
 code. 

Usage: 
```
java -jar artifacts/checkstyle-8.36-all.jar -c artifacts/style_checks.xml com.puppycrawl.tools.checkstyle.gui.Main src
```
If your code contains any bad code style, errors will show up with detailed information. 

Note that good code style is also a part of your grade in this assignment. 

## Submission Requirements

Your submission is a zip file including: 
* The Java project. Please maintain the original file structure. 
* A `github_url.txt` file containing the url of your private repository. 
  We will ask you to add TAs' accounts as collaborators.

You need to submit your zip file in [CASS](https://cssystem.cse.ust.hk/UGuides/cass/index.html) (not CANVAS).
The deadline of assignment 1 is **`5 Oct. 2020, 23:55`**. 

## Plagiarism

Do remember not to violate the following rules, otherwise it would be considered as an act of plagiarism.

* You must not share your codes with your classmates.
* You must not copy codes from your classmates.
* You must keep your Github Repository **Private** all the time. 

We will conduct code plagiarism tests on your program, and your grade will be deducted according to the [Honor
 Code](https://course.cse.ust.hk/comp3021/#HonorCode) if any extent of code plagiarism is found. 
 
## Grading Scheme

|                            | Percentage  | Remark                                                                    |
|----------------------------|-------------|---------------------------------------------------------------------------|
| Keep your Github repository private                 | 5%         | You must keep your repository private all the time.                        |
| At least 3 commits in different days                 | 5%         | You must commit in **three** **different** days in your repository.                        |
| Pass all sample tests      | 10%         | (# of passed tests / # of sample tests) * 10%                             |
| Pass all TA-only unit tests        | 40%         | (`# of passed tests` / `# of unit tests`) * 40%                                |
| Pass all TA-only integrated tests  | 30%         | (`# of passed tests` / `# of integrated tests`) * 30%                        |
| Code Style                 | 10%         | Get 10% if no error in code style checking. Deduct 1% for each 5 errors.  |
| Bonus Task 1: Pass tests for Bonus tasks 1 | Up to 10%    |  (`# of passed tests` / `# of all tests`) * 10%                                      |
| Bonus Task 2: new tests increasing branch coverage | Up to 5%    |  (`Branch Coverage of Your tests, including sample tests` - `Branch Coverage of Sample tests`) * 10% |
