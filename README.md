# Assignment-2-COP4520

## Part 1: Cupcakes.java

```
Runtime: 14.416
```

The strategy I implemented was to first pick a leader among all of the guests(threads). Assume all of the guests decided before entering the labyrinth that the leader
is guest 0. Then the leader will be the one to keep count of how many guests have entered the labyrinth. The way this is done is that when the minotar picks a guest to enter 
the labyrinth if the guest is the leader and he sees the cupcake he will eat it, everytime the leader eats the cupcake the leader knows that one new guest has previously entered the labryinth.
If the guest that has entered the labyrinth is not the leader then if that guest sees a cupcake he will ignore it else if their is no cupcake and the guest has not already asked for a new cupcake he will tell the minoutar to add
a new cupcake. Thus when the leader finally eats the n cupcakes where n = number of guests. Then the leader will know everyone has been accounted for.

 
