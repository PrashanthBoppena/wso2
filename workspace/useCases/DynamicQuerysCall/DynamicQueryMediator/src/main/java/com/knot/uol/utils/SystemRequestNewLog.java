package com.knot.uol.utils;
import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import com.knot.uol.mediators.DynamicXMLQueryMediator;
import com.knot.uol.utils.JDBCConnectionUtil;

public class SystemRequestNewLog extends AbstractMediator  {
	
    // Private class variables
	private String apiregistryConfigPath;
    private String reqId;
    private String processId;
    private String process;
    private String apiName;
    private String requestPayload;



    // Setters for private class variables
    
    
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

	public void setApiregistryConfigPath(String apiregistryConfigPath) {
		this.apiregistryConfigPath = apiregistryConfigPath;
	}

	public void setProcessId(String processId) {
        this.processId = processId;
    }

    public void setProcess(String process) {
        this.process = process;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public void setRequestPayload(String requestPayload) {
        this.requestPayload = requestPayload;
    }





    // Getters for private class variables
    public String getApiregistryConfigPath() {
 		return apiregistryConfigPath;
 	}
    
    public String getReqId() {
        return reqId;
    }

    public String getProcessId() {
        return processId;
    }

    public String getProcess() {
        return process;
    }

    public String getApiName() {
        return apiName;
    }

    public String getRequestPayload() {
        return requestPayload;
    }





    
    public boolean mediate(MessageContext context) {
    	
        Connection con = null;
        PreparedStatement pstmt = null;

        try {
        	Properties properties = PropertiesUtil.propertiesFileRead(apiregistryConfigPath);
            con = JDBCConnectionUtil.connectToDatabase("UOLLogs", "wso2", properties);
            if (con != null) {
                System.out.println("New log connection object: " + con);

                String query = "INSERT INTO bscsreqloghandler (ReqId, ProcessId, Process, Api, RequestPayload, Status, requested_on) VALUES (?, ?, ?, ?, ?, 'Inprocess', NOW())";
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, reqId);
                pstmt.setString(2, processId);
                pstmt.setString(3, process);
                pstmt.setString(4, apiName);
                pstmt.setString(5, requestPayload);

                int count = pstmt.executeUpdate();
                System.out.println("New Log count=> "+count);
            } else {
                System.out.println("UOL logs DB Connection Error");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } finally {
            // Close the PreparedStatement and Connection in the finally block   MessageContext context
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }
    
//    public static void main(String[] args) throws ClassNotFoundException {
//    	SystemRequestNewLog systemRequestNewLog = new SystemRequestNewLog();
//    	
//    	systemRequestNewLog.mediate();
//	}
}
