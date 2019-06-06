


public class Configuration {

    private int Epochs = 1000;
    private double LearnTax = 0.05;

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
