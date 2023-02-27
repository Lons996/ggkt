package top.zwf666.mycreatebean.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zwf666.mycreatebean.util.Message;

@ControllerAdvice
public class MyExceptionHandle {

    @ResponseBody //返回封装的异常信息
    @ExceptionHandler(Exception.class) //全局异常处理：发生异常时调用此方法
    public Message error(Exception e){
        Message message = new Message();
        message.add("error",e.getMessage(),404).setMsg("执行全局异常处理");
        return message;
    }

    @ResponseBody
    @ExceptionHandler({ArithmeticException.class}) //特定异常处理：优先处理指定异常
    public Message specificError(Exception e){
        Message message = new Message();
        message.add("specificError",e.getMessage(),404).setMsg("执行特定异常处理");
        return message;
    }

    @ResponseBody
    @ExceptionHandler({MyException.class}) //自定义异常处理：指定自定义的异常类
                                           //              需要手动捕获异常并抛出
    public Message myException(MyException e){
        e.setCode(555);
        Message message = new Message();
        message.add("MyException",e).setMsg(e.getMsg());
        message.setCode(404);
        return message;
    }

}
