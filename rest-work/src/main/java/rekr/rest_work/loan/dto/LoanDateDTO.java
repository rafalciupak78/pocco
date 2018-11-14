package rekr.rest_work.loan.dto;

import java.io.Serializable;
import java.util.Date;

public class LoanDateDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long amount;
	private Integer term;
	private Date startDate;
	private Date endDate;
	private Integer extTerm;
    private Double givenAmount;
	public Integer getExtTerm() {
		return extTerm;
	}

	public Double getGivenAmount() {
		return givenAmount;
	}

	public void setGivenAmount(Double givenAmoumt) {
		this.givenAmount = givenAmoumt;
	}

	public void setExtTerm(Integer extTerm) {
		this.extTerm = extTerm;
	}

	public Long getAmount() {
		return amount;
	}

	public void setAmount(Long amount) {
		this.amount = amount;
	}

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
		 

}
