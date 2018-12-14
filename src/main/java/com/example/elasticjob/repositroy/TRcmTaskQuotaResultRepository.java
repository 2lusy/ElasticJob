package com.example.elasticjob.repositroy;

import com.example.elasticjob.entity.TRcmTaskQuotaResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: K0370089
 * Date: 2018-11-23
 * Time: 16:57
 */
@Repository
public interface TRcmTaskQuotaResultRepository extends JpaSpecificationExecutor<TRcmTaskQuotaResult>, JpaRepository<TRcmTaskQuotaResult, Long> {
    @Modifying
    @Query(value = "DELETE FROM TRcmTaskQuotaResult T WHERE T.cTaskCode = :cTaskCode")
    void deleteByCTaskCode(@Param("cTaskCode") String cTaskCode);

    @Modifying
    @Query(value = "DELETE FROM TRcmTaskQuotaResult T WHERE T.cTaskCode = :cTaskCode AND T.cIndexCode = :cIndexCode")
    void deleteByCTaskCodeAndCIndexCode(@Param("cTaskCode") String cTaskCode, @Param("cIndexCode") String cIndexCode);

    @Modifying
    @Query(value = "UPDATE TRcmTaskQuotaResult T SET T.cParamValue = :cParamValue where T.cTaskCode = :cTaskCode AND T.cIndexCode = :cIndexCode AND T.cParamCode = :cParamCode")
    void modifyByTaskCodeAndIndexCodeAndParamCode(@Param("cParamValue") String cParamValue, @Param("cTaskCode") String cTaskCode, @Param("cIndexCode") String cIndexCode, @Param("cParamCode") String cParamCode);
}
