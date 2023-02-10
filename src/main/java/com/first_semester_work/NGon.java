package com.first_semester_work;

public class NGon implements IShape, IPolyPoint{
    int n;
    Point2D[] p;

    public NGon(Point2D[] p) throws Exception {
        if (p.length < 3)
            throw new Exception("Это не многоугольник.");
        if (p.length == 3){
            Segment segment1 = new Segment(p[0], p[1]);
            Segment segment2 = new Segment(p[1], p[2]);
            Segment segment3 = new Segment(p[2], p[0]);
            if (segment1.length() + segment2.length() < segment3.length() ||
                segment2.length() + segment3.length() < segment1.length() ||
                segment1.length() + segment3.length() < segment2.length())
                throw new Exception("There is no figure of dimension 3!!!");
        }
        this.p = p;
        this.n = p.length;
    }
    public int getN(){
        return n;
    }
    Point2D[] getP(){
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
    public double square() throws Exception {
        double res = 0;
        for (int i = 0; i < n - 1; i++) {
            res += p[i].getX(0) * p[i + 1].getX(1);
            res -= p[i].getX(1) * p[i + 1].getX(0);
        }
        res += p[n - 1].getX(0) * p[0].getX(1);
        res -= p[n - 1].getX(1) * p[0].getX(0);
        return Math.abs(res) / 2;
    }

    @Override
    public double length() {
        double len = 0;
        for (int i = 0; i < this.n - 1; i++) {
            len += Math.sqrt(Math.pow(p[i + 1].getX(0) - p[i].getX(0), 2) +
                    Math.pow(p[i + 1].getX(1) - p[i].getX(1), 2));
        }
        len += Math.sqrt(Math.pow(p[0].getX(0) - p[n - 1].getX(0), 2) +
                Math.pow(p[0].getX(1) - p[n - 1].getX(1), 2));
        return len;
    }
    @Override
    public NGon shift(Point2D a) throws Exception {
        for (int i = 0; i < n; i++) {
            p[i].setX(Point.add(p[i], a).getX());
        }
        return this;
    }

    @Override
    public NGon rot(double phi) throws Exception {
//        Point2D[] s = new Point2D[this.n];
//        for (int i = 0; i < this.n; i++) {
//            s[i] = this.p[i].rot(phi);
//        }
//        return new NGon(s);
        for (int i = 0; i < n; i++) {
            p[i].setX(p[i].rot(phi).getX());
        }
        return this;
    }

    @Override
    public NGon symAxis(int i) throws Exception {
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
//        Point2D[] s = new Point2D[this.n];
//        for (int j = 0; j < this.n; j++) {
//            Point2D p = new Point2D();
//            p.setX(this.p[j].getX(0), 0);
//            p.setX(this.p[j].getX(1), 1);
//            if (this.p[j].getX(k) != 0)
//                p.setX(-this.p[j].getX(k), k);
//            s[j] = p;
//        }
        for (int j = 0; j < n; j++) {
            if (p[j].getX(k) != 0)
                p[j].setX(-p[j].getX(k), k);
        }
        return this;
    }

    boolean Intersection(Point2D a_1, Point2D a_2, Point2D b_1, Point2D b_2){

        double v1, v2, v3, v4;
        double ax1 = a_1.getX(0), ax2 = a_2.getX(0), ay1 = a_1.getX(1), ay2 = a_2.getX(1);
        double bx1 = b_1.getX(0), bx2 = b_2.getX(0), by1 = b_1.getX(1), by2 = b_2.getX(1);

        v1 = (bx2 - bx1) * (ay1 - by1) - (by2 - by1) * (ax1 - bx1);
        v2 = (bx2 - bx1) * (ay2 - by1) - (by2 - by1) * (ax2 - bx1);
        v3 = (ax2 - ax1) * (by1 - ay1) - (ay2 - ay1) * (bx1 - ax1);
        v4 = (ax2 - ax1) * (by2 - ay1) - (ay2 - ay1) * (bx2 - ax1);
        return (v1 * v2 < 0) && (v3 * v4 < 0);
//        double v1, v2, v3, v4;
//        v1 = (b_2.getX(0) - b_1.getX(0)) * (a_1.getX(1) - b_1.getX(1)) -
//                (b_2.getX(1) - b_1.getX(1)) * (a_1.getX(0) - b_1.getX(0));
//        v2 = (b_2.getX(0) - b_1.getX(0)) * (a_2.getX(1) - b_1.getX(1)) -
//                (b_2.getX(1) - b_1.getX(1)) * (a_2.getX(0) - b_1.getX(0));
//        v3 = (a_2.getX(0) - a_1.getX(0)) * (b_1.getX(1) - a_1.getX(1)) -
//                (a_2.getX(1) - a_1.getX(1)) * (b_1.getX(0) - a_1.getX(0));
//        v4 = (a_2.getX(0) - a_1.getX(0)) * (b_2.getX(1) - a_1.getX(1)) -
//                (a_2.getX(1) - a_1.getX(1)) * (b_2.getX(0) - a_1.getX(0));
//        return (v1 * v2 < 0) && (v3 * v4 < 0);
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
        if (i instanceof Circle)
        {
            for (int j = 1; j < n; j++) {
                boolean f = commonSectionCircle(p[j-1], p[j], ((Circle) i).p, ((Circle) i).r);
                boolean f1 = commonSectionCircle(p[n-1], p[0], ((Circle) i).p, ((Circle) i).r);
                if (f || f1)
                    return true;
            }
        }
        else if (i instanceof Segment){
            for (int j = 1; j < n; j++) {
                boolean f = Intersection(p[j-1], p[j], ((Segment) i).start, ((Segment) i).finish);
                boolean f1 = Intersection(p[n-1], p[0], ((Segment) i).start, ((Segment) i).finish);
                if (f || f1)
                    return true;
            }
        }
        else if (i instanceof Polyline){
            for (int j = 1; j < n; j++) {
                for (int k = 1; k < ((Polyline) i).n; k++) {
                    boolean f = Intersection(p[j-1], p[j], ((Polyline) i).p[k-1], ((Polyline) i).p[k]);
                    boolean f1 = Intersection(p[n-1], p[0], ((Polyline) i).p[k-1], ((Polyline) i).p[k]);
                    if (f || f1)
                        return true;
                }
            }
        }
        else if (i instanceof NGon)
        {
//            for (int j = 0; j < n - 1; j++) {
//                for (int k = 0; k < ((NGon) i).n - 1; k++) {
//                    if (Intersection(p[j], p[j+1], ((NGon) i).p[k], ((NGon) i).p[k+1]))
//                        return true;
//                }
//                if (Intersection(p[j], p[j+1], ((NGon) i).p[0], ((NGon) i).p[((NGon) i).n - 1]))
//                    return true;
//            }
//            if (Intersection(p[0], p[n - 1], ((NGon) i).p[0], ((NGon) i).p[((NGon) i).n - 1]))
//                return true;
            for (int j = 1; j < n; j++) {
                for (int k = 1; k < ((NGon) i).n; k++) {
                    boolean f = Intersection(p[j-1], p[j], ((NGon) i).p[k-1], ((NGon) i).p[k]);
                    boolean f2 = Intersection(p[0], p[n-1], ((NGon) i).p[k-1], ((NGon) i).p[k]);
                    if (f || f2)
                        return true;
                }
                boolean f1 = Intersection(p[j-1], p[j], ((NGon) i).p[0], ((NGon) i).p[((NGon) i).n - 1]);
                if (f1)
                    return true;
            }
            boolean f3 = Intersection(p[0], p[n-1], ((NGon) i).p[0], ((NGon) i).p[((NGon) i).n - 1]);
            if (f3)
                return true;
        }
        return false;
    }

    public String toString(){
        String string = "Многоугольник: [";
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
