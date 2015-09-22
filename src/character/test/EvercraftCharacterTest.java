package character.test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import character.Alignment;
import character.Die;
import character.EvercraftCharacter;

public class EvercraftCharacterTest {
	
	@Mock
	Alignment alignment;
	
	@Mock
	Die die;
	
	@Mock
	EvercraftCharacter opponent;
	
	EvercraftCharacter underTest;
	
	@Before
	public void setup() {
		underTest = new EvercraftCharacter();
		initMocks(this);
	}
	
	@Test
	public void shouldSetCharacterName() {
		underTest.setName("test");
		assertThat(underTest.getName(), is("test"));
	}
	
	@Test
	public void shouldSetCharacterAlignment() {
		underTest.setAlignment(alignment);
		assertThat(underTest.getAlignment(), is(alignment));
	}
	
	@Test
	public void shouldHaveClassArmorDefaultTo10() {
		assertThat(underTest.getArmorClass(), is(10));
	}
	
	@Test
	public void shouldHaveClassArmorHitPointsDefaultTo5()
	{
		assertThat(underTest.getHitPoints(), is(5));
	}
	
	@Test
	public void shouldHitOpponentIfDieRollBeatsOpponentsArmorClass() {
		when(die.roll()).thenReturn(20);
		when(opponent.getArmorClass()).thenReturn(10);
		assertThat(underTest.isAttackHit(die, opponent), is(true));	
	}
	
	@Test
	public void shouldNotHitOpponentIfDieRollIsLessThanOpponentsArmorClass() {
		when(die.roll()).thenReturn(5);
		when(opponent.getArmorClass()).thenReturn(10);
		assertThat(underTest.isAttackHit(die, opponent), is(false));	
	}
	
	@Test
	public void shouldHitOpponentIfDieRollIsSameAsOpponentsArmorClass() {
		when(die.roll()).thenReturn(10);
		when(opponent.getArmorClass()).thenReturn(10);
		assertThat(underTest.isAttackHit(die, opponent), is(true));	
	}
	
	
}
