package workers;

public abstract class Worker extends Thread {

	private Buffer buffer;
	private ThreadPool tpool;
	
	public Worker(Buffer buffer, ThreadPool tpool) {

		this.buffer = buffer;
		this.tpool = tpool;
	}

	public Buffer getBuffer() {
		return buffer;
	}

	@Override
	public void run() {
		while (true){
			this.work();
		}
	}

	public abstract void work();

	public void setBegin(int n) {
		// TODO Auto-generated method stub
		
	}

	public void setEnd(int capacidad) {
		// TODO Auto-generated method stub
		
	}

}
