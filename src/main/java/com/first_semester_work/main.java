package com.first_semester_work;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Semester_work_1
{
    static Point2D set2D(Scanner in) throws Exception {
        Point2D point2D = new Point2D();
        for (int i = 0; i < 2; i++) {
            double p = in.nextDouble();
            point2D.setX(p, i);
        }
        return point2D;
    }

    static Point3D set3D(Scanner in) throws Exception {
        Point3D point3D = new Point3D();
        for (int i = 0; i < 3; i++) {
            double p = in.nextDouble();
            point3D.setX(p, i);
        }
        return point3D;
    }

    static Point2D[] create_segment() throws Exception {
        System.out.println("Введите точки.\nStart x:");
        Scanner start_x = new Scanner(System.in);
        double x_start = start_x.nextDouble();
        System.out.println("Start y:");
        Scanner start_y = new Scanner(System.in);
        double y_start = start_y.nextDouble();
        double[] start = new double[]{x_start, y_start};
        System.out.println("Finish x:");
        Scanner finish_x = new Scanner(System.in);
        double x_finish = finish_x.nextDouble();
        System.out.println("Finish y:");
        Scanner finish_y = new Scanner(System.in);
        double y_finish = finish_y.nextDouble();
        double[] finish = new double[]{x_finish, y_finish};
        Point2D point2D1 = new Point2D(start);
        Point2D point2D2 = new Point2D(finish);
        return new Point2D[] {point2D1, point2D2};
    }

    static Point2D[] create_polyline() throws Exception {
        System.out.println("Введите количество точек.");
        Scanner count_point = new Scanner(System.in);
        int point_count = count_point.nextInt();
        Point2D[] mass_of_point = new Point2D[point_count];
        for (int i = 0; i < point_count; i++) {
            Point2D point2D;
            System.out.println("Введите координаты " + (i + 1) + " точки в фоормате: x y");
            Scanner coord = new Scanner(System.in);
            point2D = set2D(coord);
            mass_of_point[i] = point2D;
        }
        return mass_of_point;
    }

    static Point2D[] create_NGon_detailed(int pnt_count) throws Exception {
        Point2D[] mss_of_point = new Point2D[pnt_count];
        for (int i = 0; i < pnt_count; i++) {
            Point2D pnt2d;
            System.out.println("Введите координаты " + (i + 1) + " точки в фоормате: x y");
            Scanner coord = new Scanner(System.in);
            pnt2d = set2D(coord);
            mss_of_point[i] = pnt2d;
        }
        return mss_of_point;
    }

    static void Intersection_of_shapes(List<IShape> figure, IShape shape, int i) throws Exception {
        Scanner c_fig;
        if (shape.cross(figure.get(i)))
            System.out.println("Фигуры пересекаются");
        else
            System.out.println("Фигуры не пересекаются");
        System.out.println("Введите тип движения фигуры:\n1 - поворот\n2 - сдвиг\n3 - симметрия");
        c_fig = new Scanner(System.in);
        int type_movement = c_fig.nextInt();
        switch (type_movement) {
            case 1 -> {
                System.out.println("Введите кол-во радианов, на которое хотите повернуть фигуру");
                c_fig = new Scanner(System.in);
                double degree = c_fig.nextDouble();
                if (figure.get(i).cross((IShape) shape.rot(degree)))
                    System.out.println("Фигуры пересекаются");
                else
                    System.out.println("Фигуры не пересекаются");
            }
            case 2 -> {
                System.out.println("Введите вектор, на который хотите сдвинуть фигуру в формате: х у");
                c_fig = new Scanner(System.in);
                Point2D vector = set2D(c_fig);
                if (figure.get(i).cross((IShape) shape.shift(vector)))
                    System.out.println("Фигуры пересекаются");
                else
                    System.out.println("Фигуры не пересекаются");
            }
            case 3 -> {
                System.out.println("Введите номер оси, относительно которой будет проводиться симметрия фигуры (0 - x, 1 - y)");
                c_fig = new Scanner(System.in);
                int sym = c_fig.nextInt();
                if (figure.get(i).cross((IShape) shape.symAxis(sym)))
                    System.out.println("Фигуры пересекаются");
                else
                    System.out.println("Фигуры не пересекаются");
            }
        }
    }

    public static void main(String[] args) throws Exception {
        double[] p2_1 = new double[]{0, 0};
        double[] p2_2 = new double[]{10, 0};
        double[] p2_3 = new double[]{10, 10};
        double[] p2_4 = new double[]{0, 10};
        double[] p2_5 = new double[]{5, 5};
        double[] p2_6 = new double[]{5, 15};
        double[] p2_7 = new double[]{15, 15};
        double[] p2_8 = new double[]{15, 5};
//
        Point2D point2_1 = new Point2D(p2_1);
        Point2D point2_2 = new Point2D(p2_2);
        Point2D point2_3 = new Point2D(p2_3);
        Point2D point2_4 = new Point2D(p2_4);
        Point2D point2_5 = new Point2D(p2_5);
        Point2D point2_6 = new Point2D(p2_6);
        Point2D point2_7 = new Point2D(p2_7);
        Point2D point2_8 = new Point2D(p2_8);

//        QGon qGon4 = new QGon(new Point2D[] {point2_1, point2_2, point2_3, point2_4});
//        QGon qGon5 = new QGon(new Point2D[] {point2_5, point2_6, point2_7, point2_8});
//        System.out.println("hello " + qGon4.cross(qGon5));
        Point2D vector1111 = new Point2D(new double[]{-1, -2});
//        NGon nGon5 = new NGon(new Point2D[]{point2_1, point2_2, point2_3, point2_4, point2_5, point2_6});
//        Circle cirs = new Circle(point2_1, 10);
//        Polyline pol = new Polyline(new Point2D[]{point2_1, point2_2, point2_3});
//        Segment seg = new Segment(point2_1, point2_2);
//        Segment seg2 = new Segment(point2_3, point2_4);
//        System.out.println(seg2.rot(1));
//        System.out.println(seg);
//        System.out.println(seg.symAxis(0));
//        System.out.println(pol);
//        System.out.println(pol.symAxis(0));
//        System.out.println(cirs);
//        System.out.println(cirs.symAxis(0));
//        System.out.println(nGon5);
//        System.out.println(nGon5.symAxis(3));

//        Rectangle r1 = new Rectangle(new Point2D[]{point2_1, point2_2, point2_3, point2_4});
//        Rectangle r2 = new Rectangle(new Point2D[]{point2_5, point2_6, point2_7, point2_8});
//        System.out.println(r1.cross(r2));

        double[] p3_1= new double[]{2, 2, 5};
        double[] p3_2= new double[]{2, 6, 5};
        double[] p3_3= new double[]{6, 6, 5};
        double[] p3_4= new double[]{6, 2, 5};
        double[] p3_5= new double[]{1, 1, 0};
        double[] p3_6= new double[]{1, 5, 0};
        double[] p3_7= new double[]{5, 5, 0};
        double[] p3_8= new double[]{5, 1, 0};

        Point3D point3_1 = new Point3D(p3_1);
        Point3D point3_2 = new Point3D(p3_2);
        Point3D point3_3 = new Point3D(p3_3);
        Point3D point3_4 = new Point3D(p3_4);
        Point3D point3_5 = new Point3D(p3_5);
        Point3D point3_6 = new Point3D(p3_6);
        Point3D point3_7 = new Point3D(p3_7);
        Point3D point3_8 = new Point3D(p3_8);

        TGon tGon = new TGon(new Point2D[]{point2_1, point2_3, point2_4});
        System.out.println(tGon);
        System.out.println(tGon.symAxis(1));

//
//        Pyramid pyramid1 = new Pyramid(point3_1, new Point3D[]{ point3_2, point3_3, point3_4, point3_5});
//        System.out.println(pyramid1);
//        Parallelepiped pr = new Parallelepiped(new Point3D[]{point3_1, point3_2, point3_3, point3_4, point3_5, point3_6, point3_7, point3_8});
//        System.out.println(pr.volume());





        List<IShape> figure = new ArrayList<>();
        System.out.println("Введите кол-во 2D фигур!");
        Scanner c_fig = new Scanner(System.in);
        int figure_count = c_fig.nextInt();
        for (int q = 0; q < figure_count; q++) {
            System.out.println("""
                    Введите тип фигуры:
                    1 - отрезок
                    2 - ломаная линия
                    3 - окружность
                    4 - Н-угольник
                    5 - треугольник
                    6 - четырехугольник
                    7 - прямоугольник
                    8 - трапеция
                    """);
            Scanner type_fig = new Scanner(System.in);
            int figure_type = type_fig.nextInt();
            switch (figure_type) {
                case 1 -> {
                    Point2D[] p2d = create_segment();
                    Segment segment = new Segment(p2d[0], p2d[1]);
                    figure.add(segment);
                }
                case 2 -> {
                    Polyline polyline = new Polyline(create_polyline());
                    figure.add(polyline);
                }
                case 3 -> {
                    System.out.println("Введите координаты центра окружности в формате: х у");
                    Scanner center = new Scanner(System.in);
                    Point2D circ_centre = new Point2D();
                    circ_centre = set2D(center);
                    System.out.println("Введите радиус окружности");
                    Scanner rad = new Scanner(System.in);
                    double radius = rad.nextDouble();
                    Circle circle = new Circle(circ_centre, radius);
                    figure.add(circle);
                }
                case 4 -> {
                    System.out.println("Введите количество точек.");
                    Scanner cnt_point = new Scanner(System.in);
                    int pnt_count = cnt_point.nextInt();
                    NGon nGon1 = new NGon(create_NGon_detailed(pnt_count));
                    figure.add(nGon1);
                }
                case 5 -> {
                    TGon triangle = new TGon(create_NGon_detailed(3));
                    figure.add(triangle);
                }
                case 6 -> {
                    QGon qGon = new QGon(create_NGon_detailed(4));
                    figure.add(qGon);
                }
                case 7 -> {
                    Rectangle rectangle = new Rectangle(create_NGon_detailed(4));
                    figure.add(rectangle);
                }
                case 8 -> {
                    Trapeze trapeze = new Trapeze(create_NGon_detailed(4));
                    figure.add(trapeze);
                }
            }
        }
        double sum_square = 0;
        double sum_len = 0;
        double average_square = 0;
        for (int i = 0; i < figure_count; i++) {
            sum_square += figure.get(i).square();
            average_square += figure.get(i).square();
            sum_len += figure.get(i).length();
        }
        if (figure_count != 0)
            average_square /= figure_count;
        System.out.println("Суммарная площадь всех фигур: " + sum_square + "\nСуммарная длинна фигур: " + sum_len +
                            "\nСредняя площадь по всем фигурам: " + average_square);
        for (int i = 0; i < figure_count; i++) {
            if (figure.get(i) instanceof Segment)
            {
                Point2D[] point2DS = create_segment();
                Segment segment = new Segment(point2DS[0], point2DS[1]);
                Intersection_of_shapes(figure, segment, i);
            }
            else if (figure.get(i) instanceof Polyline)
            {
                Point2D[] point2DS = create_polyline();
                Polyline polyline = new Polyline(point2DS);
                Intersection_of_shapes(figure, polyline, i);
            }
            else if (figure.get(i) instanceof Circle)
            {
                System.out.println("Введите координаты центра окружности в формате: х у");
                Scanner center = new Scanner(System.in);
                Point2D circ_centre = new Point2D();
                circ_centre = set2D(center);
                System.out.println("Введите радиус окружности");
                Scanner rad = new Scanner(System.in);
                double radius = rad.nextDouble();
                Circle circle = new Circle(circ_centre, radius);
                Intersection_of_shapes(figure, circle, i);
            }
            else if (figure.get(i) instanceof TGon)
            {
                TGon triangle = new TGon(create_NGon_detailed(3));
                Intersection_of_shapes(figure, triangle, i);
            }
            else if (figure.get(i) instanceof QGon)
            {
                QGon qGon = new QGon(create_NGon_detailed(4));
                Intersection_of_shapes(figure, qGon, i);
            }
            else if (figure.get(i) instanceof Rectangle)
            {
                Rectangle rectangle = new Rectangle(create_NGon_detailed(4));
                Intersection_of_shapes(figure, rectangle, i);
            }
            else if (figure.get(i) instanceof Trapeze){
                Trapeze trapeze = new Trapeze(create_NGon_detailed(4));
                Intersection_of_shapes(figure, trapeze, i);
            }
            else if (figure.get(i) instanceof NGon)
            {
                System.out.println("Введите количество точек.");
                Scanner cnt_point = new Scanner(System.in);
                int pnt_count = cnt_point.nextInt();
                NGon nGon1 = new NGon(create_NGon_detailed(pnt_count));
                Intersection_of_shapes(figure, nGon1, i);
            }
        }
        List<IFigure> figures3D = new ArrayList<>();
        System.out.println("Введите кол-во 3D фигур!");
        c_fig = new Scanner(System.in);
        figure_count = c_fig.nextInt();
        for (int i = 0; i < figure_count; i++) {
            System.out.println("""
                    Введите тип фигуры:
                    1 - пирамида
                    2 - шар
                    3 - призма
                    4 - параллепипед
                    """);
            Scanner type_fig = new Scanner(System.in);
            int figure_type = type_fig.nextInt();
            switch (figure_type){
                case 1:
                    System.out.println("Введите кол-во точек основания пирамиды");
                    c_fig = new Scanner(System.in);
                    int count = c_fig.nextInt();
                    System.out.println("Введите координаты вершины пирамиды в формате: х у z");
                    c_fig = new Scanner(System.in);
                    Point3D top = set3D(c_fig);
                    Point3D[] mass_of_point = new Point3D[count];
                    for (int j = 0; j < count; j++) {
                        System.out.println("Введите координаты " + (j + 1) + " точки");
                        c_fig = new Scanner(System.in);
                        Point3D point3D = set3D(c_fig);
                        mass_of_point[j] = point3D;
                    }
                    Pyramid pyramid = new Pyramid(top, mass_of_point);
                    figures3D.add(pyramid);
                    break;
                case 2:
                    System.out.println("Введите координаты центра шара в формате: х у z");
                    c_fig = new Scanner(System.in);
                    top = set3D(c_fig);
                    System.out.println("Введите радиус");
                    c_fig = new Scanner(System.in);
                    int radius = c_fig.nextInt();
                    Ball ball = new Ball(top, radius);
                    figures3D.add(ball);
                    break;
                case 3:
                    System.out.println("Введите кол-во точек основания призмы");
                    c_fig = new Scanner(System.in);
                    count = c_fig.nextInt();
                    mass_of_point = new Point3D[count];
                    Point3D[] mass_of_point2 = new Point3D[count];
                    for (int j = 0; j < count; j++) {
                        System.out.println("Введите координаты " + (j + 1) + " точки нижнего основания");
                        c_fig = new Scanner(System.in);
                        Point3D point3D = set3D(c_fig);
                        mass_of_point[j] = point3D;
                    }
                    for (int j = 0; j < count; j++) {
                        System.out.println("Введите координаты " + (j + 1) + " точки верхнего основания");
                        c_fig = new Scanner(System.in);
                        Point3D point3D = set3D(c_fig);
                        mass_of_point2[j] = point3D;
                    }
                    Prism prism = new Prism(mass_of_point, mass_of_point2);
                    figures3D.add(prism);
                    break;
                case 4:
                    mass_of_point = new Point3D[8];
                    for (int j = 0; j < 8; j++) {
                        System.out.println("Введите координаты " + (j + 1) + " точки в формате: х у z");
                        c_fig = new Scanner(System.in);
                        Point3D point3D = set3D(c_fig);
                        mass_of_point[j] = point3D;
                    }
                    Parallelepiped parallelepiped = new Parallelepiped(mass_of_point);
                    figures3D.add(parallelepiped);
                    break;
            }
        }
        double sum_volume = 0;
        sum_square = 0;
        double average_volume = 0;
        for (int i = 0; i < figure_count; i++) {
            sum_volume += figures3D.get(i).volume();
            average_volume += figures3D.get(i).volume();
            sum_square += figures3D.get(i).square();
        }
        if (figure_count != 0)
            average_volume /= figure_count;
        System.out.println("Суммарный объем всех фигур: " + sum_volume + "\nСуммарная площадь поверхности всех фигур " + sum_square +
                "\nСредний объем по всем фигурам " + average_volume);
    }
}