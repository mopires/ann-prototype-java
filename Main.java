public class Main {

    public static void main(String[] args) {


        double[] numbers = new double[2];
        double[] exout = new double[1];

        numbers[0] = 1;
        numbers[1] = 2;

        exout[0] = 3;

        Network network = new Network(6); //ultima camada não tem peso
        network.SetupTrainning(numbers, exout);




    }


}
