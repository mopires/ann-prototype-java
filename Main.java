public class Main {

    public static void main(String[] args) {

        Network network = new Network(4); //ultima camada não tem peso

        double[] numbers = new double[4];
        for (int i = 0;i < numbers.length; i++) numbers[i] = i+1;

        network.Input(numbers);
        network.Run();
        network.See();


    }


}
