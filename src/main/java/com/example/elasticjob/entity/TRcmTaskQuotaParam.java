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
@Table(name = "T_RCM_TASK_QUOTA_PARAM")
public class TRcmTaskQuotaParam {
    public static final String TABLE_NAME = "T_RCM_TASK_QUOTA_PARAM";
    /**
     * 主键id
     */
    private Long nId;

    /**
     * 指标名称
     */
    private String cIndexName;

    /**
     * 指标code
     */
    private String cIndexCode;

    /**
     * 指标类型(下拉框，文字，时间，数值)
     */
    private String cIndexType;

    /**
     * 是否必输
     */
    private String cIsInput;

    /**
     * 是否依赖输入（只有产品才会依赖输入）
     */
    private String cIsDepedentInput;

    /**
     * 参数code
     */
    private String cParamCode;

    /**
     * 参数名称
     */
    private String cParamName;

    /**
     * 下拉框（产品表，选择项）
     */
    private String cSelectitem;

    /**
     * 数值区间(-1,1)
     */
    private String nFluctuationThreshold;

    /**
     * 文字长度
     */
    private Integer cTextLength;

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
    @Column(name = "C_INDEX_TYPE", nullable = false, length = 20)
    public String getcIndexType() {
        return cIndexType;
    }

    public void setcIndexType(String cIndexType) {
        this.cIndexType = cIndexType;
    }

    @Basic
    @Column(name = "C_IS_INPUT", nullable = false, length = 20)
    public String getcIsInput() {
        return cIsInput;
    }

    public void setcIsInput(String cIsInput) {
        this.cIsInput = cIsInput;
    }

    @Basic
    @Column(name = "C_IS_DEPEDENT_INPUT", nullable = false, length = 20)
    public String getcIsDepedentInput() {
        return cIsDepedentInput;
    }

    public void setcIsDepedentInput(String cIsDepedentInput) {
        this.cIsDepedentInput = cIsDepedentInput;
    }

    @Basic
    @Column(name = "C_PARAM_CODE", nullable = false, length = 20)
    public String getcParamCode() {
        return cParamCode;
    }

    public void setcParamCode(String cParamCode) {
        this.cParamCode = cParamCode;
    }

    @Basic
    @Column(name = "C_PARAM_NAME", nullable = false, length = 50)
    public String getcParamName() {
        return cParamName;
    }

    public void setcParamName(String cParamName) {
        this.cParamName = cParamName;
    }

    @Basic
    @Column(name = "C_SELECTITEM", nullable = false, length = 50)
    public String getcSelectitem() {
        return cSelectitem;
    }

    public void setcSelectitem(String cSelectitem) {
        this.cSelectitem = cSelectitem;
    }

    @Basic
    @Column(name = "N_FLUCTUATION_THRESHOLD", nullable = false, length = 20)
    public String getnFluctuationThreshold() {
        return nFluctuationThreshold;
    }

    public void setnFluctuationThreshold(String nFluctuationThreshold) {
        this.nFluctuationThreshold = nFluctuationThreshold;
    }

    @Basic
    @Column(name = "C_TEXT_LENGTH", nullable = false, precision = 0)
    public Integer getcTextLength() {
        return cTextLength;
    }

    public void setcTextLength(Integer cTextLength) {
        this.cTextLength = cTextLength;
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

        TRcmTaskQuotaParam that = (TRcmTaskQuotaParam) o;

        return Objects.equal(this.nId, that.nId) &&
                Objects.equal(this.cIndexName, that.cIndexName) &&
                Objects.equal(this.cIndexCode, that.cIndexCode) &&
                Objects.equal(this.cIndexType, that.cIndexType) &&
                Objects.equal(this.cIsInput, that.cIsInput) &&
                Objects.equal(this.cIsDepedentInput, that.cIsDepedentInput) &&
                Objects.equal(this.cParamCode, that.cParamCode) &&
                Objects.equal(this.cParamName, that.cParamName) &&
                Objects.equal(this.cSelectitem, that.cSelectitem) &&
                Objects.equal(this.nFluctuationThreshold, that.nFluctuationThreshold) &&
                Objects.equal(this.cTextLength, that.cTextLength) &&
                Objects.equal(this.cCreateUser, that.cCreateUser) &&
                Objects.equal(this.dCreateDate, that.dCreateDate) &&
                Objects.equal(this.cUpdateUser, that.cUpdateUser) &&
                Objects.equal(this.dUpdateDate, that.dUpdateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nId, cIndexName, cIndexCode, cIndexType, cIsInput, cIsDepedentInput,
                cParamCode, cParamName, cSelectitem, nFluctuationThreshold, cTextLength,
                cCreateUser, dCreateDate, cUpdateUser, dUpdateDate);
    }
}
