package kr.co.oraclejava.jdbc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.orm.ibatis.SqlMapClientTemplate;
import org.springframework.stereotype.Repository;

@Repository("ibatis")
public class EmpDeptDAOibatis implements EmpDeptDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SqlMapClientTemplate sqlMap;
	
	@Override
	public int getTotalSal() {
		return (Integer) sqlMap.queryForObject("empdept.getTotalSal"); 
	}

	@Override
	public List<EmpVO> getEmpList() {
		return sqlMap.queryForList("empdept.getEmpList");
	}
	
	@Override
	public List<DeptVO> getDeptList() {
		return sqlMap.queryForList("empdept.getDeptList");
	}
	
	@Override
	public void empInsert(EmpVO vo) {
		sqlMap.insert("empdept.empInsert", vo);
	}
	
	@Override
	public EmpVO getEmp(int empno) {
		return (EmpVO) sqlMap.queryForObject("empdept.getEmp", empno);
	}
	
	@Override
	public void updateEmp(EmpVO vo) {

		if (sqlMap.update("empdept.updateEmp", vo) == 0){
			throw new RuntimeException("수정 실패");
		}
		
	}
	@Override
	public void deleteEmp(int empno) {

		if (sqlMap.delete("empdept.deleteEmp", empno) == 0){
			throw new RuntimeException("삭제 실패");
		}
	}
}