# Assignment-2-COP4520

## Part 1: Cupcakes.java

```
Runtime: 14.416
```

The strategy I implemented was to first pick a leader among all of the guests(threads). Assume all of the guests decided before entering the labyrinth that the leader
is guest 0. Then the leader will be the one to keep count of how many guests have entered the labyrinth. The way this is done is that when the Minotaur picks a guest to enter either it is the leader or it is not the leader.  

Case 1: Guest Minotaur picks is the leader  

If a cupcake is in the labyrinth the leader eat it, everytime the leader eats a cupcake the leader knows that one new guest has previously entered the labyrinth, else the leader will do nothing and leave the labyrinth.  

Case 2: Guest Minotaur picks is not the leader  

If the guest sees a cupcake he will ignore it and leave the labyrinth, else if their is no cupcake and the guest has not in a previous iteration of visiting the labyrinth asked for a new cupcake he will tell the Minotaur to add a new cupcake, this is to ensure each guest should only put back a cupcake exactly once so that no guest is overcounted.   

Thus when the leader finally eats the n cupcakes where n = number of guests. Then the leader will know everyone has been accounted for.  

In my program I used a random number genarator to simulate the Minotaur having an equally likely chance of picking the guests. I first create all of my threads. Then I simulate the Minotaur picking a random guest if a thread tries to enter my synchronized method that simulates the maze without being chosen that thread is denied entry. Once the thread chosen enters the maze it will simulate the strategy discussed above. Finally I pick a new random number to simulate the Minotaur picking a new guest. This keeps repeating until all of the guests are accounted for. To keep track if a guest has or has not put back the cupcake I have a ThreadLocal variable ThreadLocal<Boolean> putCupcakeBack that is local to each thread.
 
 
## Part 2: SeeVase.java

```
Runtime: 22.416
```

I chose strategy 3 for this problem.   
 
 The disadvantages of strategy 1 are that all the guests are going to be fighting over who can get enter the room the moment it becomes available. It is possible that a guest could be trying to enter the room but because of all of the fighting between guests the guest(thread) might not ever be able to enter and thus get stuck. The disadvantage for strategy 2 is that even though it is better the strategy 1 where the guests can easily tell if the room is available or not since the moment the room becomes available any guests can enter still a guest can wait for a long period of time and still never be able to enter. 
 
 The advantage of strategy 3 is that since every guest waits in a queue they are guranteed that they will be given a chance to go at one point when the room becomes available since the person in front of them will let them know once they are finished.
 
 The way I implemented this was to represent a queue using a linkedlist, I kept a tail pointer for the linkedList, and for each thread a variable associated to it which was the node that came before it in the linkedlist(nodeBeforeCurrent) and the node that represents it(currentNode) in the linkedlist. Each node has a locked boolean variable that represents if the guest(thread) is currently waiting in the queue. if nodeBeforeCurrent has locked set to true which means the person before this guest is waiting to enter the room then this also implies the current guest needs to wait. When the thread enters the room and is finally going to leave then it sets its locked value to false that makes it so the guest that is right after this guest will know to stop waiting and enter the room. I also make it so that every guest has a 25% chance of entring the queue.
 
