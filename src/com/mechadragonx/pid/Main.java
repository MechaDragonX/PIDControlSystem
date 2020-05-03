package com.mechadragonx.pid;

import com.mechadragonx.gfx.GraphingControl;

public class Main {
    private static final int ITERATIONS = 50;

    public static void main(String[] args) {
        PIDController controller = new PIDController(0.15, 0.15, 0.1, 60, 1, 4);
        double current = 40;
        int iteration = 0;

        GraphingControl graphCtrl = new GraphingControl(100, 0, ITERATIONS);

        while(iteration < ITERATIONS) {
            iteration++;
            current += controller.getOutput(current);
            System.out.println(iteration + ": " + current);

            graphCtrl.AddValue(current);
            graphCtrl.Draw();
        }
    }
}
