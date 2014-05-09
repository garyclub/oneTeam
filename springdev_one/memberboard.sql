
/* Drop Tables */

DROP TABLE tab_comment CASCADE CONSTRAINTS;
DROP TABLE tab_board CASCADE CONSTRAINTS;
DROP TABLE tab_board_info CASCADE CONSTRAINTS;
DROP TABLE tab_user CASCADE CONSTRAINTS;



/* Drop Sequences */

DROP SEQUENCE seq_board_info;
DROP SEQUENCE seq_board;
DROP SEQUENCE seq_comment;




/* Create Sequences */

CREATE SEQUENCE seq_board_info;
CREATE SEQUENCE seq_board;
CREATE SEQUENCE seq_comment;



/* Create Tables */

CREATE TABLE tab_comment
(
	cno number NOT NULL,
	no number NOT NULL,
	bno number NOT NULL,
	user_id varchar2(20) NOT NULL,
	bcomment varchar2(300) NOT NULL,
	regdate date DEFAULT sysdate NOT NULL,
	PRIMARY KEY (cno)
);


CREATE TABLE tab_board_info
(
	bno number NOT NULL,
	bname varchar2(100) NOT NULL,
	user_id varchar2(20) NOT NULL,
	PRIMARY KEY (bno)
);


CREATE TABLE tab_user
(
	user_id varchar2(20) NOT NULL,
	user_name varchar2(20) NOT NULL,
	user_pwd varchar2(100) NOT NULL,
	user_pwd_hint varchar2(200) NOT NULL,
	user_pwd_answer varchar2(100) NOT NULL,
	user_gender number(1) DEFAULT 1 NOT NULL,
	user_email varchar2(60),
	user_phone varchar2(16),
	user_zipcode varchar2(7) NOT NULL,
	user_addr1 varchar2(100) NOT NULL,
	user_addr2 varchar2(50) NOT NULL,
	user_regdate date DEFAULT sysdate NOT NULL,
	user_lastip varchar2(30),
	user_level number(3) DEFAULT 0 NOT NULL,
	user_status number(1) DEFAULT 0 NOT NULL,
	PRIMARY KEY (user_id)
);


CREATE TABLE tab_board
(
	no number NOT NULL,
	title varchar2(100) NOT NULL,
	content varchar2(4000) NOT NULL,
	regdate date DEFAULT sysdate NOT NULL,
	count number DEFAULT 0 NOT NULL,
	good number DEFAULT 0 NOT NULL,
	bad number DEFAULT 0 NOT NULL,
	user_id varchar2(20) NOT NULL,
	bno number NOT NULL,
	PRIMARY KEY (no)
);



/* Create Foreign Keys */

ALTER TABLE tab_comment
	ADD FOREIGN KEY (bno)
	REFERENCES tab_board_info (bno)
;


ALTER TABLE tab_board
	ADD FOREIGN KEY (bno)
	REFERENCES tab_board_info (bno)
;


ALTER TABLE tab_comment
	ADD FOREIGN KEY (user_id)
	REFERENCES tab_user (user_id)
;


ALTER TABLE tab_board_info
	ADD FOREIGN KEY (user_id)
	REFERENCES tab_user (user_id)
;


ALTER TABLE tab_board
	ADD FOREIGN KEY (user_id)
	REFERENCES tab_user (user_id)
;


ALTER TABLE tab_comment
	ADD FOREIGN KEY (no)
	REFERENCES tab_board (no)
;

select * from tab_user;
select * from tab_comment;
delete from Tab_USER where user_id='aaaaaaaa';
delete from Tab_COMMENT where user_id='taiji';
insert into tab_board_info(bno, bname, user_id)
values(seq_board_info.nextval, '자유 게시판', 'taiji');

insert into tab_board_info(bno, bname, user_id)
values(seq_board_info.nextval, '공지사항', 'taiji');
--글번호, 제목, 이름, 아이디, 날짜, 조회수
--조건:bno = 1

SELECT	b.no, b.title, u.user_name, b.user_id, b.regdate, b.count
FROM TAB_BOARD b join tab_user u
ON	b.user_id = u.user_id 
AND b.bno = '1';

SELECT	b.no, b.title, u.user_name, b.user_id, b.regdate, b.count, b.content, b.good, b.bad
FROM TAB_BOARD b join tab_user u
ON	b.user_id = u.user_id 
WHERE b.no = 2
ORDER BY b.no DESC;