package com.first_semester_work;

public class Ball implements IFigure{
    Point3D p;
    double r;

    Ball(Point3D p, double r) throws Exception {
        if (r <= 0)
            throw new Exception("Некорректный радиус.");
        this.p = p;
        this.r = r;
    }
    Point3D getP(){
        return p;
    }
    void setP(Point3D p){
        this.p = p;
    }
    double getR(){
        return r;
    }
    void setR(double r){
        this.r = r;
    }

    @Override
    public double square() {
        return 4 * Math.PI * Math.pow(r, 2);
    }

    @Override
    public double volume() {
        return 4.0 / 3 * Math.PI * Math.pow(r, 3);
    }

    public String toString(){
        String string = "Coordinates of the central point: \nx: ";
        string = string.concat(Double.toString(this.p.getX(0))) + "\ny: ";
        string = string.concat(Double.toString(this.p.getX(1))) + "\n";
        string = string.concat(Double.toString(this.p.getX(2))) + "\nRadius: ";
        string = string.concat(Double.toString(this.r));
        return string;
    }
}
