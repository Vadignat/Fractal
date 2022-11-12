package ru.vadignat.math;

import org.jetbrains.annotations.NotNull;

public class Complex{
    private double re, im;

    public double getRe() {
        return re;
    }

    public double getIm() {
        return im;
    }

    public void setRe(double re)
    {
        this.re = re;
    }

    public void setIm(double im) {
        this.im = im;
    }

    public Complex(double r, double i)
    {
        re = r;
        im = i;
    }

    public Complex(Complex other){
        re = other.re;
        im = other.im;
    }

    public Complex(double r){
        re = r;
        im = 0.0;
    }

    public Complex plus(@NotNull Complex complex){
        return new Complex(re + complex.re, im + complex.im);
    }
    public Complex minus(@NotNull Complex complex){
        return new Complex(re - complex.re, im - complex.im);
    }
    public Complex times(@NotNull Complex complex){
        return new Complex(
                re * complex.re - im * complex.im,
                re * complex.im + im * complex.re
                );
    }
    public Complex div(@NotNull Complex complex){
        double den = complex.abs2();
        return new Complex(
                (re * complex.re + im * complex.im)/ den,
                (- re * complex.im + im * complex.re) / den
        );

    }
    public double abs(){
        return Math.sqrt(re * re + im * im);
    }
    public double abs2(){
        return re * re + im * im;
    }

    @Override
    public String toString() {
        return re + " + " + im + "i";
    }
}
