package com.first_semester_work;

public class Polyline extends OpenFigure implements IPolyPoint {
    int       n;
    Point2D[] p;

    public Polyline(Point2D[] p) throws Exception {
        if (p.length < 3)
            throw new Exception("Это не ломаная.");
        this.p = p;
        this.n = p.length;
    }
    public int getN(){
        return n;
    }
    public Point2D[] getP(){
        return p;
    }
    @Override
    public Point2D getP(int i) {
        return p[i];
    }

    public void setP(Point2D[] p){
        this.p = p;
    }
    @Override
    public void setP(Point2D p, int i) {
        this.p[i] = p;
    }
    @Override
    public double length() {
        double len = 0;
        for (int i = 0; i < this.n - 1; i++) {
            len += Math.sqrt(Math.pow(p[i + 1].getX(0) - p[i].getX(0), 2) +
                             Math.pow(p[i + 1].getX(1) - p[i].getX(1), 2));
        }
        return len;
    }
    @Override
    public Polyline shift(Point2D a) throws Exception {
        Point2D[] s = new Point2D[this.n];
        for (int i = 0; i < this.n; i++) {
            Point2D p1 = new Point2D();
            p1.setX(this.p[i].getX(0), 0);
            p1.setX(this.p[i].getX(1), 1);
            s[i] = p1;
        }
        for (Point2D point2D : s) {
            point2D.setX(point2D.getX(0) + a.getX(0), 0);
            point2D.setX(point2D.getX(1) + a.getX(1), 1);
        }
        return new Polyline(s);
    }

    @Override
    public Polyline rot(double phi) throws Exception {
        Point2D[] s = new Point2D[this.n];
        for (int i = 0; i < this.n; i++) {
            s[i] = this.p[i].rot(phi);
        }
        return new Polyline(s);
    }

    @Override
    public Polyline symAxis(int i) throws Exception {
        int k;
        if (i == 0){
            k = 1;
        }
        else if (i == 1){
            k = 0;
        }
        else {
            throw new Exception("Incorrect axis!!!");
        }
        Point2D[] s = new Point2D[this.n];
        for (int j = 0; j < this.n; j++) {
            Point2D p = new Point2D();
            p.setX(this.p[j].getX(0), 0);
            p.setX(this.p[j].getX(1), 1);
            if (this.p[j].getX(k) != 0)
                p.setX(-this.p[j].getX(k), k);
            s[j] = p;
        }
        return new Polyline(s);
    }

//    boolean Intersection(double ax1, double ay1, double ax2, double ay2, double bx1, double by1, double bx2, double by2){
//        double v1, v2, v3, v4;
//        v1 = (bx2 - bx1) * (ay1 - by1) - (by2 - by1) * (ax1 - bx1);
//        v2 = (bx2 - bx1) * (ay2 - by1) - (by2 - by1) * (ax2 - bx1);
//        v3 = (ax2 - ax1) * (by1 - ay1) - (ay2 - ay1) * (bx1 - ax1);
//        v4 = (ax2 - ax1) * (by2 - ay1) - (ay2 - ay1) * (bx2 - ax1);
//        return (v1 * v2 < 0) && (v3 * v4 < 0);
//    }
    boolean Intersection(Point2D a_1, Point2D a_2, Point2D b_1, Point2D b_2){
        double v1, v2, v3, v4;
        v1 = (b_2.getX(0) - b_1.getX(0)) * (a_1.getX(1) - b_1.getX(1)) -
                (b_2.getX(1) - b_1.getX(1)) * (a_1.getX(0) - b_1.getX(0));
        v2 = (b_2.getX(0) - b_1.getX(0)) * (a_2.getX(1) - b_1.getX(1)) -
                (b_2.getX(1) - b_1.getX(1)) * (a_2.getX(0) - b_1.getX(0));
        v3 = (a_2.getX(0) - a_1.getX(0)) * (b_1.getX(1) - a_1.getX(1)) -
                (a_2.getX(1) - a_1.getX(1)) * (b_1.getX(0) - a_1.getX(0));
        v4 = (a_2.getX(0) - a_1.getX(0)) * (b_2.getX(1) - a_1.getX(1)) -
                (a_2.getX(1) - a_1.getX(1)) * (b_2.getX(0) - a_1.getX(0));
        return (v1 * v2 < 0) && (v3 * v4 < 0);
    }

    boolean commonSectionCircle    (Point2D p1, Point2D p2, Point2D C, double R)
    {
        double x1 = p1.getX(0);
        double x2 = p2.getX(0);
        double y1 = p1.getX(1);
        double y2 = p2.getX(1);
        double xC = C.getX(0);
        double yC = C.getX(1);
        x1 -= xC;
        y1 -= yC;
        x2 -= xC;
        y2 -= yC;

        double dx = x2 - x1;
        double dy = y2 - y1;

        //составляем коэффициенты квадратного уравнения на пересечение прямой и окружности.
        //если на отрезке [0..1] есть отрицательные значения, значит отрезок пересекает окружность
        double a = dx*dx + dy*dy;
        double b = 2.*(x1*dx + y1*dy);
        double c = x1*x1 + y1*y1 - R*R;

        //а теперь проверяем, есть ли на отрезке [0..1] решения
        if (-b < 0)
            return (c < 0);
        if (-b < (2.*a))
            return ((4.*a*c - b*b) < 0);

        return (a+b+c < 0);
    }

    @Override
    public boolean cross(IShape i) {
        if (i instanceof Polyline){
            for (int j = 1; j < n; j++) {
                for (int k = 1; k < ((Polyline) i).n; k++) {
                    boolean f = Intersection(p[j-1], p[j], ((Polyline) i).p[k-1], ((Polyline) i).p[k]);
                    if (f)
                        return true;
                }
            }
        }
        else if (i instanceof Segment){
            for (int j = 1; j < n; j++) {
                boolean f = Intersection(p[j-1], p[j], ((Segment) i).start, ((Segment) i).finish);
                if (f)
                    return true;
            }
        }
        else if (i instanceof NGon) {
            for (int j = 1; j < n; j++) {
                for (int k = 1; k < ((NGon) i).n; k++) {
                    boolean f = Intersection(p[j-1], p[j], ((NGon) i).p[k-1], ((NGon) i).p[k]);
                    boolean f1 = Intersection(p[j-1], p[j], ((NGon) i).p[((NGon) i).n-1], ((NGon) i).p[0]);
                    if (f || f1)
                        return true;
                }
            }
        }
        else if (i instanceof Circle){
            for (int j = 1; j < n; j++) {
                boolean f = commonSectionCircle(p[j-1], p[j], ((Circle) i).p, ((Circle) i).r);
                if (f)
                    return true;
            }
        }
        return false;
    }

    public String toString(){
        String string = "Ломаная: [";
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