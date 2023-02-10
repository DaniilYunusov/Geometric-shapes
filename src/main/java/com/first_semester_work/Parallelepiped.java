package com.first_semester_work;

public class Parallelepiped extends Prism{
    Parallelepiped(Point3D[] p) throws Exception {
        super(p);
        if (p.length != 8){
            throw new Exception("Это не параллелепипед.");
        }
        Point3D vector1 = new Point3D();
        vector1.setX(new double[] {p[4].getX(0) - p[0].getX(0),
                                   p[4].getX(1) - p[0].getX(1),
                                   p[4].getX(2) - p[0].getX(2)});
        for (int i = 1; i < n / 2 - 1; i++) {
            Point3D vector2 = new Point3D();
            vector2.setX(new double[] {p[i+4].getX(0) - p[i].getX(0),
                                       p[i+4].getX(1) - p[i].getX(1),
                                       p[i+4].getX(2) - p[i].getX(2)});
            if (vector1.getX(0) != vector2.getX(0) || vector1.getX(1) != vector2.getX(1)
                    || vector1.getX(2) != vector2.getX(2))
                throw new Exception("This is not Parallelepiped!!!");
            vector1 = vector2;
        }
    }
//    public double volume() throws Exception {
//        double A;
//        double B;
//        double C;
//        double D;
//        Point3D vector1 = new Point3D();
//        Point3D vector2 = new Point3D();
//        Point3D vector3 = new Point3D();
//        vector1.setX(new double[] {p1[2].getX(0) - p1[1].getX(0),
//                                   p1[2].getX(1) - p1[1].getX(1),
//                                   p1[2].getX(2) - p1[1].getX(2)});
//        vector2.setX(new double[] {p1[0].getX(0) - p1[1].getX(0),
//                                   p1[0].getX(1) - p1[1].getX(1),
//                                   p1[0].getX(2) - p1[1].getX(2)});
////        vector3.setX(new double[] {p1[0].getX(0) - p2[0].getX(0),
////                                   p1[0].getX(1) - p2[0].getX(1),
////                                   p1[0].getX(2) - p2[0].getX(2)});
//        A = vector1.abs();
//        B = vector2.abs();
////        C
//        Point3D n = vector1.cross_prod(vector2);
//        D = -1 * (n.getX(0) * p1[0].getX(0) + n.getX(1) * p1[0].getX(1) + n.getX(2) * p1[0].getX(2));
//        C = Math.abs(n.getX(0) * p2[0].getX(0) + n.getX(1) * p2[0].getX(1) + n.getX(2) * p2[0].getX(2) + D) / n.abs();
////        System.out.println(A + " " + B + " " + C);
//        return A * B * C;
//    }
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
