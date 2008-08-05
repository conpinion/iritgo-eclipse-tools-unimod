/*
    This file is part of the Iritgo Eclipse Tools project

    (C) 2008 Iritgo Technologies
 */

package de.iritgo.eclipse.tools.unimod;


import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.jface.util.IPropertyChangeListener;
import org.eclipse.jface.util.PropertyChangeEvent;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.osgi.framework.BundleContext;

import de.iritgo.eclipse.tools.unimod.preferences.PreferenceConstants;


/**
 * The activator class controls the plug-in life cycle
 */
public class IritgoUnimodPlugin extends AbstractUIPlugin implements IStartup
{
	/** The plug-in ID */
	public static final String PLUGIN_ID = "de.iritgo.eclipse.tools.unimod";

	/** The shared instance */
	private static IritgoUnimodPlugin plugin;

	/** Track unimod file changes */
	private IResourceChangeListener unimodResourceListener;

	/**
	 * The constructor
	 */
	public IritgoUnimodPlugin ()
	{
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#start(org.osgi.framework.BundleContext)
	 */
	public void start (BundleContext context) throws Exception
	{
		super.start (context);
		plugin = this;
	}

	/**
	 * @see org.eclipse.ui.plugin.AbstractUIPlugin#stop(org.osgi.framework.BundleContext)
	 */
	public void stop (BundleContext context) throws Exception
	{
		plugin = null;
		super.stop (context);
		if (unimodResourceListener != null)
		{
			ResourcesPlugin.getWorkspace ().removeResourceChangeListener (unimodResourceListener);
		}
	}

	/**
	 * Returns the shared instance
	 * 
	 * @return the shared instance
	 */
	public static IritgoUnimodPlugin getDefault ()
	{
		return plugin;
	}

	/**
	 * @see org.eclipse.ui.IStartup#earlyStartup()
	 */
	public void earlyStartup ()
	{
		unimodResourceListener = new UnimodResourceListener ();
		ResourcesPlugin.getWorkspace ().addResourceChangeListener (unimodResourceListener);
	}
}
