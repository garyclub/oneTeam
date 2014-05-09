package kr.co.oraclejava.jdbc;

import java.util.List;

public interface EmpDeptDAO {

	int getTotalSal();

	List<EmpVO> getEmpList();

	List<DeptVO> getDeptList();

	void empInsert(EmpVO vo);

	EmpVO getEmp(int empno);

	void updateEmp(EmpVO vo);

	void deleteEmp(int no);
	
}
