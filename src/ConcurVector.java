
public class ConcurVector {

	// El array con los elementos del vector
	private double[] elements;
	private int thread;
	
	
	/** Constructor del SeqVector.
	 * @param size, la longitud del vector.
	 * @precondition size > 0. */
	public ConcurVector(int dimension, int thread) {
		elements = new double[dimension];
		this.thread = thread;
	}

	public int getThread() {
		return thread;
	}



	public void setThread(int thread) {
		this.thread = thread;
	}



	/** Retorna la longitud del vector; es decir, su dimension. */
	public int dimension() {
		return elements.length;
	}
	
	
	/** Retorna el elemento del vector en la posicion i.
	 * @param i, la posicion del elemento a ser retornado.
	 * @precondition 0 <= i < dimension(). */
	public double get(int i) {
		return elements[i];
	}
	
	
	/** Pone el valor d en la posicion i del vector. 
	 * @param i, la posicion a setear.
	 * @param d, el valor a ser asignado en la posicion i.
	 * @precondition 0 <= i < dimension. */
	public void set(int i, double d) {
		elements[i] = d;
	}
	
    
    /**De aquí en adelante, los métodos deben ser resueltos 
     * de manera concurrente
     */


	/** Pone el valor d en todas las posiciones del vector. 
	 * @param d, el valor a ser asignado. */
	public synchronized void set(double d) {
		for (int i = 0; i < dimension(); ++i)
			elements[i] = d;
	}
	
	
	/** Copia los valores de otro vector sobre este vector.
	 * @param v, el vector del que se tomaran los valores nuevos.
	 * @precondition dimension() == v.dimension(). */
	public synchronized void assign(ConcurVector v) {
		for (int i = 0; i < dimension(); ++i)
			set(i, v.get(i));
	}
	
	
	/** Copia algunos valores de otro vector sobre este vector.
	 * Un vector mascara indica cuales valores deben copiarse.
	 * @param mask, vector que determina si una posicion se debe copiar.
	 * @param v, el vector del que se tomaran los valores nuevos.
	 * @precondition dimension() == mask.dimension() && dimension() == v.dimension(). */
	public synchronized void assign(ConcurVector mask, ConcurVector v) {
		for (int i = 0; i < dimension(); ++i)
			if (mask.get(i) >= 0)
				set(i, v.get(i));
	}
	
	
    /** Suma los valores de este vector con los de otro (uno a uno).
	 * @param v, el vector con los valores a sumar.
	 * @precondition dimension() == v.dimension(). */
	public synchronized void add(ConcurVector v) {
		for (int i = 0; i < dimension(); ++i)
			set(i, get(i) + v.get(i));
	}
	
	
	/** Multiplica los valores de este vector con los de otro
     *  (uno a uno).
	 * @param v, el vector con los valores a multiplicar.
	 * @precondition dimension() == v.dimension(). */
	public synchronized void mul(ConcurVector v) {
		for (int i = 0; i < dimension(); ++i)
			set(i, get(i) * v.get(i));
	}
	
	
	/** Obtiene el valor absoluto de cada elemento del vector. */
	public synchronized void abs() {
		for (int i = 0; i < dimension(); ++i)
			set(i, Math.abs(get(i)));
	}


	/** Obtiene la suma de todos los valores del vector. */
	public synchronized double sum() {
		double result = 0;
		for (int i = 0; i < dimension(); ++i)
			result += get(i);
		return result;
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
        double current_max = get(0);
		for (int i = 0; i < dimension(); ++i)
            current_max = Math.max(current_max, get(i));
        return current_max;
	}	
}
