package org.labWeb;

public class PointProcessor {
    public String processPoint(String x_, String y_, String r_){
        try {
            int x = Integer.parseInt(x_);
            float y = Float.parseFloat(y_);
            float r = Float.parseFloat(r_);
            boolean firstFig=(y<=0 && y>=-r) && (x<=0 && x>=-r/2);
            boolean secondFig=((x*x+y*y)<=(r*r/4)) && (x<=0);
            boolean thirdFig=(y>=((x-r/2)*2)) && (x>=0) && (y<=0);
            return (firstFig || secondFig ||thirdFig)+"";
        }catch (Exception e){
            return "failed to parse";
        }


    }
}
