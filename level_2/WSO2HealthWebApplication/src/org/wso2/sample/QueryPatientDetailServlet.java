package org.wso2.sample;

import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.addressing.EndpointReference;
import org.apache.axis2.client.Options;
import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class QueryPatientDetailServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private String dataServiceEP = "https://localhost:9445/services/WSO2HealthIT/";
	private String nameSpaceURL = "http://ws.wso2.org/dataservice/samples/health";

	private OMElement createPayload(String patientNumber) {
		OMFactory fac = OMAbstractFactory.getOMFactory();
		OMNamespace omNs = fac.createOMNamespace(
				nameSpaceURL, "ns");
		OMElement patientDetailsByNumber = fac.createOMElement(
				"patientDetailsByNumber", omNs);
		OMElement patientNo = fac.createOMElement("patientNumber", omNs);

		patientNo.setText(patientNumber);

		patientDetailsByNumber.addChild(patientNo);

		return patientDetailsByNumber;
	}

	private List<Patient> parseResultFromDSS(OMElement response) {
		List<Patient> patientList = new ArrayList<Patient>();
		Iterator<OMElement> patientItr = response
				.getChildrenWithName(new QName("patient"));
		while (patientItr.hasNext()) {
			OMElement patientEle = patientItr.next();
			String firstName = patientEle.getFirstChildWithName(
					new QName(nameSpaceURL, "patient-first-name")).getText();
			String lastName = patientEle.getFirstChildWithName(
					new QName(nameSpaceURL, "patient-last-name")).getText();
			String phone = patientEle.getFirstChildWithName(
					new QName(nameSpaceURL, "phone")).getText();
			String city = patientEle.getFirstChildWithName(
					new QName(nameSpaceURL, "city")).getText();
			String street = patientEle.getFirstChildWithName(
					new QName(nameSpaceURL, "streetname")).getText();
			String country = patientEle.getFirstChildWithName(
					new QName(nameSpaceURL, "country")).getText();

			Patient patient = new Patient();
			patient.setPatientFirstName(firstName);
			patient.setPatientLastName(lastName);
			patient.setPhoneNum(phone);
			patient.setCity(city);
			patient.setStreet(street);
			patient.setCountry(country);
			patientList.add(patient);

		}

		return patientList;

	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		OMElement patientDetails = null;
		response.setContentType("text/html");
		try {
			String patientNumber = request.getParameter("patientNumber");
			OMElement payload = createPayload(patientNumber);
			OMElement result = null;
			ConfigurationContext cc = null;
			ServiceClient serviceclient = new ServiceClient(null, null);
			Options opt = new Options();
			opt.setTo(new EndpointReference(dataServiceEP));
			opt.setAction("urn:patientDetailsByNumber");
			serviceclient = new ServiceClient(cc, null);
			serviceclient.setOptions(opt);
			result = serviceclient.sendReceive(payload);
			List patients = parseResultFromDSS(result);
			request.setAttribute("patientList", patients);
			request.setAttribute("patientNumber", patientNumber);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(
					"/patientInfoPage.jsp");
			rd.forward(request, response);

		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		    RequestDispatcher rd = getServletContext().getRequestDispatcher("/getPatientDetails.jsp");
		    PrintWriter out= response.getWriter();
		    out.println("<font color=red>Error While Quering Records : "+e.getMessage()+"</font>");
		    rd.include(request, response);
		}
	}

}
