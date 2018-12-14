package com.example.elasticjob.config;
import java.io.Serializable;
import lombok.Data;
/**
 * 注册中心配置.
 *
 * @author liushu
 */
@Data
public final class RegistryCenterConfiguration implements Serializable {

    private static final long serialVersionUID = -5996257770767863699L;
    private  String zkAddressList;
    private  String namespace;
    private  String jobName;
    private  String jobParameter;
    private  Integer shareTotalCount;
    private  String shardingItemParameters;
    private  String jobClassName;
    private  String keyflag;

}

