import java.util.concurrent.Callable;

public class MyCallable implements Callable<Integer> {
	
	int name;
	
	public MyCallable(int name) {
		this.name = name;
	}
	
	@Override
	public Integer call() {
		int value = 0;
		int msg = 3;
		
		try {
			for (int i = 1; i <= msg; i++) {
				Thread.sleep(500);
				System.out.printf("Я задача %s. Сообщение %d.", name, i);
				System.out.println("Всем привет!");
				value++;
			}
			
		} catch (InterruptedException e) {
		} finally {
			System.out.printf("Задача %s завершена \n", name);
		}
		return value;
	}
}
