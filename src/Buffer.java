
public class Buffer {

	private double[] data;
	private int begin=0, end=0;
	
	public Buffer(int capacidad) {
		this.data = new double[capacidad];
	}

	public double[] getData() {
		return data;
	}
	
	public synchronized void push(double d) {
		while(isFull()) {
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		data[begin]=d;
		begin=next(begin);
		notifyAll();
	}

	public synchronized double pop() {
		while(isEmpty()) {
			try {
				wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		double result = data[end];
		end=next(end);
		notifyAll();
		return result;
	}
	
	private boolean isFull() {
		return next(begin)==end;
	}
	
	private boolean isEmpty() {
		return begin==end;
	}
	
	private int next(int i) {
		return (i+1)%(this.data.length);
	}
}
