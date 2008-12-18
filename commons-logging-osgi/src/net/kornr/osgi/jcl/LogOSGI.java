/*
 *  Copyright 2008, Rodrigo Reyes (nogunner at gmail . com)
 *  
 *  Licensed under the Apache License, Version 2.0 (the "License"); 
 *  you may not use this file except in compliance with the License. 
 *  You may obtain a copy of the License at 
 *  http://www.apache.org/licenses/LICENSE-2.0 
 *  
 *  Unless required by applicable law or agreed to in writing, software 
 *  distributed under the License is distributed on an "AS IS" BASIS, 
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or 
 *  implied. See the License for the specific language governing 
 *  permissions and limitations under the License.  
 */
package net.kornr.osgi.jcl;

import org.apache.commons.logging.Log;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;

public class LogOSGI implements Log
{
	/**
	 * The default constructor. At its initial state, the object cannot log, but should silently ignore
	 * any logging request. 
	 */
	public LogOSGI()
	{
	}

	/**
	 * Forward the log request to the internalLog(int,Object,Throwable)
	 * @param level
	 * @param message
	 */
	final private void internalLog(int level, Object message)
	{
		internalLog(level, message, null);
	}

	/**
	 * Check the availability of the OSGI logging service, and use it is available.
	 * Does nothing otherwise.
	 * @param level
	 * @param message
	 * @param t
	 */
	final private void internalLog(int level, Object message, Throwable t)
	{
		LogService logservice = LogOSGIFactory.getLogService();
		ServiceReference serviceref = LogOSGIFactory.getServiceReference();
		
		if (logservice != null)
		{
			try {
				if (t != null)
					logservice.log(serviceref, level, message.toString(), t);
				else
					logservice.log(serviceref, level, message.toString());
			} catch (Exception exc)
			{
				// Service may have become invalid, just ignore any error
				// until the log service reference is updated by the
				// log factory.
			}
		}
	}


	public void debug(Object message) {
		internalLog(LogService.LOG_DEBUG, message);
	}

	public void debug(Object message, Throwable t) {
		internalLog(LogService.LOG_DEBUG, message, t);
	}

	public void error(Object message) {
		internalLog(LogService.LOG_ERROR, message);
	}

	public void error(Object message, Throwable t) {
		internalLog(LogService.LOG_ERROR, message, t);
	}

	public void fatal(Object message) {
		internalLog(LogService.LOG_ERROR, message);
	}

	public void fatal(Object message, Throwable t) {
		internalLog(LogService.LOG_ERROR, message, t);
	}

	public void info(Object message) {
		internalLog(LogService.LOG_INFO, message);
	}

	public void info(Object message, Throwable t) {
		internalLog(LogService.LOG_INFO, message, t);
	}

	public boolean isDebugEnabled() {
		return true;
	}

	public boolean isErrorEnabled() {
		return true;
	}

	public boolean isFatalEnabled() {
		return true;
	}

	public boolean isInfoEnabled() {
		return true;
	}

	public boolean isTraceEnabled() {
		return true;
	}

	public boolean isWarnEnabled() {
		return true;
	}

	public void trace(Object message) {
		internalLog(LogService.LOG_DEBUG, message);
	}

	public void trace(Object message, Throwable t) {
		internalLog(LogService.LOG_DEBUG, message, t);
	}

	public void warn(Object message) {
		internalLog(LogService.LOG_WARNING, message);
	}

	public void warn(Object message, Throwable t) {
		internalLog(LogService.LOG_WARNING, message,t);
	}

}
