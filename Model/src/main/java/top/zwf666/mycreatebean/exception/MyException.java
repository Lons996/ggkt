package top.zwf666.mycreatebean.exception;


import org.springframework.lang.Nullable;

public class MyException extends RuntimeException {
    private Integer code;
    private String msg;

    public MyException(Exception e) {
        super(e.getMessage());
        this.msg = e.getMessage();
    }
    public MyException(String e) {
        super(e);
        this.msg = e;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "MyException{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                '}';
    }
}
