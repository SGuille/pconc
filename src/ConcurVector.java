
import workers.WorkerAbs;
import workers.WorkerAdd;
import workers.WorkerAssign;
import workers.WorkerAssignMask;
import workers.WorkerMax;
import workers.WorkerMin;
import workers.WorkerMul;
import workers.WorkerSum;


public class ConcurVector {

	// El array con los elementos del vector
	private int dimension;
	private int maxThreads;
	private int posicionAnalizar;
	private double[] elements;



	/** Constructor */
	public ConcurVector(int dimension, int maxThreads) {
		this.dimension = dimension;
		this.maxThreads = maxThreads;
	}

	public int getThread() {
		return maxThreads;
	}



	public void setThread(int thread) {
		this.maxThreads = thread;
	}


	public int dimension() {
		return elements.length;
	}
	

	public double get(int i) {
		return elements[i];
	}
	

	public void set(int i, double d) {
		elements[i] = d;
	}

	public int cantElemsAnalizar(int i){
		int cantElemsAnalizar = this.dimension() / this.maxThreads;
		if(i<this.dimension() % this.maxThreads){
			cantElemsAnalizar = (this.dimension() / this.maxThreads) + 1;
		}

		return cantElemsAnalizar;
	}
	public synchronized int getPosicionAnalizar() {
		int posicion = this.posicionAnalizar;
		this.posicionAnalizar++;
		return posicion;
	}

	/**De aquí en adelante, los métodos deben ser resueltos
     * de manera concurrente
     */

	public synchronized void set(double d) {
		for (int i = 0; i < dimension(); ++i)
			elements[i] = d;
	}


	public synchronized void assign(ConcurVector v) {
		for(int i = 0; i < this.maxThreads; i++)
			new WorkerAssign(this, this.cantElemsAnalizar(i), v).start();
	}


	public synchronized void assign(ConcurVector mask, ConcurVector v) {
		for(int i = 0; i < this.maxThreads; i++)
			new WorkerAssignMask(this, this.cantElemsAnalizar(i), v, mask).start();
	}

	
	
    /** Suma los valores de este vector con los de otro (uno a uno).
	 * @param v, el vector con los valores a sumar.
	 * @precondition dimension() == v.dimension(). */
	public synchronized void add(ConcurVector v) {
		for(int i = 0; i < this.maxThreads; i++)
			new WorkerAdd(this, this.cantElemsAnalizar(i), v).start();
	}
	
	
	/** Multiplica los valores de este vector con los de otro
     *  (uno a uno).
	 * @param v, el vector con los valores a multiplicar.
	 * @precondition dimension() == v.dimension(). */
	public synchronized void mul(ConcurVector v) {
		for(int i = 0; i < this.maxThreads; i++)
			new WorkerMul(this, this.cantElemsAnalizar(i), v).start();
	}
	
	
	/** Obtiene el valor absoluto de cada elemento del vector. */
	public synchronized void abs() {
		for(int i = 0; i < this.maxThreads; i++)
			new WorkerAbs(this, this.cantElemsAnalizar(i)).start();
	}


	/** Obtiene la suma de todos los valores del vector. */
	public synchronized double sum() {
		/////////////
	}
    
    
    /** Obtiene el valor promedio en el vector. */
	public synchronized double mean() {
        	double total = sum();
        	return total / dimension();
	}
    
    
	/** Retorna el producto de este vector con otro.
     * El producto vectorial consiste en la suma de los productos
     * de cada coordenada.
	 * @param v, el vector a usar para realizar el producto.
	 * @precondition dimension() == v.dimension(). */
	public synchronized double prod(ConcurVector v) {
		ConcurVector aux = new ConcurVector(dimension(),1);
		aux.assign(this);
		aux.mul(v);
		return aux.sum();
	}
	
	
	/** Retorna la norma del vector.
     *  Recordar que la norma se calcula haciendo la raiz cuadrada de la
     *  suma de los cuadrados de sus coordenadas.
     */
	public synchronized double norm() {
		ConcurVector aux = new ConcurVector(dimension(),1);
		aux.assign(this);
		aux.mul(this);
		return Math.sqrt(aux.sum());
	}
	
	
    /** Obtiene el valor maximo en el vector. */
	public synchronized double max() {
		for(int i = 0; i < this.maxThreads; i++)
			new WorkerMax(this, this.cantDeElementosAAnalizar(i), b, v).start();
	}	
}

