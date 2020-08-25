=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: 17811434
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. File I/O: I have a high score system, in which a player can request to see the
     five highest scores by clicking a button. The game then reads the scores file,
     sorts the scores within, and displays the top five. Once a player finishes their
     own game, they input their name, and the scores file is overwritten to add the
     new score.

  2. Collections: I modeled all the arrows in the game with LinkedLists, due to the
     fact that LinkedLists allow easy addition and removal of objects. Collections also
     made drawing and animating very easy, since I could iterate over the entire
     Collection and have each individual arrow draw or move.

  3. Sub-typing/Dynamic Dispatch: My abstract classes, Monster and Arrow, show the
     use of sub-typing since each class that extends each abstract class uses methods
     inherit from it. I also implemented the interface Serializable for the class
     score, although I did not write the interface.

  4. Advanced Game Topic (Concurrency): My game made major use of concurrency, since
     many things happen at the same time during it. All arrows and the monster move
     at the same time, the game timer counts down, and the game also has to handle
     whatever user input is going on too. By using Collections to model arrows, a
     separate timer to model the game clock, and enabling user interactions to impact
     the Collection of arrows in place, the game was able to handle all these things
     at once. 

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  Game state: I have two abstract classes, Monster and Arrow. Monster, which
  has subclasses Monster1 and Monster2, depcts the enemies of the game. Enemies
  are simply animated and don't physically interact with other objects, but
  have a health bar and lose health every time the player correctly destroys 
  an arrow. Arrow, which has subclasses LeftArrow, RightArrow, UpArrow, and
  DownArrow, models the scrolling arrows that the user has to hit.
  
  File I/O: I have one class called Scores, which implements Serializable.
  Scores models a single score, composed of a player's name, their mode, and their
  score.  

- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  My only stumbling block was thinking of a more efficient way to model random
  deployment of the arrows. My way of doing it (having arrows only move once the
  timer count is past their deployment time) works, but I feel like it could've been
  more effective.

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you re-factor, if given the chance?
  
  I think my game design was pretty good. All my objects were well-separated, my game
  state variables (score, time, etc.) were distinct, and nothing went wrong with
  my concurrency. If given the chance, I would re-factor collisions to make them
  more correctly model when the player destroys an arrow, since it is currently
  still based on bounding boxes.

========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  N/A.
