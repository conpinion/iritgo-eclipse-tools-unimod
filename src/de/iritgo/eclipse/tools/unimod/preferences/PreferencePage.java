/*
    This file is part of the Iritgo Eclipse Tools project

    (C) 2008 Iritgo Technologies
 */

package de.iritgo.eclipse.tools.unimod.preferences;


import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;

import de.iritgo.eclipse.tools.unimod.IritgoUnimodPlugin;


/**
 *
 */
public class PreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{
	/**
	 * 
	 */
	public PreferencePage ()
	{
		super (GRID);
		setPreferenceStore (IritgoUnimodPlugin.getDefault ().getPreferenceStore ());
		setDescription ("Iritgo Eclipse Tools");
	}

	/**
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	@Override
	public void createFieldEditors ()
	{
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init (IWorkbench workbench)
	{
	}
}
