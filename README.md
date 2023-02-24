# Assignment-2-COP4520

## Part 1: Cupcakes.java

```
Runtime: 14.416
```

The strategy I implemented was to first pick a leader among all of the guests(threads). Assume all of the guests decided before entering the labyrinth that the leader
is guest 0. Then the leader will be the one to keep count of how many guests have entered the labyrinth. The way this is done is that when the Minotaur picks a guest to enter either it is the leader or it is not the leader.
Case 1: guest Minotaur picks is the leader
If a cupcake is in the labyrinth the leader eat it, everytime the leader eats a cupcake the leader knows that one new guest has previously entered the labyrinth, else the leader will do nothing and leave the labyrinth.
Case 2: guest Minotaur picks is not the leader
if the guest sees a cupcake he will ignore it and leave the labyrinth, else if their is no cupcake and the guest has not in a previous iteration of visiting the labyrinth asked for a new cupcake he will tell the Minotaur to add a new cupcake, this is to ensure each guest should only put back a cupcake exactly once so that no guest is overcounted. 
Thus when the leader finally eats the n cupcakes where n = number of guests. Then the leader will know everyone has been accounted for.

 
