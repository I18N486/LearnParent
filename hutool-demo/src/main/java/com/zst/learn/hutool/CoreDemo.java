package com.zst.learn.hutool;

import cn.hutool.core.clone.CloneSupport;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.convert.Converter;
import cn.hutool.core.convert.ConverterRegistry;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.ObjectUtil;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author stzhang
 * @Description
 * @Date 2021/3/30 11:34
 **/
public class CoreDemo {
    public static void main(String[] args) throws Exception {
        //testClone();
        //testConvert();
        testDateUtil();
    }

    /**
     * 可以利用hutool的CloneSupport父类，省去实现clone方法
     * 利用ObjectUtil可以实现深拷贝，前提：所有的类及属性类都需要实现serializable接口
     * @throws CloneNotSupportedException
     */
    private static void testClone() throws CloneNotSupportedException {
        people people = new people("人类");
        people clone = (CoreDemo.people) people.clone();
        System.out.println(clone.name);

        woman woman = new woman("女人");
        CoreDemo.woman womanClone = woman.clone();
        System.out.println(womanClone.name);

        //深拷贝
        Student student = ObjectUtil.clone(new Student("女学生", new woman("小红")));
        System.out.println(student.woman.name);
    }

    //传统的实现克隆，必须实现cloneable接口，然后重写clone()
    public static class people implements Cloneable{
        public String name;

        public people(String name) {
            this.name = name;
        }

        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
        }
    }
//    //使用hutool的泛型接口
//    public static class human implements Cloneable<human> {
//
//        @Override
//        public human clone() {
//            try {
//                return (human) super.clone();
//            } catch (CloneNotSupportedException e) {
//                throw new CloneRuntimeException(e);
//            }
//        }
//    }
    //继承hutool的泛型类
    //使用泛型类的好处是省去了实现clone方法
    public static class woman extends CloneSupport<woman> implements Serializable{
        public String name;

        public woman(String name) {
            this.name = name;
        }
    }
    public static class Student implements Serializable{
        public String s_name;
        public woman woman;

        public Student(String s_name, CoreDemo.woman woman) {
            this.s_name = s_name;
            this.woman = woman;
        }
    }

    /**
     * Convert类用于类型转换，主要有：
     * 1.基本类型与字串之间的转换（无需包装类转换）
     * 2.数组与字串之间的转换（无需自己遍历转换）
     * 3.数组与集合之间的转换（支持混合类型）
     * 4.全角、半角转换
     * 5.进制转换（16进制）
     * 6.Unicode字符转换
     * 7.编码转换
     * 8.时间单位转换（由毫秒值算出多少分钟，多少小时。。。）
     * 9.金额大小写转换（1 -》壹）
     */
    private static void testConvert(){
        String s2 = String.valueOf(3);
        Integer i2 = Integer.valueOf("3");
        String s1 = Convert.toStr(3);
        Integer i1 = Convert.toInt(s1);
        System.out.println("s1: "+s1+" s2: "+s2 + " i1: "+i1);

        int[] arr = {1,2,3,4,5};
        String s3 = Convert.toStr(arr);
        String[] str = {"1","2","3","4","5"};
        Integer[] arr2 = Convert.toIntArray(str);
        print(arr2);

        String date = "2021-03-30";
        Date dateTime = Convert.toDate(date);
        System.out.println(dateTime);

        Object[] data = {"a","你","好",'c',3};
        List<?> list = Convert.toList(data);
        print(list);

        System.out.println(Convert.toSBC("123456"));
        System.out.println(Convert.toDBC("123456"));
        String s4 = "hello world";
        System.out.println(Convert.toHex(s4, CharsetUtil.CHARSET_UTF_8));

        String s5 = "乱码？";
        String convertS5 = Convert.convertCharset(s5, "utf-8",CharsetUtil.ISO_8859_1);
        System.out.println(Convert.convertCharset(convertS5,CharsetUtil.ISO_8859_1,CharsetUtil.UTF_8));

        long millions = 345678;
        System.out.println(millions+"毫秒等于"+Convert.convertTime(millions, TimeUnit.MILLISECONDS,TimeUnit.MINUTES)+"分钟");

        double money = 17500.12;
        System.out.println(Convert.digitToChinese(money));

        //注册自定义转换器
        ConverterRegistry converterRegistry = ConverterRegistry.getInstance();
        converterRegistry.putCustom(woman.class,CustomConverter.class);
        //使用
        woman woman = converterRegistry.convert(woman.class, "自定义转换");
        System.out.println(woman.name);
    }

    //通过接口Converter实现自定义转换器
    public class CustomConverter implements Converter<woman> {
        @Override
        public woman convert(Object value, woman defaultValue) throws IllegalArgumentException {
            return new woman(value.toString());
        }
    }

    public static void print(Object obj){
        if (obj instanceof String){
            System.out.println(obj);
        } else if (obj instanceof Collection){
            ((Collection<?>) obj).forEach(e -> System.out.print(e+" "));
            System.out.println();
        } else {
            Object[] data = (Object[]) obj;
            for (Object datum : data) {
                System.out.print(datum+" ");
            }
            System.out.println();
        }
    }

    /**
     * DateUtil用于字串、long和日期之间的转换，支持格式化
     * 截取年、月、日
     * 偏移计算具体日期、计算差值
     * 计时器（执行耗时统计）
     * 星座属相
     * 是否闰年，计算年龄
     */


    private static void testDateUtil(){
        TimeInterval timer = DateUtil.timer();

        DateTime date = DateUtil.date(System.currentTimeMillis());
        String format = DateUtil.format(date, "yyyy-MM-dd HH:mm:ss");
        System.out.println(format);
        int year = DateUtil.year(date);
        DateTime nextWeek = DateUtil.offsetWeek(date, 1);
        System.out.println(nextWeek);

        DateTime past = DateUtil.parse("2018-07-10 13:14:20");
        long between = DateUtil.between(past, date, DateUnit.DAY);
        System.out.println("我们恋爱至今已有："+between +"天");

        System.out.println("消耗了："+timer.interval()+"毫秒");

        String zodiac = DateUtil.getZodiac(5, 14);
        String chineseZodiac = DateUtil.getChineseZodiac(1992);
        System.out.println("属相："+chineseZodiac+",星座："+zodiac+",年龄："+DateUtil.ageOfNow("1992-06-14")
                +"，当前是否闰年："+DateUtil.isLeapYear(1992));
    }

    /**
     * IoUtil用于文件读取、写入、支持流的获取和转换（字节、字符流互转），copy和close方法简化编码
     * FileUtil：
     *     文件操作：包括文件目录的新建、删除、复制、移动、改名等
     *     文件判断：判断文件或目录是否非空，是否为目录，是否为文件等等。
     *     绝对路径：针对ClassPath中的文件转换为绝对路径文件。
     *     文件名：主文件名，扩展名的获取
     *     读操作：包括类似IoUtil中的getReader、readXXX操作
     *     写操作：包括getWriter和writeXXX操作
     * FileTypeUtil：
     *      在服务端通过读取文件的首部几个二进制位来判断常用的文件类型，避免仅仅通过后缀名的方式来判断文件类型带来的不准确结果
     */
    private static void testIoUtil(){

    }

}
