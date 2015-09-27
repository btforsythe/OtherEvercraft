package character;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class LevelingTest {

	private Level underTest = new Level();

	@Test
	public void levelDefaultsToOne() {
		
		
		assertThat(underTest.getLevel(), is(1));
	}
	
}
