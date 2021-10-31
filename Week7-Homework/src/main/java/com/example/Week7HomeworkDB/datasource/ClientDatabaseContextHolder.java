package com.example.Week7HomeworkDB.datasource;

public class ClientDatabaseContextHolder {

    private static ThreadLocal<ClientDatabase> context = new ThreadLocal<>();

    public static void set(ClientDatabase clientDatabase){
        context.set(clientDatabase);
    }

    public static ClientDatabase getClientDatabase(){
        return context.get();
    }

    public static void clear(){
        context.remove();
    }
}
