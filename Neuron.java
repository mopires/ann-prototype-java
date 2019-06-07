

public class Neuron {

    private int id;
    private double Value = 0.0;

    private double[] Weight;

    public Neuron(int id){
        this.id = id;
    }

    public int GetId() {
        return this.id;
    }

    public double[] GetWeight() {
        return this.Weight;
    }

    public void SetWeight(double[] Weight) {
        this.Weight = new double[Weight.length];
        this.Weight = Weight;
    }

    public double GetValue(){
        return this.Value;
    }

    public void UpdateWeight(double NewWeight, int Position){
        this.Weight[Position] = NewWeight;
    }

    public void Input(double Value){
        this.Value += Value;
        Activation();
    }

    public void Activation(){

        if(!(this.Value >= 10.0)){
            this.Value = 10.0;
        }

    }

}
