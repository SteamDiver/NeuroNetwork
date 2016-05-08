import sun.plugin.javascript.navig4.Layer;

import java.io.Console;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 */


public class NeuroLayer {

    // Число входов в слое
    int COUNTX;


    //Число нейронов в слое

    double[] OUTPUT;
    List<Neuron> NeuronList = new ArrayList<>();

    // Заполняем слой нейронами
    private void GenerateNeurons(int Count) throws IOException {
        for (int i = 0; i < Count; i++) {
            NeuronList.add(new Neuron(COUNTX / Count)); //добавляем нейрон
            NeuronList.get(i).LoadNeuron(); // устанавливаем веса
        }
    }


    // Конструктор с параметрами. передается количество нейронов, количество входов, входные данные
    public NeuroLayer(int neurons, int countX, String Layer, double[] input) throws IOException {
        OUTPUT = new double[neurons];
        COUNTX = countX;
        GenerateNeurons(neurons);
        for (int i = 0; i < neurons; i++) {

            switch (Layer) {
                case "First":

                    NeuronList.get(i).Input[0] = input[i];
                    NeuronList.get(i).OutSignal = NeuronList.get(i).Summator();
                    OUTPUT[i] = NeuronList.get(i).OutSignal;
                    break;
                case "Hidden":
                    NeuronList.get(i).Input = input;
                    NeuronList.get(i).Result();
                    OUTPUT[i] = NeuronList.get(i).OutSignal;
                    break;
                case "Out":
                    NeuronList.get(i).Input = input;
                    NeuronList.get(i).OutSignal = NeuronList.get(i).Summator();
                    OUTPUT[i] = NeuronList.get(i).OutSignal;
                    break;
            }


        }


    }


    public void SaveLayer() throws IOException {
        for (int i = 0; i < NeuronList.size(); i++) {

            NeuronList.get(i).SaveNeuron();
        }
    }
    /*public void LoadLayer() throws IOException {
        for (int i = 0; i < NeuronList.size(); i++) {

            NeuronList.get(i).LoadNeuron();
        }
    }*/



    /*public static void main(String[] args) {
        double[] input = new double[]{1,1};
        new NeuroLayer(2,2,input);

        System.out.println(OUTPUT[0]);
    }*/

}
