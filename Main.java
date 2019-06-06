public class Main {

    public static void main(String[] args) {

        Network network = new Network(4); //ultima camada não tem peso

        double[] numbers = new double[2];
        double[] exout = new double[1];

        numbers[0] = 1;
        numbers[1] = 1;


        exout[0] = 2;

        network.SetupTrainning(numbers, exout);



    }


}
