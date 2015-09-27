package battle;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import battle.EvercraftBattle;
import character.Die;
import character.EvercraftCharacter;
import character.Level;


public class EvercraftBattleTest {

	@Mock
	Die die;
	
	@Mock
	Level level;
	
	@Spy
	EvercraftCharacter victim = new EvercraftCharacter(level);
	
	@Mock
	private EvercraftCharacter aggressor;
	
	EvercraftBattle underTest;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
		underTest = new EvercraftBattle(die);
	}
	
	@Test
	public void shouldHitVictimIfRollIsGreaterThanVictimClassArmor() {
		when(die.roll()).thenReturn(19);
		when(victim.getArmorClass()).thenReturn(10);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(1);
	}
	
	@Test
	public void shouldTakeDoubleDamageWhenCriticalHit() {
		when(die.roll()).thenReturn(20);
		when(victim.getArmorClass()).thenReturn(10);
		underTest.battle(aggressor, victim);
		verify(victim, times(1)).takeHit(2);
	}
	
	@Test
	public void shouldNotHitVictimIfRollIsLessThanVictimClassArmor(){
		when(die.roll()).thenReturn(9);
		when(victim.getArmorClass()).thenReturn(10);
		underTest.battle(aggressor, victim);
		verify(victim, times(0)).takeHit(Mockito.anyInt());
	}
	
}
