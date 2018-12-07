import java.util.Scanner;
import java.util.Random;

public class Dungeon {
	
	private static Scanner kb = new Scanner(System.in);
	
	
	public static void main(String[] args) {
		// Welcome text
		// ----------------------------------------------------------------
		displayWelcomeMenu();
		
		
		// Create Hero
		// ----------------------------------------------------------------
		// Display choice of character, returns (1=Wizard, 2=Warrior)
		int heroPicker = promptCharacterChoice();
		
		// Used this line to clear out data type from previous input
		kb.nextLine();
		
		// Ask user to name their character
		// Returns a String data type of a character's name
		String heroName = promptCharacterName();
		
		// Create the hero class based on the user's input (1=Wizard, 2=Warrior)
		Hero defender = setHeroActor(heroPicker, heroName);
		
		// Greet your new hero
		displayHeroGreeting( defender.getName() );
		
		
		// Create the Boss Monster
		// ----------------------------------------------------------------
		Monster boss = new Monster("Asanam", 200, 20, 30, 25, 35);
		
		
		// Create the story and fights
		// ----------------------------------------------------------------
		// Tell a story of the hero's path
		String[] storyPaths = {
				"Our defender " + defender.getName() + " travels North...\n...\n...\nbut lurking in the bushes",
				defender.getName() + " travels West...\n...\n...\nbut encounters an old lady and decides to help but surprise...",
				defender.getName() + " continues West but again...\n...\n...",
				"A cave is seen ahead and our hero enters. " + defender.getName()  + " senses an evil presence and sees a dark shadow emerge"
			};		
		
		// Loop through the story paths and initiate a fight in between
		for (int p=0; p < storyPaths.length; p++) {
			// Move on to next story path
			System.out.println( "\n\n\n\n" + storyPaths[p] + "\n\n\n\n" );
			
			if ( p == (storyPaths.length-1) ) { // This is the last story path in the array
				// The boss fight
				// ----------------------------------------------------------------
				monsterFight(defender, boss);
				
				// Did the hero survive?
				if ( defender.isAlive() ) {
					System.out.println("\n\n\nYOU BEAT THE FINAL BOSS. CONGRATULATIONS!!");
				} else {
					System.out.println("\n\n\nGAME OVER LOSER!!");
				}
			} else {
				// Start a fight
				// ----------------------------------------------------------------
				Monster randomEnemy = setMonsterActor();
				monsterFight( defender, randomEnemy );
				
				// Terminate this loop if hero dead
				if ( defender.isDead() ) {
					break;
				}
			}
		}
		
		
		// Start the end credits
		// ----------------------------------------------------------------
		displayEndCredits( boss.getName() );
												
	} // end main 


	
	
	
	// ================================================================================
	// Methods begin 
	// ================================================================================
	// ================================================================================
	// ================================================================================
	public static void displayLogo() {
		System.out.println("________                                             ________          _____                   ___");            
		System.out.println("\\______ \\  __ __  ____    ____   ____  ____   ____   \\______ \\   _____/ ____\\____   ____    __| _/___________"); 
		System.out.println(" |    |  \\|  |  \\/    \\  / ___\\_/ __ \\/  _ \\ /    \\   |    |  \\_/ __ \\   __\\/ __ \\ /    \\  / __ |/ __ \\_  __ \\");
		System.out.println(" |    `   \\  |  /   |  \\/ /_/  >  ___(  <_> )   |  \\  |    `   \\  ___/|  | \\  ___/|   |  \\/ /_/ \\  ___/|  | \\/");
		System.out.println("/_______  /____/|___|  /\\___  / \\___  >____/|___|  / /_______  /\\___  >__|  \\___  >___|  /\\____ |\\___  >__|");   
		System.out.println("        \\/           \\//_____/      \\/           \\/          \\/     \\/          \\/     \\/      \\/    \\/");
	}

	public static void displayWelcomeMenu() {
		displayLogo();
		System.out.println("\nWell hello adventurer!");
		System.out.println("\nA nearby Village has tasked you with getting a Dragon femur since they are well known as medicinal ingredient.");
		System.out.println("Are you brave enough to enter the dungeon to find one?");
		System.out.println("There will be many monsters in your path.");
		System.out.println("Beware the great evil one called Asanam as she kills all who enter. \n");
	}
			
	public static int promptCharacterChoice() {
		System.out.println("\nYou have two heroes to choose from:");
		System.out.println("1) Wizard: has lower health, but its magic is stronger than the sword.");    
		System.out.println("2) Warrior: has high health and a mighty blade.");
		System.out.println("\nPress 1 to play as a Wizard or 2 to play as a Warrior.");
		
		return kb.nextInt(); // Return the user's response
	}
	
	public static String promptCharacterName() {
		System.out.println("Choose a name for your hero? ");
		return kb.nextLine(); // Return the user's character name
	}
	
	public static void displayHeroGreeting(String heroName) {
		System.out.println("\nWelcome " +heroName);
		System.out.println("You have chosen well " );
		System.out.println("Good Luck \n ");		
	}
	
	public static void displayEndCredits(String bossName) {
		System.out.println("______________________________________________________________________________________________________________________________");
		System.out.println("_______________________________________________________________________________________________________________________________");
		displayLogo();
		System.out.println("\nCreated by: Arty");
		System.out.println("\nDeveloped by: Arty");
		System.out.println("\nDesigned by: Arty");
		System.out.println("\nStory by: Arty");
		System.out.println("\n\nSpecial thanks to THe Wife. You know who you are.");
		System.out.println("\n\n\n\n\n                                           " + bossName + " will return.");
		System.out.println("_______________________________________________________________________________________________________________________________");
		System.out.println("_______________________________________________________________________________________________________________________________");
	}
	// ================================================================================
	// Methods begin 
	// ================================================================================
	// ================================================================================
	// ================================================================================
	
	
	// Returns a Hero object. (1=Wizard, 2=Warrior)
	public static Hero setHeroActor(int heroPicker, String heroName) {
		Hero dummy = null;

		switch (heroPicker) {
			case 1:
				dummy = new Hero( heroName + "(Wizard)", 100, 17, 20, 23, 27 );
				break;	
			case 2:
				dummy = new Hero( heroName + "(Warrior)", 200, 10, 15, 20, 25 );
				break;
		}
		
		return dummy;
	}
	
	// Returns a Monster object
	public static Monster setMonsterActor() {
		Random ran = new Random();
		int monster = ran.nextInt(3)+1;
		Monster dummy = null;

		switch (monster) {
			case 1: // monsterName, health, normalMinAttack, NormalMaxAttack, SpecialMinAttack, SpecialMaxAttack
				dummy = new Monster("Troll", 50, 15, 15, 15, 15);
				break;
			case 2:
				dummy = new Monster("Goblin", 30, 10, 12, 8, 16);
				break;
			case 3:
				dummy = new Monster("Ogre", 75, 10, 15, 20, 25);
				break;
		}
		
		return dummy;
	}

	public static void monsterFight(Hero defender, Monster enemy) {
		System.out.println("A " + enemy.getName() + " appears!");
		
		displayBattleGrounds(defender, enemy);

		// Control loop variable to determine if fighting continues
		boolean keepFighting = true;
		
		do {
			// Hero turn to attack monster
			// ----------------------------------------------------------------
			// Prompt player of available moves
			System.out.println("\nWhat will you do?");
			System.out.println("Press 1) Normal attack\nPress 2) Special attack\nPress 3) Run the fuck away");
			System.out.print("\nType your choice: ");
			
			
			// Apply damage from hero to monster
			int endam;
			String endamType = "";
			int heroAction = kb.nextInt();
			if ( heroAction == 1 ) {
				endam = defender.normalAttack();
			} else if ( heroAction == 2 ) {
				endamType = " special";
				endam = defender.normalAttack();
			} else {
				System.out.println( "\n" + defender.getName() + " runs like a bitch and lives to fight another day." );
				keepFighting = false;
				break;
			}
			
			enemy.takeDamage(endam);
			System.out.println( "\n" + defender.getName() + " hits "  + enemy.getName() + " for " + endam + endamType + " damage." );
			
			
			// Check if enemy is dead after attack
			if ( enemy.isDead() ) {
				System.out.println( "Hooray the " + enemy.getName() + " is slain" );
				displayBattleGrounds(defender, enemy);
				
				// Stop the status of the loop because enemy is dead
				keepFighting = false;
				// Break from this current loop (so it doesn't run the code below),
				// and goes straight to check the 'while expression'
				break;
				
			} else {
				// Monster still alive, monster turn to attack
				// ----------------------------------------------------------------
				
				// Apply damage from monster to hero
				int herodam = enemy.randomAttack();
				defender.takeDamage(herodam);
				System.out.println( "Oof....." + defender.getName()+ " gets hit by " + enemy.getName() + " for " + herodam + " damage." );
				
				displayBattleGrounds(defender, enemy);
				
				// Check if our hero is dead
				if (defender.isDead()) {
					System.out.println( "\n" + defender.getName() + " is weary from battle" );
					keepFighting = false;
					break;
				}
			}
		}  while ( keepFighting ); // continue if hero or enemy still has health > 0
	}
	

	public static void displayBattleGrounds(Hero defender, Monster enemy) {
		System.out.println( "-------------------------------------------------------------" );
		System.out.println( defender.getName() + "                     " + enemy.getName() );
		System.out.println( "Life: " + defender.healthStatus() + "                       Life: " + enemy.healthStatus() );
		System.out.println( "-------------------------------------------------------------" );
	}
	
	
} //end Dungeon class
