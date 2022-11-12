package ru.vadignat.math.fractals;

import ru.vadignat.math.Complex;

public class Mandelbrot implements Fractal{
    private int maxIterations;
    private double r;

    public Mandelbrot(){
        maxIterations = 200;
        r = 2.;
    }

    public Mandelbrot(int maxIterations, double r){
        this.maxIterations = maxIterations;
        this.r = r;
    }

    public int getMaxIterations(){
        return maxIterations;
    }
    public void setMaxIterations(int value){
        maxIterations = Math.max(Math.abs(value),25);
    }

    public double getR(){
        return r;
    }
    public void setR(double value) {
        r = Math.max(Math.abs(value), Double.MIN_VALUE);
    }

    @Override
    public float isInSet(Complex c) {
        Complex z = new Complex(0.0);
        int cnt = 0;
        double r2 = r * r;
        while (++cnt < maxIterations) {
            z = z.times(z).plus(c);
            if(z.abs2() > r2)
                return (float) cnt / maxIterations;
        }
        return 1f;
    }
}
