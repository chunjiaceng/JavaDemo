package com.example.demo.filter;

import com.example.demo.pojo.LogDetails;
import com.example.demo.pojo.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.Nullable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;

/**
 * @author Ruby Ceng 曾春佳
 * @version 1.0.0
 * @ClassName LogHandleInterceptor.java
 * @Description TODO springMVC日志记录拦截器
 * @createTime 2024年06月06日 13:01:00
 */

@Component
@Slf4j
public class LogHandleInterceptor implements HandlerInterceptor {
    // 防止异步请求，可以在不同的线程中调用LogDetails对象
    private ThreadLocal<LogDetails> logDetails = new ThreadLocal<>();
    @Resource
    private ExecutorService executorService;
    @Value("${log.file.path}")
    private String logFilePath;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            LogDetails logDetail = new LogDetails();
            logDetails.set(logDetail);
            logDetail.setStartTime(System.currentTimeMillis());
            logDetail.setRemoteAddr(request.getRemoteAddr());
            logDetail.setUri(request.getRequestURI());
            // 如果找到对应的处理类
            if (handler instanceof HandlerMethod){
                HandlerMethod hm =(HandlerMethod) handler;
                logDetail.setHandle(hm.getBeanType().getSimpleName()+"."+ hm.getMethod().getName());
            }else{
                // 不属于处理器
                logDetail.setHandle(handler.getClass().getSimpleName());
            }
            logDetail.setMethod(request.getMethod());
            return  true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        LogDetails details = logDetails.get();
        if(details != null){
            details.setProcessTime(System.currentTimeMillis());

        }


    }
    // 处理器已经全部处理完成，异常处理也在这个模块进行处理
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        LogDetails details = logDetails.get();
        if(details != null){
            details.setEndTime(System.currentTimeMillis());
            details.setException(ex == null?"":ex.getClass().getSimpleName()+"-"+ex.getMessage());
            details.setCode(response.getStatus());
            // 先将用户置空
            details.setUser("-");
            if(isAuthenticated() && isUser()){
                LoginUser user = (LoginUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                details.setUser(user.getUser().getUsername());
            }
            printLogtoConsole(details);
            printLogtoFile(details);
        }
        logDetails.remove();
    }
    // 是否认证成功
    boolean  isAuthenticated(){
        return SecurityContextHolder.getContext()!= null
                &&SecurityContextHolder.getContext().getAuthentication() != null
                && SecurityContextHolder.getContext().getAuthentication().isAuthenticated();

    }
    //认证用户是否合法存在
    boolean isUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return  authentication.getPrincipal() != null
                &&  authentication.getPrincipal() instanceof LoginUser;
    }
    // 输出日志到控制台方法
    private void printLogtoConsole(LogDetails logDetails){
        log.info("{},{},{},{},{},{},{},{}ms,{}ms",
                logDetails.getRemoteAddr(),logDetails.getUri(),logDetails.getMethod(),
                logDetails.getHandle(),logDetails.getCode(),logDetails.getUser(),logDetails.getException(),
                logDetails.getEndTime() - logDetails.getStartTime(), //总时间
                logDetails.getProcessTime() - logDetails.getStartTime() //  处理时间
                 );
    }
    // 异步线程 输入日志到文件中
    private void printLogtoFile(LogDetails logDetails){
        executorService.submit(() -> {
            writeLogToFile(formatLog(logDetails),logFilePath);
        });

    }
    // 序列化方法
    private static String formatLog(LogDetails logDetails) {
        // 格式化日志对象为字符串的逻辑，可以根据需要自定义日志格式
        StringBuilder sb = new StringBuilder();
        Date date = new Date(System.currentTimeMillis());
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String nowDate = simpleDateFormat.format(date);
        sb.append("===================="+nowDate+"==================== \n");
        sb.append("StartTime: ").append(logDetails.getStartTime()).append("\n");
        sb.append("ProcessTime: ").append(logDetails.getProcessTime()).append("\n");
        sb.append("EndTime: ").append(logDetails.getEndTime()).append("\n");
        sb.append("Code: ").append(logDetails.getCode()).append("\n");
        sb.append("Handle: ").append(logDetails.getHandle()).append("\n");
        sb.append("Method: ").append(logDetails.getMethod()).append("\n");
        sb.append("URI: ").append(logDetails.getUri()).append("\n");
        sb.append("RemoteAddr: ").append(logDetails.getRemoteAddr()).append("\n");
        sb.append("Exception: ").append(logDetails.getException()).append("\n");
        sb.append("User: ").append(logDetails.getUser()).append("\n");
        return sb.toString();
    }
    // 将日志写入文件中
    public static void writeLogToFile(String logString, String filePath) {
            try (FileWriter fileWriter = new FileWriter(filePath,true);
                 BufferedWriter writer = new BufferedWriter(fileWriter)) {
                writer.write(logString);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

}
