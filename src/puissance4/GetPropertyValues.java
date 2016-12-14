package puissance4;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import log.Log;

public class GetPropertyValues {

	private int x;
	private int y;
	private int scoreWin;
	InputStream inputStream;

	public void getPropValues() throws IOException {

		try {
			Properties prop = new Properties();
			String propFileName = "config.properties";
			inputStream = new FileInputStream(propFileName);
			
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("config file '" + propFileName
						+ "' pas trouvé");
			}

			// récupération de config.properties

			x = Integer.parseInt(prop.getProperty("x"));
			y = Integer.parseInt(prop.getProperty("y"));
			scoreWin = Integer.parseInt(prop.getProperty("scoreWin"));

			inputStream.close();
		} catch (Exception e) {
			System.err.println("Exception: " + e);
			Log.append("erreur load config_\n");
			System.exit(0);
		}

	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getScoreWin() {
		return scoreWin;
	}
}
