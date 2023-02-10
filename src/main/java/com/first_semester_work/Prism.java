package com.first_semester_work;

public class Prism implements IFigure{
    int n;
    Point3D[] p1;
    Point3D[] p2;

    Prism(Point3D[] p1, Point3D[] p2) throws Exception {
        if (p1.length != p2.length)
            throw new Exception("Это не призма.");
        this.p1 = p1;
        this.p2 = p2;
        this.n = p1.length + p2.length;
        double h1;
        double h2;
        h1 = get_height(p1[0], p1[n / 2 - 1], p1[1], p2[0]);
        for (int i = 1; i < n / 2 - 1; i++) {
            h2 = get_height(p1[i], p1[i-1], p1[i+1], p2[i]);
            if (h1 != h2 || Math.abs(h1) == 0 || Math.abs(h2) == 0)
                throw new Exception("This is not prism!!!");
            h1 = h2;
        }
        h2 = get_height(p1[n/2 - 1], p1[n/2 - 2], p1[0], p2[n/2 - 1]);
        if (h1 != h2 || Math.abs(h2) == 0)
            throw new Exception("This is not prism!!!");
    }
    Prism(Point3D[] p){
        n = p.length;
        p1 = new Point3D[n / 2];
        p2 = new Point3D[n / 2];
        for (int i = 0; i < n / 2; i++) {
            p1[i] = p[i];
            p2[i] = p[i + n / 2];
        }
    }
    int getN(){
        return n;
    }
    Point3D[] getP(){
        Point3D[] p = new Point3D[p1.length + p2.length];
        System.arraycopy(p1, 0, p, 0, p1.length);
        System.arraycopy(p2, 0, p, p1.length, p2.length);
        return p;
    }
    void setP(Point3D[] p){
        System.arraycopy(p, 0, this.p1, 0, p.length / 2);
        System.arraycopy(p, 0, this.p2, p.length / 2, p.length);
    }
    Point3D getP(int i){
        if (i < p1.length)
            return p1[i];
        else{
            return p2[i - p1.length];
        }
    }
    void setP(Point3D p, int i){
        if (i < p1.length)
            p1[i] = p;
        else{
            p2[i - p1.length] = p;
        }
    }

    double get_height(Point3D h1, Point3D p1, Point3D p2, Point3D h2) throws Exception {
        double D;
        double h;
        double V;
        Point3D vector1 = new Point3D();
        Point3D vector2 = new Point3D();
        vector1.setX(new double[] {p1.getX(0) - h1.getX(0),
                                   p1.getX(1) - h1.getX(1),
                                   p1.getX(2) - h1.getX(2)});
        vector2.setX(new double[] {p2.getX(0) - h1.getX(0),
                                   p2.getX(1) - h1.getX(1),
                                   p2.getX(2) - h1.getX(2)});
        Point3D n = vector1.cross_prod(vector2);
        D = -1 * (n.getX(0) * h1.getX(0) + n.getX(1) * h1.getX(1) + n.getX(2) * h1.getX(2));
        h = Math.abs(n.getX(0) * h2.getX(0) + n.getX(1) * h2.getX(1) + n.getX(2) * h2.getX(2) + D) / n.abs();
        return h;
    }

    @Override
    public double square() throws Exception {
        double res = 0;
        Point3D vector1 = new Point3D();
        Point3D vector2 = new Point3D();
        for (int i = 0; i < p1.length - 1; i++) {
            vector1.setX(new double[] {p2[i].getX(0) - p1[i].getX(0),
                                       p2[i].getX(1) - p1[i].getX(1),
                                       p2[i].getX(2) - p1[i].getX(2)});
            vector2.setX(new double[] {p2[i].getX(0) - p2[i + 1].getX(0),
                                       p2[i].getX(1) - p2[i + 1].getX(1),
                                       p2[i].getX(2) - p2[i + 1].getX(2)});
            res += vector1.cross_prod(vector2).abs();
        }
        vector1.setX(new double[] {p2[p1.length - 1].getX(0) - p1[p1.length - 1].getX(0),
                                   p2[p1.length - 1].getX(1) - p1[p1.length - 1].getX(1),
                                   p2[p1.length - 1].getX(2) - p1[p1.length - 1].getX(2)});
        vector2.setX(new double[] {p2[p1.length - 1].getX(0) - p2[0].getX(0),
                                   p2[p1.length - 1].getX(1) - p2[0].getX(1),
                                   p2[p1.length - 1].getX(2) - p2[0].getX(2)});
        res += vector1.cross_prod(vector2).abs();
        double s_bottom = 0;
        for (int i = 1; i < p1.length - 1; i++) {
            vector1.setX(new double[] {p1[i].getX(0) - p1[0].getX(0),
                                       p1[i].getX(1) - p1[0].getX(1),
                                       p1[i].getX(2) - p1[0].getX(2)});
            vector2.setX(new double[] {p1[i + 1].getX(0) - p1[0].getX(0),
                                       p1[i + 1].getX(1) - p1[0].getX(1),
                                       p1[i + 1].getX(2) - p1[0].getX(2)});
            s_bottom += vector1.cross_prod(vector2).abs() / 2;
        }
        res += s_bottom * 2;
        return res;
    }

    @Override
    public double volume() throws Exception {
        double s_bottom = 0;
        double h;
        double V;
        Point3D vector1 = new Point3D();
        Point3D vector2 = new Point3D();
        for (int i = 1; i < p1.length - 1; i++) {
            vector1.setX(new double[] {p1[i].getX(0) - p1[0].getX(0),
                                       p1[i].getX(1) - p1[0].getX(1),
                                       p1[i].getX(2) - p1[0].getX(2)});
            vector2.setX(new double[] {p1[i + 1].getX(0) - p1[0].getX(0),
                                       p1[i + 1].getX(1) - p1[0].getX(1),
                                       p1[i + 1].getX(2) - p1[0].getX(2)});
            s_bottom += vector1.cross_prod(vector2).abs() / 2;
        }
        h = get_height(p1[0], p1[1], p1[2], p2[0]);
        V = s_bottom * h;
        return V;
    }

    public String toString(){
        String string = "Count point: " + n + "\nPoint coordinates: \n";
        for (int i = 0; i < this.p1.length; i++) {
            string += "Point ";
            string = string.concat(Integer.toString(i)) + ": ";
            string = string.concat(Double.toString(p1[i].getX(0))) + " ";
            string = string.concat(Double.toString(p1[i].getX(1))) + "\n";
            string = string.concat(Double.toString(p1[i].getX(2))) + "\n";
        }
        for (int i = 0; i < this.p2.length; i++) {
            string += "Point ";
            string = string.concat(Integer.toString(i)) + ": ";
            string = string.concat(Double.toString(p2[i].getX(0))) + " ";
            string = string.concat(Double.toString(p2[i].getX(1))) + "\n";
            string = string.concat(Double.toString(p2[i].getX(2))) + "\n";
        }
        return string;
    }
}
