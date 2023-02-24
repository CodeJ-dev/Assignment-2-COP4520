import java.io.IOException;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class SeeVase {	
	static final int NUM_OF_THREADS = 100;
	
	static class Node {
		AtomicBoolean locked;
		int id;
		public Node(boolean flag, int id) {
			locked = new AtomicBoolean(flag);
			this.id = id;
		}
	}
	
	static class ThreadQueue{
		AtomicReference<Node> tail = new AtomicReference<Node>(new Node(false, -1));
		ThreadLocal<Node> nodeBeforeCurrent = new ThreadLocal<Node>();
		ThreadLocal<Node> currentNode = new ThreadLocal<Node>();
				
		public void enqueue(int id) {
			currentNode.set(new Node(true, id));
			nodeBeforeCurrent.set(tail.getAndSet(currentNode.get()));
			while (nodeBeforeCurrent.get().locked.get()) {}
		}
		
		public void dequeue() {
			currentNode.get().locked.set(false);
			currentNode.set(nodeBeforeCurrent.get());
		}
	}
	
	static ThreadQueue queue = new ThreadQueue();
	static AtomicInteger totalPeopleToSeeVase = new AtomicInteger(0);
	static Random rng = new Random();
	
	public static int getRandomizedValue(int value) {
		return rng.nextInt(value);
	}
		
	static ThreadLocal<Boolean> hasGuestSeenVase = new ThreadLocal<Boolean>() {
		protected Boolean initialValue() {
			return false;
		}
	};
	
	public static void main(String args[]) throws InterruptedException, IOException {
		long start = System.currentTimeMillis();
		Thread[] guests = new Thread[NUM_OF_THREADS];
		
		for (int i = 0; i < guests.length; i++) {
			final int id = i;
			guests[i] = new Thread(new Runnable() {
				public void run() {
					while (totalPeopleToSeeVase.get() < NUM_OF_THREADS) {
						boolean doesGuestWantToSeeVase = getRandomizedValue(4) == 1;
						if (doesGuestWantToSeeVase) {
							queue.enqueue(id);
							if (!hasGuestSeenVase.get()) {
								totalPeopleToSeeVase.getAndIncrement();
								hasGuestSeenVase.set(true);
							}
							queue.dequeue();
						}
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

		System.out.print(((end - start) / 1000.0) + " ");
	}

}
