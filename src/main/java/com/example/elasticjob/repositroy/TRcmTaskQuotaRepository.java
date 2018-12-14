package com.example.elasticjob.repositroy;
import com.example.elasticjob.entity.TRcmTaskQuota;
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
 * Time: 16:54
 */
@Repository
public interface TRcmTaskQuotaRepository extends JpaSpecificationExecutor<TRcmTaskQuota>, JpaRepository<TRcmTaskQuota, Long> {
    @Modifying
    @Query(value = "DELETE FROM TRcmTaskQuota T WHERE T.cTaskCode = :cTaskCode")
    void deleteTRcmTaskQuotaByCTaskCode(@Param("cTaskCode") String cTaskCode);

    @Modifying
    @Query(value = "DELETE FROM TRcmTaskQuota T WHERE T.cTaskCode = :cTaskCode AND T.cIndexCode = :cIndexCode")
    void deleteByCTaskCodeAndCIndexCode(@Param("cTaskCode") String cTaskCode, @Param("cIndexCode") String cIndexCode);

    @Modifying
    @Query(value = "UPDATE TRcmTaskQuota T SET T.nEnable = :nEnable WHERE T.cTaskCode = :cTaskCode AND T.cIndexCode = :cIndexCode")
    void setEnableorDisAble(@Param("nEnable") boolean nEnable, @Param("cTaskCode") String cTaskCode, @Param("cIndexCode") String cIndexCode);
}
