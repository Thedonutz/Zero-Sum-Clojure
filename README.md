# project-class

@Author
JEFF MORGAN
--DO NOT STEAL FOR YOUR OWN PROJECT!!!

## Usage


Functional Programming / Concurrency

THE PROBLEM:
Consider a simple game consisting of a series of n coins (n is even) of various denominations laid in a
row. (For our purposes, the denominations do not have to match any actual real-world coins, but all
have a value greater than 0.) Two players take turns, alternately removing a coin from either end of the
row and keeping the coin so removed. Play continues until all coins are removed; the object is to end
with a higher total value than the opponent.
Note that a greedy strategy will not work in the general case. Consider the following arrangement of a
game in progress:
1 5 10 5 25 5
The current player can choose either the 1 coin at the left or the 5 coin on the right. But choosing the 5
coin on the right will make it possible for the opponent to select the 25 on their next turn, and probably
win the game.
Some thought will demonstrate the following points:
• This is a 0-sum game; there is a certain amount of value to be distributed, and the strategy
involves maximizing one’s share of that value.
• The opponent will be trying to maximize their score, and therefore minimize ours.
• Consider a row of coins from Ci to Cj.
◦ Our score will increase by the value of one of the coins on the end (Ci or Cj) plus whatever
we can expect to get from what remains (Ci+1 to Cj or Ci to Cj-1) when it’s the opponent’s
turn to move.
◦ The opponent can take the coin from whatever remains when we have made our selection.
◦ And the opponent will be playing to minimize our score.
• Therefore the value at this position (call it Vi,j) is the smaller of the positions that result after
my choice, plus the value of the coin I take, and I am trying to maximize this value.
• Putting it all together, Vi,j = max( {Ci + min(Vi+1,j-1, Vi+2,j)}, {Cj + min(Vi, j-2, Vi+1,j-1) } )
• The first term is the result of my picking Ci, the second term picking Cj.
• A brief animation walking through this can be found at
https://people.cs.clemson.edu/~bcdean/dp_practice/dp_10.swf
From the above, there is an obvious recursive solution. The subproblems overlap, so a dynamicprogramming
strategy would be optimal IF our code could have side effects (such as storing partial
results). But since we’re interested in exploring functional programming, we’ll tolerate a less efficient
algorithm in the interest of keeping our code simpler. 

## License

Copyright © 2017 FIXME

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.
