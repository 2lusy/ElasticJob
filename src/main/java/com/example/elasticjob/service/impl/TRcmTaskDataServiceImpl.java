package com.example.elasticjob.service.impl;
import com.example.elasticjob.config.RegistryCenterConfiguration;
import com.example.elasticjob.config.RegistryCenterConstants;
import com.example.elasticjob.entity.TRcmTask;
import com.example.elasticjob.service.TRcmTaskDataService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Auther: K0370098
 * @Date: 2018/12/12 14:38
 * @Description:数据转换成配置中心需要数据
 */
@Service
@Slf4j
public class TRcmTaskDataServiceImpl implements TRcmTaskDataService {
    private static final ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<String, String>();
    private static final String flagkey = "BOOLEANFLAG";
    static {
        concurrentHashMap.put(flagkey, "false");
    }
    private static HashMap<String,List<TRcmTask>> hashMapTRcmTasks=new HashMap<String,List<TRcmTask>>();
    private static final String key="TRCMTASKKEY";
    private static int obchange=0;

    @Override
    public List<RegistryCenterConfiguration> getTRcmTaskData(List<TRcmTask> tRcmTasks) {
//        List<TRcmTask> TRcmTasks = tRcmTaskRepository.findAll();
        //判断时间是否有效
        for(int i=0;i<tRcmTasks.size();i++){
            Timestamp s = tRcmTasks.get(i).getdStartDate();
            Timestamp e = tRcmTasks.get(i).getdEndDate();
            Timestamp monitorTime = tRcmTasks.get(i).getdMonitorTime();
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
            String format = df.format(System.currentTimeMillis());
            Timestamp systemTime = Timestamp.valueOf(format);
            if(monitorTime.before(s)||monitorTime.after(e)||systemTime.before(monitorTime)||systemTime.after(e)){
                tRcmTasks.remove(i);
            }
        }
        List<TRcmTask> tRcmTasksHashValue = hashMapTRcmTasks.get(key);
        if (tRcmTasksHashValue==null){
            hashMapTRcmTasks.put(key,tRcmTasks);
            Iterator<TRcmTask> iterator = tRcmTasks.iterator();
            List<RegistryCenterConfiguration> registryCenterConfigurations=new ArrayList<RegistryCenterConfiguration>();
            while(iterator.hasNext()){
                TRcmTask tRcmTask = iterator.next();
                RegistryCenterConfiguration registryCenterConfiguration = changeTRcmTaskToConfig(tRcmTask);
                registryCenterConfigurations.add(registryCenterConfiguration);
            }
            return registryCenterConfigurations;
        }
        Iterator<TRcmTask> iterator = tRcmTasks.iterator();
        ArrayList<TRcmTask> objects = new ArrayList<>();
        List<RegistryCenterConfiguration> registryCenterConfigurations=new ArrayList<RegistryCenterConfiguration>();
        while(iterator.hasNext()){
            TRcmTask tRcmTask = iterator.next();
            TRcmTask tRcmTask1 = transformationDataObj(tRcmTask, tRcmTasks, tRcmTasksHashValue);
            objects.add(tRcmTask1);
            RegistryCenterConfiguration registryCenterConfiguration = changeTRcmTaskToConfig(tRcmTask);
            if (obchange>0){
                registryCenterConfiguration.setKeyflag("false");
            }
            if (obchange==0){
                registryCenterConfiguration.setKeyflag("true");
            }
            registryCenterConfigurations.add(registryCenterConfiguration);
        }
        hashMapTRcmTasks.put(key,objects);
        return registryCenterConfigurations;
    }

    private TRcmTask transformationDataObj(TRcmTask tRcmTask,List<TRcmTask> tRcmTasks,List<TRcmTask> tRcmTasksHashValue){
        //修改了或原来的job实例
        if(tRcmTasks.size()==tRcmTasksHashValue.size()){
            cloneObject(tRcmTask,tRcmTasksHashValue);
        }
        //添加了job实例
        if(tRcmTasks.size()>tRcmTasksHashValue.size()){
            int k=0;
            for(int i=0;i<tRcmTasks.size();i++){
                for(int j=0;j<tRcmTasksHashValue.size();j++) {
                    if (tRcmTasks.get(i).getcTaskCode().equals(tRcmTasksHashValue.get(j).getcTaskCode())) {
                        cloneObject(tRcmTask,tRcmTasksHashValue);
                    }
                    k++;
                }

               if(k==tRcmTasksHashValue.size()){
                   tRcmTasksHashValue.add(tRcmTasks.get(i));

               }
            }
        }
        //删除了job实例
        if(tRcmTasks.size()<tRcmTasksHashValue.size()){
            int k=0;
            for(int i=0;i<tRcmTasksHashValue.size();i++){
                for(int j=0;j<tRcmTasks.size();j++) {
                    if (tRcmTasks.get(i).getcTaskCode().equals(tRcmTasksHashValue.get(j).getcTaskCode())) {
                        cloneObject(tRcmTask,tRcmTasksHashValue);
                    }
                    k++;
                }
                if(k==tRcmTasksHashValue.size()){
                    tRcmTasksHashValue.remove(i);
                }
            }
        }
        return tRcmTask;
    }

    private Integer cloneObject(TRcmTask tRcmTask,List<TRcmTask> tRcmTasksHashValue){
        Iterator<TRcmTask> iterator = tRcmTasksHashValue.iterator();
        obchange=0;
        while(iterator.hasNext()){
            TRcmTask hashTRcmTask = iterator.next();
            if (hashTRcmTask.getcTaskCode().equals(tRcmTask.getcTaskCode())){
                if(!hashTRcmTask.getcTaskName().equals(tRcmTask.getcTaskName())){
                    hashTRcmTask.setcTaskName(tRcmTask.getcTaskName());
                    obchange++;
                }
                if(!hashTRcmTask.getcInvokeMethod().equals(tRcmTask.getcInvokeMethod())){
                    hashTRcmTask.setcInvokeMethod(tRcmTask.getcInvokeMethod());
                    obchange++;
                }
                if(!hashTRcmTask.getdStartDate().equals(tRcmTask.getdStartDate())){
                    hashTRcmTask.setdStartDate(tRcmTask.getdStartDate());
                    obchange++;
                }
                if(!hashTRcmTask.getdEndDate().equals(tRcmTask.getdEndDate())){
                    hashTRcmTask.setdEndDate(tRcmTask.getdEndDate());
                    obchange++;
                }
                if(!hashTRcmTask.getdMonitorTime().equals(tRcmTask.getdMonitorTime())){
                    hashTRcmTask.setdMonitorTime(tRcmTask.getdMonitorTime());
                    obchange++;
                }
                if(!hashTRcmTask.getcInterval().equals(tRcmTask.getcInterval())){
                    hashTRcmTask.setcInterval(tRcmTask.getcInterval());
                    obchange++;
                }
            }
        }
        return obchange;
    }
    private RegistryCenterConfiguration changeTRcmTaskToConfig(TRcmTask tRcmTask){
        RegistryCenterConfiguration registryCenterConfiguration = new RegistryCenterConfiguration();
        registryCenterConfiguration.setZkAddressList(RegistryCenterConstants.zkAddressList);
        registryCenterConfiguration.setNamespace(RegistryCenterConstants.namespace);
        registryCenterConfiguration.setShareTotalCount(RegistryCenterConstants.shareTotalCount);
        registryCenterConfiguration.setShardingItemParameters(RegistryCenterConstants.shardingItemParameters);
        registryCenterConfiguration.setJobName(tRcmTask.getcTaskName());
        registryCenterConfiguration.setJobClassName(tRcmTask.getcTaskName());
        //后期补充通用算法
        String cron = getCron(tRcmTask.getcInterval());
        registryCenterConfiguration.setJobParameter(cron);
        return registryCenterConfiguration;
    }
    private static  String getCron(String Interval){
        if(Interval.length()==0 ||Interval==null){
            return "0/0 * * * * ?";
        }
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
        Double mtest = Double.valueOf(Interval);
        for(int t=1;t<10;t++){
            System.out.println(mtest);
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
