/**
 * 
 */
package fr.infini.domotique.communication.service.implementation;

import java.util.Enumeration;

import javax.comm.CommPortIdentifier;

import com.sun.comm.Win32Driver;

import fr.infini.domotique.communication.service.CommunicationService;

/**
 * @author Primael
 *
 */
public enum CommunicationServiceImplementation implements CommunicationService {

	INSTANCE;
	
	public void listerPortCom(){
		Win32Driver win32Driver = new Win32Driver();
		win32Driver.initialize();
		
		@SuppressWarnings("unchecked")
		Enumeration<CommPortIdentifier> portList = CommPortIdentifier.getPortIdentifiers();
		
		CommPortIdentifier portId;
		
		while(portList.hasMoreElements()){
			portId = portList.nextElement();
			System.out.println(portId.getName());
		}
	}
	
	public static void main(String[] args) {
		CommunicationServiceImplementation.INSTANCE.listerPortCom();
	}
}
