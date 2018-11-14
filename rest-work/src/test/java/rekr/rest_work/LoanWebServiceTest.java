/**
 * 
 */
package rekr.rest_work;

import javax.inject.Inject;
import javax.ws.rs.core.Response;

import junit.framework.TestCase;

/**
 * @author malgos
 *
 */
public class LoanWebServiceTest extends TestCase {

	private String jsonTest1 = "{\"amount\":\"100000\", \"term\":\"53\","
			+ "\"extend\":12,\"startDate\":\"2018.11.12 12:00\", \"extTerm\":\"12\" }";
	private String jsonTest2 = "{\"amount\":\"100000000\", \"term\":\"123\","
			+ "\"extend\":12,\"startDate\":\"2018.11.12 12:00\", \"extTerm\":\"0\" }";
	private String jsonTest3 = "{\"amount\":\"1000\", \"term\":\"13\","
			+ "\"extend\":12,\"startDate\":\"2018.11.12 2:00\", \"extTerm\":\"0\" }";

	/*
	 * (non-Javadoc)Fs
	 * 
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
	}
	 
	LoanWebService webService=new LoanWebService();

	/**
	 * Test method for // *
	 * {@link rekr.rest_work.LoanWebService#applyForLoan(java.lang.String)}.
	 */
	public void testApplyForLoan() {
		Response resp = webService.applyForLoan(jsonTest1);
		System.out.println("testApplyForLoan " + resp.getStatusInfo());
		System.out.println(resp.getEntity());
		Response resp1 = webService.applyForLoan(jsonTest2);
		System.out.println("testApplyForLoan " + resp1.getStatusInfo());		
		System.out.println(resp1.getEntity());
		Response resp2 = webService.applyForLoan(jsonTest3);
		System.out.println("testApplyForLoan " + resp2.getStatusInfo());
		System.out.println(resp2.getEntity());

	}

	/**
	 * ó Test method for
	 * {@link rekr.rest_work.LoanWebService#extendLoan(java.lang.String)}.
	 */
	public void testExtendLoan() {
		Response resp = webService.extendLoan(jsonTest1);
		System.out.println("testExtendLoan " + resp.getStatusInfo());
		System.out.println(resp.getEntity());
	}

}
