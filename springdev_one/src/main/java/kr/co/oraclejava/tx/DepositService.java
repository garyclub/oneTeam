package kr.co.oraclejava.tx;

public interface DepositService {

	int getCash() throws Exception;

	int getBank() throws Exception;

	void deposit() throws Exception;

}
