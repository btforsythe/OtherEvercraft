package character;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

import character.Alignment;


public class AlignmentTest {
	
	@Test
	public void shouldAllowGood() {
		Alignment underTest = new Alignment("Good");
		assertThat(underTest.getAlignment(), is("Good"));
	}
	
	@Test
	public void shouldAllowEvil() {
		Alignment underTest = new Alignment("Evil");
		assertThat(underTest.getAlignment(), is("Evil"));
	}
	
	@Test
	public void shouldAllowNeutral() {
		Alignment underTest = new Alignment("Neutral");
		assertThat(underTest.getAlignment(), is("Neutral"));
	}
	
	@Test
	public void shouldDefaultToNeutralForAnyNonAllowedValue() {
		Alignment underTest = new Alignment("Potato");
		assertThat(underTest.getAlignment(), is("Neutral"));
	}
	
}
