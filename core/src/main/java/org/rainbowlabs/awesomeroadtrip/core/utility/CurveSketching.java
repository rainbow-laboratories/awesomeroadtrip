package org.rainbowlabs.awesomeroadtrip.core.utility;

import java.util.ArrayList;
import java.util.Random;

public class CurveSketching {


    private double a,b,c;
    private int functionalLevel;

    public CurveSketching(double a, double b) {
        this.a = a;
        this.b = b;
        this.c = 0;
        this.functionalLevel = 1;
    }

    public CurveSketching(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
        this.functionalLevel = 2;
    }

    public CurveSketching(int functionalLevel, int max, int min) {
        /**
         *  For creating random functions
         *  first degree of function then
         */
        Random rand = new Random();

        switch (functionalLevel){
            case 1:
                this.a = rand.nextInt(max +1 -min)+min;
                this.b = rand.nextInt(max +1 -min)+min;
                if(a==0) a++;
                break;

            case 2:
                this.a = rand.nextInt(max +1 -min)+min;
                this.b = rand.nextInt(max +1 -min)+min;
                this.c = rand.nextInt(max +1 -min)+min;
                if(a==0) a++;
                break;

            default: throw new IllegalArgumentException("function level unexpected");
        }
        this.functionalLevel = functionalLevel;
    }

    public CurveSketching (double x1, double y1,double x2, double y2,double x3, double y3){
        this.functionalLevel = 2;
        this.a = (x1*(y2-y3)+x2*(y3-y1)+x3*(y1-y2))/((x1-x2)*(x1-x3)*(x3-x2));
        this.b = (Math.pow(x1,2)*(y2-y3)+Math.pow(x2,2)*(y3-y1)+Math.pow(x3,2)*(y1-y2))/((x1-x2)*(x1-x3)*(x2-x3));
        this.c = (Math.pow(x1,2)*(x2*y3-x3*y2)+x1*(Math.pow(x3,2)*y2-Math.pow(x2,2)*y3)+x2*x3*y1*(x2-x3))/((x1-x2)*(x1-x3)*(x2-x3));
    }

    public ArrayList<Double> extrema (){
        /**
         * First entry is the number of extrema
         * even X-coordinate of extrema
         * uneven Y-Coordinate of extrema
         */
        ArrayList<Double> extremaList = new ArrayList<Double>();

        switch (functionalLevel){
            case 1:
                extremaList.add(0d);
                break;
            case 2:
                extremaList.add(1d);
                extremaList.add((-1*b)/(2*a));
                extremaList.add((a*Math.pow(extremaList.get(1),2))+((b*(extremaList.get(1))+c)));
                break;
            default: throw new IllegalArgumentException("function level unexpected");

        }
        return extremaList;
    }

    public ArrayList<Double> lookUpTable (double startX, double endX, double step){
        ArrayList<Double> lookUpTable = new ArrayList<Double>();

        switch (functionalLevel){
            case 1:
                for(;startX<endX;startX+=step){
                    lookUpTable.add(a*startX+b);
                }
                break;
            case 2:
                for(;startX<endX;startX+=step){
                    lookUpTable.add(a*Math.pow(startX,2)+b*startX+c);
                }
                break;
            default: throw new IllegalArgumentException("function level unexpected");

        }
        return lookUpTable;
    }

    public ArrayList<Double> root (){
        /**
         * First Array entry is the number of roots
         * rest X-coordinate of root
         */
        ArrayList<Double> rootList = new ArrayList<Double>();
        switch (functionalLevel){
            case 1:
                if((a*(-b/a)+b)==0){
                    rootList.add(1d);
                    rootList.add(-b/a);
                }else  rootList.add(0d);

                break;
            case 2:
                rootList.add(0d);
                ArrayList<Double> onRootExtrema = extrema();
                if(onRootExtrema.get(2)==0d){
                    rootList.add(onRootExtrema.get(1));
                    rootList.add(0d);
                }else
                if((2d*a)!=0&&(Math.pow(((-b/a)/2),2) - (c/a))>=0d){
                    rootList.add(((-b/a)/2)+Math.sqrt(Math.pow(((b/a)/2),2) - (c/a)));
                    rootList.set(0,rootList.get(0)+1d);
                    rootList.add(((-b/a)/2)-Math.sqrt(Math.pow(((b/a)/2),2) - (c/a)));
                    rootList.set(0,rootList.get(0)+1d);
                }
                break;
            default: throw new IllegalArgumentException("function level unexpected");

        }

        return rootList;
    }

    public ArrayList<Double> pointOfIntersection (CurveSketching curveToIntersect){
        /**
         * only works for functions of same power
         * First entry is the number of points of intersection
         * even = x-coordinate
         * uneven = y-coordinate
         */
        ArrayList<Double> intersectionPoint = new ArrayList<Double>();

        switch (functionalLevel){
            case 1:
                intersectionPoint.add(1d);
                intersectionPoint.add((b-curveToIntersect.getB())/(curveToIntersect.getA()-a));
                intersectionPoint.add(a*intersectionPoint.get(1)+b);
                break;
            case 2:
                intersectionPoint.add(2d);
                if (Math.pow((((b-curveToIntersect.getB())/(a-curveToIntersect.getA()))/2),2)-((c-curveToIntersect.getC())/(a-curveToIntersect.getA()))>=0d){
                    double pqFormel =Math.sqrt(Math.pow((((b-curveToIntersect.getB())/(a-curveToIntersect.getA()))/2),2)-((c-curveToIntersect.getC())/(a-curveToIntersect.getA())));
                    double x1 =(b-curveToIntersect.getB())/(a-curveToIntersect.getA())*-0.5d+pqFormel;
                    double x2 =((b-curveToIntersect.getB())/(a-curveToIntersect.getA()))*-0.5d-pqFormel;
                    intersectionPoint.add(x1);
                    intersectionPoint.add(a*Math.pow(x1,2)+b*x1+c);
                    intersectionPoint.add(x2);
                    intersectionPoint.add(a*Math.pow(x2,2)+b*x2+c);
                }

                break;
            default: throw new IllegalArgumentException("function level unexpected");
        }

        return  intersectionPoint;
    }

    public double getA() {
        return a;
    }

    public void setA(double a) {
        this.a = a;
    }

    public double getB() {
        return b;
    }

    public void setB(double b) {
        this.b = b;
    }

    public double getC() {
        return c;
    }

    public void setC(double c) {
        this.c = c;
    }

    public int getFunctionalLevel() {
        return functionalLevel;
    }

    public void setFunctionalLevel(int functionalLevel) {
        this.functionalLevel = functionalLevel;
    }

    @Override
    public String toString (){
        String curve;
        switch (functionalLevel){

            case 1:
                curve= a+"x";
                if (b>0)curve+="+"+b;
                if (b<0)curve+=""+b;
                break;
            case 2:
                curve= a+"x^2";
                if (b>0) curve+="+"+b+"x";
                if (b<0) curve+=b+"x";
                if (c>0) curve+="+"+c;
                if (c<0) curve+=""+c;
                break;
            default:
                throw new IllegalArgumentException("function level unexpected");

        }
        return curve;
    }

}

