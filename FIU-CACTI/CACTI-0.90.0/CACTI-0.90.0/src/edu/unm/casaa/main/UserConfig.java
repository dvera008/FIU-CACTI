/*
This source code file is part of the CASAA Treatment Coding System Utility
    Copyright (C) 2009  UNM CASAA
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.
    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.
    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/

package edu.unm.casaa.main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.prefs.Preferences;

/**
 * Interface for user config data. An XML file the user can edit outside of the
 * application to define codes and code layout.
 */
public class UserConfig {

	/* provide access to application preferences */
	private static final Preferences appPrefs = Preferences.userNodeForPackage(Main.class);
	/* default location of config file */
	private static final String defaultPath = String.format("%s%s%s", System.getProperty("user.home"),
			System.getProperty("file.separator"), "CactiUserConfiguration.xml");

	/**
	 * Writes out a default, minimal config file
	 */
	public static void writeDefault() throws IOException {

		File config = new File(getPath());

		try (PrintWriter writer = new PrintWriter(new FileWriter(config, false))) {
			writer.println("<userConfiguration>");
			writer.println("<codes><!-- Parent codes -->");
			writer.println("<code name=\"BLAME\" value=\"11\"/>");
			writer.println("<code name=\"PERSUADE\" value=\"12\"/>");
			writer.println("<code name=\"AFFIRM\" value=\"13\"/>");
			writer.println("<code name=\"ENCOURAGE\" value=\"14\"/>");
			writer.println("<code name=\"WISHES\" value=\"15\"/>");
			writer.println("<code name=\"NEUTRAL\" value=\"16\"/>");
			writer.println("<code name=\"NO CODE\" value=\"17\"/>");
			writer.println("<code name=\"CHANGE TALK\" value=\"18\"/>");
			writer.println("<code name=\"CHANGE TALK BP\" value=\"19\"/>");
			writer.println("<code name=\"CHANGE TALK REL\" value=\"20\"/>");
			writer.println("<code name=\"CHANGE TALK INT\" value=\"21\"/>");
			writer.println("<code name=\"SUSTAIN TALK\" value=\"22\"/>");
			writer.println("<code name=\"SUSTAIN TALK BP\" value=\"23\"/>");
			writer.println("<code name=\"SUSTAIN TALK REL\" value=\"24\"/>");
			writer.println("<code name=\"SUSTAIN TALK INT\" value=\"25\"/>");
			writer.println("</codes>");
			writer.println();
			writer.println("<codeControls panel=\"left\" label=\"Parent\"><!-- Parent button layout -->");
			writer.println("<row><button code=\"BLAME\"/><button code=\"PERSUADE\"/><button code=\"AFFIRM\"/><button code=\"ENCOURAGE\"/></row>");
			writer.println("<row><button code=\"WISHES\"/><button code=\"NEUTRAL\"/><button code=\"NO CODE\"/></row>");
			writer.println("<row><button code=\"CHANGE TALK\"/><button code=\"CHANGE TALK BP\"/><button code=\"CHANGE TALK REL\"/><button code=\"CHANGE TALK INT\"/></row>");
			writer.println("<row><button code=\"SUSTAIN TALK\"/><button code=\"SUSTAIN TALK BP\"/><button code=\"SUSTAIN TALK REL\"/><button code=\"SUSTAIN TALK INT\"/></row>");
			writer.println("</codeControls>");
			writer.println();
			writer.println("<codes><!-- Teen codes -->");
			writer.println("<code name=\"TEEN TEST CODE\" value=\"30\"/>");
			writer.println("</codes>");
			writer.println();
			writer.println("<codeControls panel=\"center\" label=\"Teen\"><!-- Teen button layout -->");
			writer.println("<row><button code=\"TEEN TEST CODE\"/></row>");
			writer.println("</codeControls>");
			writer.println();
			writer.println("<codes><!-- Therapist codes -->");
			writer.println("<code name=\"THERAPIST TEST CODE\" value=\"50\"/>");
			writer.println("</codes>");
			writer.println();
			writer.println("<codeControls panel=\"right\" label=\"Therapist\"><!-- Therapist button layout -->");
			writer.println("<row><button code=\"THERAPIST TEST CODE\"/></row>");
			writer.println("</codeControls>");
			writer.println();
			writer.println("<globals><!-- Global codes -->");
			writer.println("<global name=\"SOLUTIONFOCUSED\" label=\"Solution-Focused\" value=\"0\" defaultRating=\"-1\"/>");
			writer.println("<global name=\"ENCOURAGINGTEENVOICE\" label=\"Encouraging Teen Voice\" value=\"1\" defaultRating=\"-1\"/>");
			writer.println("<global name=\"PARENTSELFEXPLORATION\" label=\"Parent Self Exploration\" value=\"2\" defaultRating=\"-1\"/>");
			writer.println("<global name=\"OPTIMISM\" label=\"Optimism\" value=\"3\" defaultRating=\"-1\"/>");
			writer.println("</globals>");
			writer.println();
			writer.println("<!-- Global code layout -->");
			writer.println("<globalControls panel=\"left\"><slider global=\"SOLUTIONFOCUSED\"/></globalControls>");
			writer.println("<globalControls panel=\"left\"><slider global=\"ENCOURAGINGTEENVOICE\"/></globalControls>");
			writer.println("<globalControls panel=\"left\"><slider global=\"PARENTSELFEXPLORATION\"/></globalControls>");
			writer.println("<globalControls panel=\"left\"><slider global=\"OPTIMISM\"/></globalControls>");
			writer.println();
			writer.println("</userConfiguration>");
		}
	}

	/**
	 * Check for config file
	 * 
	 * @return
	 */
	public static boolean exists() {

		File file = new File(getPath());
		return file.canRead();

	}

	/**
	 * Set config file location
	 * 
	 * @param configPath
	 */
	public static void setPath(String configPath) {
		appPrefs.put("configFilePath", configPath);
	}

	/**
	 * Get config file location
	 * 
	 * @return
	 */
	public static String getPath() {

		// Don't want to send back empty path
		if (appPrefs.get("configFilePath", "").isEmpty()) {
			appPrefs.put("configFilePath", defaultPath);
		}
		return appPrefs.get("configFilePath", defaultPath);

	}

}
