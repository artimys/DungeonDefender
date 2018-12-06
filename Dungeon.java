import java.util.Scanner;
import java.util.Random;

public class Dungeon {

	public static void main(String[] args) {
				
		Scanner kb = new Scanner(System.in);

		//set menus
		textWelcomeMenu();
		textChooseCharMenu();
		
		// choices are 1 or 2
		int heroPicker = kb.nextInt();
		
		kb.nextLine(); // used this line to clear out data type from previous input
		
		//prompt user for hero name
		System.out.println("Choose a name for your hero? ");
		String heroName = kb.nextLine();
		
		//set greeting/intro
		textYouChose(heroName);
		
		// Create the hero class based on the user's input (1=Wizard, 2=Warrior)
		Hero hero = setHeroActor(heroPicker, heroName);

		// First fight
		// create a random monster
		Monster enemy1 = setMonsterActor();
		monsterFight(hero, enemy1, kb);
		
		// Tell a story of the hero's path
		
		// Second fight
		// create a random monster
		Monster enemy2 = setMonsterActor();
		monsterFight(hero, enemy2, kb);
		

		// Tell a story of the hero's next path
		// Third fight
		// create a random monster
		Monster enemy3 = setMonsterActor();
		monsterFight(hero, enemy3, kb);
		
		
		// Boss fight
		Monster boss = new Monster("Asanam", 500, 20, 30, 25, 35);
		monsterFight(hero, boss, kb);			
		
		// TODO after defeating boss, it drops the medicinal ingredient that will save your town's people
		// TODO ROLL END CREDITS						
												
	} //end main 

	
	// Methods begin 
	// ================================================================================
	// ================================================================================
	// ================================================================================
	// ================================================================================
	
	public static void textWelcomeMenu() {
		
		System.out.println("________                                             ________          _____                   ___");            
		System.out.println("\\______ \\  __ __  ____    ____   ____  ____   ____   \\______ \\   _____/ ____\\____   ____    __| _/___________"); 
		System.out.println(" |    |  \\|  |  \\/    \\  / ___\\_/ __ \\/  _ \\ /    \\   |    |  \\_/ __ \\   __\\/ __ \\ /    \\  / __ |/ __ \\_  __ \\");
		System.out.println(" |    `   \\  |  /   |  \\/ /_/  >  ___(  <_> )   |  \\  |    `   \\  ___/|  | \\  ___/|   |  \\/ /_/ \\  ___/|  | \\/");
		System.out.println("/_______  /____/|___|  /\\___  / \\___  >____/|___|  / /_______  /\\___  >__|  \\___  >___|  /\\____ |\\___  >__|");   
		System.out.println("        \\/           \\//_____/      \\/           \\/          \\/     \\/          \\/     \\/      \\/    \\/");
		
		
		System.out.println("\nWell hello adventurer! ");
		System.out.println("\nA nearby Village has tasked you with getting a Dragon femur since they are well known as medicinal ingredient. ");
		System.out.println("Are you brave enough to enter the dungeon to find one? ");
		System.out.println("There will be many monsters in your path. ");
		System.out.println("Beware the great evil one called Asanam as she kills all who enter. \n "); // Manasa Ruth
		
	}// end welcomeMenu method
			
	public static void textChooseCharMenu() {		
		System.out.println("\nYou have two heroes to choose from: ");
		System.out.println("1) Wizard: has lower health, but its magic is stronger than the sword. ");    
		System.out.println("2) Warrior: has high health and a mighty blade. \n ");
		System.out.println("Press 1 to play as a Wizard or 2 to play as a Warrior. ");
		
	}// end textChooseCharMenu method
	
	public static void textYouChose(String heroName) {
		System.out.println("\nWelcome " +heroName);
		System.out.println("You have chosen well " );
		System.out.println("Good Luck \n ");		
	}//end textYouChose method
	
	
	// sets and object with hero values as chosen by user
	// (1=Wizard, 2=Warrior)
	public static Hero setHeroActor(int heroPicker, String heroName) {
		Hero dummy = null;

		switch (heroPicker) {
			case 1:
				dummy = new Hero( heroName + " (Wizard)", 100, 17, 20, 23, 27 );
				break;	
			case 2:
				dummy = new Hero( heroName + " (Warrior)", 200, 10, 15, 20, 25 );
				break;
		}
		
		return dummy;
	}//end setHeroActor method
	
	public static Monster setMonsterActor() {
		Random ran = new Random();
		int monster = ran.nextInt(3)+1;
		Monster dummy = null;

		switch (monster) {
			case 1: //monster, health, normalMinAttack, NormalMaxAttack, SpecialMinAttack, SpecialMaxAttack
				dummy = new Monster("Troll", 50, 15, 15, 15, 15);
				break;
						
			case 2:
				dummy = new Monster("Goblin", 30, 10, 12, 8, 16);
				break;
			
			case 3:
				dummy = new Monster("Ogre", 75, 10, 15, 20, 25);
				break;
					
		}//end switch
		
		return dummy;
	}//end setMonsterActor method

	// Declaring the method or defining the method => creating the method
	// whatever is in the parenthesis is called ARGUMENTS (data that the method is suppose to get)
	public static void monsterFight(Hero hero, Monster enemy, Scanner kb) {
		System.out.println("A " + enemy.getName() + " appears\n");
		System.out.println("What will you do?");
		
		
		hero.displayNameAndHealth();
		enemy.displayNameAndHealth();
		
		// control loop variable
		boolean keepFighting = true;
		 do {	
				// Hero attacks monster
				System.out.println("\n1. Normal Attack 2. Special Attack ");
			int endam;
			String endamType = "";
			if (kb.nextInt() == 1 ) {
				// do normal attack
				endam = hero.normalAtk();
			} else {
				// special attack
				endamType = " special";
				endam = hero.specialAtk();
			}
			
			enemy.takeDamage(endam);
			System.out.println( hero.getName() + " hits "  + enemy.getName() + " for " + endam + endamType + " damage \n"  );
			
			
			if (enemy.isDead()) {
				// dead
				System.out.println("Hooray the " + enemy.getName() + " is slain ");
				keepFighting = false;
				break;
				
			} else { // Monster's turn to attack
				// it's alive
				// it wants payback and will attack				
				int herodam = enemy.normalAtk(); 
				hero.takeDamage(herodam); 
				System.out.println("\nOof....." + hero.getName()+ " gets hit by " + enemy.getName() + " for " + herodam + "\n" );
				
				hero.displayNameAndHealth();
				enemy.displayNameAndHealth();
				
				if (hero.isDead()) {
					System.out.println(hero.getName() + " is weary from battle");
					keepFighting = false;
					break;
				}

			}
			
		}  while ( keepFighting ); // continue if hero or enemy still has health > 0
	}
}//end Dungeon class
