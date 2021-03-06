
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;



class NeuroLayer {

    // Число входов в слое
    private int COUNTX;


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
    NeuroLayer(int neurons, int countX, String Layer, double[] input) throws IOException {
        OUTPUT = new double[neurons];
        COUNTX = countX;
        GenerateNeurons(neurons);
        for (int i = 0; i < neurons; i++) {

            switch (Layer) {
                case "First":

                    Neuron.Input[0] = input[i];
                    NeuronList.get(i).OutSignal = NeuronList.get(i).Summator();
                    OUTPUT[i] = NeuronList.get(i).OutSignal;
                    break;
                case "Hidden":
                    Neuron.Input = input;
                    NeuronList.get(i).Result();
                    OUTPUT[i] = NeuronList.get(i).OutSignal;
                    break;
                case "Out":
                    Neuron.Input = input;
                    NeuronList.get(i).OutSignal = NeuronList.get(i).Summator();
                    OUTPUT[i] = NeuronList.get(i).OutSignal;
                    break;
            }


        }


    }


    void SaveLayer() throws IOException {
        for (Neuron aNeuronList : NeuronList) {

            aNeuronList.SaveNeuron();
        }
    }


}
