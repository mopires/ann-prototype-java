public class Main {

    public static void main(String[] args) {

        Network network = new Network(10); //ultima camada não tem peso

        double[] numbers = new double[500];
        for (int i = 0;i < numbers.length; i++) numbers[i] = i+1;

        network.Input(numbers);
        Neuron[] OutPut = network.Run();


        for (int i = 0; i < OutPut.length; i++){
            System.out.println(" "+OutPut[i].GetValue());
        }



    }


}
