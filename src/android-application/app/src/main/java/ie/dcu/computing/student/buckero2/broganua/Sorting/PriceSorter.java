package ie.dcu.computing.student.buckero2.broganua;

import java.util.Comparator;

public class PriceSorter implements Comparator<Products> {

    public int compare(Products one, Products another){
        int returnVal = 0;

        if(one.getPrice() < another.getPrice()){
            returnVal =  -1;
        }else if(one.getPrice() > another.getPrice()){
            returnVal =  1;
        }else if(one.getPrice() == another.getPrice()){
            returnVal =  0;
        }
        return returnVal;
    }
}