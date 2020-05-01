package com.mechadragonx.pid;

public class Main {
    public static void main(String[] args) {
        PIDController controller = new PIDController(0.15, 0.1, 0.1, 60, 1, 4);
        double current = 40;
        int iteration = 0;

        while(iteration < 100) {
            iteration++;
            current += controller.getOutput(current);
            System.out.println(iteration + ": " + current);
        }
    }
}
