package com.example.elasticjob.repositroy;

import com.example.elasticjob.entity.TRcmTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * Description:
 * User: K0370089
 * Date: 2018-11-23
 * Time: 16:52
 */
@Repository
public interface TRcmTaskRepository extends JpaSpecificationExecutor<TRcmTask>, JpaRepository<TRcmTask, Long> {
    @Query(value = "select t from TRcmTask t where t.cTaskCode = :taskId ")
    List<TRcmTask> getTRcmTasksById(@Param("taskId") String taskId);

    @Query(value = "select t from TRcmTask t where t.dCreateDate <?1 and t.dEndDate >=?2")
    List<TRcmTask> findValidateTask(Date date);

}
