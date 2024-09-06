package org.labWeb;

public class PointProcessor {
    public String processPoint(String xString, String yString, String rString){
        int x=Integer.parseInt(xString);
        int y=Integer.parseInt(yString);
        int r=Integer.parseInt(rString);

        return "Response:" + ((x*x+y*y)<((r*r)/2) && x<0 && y>0 || y>x-r && x>0 && y<0 || -r/2<x && x<0 && y<0 && y>-r);
    }
}
