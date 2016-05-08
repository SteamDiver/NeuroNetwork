import sun.plugin.javascript.navig4.Layer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class NeuralNW {

    double[] NWInput = new double[2];
    static int nNumber;
    List<NeuroLayer> Layers = new ArrayList<>();
    static List<Double> data = new ArrayList<>();
    static double OUTDATA;
    double T = 2;

    public static void main(String[] args) throws IOException {

        NeuralNW testnw = new NeuralNW();
        testnw.NWInput = testnw.Normalize(new double[]{1, 1});

        testnw.RunNetwork(testnw.NWInput);
        testnw.Teach();

        /*for (double out : testnw.Layers.get(2).OUTPUT) {
            System.out.println(out);

        }*/
        System.out.println(OUTDATA);

    }

    private void RunNetwork(double[] inputdata) throws IOException {

        //создаем  слои
        LoadNW();
        Layers.add(new NeuroLayer(2, 2, "First", inputdata));
        Layers.add(new NeuroLayer(3, 6, "Hidden", Layers.get(0).OUTPUT));
        Layers.add(new NeuroLayer(1, 3, "Out", Layers.get(1).OUTPUT));
        OUTDATA = Layers.get(2).OUTPUT[0];
        SaveNW();
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

    private void SaveNW() throws IOException {
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

    private void Teach() throws IOException {

        /*double[] teach = new double[4];

        BufferedReader readerteach = new BufferedReader(new FileReader("learn.txt"));
        BufferedReader readerteachout = new BufferedReader(new FileReader("learn-out.txt"));

        String[] text = readerteach.readLine().split("\\s+");
        for (int i = 0; i < text.length; i++) {
            teach[i] = Double.valueOf(text[i]);
            //System.out.print(teach[i]);
        }*/
        //T = Double.parseDouble(readerteachout.readLine());
        //T=4;
        //System.out.println(T);
        //NWInput = teach;
        /*for (Neuron neuron : Layers.get(Layers.size()-1).NeuronList) {
            neuron.CorrectWeightsC(T);
        }*/

        for (int i = 0; i < Layers.size() - 1; i++) {
            for (Neuron neuron : Layers.get(i).NeuronList) {
                neuron.CorrectWeightsB();
            }
        }

        for (Neuron neuron : Layers.get(Layers.size() - 1).NeuronList) {
            neuron.CorrectWeightsC(T);
        }
        //readerteach.close();
        //readerteachout.close();
        SaveNW();

    }


}

