package com.first_semester_work;

public class TGon extends NGon{

    public TGon(Point2D[] p) throws Exception {
        super(p);
    }
    public double square(){
        double one_one = p[0].getX(0) - p[2].getX(0);
        double one_two = p[0].getX(1) - p[2].getX(1);
        double two_one = p[1].getX(0) - p[2].getX(0);
        double two_two = p[1].getX(1) - p[2].getX(1);
        return Math.abs(one_one * two_two - two_one * one_two) / 2;
    }

//    public TGon rot(double phi) throws Exception{
//        return (TGon) super.rot(phi);
//    }

//    @Override
//    public TGon rot(double phi) throws Exception {
//        Point2D[] s = new Point2D[this.n];
//        for (int i = 0; i < this.n; i++) {
//            s[i] = this.p[i].rot(phi);
//        }
//        return new TGon(s);
//    }

    public String toString(){
        String string = "Треугольник: [";
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
