


public class Configuration {

    private int Epochs;
    private double LearnTax;

    public Configuration(int Epochs, double LearnTax){
        SetEpochs(Epochs);
        SetLearnTax(LearnTax);
    }

    public void SetEpochs(int Epochs) {
        this.Epochs = Epochs;
    }

    public void SetLearnTax(double LearnTax) {
        this.LearnTax = LearnTax;
    }

    public double GetLearnTax(){
        return this.LearnTax;
    }

    public int GetEpochs(){
        return this.Epochs;
    }
}
