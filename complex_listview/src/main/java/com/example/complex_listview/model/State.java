package com.example.complex_listview.model;

public class State {
    private String name;

    private String capital;

    private int flagResource;

    public State(String name, String capital, int flagResource) {
        this.name = name;
        this.capital = capital;
        this.flagResource = flagResource;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public void setFlagResource(int flagResource) {
        this.flagResource = flagResource;
    }

    public String getName() {
        return name;
    }

    public String getCapital() {
        return capital;
    }

    public int getFlagResource() {
        return flagResource;
    }

}
