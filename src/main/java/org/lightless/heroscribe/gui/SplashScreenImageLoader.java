/*
  HeroScribe
  Copyright (C) 2002-2004 Flavio Chierichetti and Valerio Chierichetti
   
  This program is free software; you can redistribute it and/or modify
  it under the terms of the GNU General Public License version 2 (not
  later versions) as published by the Free Software Foundation.
 
  This program is distributed in the hope that it will be useful,
  but WITHOUT ANY WARRANTY; without even the implied warranty of
  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
  GNU General Public License for more details.
 
  You should have received a copy of the GNU General Public License
  along with this program; if not, write to the Free Software
  Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package org.lightless.heroscribe.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.Toolkit;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

import javax.swing.JWindow;

import org.lightless.heroscribe.HeroScribeException;
import org.lightless.heroscribe.helper.ResourceHelper;
import org.lightless.heroscribe.list.LObject;
import org.lightless.heroscribe.list.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SplashScreenImageLoader extends JWindow {

	private static final long serialVersionUID = 1L;
	private static final Logger log = LoggerFactory.getLogger(SplashScreenImageLoader.class);

	Image splash;

	MediaTracker mt;
	Toolkit tk;

	int splashID = 1;

	public SplashScreenImageLoader(List objects) {
		super();

		mt = new MediaTracker(this);
		tk = Toolkit.getDefaultToolkit();

		InputStream imageIs = ResourceHelper.getResourceAsStream("Splash.jpg");
		byte[] image;
		try {
			image = imageIs.readAllBytes();
		} catch (IOException e) {
			throw new HeroScribeException(e);
		}

		splash = tk.createImage(image);
		mt.addImage(splash, splashID);

		try {
			mt.waitForID(splashID);
		} catch (InterruptedException e) {
			throw new HeroScribeException(e);
		}

		if (mt.isErrorID(splashID)) {
			throw new HeroScribeException("Can't load all PNG icons.");
		}

		setSize(splash.getWidth(null), splash.getHeight(null));

		setLocation((tk.getScreenSize().width - this.getWidth()) / 2,
				(tk.getScreenSize().height - this.getHeight()) / 2);

		setVisible(true);

		loadIcons(objects);

		setVisible(false);
	}

	public void paint(Graphics g) {
		if (mt.checkID(splashID)) {
			g.drawImage(splash, 0, 0, this);
		}
	}

	private void loadIcons(List objects) {
		Iterator<LObject> iterator;
		Image img;

		long start, end;

		start = System.currentTimeMillis();

		iterator = objects.objectsIterator();

		/* Board */
		img = tk.createImage(objects.getRasterPath("Europe"));
		objects.getBoard().getIcon("Europe").image = img;
		mt.addImage(img, 10);

		img = tk.createImage(objects.getRasterPath("USA"));
		objects.getBoard().getIcon("USA").image = img;
		mt.addImage(img, 10);

		while (iterator.hasNext()) {
			String id = ((LObject) iterator.next()).id;

			/* Icons */
			img = tk.createImage(objects.getRasterPath(id, "Europe"));
			objects.getObject(id).getIcon("Europe").image = img;
			mt.addImage(img, 20);

			img = tk.createImage(objects.getRasterPath(id, "USA"));
			objects.getObject(id).getIcon("USA").image = img;
			mt.addImage(img, 20);
		}

		try {
			mt.waitForAll();
		} catch (InterruptedException e) {
			throw new HeroScribeException(e);
		}

		if (mt.isErrorAny()) {
			throw new HeroScribeException("Can't load all PNG icons.");
		}

		end = System.currentTimeMillis();

		log.info("PNGs loaded (" + String.valueOf(end - start) + "ms).");
	}
}