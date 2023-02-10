package com.first_semester_work;

public class Point3D extends Point{
    Point3D() throws Exception {
        super(3);
        x = new double[this.getDim()];
    }
    Point3D(double[] x) throws Exception {
        super(3, x);
    }
    static Point3D cross_prod(Point3D p1, Point3D p2) throws Exception {
        return p1.cross_prod(p2);
    }
    Point3D cross_prod(Point3D p) throws Exception {
        double res_x = x[1] * p.getX(2) - x[2] * p.getX(1);
        double res_y = -(x[0] * p.getX(2) - x[2] * p.getX(0));
        double res_z = x[0] * p.getX(1) - x[1] * p.getX(0);
        return new Point3D(new double[] {res_x, res_y, res_z});
    }
    static double mix_prod(Point3D p1, Point3D p2, Point3D p3){
        return p1.mix_prod(p2, p3);
    }
    double mix_prod(Point3D p1, Point3D p2){
        double summand1 = x[0] * p1.getX(1) * p2.getX(2);
        double summand2 = x[1] * p1.getX(2) * p2.getX(0);
        double summand3 = x[2] * p1.getX(0) * p2.getX(1);
        double summand4 = -(x[2] * p1.getX(1) * p2.getX(0));
        double summand5 = -(x[1] * p1.getX(0) * p2.getX(2));
        double summand6 = -(x[0] * p1.getX(2) * p2.getX(1));
        return summand1 + summand2 + summand3 + summand4 + summand5 + summand6;
    }
}