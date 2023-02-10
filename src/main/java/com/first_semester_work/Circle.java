package com.first_semester_work;

public class Circle implements IShape{
    Point2D p;
    double r;

    public Circle(Point2D p, double r) throws Exception {
        if (r <= 0)
            throw new Exception("Некорректный радиус.");
        this.p = p;
        this.r = r;
    }
    public Point2D getP(){
        return p;
    }
    public void setP(Point2D p){
        this.p = p;
    }
    public double getR(){
        return r;
    }
    public void setR(double r){
        this.r = r;
    }
    @Override
    public double square() {
        return Math.PI * Math.pow(r, 2);
    }
    @Override
    public double length() {
        return 2 * Math.PI * r;
    }
    @Override
    public Circle shift(Point2D a) throws Exception {
        Point2D point = new Point2D();
        point.setX(a.getX(0) + this.p.getX(0), 0);
        point.setX(a.getX(1) + this.p.getX(1), 1);
        return new Circle(point, this.r);
    }

    @Override
    public Circle rot(double phi) throws Exception {
        return new Circle(this.p.rot(phi), this.r);
    }

    @Override
    public Circle symAxis(int i) throws Exception {
        int j;
        if (i == 0){
            j = 1;
        }
        else if (i == 1){
            j = 0;
        }
        else {
            throw new Exception("Incorrect axis!!!");
        }
        Point2D point = new Point2D();
        point.setX(this.p.getX(0), 0);
        point.setX(this.p.getX(1), 1);
        if (this.p.getX(j) != 0)
            point.setX(-this.p.getX(j), j);
        return new Circle(point, this.r);
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
            boolean f = commonSectionCircle(((Segment) i).start, ((Segment) i).finish, p, r);
            if (f)
                return true;
        }
        else if (i instanceof NGon){
            for (int j = 1; j < ((NGon) i).n; j++) {
                boolean f = commonSectionCircle(((NGon) i).p[j-1], ((NGon) i).p[j], p, r);
                if (f)
                    return true;
            }
            boolean f1 = commonSectionCircle(((NGon) i).p[((NGon) i).n - 1], ((NGon) i).p[0], p, r);
            if (f1)
                return true;
        }
        else if (i instanceof Polyline){
            for (int j = 1; j < ((Polyline) i).n; j++) {
                boolean f = commonSectionCircle(((Polyline) i).p[j-1], ((Polyline) i).p[j], p, r);
                if (f)
                    return true;
            }
        }
        else if (i instanceof Circle){
            Segment distance = new Segment(p, ((Circle) i).p);
            if (distance.length() <= r + ((Circle) i).r){
                return true;
            }
        }
        return false;
    }
    public String toString(){
        String string = "Окружность: (Center:(";
        string = string.concat(Double.toString(this.p.getX(0))) + ",";
        string = string.concat(Double.toString(this.p.getX(1))) + ");Radius:";
        string = string.concat(Double.toString(this.r) + ")");
        return string;
    }
}
