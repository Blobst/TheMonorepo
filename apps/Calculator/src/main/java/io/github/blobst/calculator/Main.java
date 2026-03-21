package io.github.blobst.calculator;

import com.raylib.Rectangle;

import static com.raylib.Raylib.*;

public class Main {
	static void main() {
		int screenWidth = 800;
		int screenHeight = 600;
		initWindow(screenWidth, screenHeight, "Calculator");
		setTargetFPS(60);

		int rows = 3;
		int cols = 3;
		int buttonWidth = 150;
		int buttonHeight = 80;
		int spacing = 20;

		// Center grid
		int startX = (screenWidth - (cols * buttonWidth + (cols - 1) * spacing)) / 2;
		int startY = (screenHeight - (rows * buttonHeight + (rows - 1) * spacing)) / 2;

		// Create buttons
		Button[][] numbers = new Button[rows][cols];
		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int x = startX + c * (buttonWidth + spacing);
				int y = startY + r * (buttonHeight + spacing);
				numbers[r][c] = new Button(x, y, buttonWidth, buttonHeight, "" + (r * cols + c + 1));
			}
		}

		Button[][] operations = new Button[rows][cols];
		String[][] ops = {
				{"+", "-", "*"},
				{"/", "=", "C"},
				{"", "", ""}
		};

		for (int r = 0; r < rows; r++) {
			for (int c = 0; c < cols; c++) {
				int x = startX + c * (buttonWidth + spacing);
				int y = startY + r * (buttonHeight + spacing);

				operations[r][c] = new Button(x, y, buttonWidth, buttonHeight, ops[r][c]);
			}
		}
		String clickedText = "";

		while (!windowShouldClose()) {
			beginDrawing();
			clearBackground(RAYWHITE);

			Rectangle textBox = new Rectangle(10, 10, 200, 100);

			drawRectangleRec(textBox, GRAY);

			// Draw buttons and check clicks
			for (int r = 0; r < rows; r++) {
				for (int c = 0; c < cols; c++) {
					Button btn = numbers[r][c];
					Button op = operations[r][c];
					btn.draw();
					op.draw();
					if (btn.isClicked()) {
						clickedText = btn.getText();
					}
					if (op.isClicked()) {
						clickedText += op.getText();
					}
				}
			}

			if (!clickedText.isEmpty()) {
				drawText(clickedText, (int) (textBox.x() + 10), (int) (textBox.y() + 10), 20, WHITE);
			}
			endDrawing();
		}

		closeWindow();
	}
}
