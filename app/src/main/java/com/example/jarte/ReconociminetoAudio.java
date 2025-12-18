package com.example.jarte;

import be.tarsos.dsp.AudioDispatcher;


import be.tarsos.dsp.io.jvm.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchProcessor;

public class ReconociminetoAudio {
    private AudioDispatcher dispatcher;
    private int sampleRate = 44100;
    private int bufferSize = 2048;
    private int overlap = 0;

    public void start(PitchDetectionHandler handler) {

        dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(sampleRate, bufferSize, overlap);


        PitchProcessor pitchProcessor =
                new PitchProcessor(
                        PitchProcessor.PitchEstimationAlgorithm.YIN,
                        sampleRate,
                        bufferSize,
                        handler
                );

        dispatcher.addAudioProcessor(pitchProcessor);

        new Thread(dispatcher, "Audio Dispatcher").start();
    }

    public void stop() {
        if (dispatcher != null) {
            dispatcher.stop();
        }
    }

}
