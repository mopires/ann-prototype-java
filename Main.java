public class Main {

    public static void main(String[] args) {

        Network network = new Network(10); //ultima camada não tem peso

        double[] numbers = new double[2];
        double[] exout = new double[1];

        numbers[0] = 1.0;
        numbers[1] = 1.0;


        exout[0] = 2;

        network.SetupTrainning(numbers, exout);



    }


}
