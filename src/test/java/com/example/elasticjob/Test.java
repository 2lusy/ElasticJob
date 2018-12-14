package com.example.elasticjob;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * @Auther: K0370098
 * @Date: 2018/12/12 19:25
 * @Description:
 */
@Slf4j
public class Test {
    public static void main(String[] args) {
        Timestamp a = Timestamp.valueOf("2018-05-18 09:32:32");
        Timestamp b = Timestamp.valueOf("2018-05-11 09:32:32");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String format = df.format(System.currentTimeMillis());

        if (b.before(a)) {
            System.out.println("b时间比a时间早");
        }
        if (a.after(b)) {
            System.out.println("b时间比a时间早");
        }
        Timestamp systemTime = Timestamp.valueOf(format);
        System.out.println(systemTime);
        System.out.println(a);



       /* Calendar now = Calendar.getInstance();
        System.out.println("年: " + now.get(Calendar.YEAR));
        System.out.println("月: " + (now.get(Calendar.MONTH) + 1) + "");
        System.out.println("日: " + now.get(Calendar.DAY_OF_MONTH));
        System.out.println("时: " + now.get(Calendar.HOUR_OF_DAY));
        System.out.println("分: " + now.get(Calendar.MINUTE));
        System.out.println("秒: " + now.get(Calendar.SECOND));
        System.out.println("当前时间毫秒数：" + now.getTimeInMillis());
        System.out.println(now.getTime());*/

        Calendar cal=Calendar.getInstance(); //调用Calendar 中的方法；
        cal.set(Calendar.DAY_OF_MONTH, 1); // 把时间调整为当月的第一天；
        cal.add(Calendar.MONTH,1); // 月份调至下个月；
        cal.add(Calendar.DAY_OF_MONTH, -1); // 时间减去一天（就等于上个月的最后一天）
        int month=cal.get(Calendar.MONTH)+1; //调取月份（月份在表示中会少 1，如：1月份得出数字是 0；
        int days=cal.get(Calendar.DAY_OF_MONTH);//调取当月的天数。
        System.out.println(month+"月份有"+days+"天");//打印最后被结果。
        String Interval="1";
       // String Interval="70";
        double K = Math.floor(Double.valueOf(Interval) / (60 * 24 * days));
     //   System.out.println(K);
        String cron = getCron(Interval);
        log.info("========================="+cron);
    }

    private static  String getCron(String Interval){
        //Interval数字为分钟的实现
        Calendar cal=Calendar.getInstance();
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH,1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
       // int month=cal.get(Calendar.MONTH)+1;
        int days=cal.get(Calendar.DAY_OF_MONTH);
        //时间在一年之内，最少一个月开始
        BigDecimal bbb=new BigDecimal(Double.toString(Double.valueOf(Interval) / (60 * 24 * days)));
        double dd=bbb.setScale(16,BigDecimal.ROUND_HALF_UP).doubleValue();
        System.out.println(dd);
        if(1<= dd && dd <12){
            double month = Math.floor(Double.valueOf(Interval) / (60 * 24 * days));
            int mth = new Double(month).intValue();
            double v = (dd - month) * 12;
            double day = Math.floor(v);
            int d = new Double(day).intValue();
            double v1 = (v - day) * 24;
            double hour = Math.floor(v1);
            int h = new Double(hour).intValue();
            double v2 = (v1 - h) * 60;
            double mi = Math.floor(v2);
            int m = new Double(mi).intValue();
            System.out.println(v);
            log.info("月"+"0 0/"+m+" 0/"+h+" 0/"+d+" 0/"+mth+" ?");
            return "0 0/"+m+" 0/"+h+" 0/"+d+" 0/"+mth+" ?";
        }
        //时间在一月之内，最少一个天开始
        BigDecimal bb=new BigDecimal(Double.toString(Double.valueOf(Interval) / (60 * 24)));
        double dou=bb.setScale(16,BigDecimal.ROUND_HALF_UP).doubleValue();
        if(1<= dou && dou <days){
            double day = Math.floor(Double.valueOf(Interval) / (60 * 24));
            int d = new Double(day).intValue();
            double hour = Math.floor((dou - d)*24);
            int h = new Double(hour).intValue();
            double mi = Math.floor(((dou - d)*24-h)*60);
            int m = new Double(mi).intValue();
            log.info("天"+"0 0/"+m+" 0/"+h+" 0/"+d+" * ?");
            return "0 0/"+m+" 0/"+h+" 0/"+d+" * ?";
        }
        //时间在一天之内，最少一个小时开始
        BigDecimal b=new BigDecimal(Double.toString(Double.valueOf(Interval) / 60));
        double i=b.setScale(16,BigDecimal.ROUND_HALF_UP).doubleValue();
        if(1<=i && i<24){
            double hour = Math.floor(Double.valueOf(Interval) / 60);
            int h = new Double(hour).intValue();
            Integer m = Integer.valueOf(Interval);
            log.info("时"+"0 0/"+m%60+" 0/"+h+" * * ?");
            return "0 0/"+m%60+" 0/"+h+" * * ?";
        }

        //时间在一小时之内，最少一个分钟开始
        Double mi = Double.valueOf(Interval);
        int o = new Double(mi).intValue();
        if(1<=o && o<60){
            log.info("分"+"0 0/"+o+" * * * ?");
            return "0 0/"+o+" * * * ?";
        }
        Double[] doubles = new Double[9];
        for (int t=1;t<10;t++){
            doubles[t-1]=Double.valueOf("0."+t);
        }
      //  System.out.println(JSON.toJSONString(doubles));
        Double mtest = Double.valueOf(Interval);
        for(int t=1;t<10;t++){
            System.out.println(mtest);
            //int mmm = new Double(mm).intValue();
            if (String.valueOf(doubles[t]).equals(String.valueOf(mtest))){
                int miao = new Double(doubles[t]*60).intValue();
                System.out.println(doubles[t]);
                return "0/"+miao+" * * * * ?";
            }
        }
        //默认"0/5 * * * * ?"，测试用
       // log.info("默认cron表达式:"+"0/5 * * * * ?");
        return "0/5 * * * * ?";
        //Interval数字为秒或小时......的实现
    }

}
