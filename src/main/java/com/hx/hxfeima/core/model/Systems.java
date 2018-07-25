//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.hx.hxfeima.core.model;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;


import com.hx.hxfeima.utils.StringUtils;
import com.hx.hxfeima.utils.TypeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.web.servlet.ModelAndView;



public class Systems {
    private static Logger logger = LoggerFactory.getLogger(Systems.class);
    public static final String MAIN_JSP_NAME = "main";
    public static final String VIEW_NAME = "_view";
    public static final String DATAGRID_NAME = "datagrid";
    public static final String MESSAGE_SUCCESS = "success";
    public static final String MESSAGE_ERROR = "error";
    public static final String MENU_CLOSE_NAME = "_menu_close";
    public static final String COMPANY_SCALE_BLOC = "bloc";
    public static final String COMPANY_SCALE_SOLE = "sole";
    
    public static final String CURRENT_USER_ID = "CURRENT_USER_ID";
    
    
	public final static BigDecimal CAID_PT = new BigDecimal(1);

    /**
     * token有效期（小时）
     */
    public static final int TOKEN_EXPIRES_HOUR = 72;

    /**
     * 存放Authorization的header字段
     */
    public static final String AUTHORIZATION = "authorization";
    
    
    public static final String TYPESK = "1";
    
    
    
	// 1000m的经度差
	public final static double LONGITUDE = 0.008993216074788535;
	// 1000米的纬度差
	public final static double LATITUDE = 0.010013208378694571;
	// 查询指定范围内的数据
	public final static int RANGE = 3;
    
    
	public final static double LICAISY = 3;  //理财基数3
	// 理财底数
	public final static double LICAIDS = 10000;
	
	
	// 当日累计最大提现金额
	public final static double MAXMONEY = 30000;

    
	// 102：会员积分充值比例
		public final static double r102 = 5;
		// 会员充值商家奖励比例
		public final static double r103 = 0.5;
		// 充值分润总和比例
		public final static double r104 = 4.76;
		// 让利率最低值
		public final static double r105 = 10;
		
		
		public final static double r1 = 2;
		// 21：省代管理平台让利分润
		public final static double r21 = 0.3;
		// 31：市代管理平台让利分润
		public final static double r31 = 0.5;
		// 41：区代管理平台让利分润
		public final static double r41 = 0.8;
		// 71：MP铺设商平台让利分润(剩余部分50%)
		public final static double r71 = 50;
		// 76：MP投资商平台让利分润(剩余部分50%)
		public final static double r76 = 50;
		// 会员推广(快乐宝)
		public final static double r90 = 0.5;
		// 会员推广(快刷宝)
		public final static double r90_2 = 1;
		// 省代推广
		public final static double r91 = 0.03;
		// 市代推广
		public final static double r92 = 0.05;
		// 区代推广
		public final static double r93 = 0.08;
		// 1003：联盟商家提现手续费比例1%
		public final static double r1003 = 1;
		//会员提现手续费比例2%
		public final static double r1004 = 2;
		//省代提现手续费比例0.3%
		public final static double r10021 = 0.3;
		//市代提现手续费比例0.5%
		public final static double r10031 = 0.5;
		//区代提现手续费比例0.8%
		public final static double r10041 = 0.8; 
		
		

		// 500：升级VIP会员积分
		public final static double r500 = 500;
		// 502：最低结算金额，单位：元。(100)
		public final static double r502 = 100;
		// 503：自动注册会员的最低消费金额。(1)
		public final static double r503 = 1;
		// 506：会员最低充值金额(1)
		public final static double r506 = 1;
		// 602：短信群发积分费用(0.06)
		public final static double r602 = 0.06;
		// 902：注册会员赠送积分。
		public final static double r902 = 100;
  
    
    public static  enum PTID{
         ALIPAY("4"), WXPAY("2");
        private final String text;
        private PTID(final String text){
            this.text=text;
        }
        @Override
        public String toString(){
            return text;
        }
}
    public static  enum TLTYPE{
        MEMBERTYPE("C"), MEMBERX("X");
       private final String text;
       private TLTYPE(final String text){
           this.text=text;
       }
       @Override
       public String toString(){
           return text;
       }
    }
       public static  enum TLMODE{
            MEMBERT("WB"), MEMBALIPY("9A"), MEMBWX("9B");
          private final String text;
          private TLMODE(final String text){
              this.text=text;
          }
          @Override
          public String toString(){
              return text;
          } 
       
       }

    private String loginLogo = "#";
    private String mainLogo = "#";
    private Integer authCodeWidth = Integer.valueOf(0);
    private Integer authCodeHeight = Integer.valueOf(0);
    private Integer defaultRows = Integer.valueOf(20);
    private String systemType = "";
    private String companyScale = "sole";
    private Boolean useRedis = Boolean.valueOf(false);
    private Boolean debug = Boolean.valueOf(false);
    private Boolean forceSignIn = Boolean.valueOf(true);
    private Boolean dynamicMenu = Boolean.valueOf(true);
    private Boolean checkAuthCode = Boolean.valueOf(true);
    private Boolean loginFailLock = Boolean.valueOf(true);
    private Integer loginFailCount = Integer.valueOf(0);
    private Boolean useOptLog = Boolean.valueOf(false);
    private Integer uploadFileMax = Integer.valueOf(10485760);
    private Boolean isSingleLogin = Boolean.valueOf(false);
    private String staticHost;
    private Integer staticPort;
    private String staticUsername;
    private String staticPassword;
    private String staticPath;
    private String accessUrl;
    private Properties configuration;
    private static Properties views;

    public Systems() {
    }

//    public final void initProperties() {
//        try {
//            logger.debug("load [cn/com/hxweb/config/config.properties]");
//       //     this.configuration = PropertiesUtils.get("config.properties");
//
//            this.configuration=PropertiesLoaderUtils.loadProperties(
//            		new ClassPathResource("cn/com/hxweb/config/config.properties"));
//
//
//
//        } catch (IOException var11) {
//            this.configuration = new Properties();
//            logger.error("[cn/com/hxweb/config/config.properties] not found.", var11);
//        }
//
//        Set keys = this.configuration.stringPropertyNames();
//        Class clzz = Systems.class;
//        Iterator var4 = keys.iterator();
//
//        while(var4.hasNext()) {
//            String key = (String)var4.next();
//            String fieldName = StringUtils.toHumpCase(key, '.');
//
//            try {
//                Field e = clzz.getDeclaredField(fieldName);
//                String value = this.configuration.getProperty(key);
//                if(StringUtils.isNotBlank(value)) {
//                    Class typeClass = e.getType();
//                    if(TypeUtils.is(typeClass, Integer.class)) {
//                        e.set(this, Integer.valueOf(Integer.parseInt(value)));
//                    } else if(TypeUtils.is(typeClass, Boolean.class)) {
//                        e.set(this, Boolean.valueOf(Boolean.parseBoolean(value)));
//                    } else if(TypeUtils.is(typeClass, Double.class)) {
//                        e.set(this, Double.valueOf(Double.parseDouble(value)));
//                    } else {
//                        e.set(this, value);
//                    }
//                }
//            } catch (NoSuchFieldException var9) {
//                logger.warn("\"{}\" in [cn/com/hxweb/config/config.properties] has not member variables named \"{}\".", key, fieldName);
//            } catch (Exception  var10) {
//                logger.error("\"" + key + "\" in [cn/com/hxweb/config/config.properties] invalid.", var10);
//            }
//        }
//
//    }
//
//    public String getConfig(String key) {
//        return this.configuration.getProperty(key);
//    }
//
//    private final void initViewMapping() {
//        try {
//            logger.debug("load [cn/com/hxweb/config/views.properties]");
//            views=PropertiesLoaderUtils.loadProperties(
//            		new ClassPathResource("cn/com/hxweb/config/views.properties"));
//
//
//        } catch (IOException var2) {
//            views = new Properties();
//            logger.warn("[cn/com/hxweb/config/views.propertie] not found.");
//        }
//
//    }

    public String getView(String key) {
        return views.getProperty(key);
    }

    @PostConstruct
    private final void init() {
       // this.initProperties();
       // this.initViewMapping();
    }

    @PreDestroy
    private final void destory() {
        views = null;
    }

    public static final ModelAndView newMainModelAndView(String contentViewName) {
        String view = views.getProperty(contentViewName);
        if(StringUtils.isNotBlank(view)) {
            contentViewName = view;
        }

        return new ModelAndView("main", "_view", contentViewName);
    }

    public static final ModelAndView newModelAndView(String viewName) {
        String view = views.getProperty(viewName);
        if(StringUtils.isNotBlank(view)) {
            viewName = view;
        }

        return new ModelAndView(viewName);
    }

    public String mappingView(String viewName) {
        return views.getProperty(viewName);
    }

    public String getCompanyScale() {
        return this.companyScale;
    }

    public boolean isBloc() {
        return "bloc".equals(this.companyScale);
    }

    public boolean isSole() {
        return "sole".equals(this.companyScale);
    }

    public String getLoginLogo() {
        return this.loginLogo;
    }

    public String getMainLogo() {
        return this.mainLogo;
    }

    public boolean isUseRedis() {
        return this.useRedis.booleanValue();
    }

    public int getAuthCodeWidth() {
        return this.authCodeWidth.intValue();
    }

    public int getAuthCodeHeight() {
        return this.authCodeHeight.intValue();
    }

    public int getDefaultRows() {
        return this.defaultRows.intValue();
    }

    public String getSystemType() {
        return this.systemType;
    }

    public int getUploadFileMax() {
        return this.uploadFileMax.intValue();
    }

    public Boolean isDebug() {
        return this.debug;
    }

    public Boolean isForceSignIn() {
        return this.forceSignIn;
    }

    public Boolean isDynamicMenu() {
        return this.dynamicMenu;
    }

    public Boolean isCheckAuthCode() {
        return this.checkAuthCode;
    }

    public Boolean getLoginFailLock() {
        return this.loginFailLock;
    }

    public Integer getLoginFailCount() {
        return this.loginFailCount;
    }

    public Boolean getUseOptLog() {
        return this.useOptLog;
    }

    public Boolean getIsSingleLogin() {
        return this.isSingleLogin;
    }

    public String getStaticPath() {
        return this.staticPath;
    }

    public String getStaticHost() {
        return this.staticHost;
    }

    public Integer getStaticPort() {
        return this.staticPort;
    }

    public String getStaticPassword() {
        return this.staticPassword;
    }

    public String getStaticUsername() {
        return this.staticUsername;
    }

    public String getAccessUrl() {
        return this.accessUrl;
    }
}
