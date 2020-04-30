package com.mechadragonx.pid;

public class PIDController {
    // Constants
    private double kProp;
    private double kInt;
    private double kDer;

    // Values to check
    private double desired;
    private double current = 0.0;
    private double max = Double.MAX_VALUE;

    // Error
    private double curError = 0.0;
    private double prevError = 0.0;
    private double error = 0.0;

    // Time
    private double startTime;
    private double timeInc;
    private double cycleCount;
    private double deltaTime;

    public PIDController(double kProp, double kInt, double kDer, double desired, double timeInc) {
        this.kProp = kProp;
        this.kInt = kInt;
        this.kDer = kDer;
        this.desired = desired;
        this.timeInc = timeInc;
        startTime = System.currentTimeMillis();
    }
    public PIDController(double kProp, double kInt, double kDer, double desired, double timeInc, double max) {
        this(kProp, kInt, kDer, desired, timeInc);
        this.max = max;
    }

    private void setCurrent(double current) {
        this.current = current;
    }
    private double calcCurrentError() {
        // prevError = curError;
        curError = desired - current;
        error = prevError + curError;
        return curError;
    }
    private double calcProportion() {
        return curError * kProp;
    }
    private double calcIntegral() {
//        if(Math.abs(error) < Math.abs(max))
//            this.error += curError * timeInc;
//        else
//            error = 0;
//        return error * kInt;
        if(curError < 7)
            error += (curError * deltaTime);
        else
            error = 0;
        return error * kInt;
    }
    private double calcDerivative() {
        return kDer * ((curError - prevError) / timeInc);
    }
    private double incrementCycle() {
        cycleCount++;
        deltaTime = timeInc * cycleCount;
        return deltaTime;
    }

    public void setMax(double max) {
        this.max = max;
        // reset();
    }
    public double getOutput(double current) {
        // incrementCycle();
        deltaTime = System.currentTimeMillis() - startTime;
        setCurrent(current);
        double prop = 0;
        double integ = 0;
        double der = 0;

        calcCurrentError();
        prop = calcProportion();
        integ = calcIntegral();
        der = calcDerivative();
        prevError = curError;

        return prop + integ + der;
    }
    public void reset() {
        // Reset values to check
        current = 0.0;

        // Reset error
        curError = 0.0;
        prevError = 0.0;
        error = 0.0;
    }
}
