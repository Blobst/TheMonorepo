package io.github.blobst.calculator;

import com.raylib.Color;
import com.raylib.Rectangle;
import lombok.AllArgsConstructor;
import lombok.Getter;

import static com.raylib.Raylib.*;
import static com.raylib.Raylib.MouseButton.MOUSE_BUTTON_LEFT;

@Getter
@AllArgsConstructor
public class Button {
	String text;
	private Rectangle rect;
	private Color normalColor;
	private Color hoverColor;
	private boolean hovered;

	public Button(int x, int y, int width, int height, String text) {
		this.rect = new Rectangle(x, y, width, height);
		this.text = text;
		this.normalColor = GRAY;
		this.hoverColor = LIGHTGRAY;
		this.hovered = false;
	}

	// Draw the button
	public void draw() {
		hovered = checkCollisionPointRec(getMousePosition(), rect);
		drawRectangleRec(rect, hovered ? hoverColor : normalColor);

		// Center text
		int textWidth = measureText(text, 20);
		int textX = (int) (rect.x() + (rect.width() - textWidth) / 2);
		int textY = (int) (rect.y() + (rect.height() - 20) / 2);
		drawText(text, textX, textY, 20, BLACK);
	}

	// Check if button was clicked
	public boolean isClicked() {
		return hovered && isMouseButtonPressed(MOUSE_BUTTON_LEFT);
	}
}