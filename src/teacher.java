import java.io.IOException;

class teacher {

    static void Teach(NeuralNW network) throws IOException {
        for (int i = 0; i < network.Layers.size() - 1; i++) {
            network.Layers.get(i).NeuronList.forEach(Neuron::CorrectWeightsB);
        }

        for (Neuron neuron : network.Layers.get(network.Layers.size() - 1).NeuronList) {
            neuron.CorrectWeightsC(network.T);
        }
        network.SaveNW();
    }
}
