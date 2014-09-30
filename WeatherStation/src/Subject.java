/*
 * interface for subject in Observer Pattern
 * 
 */
public interface Subject {
	void registerObserver(Observer observer);
	void removeObserver(Observer observer);
	void notifyObservers();
}
