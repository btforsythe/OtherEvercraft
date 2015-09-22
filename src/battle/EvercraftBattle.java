package battle;

import character.Die;
import character.EvercraftCharacter;

public class EvercraftBattle {

	Die die;
	
	public EvercraftBattle(Die die) {
		this.die = die;
	}

	public void battle(EvercraftCharacter aggressor, EvercraftCharacter victim) {
		victim.takeHit();
	}

}
