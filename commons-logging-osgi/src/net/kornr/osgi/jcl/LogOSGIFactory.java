package net.kornr.osgi.jcl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogConfigurationException;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleContext;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.osgi.framework.ServiceReference;
import org.osgi.service.log.LogService;

public class LogOSGIFactory extends LogFactory
{
	private static LogOSGI           s_logger = new LogOSGI(); 
	private static BundleContext     s_context = null;
	private static ServiceReference  s_serviceref = null;
	private static LogService    	 s_logservice = null;

	private static ServiceListener s_servlistener = new ServiceListener() {
		public void serviceChanged(ServiceEvent event)
		{
			LogService ls = (LogService)s_context.getService(event.getServiceReference());
			if (ls != null)
			{
				if (event.getType() == ServiceEvent.REGISTERED)
				{
					LogOSGIFactory.setLogService(ls);

				} 
				else if (event.getType() == ServiceEvent.UNREGISTERING)
				{
					if (ls.equals(s_logservice))
					{
						LogOSGIFactory.setLogService(null);

						// Try to find another log service as a replacement for our loss
						ServiceReference ref = s_context.getServiceReference(LogService.class.getName());
						if (ref != null)
						{
							s_logservice = (LogService)s_context.getService(ref);
						}
					}
				}
			}
		}
	};

	public static void initOSGI(BundleContext context)
	{
		initOSGI(context, null);
	}

	public static void initOSGI(BundleContext context, ServiceReference servref)
	{
		s_context = context;
		s_serviceref = servref;
		ServiceReference ref = context.getServiceReference(LogService.class.getName());
		if (ref != null)
		{
			s_logservice = (LogService)context.getService(ref);
		}

		try {
			String filter = "(objectclass=" + LogService.class.getName() + ")";
			context.addServiceListener(s_servlistener, filter);
		} catch (InvalidSyntaxException e) {
			e.printStackTrace();
		}
	}

	static public LogService getLogService()
	{
		return s_logservice;
	}

	static public ServiceReference getServiceReference()
	{
		return s_serviceref;
	}

	static public void setLogService(LogService logservice)
	{
		s_logservice = logservice;
	}

	static public void setServiceReference(ServiceReference ref)
	{
		s_serviceref = ref;
	}

	public Object getAttribute(String arg0) 
	{
		return null;
	}

	public String[] getAttributeNames() {
		return new String[0];
	}

	public Log getInstance(Class arg0) throws LogConfigurationException 
	{
		return s_logger;
	}

	public Log getInstance(String arg0) throws LogConfigurationException {
		return s_logger;
	}

	public void release() {
	}

	public void removeAttribute(String arg0) {
	}

	public void setAttribute(String arg0, Object arg1) {
	}

}
