package com.example.elasticjob.entity;

import com.google.common.base.Objects;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: K0370089
 * Date: 2018-11-22
 * Time: 17:17
 */
@Entity
@SelectBeforeUpdate
@DynamicInsert
@DynamicUpdate
@Table(name = "T_RCM_TASK_QUOTA_RESULT")
public class TRcmTaskQuotaResult {
    public static final String TABLE_NAME = "T_RCM_TASK_QUOTA_RESULT";
    /**
     * 主键id
     */
    private Long nId;

    /**
     * 任务名称
     */
    private String cTaskName;

    /**
     * 任务code
     */
    private String cTaskCode;

    /**
     * 指标名称
     */
    private String cIndexName;

    /**
     * 指标code
     */
    private String cIndexCode;

    /**
     * 参数code
     */
    private String cParamCode;

    /**
     * 参数名称
     */
    private String cParamName;

    /**
     * 参数值
     */
    private String cParamValue;

    /**
     * 创建人
     */
    private String cCreateUser;

    /**
     * 创建日期
     */
    private Date dCreateDate;

    /**
     * 更新人
     */
    private String cUpdateUser;

    /**
     * 更新日期
     */
    private Date dUpdateDate;

    @Id
    @Column(name = "N_ID", nullable = false, precision = 0)
    public long getnId() {
        return nId;
    }

    public void setnId(long nId) {
        this.nId = nId;
    }

    @Transient
    public String getcTaskName() {
        return cTaskName;
    }

    public void setcTaskName(String cTaskName) {
        this.cTaskName = cTaskName;
    }

    @Basic
    @Column(name = "C_TASK_CODE", nullable = false, length = 20)
    public String getcTaskCode() {
        return cTaskCode;
    }

    public void setcTaskCode(String cTaskCode) {
        this.cTaskCode = cTaskCode;
    }

    @Transient
    public String getcIndexName() {
        return cIndexName;
    }

    public void setcIndexName(String cIndexName) {
        this.cIndexName = cIndexName;
    }

    @Basic
    @Column(name = "C_INDEX_CODE", nullable = false, length = 20)
    public String getcIndexCode() {
        return cIndexCode;
    }

    public void setcIndexCode(String cIndexCode) {
        this.cIndexCode = cIndexCode;
    }

    @Basic
    @Column(name = "C_PARAM_CODE", nullable = false, length = 20)
    public String getcParamCode() {
        return cParamCode;
    }

    public void setcParamCode(String cParamCode) {
        this.cParamCode = cParamCode;
    }

    @Transient
    public String getcParamName() {
        return cParamName;
    }

    public void setcParamName(String cParamName) {
        this.cParamName = cParamName;
    }

    @Basic
    @Column(name = "C_PARAM_VALUE", nullable = false, length = 50)
    public String getcParamValue() {
        return cParamValue;
    }

    public void setcParamValue(String cParamValue) {
        this.cParamValue = cParamValue;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TRcmTaskQuotaResult that = (TRcmTaskQuotaResult) o;

        return Objects.equal(this.nId, that.nId) &&
                Objects.equal(this.cTaskName, that.cTaskName) &&
                Objects.equal(this.cTaskCode, that.cTaskCode) &&
                Objects.equal(this.cIndexName, that.cIndexName) &&
                Objects.equal(this.cIndexCode, that.cIndexCode) &&
                Objects.equal(this.cParamCode, that.cParamCode) &&
                Objects.equal(this.cParamName, that.cParamName) &&
                Objects.equal(this.cParamValue, that.cParamValue) &&
                Objects.equal(this.cCreateUser, that.cCreateUser) &&
                Objects.equal(this.dCreateDate, that.dCreateDate) &&
                Objects.equal(this.cUpdateUser, that.cUpdateUser) &&
                Objects.equal(this.dUpdateDate, that.dUpdateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nId, cTaskName, cTaskCode, cIndexName, cIndexCode, cParamCode,
                cParamName, cParamValue, cCreateUser, dCreateDate, cUpdateUser,
                dUpdateDate);
    }
}
