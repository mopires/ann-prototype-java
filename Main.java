import java.util.Date;

public class Main {

    public static void main(String[] args) {


        double[] numbers = new double[2];
        double[] ExpectedOut = new double[1];

        /*
        Feed feed = new Feed();

        int[][] data = new int[feed.LoadData().length][];
        data = feed.LoadData();

        Network network = new Network(5); //ultima camada não tem peso

        for (int i = 0; i < data.length;i++){

            numbers[0] = data[i][0];
            numbers[1] = data[i][1];
            ExpectedOut[0] = data[i][2];

            network.SetupTraining(numbers, ExpectedOut);
        }
        */
        Network network = new Network(5);

        numbers[0] = 1;
        numbers[1] = 1;
        System.out.println(network.Run(false, numbers)[0].GetValue());



    }


}
