package com.hx.hxfeima.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegUtil {
	
	public static Integer findIndex(String source,String regEx){
		
		Pattern pat=Pattern.compile(regEx);
		Matcher mat = pat.matcher(source);
		Integer flag=0;
		while(mat.find()){
			flag=mat.start();
			if(flag>0){
				break;
			}
		}
		
		return flag;
	}
	
	public static void main(String[] args){

		StringBuilder source=new StringBuilder("select u.user_id ");
		source.append(",org.organ_name ");
		source.append(",u.login_name ");
		source.append(",u.true_name ");
		source.append(",r.role_name ");
		source.append(",u.phone ");
		source.append(",u.state ");
		source.append(",u.login_time ");
		source.append(",(select count(useLog.user_id) from sys_use_log useLog where useLog.user_id = u.user_id and state = '10A' AND oper_type = 'Weblogin') logincount ");
		source.append("from t_user u ");
		source.append("left join t_organ org on u.org_id=org.org_id ");
		source.append("left join t_userrole userrole on userrole.user_id=u.user_id ");
		source.append("left join t_role r on userrole.role_id=r.role_id ");
		Integer index=RegUtil.findIndex(source.toString(), "from\\s[0-9a-zA-Z_]+\\su\\s");
		
		String s2=source.substring(index);
		String s1=source.substring(0,index);
		System.out.println("s1:"+s1);
		System.out.println("s2:"+s2);
	}
}
