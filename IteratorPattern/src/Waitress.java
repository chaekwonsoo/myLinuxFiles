import java.util.Iterator;

public class Waitress {
	
	private Menu pancakeHouseMenu;
	private Menu dinerMenu;
	
	public Waitress(Menu pancakeHouseMenu, Menu dinerMenu) {
		this.pancakeHouseMenu = pancakeHouseMenu;
		this.dinerMenu = dinerMenu;
	}
	
	public void printMenu() {
		Iterator<MenuItem> pancakeIterator = pancakeHouseMenu.createIterator();
		Iterator<MenuItem> dinerIterator = dinerMenu.createIterator();
		System.out.println("메뉴" + System.getProperty("line.separator") +
								"-----" + System.getProperty("line.separator") +
								"아침 메뉴");
		printMenu(pancakeIterator);
		System.out.println(System.getProperty("line.separator") + "점심 메뉴");
		printMenu(dinerIterator);
	}
	
	private void printMenu(Iterator<MenuItem> iterator) {
		while(iterator.hasNext()) {
			MenuItem menuItem = (MenuItem)iterator.next();
			System.out.println(menuItem.getName() + ", ");
			System.out.print(menuItem.getPrice() + " -- ");
			System.out.println(menuItem.getDescription());
		}
	}
	
	public void printBreakfastMenu() {
		Iterator<MenuItem> iterator = pancakeHouseMenu.createIterator();
		printMenu(iterator);
	}
	
	public void printLunchMenu() {
		Iterator<MenuItem> iterator = dinerMenu.createIterator();
		printMenu(iterator);
	}
	
	public void printVegetarianMenu() {
		Iterator<MenuItem> pancakeIterator = pancakeHouseMenu.createIterator();
		Iterator<MenuItem> dinerIterator = dinerMenu.createIterator();
		
		printVegitarianMenu(pancakeIterator);
		printVegitarianMenu(dinerIterator);
	}
	
	private void printVegitarianMenu(Iterator<MenuItem> iterator) {
		while(iterator.hasNext()) {
			MenuItem menuItem = (MenuItem)iterator.next();
			if(menuItem.isVegetarian()) {
				System.out.println(menuItem.getName() + ", ");
				System.out.print(menuItem.getPrice() + " -- ");
				System.out.println(menuItem.getDescription());
			}
		}
	}
	
	public boolean isItemVegetarian(String name) {
		Iterator<MenuItem> pancakeIterator = pancakeHouseMenu.createIterator();
		Iterator<MenuItem> dinerIterator = dinerMenu.createIterator();
		
		if(isItemVegitarian(pancakeIterator, name) || isItemVegitarian(dinerIterator, name))
			return true;
		
		return false;
	}
	
	private boolean isItemVegitarian(Iterator<MenuItem> iterator, String name) {
		while(iterator.hasNext()) {
			MenuItem menuItem = iterator.next();
			if(menuItem.getName().equals(name)) {
				if(menuItem.isVegetarian()) {
					return true;
				}
			}
		}
		return false;
	}
}
