package character;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

public class LevelingTest {

	@Mock
	EvercraftCharacter character;
	
	private Level underTest;
	
	@Before
	public void setup() {
		initMocks(this);
		underTest = new Level();
	}

	@Test
	public void levelDefaultsToOne() {
		assertThat(underTest.getLevel(), is(1));
	}
	
	@Test
	public void levelIncreasesBy1ForEach1000ofXPGained() {
		underTest.increaseXp(1000);
		
		assertThat(underTest.getLevel(), is(2));
	}
	
}
