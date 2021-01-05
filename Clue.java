/* Joanne Baba
 * 06/09/17
 * 
 * Program description:
 * In Clue, users must correctly guess the answers to the murder mystery: who did it, with what, and where.
 * There are 6 suspect cards, 6 weapon cards, and 9 room cards (21 in total).
 * 3 random cards are put away in an envelope which holds the answers (one murder, one weapon, and one room).
 * The rest of the cards are randomly assigned to either the user or the computer (each get 9 cards).
 * By travelling to different rooms, the user can start a rumour and guess the answer to the mystery, using the room they are in.
 * The computer then has to disprove the rumour by revealing ONE of the aforementioned rumour cards, even if they have more than one.
 * This repeats until the user thinks they know the answer.
 * Through logical reasoning and elimination, the user determines the answer to the mystery.
 * To make their final accusation, they must travel to the Pool and state their accusation (who, what, where).
 * If they are correct, they win. If not, they lose.
 * The user can keep track of their cards on their Clue Sheet.
 * The user can choose to play as many times as they want.
 * Valid answers are checked (nothing is case sensitive and inputs are trimmed).
 */

class Clue
{
  public static void main (String [] args)
  {
    // declares and assigns variables
    String[] cards = {"Green", "Mustard", "Peacock", "Plum", "Scarlet", "White", "Bat", "Candlestick", "Knife", "Poison", "Rope", "Trophy", "Dining Room", "Guest Room", "Hall", "Kitchen", "Living Room", "Observatory", "Patio", "Spa", "Theatre"};
    String[] answers = envelope();
    String[] hand = userHand(answers);
    String[] comp = compHand(answers, hand);
    String[] boxes = new String[21];
    String playAgain = "";
    String person = "";
    String weapon = "";
    String room = "";
    String check = "";
    String deduction = "";
    String checkAgain = "";    
    int l = 0;
    int j = 0;
    int k = 0;
    int m = 0;
    
    do {     
      // resets variables
      answers = envelope();
      hand = userHand(answers);
      comp = compHand(answers, hand);
      boxes = new String[21];
      playAgain = "";
      person = "";
      weapon = "";
      room = "";
      check = "";
      deduction = "";
      checkAgain = "";      
      l = 0;
      j = 0;
      k = 0;
      m = 0;
      
      // prints introduction screen
      System.out.println();
      System.out.println("Welcome to Clue™");
      System.out.println();
      System.out.println("It's midnight. Someone has just been murdered in Estate 141.");
      System.out.println("You and another person have been hired to solve the murder.");
      System.out.println("The goal: to identify who did it, where, and with which weapon.");
      System.out.println("There are 6 suspects, 9 rooms, and 6 weapons.");
      System.out.println("The cards with the answer to the mystery are kept in an envelope in the Pool.");
      System.out.println("The rest of the evidence cards have been split between you and the other person.");
      System.out.println("There is one evidence card for each suspect, weapon, and room.");      
      System.out.println();
      System.out.println("Here is your Clue Sheet:");    
      
      // assigns filled circle or empty circle to each card based on the user's hand
      for (j = 0; j < cards.length; j++) {
        for (k = 0; k < hand.length; k++) {
          // if user has card, check it off
          if (hand[k].equals(cards[j])) {
            boxes[j] = "•";
            k = cards.length;
          } else { // otherwise leave empty
            boxes[j] = "o";
          }
        }   
        k = 0;
      }
      
      // prints Clue Sheet
      displaySheet(boxes);
      
      System.out.println();
      System.out.println("Your Clue Sheet holds the evidence cards you have - anything with a filled circle is one of your evidence cards.");     
      System.out.println("In order to solve the mystery, you must travel around the Estate and start rumours.");
      System.out.println("The point of a rumour is to try and determine the answer to the mystery.");
      System.out.println("Once you enter a room, you must use the room you are in to start the rumour.");
      System.out.println("For example, if you are in the Observatory, your rumour may be: “I think Green did it in the Observatory with the Candlestick.”");
      System.out.println("The other person will then try to disprove your rumour by revealing one evidence card from your rumour, regardless of how many they have.");
      System.out.println("If they do not have any of the cards from your rumour, they will not reveal a card.");
      System.out.println("For example, using the previous rumour, the other person may show you the Green evidence card.");
      System.out.println("As you gather more evidence, you can keep track of it on your Clue Sheet.");  
      System.out.println("Once you think you've solved the mystery, you must go to the Pool to make an accusation.");
      System.out.println("If you're right, you win the game.");
      System.out.println("If not, the murderer kills you and you fail the mission.");
      System.out.println();
      System.out.println("Hurry. Time is running out.");
      System.out.println();
      
      // loops rumour prompts until user goes to pool to make accusation
      do {
        // prompts user to enter a room, and checks for valid input
        do {
          System.out.println("What room would you like to move to?");
          room = In.getString(); // gets room
          room = format(room); // formats room
          
          // prints warning message if room entered is invalid
          if (!isRoomValid(room)) {
            System.out.println("That is an invalid room.");
          }
        } while (!isRoomValid(room)); // checks for valid room
        
        // prints room description
        System.out.println("You are in the " + room + ".");
        
        // continue if room is not the pool
        if (!room.equals("Pool")) {
          // prompts user to enter a person, and checks for valid input
          do {
            System.out.println("Please enter the person you would like to start the rumour with.");
            person = In.getString(); // gets person
            person = format(person); // formats person
            
            // prints warning message if person entered is invalid
            if (!isPersonValid(person)) {
              System.out.println("That is an invalid person.");
            }
          } while (!isPersonValid(person)); // checks for valid person
          
          // prompts user to enter a weapon, and checks for valid input
          do {
            System.out.println("Please enter the weapon you would like to start the rumour with.");
            weapon = In.getString(); // gets weapon
            weapon = format(weapon); // formats weapon
            
            // prints warning message if weapon entered is invalid
            if (!isWeaponValid(weapon)) {
              System.out.println("That is an invalid weapon.");
            }
          } while (!isWeaponValid(weapon)); // checks for valid weapon
          
          System.out.println();
          // prints rumour
          System.out.println("Rumour: I think " + person + " did it in the " + room + " with the " + weapon + ".");    
          // prints computer's reveal
          displayReveal(comp, room, person, weapon);
          System.out.println();
          
          // prompts user to enter whether or not they want to eliminate something off the Clue Sheet, and checks for valid input
          do {
            System.out.println("Would you like to check something off your Clue Sheet? (yes/no/y/n)");
            check = In.getString(); // gets check
            check = check.trim(); // trims answer
            
            // prints warning message if input entered is invalid
            if (!isValid(check)) {
              System.out.println("That is an invalid answer.");
            }        
          } while (!isValid(check));
          
          // continue if the user wants to check off something
          if (check.equalsIgnoreCase("yes") || check.equalsIgnoreCase("y")) {
            do {
              // prompts user to enter a deduction, and checks for valid input
              do {
                System.out.println("Please enter the card you would like to check off your Clue Sheet.");
                deduction = In.getString(); // gets deduction
                deduction = format(deduction); // formats deduction
                
                // prints warning message if deduction entered is invalid
                if (!isDeductionValid(deduction)) {
                  System.out.println("That is an invalid card.");
                }
              } while (!isDeductionValid(deduction)); // checks for valid input
              
              // updates Clue Sheet with user's deduction
              for (l = 0; l < cards.length; l++) {
                // lets user know if card has already been checked off
                if (deduction.equals(cards[l]) && boxes[l].equals("•")) {
                  System.out.println("Just so you know, that card has already been checked off. And you call yourself a detective. Unbelievable.");
                  l = cards.length;
                } else if (deduction.equals(cards[l])) { // otherwise checks off card
                  boxes[l] = "•";
                  l = cards.length;
                }
              }
              
              // prompts user to enter whether they want to check off more, and checks for valid input
              do {
                System.out.println("Would you like to check something else off your Clue Sheet? (yes/no/y/n)");
                checkAgain = In.getString(); // gets answer
                checkAgain = checkAgain.trim(); // trims answer
                
                // prints warning message if answer entered is invalid
                if (!isValid(checkAgain)) {
                  System.out.println("That is an invalid answer.");
                }
              } while (!isValid(checkAgain)); // checks for valid input
            } while (checkAgain.equalsIgnoreCase("yes") || checkAgain.equalsIgnoreCase("y")); // prompts user to enter deductions till user enters no or n
            
            // prints updated Clue Sheet
            System.out.println();
            displaySheet(boxes);
            System.out.println();
          }           
        }
      } while (!room.equals("Pool")); // exits loop once user enters pool
      
      System.out.println("It is now time to make an accusation.");
      
      // prompts user to enter a person for the accusation, and checks for valid input
      do {
        System.out.println("Please enter the murderer.");
        person = In.getString(); // gets person
        person = format(person); // formats person
        
        // prints warning message if person entered is invalid
        if (!isPersonValid(person)) {
          System.out.println("That is an invalid person.");
        }
      } while (!isPersonValid(person)); // checks for valid person
      
      // prompts user to enter a weapon for the accusation, and checks for valid input
      do {
        System.out.println("Please enter the murder weapon.");
        weapon = In.getString(); // gets weapon
        weapon = format(weapon); // formats weapon
        
        // prints warning message if weapon entered is invalid
        if (!isWeaponValid(weapon)) {
          System.out.println("That is an invalid weapon.");
        }
      } while (!isWeaponValid(weapon)); // checks for valid weapon
      
      // prompts user to enter a room for the accusation, and checks for valid input
      do {
        System.out.println("Please enter the room that the murder took place in.");
        room = In.getString(); // gets room
        room = format(room); // formats room
        
        // prints warning message if room entered is invalid
        if (!isRoomValid(room) || room.equals("Pool")) {
          System.out.println("That is an invalid room.");
        }
      } while (!isRoomValid(room) || room.equals("Pool")); // checks for valid room
      
      // prints answers in envelope
      System.out.println();
      System.out.println("CONFIDENTIAL");
      for (m = 0; m < answers.length; m++) {
        System.out.println(answers[m]);
      }
      System.out.println();
      
      // prints congratulatory statement if user is correct
      if (isCorrect(answers, person, weapon, room)) {
        System.out.println("Congratulations! Your accusation is correct and you have solved the murder. Time to ask for a promotion.");
      } else { // prints letdown statement if user is wrong
        System.out.println("Your accusation is incorrect. You have failed the mission and the murderer has killed you.");
      }
      System.out.println();
      
      // prompts user to enter whether or not they want to play again, and checks for valid input
      do {
        System.out.println("Would you like to play again? (yes/no/y/n)");
        playAgain = In.getString(); // gets answer
        playAgain = playAgain.trim(); // trims answer
        
        // prints warning message if answer entered is invalid
        if (!isValid(playAgain)) {
          System.out.println("That is an invalid answer.");
        }      
      } while (!isValid(playAgain));
      
    } while (playAgain.equalsIgnoreCase("yes") || playAgain.equalsIgnoreCase("y")); // repeats game when user enters yes or y
    
    // exits loop when user enters no or n and prints thank you statement
    System.out.println();
    System.out.println("Thank you for playing.");  
  }
  
  // Capitalizes and trims the user's input and returns the formatted input
  public static String format (String input)
  {
    // declares and assigns variables
    String newInput = "";
    int i = 0;
    
    // trims input
    input = input.trim();
    // makes input all lowercase
    input = input.toLowerCase();
    
    // capitalizes the first letter of every word in the string
    for (i = 0; i < input.length(); i++) {
      if (i == 0) {
        newInput += String.valueOf(input.charAt(0)).toUpperCase();
      } else if (i > 0 && input.charAt(i - 1) == ' ') {
        newInput += String.valueOf(input.charAt(i)).toUpperCase();
      } else {
        newInput += input.charAt(i);
      }
    }
    
    // returns formatted input
    return newInput;
  }
  
  // Randomly generates the answers to the mystery and returns an array
  public static String[] envelope ()
  {   
    // declares and assigns variables
    String[] people = {"Green", "Mustard", "Peacock", "Plum", "Scarlet", "White"};
    String[] weapons = {"Bat", "Candlestick", "Knife", "Poison", "Rope", "Trophy"};
    String[] rooms = {"Dining Room", "Guest Room", "Hall", "Kitchen", "Living Room", "Observatory", "Patio", "Spa", "Theatre"};
    String[] answers = new String[3];
    int pick = 0;
    int i = 0;
    
    // randomly chooses the answers
    for (i = 0; i < answers.length; i++) {   
      // chooses the murderer
      if (i == 0) {
        pick = (int) (6 * Math.random());
        answers[i] = people[pick];
      } else if (i == 1) { // chooses the weapon
        pick = (int) (6 * Math.random());
        answers[i] = weapons[pick];
      } else if (i == 2) { // chooses the room
        pick = (int) (9 * Math.random());
        answers[i] = rooms[pick];
      }
    }
    
    // returns answers to mystery
    return answers;
  }
  
  // Randomly generates the user's hand and returns an array
  public static String[] userHand (String[] key)
  {
    // declares and assigns variables
    String[] cards = {"Green", "Mustard", "Peacock", "Plum", "Scarlet", "White", "Bat", "Candlestick", "Knife", "Poison", "Rope", "Trophy", "Dining Room", "Guest Room", "Hall", "Kitchen", "Living Room", "Observatory", "Patio", "Spa", "Theatre"};
    String[] chosen = new String[12];
    String[] hand = new String[9];
    int pick = 0;
    int m = 0;
    int i = 0;
    int a = 0;
    int j = 0;
    
    // assigns the answer cards to the chosen array
    for (m = 0; m < key.length; m++) {     
      chosen[a] = key[m];
      a++;  
    }
    
    // randomly chooses the user's hand
    for (i = 0; i < hand.length; i++) {
      // randomly generates a number to select from the array
      pick = (int) (21 * Math.random());
      for (j = 0; j < chosen.length; j++) {
        // checks if the picked card has already been chosen
        if (cards[pick].equals(chosen[j])) {
          pick = (int) (21 * Math.random());
          j = -1;
        }
      }
      j = 0;
      chosen[a] = cards[pick]; // assigns picked card to chosen array
      a++;
      hand[i] = cards[pick]; // assings picked card to the user's hand
    }   
    
    // returns user's hand
    return hand;
  }  
  
  // Generates the computer's hand and returns an array
  public static String[] compHand (String[] key, String[] user)
  {
    // declares and assigns variables
    String[] cards = {"Green", "Mustard", "Peacock", "Plum", "Scarlet", "White", "Bat", "Candlestick", "Knife", "Poison", "Rope", "Trophy", "Dining Room", "Guest Room", "Hall", "Kitchen", "Living Room", "Observatory", "Patio", "Spa", "Theatre"};
    String[] chosen = new String[12];
    String[] comp = new String[9];
    boolean isChosen = false;
    int m = 0;
    int n = 0;
    int i = 0;
    int a = 0;
    int b = 0;
    int j = 0;
    
    // assigns the answer cards to the chosen array
    for (m = 0; m < key.length; m++) {     
      chosen[a] = key[m];
      a++;  
    }
    // assigns the user's cards to the chosen array
    for (n = 0; n < user.length; n++) {     
      chosen[a] = user[n];
      a++;  
    }
    
    // assigns the leftover cards to the computer's hand    
    for (i = 0; i < cards.length; i++) {
      // checks if card is in chosen array
      for (j = 0; j < chosen.length; j++) {
        // if card is already chosen, the next card in the cards array is checked
        if (cards[i].equals(chosen[j])) {
          isChosen = true;
          j = chosen.length;
        } else { // otherwise continue checking the chosen array
          isChosen = false;
        }
      }
      j = 0;
      // assigns card to the computer's hand if the card is not chosen
      if (isChosen == false) {
        comp[b] = cards[i];
        b++;
      }
    }    
    
    // returns computer's hand
    return comp;
  }
  
  // Checks if answer is valid, returns true or false
  public static boolean isValid (String answer)
  {  
    // returns whether answer is valid
    return (answer.equalsIgnoreCase("yes") || answer.equalsIgnoreCase("no") || answer.equalsIgnoreCase("y") || answer.equalsIgnoreCase("n"));
  }
  
  // Checks if room is valid, returns true or false
  public static boolean isRoomValid (String place)
  {
    // declares and assigns variables
    String[] rooms = {"Dining Room", "Guest Room", "Hall", "Kitchen", "Living Room", "Observatory", "Patio", "Spa", "Theatre", "Pool"};
    boolean isValid = false;
    String room = place;
    int i = 0;
    
    // checks if room entered by user is a valid room
    for (i = 0; i < rooms.length; i++) {
      if (room.equalsIgnoreCase(rooms[i])) {
        isValid = true;
        i = rooms.length;
      }
    }
    
    // returns whether room is valid
    return isValid;
  }  
  
  // Checks if person is valid, returns true or false
  public static boolean isPersonValid (String suspect)
  {
    // declares and assigns variables
    String[] people = {"Green", "Mustard", "Peacock", "Plum", "Scarlet", "White"};
    boolean isValid = false;
    String person = suspect;
    int i = 0;
    
    // checks if person entered by user is a valid person
    for (i = 0; i < people.length; i++) {
      if (person.equalsIgnoreCase(people[i])) {
        isValid = true;
        i = people.length;
      }
    }
    
    // returns whether person is valid
    return isValid;
  }
  
  // Checks if weapon is valid, returns true or false
  public static boolean isWeaponValid (String arms)
  {
    // declares and assigns variables
    String[] weapons = {"Bat", "Candlestick", "Knife", "Poison", "Rope", "Trophy"};
    boolean isValid = false;
    String weapon = arms;
    int i = 0;
    
    // checks if weapon entered by user is a valid weapon
    for (i = 0; i < weapons.length; i++) {
      if (weapon.equalsIgnoreCase(weapons[i])) {
        isValid = true;
        i = weapons.length;
      }
    }
    
    // returns whether weapon is valid
    return isValid;
  }
  
  // Checks if deduction is valid, returns true or false
  public static boolean isDeductionValid (String card)
  {
    // declares and assigns variables
    String[] cards = {"Green", "Mustard", "Peacock", "Plum", "Scarlet", "White", "Bat", "Candlestick", "Knife", "Poison", "Rope", "Trophy", "Dining Room", "Guest Room", "Hall", "Kitchen", "Living Room", "Observatory", "Patio", "Spa", "Theatre"};
    boolean isValid = false;
    String deduction = card;
    int i = 0;
    
    // checks if deduction entered by user is a valid card
    for (i = 0; i < cards.length; i++) {
      if (deduction.equalsIgnoreCase(cards[i])) {
        isValid = true;
        i = cards.length;
      }
    }
    
    // returns whether deduction is valid
    return isValid;
  }
  
  // Displays the Clue Sheet
  public static void displaySheet (String[] circles)
  {
    // declares and assigns variables
    String[] cards = {"Green", "Mustard", "Peacock", "Plum", "Scarlet", "White", "Bat", "Candlestick", "Knife", "Poison", "Rope", "Trophy", "Dining Room", "Guest Room", "Hall", "Kitchen", "Living Room", "Observatory", "Patio", "Spa", "Theatre"};
    String[] boxes = circles;
    int i = 0;
    
    // prints sheet
    System.out.println("________________________________");
    System.out.println("           Clue Sheet");
    System.out.println("________________________________");
    System.out.println("            SUSPECTS");
    for (i = 0; i <= 5; i++) {
      System.out.println(cards[i] + " " + boxes[i]);
    }
    System.out.println("             WEAPONS");
    for (i = 6; i <= 11; i++) {
      System.out.println(cards[i] + " " + boxes[i]);
    }
    System.out.println("              ROOMS");
    for (i = 12; i <= 20; i++) {
      System.out.println(cards[i] + " " + boxes[i]);
    }
    System.out.println("________________________________");
  }
  
  // Displays the computer's reveal to the user's rumour
  public static void displayReveal (String[] compHand, String place, String suspect, String arms)
  {
    // declares and assigns variables
    String show = "";
    int i = 0;
    
    // checks if computer has one of the rumour cards
    for (i = 0; i < compHand.length; i++) {
      if (compHand[i].equals(place)) {
        show = compHand[i];
        i = compHand.length;
      } else if (compHand[i].equals(suspect)) {
        show = compHand[i];
        i = compHand.length;
      } else if (compHand[i].equals(arms)) {
        show = compHand[i];
        i = compHand.length;
      }
    }
    
    if (show.equals("")) {
      // prints computer's lack of cards
      show = "The other person does not have any of the mentioned rumour cards.";   
    } else {
      // prints computer's card   
      System.out.println("The other person reveals...");
    }
    
    // prints computer's reply
    System.out.println(show);
  }
  
  // Checks if user's accusation is correct, returns true or false
  public static boolean isCorrect (String[] key, String suspect, String arms, String place)
  {
    // declares and assigns variables
    boolean isCorrect = true;
    int i = 0;
    
    // checks if user's accusation is correct
    for (i = 0; i < key.length; i++) {
      if (!suspect.equals(key[0])) {
        isCorrect = false;
        i = key.length;
      }

      if (!arms.equals(key[1])) {
        isCorrect = false;
        i = key.length;
      }

      if (!place.equals(key[2])) {
        isCorrect = false;
        i = key.length;
      }
    }
    
    // returns whether accusation is correct
    return isCorrect;
  }
}