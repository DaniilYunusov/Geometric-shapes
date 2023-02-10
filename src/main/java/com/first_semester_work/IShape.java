package com.first_semester_work;

public interface IShape extends IMoveable{
    double square() throws Exception;
    double length();
    boolean cross(IShape i);
}
