package app.com.kenho.web.controller;

import app.com.kenho.domain.Logininfo;
import app.com.kenho.service.ILoginService;
import app.com.kenho.service.impl.LoginServiceImpl;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.naming.Binding;
import javax.validation.Valid;
import java.util.List;

/**
 * Created by Stephanie on 2017/8/19.
 */
@RestController
public class indexController {
    @Resource
    private ILoginService loginServiceImpl;

    @RequestMapping(value = "logininfo/{username}/{password}",method = RequestMethod.GET)
    public Logininfo getlogininfo(@PathVariable("username")String username,@PathVariable("password")String password)
    {
        //loginServiceImpl.getLoginInfo("stephanie","81DC9BDB52D04DC20036DBD8313ED055");
        return loginServiceImpl.getLoginInfo(username,password);
    }

    @RequestMapping(value = "logininfo",method = RequestMethod.POST)
    public void valid(@Validated Logininfo logininfo, BindingResult bindingResult)
    {
        if(bindingResult.hasErrors())
        {
            List<ObjectError> list = bindingResult.getAllErrors();
            StringBuilder stringBuilder = new StringBuilder();
            for(ObjectError  error:list){
                System.out.println("\n"+error.getDefaultMessage());
            }
        }
        else
        {
            loginServiceImpl.insert(logininfo);
        }
    }
}
