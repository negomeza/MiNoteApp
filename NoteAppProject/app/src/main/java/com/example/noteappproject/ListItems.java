package com.example.noteappproject;

public class ListItems {
    private String title;
    private String description;
    private boolean isImportant;
    private boolean isStarted;
    private boolean isFinished;

    public ListItems(String _title, String _description, boolean _isImportant, boolean _isStarted, boolean _isFinished) {
        this.title = _title;
        this.description = _description;
        this.isImportant = _isImportant;
        this.isStarted = _isStarted;
        this.isFinished = _isFinished;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isImportant() {
        return isImportant;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public boolean isFinished() {
        return isFinished;
    }
}