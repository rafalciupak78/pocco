/**
 * 
 */
package rekr.rest_work;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import rekr.rest_work.loan.ILoan;
import rekr.rest_work.loan.LoanImp;
import rekr.rest_work.loan.dto.LoanDateDTO;

/**
 * @author rc
 *
 */
@Path("/loan")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoanWebService {

	@Inject
	ILoan loan = new LoanImp();
	final static Logger logger = Logger.getLogger(LoanWebService.class);

	/**
	 * Apply loan
	 * 
	 * @param date
	 * @return
	 */
	@POST
	@Path("{date}")
	public Response applyForLoan(@PathParam("date") String date) {
		LoanDateDTO loanDTO = prepareDTO(date);
		String ans = loan.applyForLoan(loanDTO);
		if (ans.equals(IConstantLoan.NO1) || ans.equals(IConstantLoan.NO2))
			return Response.ok(ans).entity(DTOToJSONString(loanDTO)).status(Status.NOT_ACCEPTABLE).build();
		else
			return Response.ok(ans).entity(DTOToJSONString(loanDTO)).status(Status.OK).build();
	}

	/**
	 * Extend loan
	 * 
	 * @param date
	 * @return
	 */
	@POST
	@Path("{date}")
	public Response extendLoan(@PathParam("date") String date) {
		LoanDateDTO loanDTO = prepareDTO(date);
		String ans = loan.extendLoan(loanDTO);
		return Response.ok(ans).entity(DTOToJSONString(loanDTO)).status(Status.OK).build();
	}

	private String DTOToJSONString(LoanDateDTO loanDTO) {
		JSONObject loanJS = new JSONObject();
		loanJS.put("amount", loanDTO.getAmount());
		loanJS.put("endDate", loanDTO.getEndDate());
		loanJS.put("extTerm", loanDTO.getExtTerm());
		loanJS.put("givenAmoumt", loanDTO.getGivenAmount());
		loanJS.put("startDate", loanDTO.getStartDate());
		loanJS.put("term", loanDTO.getTerm());
		return loanJS.toJSONString();

	}

	/**
	 * Prepare Loan DTO
	 * 
	 * @param date
	 * @return
	 */
	private LoanDateDTO prepareDTO(String date) {
		JSONParser parser = new JSONParser();
		Object jsonObjParse = null;
		try {
			jsonObjParse = parser.parse(date);
		} catch (ParseException e) {
			logger.error("ParseException:", e);
		}

		JSONObject jsonObject = (JSONObject) jsonObjParse;
		Long amount = Long.parseLong((String) jsonObject.get("amount"));
		Long term = Long.parseLong((String) jsonObject.get("term"));
		int extTerm = (int) Long.parseLong((String) jsonObject.get("extTerm"));
		SimpleDateFormat sf = new SimpleDateFormat("yyyy.mm.dd hh:mm");
		Date stDate = null;
		try {
			stDate = sf.parse((String) jsonObject.get("startDate"));
		} catch (java.text.ParseException e) {
			logger.error("ParseException:", e);
		}

		Calendar c = Calendar.getInstance();
		c.setTime(stDate);
		c.add(Calendar.MONTH, IConstantLoan.maxTerm);

		LoanDateDTO loanDTO = new LoanDateDTO();
		loanDTO.setAmount(amount);
		loanDTO.setTerm(term.intValue());
		loanDTO.setStartDate(stDate);
		loanDTO.setExtTerm(extTerm);

		c.setTime(stDate);
		c.add(Calendar.MONTH, IConstantLoan.maxTerm + extTerm);

		loanDTO.setEndDate(c.getTime());
		return loanDTO;
	}
}
