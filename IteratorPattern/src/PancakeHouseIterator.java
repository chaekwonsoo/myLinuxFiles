import java.util.Iterator;
import java.util.ArrayList;

public class PancakeHouseIterator implements Iterator<MenuItem> {

	Iterator<MenuItem> iterator;
	
	public PancakeHouseIterator(ArrayList<MenuItem> menuItems) {
		iterator = menuItems.iterator();
	}
	
	@Override
	public boolean hasNext() {
		return iterator.hasNext();
	}

	@Override
	public MenuItem next() {
		return iterator.next();
	}
}
