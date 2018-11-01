package workers;

public class WorkerAbs extends Worker {

    public WorkerAbs(ConcurVector v, int cant) {
        super(v,cant);
    }

    public void work(){
        for(int cant = 0; cant < this.cantAnalizar; cant++){
            int posicionAnalizar = this.vector.getPosicionAnalizar();
            double valor = this.vector.get(posicionAnalizar);
            double resultado = Math.abs(valor);
            this.vector.set(posicionAnalizar, resultado);
        }
    }
}

