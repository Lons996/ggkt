package top.zwf666.mycreatebean.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Message implements Serializable {
    //状态码
    private int code;
    //提示信息
    private String msg;
    //返回的数据
    private Map<String, Object> data = new HashMap<>();

    //链式调用
    public Message add(String key,Object value){
        this.getData().put(key,value);
        return this;
    }

    //链式调用
    public Message add(String key,Object value,int code){
        this.getData().put(key,value);
        this.code = code;
        return this;
    }

    //链式调用
    public Message add(Map<String,Object> map ,int code){
        this.setData(map);
        this.code = code;
        return this;
    }
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public void setData(Map<String, Object> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }

}
