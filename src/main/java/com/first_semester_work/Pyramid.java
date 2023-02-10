package com.first_semester_work;

public class Pyramid implements IFigure{
    Point3D     p0;
    int         n;
    Point3D[]   p;

    Pyramid(Point3D p0, Point3D[] p) throws Exception {
        this.p0 = p0;
        this.p  = p;
        this.n  = p.length + 1;
        if (Math.abs(get_height()) == 0)
            throw new Exception("Это не пираммида.");
    }
    Pyramid(Point3D[] p) throws Exception {
        this.n     = p.length;
        this.p     = new Point3D[p.length - 1];
        this.p0    = p[0];
        System.arraycopy(p, 1, this.p, 0, p.length - 1);
        if (Math.abs(get_height()) == 0)
            throw new Exception("This is not pyramid!!!");
    }
    int getN(){
        return n;
    }
    Point3D[] getP(){
        return p;
    }
    void setP(Point3D[] p){
        this.p = p;
    }
    Point3D getP(int i){
        return p[i];
    }
    void setP(Point3D p, int i){
        this.p[i] = p;
    }
    double get_height() throws Exception {
        double D;
        double h;
        double V;
        Point3D vector1 = new Point3D();
        Point3D vector2 = new Point3D();
        vector1.setX(new double[] {p[1].getX(0) - p[0].getX(0),
                                   p[1].getX(1) - p[0].getX(1),
                                   p[1].getX(2) - p[0].getX(2)});
        vector2.setX(new double[] {p[2].getX(0) - p[0].getX(0),
                                   p[2].getX(1) - p[0].getX(1),
                                   p[2].getX(2) - p[0].getX(2)});
        Point3D n = vector1.cross_prod(vector2);
        D = -1 * (n.getX(0) * p[0].getX(0) + n.getX(1) * p[0].getX(1) + n.getX(2) * p[0].getX(2));
        h = Math.abs(n.getX(0) * p0.getX(0) + n.getX(1) * p0.getX(1) + n.getX(2) * p0.getX(2) + D) / n.abs();
        return h;
    }
    @Override
    public double square() throws Exception {
        double res = 0;
        Point3D vector1 = new Point3D();
        Point3D vector2 = new Point3D();
        for (int i = 0; i < n - 2; i++) {
            vector1.setX(new double[] {p[i].getX(0) - p0.getX(0),
                                       p[i].getX(1) - p0.getX(1),
                                       p[i].getX(2) - p0.getX(2)});
            vector2.setX(new double[] {p[i + 1].getX(0) - p0.getX(0),
                                       p[i + 1].getX(1) - p0.getX(1),
                                       p[i + 1].getX(2) - p0.getX(2)});
            res += vector1.cross_prod(vector2).abs() / 2;
        }
        vector1.setX(new double[] {p[n - 2].getX(0) - p0.getX(0),
                                   p[n - 2].getX(1) - p0.getX(1),
                                   p[n - 2].getX(2) - p0.getX(2)});
        vector2.setX(new double[] {p[0].getX(0) - p0.getX(0),
                                   p[0].getX(1) - p0.getX(1),
                                   p[0].getX(2) - p0.getX(2)});
        res += vector1.cross_prod(vector2).abs() / 2;
        for (int i = 1; i < p.length - 1; i++) {
            vector1.setX(new double[] {p[i].getX(0) - p[0].getX(0),
                                       p[i].getX(1) - p[0].getX(1),
                                       p[i].getX(2) - p[0].getX(2)});
            vector2.setX(new double[] {p[i + 1].getX(0) - p[0].getX(0),
                                       p[i + 1].getX(1) - p[0].getX(1),
                                       p[i + 1].getX(2) - p[0].getX(2)});
            res += vector1.cross_prod(vector2).abs() / 2;
        }
        return res;
    }

    @Override
    public double volume() throws Exception {
        double s_bottom = 0;
        double h;
        double V;
        Point3D vector1 = new Point3D();
        Point3D vector2 = new Point3D();
        for (int i = 1; i < p.length - 1; i++) {
            vector1.setX(new double[] {p[i].getX(0) - p[0].getX(0),
                                       p[i].getX(1) - p[0].getX(1),
                                       p[i].getX(2) - p[0].getX(2)});
            vector2.setX(new double[] {p[i + 1].getX(0) - p[0].getX(0),
                                       p[i + 1].getX(1) - p[0].getX(1),
                                       p[i + 1].getX(2) - p[0].getX(2)});
            s_bottom += vector1.cross_prod(vector2).abs() / 2;
        }
        h = get_height();
        V = 1.0 / 3 * s_bottom * h;
        return V;
    }
    public String toString(){
        String string = "Count point: " + n + "\nPoint coordinates: \n";
        for (int i = 0; i < this.p.length; i++) {
            string += "Point ";
            string = string.concat(Integer.toString(i)) + ": ";
            string = string.concat(Double.toString(p[i].getX(0))) + " ";
            string = string.concat(Double.toString(p[i].getX(1))) + "\n";
            string = string.concat(Double.toString(p[i].getX(2))) + "\n";
        }
        return string;
    }
}