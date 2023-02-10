package com.first_semester_work;

public class Segment extends OpenFigure{
    Point2D start;
    Point2D finish;

    public Segment(Point2D s, Point2D f){
        start = s;
        finish = f;
    }
    public Point2D getStart(){
        return start;
    }
    void setStart(Point2D a){
        start = a;
    }
    public Point2D getFinish(){
        return finish;
    }
    void setFinish(Point2D a){
        finish = a;
    }
    @Override
    public double length() {
        return Math.sqrt(Math.pow(finish.getX(0) - start.getX(0), 2) +
                         Math.pow(finish.getX(1) - start.getX(1), 2));
    }
    @Override
    public Segment shift(Point2D a) throws Exception {
        double []s = new double[2];
        double []f = new double[2];
        s[0] = start.getX(0) + a.getX(0);
        s[1] = start.getX(1) + a.getX(1);
        f[0] = finish.getX(0) + a.getX(0);
        f[1] = finish.getX(1) + a.getX(1);
        Point2D p1 = new Point2D(s);
        Point2D p2 = new Point2D(f);
        return new Segment(p1, p2);
    }
    @Override
    public Segment rot(double phi) throws Exception {
//        Point2D p1 = new Point2D();
//        Point2D p2 = new Point2D();
//        p1 = start.rot(phi);
//        p2 = finish.rot(phi);
        return new Segment(start.rot(phi), finish.rot(phi));
    }

    @Override
    public Segment symAxis(int i) throws Exception {
        int j;
        if (i == 0){
            j = 1;
        }
        else if (i == 1) {
            j = 0;
        }
        else {
            throw new Exception("Incorrect axis!!!");
        }
        Point2D p1 = new Point2D();
        Point2D p2 = new Point2D();
        p1.setX(start.getX(0), 0);
        p1.setX(start.getX(1), 1);
        p2.setX(finish.getX(0), 0);
        p2.setX(finish.getX(1), 1);
        if (start.getX(j) != 0)
            p1.setX(-start.getX(j), j);
        if (finish.getX(j) != 0)
            p2.setX(-finish.getX(j), j);
        return new Segment(p1, p2);
    }

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
        if (i instanceof Segment){
            boolean f =Intersection(start, finish, ((Segment) i).start, ((Segment) i).finish);
            if (f)
                return true;
        }
        else if (i instanceof Polyline){
            for (int j = 1; j < ((Polyline) i).n; j++) {
                boolean f = Intersection(start, finish, ((Polyline) i).p[j-1], ((Polyline) i).p[j]);
                if (f)
                    return true;
            }
        }
        else if (i instanceof NGon)
        {
            for (int j = 1; j < ((NGon) i).n; j++) {
                boolean f = Intersection(start, finish, ((NGon) i).p[j-1], ((NGon) i).p[j]);
                boolean f1 = Intersection(start, finish, ((NGon) i).p[((NGon) i).n-1], ((NGon) i).p[0]);
                if (f || f1)
                    return true;
            }
        }
        else if (i instanceof Circle){
            boolean f = commonSectionCircle(start, finish, ((Circle) i).p, ((Circle) i).r);
            if (f)
                return true;
        }
        return false;
    }

    public String toString(){
        String p = "Отрезок: [(";
        p = p.concat(Double.toString(start.getX(0))) + ",";
        p = p.concat(Double.toString(start.getX(1))) + ");(";
        p = p.concat(Double.toString(finish.getX(0))) + ",";
        p = p.concat(Double.toString(finish.getX(1))) + ")]";
        return p;
    }
}
