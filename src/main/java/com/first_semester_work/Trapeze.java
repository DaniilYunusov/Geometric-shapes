package com.first_semester_work;

public class Trapeze extends QGon{
    public Trapeze(Point2D[] p) throws Exception {
        super(p);
//        Segment footing1 = new Segment(p[0], p[1]);
//        Segment footing2 = new Segment(p[2], p[3]);
//        Segment footing3 = new Segment(p[1], p[2]);
//        Segment footing4 = new Segment(p[3], p[0]);
//        if ((((footing1.finish.getX(0) - footing1.start.getX(0) == 0 &&
//                footing2.finish.getX(0) - footing2.start.getX(0) == 0) ||
//                (footing1.finish.getX(1) - footing1.start.getX(1) == 0 &&
//                        footing2.finish.getX(1) - footing2.start.getX(1) == 0))
//                || (footing1.start.getX(1) - footing1.finish.getX(1) / footing1.start.getX(0) - footing1.finish.getX(0) ==
//                footing2.start.getX(1) - footing2.finish.getX(1) / footing2.start.getX(0) - footing2.finish.getX(0))) &&
//            (((footing3.finish.getX(0) - footing3.start.getX(0) == 0 &&
//                footing4.finish.getX(0) - footing4.start.getX(0) == 0) ||
//                (footing3.finish.getX(1) - footing3.start.getX(1) == 0 &&
//                        footing4.finish.getX(1) - footing4.start.getX(1) == 0))
//                || (footing3.start.getX(1) - footing3.finish.getX(1) / footing3.start.getX(0) - footing3.finish.getX(0) ==
//                footing4.start.getX(1) - footing4.finish.getX(1) / footing4.start.getX(0) - footing4.finish.getX(0))))
//        {
//            throw new Exception("This is not Trapeze!!!");
//        }
//        else if ((!(((footing1.finish.getX(0) - footing1.start.getX(0) == 0 &&
//                footing2.finish.getX(0) - footing2.start.getX(0) == 0) ||
//                (footing1.finish.getX(1) - footing1.start.getX(1) == 0 &&
//                        footing2.finish.getX(1) - footing2.start.getX(1) == 0))
//                || (footing1.start.getX(1) - footing1.finish.getX(1) / footing1.start.getX(0) - footing1.finish.getX(0) ==
//                footing2.start.getX(1) - footing2.finish.getX(1) / footing2.start.getX(0) - footing2.finish.getX(0))) &&
//                !(((footing3.finish.getX(0) - footing3.start.getX(0) == 0 &&
//                        footing4.finish.getX(0) - footing4.start.getX(0) == 0) ||
//                        (footing3.finish.getX(1) - footing3.start.getX(1) == 0 &&
//                                footing4.finish.getX(1) - footing4.start.getX(1) == 0))
//                        || (footing3.start.getX(1) - footing3.finish.getX(1) / footing3.start.getX(0) - footing3.finish.getX(0) ==
//                        footing4.start.getX(1) - footing4.finish.getX(1) / footing4.start.getX(0) - footing4.finish.getX(0)))))
//        {
//            throw new Exception("This is not Trapeze!!!");
//        }
    }
    public double square() throws Exception {
        NGon nGon = new NGon(this.getP());
        return nGon.square();
    }
//        Segment footing1 = new Segment(p[0], p[1]);
//        Segment footing2 = new Segment(p[2], p[3]);
//        if (((footing1.finish.getX(0) - footing1.start.getX(0) == 0 &&
//                footing2.finish.getX(0) - footing2.start.getX(0) == 0) ||
//                (footing1.finish.getX(1) - footing1.start.getX(1) == 0 &&
//                        footing2.finish.getX(1) - footing2.start.getX(1) == 0))
//        || (footing1.start.getX(1) - footing1.finish.getX(1) / footing1.start.getX(0) - footing1.finish.getX(0) ==
//                footing2.start.getX(1) - footing2.finish.getX(1) / footing2.start.getX(0) - footing2.finish.getX(0)))
//        {
//            Segment side1 = new Segment(p[1], p[2]);
//            Segment side2 = new Segment(p[3], p[0]);
//            if (footing1.length() > footing2.length()){
//                double h = Math.sqrt(Math.pow(side1.length(), 2) - ((Math.pow(footing1.length() - footing2.length(), 2) +
//                        Math.pow(side1.length(), 2) - Math.pow(side2.length(), 2)) / (2 * (footing1.length()) - footing2.length())));
//                return  ((footing1.length() + footing2.length()) / 2) * h;
//            }
//            else {
//                double h = Math.sqrt(Math.pow(side1.length(), 2) - ((Math.pow(footing2.length() - footing1.length(), 2) +
//                        Math.pow(side1.length(), 2) - Math.pow(side2.length(), 2)) / (2 * (footing2.length()) - footing1.length())));
//                return ((footing1.length() + footing2.length()) / 2) * h;
//            }
//        }
//        else {
//            footing1 = new Segment(p[1], p[2]);
//            footing2 = new Segment(p[3], p[0]);
//            Segment side1 = new Segment(p[0], p[1]);
//            Segment side2 = new Segment(p[2], p[3]);
//            if (footing1.length() > footing2.length()){
//                double h = Math.sqrt(Math.pow(side1.length(), 2) - ((Math.pow(footing1.length() - footing2.length(), 2) +
//                        Math.pow(side1.length(), 2) - Math.pow(side2.length(), 2)) / (2 * (footing1.length()) - footing2.length())));
//                return  ((footing1.length() + footing2.length()) / 2) * h;
//            }
//            else {
//                double h = Math.sqrt(Math.pow(side1.length(), 2) - ((Math.pow(footing2.length() - footing1.length(), 2) +
//                        Math.pow(side1.length(), 2) - Math.pow(side2.length(), 2)) / (2 * (footing2.length()) - footing1.length())));
//                return ((footing1.length() + footing2.length()) / 2) * h;
//            }
//        }
//    }

    public String toString(){
        String string = "Трапеция: [";
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
