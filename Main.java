import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
	public static void main(String[] args) throws ExecutionException, InterruptedException {
		getAll();
		getAny();
	}
	
	
	private static void getAll() throws InterruptedException, ExecutionException {
		int callabe = 4;
		int result = 0;
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		List<MyCallable> tasks = getListMyCallable(callabe);
		
		List<Future<Integer>> futures = pool.invokeAll(tasks);
		for (Future f : futures) result += (int) f.get();
		
		pool.shutdown();
		
		System.out.println("Результат для всех " + result+"\n");
	}
	
	private static void getAny() throws InterruptedException, ExecutionException {
		int callabe = 3;
		int result;
		ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
		List<MyCallable> tasks = getListMyCallable(callabe);
		
		result = pool.invokeAny(tasks);
		
		pool.shutdown();
		
		System.out.println("Результат для одной " + result+"\n");
	}
	
	private static List<MyCallable> getListMyCallable(int count) {
		List<MyCallable> tasks = new ArrayList<>();
		
		for (int i = 1; i <= count; i++) {
			MyCallable mc = new MyCallable(i);
			tasks.add(mc);
		}
		
		return tasks;
	}
}
