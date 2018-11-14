package rekr.rest_work.loan;

import rekr.rest_work.loan.dto.LoanDateDTO;

public interface ILoan {
	/**
	 * Apply for loan
	 * 
	 * @param jsonOnj
	 * @return
	 */
	public String applyForLoan(LoanDateDTO jsonOnj);

	/**
	 * Extend loan
	 * 
	 * @param jsonObj
	 * @return
	 */
	public String extendLoan(LoanDateDTO jsonObj);
}
