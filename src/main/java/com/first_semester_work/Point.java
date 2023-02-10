package com.first_semester_work;

public class Point {
    int dim;
    double[] x;

    Point(int dim) throws Exception {
        if (dim > 0)
        {
            x = new double[dim];
            this.dim = dim;
        }
        else
            throw new Exception("Incorrect dimensions!!!");
    }
    Point(int dim, double[] x) throws Exception {
        if (dim != x.length)
            throw new Exception("Incorrect dimensions!!!");
        this.dim = dim;
        this.x = x;
    }
    public int getDim(){
        return dim;
    }
    public double[] getX(){
        return x;
    }
    public double getX(int i) {
        return x[i];
    }
    public void setX(double[] x){
        this.x = x;
    }
    public void setX(double x, int i){
        this.x[i] = x;
    }
    double abs(){
        double distance = 0;
        for (double v : x) {
            distance += Math.pow(v, 2);
        }
        return Math.sqrt(distance);
    }
    static Point add(Point a, Point b) throws Exception {
        return a.add(b);
    }
    Point add(Point b) throws Exception {
        Point p = new Point(this.getDim());
        for (int i = 0; i < this.getDim(); i++) {
            p.setX(this.getX(i) + b.getX(i), i);
        }
        return p;
//        double[] p = new double[dim];
//        for (int i = 0; i < dim; i++){
//            p[i] = b.getX(i) + x[i];
//        }
//        return new Point(dim, p); //maby not
    }
    static Point sub(Point a, Point b) throws Exception {
        return a.sub(b);
    }
    Point sub(Point b) throws Exception {
        Point p = new Point(this.getDim());
        for (int i = 0; i < this.getDim(); i++) {
            double x = this.getX(i) - b.getX(i);
//            p.setX(this.getX(i) - b.getX(i), i);
            p.setX(x, i);
        }
        return p;
//        double[] p = new double[dim];
//        for (int i = 0; i < dim; i++) {
//            p[i] = x[i] - b.getX(i);
//        }
//        return new Point(dim, p); //maby not
    }
    static  Point mult(Point a, double r) throws Exception {
        return a.mult(r);
    }
    Point mult(double r) throws Exception {
        Point p = new Point(this.getDim());
        for (int i = 0; i < this.getDim(); i++) {
            p.setX(this.getX(i) * r, i);
        }
        return p;
    }
    static double mult(Point a, Point b){
        return a.mult(b);
    }
    double mult(Point b){
        double scalar = 0;
        for (int i = 0; i < this.getDim(); i++) {
            scalar += this.getX(i) * b.getX(i);
        }
        return scalar;
    }
    static Point symAxis(Point a, int i) throws Exception {
        double[] p = new double[a.getDim()];
        for (int j = 0; j < a.getDim(); j++) {
            p[j] = a.getX(j);
        }
        p[i] = -p[i];
        return new Point(2, p);
    }
    public String toString(){
        String p = "Dim: ";
        p = p.concat(Integer.toString(dim)) + "\nCoords: ";
        for (int i = 0; i < dim; i++) {
            p = p.concat(Double.toString(x[i])) + " ";
        }
        p += "\n";
        return p;
    }
//    public String toString(){
//        String str = "";
//        str += Integer.toString(dim) + " ";
//        for (int i = 0; i < dim; i++) {
//            str += Double.toString(getX(i)) + " ";
//        }
//        return str;
//    }
}