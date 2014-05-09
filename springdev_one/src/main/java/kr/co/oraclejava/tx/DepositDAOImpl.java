package kr.co.oraclejava.tx;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DepositDAOImpl implements DepositDAO{
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int getCash() throws Exception {
		String sql = "SELECT money FROM cash WHERE name = '서태지'";
		return jdbcTemplate.queryForInt(sql);
		//return (Integer) sqlMap.queryForObject("deposit.getCash", "서태지");
	}
	
	@Override
	public int getBank() throws Exception {
		String sql = "SELECT money FROM bank	WHERE name = '서태지'";
		return jdbcTemplate.queryForInt(sql);
		//return (Integer) sqlMap.queryForObject("deposit.getBank", "서태지");
	}

	@Override
	public void updateCash() throws Exception {
		String sql = "UPDATE cash SET money = money - 10000 WHERE name = '서태지'";
		jdbcTemplate.update(sql);
		//sqlMap.update("deposit.updateCash", "서태지");
	}

	@Override
	public void updateBank() throws Exception {
		String sql = "UPDATE bank SET money = money + 10000 WHERE name = '서태지'";
		jdbcTemplate.update(sql);
		//sqlMap.update("deposit.updateBank", "서태지");	
	}

}
