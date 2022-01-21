package bgv.fit.bstu.eday.Utils;

public class APIs {
    public static final String IP = "10.1.30.34";
    public static final String URL_001="http://"+IP+":8080/users/";
    public static final String URL_002="http://"+IP+":8080/tasks/";

    public static UserService getUserService(){
        return  Client.getClient(URL_001).create(UserService.class);
    }

    public static TaskService getTaskService(){
        return  Client.getClient(URL_002).create(TaskService.class);
    }
}
