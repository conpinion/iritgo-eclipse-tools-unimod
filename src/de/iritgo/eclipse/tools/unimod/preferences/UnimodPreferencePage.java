/*
    This file is part of the Iritgo Eclipse Tools project

    (C) 2008 Iritgo Technologies
 */

package de.iritgo.eclipse.tools.unimod.preferences;


import org.eclipse.jface.preference.*;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.IWorkbench;
import de.iritgo.eclipse.tools.unimod.IritgoUnimodPlugin;


/**
 * 
 */
public class UnimodPreferencePage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage
{
	/**
	 * 
	 */
	public UnimodPreferencePage ()
	{
		super (GRID);
		setPreferenceStore (IritgoUnimodPlugin.getDefault ().getPreferenceStore ());
		setDescription ("Iritgo Eclipse Tools - Unimod");
	}

	/**
	 * @see org.eclipse.jface.preference.FieldEditorPreferencePage#createFieldEditors()
	 */
	@Override
	public void createFieldEditors ()
	{
		addField (new BooleanFieldEditor (PreferenceConstants.ENABLE_AUTO_TRANSFORM, "Enable &Auto Transformation:",
						getFieldEditorParent ()));
	}

	/**
	 * @see org.eclipse.ui.IWorkbenchPreferencePage#init(org.eclipse.ui.IWorkbench)
	 */
	public void init (IWorkbench workbench)
	{
	}
}