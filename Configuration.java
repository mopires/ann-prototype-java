


public class Configuration {

    private int Epochs;
    private float LearnTax;

    public Configuration(int Epochs, double LearnTax){

    }

    public void SetEpochs(int Epochs) {
        this.Epochs = Epochs;
    }

    public void SetLearnTax(float LearnTax) {
        this.LearnTax = LearnTax;
    }
}
