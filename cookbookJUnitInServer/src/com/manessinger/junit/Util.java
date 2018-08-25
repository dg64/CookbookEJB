package com.manessinger.junit;

import java.util.Properties;

public class Util {
		public static Properties getInitProperties() {
			Properties result = new Properties();
			
			//We need to tell the context where and how to look
			result.setProperty("java.naming.factory.initial", 
					"com.sun.enterprise.naming.SerialInitContextFactory");
			result.setProperty("java,naming.factory.url.pkgs", 
					"com.sun.enterprise.naming");
			result.setProperty("java.naming.factory.state", 
					"com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");
			
			//Should not be necessary for local test (default),
			//but currently is
			result.setProperty("org.omg.CORBA.OrbInitialHost", "localhost");
			result.setProperty("org.omg.CORBA.OrbInitialPort", "3700");

			return result;
		}

}
