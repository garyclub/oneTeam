package kr.co.oraclejava.tx;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DepositServiceImpl implements DepositService{
	
	@Autowired
	private DepositDAO dao;
	
	@Override
	public int getCash() throws Exception {
		return dao.getCash();
	}

	@Override
	public int getBank() throws Exception {
		return dao.getBank();
	}

	@Override
	@Transactional
	public void deposit() throws Exception {
		dao.updateCash();
		dao.updateBank();
	}
	
	
}
