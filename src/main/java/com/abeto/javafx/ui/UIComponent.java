package com.abeto.javafx.ui;

import javafx.scene.paint.Color;

public interface UIComponent {
    String getText();
    void setText(String text);
    void setTextFill(Color color);
} 