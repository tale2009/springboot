package app.com.kenho.domain;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Setter @Getter
public class Logininfo extends BaseDomain{
	public static final int STATE_NOMAL=0;
	public static final int STATE_LOCK=1;
	public static final int USER_MANAGER = 0;// 后台用户
	public static final int USER_CLIENT = 1;// 前台用户
	@NotNull(message = "用户名不能为空")
	private String username;
	@NotNull(message = "密码不能为空")
	@Length(min = 6,max = 20,message = "密码长度应该大于6位小于20位")
	private String password;
	private int state;
	private int userType;
}
