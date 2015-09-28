package character;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;


public class ModifiedCharacterTest {

	@Mock
	private EvercraftCharacter character;

	private ModifiedCharacter underTest;
	
	@Before
	public void setup() {
		initMocks(this);
		underTest = new ModifiedCharacter(character);
	}
	
	@Test
	public void shouldReturnRollModifierBasedOnLevelAndStrengthModifier() {
		when(character.getAbilityScore("strength")).thenReturn(15);
		when(character.characterLevelValue()).thenReturn(2);
		assertThat(underTest.getRollModifier(), is(3));
	}
	
	@Test
	public void shouldReturnAttackPowerBasedOnStrengthModifier() {
		when(character.getAbilityScore("strength")).thenReturn(15);
		assertThat(underTest.getAttackPower(), is(3));
	}
	
	@Test
	public void shouldNeverReturnAttackPowerOfLessThanOne() {
		when(character.getAbilityScore("strength")).thenReturn(1);
		assertThat(underTest.getAttackPower(), is(1));
	}
	
	@Test
	public void shouldReturnCriticalHitAttackPowerBasedOnDoubledStrengthModifier() {
		when(character.getAbilityScore("strength")).thenReturn(15);
		assertThat(underTest.getCriticalHitAttackPower(), is(6));
	}
	
	@Test
	public void shouldNeverReturnCriticalHitAttackPowerOfLessThanOne() {
		when(character.getAbilityScore("strength")).thenReturn(1);
		assertThat(underTest.getCriticalHitAttackPower(), is(1));
	}
	
	@Test
	public void shouldReturnDefenseBasedOnDexterityModifier() {
		when(character.getAbilityScore("dexterity")).thenReturn(15);
		when(character.getArmorClass()).thenReturn(10);
		assertThat(underTest.getDefense(), is(12));
	}
	
	@Test
	public void shouldReturnCurrentHitPointsBasedOnLevelAndConstitutionModifier() {
		when(character.getAbilityScore("constitution")).thenReturn(15);
		when(character.getHitPoints()).thenReturn(5);
		when(character.characterLevelValue()).thenReturn(2);
		assertThat(underTest.getCurrentHitPoints(), is(14));
	}
	

	@Test
	public void eachEvenCharacterLevelShouldIncreaseRollLevelByOne() {
		doReturn(2).when(character).characterLevelValue();
		doReturn(10).when(character).getAbilityScore("strength");
		assertThat(underTest.getRollModifier(), is(1));
	}
	
	@Test
	public void eachOddCharacterLevelShouldNotIncreaseRollLevelByOne() {
		doReturn(3).when(character).characterLevelValue();
		doReturn(10).when(character).getAbilityScore("strength");
		assertThat(underTest.getRollModifier(), is(1));
	}
	
}
