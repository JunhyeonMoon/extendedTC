package checkFL;

import java.util.ArrayList;
import java.util.List;

public class errorLines {
	public List<twoInt> lines = new ArrayList<twoInt>();
	
	public errorLines(List<twoInt> lines) {
		for(int i = 0; i < lines.size(); i++)
			this.lines.add(lines.get(i));
	}
}
