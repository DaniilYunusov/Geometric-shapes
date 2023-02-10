package com.first_semester_work;

public class QGon extends NGon{

    public QGon(Point2D[] p) throws Exception {
        super(p);
        if (n != 4)
            throw new Exception("Это не четырехугольник.");

    }
    public double square() throws Exception {
        NGon n = new NGon(this.getP());
        return n.square();
//        return Math.abs((p[0].getX(0) - p[1].getX(0))*(p[0].getX(1) + p[1].getX(1)) +
//                            (p[1].getX(0) - p[2].getX(0))*(p[1].getX(1) + p[2].getX(1)) +
//                            (p[2].getX(0) - p[3].getX(0))*(p[2].getX(1) + p[3].getX(1)) +
//                            (p[3].getX(0) - p[0].getX(0))*(p[3].getX(1) - p[0].getX(1))) / 2;
    }
    public String toString(){
        String string = "Четырехугольник: [";
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
