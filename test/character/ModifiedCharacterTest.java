package character;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;


public class ModifiedCharacterTest {

	private static final int SUBTRACTIVE_ABILITY_SCORE = 1;
	private static final int ADDITIVE_ABILITY_SCORE = 15;
	private static final int MINIMUM_MODIFIED_VALUE = 1;
	private static final int DEFAULT_CHARACTER_HIT_POINTS = 5;
	private static final int DEFAULT_CHARACTER_ARMOR_CLASS = 10;
	

	@Mock
	private EvercraftCharacter character;

	private ModifiedCharacter underTest;
	
	@Before
	public void setup() {
		initMocks(this);
		underTest = new ModifiedCharacter(character);
		when(character.getHitPoints()).thenReturn(DEFAULT_CHARACTER_HIT_POINTS);
		when(character.getArmorClass()).thenReturn(DEFAULT_CHARACTER_ARMOR_CLASS);
	}
	
	@Test
	public void shouldReturnRollModifierBasedOnLevelAndStrengthModifier() {
		whenGettingAbility("strength").thenReturn(ADDITIVE_ABILITY_SCORE);
		when(character.characterLevelValue()).thenReturn(2);
		assertThat(underTest.getRollModifier(), is(3));
	}
	
	@Test
	public void shouldReturnAttackPowerBasedOnStrengthModifier() {
		whenGettingAbility("strength").thenReturn(ADDITIVE_ABILITY_SCORE);
		assertThat(underTest.getAttackPower(), is(3));
	}
	
	@Test
	public void shouldNeverReturnAttackPowerOfLessThanOne() {
		whenGettingAbility("strength").thenReturn(SUBTRACTIVE_ABILITY_SCORE);
		assertThat(underTest.getAttackPower(), is(MINIMUM_MODIFIED_VALUE));
	}
	
	@Test
	public void shouldReturnCriticalHitAttackPowerBasedOnDoubledStrengthModifier() {
		whenGettingAbility("strength").thenReturn(ADDITIVE_ABILITY_SCORE);
		assertThat(underTest.getCriticalHitAttackPower(), is(6));
	}
	
	@Test
	public void shouldNeverReturnCriticalHitAttackPowerOfLessThanOne() {
		whenGettingAbility("strength").thenReturn(SUBTRACTIVE_ABILITY_SCORE);
		assertThat(underTest.getCriticalHitAttackPower(), is(MINIMUM_MODIFIED_VALUE));
	}
	
	@Test
	public void shouldReturnDefenseBasedOnDexterityModifier() {
		whenGettingAbility("dexterity").thenReturn(ADDITIVE_ABILITY_SCORE);
		assertThat(underTest.getDefense(), is(12));
	}
	
	@Test
	public void shouldReturnCurrentHitPointsBasedOnLevelAndConstitutionModifier() {
		whenGettingAbility("constitution").thenReturn(ADDITIVE_ABILITY_SCORE);
		when(character.characterLevelValue()).thenReturn(2);
		assertThat(underTest.getCurrentHitPoints(), is(14));
	}
	
	@Test
	public void shouldNeverAllowCharacterBaseHitpointsToBeBelowOne() {
		whenGettingAbility("constitution").thenReturn(SUBTRACTIVE_ABILITY_SCORE);
		when(character.characterLevelValue()).thenReturn(1);
		assertThat(underTest.getCurrentHitPoints(), is(MINIMUM_MODIFIED_VALUE));
	}
	
	@Test
	public void shouldTakeDamageAwayFromCurrentBaseHitPoints() {
		whenGettingAbility("constitution").thenReturn(ADDITIVE_ABILITY_SCORE);
		when(character.characterLevelValue()).thenReturn(2);
		underTest.takeHit(4);
		assertThat(underTest.getCurrentHitPoints(), is(10));
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
	

	private OngoingStubbing<Integer> whenGettingAbility(String abilityName) {
		return when(character.getAbilityScore(abilityName));
	}
	
}
