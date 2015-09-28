package battle;

import character.Die;
import character.EvercraftCharacter;
import character.ModifiedCharacter;

public class EvercraftBattle {

	private static Die die;
	private int dieRoll;
	private ModifiedCharacter aggressor;
	
	private static int HIT = 1;
	private static int CRITICAL_HIT = 2;
	
	public EvercraftBattle(Die die) {
		this.die = die;
	}

	public void battle(ModifiedCharacter aggressor, ModifiedCharacter victim) {
		dieRoll = die.roll();
		this.aggressor = aggressor;
		if (!shouldTakeHit(victim)) return;
		if (isCriticalRoll()){
			victim.takeHit(aggressor.getCriticalHitAttackPower());
		}else{
			victim.takeHit(aggressor.getAttackPower());
		}
	}
	
	private boolean shouldTakeHit(ModifiedCharacter victim) {
		return (dieRoll + aggressor.getRollModifier()) >= victim.getDefense();
	}
	
	private boolean isCriticalRoll() {
		return dieRoll == 20;
	}

}
