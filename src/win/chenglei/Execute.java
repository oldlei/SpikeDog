package win.chenglei;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;

public class Execute {
    private String name;
    public Execute(){

    }
    public Execute(String name){
        this.name=name;
    }
    public void setName(String name){
        this.name = name;
    }
    public void executeByPost(HashMap<String,String> hmp){
        if(null !=name){
            try {
                System.out.println("exe");
                Class<?> aClass = Class.forName(name);
                Method doPost = aClass.getMethod("doPost", new Class[]{HashMap.class});
                doPost.invoke(aClass.newInstance(),new Object[]{hmp});


            } catch (ClassNotFoundException e) {
                System.out.println("类不存在");
            } catch (NoSuchMethodException e) {
                System.out.println("方法不存在");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }
    public void executeByGet(HashMap<String,String> hmp){
        if(null !=name){
            try {
                Class<?> aClass = Class.forName(name);
                Method doPost = aClass.getMethod("doGet", new Class[]{HashMap.class});
                doPost.invoke(aClass.newInstance(),new Object[]{hmp});


            } catch (ClassNotFoundException e) {
                System.out.println("类不存在");
            } catch (NoSuchMethodException e) {
                System.out.println("方法不存在");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }

    }
    public void execute(HashMap<String,String> hmp,String methon){
        if("GET".equals(methon)){
            executeByGet(hmp);
        }else{
            executeByPost(hmp);
        }
    }
}
