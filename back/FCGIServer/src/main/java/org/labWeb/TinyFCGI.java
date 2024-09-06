package org.labWeb;

import com.fastcgi.FCGIInterface;

public class TinyFCGI {
    private static UserStoryStorage userStoryStorage=new UserStoryStorage();
    private static PointProcessor pointProcessor=new PointProcessor();
    public static void main(String[] args) {
        int count=0;
        FCGIInterface fcgiInterface=new FCGIInterface();
        while(fcgiInterface.FCGIaccept() >= 0) {
            //System.getProperty("prop_name"); get stdIn data
            //System.out.println("some stuff"); put http response to stdout

            //TODO create a head

            String userToken=System.getProperty("token");
            String x=System.getProperty("X");
            String y=System.getProperty("y");
            String r=System.getProperty("R");
            if(userToken.equals("NEW")){
                userToken=userStoryStorage.addUser(x, y, r);
                System.out.print(userStoryStorage.getUserStory(userToken));
                System.out.println(pointProcessor.processPoint(x, y, r));
            }else{
                try{
                    userStoryStorage.append(userToken, x, y, r);
                    System.out.print(userStoryStorage.getUserStory(userToken));
                    System.out.println(pointProcessor.processPoint(x, y, r));
                }catch (NullPointerException e){
                    //throwing 400 code
                }
            }
        }
    }
}