package com.abeto.javafx.ui;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;

public class LabelWrapper implements UIComponent {
    private final Label label;

    public LabelWrapper(Label label) {
        this.label = label;
    }

    @Override
    public String getText() {
        return label.getText();
    }

    @Override
    public void setText(String text) {
        label.setText(text);
    }

    @Override
    public void setTextFill(Color color) {
        label.setTextFill(color);
    }
} 