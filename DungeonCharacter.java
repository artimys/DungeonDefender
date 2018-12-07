public class DungeonCharacter {
	// Declares properties of the class
	// ------------------------------------------------------------------------
	// ------------------------------------------------------------------------
	private String name;
	private int healthMax;
	private int health;
	
	// Variable for normAtk and specialAtk
	private int nMinAttack;
	private int nMaxAttack;
	private int sMinAttack;
	private int sMaxAttack;
	
	
	// Constructor
	// ------------------------------------------------------------------------
	// ------------------------------------------------------------------------
	public DungeonCharacter(String argName, int argHealth, int argNormalMinAtk, int argNormalMaxAtk, int argSpecialMinAtk, int argSpecialMaxAtk) {
		this.name = argName;
		this.healthMax = argHealth;
		this.health = argHealth;
		this.nMinAttack = argNormalMinAtk;
		this.nMaxAttack = argNormalMaxAtk;
		this.sMinAttack = argSpecialMinAtk;
		this.sMaxAttack = argSpecialMaxAtk;
	}

	
	// Getters and Setters
	// ------------------------------------------------------------------------
	// ------------------------------------------------------------------------
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		this.health = health;
	}
	

	// Battle Methods
	// ------------------------------------------------------------------------
	// ------------------------------------------------------------------------
	public void takeDamage(int damage) {
		health = health - damage;
	}
	public boolean isDead() {
		return this.health <= 0;
	}
	public boolean isAlive() {
		return this.health > 0;
	}
	
	public int normalAttack() {
		// Cast the return double data type to int
		return (int) Math.round( (Math.random() * ( this.nMaxAttack - this.nMinAttack)) + this.nMinAttack );
	}
	public int specialAttack() {
		// Cast the return double data type to int
		return (int) Math.round( (Math.random() * ( this.sMaxAttack - this.sMinAttack )) + this.sMinAttack );
	}
	
	public String healthStatus() {
		// Returns example string "100/100"
		return this.health + "/" + this.healthMax;
	}

}