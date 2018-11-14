package rekr.rest_work.loan;

import java.util.Calendar;
import java.util.GregorianCalendar;

import rekr.rest_work.IConstantLoan;
import rekr.rest_work.loan.dto.LoanDateDTO;

public class LoanImp implements ILoan {

	@Override
	public String applyForLoan(LoanDateDTO jsonOnj) {
		if (jsonOnj != null
				&& (jsonOnj.getAmount() > IConstantLoan.maxAmount || jsonOnj.getTerm() > IConstantLoan.maxTerm)) {
			return IConstantLoan.NO1;
		}
		Calendar calendar = GregorianCalendar.getInstance();
		calendar.setTime(jsonOnj.getStartDate());
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		if (hour > 0 && hour < 6 || jsonOnj.getAmount() > IConstantLoan.maxAmount) {
			return IConstantLoan.NO2;
		}
		jsonOnj.setGivenAmount(new Double(jsonOnj.getAmount() * 0.1));
		return IConstantLoan.OK;
	}

	@Override
	public String extendLoan(LoanDateDTO jsonObj) {
		LoanDateDTO jsonObjNew = jsonObj;
		Calendar c = Calendar.getInstance();
		c.add(Calendar.MONTH, jsonObj.getExtTerm());
		jsonObjNew.setEndDate(c.getTime());
		return IConstantLoan.OK;
	}

}
