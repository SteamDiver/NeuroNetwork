import java.io.*;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Random;


public class Neuron {
    private List<Double> Weights = new ArrayList<>();

    private int In;     // Количество входов
    static double[] Input;// Вход
    double OutSignal;   // Выходной сигнал

    double delta;


    Neuron(int inCount) {
        In = inCount;
        Input = new double[In];

    }
    // Заполняем веса случайными числами
    public void GenerateRandomWeights() {
        for (int i = 0; i < In; i++) {
            Weights.add(i, new Random().nextDouble());
        }
    }
    void setDelta(){
    }

    public void setWeights(double newWeight) {
        for (int i = 0; i < In; i++) {
            Weights.add(i, newWeight);
        }

    }

    void CorrectWeightsC(double T) {

        for (int i = 0; i < In; i++) {
            //Weights.set(i, Weights.get(i) -(NeuralNW.OUTDATA - T)*Input[i]);
            Weights.set(i,Weights.get(i) + 1 / (1 + Math.exp(-Input[i])) * (1 - 1 / (1 + Math.exp(-Input[i])))*(1 / (1 + Math.exp(-Input[i]))-T));
        }


    }

    void CorrectWeightsB() {

        for (int i = 0; i < In; i++) {
            Weights.set(i, Weights.get(i) + 1 / (1 + Math.exp(-Input[i])) * (1 - 1 / (1 + Math.exp(-Input[i]))));

        }
    }


    void SaveNeuron() throws IOException {
        FileWriter writer = new FileWriter("neurons.txt", true);
        for (Double Weight : Weights) {

            writer.write(String.valueOf(Weight) + "\n");
        }
        writer.close();
    }

    void LoadNeuron() throws IOException {

        for (int i = 0; i < In; i++, NeuralNW.nNumber++) {
            Weights.add(i, NeuralNW.data.get(NeuralNW.nNumber));

        }


    }

    double Summator() {
        double[] inp = Input;
        double sumsignal = 0;
        for (int i = 0; i < In; i++) {
            double sum = inp[i] * Weights.get(i);
            sumsignal += sum;

        }
        return sumsignal;

    }

    void Result() {

        OutSignal = 1 / (1 + Math.exp(-Summator()));

    }


}
