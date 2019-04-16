package win.chenglei;

import java.util.HashMap;

public class RequestHeader {
    private String requestHeader;
    public RequestHeader(){

    }
    public RequestHeader(String requestHeader){
        this.requestHeader = requestHeader;
    }
    public void SetHeader(String requestHeader){
        this.requestHeader = requestHeader;
    }
    /**
     GET /abc?id=1 HTTP/1.1
     Host: localhost:9090
     Connection: keep-alive
     Cache-Control: max-age=0
     Upgrade-Insecure-Requests: 1
     User-Agent: Mozilla/5.0 (Macintosh; Intel Mac OS X 10_14_4) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/73.0.3683.103 Safari/537.36
     Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*\/*;q=0.8,application/signed-exchange;v=b3
     Accept-Encoding: gzip, deflate, br
     Accept-Language: zh-CN,zh;q=0.9,en;q=0.8
     /r/n
     aaa=111&bbb=2
     */

    public HashMap<String,String> getParameter(){
        HashMap<String,String> fh= new HashMap<>();
        if(null != requestHeader && null !=getMethon() && "POST".equals(getMethon()) ){
            //TODO   解析数据
            String substring = requestHeader.substring(requestHeader.lastIndexOf("\n") + 1);
            String[] split = substring.trim().split("&");
            for (String split1:split ) {
                String[] split2 = split1.split("=");
                fh.put(split2[0],split2[1]);

            }
        }
        return fh;
    }

    public String getUrl(){
        String fh ="";
        String substring = requestHeader.substring(0,requestHeader.indexOf("\n"));
        String[] s = substring.split(" ");
        fh = s[1];
        //去除get的问号后内容
        String[] split = fh.split("[?]");
        fh = split[0];
        return fh;
    }
    public String getMethon(){

        String fh=null;
        if(null != requestHeader){
            String[] s = requestHeader.split(" ");
            fh = s[0];
        }
        return fh;
    }
}
