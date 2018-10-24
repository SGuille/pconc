import java.util.ArrayList;

public class ThreadPool {
	
	private ArrayList<Worker> workers;
	private Buffer buffer;
	
	public ThreadPool(Integer cantWorkers, Integer lenghtBuffer){
		this.workers = new ArrayList<Worker>(); 
		this.initialize(cantWorkers,lenghtBuffer);
	}

	public void initialize	(Integer cantWorkers, Integer buffer){
		this.buffer = new Buffer(buffer);
		for(int i=0;i < cantWorkers;i++){
			this.setWorker(new Worker(this.buffer));
		}
	}

	public void setWorker(Worker k){
		
		this.workers.add(k);
	}
	
	public ArrayList<Worker> getWorkers(){
		return this.workers;
	}
	
	public Buffer getBuffer(){
		return this.buffer;
	}

	//Divide el tamaño del buffer segun la cantidad de workers
	public Integer getPartesIguales(){
		return this.buffer.getCapacidad() / this.workers.size();
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