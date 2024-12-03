package com.lab10;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Method;

@Target (ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@interface CanSendMessage{}

@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface RequiresPermission{
    String level();
}

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@interface UserPermission{
    String level();
}

abstract class User{
    private String username;
    public User(String username){
        this.username=username;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    @CanSendMessage
    @RequiresPermission(level = "admin")
    public void sendMessage() throws NoSuchMethodException{
        Class<?> userClass=this.getClass();
            if(userClass.isAnnotationPresent(UserPermission.class)){
                UserPermission userPermission=userClass.getAnnotation(UserPermission.class);
                String userLevel=userPermission.level();
                Method method=userClass.getMethod("sendMessage");
                RequiresPermission requiresPermission=method.getAnnotation(RequiresPermission.class);
                String requiredLevel=requiresPermission.level();
                if (requiredLevel.equals(userLevel)){
                    System.out.println(this.username+" sent a message successfully!");
                } 
                else{
                    System.out.println(this.username+" is restricted from sending messages.");
                }
            } 
            else{
                System.out.println("User permissions are not defined for "+this.username);
            }
    }
}
@UserPermission(level="regular")
class RegularUser extends User{
    public RegularUser(String username){
        super(username);
    }
}
@UserPermission(level="admin")
class AdminUser extends User{
    public AdminUser(String username){
        super(username);
    }
}
class Task1 {
    public static void main(String[] args) throws NoSuchMethodException{
        User regularUser=new RegularUser("HarisKojiNijeAdmin");
        User adminUser=new AdminUser("HarisKojiJeAdmin");
        regularUser.sendMessage();
        adminUser.sendMessage();
    }
}