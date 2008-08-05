/*
    This file is part of the Iritgo Eclipse Tools project

    (C) 2008 Iritgo Technologies
 */

package de.iritgo.eclipse.tools.unimod.preferences;


import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

import de.iritgo.eclipse.tools.unimod.IritgoUnimodPlugin;


/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer
{
	/**
	 * @see org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer#initializeDefaultPreferences()
	 */
	public void initializeDefaultPreferences ()
	{
		IPreferenceStore store = IritgoUnimodPlugin.getDefault ().getPreferenceStore ();
		store.setDefault (PreferenceConstants.ENABLE_AUTO_TRANSFORM, true);
	}
}
