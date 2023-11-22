package org.wso2.sample;

import org.apache.axis2.client.ServiceClient;
import org.apache.axis2.context.ConfigurationContext;
import org.apache.axiom.om.OMElement;
import org.apache.axiom.om.OMFactory;
import org.apache.axiom.om.OMAbstractFactory;
import org.apache.axiom.om.OMNamespace;
import org.apache.axis2.client.Options;
import org.apache.axis2.addressing.EndpointReference;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


public class RegisterPatientServlet extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private String DSSServerURL = "https://localhost:9445/services/WSO2HealthIT/";
	private static String nameSpaceURL = "http://ws.wso2.org/dataservice/samples/health";
	
	public static OMElement createPayload(String patientNumber, String patientLastName, String patientFirstName, String phone, String city, String streetname, String country){
        OMFactory fac = OMAbstractFactory.getOMFactory();
        OMNamespace omNs = fac.createOMNamespace(nameSpaceURL, "ns");
        OMElement registerPatientQuery = fac.createOMElement("registerPatient", omNs);
        OMElement patientNo = fac.createOMElement("patientNumber", omNs);
        OMElement patientLName = fac.createOMElement("patientLastName", omNs);
        OMElement patientFName = fac.createOMElement("patientFirstName", omNs);
        OMElement patientPhone = fac.createOMElement("phone", omNs);
        OMElement patientCity = fac.createOMElement("city", omNs);
        OMElement patientStreet = fac.createOMElement("streetname", omNs);
        OMElement patientCountry = fac.createOMElement("country", omNs);

        patientNo.setText(patientNumber);
        patientLName.setText(patientLastName);
        patientFName.setText(patientFirstName);
        patientPhone.setText(phone);
        patientCity.setText(city);
        patientStreet.setText(streetname);
        patientCountry.setText(country);


        registerPatientQuery.addChild(patientNo);
        registerPatientQuery.addChild(patientLName);
        registerPatientQuery.addChild(patientFName);
        registerPatientQuery.addChild(patientPhone);
        registerPatientQuery.addChild(patientCity);
        registerPatientQuery.addChild(patientStreet);
        registerPatientQuery.addChild(patientCountry);

        return registerPatientQuery;
    }

    public void doGet(HttpServletRequest request,
                       HttpServletResponse response)
            throws ServletException, IOException {

        // Set response content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String docType =
                "<!doctype html public \"-//w3c//dtd html 4.0 " +
                        "transitional//en\">\n";
        out.println(docType +
                "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        p.serif{font-family:\"Courier New\", Courier, monospace;}\n" +
                "    </style>\n" +
                "    <title>Fluance Portal</title>\n" +
                "</head>\n" +
                "<table>\n" +
                "    <tbody>\n" +
                "    <tr>\n" +
                "        <td>\n" +
                "            <form>\n" +
                "                <div style=\"letter-spacing:12px;\" align=\"center\">Confirmation</div>\n" +
                "            </form>\n" +
                "        </td>\n" +
                "    </tr>\n" +
                "    </tbody>\n" +
                "</table>\n" +
                "<div style=\"opacity:0.5;position:absolute;right:50px;width:300px;height:500px;background-color:#40B3DF;\"></div>\n" +
                "<img src=\"./images/all-in-one.png\" style=\"float:right\" align=\"top\" alt=\"\" width=\"250\" height=\"250\">\n" +
                "<div style=\"font-family:verdana;padding:20px;border-radius:100px;border:50px solid #EE872A;\">\n" +
                "<div style=\"opacity:0.3;position:absolute;right:120px;width:400px;height:600px;background-color:#8AC007;\"></div>\n" +
                "  <table>\n" +
                "      <tbody>\n" +
                "       <tr>\n" +
                "        <td>Successfully Registered Patient Details</td><td>    <tr>\n" +
                "        <td></td>\n" +
                "        <td></td>\n" +
                "        <td></td>\n" +
                "    </tr>"
        );
        try {
            String patientNumber = request.getParameter("patientNumber");
            String patientLastName = request.getParameter("patientLastName");
            String patientFirstName = request.getParameter("patientFirstName");
            String phone = request.getParameter("phone");
            String city = request.getParameter("city");
            String streetname = request.getParameter("streetname");
            String country = request.getParameter("country");

            OMElement payload = createPayload(patientNumber,patientLastName,patientFirstName,phone,city,streetname,country);
            OMElement result=null;
            ServiceClient serviceclient;

            ConfigurationContext cc = null;

            Options opt = new Options();
            opt.setTo(new EndpointReference (DSSServerURL));
            opt.setAction("registerPatient");
            serviceclient = new ServiceClient(cc, null);
            serviceclient.setOptions(opt);
            serviceclient.fireAndForget(payload);

            out.println("  </td></tr><table>\n" +
                    "      <tbody>\n" +
                    "      <tr>\n" +
                    "          <td></td>\n" +
                    "          <td></td>\n" +
                    "          <td>\n" +
                    "              <form>\n" +
                    "                  <li><a href=\"landingPage.html\">Home</a></li>\n" +
                    "              </form>\n" +
                    "          </td>\n" +
                    "      </tr>\n" +
                    "      <tr>\n" +
                    "          <td></td>\n" +
                    "          <td></td>\n" +
                    "          <td></td>\n" +
                    "      </tr>\n" +
                    "      </tbody>\n" +
                    "      </table></div>\n" +
                    "</body>\n" +
                    "</html>");

        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        }

    }
}
