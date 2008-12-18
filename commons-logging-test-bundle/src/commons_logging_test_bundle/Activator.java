package commons_logging_test_bundle;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class Activator implements BundleActivator 
{
	private static Log logger;
	
	private boolean m_stop = false;
	public Runnable m_runner = new Runnable() {
		public void run() {
			while (m_stop == false)
			{
				try {
					logger.debug("JCL-debug-log="+new java.util.Date());
					logger.info("JCL-info-log="+new java.util.Date());
					logger.warn("JCL-warning-log="+new java.util.Date());
					logger.error("JCL-error-log="+new java.util.Date());
					Exception exc = new Exception("An EXCEPTION!");
					logger.debug("JCL-debug-log="+new java.util.Date(), exc);
					logger.info("JCL-info-log="+new java.util.Date(), exc);
					logger.warn("JCL-warning-log="+new java.util.Date(), exc);
					logger.error("JCL-error-log="+new java.util.Date(), exc);
					
					Thread.sleep(3000);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	};


	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext context) throws Exception {
		net.kornr.osgi.jcl.LogOSGIFactory.initOSGI(context);
		logger = LogFactory.getLog(Activator.class);
		new Thread(m_runner).start();
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		m_stop = true;
	}

}
