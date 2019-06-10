import java.io.*;

public class Feed {

    public Feed(){}

    public int[][] LoadData(){


        File file = new File("/home/matheus/IdeaProjects/ann-prototype/src/dataset.txt");

        //int[][] Data = new int[(short) file.length()][3];
        int[][] Data = new int[(int) file.length()][3];
        try {

            BufferedReader reader = new BufferedReader(new FileReader(file));


                String line;
                String[] tmp = new String[3];

                int i = 0;
                while ((line = reader.readLine()) != null) {

                    tmp = line.split(",");
                    Data[i][0] = Integer.valueOf(tmp[0]);
                    Data[i][1] = Integer.valueOf(tmp[1]);
                    Data[i][2] = Integer.valueOf(tmp[2]);

                    if (i == (short) file.length()){
                        break;
                    }
                    i++;
                }
        }
        catch (Exception ex){
            System.out.println("Opening the file error: " + ex.getMessage());
        }

        return Data;
    }

    public void txt(){

        try {

            FileWriter writer = new FileWriter(
                    "/home/matheus/IdeaProjects/ann-prototype/src/dataset.txt");

            BufferedWriter Pen = new BufferedWriter(writer);

            for (int i = 1; i < 1000; i++) {
                for (int j = 1; j < 1000; j++) {

                    Pen.write(i+","+j+","+(i+j));
                    Pen.newLine();
                }
            }

            Pen.close();
            System.out.println("dataset criado");

        }
        catch (IOException e) {
            System.err.format("IOException: %s%n", e);
        }


    }


}

