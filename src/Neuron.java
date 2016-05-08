import java.io.*;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Random;


public class Neuron {
    List<Double> Weights = new ArrayList<>();
    // Количество входов
    int In;
    static double[] Input;

    // Выходной сигнал

    double OutSignal;


    public Neuron(int inCount) {
        In = inCount;
        Input = new double[In];

    }


    /* Тест для одного нейрона
    public static void main(String[] args) {
        Neuron n1 = new Neuron(1);
        n1.setWeights(0,0.5);

        double[] input = new double[1];
        input[0]=40;

        n1.Input=input;
        n1.Result();
        System.out.println(n1.OutSignal);



    }*/
    // Заполняем веса случайными числами
    public void GenerateRandomWeights() {
        for (int i = 0; i < In; i++) {
            Weights.add(i, new Random().nextDouble());
        }
    }

    public void setWeights(double newWeight) {
        for (int i = 0; i < In; i++) {
            Weights.add(i, newWeight);
        }

    }

    public void CorrectWeightsC(double T) {

        for (int i = 0; i < In; i++) {
            Weights.set(i, Weights.get(i) -(NeuralNW.OUTDATA - T)*Input[i]);
            //Weights.set(i,Weights.get(i) + 1 / (1 + Math.exp(-Input[i])) * (1 - 1 / (1 + Math.exp(-Input[i])))*(1 / (1 + Math.exp(-Input[i]))-T));
        }


    }

    public void CorrectWeightsB() {

        for (int i = 0; i < In; i++) {
            //Weights.set(i, Weights.get(i) + 1 / (1 + Math.exp(-Input[i])) * (1 - 1 / (1 + Math.exp(-Input[i]))));
            Weights.set(i, Weights.get(i) + 1 / (1 + Math.exp(-Input[i])) * (1 - 1 / (1 + Math.exp(-Input[i]))));

        }
    }


    public void SaveNeuron() throws IOException {
        FileWriter writer = new FileWriter("neurons.txt", true);
        for (int i = 0; i < Weights.size(); i++) {

            writer.write(String.valueOf(Weights.get(i)) + "\n");
        }
        writer.close();
    }

    public void LoadNeuron() throws IOException {

        //BufferedReader reader = new BufferedReader(new FileReader("neurons.txt"));

        for (int i = 0; i < In; i++, NeuralNW.nNumber++) {
            Weights.add(i, NeuralNW.data.get(NeuralNW.nNumber));

        }


    }

    public double Summator() {
        double[] inp = Input;
        double sumsignal = 0;
        for (int i = 0; i < In; i++) {
            double sum = inp[i] * Weights.get(i);
            sumsignal += sum;

        }
        return sumsignal;

    }

    public void Result() {

        OutSignal = 1 / (1 + Math.exp(-Summator()));
        //OutSignal=Math.tanh(Summator());

    }


}
