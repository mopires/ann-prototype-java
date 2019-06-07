import java.awt.*;
import java.util.Scanner;


public class Network {

    Configuration config = new Configuration(10000000, 0.05);

    private Scanner ScanInput = new Scanner(System.in);

    private int NumberOfHiddenLayers;
    private int NumberOfNeurons;
    private Neuron[][] network;

    private double[] ExpectedOutput;




    public Network(int NumberOfHiddenLayers) {
        this.NumberOfHiddenLayers = NumberOfHiddenLayers;
        SetNeuronsInLayers();
        InitWeight();
    }

    private void SetNeuronsInLayers() {
        try {

            Neuron[][] network = new Neuron[this.NumberOfHiddenLayers][];

            for (int i = 0; i < this.NumberOfHiddenLayers; i++) {

                System.out.println("Layer["+i+"]");
                this.NumberOfNeurons = ScanInput.nextInt();

                Neuron[] ArrayOfNeuron = new Neuron[NumberOfNeurons];
                for (int j = 0; j < ArrayOfNeuron.length; j++) {
                    ArrayOfNeuron[j] = new Neuron(j);//Include BIAS
                }
                network[i] = new Neuron[ArrayOfNeuron.length];
                network[i] = ArrayOfNeuron;
            }
            this.network = network;

        }
        catch(Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    private void InitWeight(){

        try {

            for(int i = 0; i < this.network.length; i++) {

                if(i + 1 == this.network.length){
                    break;
                }

                for (int j = 0; j < network[i].length; j++) {

                    double[] weight = new double[this.network[i + 1].length];

                    for (int k = 0; k < this.network[i + 1].length; k++) {
                        weight[k] = 0.5;
                    }
                    this.network[i][j].SetWeight(weight);
                }
            }

        }catch (Exception ex){
            System.out.println("\n " + ex.getMessage());
        }

    }

    /*
        Criar outros tipos de input
    */
    public void Input(double[] Input) {

        System.out.println(" Assigning inputs... \n");
        for (int i = 0; i < this.network[0].length; i++){

            this.network[0][i].Input(Input[i]);

        }


    }

    private Neuron[] Synapse(){

        double NextValue = 0;
        for (int i = 0; i < this.network.length;i++){

            if(i + 1 == this.network.length){
                break;
            }

            for (int j = 0; j < this.network[i].length; j++) {

                for (int k = 0; k < this.network[i+1].length;k++){

                    NextValue += this.network[i][j].GetValue() *
                                this.network[i][j].GetWeight()[k];
                    this.network[i+1][k].Input(NextValue);
                }

                NextValue = 0;
            }


        }

        return this.network[this.network.length-1];
    }

    public Neuron[] Run(){

        return Synapse();

    }

    public void SetupTrainning(double[] Input, double[] ExpectedOutput){

        this.ExpectedOutput = ExpectedOutput;
        Input(Input);

        Neuron[] Out = Run();
        System.out.println("The network is learning... \n");

        int e = 1;
        while (e <= config.GetEpochs()){
            System.out.println(e);
            for (int i = 0; i < this.ExpectedOutput.length; i++){
                if(Out[i].GetValue() != ExpectedOutput[i]){
                    WeightUpdate();
                    Run();
                }
                else {
                    break;
                }
            }
            e++;

        }

        See();
    }

    private void WeightUpdate(){

        double NewWeight;
        for (int i = network.length-2; i >= 0;i++ ) {

            for (int j = 0; j < network[i].length;j++){

                for (int k = 0; k < network[i][j].GetWeight().length;k++){

                    System.out.println(i+"-j"+j+"-k"+k);
                    NewWeight = network[i][j].GetWeight()[k] + config.GetLearnTax() *
                                (this.ExpectedOutput[j] -
                                network[network.length-1][0].GetValue())
                                * network[i][j].GetValue();

                    network[i][j].UpdateWeight(NewWeight, k);
                }

            }

        }



    }

    public void See(){

        try {

            for (int i = 0; i < this.network[this.network.length-1].length;i++){
                System.out.println(i+ " " + this.network[this.network.length-1][i].GetValue());
            }

        }
        catch (Exception ex){
            System.out.println("\n\n" + ex.getMessage());
            System.out.println("\n\n" + ex.getCause());
        }

    }

}
