package org.labWeb;

public class SingleUserStory {

    private String xStory;
    private String yStory;
    private String rStory;
    private String requestTimeStory;
    private String serverStartStory;
    private String responseStory;
    private PointProcessor pointProcessor;

    public SingleUserStory(String X, String Y, String R) {
        pointProcessor = new PointProcessor();
        xStory = "X:" + X + ",";
        yStory = "Y:" + Y + ",";
        rStory = "R:" + R + ",";
        requestTimeStory = "request_time:" + Logger.getCurrentDate()+",";
        serverStartStory = "server_start:" + Main.serverStartTime+",";
        responseStory = "Response:" + pointProcessor.processPoint(X, Y, R) + ",";
    }

    public void addData(String X, String Y, String R) {
        xStory += X + ",";
        yStory += Y + ",";
        rStory += R + ",";
        requestTimeStory += Logger.getCurrentDate()+",";
        serverStartStory += Main.serverStartTime+",";
        responseStory += pointProcessor.processPoint(X, Y, R) + ",";
    }

    public String getData() {
        String ret = xStory.substring(0, xStory.length() - 1) +
                "\n" + yStory.substring(0, yStory.length() - 1) +
                "\n" + rStory.substring(0, rStory.length() - 1) +
                "\n" + requestTimeStory.substring(0, requestTimeStory.length() - 1) +
                "\n" + serverStartStory.substring(0, serverStartStory.length() - 1) +
                "\n" + responseStory.substring(0, responseStory.length() - 1);
        return ret;
    }
}
