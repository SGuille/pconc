package workers;

public class WorkerMax extends  Worker{

    private ConcurVector v2;

    public WorkerMax(ConcurVector v, int cant, ConcurVector v2) {
        super(v,cant);
        this.v2 = v2;
    }

    @Override
    public void work(){
        for(int cant = 0; cant < this.cantAnalizar; cant++){
            int posicionAnalizar = this.vector.getPosicionAnalizar();
            double resultado = Math.max(this.vector.get(posicionAnalizar), v2.get(posicionAnalizar));
            this.vector.set(posicionAnalizar, resultado);
        }
    }
}
