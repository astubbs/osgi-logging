package slf4jtest;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.impl.OSGILogFactory;

public class Activator implements BundleActivator {
	
	private static Logger logger;
	
	private boolean m_stop = false;
	
	public Runnable m_runner = new Runnable() {
		public void run() {
			while (m_stop == false)
			{
				try {
					logger.debug("SLF4J-debug-log="+new java.util.Date());
					logger.info("SLF4J-info-log="+new java.util.Date());
					logger.warn("SLF4J-warning-log="+new java.util.Date());
					logger.error("SLF4J-error-log="+new java.util.Date());
					Exception exc = new Exception("An EXCEPTION!");
					logger.debug("SLF4J-debug-log="+new java.util.Date(), exc);
					logger.info("SLF4J-info-log="+new java.util.Date(), exc);
					logger.warn("SLF4J-warning-log="+new java.util.Date(), exc);
					logger.error("SLF4J-error-log="+new java.util.Date(), exc);
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
		OSGILogFactory.initOSGI(context);
		System.out.println("Hello World!!");
		logger = LoggerFactory.getLogger(Activator.class);
		logger.info("THIS IS MY LOG SLF4J");
		new Thread(m_runner).start();
	}
	
	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext context) throws Exception {
		System.out.println("Goodbye World!!");
		m_stop = true;
	}

}
