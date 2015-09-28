package character;

public class ModifiedCharacter {

	private EvercraftCharacter character;
	private int damageTaken;
	private int initialHitPoints;

	public ModifiedCharacter(EvercraftCharacter character) {
		this.character = character;
		this.damageTaken = 0;
		this.initialHitPoints = 5;
	}

	public int getRollModifier() {
		return character.characterLevelValue()/2 + getModifierForAbility("strength");
	}

	public int getAttackPower() {
		return Math.max(1 + getModifierForAbility("strength"), 1);
	}

	public int getCriticalHitAttackPower() {
		return Math.max(2 + 2*getModifierForAbility("strength"), 1);
	}

	public EvercraftCharacter getCharacter() {
		return character;
	}

	public int getDefense() {
		return character.getArmorClass() + getModifierForAbility("dexterity");
	}

	public void takeHit(int attackPower) {
		this.damageTaken += attackPower;
	}

	public int getCurrentHitPoints() {
		return characterTotalHitPoints() - damageTaken;
	}
	
	private int getModifierForAbility(String abilityName) {
		return -5 + (int)Math.floor(character.getAbilityScore(abilityName) / 2);
	}
	
	private int characterTotalHitPoints() {
		return character.getHitPoints() + (character.characterLevelValue()-1)*(5+getModifierForAbility("constitution")) + getModifierForAbility("constitution");
	}
	
}
