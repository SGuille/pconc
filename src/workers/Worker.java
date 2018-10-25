package workers;

import java.util.ArrayList;

public abstract class Worker extends Thread {

	private Buffer buffer;
	private ArrayList<Double> elementos;
	
	public Worker(Buffer buffer, ArrayList<Double> elementos) {

		this.buffer = buffer;
		this.elementos = elementos;
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
