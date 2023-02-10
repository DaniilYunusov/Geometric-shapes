package com.first_semester_work;

public class Point2D extends Point{
    public Point2D() throws Exception {
        super(2);
        x = new double[this.getDim()];
    }
    public Point2D(double[] x) throws Exception {
        super(2, x);
    }
    static Point2D rot(Point2D p, double phi) throws Exception {
        return p.rot(phi);
    }
    Point2D rot(double phi) throws Exception {
        Point p1 = Point.mult(this, Math.cos(phi));
        Point p2 = Point.mult(this, Math.sin(phi));
        double X = p1.getX(0) - p2.getX(1);
        double Y = p2.getX(0) + p1.getX(1);
        return new Point2D(new double[]{X, Y});
    }
}