package book.ch5;

import com.vo.DeptVO;
import com.vo.EmpVO;
//import: 패키지가 달라서 발생하는 오류에 대한 처리
public class EmpList {
	//parameter와 return에 대해서 주석 다는 것을 원칙으로 해야 함.
	/***********************************************************************
	 * 
	 * @param empno - 사원번호
	 * @return String[] - 사원이름, 부서명
	 */
	public String[] getEmpDetail(int empno) {
		String[] info = new String[2];
		EmpVO eVO = new EmpVO();
		eVO.setEmpno(7566);
		String ename = eVO.getEname();
		info[0] = ename;
		DeptVO dVO = new DeptVO();
		int deptno = eVO.getDeptno();
		dVO.setDeptno(deptno);	//emp테이블에서 얻어온 7566 사원에 대한 부서번호이다.
		String dname = dVO.getDname();
		eVO.setDeptno(30);
		info[1] = dname;
		return info;
	}
	public static void main(String[] args) {
		
		/*
		EmpVO eVO = new EmpVO();
		eVO = new EmpVO();
		eVO = new EmpVO();
		eVO = new EmpVO();
		*/
	}

}
