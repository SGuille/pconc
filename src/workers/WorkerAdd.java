package workers;


public class WorkerAdd extends Worker {

    private ConcurVector v2;

    public WorkerAdd(ConcurVector v, int cant, ConcurVector v2) {
        super(v,cant);
        this.v2 = v2;
    }

    @Override
    public void work(){
        for(int cant = 0; cant < this.cantAnalizar; cant++){
            int posicionAnalizar = this.vector.getPosicionAnalizar();
            this.vector.set(posicionAnalizar, this.vector.get(posicionAnalizar) + v2.get(posicionAnalizar));
        }
    }

}
