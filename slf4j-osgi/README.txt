JCL (apache commons logging) LogFactory for OSGI
Author: Rodrigo Reyes, 2008

WHAT IS DOES
------------

The purpose of this library is to provide an OSGI-compliant factory
for the apache-commons logging library. It is not designed to be used
as an autonomous bundle, but rather to provide a compatibility layer
to commons-logging, specially for bundles that include a library using
the commons-logging system, and which cannot or should not (for some
reason) be refactored to use the standard OSGI Logging Service.

Once this library is added to the bundle's classpath (and configured,
see below), any call to the commons-logging API is redirected to the
OSGI LogService currently active.

If no LogService element is available, the logs are simply
ignored. The library is service-aware, so that when a LogService is
registered, it is automatically used (no need to restart the bundle).


How to use
----------

1. Add the commons-logging-osgi-XXXX.jar to your runtime classpath.

2. Add to the System properties of the JVM the following setting:
   org.apache.commons.logging.LogFactory=net.kornr.osgi.jcl.LogOSGIFactory

   For instance, add the following to your java command line:
   -Dorg.apache.commons.logging.LogFactory=net.kornr.osgi.jcl.LogOSGIFactory

   This informs the apache commons logging API to use the
   OSGI-compliant LogFactory provided instead of using its default
   settings.

3. Add in the Activator class of your bundle the following line:

     public void start(BundleContext context) throws Exception {
        ...
        net.kornr.osgi.jcl.LogOSGIFactory.initOSGI(context);
        ...
     }
	
   This bootstraps the LogFactory with the BundleContext, so that it can
   get a reference to a LogService object.

4. Add the commons-logging-osgi-XXXX.jar to the Bundle-ClassPath:
   entry of your MANIFEST.MF

That's it.

LICENCE
-------

 This software is licensed under the Apache License, Version 2.0

 you may not use this file except in compliance with the License. 
 You may obtain a copy of the License at 
 
 http://www.apache.org/licenses/LICENSE-2.0 
 
 Unless required by applicable law or agreed to in writing, software 
 distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the 
 License for the specific language governing permissions and limitations 
 under the License. 
