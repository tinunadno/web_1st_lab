package org.labWeb;

public class SingleUserStory {
    private StringBuilder userXStory;
    private StringBuilder userYStory;
    private StringBuilder userRStory;

    private final String xLabel="X:";
    private final String yLabel="Y:";
    private final String rLabel="R:";
    private final String lineSeparator="\n";

    public SingleUserStory(String X, String Y, String R) {
        userXStory=new StringBuilder(X);
        userYStory=new StringBuilder(Y);
        userRStory=new StringBuilder(R);
    }

    public void addData(String X, String Y, String R){
        userXStory.append(X);
        userYStory.append(Y);
        userRStory.append(R);
    }

    public String getData(){
        StringBuilder ret=new StringBuilder();
        ret.append(xLabel);
        ret.append(userXStory);
        ret.append(lineSeparator);
        ret.append(yLabel);
        ret.append(userYStory);
        ret.append(lineSeparator);
        ret.append(rLabel);
        ret.append(userRStory);
        ret.append(lineSeparator);
        return ret.toString();
    }
}
