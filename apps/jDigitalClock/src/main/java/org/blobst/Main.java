package org.blobst;

import com.raylib.Sound;

import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

import static com.raylib.Raylib.*;
import static com.raylib.Raylib.KeyboardKey.*;

public class Main {
	static final int WIDTH = 900;
	static final int HEIGHT = 600;
	static Sound coolSound;
	static String lastPlayed = "";

	/**
	 * gets the current time, duuh!
	 *
	 * @param pattern a time format pattern by using {@link java.time.format.DateTimeFormatter#ofPattern(String)}
	 */
	static String getCurrentTime(String pattern) {
		LocalTime time = LocalTime.now();
		DateTimeFormatter timeFormated = DateTimeFormatter.ofPattern(pattern);

		return time.format(timeFormated);
	}

	static void initAudio() {
		initAudioDevice();
		coolSound = loadSound("assets/cool.wav");
	}


	static void playCoolSoundWhenTime(String targetTime) {
		String current = getCurrentTime("HH:mm:ss");

		if (current.equals(targetTime) && !current.equals(lastPlayed)) {
			playSound(coolSound);
			lastPlayed = current;
		}
	}

	static void cleanupAudio() {
		unloadSound(coolSound);
		closeAudioDevice();
	}

	static void main() {
		initWindow(WIDTH, HEIGHT, "Digital Clock");
		initAudio();

		var clockConfig = new ClockUtils();

		clockConfig.setFile("Clock.conf");

		String inputText = clockConfig.readFile();
		if (inputText == null) inputText = "";
		inputText = inputText.trim();

		while (!windowShouldClose()) {
			inputText = clockConfig.get();
			if (inputText == null || inputText.isBlank()) {
				inputText = clockConfig.readFile();
			}
			if (inputText == null) inputText = "24";
			inputText = inputText.trim();
			
			if (isKeyDown(KEY_J)) {
				clockConfig.set("24");
			} else if (isKeyDown(KEY_K)) {
				clockConfig.set("12");
			} else if (isKeyPressed(KEY_R)) {
				String format = clockConfig.get();
				clockConfig.writeFile(clockConfig.get());
			}

			beginDrawing();

			clearBackground(BLACK);
			int textSize = 100;

			playCoolSoundWhenTime("14:48:50");
			switch (inputText) {
				case "12" -> {
					String time = getCurrentTime("hh:mm:ss a");

					int textWidth = measureText(time, textSize);
					int textX = (WIDTH - textWidth) / 2;
					int textY = (HEIGHT - textSize) / 2;

					drawText(time, textX, textY, textSize, WHITE);
				}
				case "24" -> {
					String time = getCurrentTime("HH:mm:ss");

					int textWidth = measureText(time, textSize);
					int textX = (WIDTH - textWidth) / 2;
					int textY = (HEIGHT - textSize) / 2;

					drawText(time, textX, textY, textSize, WHITE);
				}
				default -> {
					ZonedDateTime nowWithZone = ZonedDateTime.now();
					DateTimeFormatter localformatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(Locale.getDefault());
					String time = nowWithZone.format(localformatter);

					int textWidth = measureText(time, 50);
					int textX = (WIDTH - textWidth) / 2;
					int textY = (HEIGHT - 50) / 2;

					drawText(time, textX, textY, 50, WHITE);
				}
			}

			endDrawing();
		}

		cleanupAudio();
		closeWindow();
	}
}
