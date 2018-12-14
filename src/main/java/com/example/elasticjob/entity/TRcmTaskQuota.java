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
@Table(name = "T_RCM_TASK_QUOTA")
public class TRcmTaskQuota {
    public static final String TABLE_NAME = "T_RCM_TASK_QUOTA";
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
     * 创建人
     */
    private String cCreateUser;

    /**
     * 创建时间
     */
    private Date dCreateDate;

    /**
     * 是否启用
     */
    private boolean nEnable;

    /**
     * 更新日期
     */
    private String dUpdateDate;

    /**
     * 更新人
     */
    private String cUpdateUser;

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
    @Column(name = "C_CREATE_USER", nullable = true, length = 50)
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
    @Column(name = "N_ENABLE", nullable = false, precision = 0)
    public boolean isnEnable() {
        return nEnable;
    }

    public void setnEnable(boolean nEnable) {
        this.nEnable = nEnable;
    }

    @Basic
    @Column(name = "D_UPDATE_DATE", nullable = true, length = 20)
    public String getdUpdateDate() {
        return dUpdateDate;
    }

    public void setdUpdateDate(String dUpdateDate) {
        this.dUpdateDate = dUpdateDate;
    }

    @Basic
    @Column(name = "C_UPDATE_USER", nullable = true, length = 20)
    public String getcUpdateUser() {
        return cUpdateUser;
    }

    public void setcUpdateUser(String cUpdateUser) {
        this.cUpdateUser = cUpdateUser;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TRcmTaskQuota that = (TRcmTaskQuota) o;

        return Objects.equal(this.nId, that.nId) &&
                Objects.equal(this.cTaskName, that.cTaskName) &&
                Objects.equal(this.cTaskCode, that.cTaskCode) &&
                Objects.equal(this.cIndexName, that.cIndexName) &&
                Objects.equal(this.cIndexCode, that.cIndexCode) &&
                Objects.equal(this.cCreateUser, that.cCreateUser) &&
                Objects.equal(this.dCreateDate, that.dCreateDate) &&
                Objects.equal(this.nEnable, that.nEnable) &&
                Objects.equal(this.dUpdateDate, that.dUpdateDate) &&
                Objects.equal(this.cUpdateUser, that.cUpdateUser);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nId, cTaskName, cTaskCode, cIndexName, cIndexCode, cCreateUser,
                dCreateDate, nEnable, dUpdateDate, cUpdateUser);
    }
}
