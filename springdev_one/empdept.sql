
/* Drop Tables */

DROP TABLE t_emp CASCADE CONSTRAINTS;
DROP TABLE t_dept CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE seq_emp;
DROP SEQUENCE seq_dept;




/* Create Sequences */

CREATE SEQUENCE seq_emp INCREMENT BY 1 START WITH 1;
CREATE SEQUENCE seq_dept INCREMENT BY 10 START WITH 10;



/* Create Tables */

CREATE TABLE t_emp
(
	empno number NOT NULL,
	ename varchar2(20) NOT NULL,
	sal number DEFAULT 0 NOT NULL,
	deptno number NOT NULL,
	PRIMARY KEY (empno)
);


CREATE TABLE t_dept
(
	deptno number NOT NULL,
	dname varchar2(20) NOT NULL,
	PRIMARY KEY (deptno)
);



/* Create Foreign Keys */

ALTER TABLE t_emp
	ADD FOREIGN KEY (deptno)
	REFERENCES t_dept (deptno)
;

select * from t_emp;
select * from t_dept;

DELETE FROM t_emp WHERE ename='이경민';
UPDATE t_emp SET deptno='40' WHERE deptno='210'; 
SELECT	e.empno, e.ename, e.sal, d.deptno, d.dname
FROM 	t_emp e join t_dept d
on		e.deptno = d.deptno;

