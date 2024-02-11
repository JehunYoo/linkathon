package com.link.back.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.link.back.entity.BackPerformanceMessage;

public interface BackPerformanceMessageRepository extends JpaRepository<BackPerformanceMessage,Long> {

	@Query(value = "SELECT " +
		"SUM(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(message, ' ', 1), ':', -1) = 'Add' THEN 1 ELSE 0 END) AS AddCount, " +
		"SUM(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(message, ' ', 1), ':', -1) = 'Make' THEN 1 ELSE 0 END) AS MakeCount, " +
		"SUM(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(message, ' ', 1), ':', -1) = 'Remove' THEN 1 ELSE 0 END) AS RemoveCount, " +
		"SUM(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(message, ' ', 1), ':', -1) = 'Complete' THEN 1 ELSE 0 END) AS CompleteCount, " +
		"SUM(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(message, ' ', 1), ':', -1) = 'Change' THEN 1 ELSE 0 END) AS ChangeCount, " +
		"SUM(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(message, ' ', 1), ':', -1) = 'Merge' THEN 1 ELSE 0 END) AS MergeCount, " +
		"SUM(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(message, ' ', 1), ':', -1) = 'Refactor' THEN 1 ELSE 0 END) AS RefactorCount, " +
		"SUM(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(message, ' ', 1), ':', -1) = 'Move' THEN 1 ELSE 0 END) AS MoveCount, " +
		"SUM(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(message, ' ', 1), ':', -1) = 'Replace' THEN 1 ELSE 0 END) AS ReplaceCount, " +
		"SUM(CASE WHEN SUBSTRING_INDEX(SUBSTRING_INDEX(message, ' ', 1), ':', -1) NOT IN ('Add', 'Make', 'Remove', 'Complete', 'Change', 'Merge', 'Refactor', 'Move', 'Replace') THEN 1 ELSE 0 END) AS EtcCount " +
		"FROM back_performance_message bpm JOIN back_performance bp ON bpm.back_performance_id = bp.back_performance_id " +
		"WHERE bp.project_id = :projectId", nativeQuery = true)
	public Map<String,Integer> countBackPerformanceMessageByMessageStartsWith(Long projectId);

}
