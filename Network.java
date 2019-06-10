import java.io.*;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;


public class Network {

    Configuration config = new Configuration(10000, 0.030);

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

        Random RandNum;

        try {

            for(int i = 0; i < this.network.length; i++) {

                if(i + 1 == this.network.length){
                    break;
                }

                for (int j = 0; j < network[i].length; j++) {

                    double[] weight = new double[this.network[i + 1].length];

                    for (int k = 0; k < this.network[i + 1].length; k++) {
                        RandNum = new Random();
                        weight[k] = RandNum.nextDouble()  / 100;
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

    public Neuron[] Run(boolean Training, double[] Input){

        if(Training){
            Input(Input);
            return Synapse();
        }
        else {
            LoadData();
            Input(Input);
            return Synapse();
        }


    }

    public void SetupTraining(double[] Input, double[] ExpectedOutput){

        this.ExpectedOutput = ExpectedOutput;

        //System.out.println("The network is learning... \n");

        int e = 1;
        while (e <= config.GetEpochs()){

            Run(true, Input);
            WeightUpdate();
            e++;

        }
        // Save the data(Number of layers, number of neurons
        // on each layer, weights...
        // data.txt
        Save();
    }

    private void WeightUpdate(){

        double NewWeight = 0;

            for (int i = network.length-2; i >= 0;i--) { //run for the layers

                for (int j = 0; j < network[i].length-1;j++){ // run for the neurons

                    for (int k = 0; k < network[i][j].GetWeight().length;k++){ //run for the weights

                        for (int l = 0; l < this.ExpectedOutput.length;l++) { //run for the outputs

                            try {

                                NewWeight = network[i][j].GetWeight()[k] +
                                (config.GetLearnTax() *
                                ((this.ExpectedOutput[j] - network[network.length - 1][l].GetValue())
                                * network[i][j].GetValue()));

                            } catch (Exception ex) {
                                //System.out.println(ex.getMessage()); retorna null. Não mostra o erro
                            }

                        }

                        network[i][j].UpdateWeight(NewWeight, k);
                        //System.out.println("Layer: "+i+"Neuron: "+j+"W: "+network[i][j].GetWeight()[k]);
                    }

                }

            }

    }

    private void Save(){

        String[][][] Content = new String[network.length][][];

        for (int i = 0; i < network.length-1;i++){
            Content[i] = new String[network[i].length][];
            for (int j = 0; j < network[i].length;j++){
                Content[i][j] = new String[network[i][j].GetWeight().length];
                for (int k = 0; k < network[i][j].GetWeight().length;k++){
                    Content[i][j][k] = String.valueOf(network[i][j].GetWeight()[k]);
                }
            }
        }

        try {

            FileWriter writer = new FileWriter(
                    "/home/matheus/IdeaProjects/ann-prototype/src/data.txt");

            BufferedWriter Pen = new BufferedWriter(writer);

            //ANN info
            //Pen.write("Epochs " + config.GetEpochs());
            //Pen.newLine();
            //Pen.write("LearnTax " + config.GetLearnTax());


            //ANN Weights
            for (int i = 0; i < network.length-1;i++){

                for (int j = 0; j < network[i].length;j++){

                    for (int k = 0; k < network[i][j].GetWeight().length;k++){

                        Pen.write(Content[i][j][k]);
                        Pen.newLine();
                    }
                }
            }
            Pen.close();
            System.out.println("Weights saved");

        }
        catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }


    }

    private void LoadData(){


        File file = new File("/home/matheus/IdeaProjects/ann-prototype/src/data.txt");

        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));

            for (int i = 0; i < network.length;i++){
                for (int j = 0; j < network.length;j++){
                    for (int k = 0; k < network.length;k++){

                        String line;
                        while ((line = reader.readLine()) != null) {

                            network[i][j].UpdateWeight(Double.valueOf(line),k);

                        }
                    }
                }
            }
        }
        catch (Exception ex){
            System.out.println("Opening the file error: " + ex.getMessage());
        }
    }

    public void See(){

        try {

            for (int i = 0; i < this.network[this.network.length-1].length;i++){
                System.out.println(i+")Expected: " + this.ExpectedOutput[i]);
                System.out.println(i+ " -> " + this.network[this.network.length-1][i].GetValue());
            }

        }
        catch (Exception ex){
            System.out.println("\n\n" + ex.getMessage());
            System.out.println("\n\n" + ex.getCause());
        }

    }

}
