package com.first_semester_work;

public class Rectangle extends QGon{
    public Rectangle(Point2D[] p) throws Exception {
        super(p);
        if (n != 4)
            throw new Exception("Это не прямоугольник.");
        Segment segment1, segment2;
        double sk;
        for (int i = 0; i < n - 2; i++) {
            segment1 = new Segment(p[i], p[i+1]);
            segment2 = new Segment(p[i+1], p[i+2]);
            sk = Point.mult(Point.sub(segment1.getFinish(), segment1.getStart()), Point.sub(segment2.getFinish(), segment2.getStart()));
            if (sk != 0)
            {
                throw new Exception("Это не прямоугольник.");
            }
        }
        segment1 = new Segment(p[0], p[n - 1]);
        segment2 = new Segment(p[n - 1], p[n - 2]);
        sk = Point.mult(Point.sub(segment1.getFinish(), segment1.getStart()), Point.sub(segment2.getFinish(), segment2.getStart()));
        if (sk != 0)
            throw new Exception("Это не прямоугольник.");
    }
    public double square() throws Exception {
        double a = new Segment(p[0], p[1]).length();
        double b = new Segment(p[1], p[2]).length();
        return a * b;
    }
    public String toString(){
        String string = "Прямоугольник: [";
        for (int i = 0; i < this.p.length; i++) {
            string += "(";
            string = string.concat(Double.toString(p[i].getX(0))) + ",";
            if (this.p.length - 1 != i)
                string = string.concat(Double.toString(p[i].getX(1))) + ");";
            else
                string = string.concat(Double.toString(p[i].getX(1))) + ")]";
        }
        return string;
    }
}