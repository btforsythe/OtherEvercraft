package character;

import java.util.ArrayList;
import java.util.List;

public class Alignment {
	
	private List<String> allowableAlignments;
	private String currentAlignment;
	
	public Alignment(String alignment) {
		allowableAlignments = constructAllowableAlignments();
		currentAlignment  = allowAlignment(alignment);
	}

	private String allowAlignment(String alignment) {
		return allowableAlignments.contains(alignment) ? alignment : "Neutral";
	}

	private List<String> constructAllowableAlignments() {
		List<String> allowableAlignments = new ArrayList<String>();
		allowableAlignments.add("Good");
		allowableAlignments.add("Evil");
		allowableAlignments.add("Neutral");
		return allowableAlignments;
	}

	public String getAlignment() {
		return currentAlignment;
	}
	
}
