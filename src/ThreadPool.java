import java.util.ArrayList;

public class ThreadPool {
	
	private ArrayList<Worker> workers;
	private Buffer buffer;
	
	public ThreadPool(Integer cantWorkers, Integer lenghtBuffer){
		this.workers = new ArrayList<Worker>(); 
		this.initilize(cantWorkers,lenghtBuffer);
	}

	public void initilize(Integer cantWorkers, Integer buffer){
		this.initializeBuffer(buffer);
		for(int i=0;i == cantWorkers;i++){
			this.workers.add(initializeWorker(this.buffer));
		}
	}

	public Worker initializeWorker(Buffer buffer) {
		return new Worker(buffer);
	}

	public void initializeBuffer(Integer buffer) {
		this.buffer = new Buffer(buffer);
	}

	//Divide el tamaño del buffer segun la cantidad de workers
	public Integer getPartesIguales(){
		return this.buffer.getCapacidad() % this.workers.size();
	}
	
	public void asignarTrabajo(){
		int n= 0;
		for(Worker k : this.workers){
			if(isLastWorker(k)){                        //Al ultimo worker se le asigna lo restante del buffer
				k.setBegin(n);
				k.setEnd(this.buffer.getCapacidad());
			}else{
				k.setBegin(n);							//Se setea la ubicacion donde empieza su trabajo
				k.setEnd(n+getPartesIguales()-1);		//Se setea la ubicacion donde termina su trabajo
				n=n+getPartesIguales();
			}
		}
	}

	private boolean isLastWorker(Worker k) {
		
		return this.workers.indexOf(k) == this.workers.size()-1;
	}
}