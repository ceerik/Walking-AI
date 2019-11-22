# Walking-AI

A Java program where an AI controls the movement of n-limbed star-shaped creatures to make them move as far across the screen as possible.

With a simple graphical representation using ch.aplu.turtle.

Not yet funtional; main things remaining to implement:
  - Although everything is written to work in the turtle framework, no drawing is being done yet.
  - AI.
  - General bugtesting.
  - Completing the partially complete collision detection system in Limb.
  - Implementing the move() method in Limb; most support methods (except the point directly above) are complete.
  - Implementing the runnable portion of World.
  - Creating a Main() method.
 
What IS done is most of the basic structure and methods of all non-AI classes (excepting graphics); mainly concerning itself with the basic data structure, the physics of the world, and the creatures inhabiting it.

The rules of physics followed by the creatures of this world are:
  - Fall until an endpoint of a limb touches the ground.
  - A limb is anchored if it is the first limb to touch the ground or it touches the ground in front of the last anchored limb.
  - An anchored limb moves the center of the creature (and all limbs with it) as it would otherwise have moved its foot.
  - When the center of the creature is moved, it simply changes x and y coordinates, it does not pivot. (And along with it it also changes the positions
    of the rest of the limbs in the same manner.)
  - There can be only one anchoredLimbMove per turn, any number of other limbs may end up being anchored at some point during the turn,
    however only the limb that is anchored at the beginning of the turn will make an anchored move.
  - Only endpoints of limbs can interact with the ground, the creature centrepoint falls straight through.
  - The position of the creature centrepoint does count for calculation of the creatures viability though.
 
 Potential improvements (beyond just implementing the unimplemented stuff):
  - The second to last law of physics of this world (creature centrepoints cannot collide with the ground) could rather elegantly be resolved by making an interface called "Collidable" (or somesuch) and implementing that interface in both the Creature and Limb classes (Currently the Limb class is the only one to have collision detection (via couple of different methods depending on which type of movement is attempted).)
  - If you wanted to improve multi-threaded performance you could make the limbs runnable, since they're completely independent from each other during each turn, excepting whichever limb is anchored at the beginning of the turn (as its movement, the first of every turn, has the potential to affect all other limbs of the creature.). This would cause problems with thread-safe behaviour regarding the new anchorings of limbs during a turn; you could, however, simply replace the old anchoring procedure with check once all limbs of a creature are done moving and swap the anchor to whichever one is the furthest along in the x-axis.


Dependencies:
  - ch.aplu.turtle (see https://www.aplu.ch/home/apluhomex.jsp?site=65)
