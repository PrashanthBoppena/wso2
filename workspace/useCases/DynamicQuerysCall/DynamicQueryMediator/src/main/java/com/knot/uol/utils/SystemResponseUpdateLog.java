package com.knot.uol.utils;

import java.sql.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.synapse.MessageContext;
import org.apache.synapse.mediators.AbstractMediator;

public class SystemResponseUpdateLog extends AbstractMediator {
	private String apiregistryConfigPath;
	private String reqId;
	private String processId;
	private String process;
	private String responsePayload;
	private String status;
	
	// setters and getters for private class variables
	public String getApiregistryConfigPath() {
		return apiregistryConfigPath;
	}

	public void setApiregistryConfigPath(String apiregistryConfigPath) {
		this.apiregistryConfigPath = apiregistryConfigPath;
	}
	
	public String getReqId() {
		return reqId;
	}

	public void setReqId(String reqId) {
		this.reqId = reqId;
	}

	public String getProcessId() {
		return processId;
	}

	public void setProcessId(String processId) {
		this.processId = processId;
	}

	public String getProcess() {
		return process;
	}

	public void setProcess(String process) {
		this.process = process;
	}

	public String getResponsePayload() {
		return responsePayload;
	}

	public void setResponsePayload(String responsePayload) {
		this.responsePayload = responsePayload;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Override
	public boolean mediate(MessageContext context) {
        Connection con = null;
        PreparedStatement pstmt = null;
        

        try {
        	Properties properties = PropertiesUtil.propertiesFileRead(apiregistryConfigPath);
            con = JDBCConnectionUtil.connectToDatabase("UOLLogs", "wso2", properties);
            if(con!=null) {
                System.out.println("update log conn obj====> "+con);
                
                String query = "update bscsreqloghandler set ResponsePayload=?, Status=? where ProcessId=?";
                                
                pstmt = con.prepareStatement(query);
                pstmt.setString(1, responsePayload);
                
                pstmt.setString(2, status);
                pstmt.setString(3, processId);

                int count = pstmt.executeUpdate();
                System.out.println("logs updated==> "+count);            	
            }else {
            	System.out.println("UOL logs DB Connection Error");  
            }

        } catch (SQLException e) {
            e.printStackTrace();
            // Handle the exception appropriately
        } finally {
            // Close the PreparedStatement and Connection in the finally block
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

}
