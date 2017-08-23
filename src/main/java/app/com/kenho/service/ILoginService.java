package app.com.kenho.service;

import app.com.kenho.domain.Logininfo;

/**
 * Created by Stephanie on 2017/8/20.
 */
public interface ILoginService {
    public Logininfo getLoginInfo(String username,String password);
    public int insert(Logininfo logininfo);
}
