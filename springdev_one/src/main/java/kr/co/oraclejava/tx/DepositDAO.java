package kr.co.oraclejava.tx;

public interface DepositDAO {
	int getCash() throws Exception;

	int getBank() throws Exception;

	void updateCash() throws Exception;

	void updateBank() throws Exception;

}
