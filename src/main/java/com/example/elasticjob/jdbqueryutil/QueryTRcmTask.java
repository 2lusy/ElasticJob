package com.example.elasticjob.jdbqueryutil;

import com.example.elasticjob.entity.TRcmTask;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Auther: K0370098
 * @Date: 2018/12/13 11:27
 * @Description:
 */
public class QueryTRcmTask {
    public static List<TRcmTask> queryTRcmTask(){
        Connection conn = null;
        ResultSet resultSet = null;
        List<TRcmTask> tRcmTasks= new ArrayList<TRcmTask>();
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver").newInstance();
            conn = (Connection) DriverManager.getConnection("jdbc:oracle:thin:@168.61.40.11:1521:tstgz", "fa_dev", "1");
            PreparedStatement preparedStatement = conn.prepareStatement("SELECT *FROM T_RCM_TASK");
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                TRcmTask tRcmTask = new TRcmTask();
                tRcmTask.setnId(resultSet.getLong("N_ID"));
                tRcmTask.setcTaskCode(resultSet.getString("C_TASK_CODE"));
                tRcmTask.setcTaskName(resultSet.getString("C_TASK_NAME"));
                tRcmTask.setcInvokeMethod(resultSet.getString("C_INVOKE_METHOD"));
                tRcmTask.setdStartDate(resultSet.getTimestamp("D_START_DATE"));
                tRcmTask.setdEndDate(resultSet.getTimestamp("D_END_DATE"));
                tRcmTask.setcCreateUser(resultSet.getString("C_CREATE_USER"));
                tRcmTask.setdCreateDate(resultSet.getDate("D_CREATE_DATE"));
                tRcmTask.setdMonitorTime(resultSet.getTimestamp("D_MONITOR_TIME"));
                tRcmTask.setcUpdateUser(resultSet.getString("C_UPDATE_USER"));
                tRcmTask.setdUpdateDate(resultSet.getDate("D_UPDATE_DATE"));
                tRcmTask.setcInterval(resultSet.getString("C_INTERVAL"));
                tRcmTasks.add(tRcmTask);
            }
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tRcmTasks;
    }
}
