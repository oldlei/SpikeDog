package win.chenglei;

import java.io.*;
import java.net.*;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.HashMap;

public class Test {
    public void jieshou(){
        ServerSocket ss=null;
        String html="";
        //简历socket后建新线程
        //请求头解析
        //调xml
        //反射调方法
        //页面返回


        try {
            ss = new ServerSocket(9090);
            while(true){
                Socket accept = ss.accept();
                //开一个线程跑接受，再开一个线程跑输出
                InputStream inputStream = accept.getInputStream();
                html = chuli(inputStream,accept);

                OutputStream outputStream = accept.getOutputStream();

                String xiangyingtou ="HTTP/1.1 200 OK\r\n" +
                        "Date: "+new Date().toString()+"\r\n" +
                        "Content-Type: text/html;charset=UTF-8\r\n" +
                        "Content-Length: "+html.getBytes("UTF-8").length+"\r\n" +
                        "\r\n" +
                        html+"/r/n";
            outputStream.write(xiangyingtou.getBytes());
            outputStream.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            if(null == ss){
                try {
                    ss.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        new Test().jieshou();
    }
    public String chuli(InputStream inputStream,Socket accept){
        String html = "";
        try{
            byte[] b = new byte[361];
            int i;
            StringBuilder strb= new StringBuilder();
            int aa=0;
            while((i=inputStream.read(b))!=-1){
                strb.append(new String(b,0,i));
                aa++;
                if(i <= 361){
                    accept.shutdownInput();
                    break;

                }
            }
            RequestHeader requestHeader = new RequestHeader(strb.toString());
            String url = requestHeader.getUrl();//请求地址

            String methon = requestHeader.getMethon();//请求方式

            HashMap<String,String> parameter = requestHeader.getParameter();//参数列表
            //System.out.println(requestHeader.getMethon());

            //读取xml
            ReadXml rx = new ReadXml("web.xml");
            HashMap<String, String> jiexi = rx.jiexi();
            //通过请求地址，获得对应类
            //

            String leiming = jiexi.get(url);


            if(null != leiming){
                //通过反射，调用对应类的方法
                Execute e = new Execute(leiming);

                e.execute(parameter,methon);
                html = "<html><head></head><body><h1>测试测试测试</h1></body></html>";
            }else{
                // 获取不到对应类，直接文本返回
                InputStream it = new FileInputStream(this.getClass().getResource("/web").getPath()+url);
                byte[] bb = new byte[1024];
                int ii;
                StringBuilder stringbuilder = new StringBuilder();
                while((ii=it.read(bb))!=-1){
                    stringbuilder.append(new String(bb,0,ii));
                }
                html = stringbuilder.toString();
            }
        }catch (Exception e){

        }

        return html;
    }
    public void writer(String html){

    }

}
