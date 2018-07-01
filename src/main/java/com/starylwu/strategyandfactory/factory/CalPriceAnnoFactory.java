package com.starylwu.strategyandfactory.factory;

import com.starylwu.strategyandfactory.annotation.PriceRegion;
import com.starylwu.strategyandfactory.strategy.CalPriceStrategy;
import com.starylwu.strategyandfactory.strategy.impl.Orgnic;
import org.springframework.cache.concurrent.ConcurrentMapCache;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.lang.annotation.Annotation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Auther: Wuyulong
 * @Date: 2018/7/1 09:28
 * @Description: 根据注解获取实例的工厂
 */
public class CalPriceAnnoFactory {

    //私有化构造方法
    private CalPriceAnnoFactory(){
        initFactory();
    }

    private static ApplicationContext context = null;

    public static void initContext(ApplicationContext app){
        if (context != null){
            return;
        }
        context = app;
    }

    private static class CalPriceAnnoSingleton{
        private static final CalPriceAnnoFactory factory = new CalPriceAnnoFactory();
    }

    public static CalPriceAnnoFactory instance(){
        return CalPriceAnnoSingleton.factory;
    }



    /**
     * 要扫描的包
     */
    private static final String CAL_PRICE_IMPL = "com.starylwu.strategyandfactory.strategy.impl";

    /**
     * 统一类加载器
     */
    private ClassLoader classLoader = getClass().getClassLoader();

    /**
     * 用于存放 min 与 计算实例的关联
     */
    private ConcurrentMap<Integer, CalPriceStrategy> calPriceCache = new ConcurrentHashMap<>();


    /**
     * 获取价格计算实例
     * @param totalAmount
     * @return
     */
    public CalPriceStrategy createCalPrice(Double totalAmount){
        int min = (totalAmount.intValue() / 10000) * 10000;
        return calPriceCache.getOrDefault(min, new Orgnic());
    }

    /**
     * 初始化工厂
     */
    private void initFactory(){
        //获取class文件
        List<File> fileList = getClassWithPackage();

        //加载策略接口（使用相同的类加载器）
        try {
            Class<CalPriceStrategy> calPriceStrategyClass = (Class<CalPriceStrategy>)classLoader.loadClass(CalPriceStrategy.class.getName());

            //查看class文件是否是策略实现并且加载进入cache中
//            WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
            fileList.stream()
                    .map(file -> {
                        try {
                            return classLoader.loadClass(CAL_PRICE_IMPL + "." + file.getName().replace(".class", ""));
                        } catch (ClassNotFoundException e) {
                            throw new ClassCastException("can not found the file " + file.getName());
                        }
                    })
                    .filter(clazz -> {
                        return (CalPriceStrategy.class.isAssignableFrom(clazz) && clazz != calPriceStrategyClass);
                    }).forEach(clazz -> {
                        PriceRegion priceRegion = handleAnnotation((Class<? extends CalPriceStrategy>) clazz);
                        Optional.ofNullable(priceRegion).filter(Objects::nonNull).ifPresent(pr -> {
                            int min = priceRegion.min();
                            CalPriceStrategy calPriceStrategy = (CalPriceStrategy)context.getBean(clazz);
                            calPriceCache.putIfAbsent(min, calPriceStrategy);
                        });
                    });
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }


    /**
     * 获取类上面的注解
     * @param clazz
     * @return
     */
    private PriceRegion handleAnnotation(Class<? extends CalPriceStrategy> clazz){
        PriceRegion priceRegion = null;
        List<Annotation> annotations = Arrays.asList(Optional.ofNullable(clazz.getDeclaredAnnotations()).orElse(new Annotation[0]));
        return (PriceRegion) annotations.stream().filter(annotation -> {
            return annotation instanceof PriceRegion;
        })
        .findFirst().orElse(priceRegion);
    }

    /**
     * 获取制定包下的所有class文件
     */
    private List<File> getClassWithPackage(){
        String filePath = CAL_PRICE_IMPL.replace(".", "/");

        try {
            File file = new File(classLoader.getResource(filePath).toURI());
            return Arrays.asList(Optional.ofNullable(file).orElse(new File(""))
                    .listFiles(filename -> {
                        if (filename.getName().endsWith(".class")){
                            //是class文件
                            return true;
                        }
                        return false;
                    }));
        }catch (Exception e){
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
