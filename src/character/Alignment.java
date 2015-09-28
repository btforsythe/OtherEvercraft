package character;

import static com.google.common.collect.Lists.newArrayList;

import java.util.ArrayList;
import java.util.List;

public class Alignment {
	
	private List<String> allowableAlignments = newArrayList("Good", "Evil", "Neutral");
	private String currentAlignment;
	
	public Alignment(String alignment) {
		currentAlignment  = allowAlignment(alignment);
	}

	private String allowAlignment(String alignment) {
		return allowableAlignments.contains(alignment) ? alignment : "Neutral";
	}

	public String getAlignment() {
		return currentAlignment;
	}
	
}
