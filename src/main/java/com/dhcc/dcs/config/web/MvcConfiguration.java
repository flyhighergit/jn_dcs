package com.dhcc.dcs.config.web;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.dhcc.dcs.interceptor.LoginInterceptor;
import com.dhcc.dcs.util.PathUtil;
import com.google.code.kaptcha.servlet.KaptchaServlet;


/**
 * 开启Mvc,自动注入spring容器。 WebMvcConfigurerAdapter：配置视图解析器
 * 当一个类实现了这个接口（ApplicationContextAware）之后，这个类就可以方便获得ApplicationContext中的所有bean
 * 
 * @author zhanglei
 *
 */
@Configuration
// 等价于<mvc:annotation-driven/>
@EnableWebMvc
public class MvcConfiguration implements WebMvcConfigurer, ApplicationContextAware {
	// Spring容器
	private ApplicationContext applicationContext;

	private static String seperator = System.getProperty("file.separator");

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * 静态资源配置
	 * 
	 * @param registry
	 */
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		String basePath = PathUtil.getImgBasePath()+"/upload/".replace("/", seperator);
		registry.addResourceHandler("/upload/**").addResourceLocations("file:"+basePath);
	}

	/**
	 * 定义默认的请求处理器
	 */
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}

	/**
	 * 创建viewResolver
	 * 
	 * @return
	 */
	@Bean(name = "viewResolver")
	public ViewResolver createViewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		// 设置Spring 容器
		viewResolver.setApplicationContext(this.applicationContext);
		// 取消缓存
		viewResolver.setCache(false);
		// 设置解析的前缀
		viewResolver.setPrefix("/WEB-INF/html/");
		// 设置试图解析的后缀
		viewResolver.setSuffix(".html");
		return viewResolver;
	}

	/**
	 * 文件上传解析器
	 * 
	 * @return
	 */
	@Bean(name = "multipartResolver")
	public CommonsMultipartResolver createMultipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("utf-8");
		// 1024 * 1024 * 20 = 20M
		multipartResolver.setMaxUploadSize(20971520);
		multipartResolver.setMaxInMemorySize(20971520);
		return multipartResolver;
	}
	
	/**
	 * 消息转换器
	 */
	/*@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		StringHttpMessageConverter converter  = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		converters.add(converter);
	}*/
	
	/**
	 * 消息转换器（自定义fastJson转换器）
	 */
	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		StringHttpMessageConverter converter  = new StringHttpMessageConverter(Charset.forName("UTF-8"));
		converters.add(converter);
	 	//1.需要定义一个convert转换消息的对象;
	 	FastJsonHttpMessageConverter fastJsonHttpMessageConverter = new FastJsonHttpMessageConverter();
	 	//2.添加fastJson的配置信息，比如：是否要格式化返回的json数据;
	 	FastJsonConfig fastJsonConfig = new FastJsonConfig();
	 	fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
	 	//3处理中文乱码问题
		List<MediaType> fastMediaTypes = new ArrayList<>();
		fastMediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
		//4.在convert中添加配置信息.
		fastJsonHttpMessageConverter.setSupportedMediaTypes(fastMediaTypes);
		fastJsonHttpMessageConverter.setFastJsonConfig(fastJsonConfig);
		//5.将convert添加到converters当中.
		converters.add(fastJsonHttpMessageConverter);
	}

	/**
	 * 添加拦截器配置
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 拦截的路径（全局）
		String interceptPath = "/**";
		// 注册拦截器
		InterceptorRegistration loginIR = registry.addInterceptor(new LoginInterceptor());
		// 配置拦截的路径
	    loginIR.addPathPatterns(interceptPath);
		// 配置不拦截的路径
		loginIR.excludePathPatterns("/driverlogin/login");
		loginIR.excludePathPatterns("/driverlogin/logincheck");
		loginIR.excludePathPatterns("/verify/getRecord");
		loginIR.excludePathPatterns("/frontend/signin");
		loginIR.excludePathPatterns("/frontend/protocol");
		loginIR.excludePathPatterns("/resources/js");
		loginIR.excludePathPatterns("/resources/css");
		loginIR.excludePathPatterns("/resources/images");
		loginIR.excludePathPatterns("/driverlogin/signinUser");
	}

	@Value("${kaptcha.border}")
	private String border;

	@Value("${kaptcha.textproducer.font.color}")
	private String fcolor;

	@Value("${kaptcha.image.width}")
	private String width;

	@Value("${kaptcha.textproducer.char.string}")
	private String cString;

	@Value("${kaptcha.image.height}")
	private String height;

	@Value("${kaptcha.textproducer.font.size}")
	private String fsize;

	@Value("${kaptcha.noise.color}")
	private String nColor;

	@Value("${kaptcha.textproducer.char.length}")
	private String clength;

	@Value("${kaptcha.textproducer.font.names}")
	private String fnames;

	/**
	 * 由于web.xml不生效了，需要在这里配置Kaptcha验证码Servlet
	 */
	@Bean
	public ServletRegistrationBean<KaptchaServlet> servletRegistrationBean() throws ServletException {
		ServletRegistrationBean<KaptchaServlet> servlet = new ServletRegistrationBean<KaptchaServlet>(new KaptchaServlet(), "/Kaptcha");
		servlet.addInitParameter("kaptcha.border", border);// 无边框
		servlet.addInitParameter("kaptcha.textproducer.font.color", fcolor); // 字体颜色
		servlet.addInitParameter("kaptcha.image.width", width);// 图片宽度
		servlet.addInitParameter("kaptcha.textproducer.char.string", cString);// 使用哪些字符生成验证码
		servlet.addInitParameter("kaptcha.image.height", height);// 图片高度
		servlet.addInitParameter("kaptcha.textproducer.font.size", fsize);// 字体大小
		servlet.addInitParameter("kaptcha.noise.color", nColor);// 干扰线的颜色
		servlet.addInitParameter("kaptcha.textproducer.char.length", clength);// 字符个数
		servlet.addInitParameter("kaptcha.textproducer.font.names", fnames);// 字体
		return servlet;
	}

}