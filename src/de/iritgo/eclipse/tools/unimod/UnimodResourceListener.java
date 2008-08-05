/*
    This file is part of the Iritgo Eclipse Tools project

    (C) 2008 Iritgo Technologies
 */

package de.iritgo.eclipse.tools.unimod;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.FilenameUtils;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.Path;
import org.xml.sax.SAXException;

import com.evelopers.unimod.core.stateworks.StateMachine;
import com.evelopers.unimod.plugin.eclipse.model.GModel;
import com.evelopers.unimod.plugin.eclipse.transform.gxml.GXMLToGModel;
import com.evelopers.unimod.transform.TransformException;
import com.evelopers.unimod.transform.xml.ModelToXML;

import de.iritgo.eclipse.tools.unimod.preferences.PreferenceConstants;


/**
 * This resource listener tracks changes made to a .unimod file and
 * automatically performs the transformation to a runtime .xml state machine
 * definition.
 */
public class UnimodResourceListener implements IResourceChangeListener
{
	public void resourceChanged (IResourceChangeEvent event)
	{
		if (! IritgoUnimodPlugin.getDefault ().getPreferenceStore ().getBoolean (PreferenceConstants.ENABLE_AUTO_TRANSFORM))
		{
			return;
		}
		
		if (event.getType () != IResourceChangeEvent.POST_CHANGE)
		{
			return;
		}

		IResourceDeltaVisitor visitor = new IResourceDeltaVisitor ()
		{
			public boolean visit (IResourceDelta delta)
			{
				if (delta.getKind () != IResourceDelta.CHANGED)
				{
					return true;
				}
				if ((delta.getFlags () & IResourceDelta.CONTENT) == 0)
				{
					return true;
				}
				IResource resource = delta.getResource ();
				if (resource.getType () == IResource.FILE && "unimod".equalsIgnoreCase (resource.getFileExtension ()))
				{
					try
					{
						File unimodFile = delta.getResource ().getLocation ().toFile ();
						InputStream in = new FileInputStream (unimodFile);
						GXMLToGModel x2g = new GXMLToGModel ();
						GModel model = x2g.transform (in);
						in.close ();
						model.setRootStateMachine ((StateMachine) model.getStateMachines ().get (0));
						OutputStream out = new FileOutputStream (new File (FilenameUtils.getFullPath (unimodFile
										.getAbsolutePath ()),
										FilenameUtils.getBaseName (unimodFile.getAbsolutePath ()) + ".xml"));
						ModelToXML.write (model, out);
						out.close ();
						ResourcesPlugin.getWorkspace ().getRoot ().findMember (
										new Path (FilenameUtils.getBaseName (unimodFile.getAbsolutePath ()) + ".xml"))
										.refreshLocal (0, null);
					}
					catch (FileNotFoundException x)
					{
						System.out.println (x);
					}
					catch (IOException x)
					{
						System.out.println (x);
					}
					catch (SAXException x)
					{
						System.out.println (x);
					}
					catch (TransformException x)
					{
						System.out.println (x);
					}
					catch (CoreException x)
					{
					}
				}
				return true;
			}
		};
		try
		{
			event.getDelta ().accept (visitor);
		}
		catch (CoreException e)
		{
		}
	}
};
