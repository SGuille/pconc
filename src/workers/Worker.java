package workers;

public abstract class Worker extends Thread {

	public ConcurVector vector;
	public int cantAnalizar;

	public Worker(ConcurVector v, int cant) {
		this.vector = v;
		this.cantAnalizar = cant;
	}

	@Override
	public void run() {
		this.work();
	}

		public abstract void work();
	}

}
