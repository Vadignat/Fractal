package ru.vadignat.graphics;

import ru.vadignat.Converter;
import ru.vadignat.Painter;
import ru.vadignat.math.Complex;
import ru.vadignat.math.fractals.Fractal;

import java.awt.*;

public class FractalPainter implements Painter {
    private Converter cvrt;
    private Fractal f;
    private Colorizer colorFunc;

    public Colorizer getColorFunc() {
        return colorFunc;
    }
    public void setColorFunc(Colorizer colorFunc) {
        this.colorFunc = colorFunc;
    }

    public void changeConverter(Converter cvrt){
        this.cvrt = cvrt;
    }
    public Converter getConverter(){return this.cvrt;}

    public FractalPainter(){}
    public FractalPainter(Converter cvrt, Fractal f){
        this.cvrt = cvrt;
        this.f = f;
    }
    public FractalPainter(Fractal f, Colorizer colorFunc){
        this.f = f;
        this.colorFunc = colorFunc;
    }

    @Override
    public void paint(Graphics g, int width, int height) {
        if(cvrt == null){
            cvrt = new Converter(-2.,1.,-1.,1., width, height);
        }
        else{
        cvrt = new Converter(cvrt.getxMin(),cvrt.getxMax(), cvrt.getyMin(),cvrt.getyMax(), width, height);
        }
        for(int i = 0; i < width; i++){
            for (int j = 0; j < height; j++)
            {
                double x = cvrt.xScr2Crt(i);
                double y = cvrt.yScr2Crt(j);
                float r = f.isInSet(new Complex(x, y));
                Color c = colorFunc.getColor(r);
                g.setColor(c);
                g.drawOval(i, j, 1, 1);
            }
        }
    }
}
