package com.example.elasticjob.repositroy;


import com.example.elasticjob.entity.TRcmTaskQuotaParam;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: K0370089
 * Date: 2018-11-23
 * Time: 16:56
 */
@Repository
public interface TRcmTaskQuotaParamRespository extends JpaSpecificationExecutor<TRcmTaskQuotaParam>, JpaRepository<TRcmTaskQuotaParam, Long> {
    @Query(value = "SELECT T FROM TRcmTaskQuotaParam  T WHERE T.cIndexCode = :cIndexCode ")
    List<TRcmTaskQuotaParam> getAllByCIndexCode(@Param("cIndexCode") String cIndexCode);
}
