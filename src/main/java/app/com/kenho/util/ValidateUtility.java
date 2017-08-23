package app.com.kenho.util;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Stephanie on 2017/8/22.
 */
public class ValidateUtility {
    public static String judgeValidate(BindingResult result, HttpServletResponse response) {
        if(result.hasErrors()){
            List<ObjectError> list = result.getAllErrors();
            StringBuilder stringBuilder = new StringBuilder();
            for(ObjectError  error:list){
                stringBuilder.append("\n"+error.getDefaultMessage());
            }
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            return stringBuilder.toString();
        }
        return "ok";
    }
}
