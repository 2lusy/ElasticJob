package com.example.elasticjob.entity;

import com.google.common.base.Objects;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created with IntelliJ IDEA.
 * Description:风险监控任务表
 * User: K0370089
 * Date: 2018-11-23
 * Time: 10:07
 */
@Entity
@Table(name = "T_RCM_TASK")
@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
public class TRcmTask {
    public static final String TABLE_NAME = "T_RCM_TASK";
    /**
     * 主键id
     */
    private Long nId;

    /**
     * 任务code
     */
    private String cTaskCode;

    /**
     * 任务名称
     */
    private String cTaskName;

    /**
     * 触发方式
     */
    private String cInvokeMethod;

    /**
     * 任务生效时间
     */
    private Timestamp dStartDate;

    /**
     * 任务结束时间
     */
    private Timestamp dEndDate;

    /**
     * 创建人
     */
    private String cCreateUser;

    /**
     * 创建日期
     */
    private Date dCreateDate;

    /**
     * 任务监控时间
     */
    private Timestamp dMonitorTime;

    /**
     * 更新人
     */
    private String cUpdateUser;

    /**
     * 更新日期
     */
    private Date dUpdateDate;

    /**
     * 定时间隔
     */
    private String cInterval;

    @Id
    @Column(name = "N_ID", nullable = false, precision = 0)
    public long getnId() {
        return nId;
    }

    public void setnId(long nId) {
        this.nId = nId;
    }

    @Basic
    @Column(name = "C_TASK_CODE", nullable = false, length = 50)
    public String getcTaskCode() {
        return cTaskCode;
    }

    public void setcTaskCode(String cTaskCode) {
        this.cTaskCode = cTaskCode;
    }

    @Basic
    @Column(name = "C_TASK_NAME", nullable = false, length = 50)
    public String getcTaskName() {
        return cTaskName;
    }

    public void setcTaskName(String cTaskName) {
        this.cTaskName = cTaskName;
    }

    @Basic
    @Column(name = "C_INVOKE_METHOD", nullable = false, length = 20)
    public String getcInvokeMethod() {
        return cInvokeMethod;
    }

    public void setcInvokeMethod(String cInvokeMethod) {
        this.cInvokeMethod = cInvokeMethod;
    }

    @Basic
    @Column(name = "D_START_DATE", nullable = false)
    public Timestamp getdStartDate() {
        return dStartDate;
    }

    public void setdStartDate(Timestamp dStartDate) {
        this.dStartDate = dStartDate;
    }

    @Basic
    @Column(name = "D_END_DATE", nullable = false)
    public Timestamp getdEndDate() {
        return dEndDate;
    }

    public void setdEndDate(Timestamp dEndDate) {
        this.dEndDate = dEndDate;
    }

    @Basic
    @Column(name = "C_CREATE_USER", nullable = true, length = 20)
    public String getcCreateUser() {
        return cCreateUser;
    }

    public void setcCreateUser(String cCreateUser) {
        this.cCreateUser = cCreateUser;
    }

    @Basic
    @Column(name = "D_CREATE_DATE", nullable = true)
    public Date getdCreateDate() {
        return dCreateDate;
    }

    public void setdCreateDate(Date dCreateDate) {
        this.dCreateDate = dCreateDate;
    }

    @Basic
    @Column(name = "D_MONITOR_TIME", nullable = false)
    public Timestamp getdMonitorTime() {
        return dMonitorTime;
    }

    public void setdMonitorTime(Timestamp dMonitorTime) {
        this.dMonitorTime = dMonitorTime;
    }

    @Basic
    @Column(name = "C_UPDATE_USER", nullable = true, length = 20)
    public String getcUpdateUser() {
        return cUpdateUser;
    }

    public void setcUpdateUser(String cUpdateUser) {
        this.cUpdateUser = cUpdateUser;
    }

    @Basic
    @Column(name = "D_UPDATE_DATE", nullable = true)
    public Date getdUpdateDate() {
        return dUpdateDate;
    }

    public void setdUpdateDate(Date dUpdateDate) {
        this.dUpdateDate = dUpdateDate;
    }

    @Basic
    @Column(name = "C_INTERVAL", nullable = false, length = 20)
    public String getcInterval() {
        return cInterval;
    }

    public void setcInterval(String cInterval) {
        this.cInterval = cInterval;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TRcmTask that = (TRcmTask) o;

        return Objects.equal(this.nId, that.nId) &&
                Objects.equal(this.cTaskCode, that.cTaskCode) &&
                Objects.equal(this.cTaskName, that.cTaskName) &&
                Objects.equal(this.cInvokeMethod, that.cInvokeMethod) &&
                Objects.equal(this.dStartDate, that.dStartDate) &&
                Objects.equal(this.dEndDate, that.dEndDate) &&
                Objects.equal(this.cCreateUser, that.cCreateUser) &&
                Objects.equal(this.dCreateDate, that.dCreateDate) &&
                Objects.equal(this.dMonitorTime, that.dMonitorTime) &&
                Objects.equal(this.cUpdateUser, that.cUpdateUser) &&
                Objects.equal(this.dUpdateDate, that.dUpdateDate) &&
                Objects.equal(this.cInterval, that.cInterval);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nId, cTaskCode, cTaskName, cInvokeMethod, dStartDate, dEndDate,
                cCreateUser, dCreateDate, dMonitorTime, cUpdateUser, dUpdateDate,
                cInterval);
    }
}
