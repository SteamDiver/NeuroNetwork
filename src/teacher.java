import java.io.IOException;

/**
 * Created by panda on 09.06.16.
 */
public class teacher {

    static void Teach(NeuralNW network) throws IOException {
        for (int i = 0; i < network.Layers.size() - 1; i++) {
            for (Neuron neuron : network.Layers.get(i).NeuronList) {
                neuron.CorrectWeightsB();
            }
        }

        for (Neuron neuron : network.Layers.get(network.Layers.size() - 1).NeuronList) {
            neuron.CorrectWeightsC(network.T);
        }
        network.SaveNW();
    }
}
