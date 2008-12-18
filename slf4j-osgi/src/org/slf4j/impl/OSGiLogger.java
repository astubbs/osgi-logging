package org.slf4j.impl;

import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;
import org.slf4j.helpers.MarkerIgnoringBase;
import org.slf4j.helpers.MessageFormatter;

public class OSGiLogger extends MarkerIgnoringBase 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Check the availability of the OSGI logging service, and use it is available.
	 * Does nothing otherwise.
	 * @param level
	 * @param message
	 * @param t
	 */
	final private void internalLog(int level, Object message, Throwable t)
	{
		LogService logservice = OSGILogFactory.getLogService();
		ServiceReference serviceref = OSGILogFactory.getServiceReference();

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

	public boolean isTraceEnabled() {
		return true;
	}
	public void trace(String msg) {
		internalLog(LogService.LOG_DEBUG, msg, null);
	}

	public void trace(String format, Object arg) {
		String msgStr = MessageFormatter.format(format, arg);
		internalLog(LogService.LOG_DEBUG, msgStr, null);
	}

	public void trace(String format, Object arg1, Object arg2) {
		String msgStr = MessageFormatter.format(format, arg1, arg2);
		internalLog(LogService.LOG_DEBUG, msgStr, null);
	}


	public void trace(String format, Object[] argArray) {
		String msgStr = MessageFormatter.arrayFormat(format, argArray);
		internalLog(LogService.LOG_DEBUG, msgStr, null);
	}

	public void trace(String msg, Throwable t) {
		internalLog(LogService.LOG_DEBUG, msg, t);
	}



	public boolean isDebugEnabled() {
		return true;
	}
	public void debug(String msg) {
		internalLog(LogService.LOG_DEBUG, msg, null);
	}
	public void debug(String format, Object arg) {
		String msgStr = MessageFormatter.format(format, arg);
		internalLog(LogService.LOG_DEBUG, msgStr, null);
	}

	public void debug(String format, Object arg1, Object arg2) {
		String msgStr = MessageFormatter.format(format, arg1, arg2);
		internalLog(LogService.LOG_DEBUG, msgStr, null);
	}

	public void debug(String format, Object[] argArray) {
		String msgStr = MessageFormatter.arrayFormat(format, argArray);
		internalLog(LogService.LOG_DEBUG, msgStr, null);
	}

	public void debug(String msg, Throwable t) {
		internalLog(LogService.LOG_DEBUG, msg, t);
	}

	public boolean isInfoEnabled() {
		return true;
	}

	public void info(String msg) {
		internalLog(LogService.LOG_INFO, msg, null);
	}

	public void info(String format, Object arg) {
		String msgStr = MessageFormatter.format(format, arg);
		internalLog(LogService.LOG_INFO, msgStr, null);
	}
	public void info(String format, Object arg1, Object arg2) {
		String msgStr = MessageFormatter.format(format, arg1, arg2);
		internalLog(LogService.LOG_INFO, msgStr, null);
	}

	public void info(String format, Object[] argArray) {
		String msgStr = MessageFormatter.arrayFormat(format, argArray);
		internalLog(LogService.LOG_INFO, msgStr, null);
	}

	public void info(String msg, Throwable t) {
		internalLog(LogService.LOG_INFO, msg, t);
	}

	public boolean isWarnEnabled() {
		return true;
	}
	public void warn(String msg) {
		internalLog(LogService.LOG_WARNING, msg, null);
	}

	public void warn(String format, Object arg) {
		String msgStr = MessageFormatter.format(format, arg);
		internalLog(LogService.LOG_WARNING, msgStr, null);
	}

	public void warn(String format, Object arg1, Object arg2) {
		String msgStr = MessageFormatter.format(format, arg1, arg2);
		internalLog(LogService.LOG_WARNING, msgStr, null);
	}

	public void warn(String format, Object[] argArray) {
		String msgStr = MessageFormatter.arrayFormat(format, argArray);
		internalLog(LogService.LOG_WARNING, msgStr, null);
	}

	public void warn(String msg, Throwable t) {
		internalLog(LogService.LOG_WARNING, msg, t);
	}

	public boolean isErrorEnabled() {
		return true;
	}

	public void error(String msg) {
		internalLog(LogService.LOG_ERROR, msg, null);
	}

	public void error(String format, Object arg) {
		String msgStr = MessageFormatter.format(format, arg);
		internalLog(LogService.LOG_ERROR, msgStr, null);
	}

	public void error(String format, Object arg1, Object arg2) {
		String msgStr = MessageFormatter.format(format, arg1, arg2);
		internalLog(LogService.LOG_ERROR, msgStr, null);
	}
	public void error(String format, Object[] argArray) {
		String msgStr = MessageFormatter.arrayFormat(format, argArray);
		internalLog(LogService.LOG_ERROR, msgStr, null);
	}

	public void error(String msg, Throwable t) {
		internalLog(LogService.LOG_ERROR, msg, t);
	}

}
