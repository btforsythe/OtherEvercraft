package battle;

import character.Die;
import character.EvercraftCharacter;

public class EvercraftBattle {

	Die die;
	
	public EvercraftBattle(Die die) {
		this.die = die;
	}

	public void battle(EvercraftCharacter aggressor, EvercraftCharacter victim) {
		if (!shouldTakeHit(victim)) return;
		if (die.roll()<20){
			victim.takeHit(1);
		}else{
			victim.takeHit(2);
		}
	}
	
	public boolean shouldTakeHit(EvercraftCharacter victim) {
		return die.roll() > victim.getArmorClass();
	}

}
