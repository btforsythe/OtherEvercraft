package character;

import java.util.HashMap;
import java.util.Map;


public class EvercraftCharacter {

	private String name;
	private Alignment alignment;
	private Integer armorClass = 10;
	private Integer hitPoints = 5;
	private Abilities abilities = new Abilities();
	
	public void setName(String name) {
		this.name = name;
	}

	public void setAlignment(Alignment alignment) {
		this.alignment = alignment;
	}

	public String getName() {
		return name;
	}

	public Alignment getAlignment() {
		return alignment;
	}

	public Integer getArmorClass() {
		return armorClass ;
	}

	public Integer getHitPoints() {
		return hitPoints;
	}

	public boolean isAttackHit(Die die, EvercraftCharacter opponent) {
		return die.roll() >= opponent.getArmorClass();
	}

	public void takeHit(int i) {
		hitPoints--;
	}

	public void setAbilityScore(String abilityName, int abilityScore) {
		abilities.setAbilityScore(abilityName, abilityScore);
	}

	public int getAbilityScore(String abilityName) {
		return abilities.getAbilityScore(abilityName);
	}
	
}
