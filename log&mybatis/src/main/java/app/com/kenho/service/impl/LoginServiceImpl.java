package app.com.kenho.service.impl;

import app.com.kenho.domain.Logininfo;
import app.com.kenho.mapper.LogininfoMapper;
import app.com.kenho.service.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Stephanie on 2017/8/20.
 */
@Service
public class LoginServiceImpl implements ILoginService {
    @Resource
    private LogininfoMapper logininfoMapper;
    @Override
    public Logininfo getLoginInfo(String username, String password) {
        Logininfo logininfo=new Logininfo();
        logininfo.setUsername(username);
        logininfo.setPassword(password);
        Logininfo returninfo=logininfoMapper.login(logininfo);
        return returninfo;
    }

    @Override
    public int insert(Logininfo logininfo) {
        logininfo.setState(0);
        logininfo.setUserType(0);
        return logininfoMapper.insert(logininfo);
    }
}
