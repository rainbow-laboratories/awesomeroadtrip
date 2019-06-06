package org.rainbowlabs.awesomeroadtrip.core.math;

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

    public CurveSketching(int functionalLevel, int bound) {
        /**
         *  For creating random functions
         *  first degree of function then
         */
        Random rand = new Random();
        switch (functionalLevel){
            case 1:
                this.a = rand.nextInt(bound);
                this.b = rand.nextInt(bound);
                if(a==0) a++;
                break;

            case 2:
                this.a = rand.nextInt(bound);
                this.b = rand.nextInt(bound);
                this.c = rand.nextInt(bound);
                if(a==0) a++;
                break;

            default: throw new IllegalArgumentException("function level unexpected");
        }
        this.functionalLevel = functionalLevel;
    }

    public ArrayList<Double> extrema (){
        /**
         * First Array entry is the number of extrema
         */
        ArrayList<Double> extremaList = new ArrayList<Double>();

        switch (functionalLevel){
            case 1:
                extremaList.add(0d);
                break;
            case 2:
                if(a*(-b/(2*a)+b)==0){
                    extremaList.add(1d);
                    extremaList.add(-b/(2*a));
                }else  extremaList.add(0d);
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
                if((((-b+(Math.sqrt(Math.pow(b,2) - 4d*a*c)))/2d*a)+"").equals("NaN")){
                    rootList.add((-b+(Math.sqrt(Math.pow(b,2) - 4d*a*c)))/2d*a);
                    rootList.set(0,rootList.get(0)+1d);
                }
                if((((-b-(Math.sqrt(Math.pow(b,2) - 4d*a*c)))/2d*a)+"").equals("NaN")){
                    rootList.add((-b-(Math.sqrt(Math.pow(b,2) - 4d*a*c)))/2d*a);
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
         * uneven = x-coordinate
         * even = y-coordinate
         */
        ArrayList<Double> intersectionPoint = new ArrayList<Double>();

        switch (functionalLevel){
            case 1:
                //   intersectionPoint.add(()/())


                break;
            case 2:
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