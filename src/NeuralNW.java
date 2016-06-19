

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NeuralNW {

    private double[] NWInput = new double[2];
    static int nNumber;
    List<NeuroLayer> Layers = new ArrayList<>();
    static List<Double> data = new ArrayList<>();
    static double OUTDATA;
    double T = 20;

    public static void main(String[] args) throws IOException {

        NeuralNW testnw = new NeuralNW();
        testnw.NWInput = testnw.Normalize(new double[]{1, 2});

        testnw.RunNetwork(testnw.NWInput);
        teacher.Teach(testnw);
        testnw.GetOutData();

        System.out.println(OUTDATA);

    }

    private void RunNetwork(double[] inputdata) throws IOException {

        //создаем  слои
        LoadNW();
        Layers.add(new NeuroLayer(1, 1, "First", inputdata));
        Layers.add(new NeuroLayer(1, 1, "Hidden", Layers.get(0).OUTPUT));
        Layers.add(new NeuroLayer(1, 1, "Out", Layers.get(1).OUTPUT));

    }
    private void GetOutData(){
        OUTDATA = Layers.get(2).OUTPUT[0];
    }


    private void LoadNW() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("neurons.txt"));
        int i = 0;
        String str;
        while ((str = reader.readLine()) != null) {
            data.add(i, Double.valueOf(str));
            i++;
        }
        reader.close();
    }

    void SaveNW() throws IOException {
        new File("neurons.txt").delete();


        for (NeuroLayer Layer : Layers) {
            Layer.SaveLayer();
        }

    }

    private double[] Normalize(double[] data) {
        double xmin = 0, xmax = 10, d1 = 0, d2 = 1;

        for (int i = 0; i < NWInput.length; i++) {
            data[i] = ((data[i] - xmin) * (d2 - d1)) / (xmax - xmin) + d1;

        }
        return data;

    }



}

