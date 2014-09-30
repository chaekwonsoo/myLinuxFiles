import java.util.Iterator;

public class DinerMenuIterator implements Iterator<MenuItem> {

	MenuItem[] menuItems;
	int position = 0;
	
	public DinerMenuIterator(MenuItem[] menuItems) {
		this.menuItems = menuItems;
	}
	
	@Override
	public boolean hasNext() {
		if(position >= menuItems.length || menuItems[position] == null) {
			return false;
		} else {
			return true;
		}
	}

	@Override
	public MenuItem next() {
		MenuItem menuItem = menuItems[position];
		position++;
		return menuItem;
	}
	
	// next() 에서 마지막으로 리턴한 컬렉션 항목을 삭제.
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
		
		/*
		if(position <= 0) {
			throw new IllegalStateException("next() should be called first!");
		}
		if(menuItems[position - 1] != null) {
			for(int i = position - 1; i < (menuItems.length - 1); i++) {
				menuItems[i] = menuItems[i + 1];
			}
			menuItems[menuItems.length - 1] = null;
		}
		*/
	}
}
