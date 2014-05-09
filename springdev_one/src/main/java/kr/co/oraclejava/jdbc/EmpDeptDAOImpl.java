package kr.co.oraclejava.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("springjdbc")
public class EmpDeptDAOImpl implements EmpDeptDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public int getTotalSal() {
		return jdbcTemplate.queryForInt("SELECT sum(sal) AS sum_sal FROM t_emp"); 
	}

	@Override
	public List<EmpVO> getEmpList() {
		RowMapper rowMapper = new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				DeptVO deptVO = new DeptVO();
				deptVO.setDeptno(rs.getInt("deptno"));
				deptVO.setDname(rs.getString("dname"));
				EmpVO empVO = new EmpVO();
				empVO.setDeptVO(deptVO);
				empVO.setEmpno(rs.getInt("empno"));
				empVO.setEname(rs.getString("ename"));
				empVO.setSal(rs.getInt("sal"));
				
				return empVO;
			}
		};
		//클래스 안에 클래스로 만든다.
		//구현한 클래스를 다시 맵핑한다.
		//일회용으로 인스턴스를 생성한다.
		String sql = "SELECT	e.empno, e.ename, e.sal, d.deptno, d.dname " +
						"FROM 	t_emp e join t_dept d " +
						"on		e.deptno = d.deptno ";	
		
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	@Override
	public List<DeptVO> getDeptList() {
		RowMapper rowMapper = new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				DeptVO deptVO = new DeptVO();
				deptVO.setDeptno(rs.getInt("deptno"));
				deptVO.setDname(rs.getString("dname"));
				
				return deptVO;
			}
		};
		
		String sql = "SELECT deptno, dname " +
					 "FROM 	t_dept " +
					 "ORDER BY deptno desc";
		return jdbcTemplate.query(sql, rowMapper);
	}
	
	@Override
	public void empInsert(EmpVO vo) {
		
		String sql = "INSERT INTO t_emp(empno, ename, sal, deptno) " +
				 	 "VALUES (seq_emp.nextval, ?, ?, ?) ";
		Object[] args = {vo.getEname(), vo.getSal(), vo.getDeptVO().getDeptno()};
		jdbcTemplate.update(sql, args);
	//merge 기존에 있으면 수정, 없으면 등록 또는 삭제를 해주는 sql 명령문.
	}
	
	@Override
	public EmpVO getEmp(int empno) {
		String sql = 
				"SELECT empno, ename, sal, deptno " + 
						"FROM t_emp " + 
						"WHERE empno = ? ";
		Object[] arg = {empno};
		
		RowMapper rowMapper = new RowMapper() {

			@Override
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				DeptVO deptVO = new DeptVO();
				deptVO.setDeptno(rs.getInt("deptno"));
			
				EmpVO empVO = new EmpVO();
				empVO.setDeptVO(deptVO);
				empVO.setEmpno(rs.getInt("empno"));
				empVO.setEname(rs.getString("ename"));
				empVO.setSal(rs.getInt("sal"));
				
				return empVO;
			}
		};
		
		return jdbcTemplate.queryForObject(sql, arg, rowMapper);
	}
	
	@Override
	public void updateEmp(EmpVO vo) {
		String sql = "UPDATE t_emp " +
					 "SET ename=?, sal=?, deptno=? " +
			 	 "WHERE empno=? ";
	Object[] args = {
			vo.getEname(), 
			vo.getSal(), 
			vo.getDeptVO().getDeptno(),
			vo.getEmpno()
			};
		if (jdbcTemplate.update(sql, args) == 0){
			throw new RuntimeException("수정 실패");
		}
		
	}
	@Override
	public void deleteEmp(int empno) {
		String sql = "DELETE " +
					 "FROM t_emp " +
					 "WHERE empno=? ";
		
		Object[] arg = {empno};
		
		if (jdbcTemplate.update(sql, arg) == 0){
			throw new RuntimeException("삭제 실패");
		}
	}
}