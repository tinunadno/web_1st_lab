package org.labWeb;

import java.util.HashMap;

public class UserStoryStorage {
    private HashMap<String, SingleUserStory> usersStoryHashMap;
    private TokenGenerator tokenGenerator;

    public UserStoryStorage(){
        usersStoryHashMap=new HashMap<>();
        tokenGenerator=new TokenGenerator();
    }

    public void append(String token, String x, String y, String r){
        if(usersStoryHashMap.containsKey(token)){
            usersStoryHashMap.get(token).addData(x, y, r);
        }else{
            throw new NullPointerException();
        }
    }

    public String addUser(String x, String y, String r){
        SingleUserStory userStory=new SingleUserStory(x, y, r);
        String token=tokenGenerator.getNewToken();
        usersStoryHashMap.put(token, userStory);
        return token;
    }

    public String getUserStory(String token){
        if(usersStoryHashMap.containsKey(token)){
            return usersStoryHashMap.get(token).toString();
        }else{
            throw new NullPointerException();
        }
    }
}
