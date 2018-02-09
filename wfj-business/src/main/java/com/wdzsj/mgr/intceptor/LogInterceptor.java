package com.wdzsj.mgr.intceptor;

import com.alibaba.fastjson.JSON;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志记录拦截器
 */
public class LogInterceptor implements MethodInterceptor {

    private final static Logger loger = LoggerFactory.getLogger(LogInterceptor.class);

    public Object invoke(MethodInvocation invocation) throws Throwable {

        String methodName = invocation.getMethod().toString().substring(invocation.getMethod().toString().lastIndexOf(" ") + 1);
        String name = invocation.getMethod().getName();

        // 方法前记录方法名称
        loger.info("方法 ****【" + name + "】**** 开始执行作 **** start ****");
        loger.info("当前执行的方法是 ****【" + methodName + "】");

        //获取方法中的参数
        Object[] arguments = invocation.getArguments();//方法入参

        if (arguments == null || arguments.length < 1) {
            loger.info("当前方法【 无参数 】");
        } else {
            for (int args=0; args<arguments.length;args++) {
                if(arguments[args] instanceof HttpServletRequest){
                    HttpServletRequest requests = (HttpServletRequest)arguments[args];

                    StringBuffer requestURL = requests.getRequestURL();
                    loger.info("当前方法的 request 参数中的数据 HttpServletRequest Parameter：【 " + requestURL + " 】");
                    String requestURI = requests.getRequestURI();
                    loger.info("当前方法的 request 参数中的数据 HttpServletRequest Parameter：【 " + requestURI + " 】");

                    loger.info("当前方法的第【 " + args + " 】个参数是 HttpServletRequest Parameter：" + arguments[args]);
                }else if (arguments[args] instanceof HttpServletResponse){
                    loger.info("当前方法的第【 " + args + " 】个参数是 HttpServletResponse Parameter：" + arguments[args]);
                }else {
                    loger.info("当前方法的第【 " + args + " 】个参数是：****** " + JSON.toJSONString(arguments[args]) + " ******");
                }
            }
        }

        //获取方法执行完毕后的返回结果
        Object obj = invocation.proceed();// 执行方法后返回的结果
        loger.info("执行完当前的方法后返回的结果 **** " + JSON.toJSONString(obj) + "****");

        loger.info("方法 ****【" + name + "】**** 执行完毕 **** end ****");// 方法后的操作

        return obj;
    }
}
