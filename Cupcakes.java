import java.io.IOException;
import java.util.Random;

public class Cupcakes {	
	static final int NUM_OF_THREADS = 100;
	static final int MAX_TIME_THROUGH_MAZE = 1;
	static final int leader = 0;
	static boolean cupcakeExists = true;
	static int counter = 0;
	static int randomlyChosen;
	static Random rng = new Random();	
	
	public static int getNextThreadRandomized() {
		return rng.nextInt(NUM_OF_THREADS);
	}
	
	static ThreadLocal<Boolean> putCupcakeBack = new ThreadLocal<Boolean>() {
		protected Boolean initialValue() {
			return false;
		}
	};
	
	public synchronized static boolean simulateThroughMaze(int id) throws InterruptedException {		
		if (counter == NUM_OF_THREADS) 
			return true;
		
		if (randomlyChosen != id)
			return false;
		
		int timeToGoThroughMaze = rng.nextInt(MAX_TIME_THROUGH_MAZE);
		Thread.sleep(timeToGoThroughMaze);
		
		if (id == leader && cupcakeExists) {
			cupcakeExists = false;
			counter++;
		}
		else if (id != leader && !putCupcakeBack.get() && !cupcakeExists) {
			cupcakeExists = true;
			putCupcakeBack.set(true);
		}
		
		randomlyChosen = getNextThreadRandomized();
		
		return false;
	}
	
	public static void main(String args[]) throws InterruptedException, IOException {
		long start = System.currentTimeMillis();
		Thread[] guests = new Thread[NUM_OF_THREADS];
		randomlyChosen = getNextThreadRandomized();
		
		for (int i = 0; i < guests.length; i++) {
			final int id = i;
			guests[i] = new Thread(new Runnable() {
				public void run() {
					try {
						while (!simulateThroughMaze(id));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			});
		}
			
		for (int i = 0; i < guests.length; i++) {
			guests[i].start();
		}
		for (int i = 0; i < guests.length; i++) {
			guests[i].join();
		}
		
		long end = System.currentTimeMillis();

		System.out.println(((end - start) / 1000.0));
	}

}
