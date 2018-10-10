
public class Worker extends Thread {

	private Buffer buffer;
	
	public Worker(Buffer buffer) {
		this.buffer = buffer;
	}

	public Buffer getBuffer() {
		return buffer;
	}
	
	public void run() {
		while(true) {
			
		}
	}
}
