import java.util.Random;

public class Monster extends DungeonCharacter {
	
	private Random ran = new Random();
	
	// Constructor
	public Monster(String argName, int argHealth, int argNormalMinAtk, int argNormalMaxAtk, int argSpecialMinAtk, int argSpecialMaxAtk) {
		super(argName, argHealth, argNormalMinAtk, argNormalMaxAtk, argSpecialMinAtk, argSpecialMaxAtk);
		
	}

	
	
	
	public int randomAttack() {
		if ( ran.nextInt(1) == 0 ) {
			return super.normalAttack();
		} else {
			return super.specialAttack();
		}
	}

	/*
	public int dropPotion() {
		if ( ran.nextInt(1) == 0 ) {
			return 25;
		} else {
			return 0;
		}
	}
	*/

}