package org.labWeb;

import java.util.HashMap;

public class UserStoryStorage {
    private final HashMap<String, SingleUserStory> usersStoryHashMap;
    private final TokenGenerator tokenGenerator;

    public UserStoryStorage(){
        usersStoryHashMap=new HashMap<>();
        tokenGenerator=new TokenGenerator();
    }

    public void append(String x, String y, String r, String token) throws NullPointerException{
        usersStoryHashMap.get(token).addData(x, y, r);
    }

    public String addUser(String x, String y, String r){
        SingleUserStory userStory=new SingleUserStory(x, y, r);
        String token=tokenGenerator.getNewToken();
        usersStoryHashMap.put(token, userStory);
        return token;
    }

    public String getUserStory(String token) throws NullPointerException{
        return usersStoryHashMap.get(token).getData();
    }
    public void removeUser(String token){
        usersStoryHashMap.remove(token);
    }
}
