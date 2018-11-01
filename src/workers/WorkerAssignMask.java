package workers;
import ConcurVector;

public class WorkerAssignMask extends Worker{

    private ConcurVector v2;
    private ConcurVector mask;

    public WorkerAssignMask(ConcurVector v, int cant, ConcurVector v2, ConcurVector mask) {
        super(v,cant);
        this.v2 = v2;
        this.mask = mask;
    }

    @Override
    public void work(){
        for(int cant = 0; cant < this.cantAnalizar; cant++){
            int posicionAnalizar = this.vector.getPosicionAnalizar();
            if (this.mask.get(posicionAnalizar) >= 0)
                this.vector.set(posicionAnalizar, v2.get(posicionAnalizar));
        }
        {
}
